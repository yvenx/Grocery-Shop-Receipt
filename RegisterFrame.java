//author Yifan Xu
//Grocery Shop Receipt
//5-27-2014

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.text.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class RegisterFrame extends JFrame {
	private ShoppingCart items;
	private JTextField total;
	private static final Color defaultColor  = null;
	
	public RegisterFrame(Catalog products)      {
		// create frame and order list
		setTitle(products.getName());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		items = new ShoppingCart();
		
		jTextFieldList = new ArrayList<JTextField>();
		
		// set up text field with order total
		total = new JTextField("$0.00", 10);
		total.setBackground(Color.WHITE);
		total.setEditable(false);
		total.setEnabled(false);
		total.setDisabledTextColor(Color.BLACK);
		JPanel p = new JPanel();
		
		p.setForeground(Color.BLACK);
		
		p.setBackground(defaultColor);
		JLabel l = new JLabel("Total balance ");
		l.setForeground(Color.BLACK);
		p.add(l);
		p.add(total);
		add(p, BorderLayout.NORTH);
		
		p = new JPanel(new GridLayout(40, 1));//new GridLayout is set by how many items in the grocery file
		p.setBackground(defaultColor);
		for (int i = 0; i < products.size(); i++)
			addItem(products.get(i), p);
			add(new JScrollPane(p));
		
		p = new JPanel();
			add(checkOutButton(), BorderLayout.EAST);
		
		p = new JPanel();
			add(cancelButton(), BorderLayout.WEST);
		
		p = new JPanel();
			add(makeCheckBoxPanel(), BorderLayout.SOUTH);
		// adjust size to just fit
		pack();
	}
	
	JCheckBox checkBox;
	// Sets up the "discount" checkbox for the frame
	private JPanel makeCheckBoxPanel() {
		JPanel p = new JPanel();
		p.setBackground(defaultColor);
		checkBox = new JCheckBox("Membership Card");
			p.add(checkBox);
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				items.setDiscount(checkBox.isSelected());
				updateTotal();
			}
		});
		return p;
	}
	
	private JPanel checkOutButton(){//checks out order
		JPanel p = new JPanel();
		p.setBackground(defaultColor);
		final JButton cb = new JButton("Check Out");
			p.add(cb);
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				double amount = items.getTotal();
				String total = (NumberFormat.getCurrencyInstance().format(amount));
				String prodsInfo = items.getMessage();
				String discountInfo = items.getDiscount();
				String display = prodsInfo + "\n" + "Your Total is: " + total;
				int choice = JOptionPane.showConfirmDialog(null, display + "\n" + discountInfo + "\n" + "Check Out?");
				String receiptName = null;
				if(choice == JOptionPane.YES_OPTION){
					try {
						receiptName = printReceipt();//detemines canceling order inbetween		
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(receiptName!=null){
						JOptionPane.showMessageDialog(null,"Thank you for shopping at Safeway! " );
					}
				}
				else if(choice == JOptionPane.CANCEL_OPTION) {
					items.cancel();
					updateTotal();
					for(JTextField jtf:jTextFieldList){
						jtf.setText("");
					}
					checkBox.setSelected(false);
				}else {
					//do nothing
				}
			}
		});
		return p;
	}
	
	private JPanel cancelButton(){//cancels/restarts order
		JPanel p = new JPanel();
		p.setBackground(defaultColor);
		final JButton cb = new JButton("Cancel");
			p.add(cb);
		cb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					items.cancel();
					updateTotal();
					for(JTextField jtf:jTextFieldList){
						jtf.setText("");
					}
					checkBox.setSelected(false);
					JOptionPane.showMessageDialog(null,"Thank you for shopping at Safeway! " );
				}
		});
		return p;
	}
	
	protected String printReceipt()  throws FileNotFoundException{
		//takes from PrintReceipt to transfer info to text file
		String name = JOptionPane.showInputDialog(null, "Please type in today's date" + "\n" + 
				"Enter this date in mmddyyyy format. For example, 08171975 for August 17, 1975.");
		if(name == null){
			//do nothing
		}else{
			PrintReceipt.generateReceipt(name, items);
		}
		return name;
	}
	
	List<JTextField> jTextFieldList;//tries for cancel in order
	
	// adds a product to the panel, including a textfield for user input of
	// the quantity
	private void addItem(final Item product, JPanel p) {
		JPanel sub = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sub.setBackground(null);
		final JTextField quantity = new JTextField(4);
		jTextFieldList.add(quantity); 
		quantity.setHorizontalAlignment(SwingConstants.CENTER);
		quantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateItem(product, quantity);
				quantity.transferFocus();
			}
		});
		quantity.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				updateItem(product, quantity);
			}
		});
		sub.add(quantity);
		JLabel l = new JLabel("" + product);
		l.setForeground(Color.BLACK);
		sub.add(l);
		p.add(sub);
	}
	
	// When the user types a new value into one of the quantity fields,
	// parse the input and update the ShoppingCart.  Display an error
	// message if text is not a number or is negative.
	private void updateItem(Item product, JTextField quantity) {
		int number;
		String text = quantity.getText().trim();
		try {
			number = Integer.parseInt(text);
		} catch (NumberFormatException error) {
			number = 0;
		}
		if (number <= 0 && text.length() > 0) {
			Toolkit.getDefaultToolkit().beep();
			quantity.setText("");
			number = 0;
		}
		items.add(new ItemOrder(product, number));
		updateTotal();
	}
	
	// reset the text field for order total
	private void updateTotal() {
		double amount = items.getTotal();
		total.setText(NumberFormat.getCurrencyInstance().format(amount));
	}

}
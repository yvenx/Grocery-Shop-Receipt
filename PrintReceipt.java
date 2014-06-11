//author Yifan Xu
//Grocery Shop Receipt
//5-27-2014

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.NumberFormat;

public class PrintReceipt{
	public static void generateReceipt(String fileName, ShoppingCart items) throws FileNotFoundException{
		//transfers order info to the text file
		PrintStream out = new PrintStream(fileName + ".txt");
		double amount = items.getTotal();
		double discount = items.getTotal() *0.1;
		String total = (NumberFormat.getCurrencyInstance().format(amount));
		String finalDiscount = (NumberFormat.getCurrencyInstance().format(discount));
		String prodsInfo = items.getMessage();
		String discountInfo = items.getDiscount();
		//basic receipt format in text file
			out.println("Safeway Inc.");
			out.println("Safeway Store, 3605-21, 7340 35th Ave NE, Seattle, WA 98115");
			out.println();
			out.println("Groceries: ");
			out.println();
			out.println("\n" + prodsInfo);
			out.println();
			out.println("\n" + discountInfo);
			out.println();
			out.println("\n" + "Members save: " + finalDiscount);
			out.println("Your total is: " + total + "\n");
			out.println();
			out.println("\n" + "Thank you for shopping at Safeway!");
	}
}

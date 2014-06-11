//author Yifan Xu
//Grocery Shop Receipt
//5-27-2014

import java.util.ArrayList;

public class ShoppingCart {
	private ArrayList<ItemOrder> itemOrders;//Constructors 
	private boolean isDiscount;
	
	
	public ShoppingCart(){//Empty List of Orders called itemOrders taken from ItemOrder
		itemOrders = new ArrayList<ItemOrder>();
		isDiscount = false;
		
	}
	
	public void add(ItemOrder itemOrder){//determines orders and the replacements for the orders
		ItemOrder duplicate =null;
		for(ItemOrder io:itemOrders){//searched up on oracle website
			if(io.item.name.equals(itemOrder.item.name)){//comparing previous, current, next orders using io from ArrayList class
				duplicate = io;//replaces if order is changed
			}
		}
		if(duplicate!=null){
			itemOrders.remove(duplicate);//removes the order and it's price if it is changed
		}
		itemOrders.add(itemOrder);//normal adding of order
	}
	
	public void setDiscount(boolean isDiscount){//determines discount using boolean
		this.isDiscount = isDiscount;
		
	}

	public double getTotal(){//determines total cost
		double total = 0.0;
		for(ItemOrder io:itemOrders){//comparing from order to order
			total = total+io.getPrice();//accumulates price from order to order
		}
		if(isDiscount){
			total = total*.9;//case of a discount
		}
		return total;
	}
	
	public String getMessage(){//message display in check out order
		StringBuilder items = new StringBuilder();
		for(ItemOrder io:itemOrders){
			if(io.quantity != 0) {
					items.append(io.toString() + "\r\n");
			}
		}
		return items.toString();
	}
	
	public String getDiscount(){
		StringBuilder discount = new StringBuilder();
		String temp = isDiscount?"Yes":"No";
		discount.append("10% Membership Discount: " + temp + "\n");//determines if there is discount upon checking box
		return discount.toString();
	}

	public void cancel() {//method for canceling order
		itemOrders.clear();
		isDiscount=false;
	}
	
//ShoppingCart() 
//Constructor that creates an empty list of item orders.
 
//add(item order)
//Adds an item order to the list, replacing any previous order for this item with the new order. The parameter will be of type ItemOrder.
 
//setDiscount(value)
//Sets whether or not this order gets a discount (true means there is a discount, false means no discount).
 
//getTotal() 
//Returns the total cost of the shopping cart.
}
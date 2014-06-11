//author Yifan Xu
//Grocery Shop Receipt
//5-27-2014

import java.text.NumberFormat;

public class ItemOrder{
	public Item item;//constructors
	public int quantity;
	
	public ItemOrder(Item item, int quantity){
		this.item=item;//references 
		this.quantity=quantity;
	}
	
	public double getPrice(){
		return item.priceFor(quantity);//using priceFor from Item class to find cost for a order
	}
	
	public Item getItem(){
		return item;//a reference for the item in the order
	}
	public String toString(){//rounds the price for items in the check out 
		return item.name + " " + quantity + " " + (NumberFormat.getCurrencyInstance().format(getPrice()));
	}
}

//ItemOrder(item, quantity)	
//Constructor that creates an item order for the given item and given quantity. The quantity will be an integer.

//getPrice()	
//Returns the cost for this item order.

//getItem()	
//Returns a reference to the item in this order.
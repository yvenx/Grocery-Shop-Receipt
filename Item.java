//author Yifan Xu
//Grocery Shop Receipt
//5-27-2014

import java.text.*;

public class Item{//constructors
	public String name;
	public double price;
	public int bulkQuantity;
	public double bulkPrice;
		
	public Item(String name, double price){
		this.name=name;
		this.price=price;
	}
	
	public Item(String name, double price, int bulkQuantity, double bulkPrice){//, int quantity){
		if(price<0.0){
			throw new IllegalArgumentException();//throws illegal argument
		}
		this.name=name;//more reference constructors
		this.price=price;
		this.bulkQuantity=bulkQuantity;
		this.bulkPrice=bulkPrice;
	}
	
	public double priceFor(int quantity){//finds the price given the quantity of a item
		if(bulkQuantity==0){//normal one by one item buying if under the bulk quantity amount
			return price *quantity;
		}
		int remainder = quantity%bulkQuantity;//determines how much is left over if bought in bulk quantities
		int finalBulk = (int)Math.floor(quantity/bulkQuantity);//finalBulk determines exactly how much of the bulkQuantity
		//there are while excluding the remainder of single items
			return price *remainder + finalBulk*bulkPrice;//returns the price of items if there is bulk quantity
	}
	
	public String toString(){
		NumberFormat nf = NumberFormat.getCurrencyInstance();//includes $ sign in menu order
		String priceText = nf.format(price);
		String bulkPriceText = nf.format(bulkPrice);
	
		if(bulkQuantity>0){//if statement determining where the string will be a bulk or normal situation
			
			String newLine = String.format("    ");//format in the register menu
			return String.format(this.name + newLine + priceText + newLine + bulkQuantity + "/" + bulkPriceText);
		}
		else {
			String newLine = String.format("    ");
			return String.format(this.name + newLine + priceText + newLine);
		}
	}
}
//Item(name, price)	
//Constructor that takes a name and a price as arguments. The name will be a String and the price will be a double. 
//Should throw an IllegalArgumentException if price is negative.

//Item(name, price, bulk quantity, bulk price)	
//Constructor that takes a name and a single-item price and a bulk quantity and a bulk price as arguments. 
//The name will be a String and the quantity will be an integer and the prices will be doubles. 
//Should throw an IllegalArgumentException if any number is negative.

//priceFor(quantity)	
//Returns the price for a given quantity of the item (taking into account bulk price, if applicable). 
//Quantity will be an integer. Should throw an IllegalArgumentException if quantity is negative.

//toString()	
//Returns a String representation of this item: name followed by a comma and space followed by price. 
//If this has a bulk price, then you should append an extra space and 
//a parenthesized description of the bulk pricing that has the bulk quantity, the word “for” and the bulk price.
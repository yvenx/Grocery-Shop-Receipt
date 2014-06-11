//author Yifan Xu
//Grocery Shop Receipt
//5-27-2014

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class CatalogReader {
		 public static List<Item> read(String fileName) throws FileNotFoundException{
			 Scanner input = new Scanner(new File(fileName));//takes in scanner to scan grocery file
			 String productName;
			 double price = 0.0;
			 int quantity = 0;
			 double quantityPrice = 0.0;
			 List prodList = new ArrayList<Item>();//intializes arraylist
			 while (input.hasNextLine()) { //finds the info on items in grocery file
				   String nextLine = input.nextLine();
				   Scanner currentLine = new Scanner(nextLine);
				   productName = currentLine.next(); //name
				   price = currentLine.nextDouble(); //price
				   quantity = currentLine.nextInt(); //quantity
				   quantityPrice = currentLine.nextDouble();
				   
				   Item item = new Item(productName, price, quantity, quantityPrice);//creates object of format in register menu
				   prodList.add(item);//adds item onto register menu
		 } 	
			return prodList;//returns the output
			 
		 }
}
//text file reader
//each line can be used to generate an Item object
//read a txt file
//for each line of record, generate an Item object. The put the Item object into an ArrayList.
//from the Catalog reader, you can get a list of Items.
//Catalog list
// CatalogReader will return a list of Items.
//for each Item inside the list returned from CatalogReader:
//list.add( item);
//List<Item> read(String fileName)

//Title: 
//Address/phone:
//Items:
//Tax:
//Total:
//Cash/Credit:
//Change:
//Number of Items:

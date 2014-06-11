//author Yifan Xu
//Grocery Shop Receipt
//5-27-2014

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class ShoppingMain {
    public static void main(String[] args) throws FileNotFoundException {
    	String fileName = "grocery.txt";
    	List<Item> prodList = CatalogReader.read(fileName);
        Catalog list = new Catalog("Safeway Groceries");
        for(Item item:prodList){
			  list.add(item);
        }
        
        RegisterFrame f = new RegisterFrame(list);
        f.setVisible(true);
    }
}

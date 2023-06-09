package warehouse;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.jar.Attributes.Name;

public class Warehouse {
    private Sector[] sectors;
    
    // Initializes every sector to an empty sector
    public Warehouse() {
        sectors = new Sector[10];

        for (int i = 0; i < 10; i++) {
            sectors[i] = new Sector();
        }
    }
    
    /**
     * Provided method, code the parts to add their behavior
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void addProduct(int id, String name, int stock, int day, int demand) {
        evictIfNeeded(id);
        addToEnd(id, name, stock, day, demand);
        fixHeap(id);
    }

    /**
     * Add a new product to the end of the correct sector
     * Requires proper use of the .add() method in the Sector class
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    private void addToEnd(int id, String name, int stock, int day, int demand) {

       Product newProduct = new Product(id, name, stock, day, demand);

       int isolate = id %10;

       sectors[isolate].add(newProduct);

    }

    /**
     * Fix the heap structure of the sector, assuming the item was already added
     * Requires proper use of the .swim() and .getSize() methods in the Sector class
     * @param id The id of the item which was added
     */
    private void fixHeap(int id) {

        int isolate = id % 10;

        Sector sector = sectors[isolate];

        sector.swim(sector.getSize());

    } 
    
    /**
     * Delete the least popular item in the correct sector, only if its size is 5 while maintaining heap
     * Requires proper use of the .swap(), .deleteLast(), and .sink() methods in the Sector class
     * @param id The id of the item which is about to be added
     */
    private void evictIfNeeded(int id) {

       int isolate = id % 10;
       int j = sectors[isolate].getSize();
       
        if (j == 5) {
            sectors[isolate].swap(1, j);
            sectors[isolate].deleteLast();
            sectors[isolate].sink(1);
        } else
                return;
        }
    
        


    /**
     * Update the stock of some item by some amount
     * Requires proper use of the .getSize() and .get() methods in the Sector class
     * Requires proper use of the .updateStock() method in the Product class
     * @param id The id of the item to restock
     * @param amount The amount by which to update the stock
     */
    public void restockProduct(int id, int amount) {
        int isolate = id % 10;
        Sector sector = sectors[isolate];

        for (int i = 1; i <= sector.getSize(); i++) {
            Product product = sector.get(i);
            if (product.getId() == id) {
                product.updateStock(amount);
                break;
            }
        }
     

    }


    
    /**
     * Delete some arbitrary product while maintaining the heap structure in O(logn)
     * Requires proper use of the .getSize(), .get(), .swap(), .deleteLast(), .sink() and/or .swim() methods
     * Requires proper use of the .getId() method from the Product class
     * @param id The id of the product to delete
     */
    public void deleteProduct(int id) {
        int isolate = id % 10;
        Sector sector = sectors[isolate];

        for (int i = 1; i <= sector.getSize(); i++) {
            if (sector.get(i).getId() == id) {
                sector.swap(i, sector.getSize());
                sector.deleteLast();
                sector.sink(i);
                break;
            }
        }
    }
    
    /**
         * Simulate a purchase order for some product
         * Requires proper use of the getSize(), sink(), get() methods in the Sector class
         * Requires proper use of the getId(), getStock(), setLastPurchaseDay(), updateStock(), updateDemand() methods
         * @param id The id of the purchased product
         * @param day The current day
         * @param amount The amount purchased
         */

        public void purchaseProduct(int id, int day, int amount) {

            int isolate = id % 10;
            Sector sector = sectors[isolate];

            for (int i = 1; i <= sector.getSize(); i++) {
                Product product = sector.get(i);
                if (product.getId() == id) {
                    product.setLastPurchaseDay(day);
                    product.updateStock(-amount);
                    product.updateDemand(amount);
                    sector.sink(i);
                    break;
                }
            }
            
        }
        
        /**
         * Construct a better scheme to add a product, where EMPTY SPACES ARE ALWAYS FILLED 
         * @param id The id of the item to add
         * @param name The name of the item to add
         * @param stock The stock of the item to add
         * @param day The day of the item to add
         * @param demand Initial demand of the item to add
         */
        
    public void betterAddProduct(int id, String name, int stock, int day, int demand) {

        int isolate = id % 10; 
        Product newProduct = new Product(id, name, stock, day, demand);

    if (sectors[isolate].getSize() != 5) {
        addToEnd(id, name, stock, day, demand);
        fixHeap(id);

        return;
    }
        
    int current  = (isolate + 1) % 10;

    while(current != isolate) {
        if (sectors[current].getSize() != 5) {
            sectors[current].add(newProduct);
            sectors[current].swim(sectors[current].getSize());

            return;
        }

        current = (current + 1) % 10;
    }

    addProduct(id, name, stock, day, demand);
}

    public String toString() {
        String warehouseString = "[\n";

        for (int i = 0; i < 10; i++) {
            warehouseString += "\t" + sectors[i].toString() + "\n";
        }
        
        return warehouseString + "]";
    }

    public Sector[] getSectors () {
        return sectors;
    }
}

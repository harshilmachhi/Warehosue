package warehouse;

/*
 * Use this class to put it all together.
 */ 

 /*Have all previous parts working to test this method.
DO NOT have to change any code in your Warehouse class to get the correct output. You are simply writing a main method which puts all the previous steps together and answers all types of queries at once.
The input file is formatted as follows:
An integer n representing the number of queries
n lines, each containing either an add, restock, purchase, or delete query
Add queries are identical to the ones from Restock.
Restock queries are identical to the ones from Restock.
Purchase queries are identical to the ones from PurchaseProduct
Delete queries are identical to the ones from DeleteProduct
Fill in the Everything.java file to read from args[0] and write to args[1]. Create a new Warehouse object, then operate on your Warehouse object responding to each query. Finally, you can simply print out your Warehouse object to your output file. For example, if your Warehouse object is named w, call StdOut.println(w).
The output will be a text representation of your warehouse, showing the Products in each sector, specifically their names, stocks and popularities.
Here is an example of a correct “everything.out” file obtained from running the Everything.java file with the command line arguments “everything.in” and “everything.out” in that order. */
public class Everything {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

        Warehouse warehouses = new Warehouse();

        int num = StdIn.readInt();

        for(int i = 0; i < num; i++) {
            String query = StdIn.readString();
            if (query.equals("add")) {
                int day = StdIn.readInt();
                int id = StdIn.readInt();
                String name = StdIn.readString();
                int stock = StdIn.readInt();
                int demand = StdIn.readInt();

                warehouses.addProduct(id, name, stock, day, demand);
            }
            if(query.equals("restock")) {
                int id = StdIn.readInt();
                int amount = StdIn.readInt();

                warehouses.restockProduct(id, amount);
            }

            if(query.equals("purchase")) {
                int day = StdIn.readInt();
                int id = StdIn.readInt();
                int amount = StdIn.readInt();

                warehouses.purchaseProduct(id, day, amount);
            }

            if(query.equals("delete")) {
                int id = StdIn.readInt();

                warehouses.deleteProduct(id);
            }
        }

        StdOut.println(warehouses);
        
    }
}

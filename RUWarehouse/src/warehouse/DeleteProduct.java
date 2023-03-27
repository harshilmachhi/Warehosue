package warehouse;

/*
 * Use this class to test the deleteProduct method.
 */ 
public class DeleteProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

        Warehouse warehouses = new Warehouse();

        int num = StdIn.readInt();
        for(int i=0; i<num; i++) {

            String query = StdIn.readString();

            if(query.equals("add")) {
                int day = StdIn.readInt();
                int id = StdIn.readInt();
                String name = StdIn.readString();
                int stock = StdIn.readInt();
                int demand = StdIn.readInt();

                warehouses.addProduct(id, name, stock, day, demand);

            }
            if(query.equals("delete")) {
                int id = StdIn.readInt();

                warehouses.deleteProduct(id);
            }
        }
        StdOut.println(warehouses);


    }
}

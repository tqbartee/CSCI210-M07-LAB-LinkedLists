public class DTInventoryBasic {

    // =========================================================================
    // 📦 ITEM CLASS WITH EMBEDDED POINTER
    // =========================================================================
    // next points to the next element in the sequence
    // This is the "old-fashioned way" where the next is embedded with the item
    // The "new" way will use Java Generics with the Java LinkedList class
    static class InventoryItem {
        private String skuName;
        private int quantity;
        private double price;
        public InventoryItem next;

        // Create an inventory item
        public InventoryItem(String skuName, int quantity, double price) {
            this.skuName = skuName;
            this.quantity = quantity;
            this.price = price;
            this.next = null; // New items start unlinked
        }

        // This prints an individual item
        // It does not print the next component - that is a pointer to a
        // memory address with the next item in the list
        @Override
        public String toString() {
            return String.format("%s (Qty: %d) @ $%.2f each", skuName, quantity, price);
        }
    }

    // =========================================================================
    // ⚙️ EXECUTABLE RUNTIME CONTEXT
    // =========================================================================
    public static void main(String[] args) {

        // Create several standalone inventory items
        InventoryItem item1 = new InventoryItem("Greeting Cards", 200, 1.25);
        InventoryItem item2 = new InventoryItem("Glass Vases", 45, 1.25);
        InventoryItem item3 = new InventoryItem("Storage Bins", 60, 1.25);
        InventoryItem item4 = new InventoryItem("Snack Chips", 150, 1.25);
        InventoryItem item5 = new InventoryItem("Cleaning Spray", 90, 1.25);

        // TODO: Link the items together manually into a chain (Linear List)
        // (Students will write the lines below)

        // TODO: Create the head and tail references of the list
        // (Students will write the lines below)

        // Print the linear linked list by traversing the links
        System.out.println("--- Traversing the Manual Node Chain (Linear List) ---");
        InventoryItem current = head;
        while (current != null) {
            System.out.println(current.toString());
            current = current.next;
        }
    }
}

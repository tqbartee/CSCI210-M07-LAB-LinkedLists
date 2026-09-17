import java.util.LinkedList;

public class DTInventoryJavaLinkedList {

    // =========================================================================
    // 📦 MODERNIZED ITEM CLASS (No Manual Pointers)
    // =========================================================================
    static class InventoryItem {
        private String skuName;
        private int quantity;
        private double price;

        // Create an inventory item
        public InventoryItem(String skuName, int quantity, double price) {
            this.skuName = skuName;
            this.quantity = quantity;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format("%s (Qty: %d) @ $%.2f each", skuName, quantity, price);
        }
    }

    // =========================================================================
    // ⚙️ MODERN EXECUTABLE RUNTIME CONTEXT
    // =========================================================================
    public static void main(String[] args) {

        // 1. Create several standalone inventory items
        InventoryItem item1 = new InventoryItem("Greeting Cards", 200, 1.25);
        InventoryItem item2 = new InventoryItem("Glass Vases", 45, 1.25);
        InventoryItem item3 = new InventoryItem("Storage Bins", 60, 1.25);
        InventoryItem item4 = new InventoryItem("Snack Chips", 150, 1.25);
        InventoryItem item5 = new InventoryItem("Cleaning Spray", 90, 1.25);

        // 2. Instantiate Java's built-in LinkedList class utilizing Generics
        LinkedList<InventoryItem> restockingList = new LinkedList<>();

        // TODO
        //  3. Add items to the front of the list (item1 and then item2)
        // use addFirst method

        // TODO
        // 4. Add items to the back of the list (item3, item4, item5)
        // use addLast method

        // 5. Print the complete list before removals
        System.out.println("--- Full LinkedList Before Removals ---");
        for (InventoryItem currentItem : restockingList) {
            System.out.println(currentItem.toString());
        }
        System.out.println(); // Formatting space

        // =====================================================================
        // ✂️ DELETION OPERATORS (Processing the head and tail)
        // =====================================================================
        // 6. Remove one element from the front (Head)
        InventoryItem removedFromFront = restockingList.removeFirst();
        System.out.println("Removed from Front (Head): " + removedFromFront);

        // TODO
        // 7. Remove one element from the back (Tail)
        // use removeLast, parallel the removedFromFront code
        System.out.println("Removed from Back (Tail): " + removedFromBack);
        System.out.println(); // Formatting space

        // 8. Print the remaining list to verify structural mutations
        System.out.println("--- Remaining LinkedList After Removals ---");
        for (InventoryItem currentItem : restockingList) {
            System.out.println(currentItem.toString());
        }
    }
}

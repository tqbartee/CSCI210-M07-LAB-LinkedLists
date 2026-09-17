import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DTInventoryJavaLinkedListTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Test InventoryItem constructor and toString formatting")
    void testInventoryItemInitializationAndToString() {
        DTInventoryJavaLinkedList.InventoryItem item = new DTInventoryJavaLinkedList.InventoryItem("Greeting Cards", 200, 1.25);
        
        assertNotNull(item, "Inventory item should be successfully instantiated");
        assertEquals("Greeting Cards (Qty: 200) @ $1.25 each", item.toString(), "toString output should match expected format");
    }

    @Test
    @DisplayName("Test LinkedList addFirst and addLast operations")
    void testLinkedListAddFirstAndAddLast() {
        LinkedList<DTInventoryJavaLinkedList.InventoryItem> list = new LinkedList<>();

        DTInventoryJavaLinkedList.InventoryItem item1 = new DTInventoryJavaLinkedList.InventoryItem("Greeting Cards", 200, 1.25);
        DTInventoryJavaLinkedList.InventoryItem item2 = new DTInventoryJavaLinkedList.InventoryItem("Glass Vases", 45, 1.25);
        DTInventoryJavaLinkedList.InventoryItem item3 = new DTInventoryJavaLinkedList.InventoryItem("Storage Bins", 60, 1.25);

        list.addFirst(item1); // [item1]
        list.addFirst(item2); // [item2, item1]
        list.addLast(item3);  // [item2, item1, item3]

        assertEquals(3, list.size(), "List size should be 3");
        assertSame(item2, list.getFirst(), "First element should be item2 (Glass Vases)");
        assertSame(item3, list.getLast(), "Last element should be item3 (Storage Bins)");
        assertSame(item1, list.get(1), "Middle element (index 1) should be item1 (Greeting Cards)");
    }

    @Test
    @DisplayName("Test LinkedList removeFirst and removeLast operations")
    void testLinkedListRemoveFirstAndRemoveLast() {
        LinkedList<DTInventoryJavaLinkedList.InventoryItem> list = new LinkedList<>();

        DTInventoryJavaLinkedList.InventoryItem item1 = new DTInventoryJavaLinkedList.InventoryItem("Greeting Cards", 200, 1.25);
        DTInventoryJavaLinkedList.InventoryItem item2 = new DTInventoryJavaLinkedList.InventoryItem("Glass Vases", 45, 1.25);
        DTInventoryJavaLinkedList.InventoryItem item3 = new DTInventoryJavaLinkedList.InventoryItem("Storage Bins", 60, 1.25);

        list.addLast(item1);
        list.addLast(item2);
        list.addLast(item3);

        DTInventoryJavaLinkedList.InventoryItem removedFirst = list.removeFirst();
        assertSame(item1, removedFirst, "removeFirst should return item1");
        assertEquals(2, list.size(), "List size should be 2 after first removal");

        DTInventoryJavaLinkedList.InventoryItem removedLast = list.removeLast();
        assertSame(item3, removedLast, "removeLast should return item3");
        assertEquals(1, list.size(), "List size should be 1 after second removal");

        assertSame(item2, list.getFirst(), "Remaining item should be item2");
    }

    @Test
    @DisplayName("Test LinkedList iteration sequence")
    void testLinkedListIteration() {
        LinkedList<DTInventoryJavaLinkedList.InventoryItem> list = new LinkedList<>();

        DTInventoryJavaLinkedList.InventoryItem item1 = new DTInventoryJavaLinkedList.InventoryItem("Greeting Cards", 200, 1.25);
        DTInventoryJavaLinkedList.InventoryItem item2 = new DTInventoryJavaLinkedList.InventoryItem("Glass Vases", 45, 1.25);

        list.addFirst(item1);
        list.addFirst(item2);

        StringBuilder sb = new StringBuilder();
        for (DTInventoryJavaLinkedList.InventoryItem item : list) {
            sb.append(item.toString()).append("\n");
        }

        String expected = "Glass Vases (Qty: 45) @ $1.25 each\nGreeting Cards (Qty: 200) @ $1.25 each\n";
        assertEquals(expected, sb.toString(), "Iteration sequence should match insertion order via addFirst");
    }

    @Test
    @DisplayName("Test DTInventoryJavaLinkedList main execution output")
    void testMainExecutionOutput() {
        DTInventoryJavaLinkedList.main(new String[]{});

        String output = outputStreamCaptor.toString().trim();

        // Verify full list section
        assertTrue(output.contains("--- Full LinkedList Before Removals ---"));
        assertTrue(output.contains("Glass Vases (Qty: 45) @ $1.25 each"));
        
        // Verify deletion messages
        assertTrue(output.contains("Removed from Front (Head): Glass Vases (Qty: 45) @ $1.25 each"));
        assertTrue(output.contains("Removed from Back (Tail): Cleaning Spray (Qty: 90) @ $1.25 each"));

        // Verify remaining list section
        assertTrue(output.contains("--- Remaining LinkedList After Removals ---"));
        assertTrue(output.contains("Greeting Cards (Qty: 200) @ $1.25 each"));
        assertTrue(output.contains("Storage Bins (Qty: 60) @ $1.25 each"));
        assertTrue(output.contains("Snack Chips (Qty: 150) @ $1.25 each"));

        // Ensure removed items are not in the remaining section
        int remainingIndex = output.indexOf("--- Remaining LinkedList After Removals ---");
        String remainingOutput = output.substring(remainingIndex);
        assertFalse(remainingOutput.contains("Glass Vases"), "Glass Vases should be removed from remaining list");
        assertFalse(remainingOutput.contains("Cleaning Spray"), "Cleaning Spray should be removed from remaining list");
    }
}

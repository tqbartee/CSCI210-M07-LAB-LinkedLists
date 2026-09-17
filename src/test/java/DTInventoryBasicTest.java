import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DTInventoryBasicTest {

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
    @DisplayName("Test InventoryItem constructor and initial null next reference")
    void testInventoryItemInitialization() {
        DTInventoryBasic.InventoryItem item = new DTInventoryBasic.InventoryItem("Greeting Cards", 200, 1.25);
        
        assertNotNull(item, "Inventory item should be successfully instantiated");
        assertNull(item.next, "New inventory item should have next set to null initially");
    }

    @Test
    @DisplayName("Test InventoryItem toString formatting")
    void testInventoryItemToString() {
        DTInventoryBasic.InventoryItem item = new DTInventoryBasic.InventoryItem("Glass Vases", 45, 1.25);
        String expectedString = "Glass Vases (Qty: 45) @ $1.25 each";
        
        assertEquals(expectedString, item.toString(), "toString output should match expected format");
    }

    @Test
    @DisplayName("Test manual node linking and traversal")
    void testManualNodeLinkingAndTraversal() {
        DTInventoryBasic.InventoryItem item1 = new DTInventoryBasic.InventoryItem("Greeting Cards", 200, 1.25);
        DTInventoryBasic.InventoryItem item2 = new DTInventoryBasic.InventoryItem("Glass Vases", 45, 1.25);
        DTInventoryBasic.InventoryItem item3 = new DTInventoryBasic.InventoryItem("Storage Bins", 60, 1.25);

        // Manually link items
        item1.next = item2;
        item2.next = item3;

        // Traverse chain
        List<String> itemsInChain = new ArrayList<>();
        DTInventoryBasic.InventoryItem current = item1;
        while (current != null) {
            itemsInChain.add(current.toString());
            current = current.next;
        }

        assertEquals(3, itemsInChain.size(), "Traversal should visit 3 nodes");
        assertEquals("Greeting Cards (Qty: 200) @ $1.25 each", itemsInChain.get(0));
        assertEquals("Glass Vases (Qty: 45) @ $1.25 each", itemsInChain.get(1));
        assertEquals("Storage Bins (Qty: 60) @ $1.25 each", itemsInChain.get(2));
    }

    @Test
    @DisplayName("Test head and tail references in linked chain")
    void testHeadAndTailReferences() {
        DTInventoryBasic.InventoryItem item1 = new DTInventoryBasic.InventoryItem("Greeting Cards", 200, 1.25);
        DTInventoryBasic.InventoryItem item2 = new DTInventoryBasic.InventoryItem("Glass Vases", 45, 1.25);
        DTInventoryBasic.InventoryItem item3 = new DTInventoryBasic.InventoryItem("Storage Bins", 60, 1.25);

        item1.next = item2;
        item2.next = item3;

        DTInventoryBasic.InventoryItem head = item1;
        DTInventoryBasic.InventoryItem tail = item3;

        assertSame(item1, head, "Head reference should point to first item");
        assertSame(item3, tail, "Tail reference should point to last item");
        assertNull(tail.next, "Tail.next should be null");
    }

    @Test
    @DisplayName("Test node insertion into existing chain")
    void testNodeInsertion() {
        DTInventoryBasic.InventoryItem item1 = new DTInventoryBasic.InventoryItem("Greeting Cards", 200, 1.25);
        DTInventoryBasic.InventoryItem item3 = new DTInventoryBasic.InventoryItem("Storage Bins", 60, 1.25);
        item1.next = item3;

        // Insert item2 between item1 and item3
        DTInventoryBasic.InventoryItem item2 = new DTInventoryBasic.InventoryItem("Glass Vases", 45, 1.25);
        item2.next = item1.next;
        item1.next = item2;

        assertSame(item2, item1.next, "item1.next should now point to item2");
        assertSame(item3, item2.next, "item2.next should now point to item3");
    }

    @Test
    @DisplayName("Test DTInventoryBasic main execution output")
    void testMainExecutionOutput() {
        DTInventoryBasic.main(new String[]{});

        String output = outputStreamCaptor.toString().trim();

        assertTrue(output.contains("--- Traversing the Manual Node Chain (Linear List) ---"));
        assertTrue(output.contains("Greeting Cards (Qty: 200) @ $1.25 each"));
        assertTrue(output.contains("Glass Vases (Qty: 45) @ $1.25 each"));
        assertTrue(output.contains("Storage Bins (Qty: 60) @ $1.25 each"));
        assertTrue(output.contains("Snack Chips (Qty: 150) @ $1.25 each"));
        assertTrue(output.contains("Cleaning Spray (Qty: 90) @ $1.25 each"));
    }
}

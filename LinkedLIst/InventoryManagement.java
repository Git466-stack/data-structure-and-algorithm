class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Item next;

    // Constructor
    public Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    private Item head;

    // Add an item at the beginning
    public void addAtBeginning(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    // Add an item at the end
    public void addAtEnd(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newItem;
    }

    // Add an item at a specific position
    public void addAtPosition(int position, String itemName, int itemId, int quantity, double price) {
        if (position <= 0) {
            System.out.println("Invalid position!");
            return;
        }
        Item newItem = new Item(itemName, itemId, quantity, price);
        if (position == 1) {
            addAtBeginning(itemName, itemId, quantity, price);
            return;
        }
        Item temp = head;
        for (int i = 1; i < position - 1; i++) {
            if (temp == null) {
                System.out.println("Position out of bounds!");
                return;
            }
            temp = temp.next;
        }
        newItem.next = temp.next;
        temp.next = newItem;
    }

    // Remove an item by Item ID
    public void removeByItemId(int itemId) {
        if (head == null) {
            System.out.println("Inventory is empty!");
            return;
        }
        if (head.itemId == itemId) {
            head = head.next;
            System.out.println("Item with ID " + itemId + " removed successfully.");
            return;
        }
        Item temp = head;
        while (temp.next != null && temp.next.itemId != itemId) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Item with ID " + itemId + " not found!");
        } else {
            temp.next = temp.next.next;
            System.out.println("Item with ID " + itemId + " removed successfully.");
        }
    }

    // Update quantity of an item by Item ID
    public void updateQuantityByItemId(int itemId, int newQuantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                temp.quantity = newQuantity;
                System.out.println("Quantity updated for Item ID " + itemId);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + itemId + " not found!");
    }

    // Search for an item by Item ID or Item Name
    public void searchItem(String itemName, Integer itemId) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if ((itemName != null && temp.itemName.equalsIgnoreCase(itemName)) ||
                (itemId != null && temp.itemId == itemId)) {
                System.out.println("[Name: " + temp.itemName + ", ID: " + temp.itemId +
                        ", Quantity: " + temp.quantity + ", Price: " + temp.price + "]");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("Item not found!");
        }
    }

    // Calculate total value of inventory
    public void calculateTotalValue() {
        double totalValue = 0;
        Item temp = head;
        while (temp != null) {
            totalValue += temp.quantity * temp.price;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: " + totalValue);
    }

    // Sort the inventory by Item Name (Ascending)
    public void sortByNameAscending() {
        if (head == null || head.next == null) return;
        head = mergeSort(head, true, true);
        System.out.println("Inventory sorted by Item Name (Ascending).");
    }

    // Sort the inventory by Price (Descending)
    public void sortByPriceDescending() {
        if (head == null || head.next == null) return;
        head = mergeSort(head, false, false);
        System.out.println("Inventory sorted by Price (Descending).");
    }

    // Merge sort implementation
    private Item mergeSort(Item head, boolean sortByName, boolean ascending) {
        if (head == null || head.next == null) return head;

        Item middle = getMiddle(head);
        Item nextToMiddle = middle.next;

        middle.next = null;

        Item left = mergeSort(head, sortByName, ascending);
        Item right = mergeSort(nextToMiddle, sortByName, ascending);

        return merge(left, right, sortByName, ascending);
    }

    private Item merge(Item left, Item right, boolean sortByName, boolean ascending) {
        if (left == null) return right;
        if (right == null) return left;

        Item result;
        int comparison = sortByName
                ? left.itemName.compareToIgnoreCase(right.itemName)
                : Double.compare(left.price, right.price);
        boolean condition = ascending ? (comparison <= 0) : (comparison >= 0);

        if (condition) {
            result = left;
            result.next = merge(left.next, right, sortByName, ascending);
        } else {
            result = right;
            result.next = merge(left, right.next, sortByName, ascending);
        }
        return result;
    }

    private Item getMiddle(Item head) {
        if (head == null) return null;
        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Display all items
    public void displayItems() {
        if (head == null) {
            System.out.println("Inventory is empty!");
            return;
        }
        System.out.println("Inventory:");
        Item temp = head;
        while (temp != null) {
            System.out.println("[Name: " + temp.itemName + ", ID: " + temp.itemId +
                    ", Quantity: " + temp.quantity + ", Price: " + temp.price + "]");
            temp = temp.next;
        }
    }
}

public class InventoryManagement {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Adding items
        inventory.addAtEnd("Laptop", 1, 5, 1200.00);
        inventory.addAtBeginning("Mouse", 2, 50, 20.00);
        inventory.addAtPosition(2, "Keyboard", 3, 30, 45.00);

        // Display all items
        inventory.displayItems();

        // Update quantity
        inventory.updateQuantityByItemId(2, 100);

        // Search for an item
        inventory.searchItem("Mouse", null);

        // Calculate total inventory value
        inventory.calculateTotalValue();

        // Sort by name and display
        inventory.sortByNameAscending();
        inventory.displayItems();

        // Sort by price and display
        inventory.sortByPriceDescending();
        inventory.displayItems();

        // Remove an item
        inventory.removeByItemId(3);

        // Display remaining items
        inventory.displayItems();
    }
}

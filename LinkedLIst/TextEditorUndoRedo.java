class TextEditorState {
    String content; // Text content of the state
    TextEditorState next; // Pointer to the next state (redo)
    TextEditorState prev; // Pointer to the previous state (undo)

    // Constructor
    public TextEditorState(String content) {
        this.content = content;
        this.next = null;
        this.prev = null;
    }
}

class TextEditor {
    private TextEditorState head; // The first state
    private TextEditorState tail; // The last state
    private TextEditorState current; // The current state
    private int size; // Current size of the history
    private final int MAX_SIZE = 10; // Maximum allowed history size

    // Constructor
    public TextEditor() {
        this.head = null;
        this.tail = null;
        this.current = null;
        this.size = 0;
    }

    // Add a new state to the text editor
    public void addState(String content) {
        TextEditorState newState = new TextEditorState(content);

        // If the list is empty, initialize it
        if (head == null) {
            head = tail = current = newState;
        } else {
            // Remove any forward history (redo states) after the current state
            current.next = null;

            // Add the new state to the end of the list
            newState.prev = current;
            current.next = newState;
            current = newState;
            tail = newState;
        }

        // Increment the size
        size++;

        // Maintain the size limit
        if (size > MAX_SIZE) {
            head = head.next;
            head.prev = null;
            size--;
        }

        System.out.println("State added: " + content);
    }

    // Undo to the previous state
    public void undo() {
        if (current == null || current.prev == null) {
            System.out.println("Cannot undo. Already at the earliest state.");
            return;
        }
        current = current.prev;
        System.out.println("Undo performed. Current state: " + current.content);
    }

    // Redo to the next state
    public void redo() {
        if (current == null || current.next == null) {
            System.out.println("Cannot redo. Already at the latest state.");
            return;
        }
        current = current.next;
        System.out.println("Redo performed. Current state: " + current.content);
    }

    // Display the current state
    public void displayCurrentState() {
        if (current == null) {
            System.out.println("No current state.");
        } else {
            System.out.println("Current state: " + current.content);
        }
    }

    // Display all states (for debugging or visualization)
    public void displayAllStates() {
        TextEditorState temp = head;
        System.out.println("All states:");
        while (temp != null) {
            System.out.println(temp.content + (temp == current ? " <-- [Current]" : ""));
            temp = temp.next;
        }
    }
}

public class TextEditorUndoRedo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        // Simulate adding states
        editor.addState("Hello");
        editor.addState("Hello, World");
        editor.addState("Hello, World!");
        editor.displayCurrentState();

        // Undo and redo
        editor.undo();
        editor.displayCurrentState();

        editor.redo();
        editor.displayCurrentState();

        // Add new state after undo
        editor.undo();
        editor.addState("Hello, Universe");
        editor.displayCurrentState();

        // Add more states to exceed the limit
        for (int i = 1; i <= 10; i++) {
            editor.addState("State " + i);
        }

        // Display all states
        editor.displayAllStates();
    }
}

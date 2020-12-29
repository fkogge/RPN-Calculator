package RPN_Calculator;

/**
 * The Stack class constructs and defines methods for a linked list
 * implementation of a stack of a primitive data type.
 *
 * @param <T> Data type
 * @author  Francis Kogge
 * @version 1.0
 */
public class Stack<T> {
    private Node top; // Top of the stack
    private int count; // Number of elements in the stack

    /**
     * The Stack constructor initializes an empty stack. An empty stack will
     * have no elements and its top node will be null.
     */
    public Stack() {
        top = null;
        count = 0;
    }

    /**
     * Returns a boolean value indicating whether or not the stack is empty.
     *
     * @return True if the stack is empty
     *         False if the stack is not empty
     */
    public boolean empty() {
        return top == null;
    }

    /**
     * Adds an element to the top of the stack.
     *
     * @param value Element to be added to the stack
     */
    public void push(T value) {
        // Assign the top value to a new node pointing to the previous
        // top value of the stack
        top = new Node(value, top);
        count++;
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return Element at the top of the stack
     */
    public T pop() {
        // If the stack is empty
        if (empty()) {
            throw new IllegalArgumentException("Can't pop an empty stack.");
        // If the stack is not empty
        } else {
            // Captures the value at the top of the stack
            T target = peek();
            // Set the top of the stack to the next element in the stack
            top = top.next;
            count--;
            return target;
        }
    }

    /**
     * Returns the value at the top of the stack. The element will remain
     * at the top of the stack after execution.
     *
     * @return Value at the top of the stack
     */
    public T peek() {
        // If the stack is empty
        if (empty()) {
            throw new IllegalArgumentException("Can't peek an empty stack.");
        // If the stack is not empty
        } else {
            // Returns the value at the top of the stack
            return top.value;
        }
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return Size of the stack
     */
    public int size() {
        return count;
    }

    /**
     * Defines the properties of a node in a linked list.
     */
    private class Node {
        T value; // Value of the created node
        Node next; // Node which the created node will point to

        /**
         * The Node constructor creates a node and points that node to the
         * next node, which is passed in as an argument.
         *
         * @param value Value of the created node
         * @param next  Node which the created node will point to
         */
        Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}

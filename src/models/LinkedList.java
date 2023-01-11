package models;

public class LinkedList<T> {

    public Node<T> head;

    // Linked list Node.
    // This inner class is made static
    // so that main() can access it
    public static class Node<T> {

        public T data;
        public Node<T> next;

        // Constructor
        public Node(T d) {
            this.data = d;
            this.next = null;
        }
    }

    // Method to insert a new node
    public LinkedList<T> insert(T data) {
        // Create a new node with given data
        Node<T> new_node = new Node<T>(data);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (this.head == null) {
            this.head = new_node;
        } else {
            // Else traverse till the last node
            // and insert the new_node there
            Node<T> last = this.head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
        }

        // Return the this by head
        return this;
    }
}
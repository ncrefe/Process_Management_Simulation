import models.LinkedList;
import models.LinkedList.Node;
import models.ProcessQueue;

/* Muhammed Efe INCIR 270201029
In our homework, we aimed to keep the main program as short as possible in order to be legible and to make our code
the best we can for the sake of cleanliness. And we developed it to be more precise and realistic values in statistics.
*/

public class ProcessManagementSimulation {
    public static void main(String[] args) {
        LinkedList<ProcessQueue> linkedlist = new LinkedList<ProcessQueue>();

        linkedlist.insert(new ProcessQueue(1, 3));
        linkedlist.insert(new ProcessQueue(2, 5));
        linkedlist.insert(new ProcessQueue(3, 10));

        Node<ProcessQueue> currNode = linkedlist.head;

        while (currNode != null) {
            currNode.data.print();
            currNode = currNode.next;
        }
    }
}

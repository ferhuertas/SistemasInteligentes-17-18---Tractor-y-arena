import java.util.PriorityQueue;

public class Frontier {

    //Working with queues
    private PriorityQueue<Node> queue;

    public void createFrontier() {
        queue = new PriorityQueue<Node>();
    }

    public void insertNode(Node n) {
        queue.add(n);
    }

    public Node removeFirst() {
        return queue.poll();
    }

    public PriorityQueue<Node> getQueue() {
        return queue;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }


}

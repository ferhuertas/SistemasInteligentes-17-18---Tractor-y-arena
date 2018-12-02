import java.util.PriorityQueue;
import java.util.LinkedList;
public class Frontier {

	//Working with queues
	private PriorityQueue<Node> queue;
	
	public void createFrontier() {
		queue = new PriorityQueue<Node> ();
		
	}
	public void insertNode(Node n){
		queue.add(n);
	}
	public void removeFirst(){
		queue.peek();
	}
	public PriorityQueue<Node> getQueue(){
		return queue;
	}
	public void isEmpty(){
		queue.isEmpty();
	}
	
	
	//Working with linked list
	private LinkedList<Node> list;
	public void listinsertNode(Node n){
		list.add(n);
	}
	public void listcreateFrontier() {
		list = new LinkedList<Node>();
		
	}
	public void listremoveFirstNode(){
		list.removeFirst();
	}
	public void listisEmpty(){
		list.isEmpty();
	}
	
}

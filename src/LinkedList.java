 class Node<T> {
	
	public T data;
	public Node<T> next;
	
	public Node (T val) {
		data = val;
		next = null;
	}
}

public class LinkedList<T> implements List<T> {

	private Node<T> head;
	private Node<T> current;

	
	public LinkedList () {
		head = current = null;
	}

	public boolean empty() {
		return head == null;
	}

	public boolean full() {
		return false;
	}

	public void findFirst() {
		current = head;
	}

	public void findNext() {
		current = current.next;
	}

	public boolean last() {
		return current.next == null;
	}

	public T retrieve() {
		return current.data;
	}

	public void update(T e) {
		current.data = e;
	}

	public void insert(T e) {
		Node<T> p = new Node<T>(e);
		if (head == null) {
			current = head = p;
		}
		else {
			p.next = current.next;
			current.next = p;
			current = p;
		}
	}

	public void remove() {
		if (current == head) {
			head = head.next;
			current = current.next;
		}
		else {
			Node<T> p = head;

			while (p.next != current)
				p = p.next;

			p.next = current.next;
		}

		if (current.next != null)
			current = current.next;
		else
			current = head;
	}

}
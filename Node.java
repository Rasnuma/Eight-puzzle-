import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class Node implements Comparable<Node> {
	static int ACTION_COST = 1;

	State state;
	Node parent;
	int depth;
	int cost;

	public Node(State s, Node p, int d, int c) {
		// super();
		this.state = s;
		this.parent = p;
		this.depth = d;
		cost = c;
	}

	public static Node A_star(State init) {

		Node root = new Node(init, null, 0, 0);

		PriorityQueue<Node> fringe = new PriorityQueue<Node>();

		HashSet<State> closedList = new HashSet<State>();

		fringe.add(root);
		closedList.add(init);

		while (fringe.isEmpty() == false) {
			Node front = fringe.poll();

			State s = front.state;

			if (s.goal_test())
				return front;

			ArrayList<State> successors = s.get_successors();

			if (successors != null)
				for (State state : successors)
					if (state != null && !closedList.contains(state)) {
						Node child = new Node(state, front, front.depth + 1,front.cost + ACTION_COST);

						fringe.add(child);
						closedList.add(state);
					}

			/*Node a=fringe.poll();
			int n= a.cost+a.heuristic();
			System.out.println(a.state+"\n:"+n);

			break;*/ //used to confirm the priority queue is functioning properly
		}

		return null;
	}

	public int heuristic() {
		return state.manhattan_distance();
	}

	public static void print_soln(Node n) {

		// Modify this method to print the solution in the actual order, rather
		// than the reverse order
		Stack<Node> x = new Stack<Node>();

		while (n != null) {
			x.push(n);
			n = n.parent;
		}

		while (!x.isEmpty())
		{
			System.out.println(x.pop().state);
		}
	}

	@Override
	public int compareTo(Node another) {
		return ((cost + heuristic()) - (another.cost + another.heuristic()));
	}
}

import java.util.ArrayList;
import java.util.Objects;

public class State {
	int board[][], bR, bC;
    static int goal[][]= new int[3][3];
    static int nR, nC;

	public State(int[][] board) {
		// super();
        //generateGoal();
		this.board = board;
	}

	public static void generateGoal(){
        int k=1;
        for (int i=0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                goal[i][j] = k;
                k++;
            }
        }
        goal[2][2]=0;
    }

    public static void generateRandomGoal(){
		goal = Main.generate_random_board();
	}

	public void search_State(int n) {
		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 3; ++j)
				if (board[i][j] == n) {
					bR = i;
					bC = j;

					return;
				}
	}

	public static void search_goal(int n) {
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j)
                if (goal[i][j] == n) {
                    nR = i;
                    nC = j;

                    return;
                }
    }

	public State move_up() {

		search_State(0);

		if (bR == 0)
			return null;

		int newBoard[][] = new int[3][3];

		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 3; ++j)
				newBoard[i][j] = board[i][j];

		int temp = newBoard[bR][bC];
		newBoard[bR][bC] = newBoard[bR - 1][bC];
		newBoard[bR - 1][bC] = temp;

		return new State(newBoard);
	}

	public State move_down() {

		search_State(0);

		if (bR == 2)
			return null;

		int newBoard[][] = new int[3][3];

		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 3; ++j)
				newBoard[i][j] = board[i][j];

		int temp = newBoard[bR][bC];
		newBoard[bR][bC] = newBoard[bR + 1][bC];
		newBoard[bR + 1][bC] = temp;

		return new State(newBoard);
	}

	public State move_left() {

		search_State(0);

		if (bC == 0)
			return null;

		int newBoard[][] = new int[3][3];

		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 3; ++j)
				newBoard[i][j] = board[i][j];

		int temp = newBoard[bR][bC];
		newBoard[bR][bC] = newBoard[bR][bC - 1];
		newBoard[bR][bC - 1] = temp;

		return new State(newBoard);
	}

	public State move_right() {

		search_State(0);

		if (bC == 2)
			return null;

		int newBoard[][] = new int[3][3];

		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 3; ++j)
				newBoard[i][j] = board[i][j];

		int temp = newBoard[bR][bC];
		newBoard[bR][bC] = newBoard[bR][bC + 1];
		newBoard[bR][bC + 1] = temp;

		return new State(newBoard);
	}

	public boolean goal_test() {

        if (equals(new State(goal)))
            return true;

		return false;
	}

	public ArrayList<State> get_successors() {
	    ArrayList<State> successors = new ArrayList<>();

	    if (move_right()!=null)
	        successors.add(move_right());

	    if (move_down()!=null)
	        successors.add(move_down());

	    if (move_left()!=null)
	        successors.add(move_left());

	    if (move_up()!=null)
	        successors.add(move_up());

		return successors;
	}

	public String toString() {

		String s=null;

		for (int i=0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                if (s!=null)
                    s = s+board[i][j]+" ";
                else
                    s = board[i][j]+" ";
            }
            s+="\n";
        }

		// Complete here

		return s;
	}

	public int manhattan_distance() {
	    
	    int h = 0;

		for (int i=1; i<9; i++)
        {
            search_State(i);
            search_goal(i);
            if(bC!=nC)
                h+=Math.abs(bC-nC);
            if (bR!=nR)
                h+=Math.abs(bR-nR);
        }

		return h;
	}

	@Override
	public boolean equals(Object obj) {
		// return hashCode() == ((State) obj).hashCode();

		State rhs = (State) obj;

		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 3; ++j)
				if (board[i][j] != rhs.board[i][j])
					return false;

		return true;
	}

	@Override
	public int hashCode() {

		// return Arrays.deepHashCode(board);

		return Objects
				.hash(board[0][0], board[0][1], board[0][2], board[1][0],
						board[1][1], board[1][2], board[2][0], board[2][1],
						board[2][2]);
	}
}

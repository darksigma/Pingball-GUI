package warmup;

import java.util.ArrayList;

public class Board {
	public static final double EPSILON = 0.000001;
	
	private BoardItem[][] board;
	private int xMax;
	private int yMax;
	private ArrayList<BoardItem> contains;
	
	public Board(int xMax, int yMax){
		this.xMax = xMax;
		this.yMax = yMax;
		
		this.board = new BoardItem[yMax][xMax];
		this.contains = new ArrayList<BoardItem>();
	}
	
	public void addToBoard(BoardItem item){
		board[item.getY()][item.getX()] = item;
		contains.add(item);
	}
	
	public int getXMax(){
		return xMax;
	}
	
	public int getYMax(){
		return yMax;
	}
	
	public void step(double timeStep){
		
		for(BoardItem item : contains){
			board[item.getY()][item.getX()] = null;
			item.step(timeStep);
			board[item.getY()][item.getX()] = item;
		}
	}
	
	public String toString(){
		StringBuffer result = new StringBuffer();
		
		result.append('-');
		for(BoardItem item : board[0]){
			result.append('-');
		}
		result.append('-');
		result.append('\n');
		
		for(BoardItem[] row : board){
			result.append('|');
			for (BoardItem item : row){
				if (item != null){
					result.append(item.charRep());
				}else{
					result.append(' ');
				}
			}
			result.append('|');
			result.append('\n');
		}
		
		result.append('-');
		for(BoardItem item : board[0]){
			result.append('-');
		}
		result.append('-');
		
		return result.toString();
	}
}

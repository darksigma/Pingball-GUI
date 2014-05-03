package warmup;

public interface BoardItem {
	public int getX();
	public int getY();
	public void step(double timeStep);
	public char charRep();

}

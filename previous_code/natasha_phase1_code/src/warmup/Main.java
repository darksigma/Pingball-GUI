package warmup;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TODO: put documentation for your class here
 */
public class Main {
    static Board brd;
    static class Update extends TimerTask {
        public void run() {
            brd.update(.01);
            System.out.println(brd);
        }
    }
    /**
     * TODO: describe your main function's command line arguments here
     */
    public static void main(String[] args) {
        brd = new Board(20, 20, new Ball(30, 22), 4, 4);
        Timer timer = new Timer();
        timer.schedule(new Update(), 0, 50);
    }

}
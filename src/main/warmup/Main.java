package warmup;

import physics.*;

public class Main {

    public static final double BALL_RADIUS = 0.5;
    public static final double WIDTH = 20; // in L
    public static final double HEIGHT = 20; // in L
    public static final double VX = 2;
    public static final double VY = 7;
    public static final double SIMULATION_TIMESTEP = 0.001;
    public static final double FRAMERATE = 20; // fps
    
    public static void main(String[] args) {
        Ball ball = new Ball(WIDTH / 2, HEIGHT / 2, BALL_RADIUS, VX, VY);
        Wall[] walls = new Wall[]{
            new Wall(0, 0, WIDTH, 0), // bottom
            new Wall(0, 0, 0, HEIGHT), // left
            new Wall(WIDTH, 0, WIDTH, HEIGHT), // right
            new Wall(0, HEIGHT, WIDTH, HEIGHT) // top
        };
        while (true) {
            long start = System.nanoTime();
            simulate(1 / FRAMERATE, ball, walls);
            printBoard(ball);
            long mid = System.nanoTime();
            try {
                Thread.sleep((long) (1000 / FRAMERATE - (mid - start) / 1e6));
            } catch (InterruptedException e) {
                // do nothing
            }
            long end = System.nanoTime();
            System.out.println((1 / ((double) (end - start) / 1e9)) + " FPS");
        }
    }

    public static void printBoard(Ball ball) {
        int ballX = (int) Math.floor(ball.getBall().getCenter().x());
        int ballY = (int) Math.floor(ball.getBall().getCenter().y());
        for (int i = -1; i <= HEIGHT; i++) {
            for (int j = -1; j <= WIDTH; j++) {
                if (i == -1 || i == HEIGHT || j == -1 || j == HEIGHT) {
                    System.out.print(".");
                }
                else if (i == ballY && j == ballX) {
                    System.out.print("O");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------------");
    }

    private static void simulate(double time, Ball ball, Wall[] walls) {
        // do gravity and friction, but not for warmup
        Wall reflectWall = null;
        double tColl = Double.MAX_VALUE;
        for (Wall wall: walls) {
            double wallCollisionTime = wall.timeUntilCollision(ball);
            if (wallCollisionTime < tColl) {
                tColl = wallCollisionTime;
                reflectWall = wall;
            }
        }
        if (tColl > time) {
            ball.move(time);
        } else {
            ball.move(tColl);
            reflectWall.reflect(ball);
            simulate(time - tColl, ball, walls);
        }
    }

    private static class Ball {

        private Circle ball;
        private Vect velocity;
        
        public Ball(double x, double y, double r, double vx, double vy) {
            ball = new Circle(x, y, r);
            velocity = new Vect(vx, vy);
        }

        public Circle getBall() { return ball; }

        public Vect getVelocity() { return velocity; }

        public void setVelocity(Vect v) { velocity = v; }

        public void move(double dt) {
            ball = new Circle(ball.getCenter().plus(velocity.times(dt)), ball.getRadius());
        }
    }
    
    private static class Wall {

        private final LineSegment lineSegment;
        private final Circle[] circles;

        public Wall(double startX, double startY, double endX, double endY) {
            lineSegment = new LineSegment(startX, startY, endX, endY);
            circles = new Circle[]{
                new Circle(startX, startY, 0),
                new Circle(endX, endY, 0)
            };
        }

        public double timeUntilCollision(Ball ball) {
            double minTime = Geometry.timeUntilWallCollision(lineSegment, ball.getBall(), ball.getVelocity());
            for (Circle circle: circles) {
                double cTime = Geometry.timeUntilCircleCollision(circle, ball.getBall(), ball.getVelocity());
                if (cTime < minTime) {
                    minTime = cTime;
                }
            }
            return minTime;
        }

        public void reflect(Ball ball) {
            Circle reflectCircle = null;
            double minTime = Geometry.timeUntilWallCollision(lineSegment, ball.getBall(), ball.getVelocity());
            for (Circle circle: circles) {
                double cTime = Geometry.timeUntilCircleCollision(circle, ball.getBall(), ball.getVelocity());
                if (cTime < minTime) {
                    reflectCircle = circle;
                    minTime = cTime;
                }
            }
            final Vect newVelocity;
            if (reflectCircle == null) {
                newVelocity = Geometry.reflectWall(lineSegment, ball.getVelocity());
            } else {
                newVelocity = Geometry.reflectCircle(reflectCircle.getCenter(), ball.getBall().getCenter(), ball.getVelocity());
            }
            ball.setVelocity(newVelocity);
        }
    }

}

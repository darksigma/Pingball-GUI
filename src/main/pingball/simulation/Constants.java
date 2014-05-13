package pingball.simulation;

/**
 * Constants for this game that can be accessed by all clasees
 */
public class Constants {

    public static final double BALL_RADIUS = 0.25;

    public static final int BOARD_WIDTH = 20;

    public static final int BOARD_HEIGHT = 20;
    
    public static final double DEFAULT_GRAVITY = 25.0;
    
    public static final double DEFAULT_FRICTION_MU1 = 0.025;
    
    public static final double DEFAULT_FRICTION_MU2 = 0.025;

    public static final double SIMULATION_TIMESTEP = 0.001;

    public static final double WALL_REFLECTION_COEFF = 1.0;

    public static final double BUMPER_REFLECTION_COEFF = 1.0;

    public static final double FLIPPER_REFLECTION_COEFF = 0.95;

    public static final double FLIPPER_LENGTH = 2.0;
    
    public static final double FLIPPER_MAX_ROTATE = Math.PI / 2.0;
    
    public static final double FLIPPER_ANGULAR_VELOCITY = 6.0 * Math.PI;
    
    public static final double ABSORBER_REFLECTION_COEFF = 0.0;
    
    public static final double ABSORBER_SHOOT_VELOCITY = 50.0;
    
    public static final double SPAWNER_SHOOT_VELOCITY = 50.0;

    public static final double BALL_DENSITY = 1.0;
    
    public static final double DEGREE_TO_RADIAN_0 = 0.0;
    
    public static final double DEGREE_TO_RADIAN_90 = Math.PI / 2.0;
    
    public static final double DEGREE_TO_RADIAN_180 = Math.PI;
    
    public static final double DEGREE_TO_RADIAN_270 = 1.5 * Math.PI;

    public static final double PORTAL_REFLECTION_COEFF = 0;

    public static final double BALLSPAWNER_REFLECTION_COEFF = 1.0;
    
}

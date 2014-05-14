package pingball.gui;

public class BoardGUITest {

	/*
	 * BoardGUI testing strategy (game display):
	 * 
	 * 	Balls:
	 * 
	 * 		The balls appeared to move correctly. In juggler.pb, we saw the balls would shoot straight
	 * out from the bottom right corner of the absorber at a high velocity, which was expected. The balls
	 * appeared to bounce off walls and bumpers at perfectly (angle of incidence = angle of reflection).
	 * The balls also bounced off the flippers properly from any part of the flipper. This was especially
	 * evident in FlipperWorld.pb, which is filled with flippers. We would pause the GUI many times to
	 * observe the collision behavior. Balls seemed to touch the gadgets just on their surface before
	 * bouncing off, which is what we expected.
	 * 		Additionally, in the PerpetualWorldWIthKeypresses.pb board, the balls appeared to collide
	 * with each other correctly when they slowed down, bouncing on the bottom wall. Faster balls would
	 * bounce off with less energy than the slower ball, due to the conservation of energy and momentum.
	 * In addition, the balls didn't slow down very much when bouncing on the bottom wall because the
	 * friction values of 0.025 are so low. Overall, the ball physics appears to be correct.
	 * 		
	 * 		The number of balls in a game works correctly. When flipperWorldWithKeypresses.pb is played in 
	 * single-player mode, we noticed that the number of balls stays the same since the absorber cannot
	 * reduce the number of balls. In server mode, the number of balls decreases due to absorbers in
	 * other boards. In addition, when an absorber is activated by a keypress, we notice that the number
	 * of visible balls increases by 1 if the absorber was holding a ball. This is what we expected.
	 * With ball spawner, everytime a ball collides with the ballspawner, a new ball is created from
	 * the center of the spawner. This is what we expected and what we saw in jugglerWithSpawner.pb.
	 * 		In server mode, if a player pauses their game, it remains conected to the boards it was
	 * connected to. We observed that balls were leaving other boards through the connected walls but
	 * weren't going anywhere. Then, when the paused board resumed the game, all the balls that left the
	 * other boards showed up on the paused board through connected walls. This is what we expected to
	 * happen.
	 * 
	 * 
	 * 	Circle, Square, Triangle Bumpers/Flippers:
	 * 
	 * 		All the bumpers appeared to work correctly. We observed that they have no action by linking
	 * keypresses and trigger/actions to them in files such as jugglerWithKeypresses.pb and
	 * perpetualWithKeypresses.pb. When we clicked the respective key, we would observe nothing with
	 * the bumpers. Their triggers also worked correctly. Bumpers are triggered by collisions. When
	 * balls collided with any bumper, the color would change. Keypresses would not change the color
	 * however, because kepresses induce action and not triggers. In addition, if a keypress triggered
	 * a bumper which was supposed to trigger the action of a flipper, the flipper wouldn't move when
	 * we hit the key because trigger/actions are not transitive like that. This worked correctly
	 * when tested with perpetualWithKeypresses.pb. Bumpers also appeared in the right location in the
	 * display.
	 * 		Flippers appeared to be working correctly as well. From the flipperWorld.pb
	 * and perpetualWorldWithKeypresses.pb and jugglerWithKeypresses.pb files, we saw that trigger-actions
	 * and keypress-actions caused the flippers to either flip up or down/do their action. If a key
	 * was held down for a prolonged period of time, the flipper would oscillate up/down (depending
	 * on how it flipped), as it would keep performing its action. Flipper collisions worked accurately
	 * as well, since it appeared that balls would bounce off at an angle depending on the flipper's
	 * rotational movement. The balls also never went through the flippers, colliding exactly on the
	 * thin surface. The flippers appeared in the right locations in the display, and with the correct color.
	 * 
	 * 	Absorber:
	 * 
	 * 		To test absorbers, we observed several of the sampleBoard files, juggler.pb, jugglerPortal.pb,
	 * perpetual.pb, flipperWorld.pb, jugglerPortal1.pb, and jugglerPortal2.pb. In all files, we saw
	 * the balls enter the absorber from any position on the absorber, and they were shot straight up
	 * at a high velocity. We knew the velocity was consistently the same by observing the ball motion
	 * in juggler.pb, as the balls all had the same initial trajectories towards the absorber. The balls
	 * all left the bottom right corner of the absorber, as expected. In addition, when the balls entered
	 * the absorber, we observed the absorber would flicker its color, just as the bumpers would upon
	 * collisions.
	 * 		While running the jugglerPortal1.pb and jugglerPortal2.pb boards, we observed the case where
	 * a ball got stuck in the absorber in jugglerPortal2.pb. We observed the ball bouncing around in
	 * the absorber (shown as in front of the absorber image), and all other balls on the board were
	 * absorbed into the absorber. This is because the ball was trying to be shot out but got stuck,
	 * so all remaining balls got absorbed and nothing could be shot out. This is what the spec wanted
	 * and what we observed, so our absorber can handle unusual cases well.
	 * 		The absorber color and location was correct in all cases. Overall, our absorbers appeared
	 * to work as expected.
	 * 
	 * 	Portals:
	 * 		
	 * 		To test portals in single-player mode, we used jugglerPortal.pb. In this file, there are
	 * two portals that link to each other. When a ball entered one portal, it would exit the other
	 * portal at the same angle that it entered the first portal. This is what we expected. In addition,
	 * if two balls entered the portal sequentially, they would exit the second portal sequentially,
	 * conserving their angle and velocity. They both appeared as black in the display, showing that
	 * they are active, which is what we expected.
	 * 
	 * 		To test portals in server mode, we used jugglerPortal1.pb and jugglerPortal2.pb. If we
	 * started one board and not the other, then the portals would appear gray, showing they are
	 * inactive, and balls would simply pass over them and not go through them. When we start the
	 * other board (regardless of if we connect the walls), the portals on the portals on both boards
	 * turned black and balls could pass through. When balls would pass through a portal, they would
	 * come through the portal they were linked to in sequential order. This is what we expected. We
	 * paused the games and counted the balls and observed that the number of balls remained conserved.
	 * If two balls entered the a different linked portal at the same time, each exited the opposite
	 * portal. This was hard to create, but we came close and the portals/balls behaved as expected.
	 * If one board paused while the other board was running, then the balls would still be able to
	 * go through the portal on the unpaused board. When the paused board resumed its game, the balls
	 * would all come through its portal, as expected. The portals functioned the same way when we
	 * had the boards joined by walls as well, which is what we expected.
	 * 
	 * 		The ball motion (angle, velocity) entering and exiting portals was preserved. 
	 * See ball test above for further details.
	 * 	
	 * 
	 * 	Walls:
	 * 	
	 * 		In single-player mode, walls appeared black, showing they aren't transparent and balls
	 * can't pass through. This worked as expected, as balls that collided with the walls would
	 * bounce off at the same angle of incidence with the same velocity. They were all contained in
	 * the board. We used sampleBoard files to test this, as well as juggler.pb and flipperWorld.pb.
	 * After pausing and resuming, the wall states wouldn't change and would remain black. Balls would
	 * start moving from where they left off, regardless of their position relative to the walls.
	 * 
	 * 		In server mode:
	 * 		We tested the walls by connecting multiple board files, including juggler.pb,
	 * flipperWorld.pb, perpetual.pb, jugglerPortal1.pb, and jugglerPortal2.pb. When we connected
	 * two boards, we observed that their walls would change from black to gray (showing that the
	 * walls became transparent / balls can pass through to another board) displaying the name of
	 * the connected board. We expected this to happen. Balls would simply pass through transparent
	 * walls and enter the connected board at the same angle and velocity that they had when they left.
	 * All four walls can be connected to board(s), but one wall can only connect to one board, which
	 * is what we expected. Walls that aren't connected would remain black and balls couldn't pass
	 * through, as expected.
	 * 		When we paused the boards before connecting them, the boards would show they are connected
	 * immediately after resuming the games. When we paused a board while being connected, its wall
	 * state wouldn't change (similar to portal). Balls could still exit connected boards, but wouldn't
	 * show up in the paused board until the paused board resumed its game, after which all the balls would
	 * come through sequentially. This is what we expected to happen.
	 * 		When we restarted a board while it was connected to another board, it would disconnect its
	 * connection from the other board but would remain connected to the server, so it could still connect
	 * to other boards. This would be reflected in the other boards as well, as their walls would become
	 * black and non-transparent when connected boards restarted. This same behavior occurred when
	 * a board exited the game while connected to other boards. The connected boards would lose connection
	 * and their walls would become regular, non transparent walls, as expected.
	 * 		
	 * 		Visually, walls appear black until connected to other boards. Then, when connected, walls
	 * become light gray and correctly display the connected board's name inside the wall, centered.
	 * This is what we expected and what we saw.
	 * 
	 * 	Ball spawners (extra feature):
	 * 
	 * 		BallSpawners is a new game gadget, our extra game feature. We tested this with
	 * jugglerWithSpawner.pb. The ballSpawner game object has no action, but is triggered by 
	 * collisions/ balls colliding with the ballSpawner. Upon trigger, a new ball is shot out
	 * through the center of the ballspawner, which we saw. We also saw that the new ball
	 * wouldn't always move in the same direction, which is expected because its direction vector
	 * is randomized. The ball that had originally collided with the ball spawner would reflect off
	 * of the ball spawner, just as it would with a circle bumper. This is what was expected
	 * and what we saw, so the ball spawner appeared to work correctly.
	 * 		In terms of graphics, the ballspawner was in its correct location on the board with the
	 * right image, which is a hollow blue ring.
	 * 
	 * Please see additional tests in pingball.simulation.gadget for the non-visual tests for all
	 * the gadgets/board objects.
	 */
}

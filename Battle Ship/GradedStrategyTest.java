import student.micro.*;
import static org.assertj.core.api.Assertions.*;
import student.micro.battleship.*;

// -------------------------------------------------------------------------
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Keller Bice (kece05)
/**
 *  This script test the GradedStrategy to make sure
 *  that there is no bugs. Also, this script long because Web-Cat
 *  requires me to test every method and if statement
 *  There was not much I can change
 *
 *  @author Keller Bice (kece05)
 *  @version (2024.10.08)
 */
public class GradedStrategyTest extends TestCase {
    
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    private TestableGameState gameState;
    private TestableBoard     opponentsBoard;
    private GradedStrategy strategy;
    
    /**
     * Creates a new GradedStrategyTest test object.
     */
    public GradedStrategyTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp() {
        //Setting up the test Game
        gameState = new TestableGameState();
        opponentsBoard = gameState.getOpponentsBoard();
        strategy = new GradedStrategy();
        changeVariables();

        //Setting up the game
        setupOpponentShips();
        setupOpponentFirePattern();
    }

    /**
     * Sets up the opponents board to allow me to test
     * my strategy
     */
    private void setupOpponentShips() {
        opponentsBoard.placeShips(
            ". . . . . . BBBB",
            ". . DDD . . . . .",
            ". . . . . . . . . .",
            ". . . . . . . . PP", 
            "C . . . . . . . . .",
            "C . . . . . . . . .",
            "C . . . . . . . . .",
            "C . . . . . . S . .",
            "C . . . . . . S . .",
            ". . . . . . . S . ."
        );
    }

    
    /**
     * Sets up the opponents board to allow me to test
     * my strategy
     */
    private void setupOpponentFirePattern() {
        opponentsBoard.firePattern(
            "..........",
            "..........",
            "..........",
            "..........",
            "..........",
            "..........",
            "...*****..",   
            "..........",
            "..........",
            ".........."
        );
    }

    /**
     * Tests getName method
     */
    public void testGetName() {
        //Make sures that the method works properly
        assertThat(strategy.getName()).isEqualTo("AreaStrategy - kece05");
    }
    
    /**
     * Changes all the initialized varaibles to test
     * newGame and resetGame method
     */
    public void changeVariables() {
        strategy.setHitShip(true); 
        strategy.setXHit(0);
        strategy.setYHit(0); 
        strategy.setTDirection("NaN");
        strategy.setDirection("NaN");
        strategy.setXMoving(-1);
        strategy.setYMoving(-1);
        strategy.setRound(-1);
    }
    
    /**
     * Tests placeShips method
     */
    public void testPlaceShips() {
        //Testing if even rounds give the first layout
        strategy.setRound(0);
        strategy.placeShips(gameState);
        
        //Getting the first row of the layout and checking
        //if the char matches the last char of layout1
        String[] layoutRow1 = strategy.getLayout().split("\n");
        assertTrue(layoutRow1[0].endsWith("B"));
        
        //Testing if odd rounds give the second layout
        strategy.setRound(1);
        strategy.placeShips(gameState);
        
        //Getting the first row of the layout and checking
        //if the char matches the last char of layout2
        String[] layoutRow2 = strategy.getLayout().split("\n");
        assertTrue(layoutRow2[0].endsWith("S"));
    }
    
    /**
     * Tests newGame method
     */
    public void testNewGame() {
        //Changing variables values
        changeVariables();
        
        //Calling the newGame method
        strategy.newGame();
        
        //Checks to make sure the newGame method work properly
        assertThat(strategy.getHitShip()).isEqualTo(false);
        assertThat(strategy.getXHit()).isEqualTo(-1);
        assertThat(strategy.getYHit()).isEqualTo(-1);
        assertThat(strategy.getTDirection()).isEqualTo("");
        assertThat(strategy.getDirection()).isEqualTo("");
        assertThat(strategy.getXMoving()).isEqualTo(0);
        assertThat(strategy.getYMoving()).isEqualTo(0);
        assertThat(strategy.getterRound()).isEqualTo(0);
    }
    
    /**
     * Tests resetGame method
     */
    public void testResetGame() {
        //Changing variables values
        changeVariables();
        
        //Calling the newGame method
        strategy.resetGame(true);
        
        //Checks to make sure the newGame method work properly
        assertThat(strategy.getHitShip()).isEqualTo(false);
        assertThat(strategy.getXHit()).isEqualTo(-1);
        assertThat(strategy.getYHit()).isEqualTo(-1);
        assertThat(strategy.getTDirection()).isEqualTo("");
        assertThat(strategy.getDirection()).isEqualTo("");
        assertThat(strategy.getXMoving()).isEqualTo(0);
        assertThat(strategy.getYMoving()).isEqualTo(0);
        
        //Changing variables values
        changeVariables();
        
        //Calling the newGame method
        strategy.resetGame(false);
        
        //Checks to make sure the newGame method work properly
        assertThat(strategy.getHitShip()).isEqualTo(true);
        assertThat(strategy.getXHit()).isEqualTo(0);
        assertThat(strategy.getYHit()).isEqualTo(0);
        assertThat(strategy.getTDirection()).isEqualTo("NaN");
        assertThat(strategy.getDirection()).isEqualTo("NaN");
        assertThat(strategy.getXMoving()).isEqualTo(-1);
        assertThat(strategy.getYMoving()).isEqualTo(-1);
    }
    
    /**
     * Tests testRight method
     */
    public void testTestRight() {
        //Set a starting coordinates for testing and
        //Direction
        strategy.setXHit(8);
        strategy.setYHit(0); 
        strategy.setTDirection("R");
        
        //Calling the testRight method
        strategy.testRight(opponentsBoard);
        
        //Checks to make sure the testRight method work properly
        assertThat(strategy.getXMoving()).isEqualTo(9);
        assertThat(strategy.getYHit()).isEqualTo(0);
        
        //Set a starting coordinates for testing and
        //Direction
        strategy.setXHit(9);
        strategy.setYHit(0); 
        strategy.setTDirection("R");
        
        //Checks to make sure the testRight method work properly
        assertThat(strategy.testRight(opponentsBoard)).isEqualTo(false);
        assertThat(strategy.getTDirection()).isEqualTo("L");
        
        //Set a starting coordinates for testing and
        //Direction
        strategy.setXHit(0);
        strategy.setYHit(0); 
        strategy.setTDirection("");
                
        //Checks to make sure the testRight method work properly
        assertThat(strategy.testRight(opponentsBoard)).isEqualTo(false);
        assertThat(strategy.getTDirection()).isEqualTo("");
        
        //Set a starting coordinates for testing and
        //Direction and testing canFire
        opponentsBoard.fireAt(9, 9);
        strategy.setXHit(8);
        strategy.setYHit(9); 
        strategy.setTDirection("R");
        
        //Checks to make sure the testRight method work properly
        assertThat(strategy.testRight(opponentsBoard)).isEqualTo(false);
        assertThat(strategy.getTDirection()).isEqualTo("L");
        
    }
    
    /**
     * Tests testLeft method
     */
    public void testTestLeft() {
        //Set a starting coordinates for testing and
        //Direction
        strategy.setXHit(8);
        strategy.setYHit(0); 
        strategy.setTDirection("L");
        
        //Calling the testLeft method
        strategy.testLeft(opponentsBoard);
        
        //Checks to make sure the testLeft method work properly
        assertThat(strategy.getXMoving()).isEqualTo(7);
        assertThat(strategy.getYHit()).isEqualTo(0);
        
        //Set a starting coordinates for testing and
        //Direction
        strategy.setXHit(0);
        strategy.setYHit(0); 
        strategy.setTDirection("L");
                
        //Checks to make sure the testLeft method work properly
        assertThat(strategy.testLeft(opponentsBoard)).isEqualTo(false);
        assertThat(strategy.getTDirection()).isEqualTo("D");
        
        //Set a starting coordinates for testing and
        //Direction
        strategy.setXHit(0);
        strategy.setYHit(0); 
        strategy.setTDirection("");
                
        //Checks to make sure the testLeft method work properly
        assertThat(strategy.testLeft(opponentsBoard)).isEqualTo(false);
        assertThat(strategy.getTDirection()).isEqualTo("");
        
        //Set a starting coordinates for testing and
        //Direction and testing canFire
        opponentsBoard.fireAt(0, 9);
        strategy.setXHit(1);
        strategy.setYHit(9); 
        strategy.setTDirection("L");
        
        //Checks to make sure the testLeft method work properly
        assertThat(strategy.testLeft(opponentsBoard)).isEqualTo(false);
        assertThat(strategy.getTDirection()).isEqualTo("D");
    }
    
    /**
     * Tests testDown method
     */
    public void testTestDown() {
        //Set a starting coordinates for testing and
        //Direction
        strategy.setXHit(0);
        strategy.setYHit(8); 
        strategy.setTDirection("D");
        
        //Calling the testRight method
        strategy.testDown(opponentsBoard);
        
        //Checks to make sure the testDown method work properly
        assertThat(strategy.getXHit()).isEqualTo(0);
        assertThat(strategy.getYMoving()).isEqualTo(9);
        
        
        //Set a starting coordinates for testing and
        //Direction
        strategy.setXHit(0);
        strategy.setYHit(9); 
        strategy.setTDirection("D");
                
        //Checks to make sure the testDown method work properly
        assertThat(strategy.testDown(opponentsBoard)).isEqualTo(false);
        assertThat(strategy.getTDirection()).isEqualTo("U");
        
        //Set a starting coordinates for testing and
        //Direction
        strategy.setXHit(0);
        strategy.setYHit(0); 
        strategy.setTDirection("");
                
        //Checks to make sure the testDown method work properly
        assertThat(strategy.testDown(opponentsBoard)).isEqualTo(false);
        assertThat(strategy.getTDirection()).isEqualTo("");
        
        //Set a starting coordinates for testing and
        //Direction and testing canFire
        opponentsBoard.fireAt(9, 8);
        strategy.setXHit(9);
        strategy.setYHit(7); 
        strategy.setTDirection("D");
        
        //Checks to make sure the testDown method work properly
        assertThat(strategy.testDown(opponentsBoard)).isEqualTo(false);
        assertThat(strategy.getTDirection()).isEqualTo("U");
    }
    
    /**
     * Tests testUp method
     */
    public void testTestUp() {
        //Set a starting coordinates for testing and
        //Direction
        strategy.setXHit(0);
        strategy.setYHit(8); 
        strategy.setTDirection("U");
        
        //Calling the testRight method
        strategy.testUp(opponentsBoard);
        
        //Checks to make sure the testUp method work properly
        assertThat(strategy.getXHit()).isEqualTo(0);
        assertThat(strategy.getYMoving()).isEqualTo(7);
        
        //Set a starting coordinates for testing and
        //Direction
        strategy.setXHit(0);
        strategy.setYHit(0); 
        strategy.setTDirection("U");
                
        //Checks to make sure the testUp method work properly
        assertThat(strategy.testUp(opponentsBoard)).isEqualTo(false);
        assertThat(strategy.getTDirection()).isEqualTo("");
        
        //Set a starting coordinates for testing and
        //Direction
        strategy.setXHit(0);
        strategy.setYHit(0); 
        strategy.setTDirection("");
                
        //Checks to make sure the testUp method work properly
        assertThat(strategy.testUp(opponentsBoard)).isEqualTo(false);
        assertThat(strategy.getTDirection()).isEqualTo("");
        
        //Set a starting coordinates for testing and
        //Direction and testing canFire
        opponentsBoard.fireAt(0, 0);
        
        strategy.setXHit(0);
        strategy.setYHit(1); 
        strategy.setTDirection("U");
        
        //Checks to make sure the testUp method work properly
        assertThat(strategy.testUp(opponentsBoard)).isEqualTo(false);
        assertThat(strategy.getTDirection()).isEqualTo("");
    }
    
    /**
     * Test the vertical direction to make sure it works as intended
     */
    public void testVerticalDirection() {
        //Sets the starting shot and direction
        strategy.setXHit(7);
        strategy.setYHit(6);
        strategy.setYMoving(7); 
        strategy.setTDirection("D");

        //Calls the verticalDirection method
        CallShotMove move1 = strategy.verticalDirection(opponentsBoard);

        //Checks to make sure the verticalDirection method work properly
        assertThat(7).isEqualTo(move1.getX());
        assertThat(8).isEqualTo(move1.getY());
        
        //Sets the starting shot and direction
        strategy.setXHit(7);
        strategy.setYHit(6);
        strategy.setYMoving(7); 
        strategy.setTDirection("U");

        //Calls the verticalDirection method
        CallShotMove move2 = strategy.verticalDirection(opponentsBoard);

        //Checks to make sure the verticalDirection method work properly
        assertThat(7).isEqualTo(move2.getX());
        assertThat(7).isEqualTo(move2.getY());
        
        //----Test UP Range--------
        //Sets the starting shot and direction
        strategy.setYHit(2);
        strategy.setXHit(1);
        strategy.setYMoving(0); 
        strategy.setTDirection("U");

        //Calls the verticalDirection method
        CallShotMove move3 = strategy.verticalDirection(opponentsBoard);

        //Checks to make sure the horizontalDirection method work properly
        assertThat(1).isEqualTo(move3.getX());
        assertThat(3).isEqualTo(move3.getY());
        assertThat(strategy.getTDirection()).isEqualTo("D");
        
        
        //----Test Down Range--------
        //Sets the starting shot and direction
        strategy.setYHit(8);
        strategy.setXHit(1);
        strategy.setYMoving(9); 
        strategy.setTDirection("D");

        //Calls the verticalDirection method
        CallShotMove move4 = strategy.verticalDirection(opponentsBoard);

        //Checks to make sure the horizontalDirection method work properly
        assertThat(7).isEqualTo(move4.getY());
        assertThat(1).isEqualTo(move4.getX());
        assertThat(strategy.getTDirection()).isEqualTo("U");
        
        
        //----Test canFire --------
        opponentsBoard.fireAt(1, 8);
        //Sets the starting shot and direction
        strategy.setYHit(7);
        strategy.setXHit(1);
        strategy.setYMoving(7); 
        strategy.setTDirection("D");

        //Calls the horizontalDirection method
        CallShotMove move5 = strategy.verticalDirection(opponentsBoard);

        //Checks to make sure the horizontalDirection method work properly
        assertThat(6).isEqualTo(move5.getY());
        assertThat(1).isEqualTo(move5.getX());
        assertThat(strategy.getTDirection()).isEqualTo("U");
    }

    /**
     * Test the horizontal direction to make sure it works as intended
     */
    public void testHorizontalDirection() {
        //Sets the starting shot and direction
        strategy.setXHit(2);
        strategy.setYHit(1);
        strategy.setXMoving(2); 
        strategy.setTDirection("R");

        //Calls the horizontalDirection method
        CallShotMove move1 = strategy.horizontalDirection(opponentsBoard);

        //Checks to make sure the horizontalDirection method work properly
        assertThat(3).isEqualTo(move1.getX());
        assertThat(1).isEqualTo(move1.getY());
        
        //Sets the starting shot and direction
        strategy.setXHit(2);
        strategy.setYHit(1);
        strategy.setXMoving(2); 
        strategy.setTDirection("L");

        //Calls the horizontalDirection method
        CallShotMove move2 = strategy.horizontalDirection(opponentsBoard);

        //Checks to make sure the horizontalDirection method work properly
        assertThat(1).isEqualTo(move2.getX());
        assertThat(1).isEqualTo(move2.getY());
        
        
        //----Test LEFT Range--------
        //Sets the starting shot and direction
        strategy.setXHit(2);
        strategy.setYHit(1);
        strategy.setXMoving(0); 
        strategy.setTDirection("L");

        //Calls the horizontalDirection method
        CallShotMove move3 = strategy.horizontalDirection(opponentsBoard);

        //Checks to make sure the horizontalDirection method work properly
        assertThat(3).isEqualTo(move3.getX());
        assertThat(1).isEqualTo(move3.getY());
        assertThat(strategy.getTDirection()).isEqualTo("R");
        
        
        //----Test RIGHT Range--------
        //Sets the starting shot and direction
        strategy.setXHit(8);
        strategy.setYHit(1);
        strategy.setXMoving(9); 
        strategy.setTDirection("R");

        //Calls the horizontalDirection method
        CallShotMove move4 = strategy.horizontalDirection(opponentsBoard);

        //Checks to make sure the horizontalDirection method work properly
        assertThat(7).isEqualTo(move4.getX());
        assertThat(1).isEqualTo(move4.getY());
        assertThat(strategy.getTDirection()).isEqualTo("L");
        
        
        //----Test canFire --------
        opponentsBoard.fireAt(8, 1);
        //Sets the starting shot and direction
        strategy.setXHit(7);
        strategy.setYHit(1);
        strategy.setXMoving(7); 
        strategy.setTDirection("R");

        //Calls the horizontalDirection method
        CallShotMove move5 = strategy.horizontalDirection(opponentsBoard);

        //Checks to make sure the horizontalDirection method work properly
        assertThat(6).isEqualTo(move5.getX());
        assertThat(1).isEqualTo(move5.getY());
        assertThat(strategy.getTDirection()).isEqualTo("L");
    }

    /**
     * Test the callNextShot method to make sure it works as intended
     */
    public void testCallNextShot() {
        //Runs the randomShot function
        strategy.resetGame(true);
        strategy.setHitShip(false);
        //Calls the method to get the next shot
        CallShotMove move0 = strategy.callNextShot(gameState);
        //Checks the move to ensure it's valid
        assertTrue(opponentsBoard.canFireAt(move0.getX(), move0.getY()));
        
        //Setting Up variable to test an if-then
        //in this function
        strategy.setHitShip(true);
        strategy.setDirection("");
        strategy.setXHit(1);
        strategy.setYHit(1);
        
        //Calling callNextShot
        CallShotMove move1 = strategy.callNextShot(gameState);
        
        //Checking to make sure the function worked
        assertTrue(move1.getX() >= 0 && move1.getX() < 10);
        assertTrue(move1.getY() >= 0 && move1.getY() < 10);
        assertThat(strategy.getTDirection()).isEqualTo("R");
        
        //Setting Up variable to test an if-then
        //in this function
        strategy.setHitShip(true);
        strategy.setDirection("h");
        strategy.setTDirection("R");
        strategy.setXHit(1);
        strategy.setYHit(1);
        strategy.setXMoving(1);
        
        //Calling the method
        CallShotMove move2 = strategy.callNextShot(gameState);
        
        //Checking if it worked - it is going the opposite direciton
        //because miss is equal to true
        assertTrue(move2.getX() == 0 && move2.getY() == 1);
        assertThat(strategy.getTDirection()).isEqualTo("L");
        
        //Setting Up variable to test an if-then
        //in this function
        strategy.setXHit(1);
        strategy.setYHit(1);
        strategy.setXMoving(1);
        strategy.setDirection("h");
        strategy.setTDirection("L");
        
        //Calling the method
        CallShotMove move3 = strategy.callNextShot(gameState);
        
        //Checking if it worked - it is going the opposite direciton
        //because miss is equal to true
        assertTrue(move3.getX() == 2 && move3.getY() == 1);
        assertThat(strategy.getTDirection()).isEqualTo("R");
        
        //Setting Up variable to test an if-then
        //in this function
        strategy.setDirection("v");
        strategy.setTDirection("U");
        strategy.setXHit(1);
        strategy.setYHit(1);
        strategy.setYMoving(1);
        
         //Calling the method
        CallShotMove move4 = strategy.callNextShot(gameState);
        
        //Checking if it worked - it is going the opposite direciton
        //because miss is equal to true
        assertTrue(move4.getX() == 1 && move4.getY() == 2);
        assertThat(strategy.getTDirection()).isEqualTo("D");
        
        //Setting Up variable to test an if-then
        //in this function
        strategy.setDirection("v");
        strategy.setTDirection("D");
        strategy.setXHit(1);
        strategy.setYHit(1);
        strategy.setYMoving(1);
        
         //Calling the method
        CallShotMove move5 = strategy.callNextShot(gameState);
        
        //Checking if it worked - it is going the opposite direciton
        //because miss is equal to true
        assertTrue(move5.getX() == 1 && move5.getY() == 0);
        assertThat(strategy.getTDirection()).isEqualTo("U");
    }

    /**
     * Test to make sure my strategy won't shoot outside the board
     */
    public void testBoundries() {
        //Sets the hit-marker for the directionTest to test for
        strategy.setXHit(8);
        strategy.setYHit(0);
        strategy.setTDirection(""); 
        strategy.setHitShip(true);

        //Calls directionTest method
        CallShotMove move0 = strategy.directionTest(opponentsBoard);

        //Make sures that the method works properly
        assertTrue(move0.getX() >= 0 && move0.getX() < 10);
        
        //Sets the hit-marker for the directionTest to test for
        strategy.setXHit(8);
        strategy.setYHit(0);
        strategy.setTDirection("R"); 
        strategy.setHitShip(true);

        //Calls directionTest method
        CallShotMove move1 = strategy.directionTest(opponentsBoard);

        //Make sures that the method works properly
        assertTrue(move1.getX() >= 0 && move1.getX() < 10);
        
        //Sets the hit-marker for the directionTest to test for
        strategy.setXHit(1);
        strategy.setYHit(0);
        strategy.setTDirection("L"); 
        strategy.setHitShip(true);

        //Calls directionTest method
        CallShotMove move2 = strategy.directionTest(opponentsBoard);

        //Make sures that the method works properly
        assertTrue(move2.getX() >= 0 && move2.getX() < 10);
        
        //Sets the hit-marker for the directionTest to test for
        strategy.setXHit(0);
        strategy.setYHit(8);
        strategy.setTDirection("D"); 
        strategy.setHitShip(true);

        //Calls directionTest method
        CallShotMove move3 = strategy.directionTest(opponentsBoard);

        //Make sures that the method works properly
        assertTrue(move3.getY() >= 0 && move3.getY() < 10);
        
        //Sets the hit-marker for the directionTest to test for
        strategy.setXHit(1);
        strategy.setYHit(0);
        strategy.setTDirection("U"); 
        strategy.setHitShip(true);

        //Calls directionTest method
        CallShotMove move4 = strategy.directionTest(opponentsBoard);

        //Make sures that the method works properly
        assertTrue(move4.getY() >= 0 && move4.getY() < 10);
    }
    
    /**
     * Test to make sure my strategy changes the direction
     */
    public void testChangeDirection() {
        //Sets the hit-marker for the directionTest to test for
        strategy.setXHit(8);
        strategy.setYHit(0);
        strategy.setTDirection(""); 
        strategy.setHitShip(true);

        //Calls directionTest method
        strategy.directionTest(opponentsBoard);

        //Make sures that the method works properly
        assertThat(strategy.getTDirection()).isEqualTo("R");
        
        //Sets the hit-marker for the directionTest to test for
        strategy.setXHit(9);
        strategy.setYHit(0);
        strategy.setTDirection("R"); 
        strategy.setHitShip(true);

        //Calls directionTest method
        strategy.directionTest(opponentsBoard);

        //Make sures that the method works properly
        assertThat(strategy.getTDirection()).isEqualTo("L");
        
        //Sets the hit-marker for the directionTest to test for
        strategy.setXHit(0);
        strategy.setYHit(0);
        strategy.setTDirection("L"); 
        strategy.setHitShip(true);

        //Calls directionTest method
        strategy.directionTest(opponentsBoard);

        //Make sures that the method works properly
        assertThat(strategy.getTDirection()).isEqualTo("D");
        
        //Sets the hit-marker for the directionTest to test for
        strategy.setXHit(0);
        strategy.setYHit(9);
        strategy.setTDirection("D"); 
        strategy.setHitShip(true);

        //Calls directionTest method
        strategy.directionTest(opponentsBoard);

        //Make sures that the method works properly
        assertThat(strategy.getTDirection()).isEqualTo("U");
        
        //Sets the hit-marker for the directionTest to test for
        strategy.setXHit(0);
        strategy.setYHit(0);
        strategy.setTDirection("U"); 
        strategy.setHitShip(true);

        //Calls directionTest method
        CallShotMove move4 = strategy.directionTest(opponentsBoard);

        //Make sures that the method works properly- Calls RandomShot function
        assertTrue(move4.getY() >= 0 && move4.getY() < 10);
        assertThat(strategy.getTDirection()).isEqualTo("");
    }
    
    /**
     * Tests the updateDirection method
     */
    public void testUpdateDirection() {
        //Sets up the test for direction by setting it to U
        //Expecting that the updateDirection will set direction
        //to v
        strategy.setTDirection("U");

        //Calls updateDirection method
        strategy.updateDirection(true);

        //Make sures that the method works properly
        assertThat(strategy.getDirection()).isEqualTo("v");

        //Sets up the test for direction by setting it to D
        //Expecting that the updateDirection will set direction
        //to v
        strategy.setDirection("");
        strategy.setTDirection("D");

        //Calls updateDirection method
        strategy.updateDirection(true);

        //Make sures that the method works properly
        assertThat(strategy.getDirection()).isEqualTo("v");
        
        //Sets up the test for direction by setting it to L
        //Expecting that the updateDirection will set direction
        //to v
        strategy.setDirection("");
        strategy.setTDirection("L");

        //Calls updateDirection method
        strategy.updateDirection(true);

        //Make sures that the method works properly
        assertThat(strategy.getDirection()).isEqualTo("h");
        
        //Sets up the test for direction by setting it to R
        //Expecting that the updateDirection will set direction
        //to v
        strategy.setDirection("");
        strategy.setTDirection("R");

        //Calls updateDirection method
        strategy.updateDirection(true);

        //Make sures that the method works properly
        assertThat(strategy.getDirection()).isEqualTo("h");
        
        //Sets up the test for direction by setting it to R
        //Expecting that the updateDirection will set direction
        //to v
        strategy.setDirection("");
        strategy.setTDirection("R");

        //Calls updateDirection method
        strategy.updateDirection(false);

        //Make sures that the method works properly
        assertThat(strategy.getDirection()).isEqualTo("");
    }
    
    /**
     * Tests the setHitShip method
     */
    public void testGetHitShip() {
        //Setting the hitShip value to true
        strategy.setHitShip(true);
        
        //Make sures that the method works properly
        assertThat(strategy.getHitShip()).isEqualTo(true);
        
        //Setting the hitShip value to false
        strategy.setHitShip(false);
        
        //Make sures that the method works properly
        assertThat(strategy.getHitShip()).isEqualTo(false);
    }
    
    /**
     * Tests the getTDirection method
     */
    public void testGetTDirection() {
        //Setting the testDirection value to "R"
        strategy.setTDirection("R");
        //Make sures that the method works properly
        assertThat(strategy.getTDirection()).isEqualTo("R");
    
        //Setting the testDirection value to "L"
        strategy.setTDirection("L");
        //Make sures that the method works properly
        assertThat(strategy.getTDirection()).isEqualTo("L");
    
        //Setting the testDirection value to "U"
        strategy.setTDirection("U");
        //Make sures that the method works properly
        assertThat(strategy.getTDirection()).isEqualTo("U");
    
        //Setting the testDirection value to "D"
        strategy.setTDirection("D");
        //Make sures that the method works properly
        assertThat(strategy.getTDirection()).isEqualTo("D");
    }

    /**
     * Tests changeHorizontal method
     */
    public void testChangeHorizontal() {
        //Setting the testDirection value to "R"
        strategy.setTDirection("R");
        //Calling the changeHorizontal Method
        strategy.changeHorizontal(true);
        //Make sures that the method works properly
        assertThat(strategy.getTDirection()).isEqualTo("L");
        
        //Setting the testDirection value to "L"
        strategy.setTDirection("L");
        //Calling the changeHorizontal Method
        strategy.changeHorizontal(true);
        //Make sures that the method works properly
        assertThat(strategy.getTDirection()).isEqualTo("R");
        
        //Setting the testDirection value to "R"
        strategy.setTDirection("R");
        //Calling the changeHorizontal Method
        strategy.changeHorizontal(false);
        //Make sures that the method works properly
        assertThat(strategy.getTDirection()).isEqualTo("R");
    }
    
    
    /**
     * Tests changeHorizontal method
     */
    public void testChangeVertical() {
        //Setting the testDirection value to "U"
        strategy.setTDirection("U");
        //Calling the changeVertical Method
        strategy.changeVertical(true);
        //Make sures that the method works properly
        assertThat(strategy.getTDirection()).isEqualTo("D");
        
        //Setting the testDirection value to "D"
        strategy.setTDirection("D");
        //Calling the changeVertical Method
        strategy.changeVertical(true);
        //Make sures that the method works properly
        assertThat(strategy.getTDirection()).isEqualTo("U");
        
        //Setting the testDirection value to "U"
        strategy.setTDirection("U");
        //Calling the changeVertical Method
        strategy.changeVertical(false);
        //Make sures that the method works properly
        assertThat(strategy.getTDirection()).isEqualTo("U");
    
    }
    
    /**
     * Tests canPlayDeviously method
     */
    public void testCanPlayDeviously() {
        //Make sures that the method works properly
        assertFalse(strategy.canPlayDeviously());
    
    }
    
    /**
     * Tests newHitUpdate method
     */
    public void testNewHitUpdate() {
        //Initializing testing variables
        strategy.setXHit(2);
        strategy.setYHit(3);
        strategy.setXMoving(0);
        strategy.setYMoving(0);
        
        //Running Method
        strategy.newHitUpdate(true, 3, 4);
        
        //Checking to see if method worked
        assertThat(strategy.getXHit()).isEqualTo(3);
        assertThat(strategy.getYHit()).isEqualTo(4);
        assertThat(strategy.getXMoving()).isEqualTo(3);
        assertThat(strategy.getYMoving()).isEqualTo(4);
        
        
        //Initializing testing variables
        strategy.setXHit(2);
        strategy.setYHit(3);
        strategy.setXMoving(0);
        strategy.setYMoving(0);
        
        //Running Method
        strategy.newHitUpdate(false, 3, 4);
        
        //Checking to see if method worked
        assertThat(strategy.getXHit()).isEqualTo(2);
        assertThat(strategy.getYHit()).isEqualTo(3);
        assertThat(strategy.getXMoving()).isEqualTo(0);
        assertThat(strategy.getYMoving()).isEqualTo(0);
        
        
    }
    
    /**
     * Tests getRandomShot method
     */
    public void testGetRandomShot() {
        //Calling Method
        CallShotMove move = strategy.getRandomShot(opponentsBoard);
        
        //Checking that move was inside boundries
        assertTrue(move.getX() >= 0 && move.getX() < 10);
        assertTrue(move.getY() >= 0 && move.getY() < 10);
    }
    
    /**
     * Tests horizontal method when miss is false
     */
    public void testHorizontalFalse() {
        //Initalizing Variables for a sucessful hit
        strategy.setTDirection("L");
        strategy.setXMoving(1);
        strategy.setYHit(0);
        strategy.setXHit(8);
        
        //Calling method
        CallShotMove move1 = strategy.horizontal(opponentsBoard, false);
        
        //Checking if it worked properly
        assertThat(move1.getX()).isEqualTo(0);
        assertThat(move1.getY()).isEqualTo(0);
        assertThat(strategy.getXMoving()).isEqualTo(0);
    
        //Not inRange
        CallShotMove move2 = strategy.horizontal(opponentsBoard, false);
        
        //Checking if it worked properly
        assertThat(move2.getX()).isEqualTo(9);
        assertThat(move2.getY()).isEqualTo(0);
        assertThat(strategy.getXMoving()).isEqualTo(9);
        assertThat(strategy.getTDirection()).isEqualTo("R");
        
        
        //Not inRange
        CallShotMove move3 = strategy.horizontal(opponentsBoard, false);
        
        //Checking if it worked properly
        assertThat(move3.getX()).isEqualTo(7);
        assertThat(move3.getY()).isEqualTo(0);
        assertThat(strategy.getXMoving()).isEqualTo(7);
        assertThat(strategy.getTDirection()).isEqualTo("L");
    }
    
    /**
     * Tests vertical method when miss is false
     */
    public void testVerticalFalse() {
        //Initalizing Variables for a sucessful hit
        strategy.setTDirection("U");
        strategy.setYMoving(1);
        strategy.setYHit(8);
        strategy.setXHit(0);
        
        //Calling method
        CallShotMove move1 = strategy.vertical(opponentsBoard, false);
        
        //Checking if it worked properly
        assertThat(move1.getX()).isEqualTo(0);
        assertThat(move1.getY()).isEqualTo(0);
        assertThat(strategy.getYMoving()).isEqualTo(0);
    
        //Not inRange
        CallShotMove move2 = strategy.vertical(opponentsBoard, false);
        
        //Checking if it worked properly
        assertThat(move2.getY()).isEqualTo(9);
        assertThat(move2.getX()).isEqualTo(0);
        assertThat(strategy.getYMoving()).isEqualTo(9);
        assertThat(strategy.getTDirection()).isEqualTo("D");
        
        
        //Not inRange
        CallShotMove move3 = strategy.vertical(opponentsBoard, false);
        
        //Checking if it worked properly
        assertThat(move3.getY()).isEqualTo(7);
        assertThat(move3.getX()).isEqualTo(0);
        assertThat(strategy.getYMoving()).isEqualTo(7);
        assertThat(strategy.getTDirection()).isEqualTo("U");
    }
    
    
    /**
     * Testing vertical method when miss is true
     */
    public void testVerticalTrue() {
        //Initalizing Variables for a unsucessful hit
        strategy.setTDirection("U");
        strategy.setYMoving(1);
        strategy.setYHit(8);
        strategy.setXHit(0);
        
        //Calling method
        CallShotMove move1 = strategy.vertical(opponentsBoard, true);
        assertThat(move1.getY()).isEqualTo(9);
        assertThat(move1.getX()).isEqualTo(0);
        assertThat(strategy.getYMoving()).isEqualTo(9);
        assertThat(strategy.getTDirection()).isEqualTo("D");
        
        //Initalizing Variables for a unsucessful hit
        strategy.setTDirection("D");
        strategy.setYMoving(1);
        strategy.setYHit(8);
        strategy.setXHit(0);
        
        //Calling method
        CallShotMove move2 = strategy.vertical(opponentsBoard, true);
        assertThat(move2.getY()).isEqualTo(7);
        assertThat(move2.getX()).isEqualTo(0);
        assertThat(strategy.getYMoving()).isEqualTo(7);
        assertThat(strategy.getTDirection()).isEqualTo("U");
    }
    
    /**
     * Testing vertical method when miss is true
     */
    public void testHorizontalTrue() {
        //Initalizing Variables for a unsucessful hit
        strategy.setTDirection("L");
        strategy.setXMoving(1);
        strategy.setXHit(8);
        strategy.setYHit(0);
        
        //Calling method
        CallShotMove move1 = strategy.horizontal(opponentsBoard, true);
        assertThat(move1.getX()).isEqualTo(9);
        assertThat(move1.getY()).isEqualTo(0);
        assertThat(strategy.getXMoving()).isEqualTo(9);
        assertThat(strategy.getTDirection()).isEqualTo("R");
        
        //Initalizing Variables for a unsucessful hit
        strategy.setTDirection("R");
        strategy.setXMoving(1);
        strategy.setXHit(8);
        strategy.setYHit(0);
        
        //Calling method
        CallShotMove move2 = strategy.horizontal(opponentsBoard, true);
        assertThat(move2.getX()).isEqualTo(7);
        assertThat(move2.getY()).isEqualTo(0);
        assertThat(strategy.getXMoving()).isEqualTo(7);
        assertThat(strategy.getTDirection()).isEqualTo("L");
    }
    
    /**
     * This test method test the return of newHit
     * if hitShip is false and last hit was true
     */
    public void testNewHitFT() {
        strategy.setHitShip(false);
        opponentsBoard.fireAt(0, 8);
        strategy.callNextShot(gameState);
        assertThat(strategy.getNewHit()).isEqualTo(true);     
    }
    
    /**
     * This test method test the return of newHit
     * if hitShip is false and last hit was false
     */
    public void testNewHitFF() {
        strategy.setHitShip(false);
        opponentsBoard.fireAt(1, 8);
        strategy.callNextShot(gameState);
        assertThat(strategy.getNewHit()).isEqualTo(false);     
    }
    
    /**
     * This test method test the return of newHit
     * if hitShip is true and last hit was true
     */
    public void testNewHitTT() {
        strategy.setHitShip(true);
        opponentsBoard.fireAt(0, 8);
        strategy.callNextShot(gameState);
        assertThat(strategy.getNewHit()).isEqualTo(false);     
    }
    
    /**
     * This test method test the return of newHit
     * if hitShip is true and last hit was false
     */
    public void testNewHitTF() {
        strategy.setHitShip(true);
        opponentsBoard.fireAt(1, 8);
        strategy.callNextShot(gameState);
        assertThat(strategy.getNewHit()).isEqualTo(false);     
    }
    
    /**
     * This test method tests the return of miss
     * if hitShip is true and miss shot is true
     */
    public void testMissTT() {
        strategy.setHitShip(true);
        opponentsBoard.fireAt(1, 8);
        strategy.callNextShot(gameState);
        assertThat(strategy.getMiss()).isEqualTo(true);     
    }
    
    /**
     * This test method tests the return of miss
     * if hitShip is true and miss shot is false
     */
    public void testMissTF() {
        strategy.setHitShip(true);
        opponentsBoard.fireAt(0, 8);
        strategy.callNextShot(gameState);
        assertThat(strategy.getMiss()).isEqualTo(false);     
    }
    
    /**
     * This test method tests the return of miss
     * if hitShip is false and miss shot is true
     */
    public void testMissFT() {
        strategy.setHitShip(false);
        opponentsBoard.fireAt(1, 8);
        strategy.callNextShot(gameState);
        assertThat(strategy.getMiss()).isEqualTo(false);     
    }

    /**
     * This test method tests the return of miss
     * if hitShip is false and miss shot is false
     */
    public void testMissFF() {
        strategy.setHitShip(false);
        opponentsBoard.fireAt(0, 8);
        strategy.callNextShot(gameState);
        assertThat(strategy.getMiss()).isEqualTo(false);     
    }
        
    /**
     * This tests miss as false with the testDirection
     * equaling a direction
     */
    public void testUpdateDirFT() {
        //Initializing conditions
        strategy.setHitShip(false);
        opponentsBoard.fireAt(0, 8);
        
        //Test if Miss is false and direction is U
        strategy.setTDirection("U");
        strategy.callNextShot(gameState);
        assertThat(strategy.getDirection()).isEqualTo("v");
        
        //Test if Miss is false and direction is D
        strategy.setTDirection("D");
        strategy.callNextShot(gameState);
        assertThat(strategy.getDirection()).isEqualTo("v");

        //Test if Miss is false and direction is L
        strategy.setTDirection("L");
        strategy.callNextShot(gameState);
        assertThat(strategy.getDirection()).isEqualTo("h");
        
        //Test if Miss is false and direction is R
        strategy.setTDirection("R");
        strategy.callNextShot(gameState);
        assertThat(strategy.getDirection()).isEqualTo("h");
    }
    
    /**
     * This tests miss as true with the testDirection
     * equaling a direction
     */
    public void testUpdateDirTT() {
        strategy.setHitShip(true);
        opponentsBoard.fireAt(1, 8);
        strategy.callNextShot(gameState);
        
        //Test if Miss is true and direction is U
        strategy.setTDirection("U");
        assertThat(strategy.getDirection()).isEqualTo("");
        
        //Test if Miss is true and direction is D
        strategy.setTDirection("D");
        assertThat(strategy.getDirection()).isEqualTo("");

        //Test if Miss is true and direction is L
        strategy.setTDirection("L");
        assertThat(strategy.getDirection()).isEqualTo("");
        
        //Test if Miss is true and direction is R
        strategy.setTDirection("R");
        assertThat(strategy.getDirection()).isEqualTo("");
        
    }
    
    /**
     * This tests miss as false with the testDirection
     * equaling null
     */
    public void testUpdateDirTF() {
        //Initializing conditions
        strategy.setHitShip(false);
        opponentsBoard.fireAt(0, 8);
        strategy.setTDirection("");
        
        //Test if Miss is true and direction is U
        strategy.callNextShot(gameState);
        assertThat(strategy.getDirection()).isEqualTo("");
    }
    
    /**
     * This tests miss as false with the testDirection
     * equaling null
     */
    public void testUpdateDirFF() {
        //Initializing conditions
        strategy.setHitShip(false);
        opponentsBoard.fireAt(1, 8);
        strategy.setTDirection("");
        
        //Test if Miss is true and direction is null
        strategy.callNextShot(gameState);
        assertThat(strategy.getDirection()).isEqualTo("");
        
    }
    
    /**
     * This tests the getRandomShot() to make sure
     * if all the spaces are shot at, it wont shoot there
     */
    public void testCanFire() {
        //Shooting at every row except the first on
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (x != 0) {
                    opponentsBoard.fireAt(x, y);
                }
            }
        }
        
        CallShotMove move = strategy.getRandomShot(opponentsBoard);
        
        //Checking to make sure x is equal to 0
        assertThat(move.getX()).isEqualTo(0);
        //Making sure Y is in range
        assertThat(move.getY() >= 0 && move.getY() < 10).isEqualTo(true);
    }
}












import student.micro.battleship.*;
import java.util.Random;

//-------------------------------------------------------------------------
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Keller Bice (kece05)

/**
 * This script is used to implement my stragety that I use 
 * when I play battleship. The strategy is to randomly fire
 * at random positions until I hit a ship. Once I hit
 * a ship, I find the direction the ship is going.
 * Once I find the direction, follow the direction 
 * until I sink the ship.
 *
 *  @author Keller Bice (kece05)
 *  @version (2024.10.07)
 */
public class GradedStrategy implements BattleshipStrategy
{
    //Initializing Fields
    
    //State of what the strategy should do
    private boolean hitShip = false;
    
    //Original x-coord of the hit marker
    private int xHit = -1;
    
    //Original y-coord of the hit marker
    private int yHit = -1; 
    
    //Follows x-axis of ship
    private int xMoving = 0; 
    
    //Follows y-axis of ship
    private int yMoving = 0; 
    
    //Direction of the hit ship
    private String direction = "";
    
    ///Used to find the direction of hit ship
    private String testDirection = ""; 
    
    //Used to track rounds and is used from deciding the layout
    private int getRound = 0;
    
    //Creates the random fuction into a variable
    private Random random = new Random();
    
    //The layout of the ship
    private String layout = "";
    
    //Tells if there has been a new hit
    private boolean newHit;
        
    //Tells if the last hit missed or not
    private boolean miss;
    
    
    // Setter and Getter methods for each variable
    /**
     * @return a boolean of the value of newHit
     */
    public boolean getMiss() {
        return miss;
    }
    
    /**
     * @return a boolean of the value of newHit
     */
    public boolean getNewHit() {
        return newHit;
    }
    
    /**
     * @return a boolean of the value of hitShip
     */
    public String getLayout() {
        return layout;
    }
    
    /**
     * Sets the value for hitShip
     * @param hit is the value to set hitShip
     */
    public void setHitShip(boolean hit) {
        hitShip = hit;
    }
    
    /**
     * @return a boolean of the value of hitShip
     */
    public boolean getHitShip() {
        return hitShip;
    }
    
    /**
     * Sets the value for xHit
     * @param xCoordHit is the value to set xHit
     */
    public void setXHit(int xCoordHit) {
        xHit = xCoordHit;
    }
    
    /**
     * @return the value of xHit
     */
    public int getXHit() {
        return xHit;
    }
    
    /**
     * Sets the value for xHit
     * @param yCoordHit is the value to set xHit
     */
    public void setYHit(int yCoordHit) {
        yHit = yCoordHit;
    }
    
    /**
     * @return the value of yHit
     */
    public int getYHit() {
        return yHit;
    }
    
    /**
     * Sets the value for xMoving
     * @param xCoord is the value to set xMoving
     */
    public void setXMoving(int xCoord) {
        xMoving = xCoord;
    }
    
    /**
     * @return the value of xMoving
     */
    public int getXMoving() {
        return xMoving;
    }
    
    /**
     * Sets the value for yMoving
     * @param yCoord is the value to set yMoving
     */
    public void setYMoving(int yCoord) {
        yMoving = yCoord;
    }
    
    /**
     * @return the value of yMoving
     */
    public int getYMoving() {
        return yMoving;
    }

    /**
     * Sets the value for direction
     * @param inputDirection is the value to set direction
     */
    public void setDirection(String inputDirection) {
        direction = inputDirection;
    }
    
    /**
     * @return the value of direction
     */
    public String getDirection() {
        return direction;
    }
    
    /**
     * Sets the value for testDirection
     * @param inputTDirection is the value to set testDirection
     */
    public void setTDirection(String inputTDirection) {
        testDirection = inputTDirection;
    }
    
    /**
     * @return the value of testDirection
     */
    public String getTDirection() {
        return testDirection;
    }
    
    /**
     * Sets the value for getRound
     * @param round is the value to set getRound
     */
    public void setRound(int round) {
        getRound = round;
    }
    
    /**
     * @return the value of round
     */
    public int getterRound() {
        return getRound;
    }
    

    /**
     * Initializes a newly created GradedStrategy object.
    */
    public GradedStrategy() {
        super();    
    }

    /**
     * Creates players name
     * @return my strategy player name
    */
    public String getName() {
        return "AreaStrategy - kece05";
    }

    /**
     * Resets all the initialized variables
    */
    public void newGame() {
        hitShip = false; 
        xHit = -1;
        yHit = -1; 
        testDirection = "";
        direction = "";
        xMoving = 0;
        yMoving = 0;
        getRound++;
    }

    /**
     * Sets up the ship positions for your board
     * @param currentGameState is the current information of the game and board
     * @return the layout for my board
    */
    public ShipPlacementMove placeShips(GameState currentGameState) {
        //This sets the layout of the positioning
        String layout1 = String.join("\n",
                ". . . . . . B B B B",
                ". . D D D . . . . .",
                ". . . . . . . . . .",
                ". . . . . . . . P P",
                "C . . . . . . . . .",
                "C . . . . . . . . .",
                "C . . . . . . . . .",
                "C . . . . . . S . .",
                "C . . . . . . S . .",
                ". . . . . . . S . ."
            );

        String layout2 = String.join("\n",
                ". . . . . . . . . S",
                ". . . . . . . . . S",
                ". . . . . . . . . S",
                "D D D . . . . . . .",
                ". . . . . . C C C C C",
                ". . . . . . . . . . .",
                "P P . . . . . . . . .",
                ". . . . . . . . . . .",
                ". . . . . . B B B B .",
                ". . . . . . . . . . ."
            );

        //Uses logic to make a decision of which layout to use
        //Even: layout1 - Odd: layout2
        layout = (getRound % 2 == 0) ? layout1 : layout2;
        
        //Returns the specified layout to the board
        return new ShipPlacementMove(currentGameState, layout);
    }    

    /**
     * Test if the right direction continues the hit streak
     * @param board gets the current information of the game and board
     * @return a true or false statement wether the strategy can fire
    */
    public boolean testRight(Board board) {
        //Sees if the current direction to test is right
        if (testDirection.equals("R")) {
            //Makes sure the next move is inbounds and hasn't been hit yet
            if (xHit + 1 < 10 && board.canFireAt(xHit + 1, yHit)) {
                //Update the xMoving to fire to the right
                xMoving = xHit + 1;
                //Returns that it is safe to test the direction
                return true;
            }
            //If it not safe to fire right, then test the left direction
            testDirection = "L"; 
        }
        //If it isn't currently test the right direction, it returns false
        return false;
    }

    /**
     * Test if the left direction continues the hit streak
     * @param board gets the current information of the game and board
     * @return a true or false statement wether the strategy can fire
    */
    public boolean testLeft(Board board) {
        //Sees if the current direction to test is left
        if (testDirection.equals("L")) {
            //Makes sure the next move is inbounds and hasn't been hit yet
            if (xHit - 1 >= 0 && board.canFireAt(xHit - 1, yHit)) {
                //Update the xMoving to fire to the left
                xMoving = xHit - 1;
                //Returns that it is safe to test the direction
                return true;
            }
            //If it not safe to fire left, then test the upwards direction
            testDirection = "D"; 
        }
        //If it isn't currently test the left direction, it returns false
        return false;
    }

    /**
     * Test if the downwards direction continues the hit streak
     * @param board gets the current information of the game and board
     * @return a true or false statement wether the strategy can fire
    */
    public boolean testDown(Board board) {
        //Sees if the current direction to test is upwards
        if (testDirection.equals("D")) {
            //Makes sure the next move is inbounds and hasn't been hit yet
            if (yHit + 1 < 10 && board.canFireAt(xHit, yHit + 1)) {
                //Update the yMoving to fire downwards
                yMoving = yHit + 1;
                //Returns that it is safe to test the direction
                return true;
            }
            //If it not safe to fire downwards, then test the upwards direction
            testDirection = "U";  
        }
        //If it isn't currently test the downwards direction, it returns false
        return false;
    }

    /**
     * Test if the upwards direction continues the hit streak
     * @param board gets the current information of the game and board
     * @return a true or false statement wether the strategy can fire
    */
    public boolean testUp(Board board) {
        //Sees if the current direction to test is upwards
        if (testDirection.equals("U")) {
            //Makes sure the next move is inbounds and hasn't been hit yet
            if (yHit - 1 >= 0 && board.canFireAt(xHit, yHit - 1)) {
                //Update the yMoving to fire upwards
                yMoving = yHit - 1;
                //Returns that it is safe to test the direction
                return true;
            }
            //If it not safe to fire upwards, then reset testDirection
            testDirection = "";
        }
        //If it isn't currently test the downwards direction, it returns false
        return false;
    }

    /**
     * Method is used to find if the ships direction
     * @param board gets the current information of the game and board
     * @return the next firing position
    */
    public CallShotMove directionTest(Board board) {
        //I didnt do else-if statements so it can continually test
        //all statements to find the correct direction

        //Sets TestDirection to Right if it is the first time
        if (testDirection.equals("")) {
            testDirection = "R";
        }

        //Test the right direction
        if (testRight(board)) {
            //Returns a valid coordinate to fire to the right
            return new CallShotMove(xHit + 1, yHit);
        }

        //Test the left direction
        if (testLeft(board)) {
            //Returns a valid coordinate to fire to the left
            return new CallShotMove(xHit - 1, yHit);
        }

        //Test the downwards direction
        if (testDown(board)) {
            //Returns a valid coordinate to fire downwards
            return new CallShotMove(xHit, yHit + 1);
        }

        //Test the upwards direction
        if (testUp(board)) {
            //Returns a valid coordinate to fire upwards
            return new CallShotMove(xHit, yHit - 1);
        }

        //If there's no valid direction, then go back to randomly firing
        return getRandomShot(board);
    }

    /**
     * See if the direction its firing is still valid
     * If it misses then it means it needs to go the other way
     * @param misses tells the computer is the last fire hit a ship or not
    */
    public void changeHorizontal(boolean misses) {
        if (misses) {
            //Changing the direction to go the other way
            testDirection = testDirection.equals("R") ? "L" : "R"; 
            //Reseting point to where to fire
            xMoving = xHit;
        }
    }

    /**
     * See if the direction its firing is still valid
     * If it misses then it means it needs to go the other way
     * @param misses tells the computer is the last fire hit a ship or not
    */
    public void changeVertical(boolean misses) {
        if (misses) {
            //Changing the direction to go the other way
            testDirection = testDirection.equals("D") ? "U" : "D";
            //Reseting point to where to fire
            yMoving = yHit;
        }
    }

    /**
     * Method is used to continue in the horizontal direction
     * is the test direction did the logic
     * @param board gets the current information of the game and board
     * @return it returns next move
    */
    public CallShotMove horizontalDirection(Board board) {
        //Using a fancy way of doing if-else statement to decide
        //wether to move left or right
        int directionMove = testDirection.equals("R") ? 1 : -1;
        int xDirection = xMoving + directionMove;

        //Initializing the range bools to make sure the next move is in range
        boolean inRightRange = (testDirection.equals("R") && xDirection < 10);
        boolean inLeftRange = (testDirection.equals("L") && xDirection >= 0);
        boolean inRange = (inRightRange || inLeftRange);

        //Checking if the next fire is is range and hasn't been hit yet
        if (inRange && board.canFireAt(xMoving + directionMove, yHit)) {
            //Updating the xMoving to the most recent fire
            //So then it can continue to move in either left or right direction
            xMoving = xDirection;
            return new CallShotMove(xMoving, yHit);
        } 
        else {
            //This will change the direction immediately
            //Or else it will cause the strategy to break
            changeHorizontal(true);
            xDirection = testDirection.equals("R") ? xMoving + 1 : xMoving - 1;
            xMoving = xDirection;
            return new CallShotMove(xMoving, yHit);
        }
    }

    /**
     * Method is used to continue in the vertical direction
     * is the test direction did the logic
     * @param board gets the current information of the game and board
     * @return it returns next move
    */
    public CallShotMove verticalDirection(Board board) {
        //Using a fancy way of doing if-else statement to decide
        //wether to move down or up
        int directionMove = testDirection.equals("D") ? 1 : -1;
        int yDirection = yMoving + directionMove;

        //Initializing the range bools to make sure the next move is in range
        boolean inDownRange = (testDirection.equals("D") && yDirection < 10);
        boolean inUpRange = (testDirection.equals("U") && yDirection >= 0);
        boolean inRange = (inUpRange || inDownRange);


        //Checking if the next fire is is range and hasn't been hit yet
        if (inRange && board.canFireAt(xHit, yMoving + directionMove)) {
            //Updating the xMoving to the most recent fire
            //So then it can continue to move in either up or down
            yMoving = yDirection;
            return new CallShotMove(xHit, yMoving);
        } 
        else {
            //This will change the direction immediately
            //Or else it will cause the strategy to break
            changeVertical(true);
            yDirection = testDirection.equals("D") ? yMoving + 1 : yMoving - 1;
            yMoving = yDirection;
            return new CallShotMove(xHit, yMoving);
        }
    }

    /**
     * Method is used to use decide to continue in same direction
     * or change direction depending on if the last shot fired missed
     * and then calls 'horizontalDirection' method 
     * @param board gets the current information of the game and board
     * @param misses tells the computer is the last fire hit a ship or not
     * @return the next firing position
    */
    public CallShotMove horizontal(Board board, boolean misses) {
        changeHorizontal(misses);
        return horizontalDirection(board);
    }

    /**
     * Method is used to use decide to continue in same direction
     * or change direction depending on if the last shot fired missed
     * and then calls 'verticalDirection' method 
     * @param board gets the current information of the game and board
     * @param misses tells the computer is the last fire hit a ship or not
     * @return the next firing position
    */
    public CallShotMove vertical(Board board, boolean misses) {
        changeVertical(misses);
        return verticalDirection(board);
    }

    /**
     * This method is the center point in my strategy, 
     * where the next firing spot is decide
     * @param currentGameState gets the current information
     * of the game and board
     * @return callNextShot returns the next firing position
    */
    public CallShotMove callNextShot(GameState currentGameState) {
        //Initializing variables needed to make logic decisions
        Board board = currentGameState.getOpponentsBoard();
        int previousX = board.getLastAttackX();
        int previousY = board.getLastAttackY();
        
        //Variable states what happened the round before
        CellStatus currentState = board.getStatusAt(previousX, previousY);

        //Checks if a its a new ship being hit by checking previous status
        //and seeing if the state of what the strategy is in
        newHit = (currentState == CellStatus.HIT && !hitShip);

        //Checking to see if the last fire was a miss 
        //while being in a state of attacking
        miss = (currentState == CellStatus.MISS && hitShip);

        //Resets variables if the ship was sunk
        resetGame(board.lastAttackSunkAShip());

        //Updates xHit,yHit, xMoving, yMoving variables to the
        //orignial hit coordinates if a new hit was registered
        newHitUpdate(newHit, previousX, previousY);

        //Updates the 'direction' variable if the directionTest method
        //finds the correct direction
        updateDirection(!miss && !testDirection.equals(""));

        //If the program is in a state of attack but doesnt have the direction
        //this will run to find the direction
        if (hitShip && direction.equals("")) {
            return directionTest(board);
        }

        //If the program is in a state of attack and has the direction
        //this if-else function will run to sink the ship
        if (direction.equals("h")) {
            return horizontal(board, miss);
        } 
        else if (direction.equals("v")) {
            return vertical(board, miss);
        }

        return getRandomShot(board);
    }
    /**
     * Updates xHit,yHit, xMoving, yMoving variables to the
     * orignial hit coordinates if a new hit was registered
     * @param hit this parameter tells if the last fire hit a ship
     * @param x this parameters gets the x-coordinate of last fired
     * @param y this parameters gets the y-coordinate of last fired
    */
    public void newHitUpdate(boolean hit, int x, int y) {
        //Checks if a ship was hit
        if (hit) {
            //Updates the variables to the new hit ships coordinates
            xHit = x;
            xMoving = xHit; 

            yHit = y;
            yMoving = yHit;

            hitShip = true;
        }
    }

    /**
     * This method resets variables if the ship was sunk
     * @param sunkShip this parameters tells if the last fire sunk a ship or not
    */
    public void resetGame(boolean sunkShip) {
        //Checks if the ship was sunk
        if (sunkShip) {
            //Resets the variables because if it doesn't 
            //the strategy will be in a constant attack state
            //and break the strategy
            hitShip = false; 
            xHit = -1;
            yHit = -1; 
            testDirection = "";
            direction = "";
            xMoving = 0;
            yMoving = 0;
            random = new Random();
        }
    }

    /**
     * This method updates the 'direction' variable if the directionTest method
     * finds the correct direction
     * @param update tells if the direction of the hit ship was was found
    */
    public void updateDirection(boolean update) {
        if (update) {
            if (testDirection.equals("U") || testDirection.equals("D")) {
                direction = "v"; //Sets the direction to vertical 
            } 
            else {
                direction = "h"; //Sets the direction to horizontal 
            }
        }
    }

    /**
     * This method is used to find new ships by randomly choosing integars
     * for x and y between 0-9
     * @param board gets the current information of the game and board
     * @return the next random firing position
    */
    public CallShotMove getRandomShot(Board board) {
        //Turns off attack mode
        resetGame(true);
        //Creates a random number
        int x = 0;
        int y = 0;
        
        //Repeatly tries to find a random and valid unfired cell
        do {
            //Creates a random number with the random function variable
            x = random.nextInt(10);
            y = random.nextInt(10);
            //Checks the validity of the random position
        } while (!board.canFireAt(x, y));
        
        return new CallShotMove(x, y);
    }

    /**
     * This method is to decide if cheating is allowed
     * @return if cheating is allowed
    */
    public boolean canPlayDeviously() {
        return false;
    }
}
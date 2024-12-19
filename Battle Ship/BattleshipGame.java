import student.micro.battleship.*;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Keller Bice (kece05)
//-------------------------------------------------------------------------
/**
 *  A game of "Battleship".
 *
 *  @author Keller Bice (kece05)
 *  @version (2024.10.07)
 */
public class BattleshipGame extends Game {

    /**
     * Creates a new BattleshipGame object.
     */
    public BattleshipGame() {
        super();
    }

    /**
     * Create a strategy object representing your player strategy.
     * @return The strategy I want to use in this game.
     */
    public BattleshipStrategy createMyStrategy(){
        return new GradedStrategy();
    }

    /**
     * Create a strategy object representing your opponent's strategy.
     * @return The strategy my opponent will use in this game.
     */
    public BattleshipStrategy createOpponentsStrategy() {
        return new RandomStrategy();
    }

}
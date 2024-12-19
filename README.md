# Battleship Strategy Project

This repository contains a Java implementation of a custom Battleship strategy using the `GradedStrategy` class. The strategy has been rigorously tested and demonstrates a win rate exceeding 95% against various opponents.

## Files

- **`BattleshipGame.java`**: Entry point for the Battleship game. This file defines the game logic and integrates the strategies.
- **`GradedStrategy.java`**: Implements the custom strategy for targeting opponent ships.
- **`GradedStrategyTest.java`**: Comprehensive test suite to ensure the reliability and correctness of the `GradedStrategy` implementation.

## How the Strategy Works

The `GradedStrategy` uses a two-phase approach:

### Phase 1: Random Search
In the initial phase, the strategy randomly fires at positions on the board. This random targeting continues until a ship is hit.

### Phase 2: Directional Targeting
Once a ship is hit, the strategy determines its orientation (horizontal or vertical) and systematically targets adjacent cells in that direction until the ship is sunk.

- **Directional Testing**: When a ship is hit, the strategy tests directions (right, left, down, up) to identify the ship’s orientation.
- **Systematic Firing**: After determining the direction, the strategy follows the ship’s orientation to target and sink it efficiently.
- **Adaptability**: If the direction fails (e.g., due to a miss or boundary), the strategy adjusts to test other directions.

### Key Features
- **Dynamic Updates**: Resets variables when a ship is sunk to avoid targeting redundant cells.
- **Efficient Random Targeting**: Ensures no cell is fired upon twice during the random search phase.
- **Boundary Awareness**: Prevents firing outside the board boundaries.

## Win Rate
Through extensive testing, this strategy has consistently achieved a win rate exceeding 95%. The combination of randomness for discovery and precision for elimination makes it highly effective against various opponents.

## How to Run

### Prerequisites
Ensure you have Java installed on your system.

### Steps
1. Compile the Java files:
   ```bash
   javac -d bin src/*.java
   ```
2. Run the game:
   ```bash
   java -cp bin BattleshipGame
   ```

## Running Tests
The test suite is designed to validate all aspects of the strategy. To run the tests:

1. Compile the test files along with the main files:
   ```bash
   javac -d bin -cp path/to/testing/library src/*.java
   ```
2. Execute the tests using your preferred testing framework (e.g., JUnit).

## License
This project is licensed under the [MIT License](LICENSE). Feel free to use and modify the code as needed.

---

This project was created by **Keller Bice (kece05)** as part of a Virginia Tech class assignment. The strategy leverages advanced algorithms to dominate the game of Battleship, reflecting a deep understanding of game mechanics and Java programming.


package tetris.cupkit;

import java.util.Random;

import tetris.autoplay.AutoPlayer;
import tetris.autoplay.AutoPlayer.Move;
import tetris.game.MyTetrisFactory;
import tetris.game.TetrisGame;
import tetris.game.TetrisGameView;

public class AISampler {

	public long playout(long seed) throws IllegalArgumentException {
		TetrisGame game = MyTetrisFactory.createTetrisGame(new Random(seed));
		game.step();
		AutoPlayer player = MyTetrisFactory.createAutoPlayer(new TetrisGameView(game));
		long points = playout(game, player);
		System.out.println("Seed                " + seed);
		return points;
	}

	public long playout(TetrisGame game, AutoPlayer player) throws IllegalArgumentException {
		// limits
		final long stepLimit = 5000;
		// ms
		final long stepThinkTime = 100;
		// ms to ns
		final long totalThinkTime = stepLimit * stepThinkTime * 1000000;

		// tracking
		long elapsedThinkTime = 0;

		long startTime = System.nanoTime();
		long steps;
		for (steps = 0; steps < stepLimit && !game.isGameOver();) {
			// query next AI decision
			Move move;
			{
				long startThinkTime = System.nanoTime();
				move = player.getMove();
				long endThinkTime = System.nanoTime();
				elapsedThinkTime += endThinkTime - startThinkTime;
			}

			// enforce think time limit
			if (elapsedThinkTime >= totalThinkTime) {
				break;
			}

			// execute
			boolean valid = true;
			switch (move) {
			case DOWN:
				game.step();
				steps++;
				break;
			case LEFT:
				valid = game.moveLeft();
				break;
			case RIGHT:
				valid = game.moveRight();
				break;
			case ROTATE_CCW:
				valid = game.rotatePieceCounterClockwise();
				break;
			case ROTATE_CW:
				valid = game.rotatePieceClockwise();
				break;
			default:
				throw new IllegalArgumentException("Unknown move kind");
			}

			if (!valid)
				throw new IllegalArgumentException("AI attempted invalid move");
		}
		long endTime = System.nanoTime();

		long elapsedTime = endTime - startTime;
		double elapsedMillis = elapsedTime / 1000000.0;
		double elapsedThinkMillis = elapsedThinkTime / 1000000.0;

		System.out.println("---------------------------------------");
		System.out.println("Points              " + game.getPoints());
		System.out.println("Steps               " + steps);
		System.out.println("Elapsed time        " + elapsedMillis + " ms");
		System.out.println("Elapsed think time  " + elapsedThinkMillis + " ms");
		System.out.println("AvgTime per step    " + (elapsedMillis / steps) + " ms");
		return game.getPoints();
	}

	public static void main(String[] args) {
		Random seeder = new Random();
		for (long i = 0; i < 5; ++i) {
			new AISampler().playout(seeder.nextInt());
		}
	}
}

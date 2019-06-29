package tetris.autoplay;

import tetris.game.TetrisGameView;

public class AutoPlayerImplementation implements AutoPlayer
{
	private TetrisGameView game;
	
	public AutoPlayerImplementation(TetrisGameView pGame)
	{
		game = pGame;
		game.addObserver(this);
	}

	@Override
	public void rowsCompleted()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void piecePositionChanged()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void pieceLanded()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void gameOver()
	{
		game.removeObserver(this);
	}

	@Override
	public Move getMove()
	{
		return Move.DOWN;
	}
}
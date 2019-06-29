package tetris.game;

import java.util.ArrayList;
import java.util.Random;
import tetris.game.pieces.*;
//import tetris.game.pieces.Piece;
//import tetris.game.pieces.PieceFactory;
//import tetris.game.pieces.PieceFactoryImplementation;
//import tetris.game.pieces.PieceImplementation;
 

public class TetrisGameImplementation implements TetrisGame
{

//	private Random random;
//	private int rows;
//	private int columns;
	private Board board;
	private PieceFactory pieceFactory;
	private ArrayList<GameObserver> observerList;
	
	//#1 Victory Royale 
	
	private boolean gameOver;
	private int completedRows;
	private int points;
	private Piece currentPiece;
	private Piece nextPiece;
	private int cpRow;
	private int cpColumn;
	
	public TetrisGameImplementation(Board pboard, PieceFactory ppiece)
	{
//		random = pr;
//		rows = prows;
//		columns = pcolumns;
		board = pboard;
		pieceFactory = ppiece;
		observerList = new ArrayList<GameObserver>(10);
		
		gameOver = false;
		completedRows = 0;
		points = 0;
		currentPiece = null;
		nextPiece = pieceFactory.getNextRandomPiece();
		cpRow = 0;
		cpColumn = 0;
	}

	@Override
	public void addObserver(GameObserver observer)
	{
		observerList.add(observer);
	}

	@Override
	public void removeObserver(GameObserver observer)
	{
		observerList.remove(observer);
	}

	@Override
	public Piece getCurrentPiece()
	{
		return currentPiece;
	}

	@Override
	public Board getBoard()
	{	
		return board;
	}

	@Override
	public Piece getNextPiece()
	{
		return nextPiece;
	}

	@Override
	public int getNumberOfCompletedRows()
	{
		return completedRows;
	}

	@Override
	public int getPieceColumn()
	{
		return cpColumn;
	}

	@Override
	public int getPieceRow()
	{
		return cpRow;
	}

	@Override
	public long getPoints()
	{
		return points;
	}

	@Override
	public boolean isGameOver()
	{
		return gameOver;
	}

	@Override
	public boolean moveDown()
	{
		System.out.println("Arrived in moveDown");
		if(board.canRemovePiece(currentPiece, cpRow, cpColumn))
		{
			board.removePiece(currentPiece, cpRow, cpColumn);
			if(board.canAddPiece(currentPiece, cpRow+1, cpColumn))
			{
				board.addPiece(currentPiece, cpRow+1, cpColumn);
				cpRow++;
				observerList.forEach((o) -> o.piecePositionChanged());
				return true;
			}
			else
			{
				board.addPiece(currentPiece, cpRow, cpColumn);
				observerList.forEach((o) -> o.pieceLanded());
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean moveLeft()
	{
		if(board.canRemovePiece(currentPiece, cpRow, cpColumn))
		{
			board.removePiece(currentPiece, cpRow, cpColumn);
			if(board.canAddPiece(currentPiece, cpRow, cpColumn-1))
			{
				board.addPiece(currentPiece, cpRow, cpColumn-1);
				cpColumn--;
				observerList.forEach((o) -> o.piecePositionChanged());
				return true;
			}
			else
			{
				board.addPiece(currentPiece, cpRow, cpColumn);
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean moveRight()
	{
		if(board.canRemovePiece(currentPiece, cpRow, cpColumn))
		{
			board.removePiece(currentPiece, cpRow, cpColumn);
			if(board.canAddPiece(currentPiece, cpRow, cpColumn+1))
			{
				board.addPiece(currentPiece, cpRow, cpColumn+1);
				cpColumn++;
				observerList.forEach((o) -> o.piecePositionChanged());
				return true;
			}
			else
			{
				board.addPiece(currentPiece, cpRow, cpColumn);
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean newPiece()
	{
		if(!gameOver)
		{
			int removedRows = board.deleteCompleteRows();
			completedRows += removedRows;
			int pointsToAdd = 0;
			if(removedRows == 1)
			{
				pointsToAdd = 100;
				observerList.forEach((o) -> o.rowsCompleted());
			}
			else
			{
				if(removedRows == 2)
				{
					pointsToAdd = 300;
					observerList.forEach((o) -> o.rowsCompleted());
				}
				else
				{
					if(removedRows == 3)
					{
						pointsToAdd = 500;
						observerList.forEach((o) -> o.rowsCompleted());
					}
					else
					{
						if(removedRows > 3)
						{
							pointsToAdd = 1000;
							observerList.forEach((o) -> o.rowsCompleted());
						}
					}
				}
			}
			points += pointsToAdd;
		
			if(!board.canAddPiece(nextPiece, 2, board.getNumberOfColumns()/2))
			{
				setGameOver();
				return false;
			}
			board.addPiece(nextPiece, 2, board.getNumberOfColumns()/2);
			currentPiece = nextPiece;
			cpRow = 2;
			cpColumn = board.getNumberOfColumns()/2;
			nextPiece = pieceFactory.getNextRandomPiece();
			
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean rotatePieceClockwise()
	{
		if(board.canRemovePiece(currentPiece, cpRow, cpColumn))
		{
			board.removePiece(currentPiece, cpRow, cpColumn);
			Piece newPiece = currentPiece.getClockwiseRotation();
			if(board.canAddPiece(newPiece, cpRow, cpColumn))
			{
				board.addPiece(newPiece, cpRow, cpColumn);
				currentPiece = newPiece;
				return true;
			}
			else
			{
				board.addPiece(currentPiece, cpRow, cpColumn);
				return false;
			}
		}
		else 
		{
			return false;
		}
	}

	@Override
	public boolean rotatePieceCounterClockwise()
	{
		if(board.canRemovePiece(currentPiece, cpRow, cpColumn))
		{
			board.removePiece(currentPiece, cpRow, cpColumn);
			Piece newPiece = currentPiece.getCounterClockwiseRotation();
			if(board.canAddPiece(newPiece, cpRow, cpColumn))
			{
				board.addPiece(newPiece, cpRow, cpColumn);
				currentPiece = newPiece;
				return true;
			}
			else
			{
				board.addPiece(currentPiece, cpRow, cpColumn);
				return false;
			}
		}
		else 
		{
			return false;
		}
	}

	@Override
	public void setGameOver()
	{
		observerList.forEach((o) -> o.gameOver());
		gameOver = true;
	}

	@Override
	public void step()
	{
		if(!gameOver)
		{
			if(currentPiece == null)
			{
				System.out.println("yes");
				newPiece();
			}
			else
			{
				System.out.println("hello");
				if(!moveDown())
				{
					System.out.println("darkness");
					if(!newPiece())
					{
						System.out.println("my old friend");
						setGameOver();
						((BoardImplementation)board).printStats();
					}
				}
			}
		}
	}
}
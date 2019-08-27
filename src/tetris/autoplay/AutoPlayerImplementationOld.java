package tetris.autoplay;

import tetris.game.TetrisGameView;
import tetris.game.pieces.*;
import tetris.game.pieces.Piece.PieceType;

public class AutoPlayerImplementationOld implements AutoPlayer
{
	private TetrisGameView game;
	private Piece currentPiece;
	private Piece nextPiece;
	private PieceType[][] pieceTypeBoard;
	private int column;
	private int row;
	private int rows;
	private int columns;
	
	public AutoPlayerImplementationOld(TetrisGameView pGame)
	{
		game = pGame;
		game.addObserver(this);
		currentPiece = game.getCurrentPieceCopy();
		nextPiece = game.getNextPieceCopy();
		column = game.getPieceColumn();
		row = game.getPieceRow();
		rows = game.getBoardCopy().getNumberOfRows();
		columns = game.getBoardCopy().getNumberOfColumns();
		pieceTypeBoard = new PieceType[rows][columns];
		
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				pieceTypeBoard[i][j] = null;
			}
		}
	}

	@Override
	public void rowsCompleted()
	{
		int value = 0;
	
		for(int i = rows-1; i >= 0; i--)
		{
			for(int j = 0; j < columns; j++)
			{
				if(pieceTypeBoard[i][j] != null)
				{
					value++;
				}
			}
			if(value == columns)
			{
				for(int j = 0; j < columns; j++)
				{
					if(pieceTypeBoard[i][j] != null)
					{
						pieceTypeBoard[i][j] = null;
					}
				}
				for(int ii = i; ii >= 0; ii--)
					{
						for(int j = 0; j < columns; j++)
						{
							if(ii == 0)
							{
								pieceTypeBoard[ii][j] = null;
							}
							else
							{
								pieceTypeBoard[ii][j] = pieceTypeBoard[ii-1][j];
							}
						}
					}
			}
			i++;
		}
		value = 0;
	}

	@Override
	public void piecePositionChanged()
	{
		Point rotationPoint = currentPiece.getRotationPoint();
		boolean[][] pb = currentPiece.getBody();
		int pWidth = pb[0].length;
		int pHeight = pb.length;
		
		int leftOffset = rotationPoint.getColumn();
		int rightOffset = pWidth - leftOffset - 1;
		int upOffset = rotationPoint.getRow();
		int downOffset = pHeight - upOffset - 1;
		
		int leftIndex = column - leftOffset;
		int rightIndex = column + rightOffset;
		int upIndex = row - upOffset;
		int downIndex = row + downOffset;
		int x = 0;
		int y = 0;
		for(int i = upIndex; i <= downIndex; i++)
		{
			for(int j = leftIndex; j <= rightIndex; j++)
			{
				if(pb[y][x] == true)
				{
					pieceTypeBoard[i][j] = currentPiece.getPieceType();
				}
				x++;
			}
			x = 0;
			y++;
		}
	}

	@Override
	public void pieceLanded()
	{
		
		
		Point rotationPoint = currentPiece.getRotationPoint();
		boolean[][] pb = currentPiece.getBody();
		int pWidth = pb[0].length;
		int pHeight = pb.length;
		
		int leftOffset = rotationPoint.getColumn();
		int rightOffset = pWidth - leftOffset - 1;
		int upOffset = rotationPoint.getRow();
		int downOffset = pHeight - upOffset - 1;
		
		int leftIndex = column - leftOffset;
		int rightIndex = column + rightOffset;
		int upIndex = row - upOffset;
		int downIndex = row + downOffset;
		int x = 0;
		int y = 0;
		for(int i = upIndex; i <= downIndex; i++)
		{
			for(int j = leftIndex; j <= rightIndex; j++)
			{
				if(pb[y][x] == true)
				{
					pieceTypeBoard[i][j] = currentPiece.getPieceType();
				}
				x++;
			}
			x = 0;
			y++;
		}
	}

	@Override
	public void gameOver()
	{
		game.removeObserver(this);
	}

	@Override
	public Move getMove()
	{
		PieceType typeCurrentPiece = currentPiece.getPieceType();
		int cpColumn = game.getPieceColumn();
		int cpRow = game.getPieceRow();
		switch(typeCurrentPiece)
		{
			case I:
				Point a = new Point(1,0);
				Point b = new Point(2,0);

				
				if(currentPiece.getRotationPoint().equals(a) || currentPiece.getRotationPoint().equals(b))
				{
					return Move.ROTATE_CW;
				}
					if(pieceTypeBoard[cpRow][cpColumn] == null && pieceTypeBoard[cpRow+1][cpColumn] == null && pieceTypeBoard[cpRow+2][cpColumn] == null)
					{
						return Move.DOWN;
					}
					else
					{
						if(pieceTypeBoard[cpRow][cpColumn] == null && pieceTypeBoard[cpRow+1][cpColumn+1] == null && pieceTypeBoard[cpRow+2][cpColumn+2] == null)
						{
								return Move.LEFT;
						}
						else
					{
						return Move.RIGHT;
					}
				}
			case J:
				Point currentPoint = new Point(1,1);
				if(!currentPoint.equals(currentPiece.getRotationPoint()))
				{
					return Move.ROTATE_CCW;
				}
				else 
				{
					if(pieceTypeBoard[cpRow][cpColumn] == null && pieceTypeBoard[cpRow+1][cpColumn] == null && pieceTypeBoard[cpRow+2][cpColumn] == null)
					{
						return Move.DOWN;
					}
					else
					{
						if(pieceTypeBoard[cpRow][cpColumn] == null && pieceTypeBoard[cpRow+1][cpColumn+1] == null && pieceTypeBoard[cpRow+2][cpColumn+2] == null)
						{
								return Move.LEFT;
						}
						else
						{
						return Move.RIGHT;
						}
					}
				}
			case L:
				Point currentPoint2 = new Point(1,0);
				if(!currentPoint2.equals(currentPiece.getRotationPoint()))
				{
					return Move.ROTATE_CCW;
				}
				else 
				{
					if(pieceTypeBoard[cpRow][cpColumn] == null && pieceTypeBoard[cpRow+1][cpColumn] == null && pieceTypeBoard[cpRow+2][cpColumn] == null)
					{
						return Move.DOWN;
					}
					else
					{
						if(pieceTypeBoard[cpRow][cpColumn] == null && pieceTypeBoard[cpRow+1][cpColumn+1] == null && pieceTypeBoard[cpRow+2][cpColumn+2] == null)
						{
								return Move.LEFT;
						}
						else
						{
						return Move.RIGHT;
						}
					}
				}
			case O:
				return Move.DOWN;
			case S:
				Point currentPoint3 = new Point(0,1);
				if(!currentPoint3.equals(currentPiece.getRotationPoint()))
				{
					return Move.ROTATE_CW;
				}
				else 
				{
					if(pieceTypeBoard[cpRow][cpColumn] == null && pieceTypeBoard[cpRow+1][cpColumn] == null && pieceTypeBoard[cpRow+2][cpColumn] == null)
					{
						return Move.DOWN;
					}
					else
					{
						if(pieceTypeBoard[cpRow][cpColumn] == null && pieceTypeBoard[cpRow+1][cpColumn+1] == null && pieceTypeBoard[cpRow+2][cpColumn+2] == null)
						{
								return Move.LEFT;
						}
						else
						{
						return Move.RIGHT;
						}
					}
				}
			case T:
				Point currentPoint4 = new Point(1,1);
				if(!currentPoint4.equals(currentPiece.getRotationPoint()) || currentPiece.getWidth() != 3)
				{
					return Move.ROTATE_CW;
				}
				else 
				{
					if(pieceTypeBoard[cpRow][cpColumn] == null && pieceTypeBoard[cpRow+1][cpColumn] == null && pieceTypeBoard[cpRow+2][cpColumn] == null)
					{
						return Move.DOWN;
					}
					else
					{
						if(pieceTypeBoard[cpRow][cpColumn] == null && pieceTypeBoard[cpRow+1][cpColumn+1] == null && pieceTypeBoard[cpRow+2][cpColumn+2] == null)
						{
								return Move.LEFT;
						}
						else
						{
						return Move.RIGHT;
						}
					}
				}
			case Z:
				return Move.DOWN;
			default:
				return Move.DOWN; 
		}
	}
}
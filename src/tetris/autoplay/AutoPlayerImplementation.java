package tetris.autoplay;

import tetris.game.TetrisGameView;
import tetris.game.*;
import tetris.game.pieces.*;
import tetris.game.pieces.Piece.PieceType;
import tetris.game.TetrisGameImplementation;

public class AutoPlayerImplementation implements AutoPlayer
{
	private TetrisGameView game;
	private Piece currentPiece;
	private Piece nextPiece;
	private Board board;
	private PieceType[][] pieceTypeBoard;
	private int rows;
	private int columns;
	
	private int cpColumn;
	private int cpRow;
	private int bestRotation;
	private int bestOffset;
	private int lowestGaps;
	private int lowestBoardHeight;
	private int lowestPieceHeight;
	
	private boolean planComputed;
	
	public AutoPlayerImplementation(TetrisGameView pGame)
	{
		game = pGame;
		game.addObserver(this);
		currentPiece = game.getCurrentPiece();
		nextPiece = game.getNextPieceCopy();
		board = game.getBoardCopy();
		pieceTypeBoard = board.getBoard();
		rows = board.getNumberOfRows();
		columns = board.getNumberOfColumns();
		
		cpColumn = game.getPieceColumn();
		cpRow = game.getPieceRow();
		lowestGaps = 99999;
		lowestBoardHeight = 99999;
		lowestPieceHeight = 99999;
		
		planComputed = false;
	}

	@Override
	public void rowsCompleted()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void piecePositionChanged() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pieceLanded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub

	}

	@Override
	public Move getMove()
	{
		if(!planComputed)
		{
			board = game.getBoardCopy();
			pieceTypeBoard = board.getBoard();
			currentPiece = game.getCurrentPiece();
			nextPiece = game.getNextPieceCopy();
			cpColumn = game.getPieceColumn();
			cpRow = game.getPieceRow();
			
			computePlan();
			planComputed = true;
		}
		//todo
		return null;
	}
	
	private int countGaps()
	{
		int gaps = 0;
		for(int x = 0; x < columns; x++)
		{
			boolean foundBlock = false;
			for(int y = 0; y < rows; y++)
			{
				if(pieceTypeBoard[y][x] != null)
				{
					foundBlock = true;
				}
				if(foundBlock && pieceTypeBoard[y][x] == null)
				{
					gaps++;
				}
			}
		}
		return gaps;
	}
	
	private int countBoardHeight()
	{
		int height = 0;
		for(int x = 0; x < columns; x++)
		{
			for(int y = 0; y < rows; y++)
			{
				if(pieceTypeBoard[y][x] != null)
				{
					height += rows - y;
					break;
				}
			}
		}
		return height;
	}
	
	private void computePlan()
	{
		for(int rotation = 0; rotation <= 3; rotation++)
		{
			//left and 0 offset
			int offset = 0;
			while(true)
			{
				//down
				while(true)
				{
					if(board.canRemovePiece(currentPiece, cpRow, cpColumn))
					{
						board.removePiece(currentPiece, cpRow, cpColumn);
						if(board.canAddPiece(currentPiece, cpRow+1, cpColumn))
						{
							board.addPiece(currentPiece, cpRow+1, cpColumn);
							cpRow++;
						}
						else
						{
							board.addPiece(currentPiece, cpRow, cpColumn);
							break;
						}
					}
					else
					{
						break;
					}
				}
				
				
				//score
				int currentGaps = countGaps();
				int currentBoardHeight = countBoardHeight();
				if(currentGaps <= lowestGaps && currentBoardHeight <= lowestBoardHeight && cpRow <= lowestPieceHeight)
				{
					lowestGaps = currentGaps;
					lowestBoardHeight = currentBoardHeight;
					lowestPieceHeight = cpRow;
					bestRotation = rotation;
					bestOffset = offset;
				}
				
				
				//reset piece height
				board.removePiece(currentPiece, cpRow, cpColumn);
				board.addPiece(currentPiece, 2, cpColumn);
				cpRow = 2;
				
				
				//move left
				if(board.canRemovePiece(currentPiece, cpRow, cpColumn))
				{
					board.removePiece(currentPiece, cpRow, cpColumn);
					if(board.canAddPiece(currentPiece, cpRow, cpColumn-1))
					{
						board.addPiece(currentPiece, cpRow, cpColumn-1);
						cpColumn--;
						offset--;
					}
					else
					{
						board.addPiece(currentPiece, cpRow, cpColumn);
						break;
					}
				}
				else
				{
					break;
				}
			}
			
			
			//reset piece
			board.removePiece(currentPiece, cpRow, cpColumn);
			board.addPiece(currentPiece, 2, columns/2);
			cpRow = 2;
			cpColumn = columns/2;
			
			
			//right offset
			offset = 0;
			while(true)
			{
				//move right and down
				if(board.canRemovePiece(currentPiece, cpRow, cpColumn))
				{
					board.removePiece(currentPiece, cpRow, cpColumn);
					if(board.canAddPiece(currentPiece, cpRow, cpColumn+1))
					{
						board.addPiece(currentPiece, cpRow, cpColumn+1);
						cpColumn++;
						offset++;
						
						//down
						while(true)
						{
							if(board.canRemovePiece(currentPiece, cpRow, cpColumn))
							{
								board.removePiece(currentPiece, cpRow, cpColumn);
								if(board.canAddPiece(currentPiece, cpRow+1, cpColumn))
								{
									board.addPiece(currentPiece, cpRow+1, cpColumn);
									cpRow++;
								}
								else
								{
									board.addPiece(currentPiece, cpRow, cpColumn);
									break;
								}
							}
							else
							{
								break;
							}
						}
						
						
						//score
						int currentGaps = countGaps();
						int currentBoardHeight = countBoardHeight();
						if(currentGaps <= lowestGaps && currentBoardHeight <= lowestBoardHeight && cpRow <= lowestPieceHeight)
						{
							lowestGaps = currentGaps;
							lowestBoardHeight = currentBoardHeight;
							lowestPieceHeight = cpRow;
							bestRotation = rotation;
							bestOffset = offset;
						}
						
						
						//reset piece height
						board.removePiece(currentPiece, cpRow, cpColumn);
						board.addPiece(currentPiece, 2, cpColumn);
						cpRow = 2;
					}
					else
					{
						board.addPiece(currentPiece, cpRow, cpColumn);
						break;
					}
				}
				else
				{
					break;
				}
			}
		}
	}
}
package tetris.game;

import tetris.game.pieces.*;
import tetris.game.pieces.Piece.PieceType;

public class BoardImplementation implements Board
{

	private int rows;
	private int columns;
	private PieceType[][] board;
	private Piece[][] pieceBoard;
			
	public BoardImplementation(int prows, int pcolumns)
	{
		rows = prows;
		columns = pcolumns;
		board = new PieceType[rows][columns];
		pieceBoard = new Piece[rows][columns];
		
		for(int i=0;i<columns;i++)
		{
			for(int j=0;j<rows;j++)
			{
				board[j][i] = null;
				pieceBoard[j][i] = null;
			}	
		}
	}
	
	public BoardImplementation(int prows, int pcolumns, PieceType[][] pboard, Piece[][] ppieceBoard)
	{
		rows = prows;
		columns = pcolumns;
		board = pboard;
		pieceBoard = ppieceBoard;
	}

	@Override
	public PieceType[][] getBoard()
	{
		return board;
	}

	@Override
	public int getNumberOfRows() 
	{
		return rows;
	}

	@Override
	public int getNumberOfColumns()
	{
		return columns;
	}

	@Override
	public void addPiece(Piece piece, int row, int column)
	{
		if(canAddPiece(piece, row, column))
		{
			Point rotationPoint = piece.getRotationPoint();
			boolean[][] pb = piece.getBody();
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
			
//			System.out.println();
//			System.out.println("addPiece: PieceType: " + piece.getPieceType().toString());
//			System.out.println("addPiece: Piece Rotation: " + ((PieceImplementation)piece).getRotation());
//			System.out.println();
//			System.out.println("addPiece: pWidth: " + pWidth);
//			System.out.println("addPiece:  pHeight: " + pHeight);
//			System.out.println();
//			System.out.println("addPiece: leftOffset: " + leftOffset);
//			System.out.println("addPiece: rightOffset: " + rightOffset);
//			System.out.println("addPiece: upOffset: " + upOffset);
//			System.out.println("addPiece: downOffset: " + downOffset);
//			System.out.println();
//			System.out.println("addPiece: leftIndex: " + leftIndex);
//			System.out.println("addPiece: rightIndex: " + rightIndex);
//			System.out.println("addPiece: upIndex: " + upIndex);
//			System.out.println("addPiece: downIndex: " + downIndex);
//			System.out.println();
			
			int x = 0;
			int y = 0;
			for(int i = upIndex; i <= downIndex; i++)
			{
				for(int j = leftIndex; j <= rightIndex; j++)
				{
					if(pb[y][x] == true)
					{
						board[i][j] = piece.getPieceType();
						pieceBoard[i][j] = piece;
					}
					x++;
				}
				x = 0;
				y++;
			}
		}
	}

	@Override
	public boolean canAddPiece(Piece piece, int row, int column)
	{
		if(row < 0 || row >= rows || column < 0 || column >= columns)
		{
			return false;
		}
		
		Point rotationPoint = piece.getRotationPoint();
		boolean[][] pb = piece.getBody();
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
		
		if(leftIndex < 0 || leftIndex >= columns || rightIndex < 0 || rightIndex >= columns || upIndex < 0 || upIndex >= rows || downIndex < 0 || downIndex >= rows)
		{
			return false;
		}
		
//		System.out.println();
//		System.out.println("canAddPiece: PieceType: " + piece.getPieceType().toString());
//		System.out.println("canAddPiece: Piece Rotation: " + ((PieceImplementation)piece).getRotation());
//		System.out.println();
//		System.out.println("canAddPiece: pWidth: " + pWidth);
//		System.out.println("canAddPiece:  pHeight: " + pHeight);
//		System.out.println();
//		System.out.println("canAddPiece: leftOffset: " + leftOffset);
//		System.out.println("canAddPiece: rightOffset: " + rightOffset);
//		System.out.println("canAddPiece: upOffset: " + upOffset);
//		System.out.println("canAddPiece: downOffset: " + downOffset);
//		System.out.println();
//		System.out.println("canAddPiece: leftIndex: " + leftIndex);
//		System.out.println("canAddPiece: rightIndex: " + rightIndex);
//		System.out.println("canAddPiece: upIndex: " + upIndex);
//		System.out.println("canAddPiece: downIndex: " + downIndex);
//		System.out.println();
		
		int x = 0;
		int y = 0;
		for(int i = upIndex; i <= downIndex; i++)
		{
			for(int j = leftIndex; j <= rightIndex; j++)
			{
//				System.out.println("canAddPiece: x: " + x + " y: " + y);
				if(pb[y][x] == true)
				{
					if(board[i][j] != null)
					{
						return false;
					}
				}
				x++;
			}
			x = 0;
			y++;
		}
		return true;
	}

	@Override
	public void removePiece(Piece piece, int row, int column)
	{
		if(canRemovePiece(piece, row, column))
		{
			boolean[][] pb = piece.getBody();
			int pWidth = pb[0].length;
			int pHeight = pb.length;
			
			int leftIndex = column - pWidth +  1;
			int rightIndex = column + pWidth - 1;
			int upIndex = row - pHeight + 1;
			int downIndex = row + pHeight - 1;
			
			if(leftIndex < 0)
			{
				leftIndex = 0;
			}
			if(rightIndex >= columns)
			{
				rightIndex = columns - 1;
			}
			if(upIndex < 0)
			{
				upIndex = 0;
			}
			if(downIndex >= rows)
			{
				downIndex = rows - 1;
			}
			
			System.out.println();
			System.out.println("removePiece: PieceType: " + piece.getPieceType().toString());
			System.out.println("removePiece: Piece Rotation: " + ((PieceImplementation)piece).getRotation());
			System.out.println();
			System.out.println("removePiece: pWidth: " + pWidth);
			System.out.println("removePiece:  pHeight: " + pHeight);
			System.out.println();
			System.out.println("removePiece: leftIndex: " + leftIndex);
			System.out.println("removePiece: rightIndex: " + rightIndex);
			System.out.println("removePiece: upIndex: " + upIndex);
			System.out.println("removePiece: downIndex: " + downIndex);
			System.out.println();
			
			for(int i = upIndex; i <= downIndex; i++)
			{
				for(int j = leftIndex; j <= rightIndex; j++)
				{
					if(pieceBoard[i][j] == piece)
					{
						board[i][j] = null;
						pieceBoard[i][j] = null;
					}
				}
			}
		}
	}

	@Override
	public boolean canRemovePiece(Piece piece, int row, int column)
	{
		if(board[row][column] == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public int deleteCompleteRows()
	{
		int value = 0;
		int returnValue = 0;
		for(int i = rows-1; i >= 0; i--)
		{
			for(int j = 0; j < columns; j++)
			{
				if(board[i][j] != null)
				{
					value++;
				}
			}
			if(value == columns)
			{
				for(int j = 0; j < columns; j++)
				{
					if(board[i][j] != null)
					{
						board[i][j] = null;
						pieceBoard[i][j] = null;
					}
				}
				
				for(int ii = i; ii >= 0; ii--)
				{
					for(int j = 0; j < columns; j++)
					{
						if(ii == 0)
						{
							board[ii][j] = null;
							pieceBoard[ii][j] = null;
						}
						else
						{
							board[ii][j] = board[ii-1][j];
							pieceBoard[ii][j] = pieceBoard[ii-1][j];
						}
					}
				}
				
				i++;
				returnValue++;
			}
			value = 0;
		}
		return returnValue;
	}

	@Override
	public Board clone()
	{
		PieceType[][] boardSave = new PieceType[rows][columns];
		for(int i = 0; i < columns; i++)
		{
			for(int j = 0; j < rows; j++)
			{
				boardSave[j][i] = board[j][i];
			}
		}
		Piece[][] pieceBoardSave = new Piece[rows][columns];
		for(int i = 0; i < columns; i++)
		{
			for(int j = 0; j < rows; j++)
			{
				pieceBoardSave[j][i] = pieceBoard[j][i];
			}
		}
		return new BoardImplementation(rows, columns, boardSave, pieceBoardSave); 
	}
	
	public void printStats()
	{
		System.out.println();
		System.out.println("PieceType Board Width: " + board[0].length);
		System.out.println("PieceType Board Height: " + board.length);
		System.out.println("Piece Board Width: " + pieceBoard[0].length);
		System.out.println("Piece Board Height: " + pieceBoard.length);
		System.out.println();
		
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[0].length; j++)
			{
				if(board[i][j] == null)
				{
					System.out.print("X ");
				}
				else
				{
					System.out.print(board[i][j] + " ");
				}
				
			}
			System.out.println();
		}
		System.out.println();
		
		for(int i = 0; i < pieceBoard.length; i++)
		{
			for(int j = 0; j < pieceBoard[0].length; j++)
			{
				if(pieceBoard[i][j] == null)
				{
					System.out.print("X ");
				}
				else
				{
					System.out.print(pieceBoard[i][j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
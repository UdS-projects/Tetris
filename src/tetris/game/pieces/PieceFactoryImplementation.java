package tetris.game.pieces;

import java.util.Random;
import tetris.game.pieces.Piece.PieceType;


public class PieceFactoryImplementation implements PieceFactory
{

	private Random random;
	
	public PieceFactoryImplementation(Random prandom)
	{
		random = prandom;
	}
	
	@Override
	public Piece getIPiece()
	{
		boolean body[][] = new boolean[4][1]; 
		for(int i=0;i<4;i++)
		{
			body[i][0] = true;
		}
		
		return new PieceImplementation(body, PieceType.I, (byte) 0);
	}

	@Override
	public Piece getJPiece()
	{
		boolean body[][] = new boolean[3][2];
		body[0][0] = false;
		body[1][0] = false;
		body[2][0] = true;
		body[0][1] = true;
		body[1][1] = true;
		body[2][1] = true;
		
		return new PieceImplementation(body, PieceType.J, (byte) 0);
	}

	@Override
	public Piece getLPiece()
	{
		boolean body[][] = new boolean[3][2];
		body[0][0] = true;
		body[1][0] = true;
		body[2][0] = true;
		body[0][1] = false;
		body[1][1] = false;
		body[2][1] = true;
		
		return new PieceImplementation(body, PieceType.L, (byte) 0);
	}

	@Override
	public Piece getOPiece()
	{
		boolean body[][] = new boolean[2][2];
		body[0][0] = true;
		body[1][0] = true;
		body[0][1] = true;
		body[1][1] = true;
		
		return new PieceImplementation(body, PieceType.O, (byte) 0);
	}

	@Override
	public Piece getSPiece()
	{
		boolean body[][] = new boolean[2][3];
		body[0][0] = false;
		body[1][0] = true;
		body[0][1] = true;
		body[1][1] = true;
		body[0][2] = true;
		body[1][2] = false;
		
		return new PieceImplementation(body, PieceType.S, (byte) 0);
	}

	@Override
	public Piece getZPiece()
	{
		boolean body[][] = new boolean[2][3];
		body[0][0] = true;
		body[1][0] = false;
		body[0][1] = true;
		body[1][1] = true;
		body[0][2] = false;
		body[1][2] = true;
		
		return new PieceImplementation(body, PieceType.Z, (byte) 0);
	}

	@Override
	public Piece getTPiece()
	{
		boolean body[][] = new boolean[2][3];
		body[0][0] = true;
		body[1][0] = false;
		body[0][1] = true;
		body[1][1] = true;
		body[0][2] = true;
		body[1][2] = false;
		
		return new PieceImplementation(body, PieceType.T, (byte) 0);
	}

	@Override
	public Piece getNextRandomPiece()
	{
		//int randomNumber = (int)(Math.random()*7);
		int randomNumber = random.nextInt();
		if(randomNumber < 0)
		{
			randomNumber = randomNumber * -1;
		}
		
		randomNumber = randomNumber % 7;
		//System.out.println("Trying to generate piece with number " + randomNumber);
		
		switch(randomNumber)
		{
		case 0:
			return getIPiece();
		case 1:
			return getJPiece();
		case 2:
			return getLPiece();
		case 3:
			return getOPiece();
		case 4:
			 return getSPiece();
		case 5:
			return getZPiece();
		case 6:
			return getTPiece();
			
		default:
			return null;
		}
	}

}

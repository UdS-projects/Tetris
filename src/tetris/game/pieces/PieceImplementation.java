package tetris.game.pieces;

public class PieceImplementation implements Piece {

	private boolean[][] body;
	private PieceType type;
	private byte rotation;
	
	public PieceImplementation(boolean[][] pbody, PieceType ptype, byte protation)
	{
		body = pbody;
		type = ptype;
		rotation = protation;
	}

	@Override
	public int getWidth()
	{
		switch(type)
		{
			case L: 
				if(rotation == 0 || rotation == 2)
				{
					return 2;
				}
				if(rotation == 1 || rotation == 3)
				{
					return 3;
				}
			case J:
				if(rotation == 0 || rotation == 2)
				{
					return 2;
				}
				if(rotation == 1 || rotation == 3)
				{
					return 3;
				}
			case T: 
				if(rotation == 0 || rotation == 2)
				{
					return 3;
				}
				if(rotation == 1 || rotation == 3)
				{
					return 2;
				}
			case O:
				return 2;
			case I:
				if(rotation == 0 || rotation == 2)
				{
					return 1;
				}
				if(rotation == 1 || rotation == 3)
				{
					return 4;
				}
			case Z:
				if(rotation == 0 || rotation == 2)
				{
					return 3;
				}
				if(rotation == 1 || rotation == 3)
				{
					return 2;
				}
			case S:
				if(rotation == 0 || rotation == 2)
				{
					return 3;
				}
				if(rotation == 1 || rotation == 3)
				{
					return 2;
				}
				
			default: return 0;
		}
	}

	@Override
	public int getHeight() {
		switch(type)
		{
			case L: 
				if(rotation == 0 || rotation == 2)
				{
					return 3;
				}
				if(rotation == 1 || rotation == 3)
				{
					return 2;
				}
			case J:
				if(rotation == 0 || rotation == 2)
				{
					return 3;
				}
				if(rotation == 1 || rotation == 3)
				{
					return 2;
				}
			case T: 
				if(rotation == 0 || rotation == 2)
				{
					return 2;
				}
				if(rotation == 1 || rotation == 3)
				{
					return 3;
				}
			case O:
				return 2;
			case I:
				if(rotation == 0 || rotation == 2)
				{
					return 4;
				}
				if(rotation == 1 || rotation == 3)
				{
					return 1;
				}
			case Z:
				if(rotation == 0 || rotation == 2)
				{
					return 2;
				}
				if(rotation == 1 || rotation == 3)
				{
					return 3;
				}
			case S:
				if(rotation == 0 || rotation == 2)
				{
					return 2;
				}
				if(rotation == 1 || rotation == 3)
				{
					return 3;
				}
				
			default: return 0;
		}
	}

	@Override
	public boolean[][] getBody()
	{
		return body;
	}

	@Override
	public Piece getClockwiseRotation()
	{
		switch(type)
		{
			case L:
			{
				if(rotation == 0)
				{
					boolean[][] newBody = new boolean[2][3];
					newBody[0][0] = true;
					newBody[1][0] = true;
					newBody[0][1] = true;
					newBody[1][1] = false;
					newBody[0][2] = true;
					newBody[1][2] = false;
					
					return new PieceImplementation(newBody, type, (byte) 1);
				}
				else
				{
					if(rotation == 1)
					{
						boolean[][] newBody = new boolean[3][2];
						newBody[0][0] = true;
						newBody[1][0] = false;
						newBody[2][0] = false;
						newBody[0][1] = true;
						newBody[1][1] = true;
						newBody[2][1] = true;
						
						return new PieceImplementation(newBody, type, (byte) 2);
					}
					else
					{
						if(rotation == 2)
						{
							boolean[][] newBody = new boolean[2][3];
							newBody[0][0] = false;
							newBody[1][0] = true;
							newBody[0][1] = false;
							newBody[1][1] = true;
							newBody[0][2] = true;
							newBody[1][2] = true;
							
							return new PieceImplementation(newBody, type, (byte) 3);
						}
						else
						{
							boolean[][] newBody = new boolean[3][2];
							newBody[0][0] = true;
							newBody[1][0] = true;
							newBody[2][0] = true;
							newBody[0][1] = false;
							newBody[1][1] = false;
							newBody[2][1] = true;
							
							return new PieceImplementation(newBody, type, (byte) 0);
						}
					}
				}
			}
			
			case J:
			{
				if(rotation == 0)
				{
					boolean[][] newBody = new boolean[2][3];
					newBody[0][0] = true;
					newBody[1][0] = true;
					newBody[0][1] = false;
					newBody[1][1] = true;
					newBody[0][2] = false;
					newBody[1][2] = true;
					
					return new PieceImplementation(newBody, type, (byte) 1);
				}
				else
				{
					if(rotation == 1)
					{
						boolean[][] newBody = new boolean[3][2];
						newBody[0][0] = true;
						newBody[1][0] = true;
						newBody[2][0] = true;
						newBody[0][1] = true;
						newBody[1][1] = false;
						newBody[2][1] = false;
						
						return new PieceImplementation(newBody, type, (byte) 2);
					}
					else
					{
						if(rotation == 2)
						{
							boolean[][] newBody = new boolean[2][3];
							newBody[0][0] = true;
							newBody[1][0] = false;
							newBody[0][1] = true;
							newBody[1][1] = false;
							newBody[0][2] = true;
							newBody[1][2] = true;
							
							
							return new PieceImplementation(newBody, type, (byte) 3);
						}
						else
						{
							boolean[][] newBody = new boolean[3][2];
							newBody[0][0] = false;
							newBody[1][0] = false;
							newBody[2][0] = true;
							newBody[0][1] = true;
							newBody[1][1] = true;
							newBody[2][1] = true;
							
							return new PieceImplementation(newBody, type, (byte) 0);
						}
					}
				}
			}
			case T:
			{
				if(rotation == 0)
				{
					boolean[][] newBody = new boolean[3][2];
					newBody[0][0] = false;
					newBody[1][0] = true;
					newBody[2][0] = false;
					newBody[0][1] = true;
					newBody[1][1] = true;
					newBody[2][1] = true;
					
					return new PieceImplementation(newBody, type, (byte) 1);
				}
				else
				{
					if(rotation == 1)
					{
						boolean[][] newBody = new boolean[2][3];
						newBody[0][0] = false;
						newBody[1][0] = true;
						newBody[0][1] = true;
						newBody[1][1] = true;
						newBody[0][2] = false;
						newBody[1][2] = true;
						
						return new PieceImplementation(newBody, type, (byte) 2);
					}
					else
					{
						if(rotation == 2)
						{
							boolean[][] newBody = new boolean[3][2];
							newBody[0][0] = true;
							newBody[1][0] = true;
							newBody[2][0] = true;
							newBody[0][1] = false;
							newBody[1][1] = true;
							newBody[2][1] = false;
							
							return new PieceImplementation(newBody, type, (byte) 3);
						}
						else
						{
							boolean[][] newBody = new boolean[2][3];
							newBody[0][0] = true;
							newBody[1][0] = false;
							newBody[0][1] = true;
							newBody[1][1] = true;
							newBody[0][2] = true;
							newBody[1][2] = false;
								
							return new PieceImplementation(newBody, type, (byte) 0);
						}
					}
				}
			}
			case O:
			{
				if(rotation == 3)
				{
					return new PieceImplementation(body, type, (byte) 0);
				}
				else
				{
					return new PieceImplementation(body, type, (byte)(rotation + 1));
				}
			}
			case I:
			{
				//System.out.println("Clockwise I");
				if(rotation == 0 || rotation == 2)
				{
					boolean[][] newBody = new boolean[1][4];
					newBody[0][0] = true;
					newBody[0][1] = true;
					newBody[0][2] = true;
					newBody[0][3] = true;
					
					return new PieceImplementation(newBody, type, (byte)(rotation + 1));
				}
				else
				{
					boolean[][] newBody = new boolean[4][1];
					newBody[0][0] = true;
					newBody[1][0] = true;
					newBody[2][0] = true;
					newBody[3][0] = true;
					
					if(rotation != 3)
					{
						return new PieceImplementation(newBody, type, (byte)(rotation + 1));
					}
					else
					{
						return new PieceImplementation(newBody, type, (byte) 0);
					}
				}
			}
			case Z:
			{
				if(rotation == 0 || rotation == 2)
				{

					boolean[][] newBody = new boolean[3][2];
					newBody[0][0] = false;
					newBody[1][0] = true;
					newBody[2][0] = true;
					newBody[0][1] = true;
					newBody[1][1] = true;
					newBody[2][1] = false;
					
					return new PieceImplementation(newBody, type, (byte)(rotation + 1));
				}
				else
				{
					boolean[][] newBody = new boolean[2][3];
					newBody[0][0] = true;
					newBody[1][0] = false;
					newBody[0][1] = true;
					newBody[1][1] = true;
					newBody[0][2] = false;
					newBody[1][2] = true;
					
					if(rotation != 3)
					{
						return new PieceImplementation(newBody, type, (byte)(rotation + 1));
					}
					else
					{
						return new PieceImplementation(newBody, type, (byte) 0);
					}
				}
			}
			case S:
			{
				if(rotation == 0 || rotation == 2)
				{
					boolean[][] newBody = new boolean[3][2];
					newBody[0][0] = true;
					newBody[1][0] = true;
					newBody[2][0] = false;
					newBody[0][1] = false;
					newBody[1][1] = true;
					newBody[2][1] = true;
					
					return new PieceImplementation(newBody, type, (byte)(rotation + 1));
				}
				else
				{
					boolean[][] newBody = new boolean[2][3];
					newBody[0][0] = false;
					newBody[1][0] = true;
					newBody[0][1] = true;
					newBody[1][1] = true;
					newBody[0][2] = true;
					newBody[1][2] = false;
					
					if(rotation != 3)
					{
						return new PieceImplementation(newBody, type, (byte)(rotation + 1));
					}
					else
					{
						return new PieceImplementation(newBody, type, (byte) 0);
					}
				}
			}
			
			default: return null;
		}
	}

	@Override
	public Piece getCounterClockwiseRotation() {
		switch(type)
		{
			case L:
			{
				if(rotation == 0)
				{
					boolean[][] newBody = new boolean[2][3];
					newBody[0][0] = false;
					newBody[1][0] = true;
					newBody[0][1] = false;
					newBody[1][1] = true;
					newBody[0][2] = true;
					newBody[1][2] = true;
					
					return new PieceImplementation(newBody, type, (byte) 3);
				}
				else 
				{
					if(rotation == 1)
					{
						boolean[][] newBody = new boolean[3][2];
						newBody[0][0] = true;
						newBody[1][0] = true;
						newBody[2][0] = true;
						newBody[0][1] = false;
						newBody[1][1] = false;
						newBody[2][1] = true;
						
						return new PieceImplementation(newBody, type, (byte) 0);
					}
					else
					{
						if(rotation == 2)
						{
							boolean[][] newBody = new boolean[2][3];
							newBody[0][0] = true;
							newBody[1][0] = true;
							newBody[0][1] = true;
							newBody[1][1] = false;
							newBody[0][2] = true;
							newBody[1][2] = false;
								
							return new PieceImplementation(newBody, type, (byte) 1);
						}
						else
						{
							boolean[][] newBody = new boolean[3][2];
							newBody[0][0] = true;
							newBody[1][0] = false;
							newBody[2][0] = false;
							newBody[0][1] = true;
							newBody[1][1] = true;
							newBody[2][1] = true;
							
							return new PieceImplementation(newBody, type, (byte) 2);
						}
					}
				}
			}
			
			case J:
			{
				if(rotation == 0)
				{

					boolean[][] newBody = new boolean[2][3];
					newBody[0][0] = true;
					newBody[1][0] = false;
					newBody[0][1] = true;
					newBody[1][1] = false;
					newBody[0][2] = true;
					newBody[1][2] = true;
					
					
					return new PieceImplementation(newBody, type, (byte) 3);
				}
				else
				{
					if(rotation == 1)
					{

						boolean[][] newBody = new boolean[3][2];
						newBody[0][0] = false;
						newBody[1][0] = false;
						newBody[2][0] = true;
						newBody[0][1] = true;
						newBody[1][1] = true;
						newBody[2][1] = true;
					
						return new PieceImplementation(newBody, type, (byte) 0);
					}
					else
					{
						if(rotation == 2)
						{
							
							boolean[][] newBody = new boolean[2][3];
							newBody[0][0] = true;
							newBody[1][0] = true;
							newBody[0][1] = false;
							newBody[1][1] = true;
							newBody[0][2] = false;
							newBody[1][2] = true;
							
							return new PieceImplementation(newBody, type, (byte) 1);
						}
						else
						{
							boolean[][] newBody = new boolean[3][2];
							newBody[0][0] = true;
							newBody[1][0] = true;
							newBody[2][0] = true;
							newBody[0][1] = true;
							newBody[1][1] = false;
							newBody[2][1] = false;
							
							return new PieceImplementation(newBody, type, (byte) 2);
						}
					}
				}
			}
			case T:
			{
				if(rotation == 0)
				{
					boolean[][] newBody = new boolean[3][2];
					newBody[0][0] = true;
					newBody[1][0] = true;
					newBody[2][0] = true;
					newBody[0][1] = false;
					newBody[1][1] = true;
					newBody[2][1] = false;
					
					return new PieceImplementation(newBody, type, (byte) 3);
				}
				else
				{
					if(rotation == 1)
					{
						boolean[][] newBody = new boolean[2][3];
						newBody[0][0] = true;
						newBody[1][0] = false;
						newBody[0][1] = true;
						newBody[1][1] = true;
						newBody[0][2] = true;
						newBody[1][2] = false;
						
						return new PieceImplementation(newBody, type, (byte) 0);
					}
					else
					{
						if(rotation == 2)
						{
							boolean[][] newBody = new boolean[3][2];
							newBody[0][0] = false;
							newBody[1][0] = true;
							newBody[2][0] = false;
							newBody[0][1] = true;
							newBody[1][1] = true;
							newBody[2][1] = true;
							
							return new PieceImplementation(newBody, type, (byte) 1);
						}
						else
						{
							boolean[][] newBody = new boolean[2][3];
							newBody[0][0] = false;
							newBody[1][0] = true;
							newBody[0][1] = true;
							newBody[1][1] = true;
							newBody[0][2] = false;
							newBody[1][2] = true;
							
							return new PieceImplementation(newBody, type, (byte) 2);
						}
					}
				}
			}
			case O:
			{
				if (rotation == 0)
				{
					return new PieceImplementation(body, type, (byte) 3);
				}
				else
				{
					return new PieceImplementation(body, type, (byte) (rotation - 1));
				}
			}
			case I:
			{
				//System.out.println("Counter Clockwise I");
				if(rotation == 0 || rotation == 2)
				{
					boolean[][] newBody = new boolean[1][4];
					newBody[0][0] = true;
					newBody[0][1] = true;
					newBody[0][2] = true;
					newBody[0][3] = true;
					
					if(rotation != 0)
					{
						return new PieceImplementation(newBody, type, (byte) (rotation - 1));
					}
					else 
					{
						return new PieceImplementation(newBody, type, (byte) 3);	
					}
				}
				else
				{
					boolean[][] newBody = new boolean[4][1];
					newBody[0][0] = true;
					newBody[1][0] = true;
					newBody[2][0] = true;
					newBody[3][0] = true;
					
					return new PieceImplementation(newBody, type, (byte) (rotation - 1));
				}
			}
			case Z:
			{
				if(rotation == 0 || rotation == 2)
				{
					boolean[][] newBody = new boolean[3][2];
					newBody[0][0] = false;
					newBody[1][0] = true;
					newBody[2][0] = true;
					newBody[0][1] = true;
					newBody[1][1] = true;
					newBody[2][1] = false;
					
					if(rotation != 0)
					{
						return new PieceImplementation(newBody, type, (byte)(rotation - 1));
					}
					else
					{
						return new PieceImplementation(newBody, type, (byte) 3);
					}
				}
				else
				{
					boolean[][] newBody = new boolean[2][3];
					newBody[0][0] = true;
					newBody[1][0] = false;
					newBody[0][1] = true;
					newBody[1][1] = true;
					newBody[0][2] = false;
					newBody[1][2] = true;
					
					return new PieceImplementation(newBody, type, (byte)(rotation - 1));
				}
			}
			case S:
			{
				if(rotation == 0 || rotation == 2)
				{
					boolean[][] newBody = new boolean[3][2];
					newBody[0][0] = true;
					newBody[1][0] = true;
					newBody[2][0] = false;
					newBody[0][1] = false;
					newBody[1][1] = true;
					newBody[2][1] = true;
					
					if(rotation != 0)
					{
						return new PieceImplementation(newBody, type, (byte)(rotation - 1));	
					}
					else
					{
						return new PieceImplementation(newBody, type, (byte) 3);
					}
				}
				else
				{
					boolean[][] newBody = new boolean[2][3];
					newBody[0][0] = false;
					newBody[1][0] = true;
					newBody[0][1] = true;
					newBody[1][1] = true;
					newBody[0][2] = true;
					newBody[1][2] = false;
					
					return new PieceImplementation(newBody, type, (byte)(rotation - 1));
				}
			}
			
			default: return null;
		}
	}

	@Override
	public Point getRotationPoint()
	{
		//System.out.println("Arrived in getRotationPoint");
		switch(type)
		{
			case L:
			{
				if(rotation == 0)
				{	
					return new Point(1, 0);
				}
				if(rotation == 1)
				{
					return new Point(0, 1);
				}
				if(rotation == 2)
				{
					return new Point(1, 1);
				}
				if(rotation == 3)
				{
					return new Point(1, 1);
				}
			}
			
			case J:
			{
				if(rotation == 0)
				{
					return new Point(1, 1);
				}
				if(rotation == 1)
				{
					return new Point(1, 1);
				}
				if(rotation == 2)
				{
					return new Point(1, 0);
				}
				if(rotation == 3)
				{
					return new Point(0, 1);
				}
			}
			case T:
			{
				if(rotation == 0)
				{
					return new Point(0, 1);
				}
				if(rotation == 1)
				{
					return new Point(1, 1);
				}
				if(rotation == 2)
				{
					return new Point(1, 1);
				}
				if(rotation == 3)
				{
					return new Point(1, 0);
				}
			}
			case O:
			{
				if(rotation == 0)
				{
					return new Point(1, 1);
				}
				if(rotation == 1)
				{
					return new Point(1, 0);
				}
				if(rotation == 2)
				{
					return new Point(0, 0);
				}
				if(rotation == 3)
				{
					return new Point(0, 1);
				}
			}
			case I:
			{
				if(rotation == 0)
				{
					return new Point(1, 0);
				}
				if(rotation == 1)
				{
					return new Point(0, 2);
				}
				if(rotation == 2)
				{
					return new Point(2, 0);
				}
				if(rotation == 3)
				{
					return new Point(0, 1);
				}
			}
			case Z:
			{
				if(rotation == 0)
				{
					return new Point(1, 1);
				}
				if(rotation == 1)
				{
					return new Point(1, 0);
				}
				if(rotation == 2)
				{
					return new Point(0, 1);
				}
				if(rotation == 3)
				{
					return new Point(1, 1);
				}
			}
			case S:
			{
				if(rotation == 0)
				{
					return new Point(1, 1);
				}
				if(rotation == 1)
				{
					return new Point(1, 0);
				}
				if(rotation == 2)
				{
					return new Point(0, 1);
				}
				if(rotation == 3)
				{
					return new Point(1, 1);
				}
			}
			
			default: return null;
		}
	}
	

	@Override
	public PieceType getPieceType()
	{
		return type;
	}

	@Override
	public Piece clone()
	{
		boolean newBody[][] = new boolean[getHeight()][getWidth()];
		for(int i=0; i<getHeight(); i++)
		{
			for(int j=0; j<getWidth();j++)
			{
				newBody[i][j] = body[i][j];
			}
		}
		
		PieceType x = type;
		byte rotationCopy = rotation;
		
		return new PieceImplementation(newBody, x, rotationCopy);
	}
	
	public byte getRotation()
	{
		return rotation;
	}

	@Override
	public boolean equals(Object object)
	{
		if(object instanceof Piece) 
		{
			PieceImplementation piece = (PieceImplementation) object;
			if(type == piece.getPieceType() && rotation == piece.getRotation())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
}
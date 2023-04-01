/**
 * @author Conner Sommerfield
 * CS 160 - 03: Intermediate Computer Programming
 * Professor Kraft
 * 6 May 2022
 */


import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Frog extends Racer {
	
	private int yStart = getY();
	private int xDifference = 0;

   /** Default Constructor: calls Racer default constructor
   */
   public Frog( )
   {
     super( );
   }

   /** Constructor
   *    @param rID  racer Id, passed to Racer constructor
   *    @param rX    x position, passed to Racer constructor
   *    @param rY    y position, passed to Racer constructor
   */
   public Frog( String rID, int rX, int rY )
   {
     super( rID, rX, rY );
   }

   /** move:  calculates the new x position for the racer
    * 
   *   Frog move characteristics: Is lazy and doesn't move 95% of the time
   *   When it does decide to move, picks random hop distance between 10 and 20.
   *   A theoretical parabola array is created and 
   *   filled with Y values based on ax2 + bx formula
   *   Frog Y value jumps to vertex of parabola (value in index of parabola array length / 2)
   *   On next move() cycle, frog returns to original Y value and 
   *   travels the same x value that it traveled the previous cycle
   *   (finishes parabola trajectory on way down due to gravity)
   *   Waits for random move command again to start new parabola trajectory
   *     
   */
   public void move( )
   {
	   Random rand = new Random( );
	   int move =  rand.nextInt( 100 ) + 1 ;
	   int parabolaXLength;
	   double parabolaXLengthMin = 10.0;
	   double parabolaXLengthMax = 20.0;
	   double a;
	   double b;
	   
	   int xStart = getX();
	   
	   /** if frog is not at original Y position, it must be in middle of hop
	    * this means it needs to return to original Y position and
	    * move appropriate x amount based on what it move last cycle
		 */
	   if (getY() != yStart) {
		   setX(getX() + xDifference);
		   setY(yStart);
		   
	   }
	   
	   /**
	    * If not out of original spot, frog is ready to hop
	    */
	   else {
		   if ( move > 95 ) {
			   
			   parabolaXLength = (int)Math.floor(Math.random()*
					   (parabolaXLengthMax-parabolaXLengthMin)+parabolaXLengthMin); // random hop length
			   a = .3333333;  // appropriate a value for parabola to keep frog from colliding with other racers
			   b = -1 * parabolaXLength * a; // based on vertex formula -b/2a (had to do some algebra)
			   
			   double[] parabolaYPoints = new double[parabolaXLength];
			   for (int i = 0; i < parabolaYPoints.length; i++) {
				   parabolaYPoints[i] = a * Math.pow((double)i, 2) + b * i; // parabola equation for Ypoints
			   }
			   for(int j = 0; j < parabolaYPoints.length; j++) {
				   setX( getX() + 1 );
				   
			   }
			   setY(yStart + (int)parabolaYPoints[parabolaYPoints.length/2]); //frog moves to y vertex of parabola
		   }
		   
	   int xFinish = getX();
	   xDifference = xFinish - xStart;
   
	   }
   }

   /** draw: draws the Frog at current (x, y) coordinate
   *       @param g   Graphics context
   */
   public void draw( Graphics g )
   {
     int startX = getX( );
     int startY = getY( );
     
     if (this.isWinner) {
    	 morph(g);
     }
     
     g.setColor( new Color( 102, 255, 102 ) ); // light green
 	//body
      g.fillOval( startX - 30, startY - 5, 30, 20 );

      //head
      g.fillOval( startX - 15, startY -10,  18, 25 );

      // eye
      g.setColor( new Color( 0, 0, 0));
      g.fillOval( startX - 7  , startY - 5, 5, 5);
      
      //stain
      g.setColor( new Color( 20, 20, 20));
      g.fillOval( startX - 25, startY - 2, 10, 8 );
      
      //flatten bottom
      g.clearRect( startX - 30, startY + 11, 35, 4 );
       
      //legs
      g.setColor( new Color( 34, 139, 34 ) );  // dark green
      g.fillRect( startX - 27, startY + 10,  12, 5 );
      g.fillRect( startX - 13, startY + 10, 12, 5 );

     
   }
   
   /**
    * Helper method to continually draw frogs for proliferation morph effect
    */
   public void drawInstructions(Graphics g) {
	   int startX = getX( );
	   int startY = getY( );
	   
	    g.setColor( new Color( 102, 255, 102 ) ); // light green
   	//body
        g.fillOval( startX - 30, startY - 5, 30, 20 );

        //head
        g.fillOval( startX - 15, startY -10,  18, 25 );

        // eye
        g.setColor( new Color( 0, 0, 0));
        g.fillOval( startX - 7  , startY - 5, 5, 5);
        
        //stain
        g.setColor( new Color( 20, 20, 20));
        g.fillOval( startX - 25, startY - 2, 10, 8 );
        
        //flatten bottom
        g.clearRect( startX - 30, startY + 11, 35, 4 );
         
        g.setColor( new Color( 34, 139, 34 ) );  // brown
        g.fillRect( startX - 27, startY + 10,  12, 5 );
        g.fillRect( startX - 13, startY + 10, 12, 5 );
    }

    
   
   /**
    * Profilerates frogs on y-axis
    */
   public void morph(Graphics g) {
	   int i = 0;
	   while (i < 10) {
		   setY(getY() + 25);
		   try {
		      Thread.sleep(50);
		   }
		   catch(InterruptedException e){
		      System.out.println("main thread interrupted");
		   }
		   drawInstructions(g);
		   i++;
	   }
	  
   }
}

/**
 * @author Conner Sommerfield
 * CS 160 - 03: Intermediate Computer Programming
 * Professor Kraft
 * 6 May 2022
 */

import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Lion extends Racer {
	private int increment = 0;
	private int moveCounter = 10;
	private Random rand = new Random();
	private int[] speed = new int[100];
	
   /** Default Constructor: calls Racer default constructor
   */
   public Lion( )
   {
     super( );
   }

   /** Constructor
   *    @param rID  racer Id, passed to Racer constructor
   *    @param rX    x position, passed to Racer constructor
   *    @param rY    y position, passed to Racer constructor
   */
   public Lion( String rID, int rX, int rY )
   {
     super( rID, rX, rY );
   }

   /** move:  calculates the new x position for the racer
   *   Lion move characteristics: Starts slow (at 0) but accelerates
   *   Lion starts race in a stopped position
   *   The lion moves at a constant rate of every tenth cycle (using modulus operator)
   *   However, speed is randomly incremented by a value of 1 or 2
   *   Speed determines how many x spaces lion will move in that cycle
   *   If more 2's are chosen randomly, speed will pick up quicker
   */
   public void move( )
   {
	 
	 
	 speed[0] = 0;
	 for (int i = 1; i < speed.length; i++) {
		 speed[i] = speed[i-1] + rand.nextInt(2) + 1; // array with increasingly larger values for speed
	 }
	 if (moveCounter % 10 == 1) { // lion moves every 10 calls of move()
		 setX( getX( ) + speed[increment] );
		 increment ++;
	 }
	 moveCounter++;
     
   }

   /** draw: draws the Lion at current (x, y) coordinate
   *       @param g   Graphics context
   *       
   *   
   */
   public void draw( Graphics g )
   {
     int startX = getX( );
     int startY = getY( );
     
     g.setColor( new Color( 255, 255, 0 ) ); // yellow

     //body
     g.fillOval( startX - 30, startY, 25, 15 );

     //head
     g.fillOval( startX - 10, startY - 5,  15, 18 );

     //flatten bottom
      g.clearRect( startX - 30, startY + 11, 35, 4 );

     //feet
     g.setColor( new Color( 185, 156, 187 ) );  // brown
     g.fillRect( startX - 27, startY + 10,  12, 5 );
     g.fillRect( startX - 13, startY + 10, 12, 5 );
     
     //tail
     if (this.isWinner) {
    	 morph(g);
     }
     g.setColor( new Color(185, 156, 187 ) ); // brown
     g.fillRect(startX - 40, startY + 6, 10, 3);
   }

   
   /**
    * Wags lion tail by adding new verticle rectangle
    */
   public void morph(Graphics g) {
	   int startX = getX( );
	   int startY = getY( );
	   // tail wags up
	   g.fillRect(startX - 41, startY - 2, 3, 10);
   }
 
}

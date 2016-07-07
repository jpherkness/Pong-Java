/**
 * Created by Joseph Herkness on 7/1/16.
 */

import java.awt.Graphics;
import java.awt.Color;

public class Ball {

    public int x;                            // X position
    public int y;                            // Y position
    public int vx = 3;                       // X velocity
    public int vy = 1;                       // Y velocity
    public static final int DIAMETER = 15;   // Diameter of ball
    public CollisionBox box;                 // The balls collision box

    /**
     * Constructor for a ball object
     */
    public Ball(){
        reset();
    }

    public void reset(){
        setPosition(Game.game.WIDTH/2 , Game.game.HEIGHT/2);
    }

    /**
     * Sets the x and y position of the ball within the game
     * @param x the x position
     * @param y the y position
     */
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        box = new CollisionBox(x, y, DIAMETER, DIAMETER);
    }

    public void render(Graphics g){

        // Draw the ball at the current x and y position
        g.setColor(Color.white);
        g.fillOval((int)x, (int)y, DIAMETER, DIAMETER);
    }

    public void update(){


        // First we check to see if the ball collides with the walls
        if(x + vx < 0){
            Game.game.rightScore += 1;
            reset();
        }
        if(x + DIAMETER + vx > Game.game.getWidth()){
            Game.game.leftScore += 1;
            reset();
        }
        if(y + vy < 0 || y + DIAMETER + vy > Game.game.getHeight()) vy = -vy;

        // Check to see if the ball collides with the paddles
        if(box.intersects(Game.game.leftPaddle.box)) vx = -vx;
        if(box.intersects(Game.game.rightPaddle.box)) vx = -vx;

        // Then we update the balls position
        x += vx;
        y += vy;

        // Then we update the collision box
        box.setBounds(x, y, DIAMETER, DIAMETER);

    }

}

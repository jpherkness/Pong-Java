/**
 * Created by Joseph Herkness on 7/2/16.
 */
import java.awt.Graphics;

public class Paddle {

    public int x;
    public int y;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 50;
    public int speed = 2;
    public CollisionBox box;

    public Paddle(int x, int y){
        this.x = x;
        this.y = y;
        box = new CollisionBox(x, y, WIDTH, HEIGHT);
    }

    public void render(Graphics g){
        g.fillRoundRect(x, y, WIDTH, HEIGHT, WIDTH, WIDTH);
    }

    public void update(){}

    public void checkY(){
        if(y < 0) y = 0;
        if(y > Game.game.getHeight() - HEIGHT) y = Game.game.getHeight() - HEIGHT;
    }
}

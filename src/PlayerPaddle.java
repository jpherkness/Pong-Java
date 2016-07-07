/**
 * Created by Joseph Herkness on 7/2/16.
 */
public class PlayerPaddle extends Paddle{

    public PlayerPaddle(int x, int y){
        super(x, y);
    }

    @Override
    public void update(){

        // First we check to see if any keys are pressed and move the paddle
        if(Game.game.keys[0]) y -= speed;
        if(Game.game.keys[1]) y += speed;

        // Check to make sure the paddle is on the screen
        checkY();

        // Then we update the collision box
        box.setBounds(x, y, WIDTH, HEIGHT);
    }
}

/**
 * Created by Joseph Herkness on 7/2/16.
 */

public class ComputerPaddle extends Paddle {

    public ComputerPaddle(int x, int y){
        super(x, y);
    }

    @Override
    public void update(){
        Ball ball = Game.game.ball;

        // First we determine where to move the paddle
        int ballCenterY = ball.y + ball.DIAMETER/2;
        int paddleCenterY = y + HEIGHT/2;
        if(ball.x > Game.game.WIDTH / 2) {
            if (ballCenterY < paddleCenterY) y -= speed;
            if (ballCenterY > paddleCenterY) y += speed;
        }
        // Then we check to make sure the paddle is on the screen
        checkY();

        // Then we update the bounds
        box.setBounds(x, y, WIDTH, HEIGHT);

    }
}

/**
 * Created by Joseph on 7/1/16.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener{

    protected static final String NAME = "Pong"; // Name of the game window
    public static final int WIDTH = 400;         // Width of the game window
    public static final int HEIGHT = 300;        // Height of the game window
    public static Game game;                     // Static reference to the game object
    public boolean[] keys = new boolean[2];      // keys[0] = UP, keys[1] = DOWN
    public Timer timer;
    public boolean running;

    public Ball ball;
    public Paddle leftPaddle;
    public Paddle rightPaddle;
    public int leftScore;
    public int rightScore;

    public Game(){
        game = this;
        game.addKeyListener(this);
        game.setFont(new Font("Helvetica Neue", Font.BOLD, 20));

        // Initialize our instance variables
        ball = new Ball();
        leftPaddle = new PlayerPaddle(10, HEIGHT/2 - Paddle.HEIGHT/2);
        rightPaddle = new ComputerPaddle(WIDTH - 10 - Paddle.WIDTH, HEIGHT/2 - Paddle.HEIGHT/2);
    }

    /**
     * Creates a timer that calls the gameLoop function 60 times a second
     */
    public void start(){
        timer = new Timer(1000 / 60, e -> gameLoop());
        running  = true;
        timer.start();
    }

    /**
     * The game loop function is responsible for updating and rendering all game objects
     */
    public void gameLoop(){
        update();
        repaint();
        if(!running) timer.stop();
    }

    /**
     * Updates the state of all game objects
     */
    public void update(){
        ball.update();
        leftPaddle.update();
        rightPaddle.update();

        if(leftScore >= 5) running = false;
        if(rightScore >= 5) running = false;

    }

    /**
     * Draws all game objects
     * @param g the graphics object that everything should be drawn to
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // First we turn our graphics object into a graphics2d object
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the background
        g2d.setColor(new Color(97,198,154));
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        g2d.setColor(new Color(255,255,255,30));
        g2d.setStroke(new BasicStroke(6));
        g2d.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);

        // Draw the score
        g2d.setColor(Color.white);
        g2d.drawString(Integer.toString(leftScore), WIDTH/2 - 30, 30);
        g2d.drawString(Integer.toString(rightScore), WIDTH/2 + 30, 30);

        // Draw all objects
        ball.render(g2d);
        leftPaddle.render(g2d);
        rightPaddle.render(g2d);

        // Dispose of graphics objects
        g.dispose();
        g2d.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) keys[0] = true;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) keys[1] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) keys[0] = false;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) keys[1] = false;
    }

    public static void main(String[] args){
        // Create the game object
        Game game = new Game();

        // Setup the window
        game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        game.setFocusable(true);
        game.requestFocus();

        JFrame frame = new JFrame(NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Start the game
        game.start();
    }
}

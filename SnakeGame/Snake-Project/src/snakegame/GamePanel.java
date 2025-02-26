package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private long startTime;
    private Snake snake;
    private Food food;
    private ArrayList<Rock> rocks;
    private ArrayList<Point> rockPoints;
    private Timer timer;
    private boolean isGameOver;
    private HighScores highScores;
    private String playerName;
    private JButton startButton;
    private boolean isGameStarted = false;
    private final int TILE_SIZE = 40;
    private final int WIDTH = 1000;
    private final int HEIGHT = 800;
    private int DELAY =150;
    private Image snakeImage = new ImageIcon("/Users/hajiaga/Desktop/ELTE/Snake/src/snakegame/SnakeBody.png").getImage();
    private Image foodImage = new ImageIcon("/Users/hajiaga/Desktop/ELTE/Snake/src/snakegame/FoodApple.png").getImage();
    private Image rockImage = new ImageIcon("/Users/hajiaga/Desktop/ELTE/Snake/src/snakegame/rock.png").getImage();
    private Image snakeHeadImage = new ImageIcon("/Users/hajiaga/Desktop/ELTE/Snake/src/snakegame/SnakeHead.png").getImage();
    private JButton restartButton;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(0xe9e8e4));
        setFocusable(true);
        addKeyListener(this);
    
        restartButton = new JButton("Restart Game");
        restartButton.setBounds(WIDTH / 2 - 75, HEIGHT - 70, 150, 50);
        restartButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to restart the game?", 
                "Confirm Restart", 
                JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                resetGame();
            }
        });
        startButton = new JButton("Start Game");
        startButton.setBounds(WIDTH / 2 - 75, HEIGHT / 2 - 25, 150, 50);
        startButton.addActionListener(e -> {
            isGameStarted = true;
            startButton.setVisible(false);
            resetGame();
        });

        setLayout(null);
        add(startButton);
        try {
            highScores = new HighScores();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to database: " + e.getMessage());
            e.printStackTrace();
        }
        setLayout(null);
        add(restartButton);
    
        resetGame();
    }
    public void resetGame() {
        snake = new Snake(WIDTH / (2 * TILE_SIZE), HEIGHT / (2 * TILE_SIZE));
        rockPoints = new ArrayList<>();
        rocks = generateRocks(10);
        food = generateFood();
        isGameOver = false;
        playerName = null;
        restartButton.setVisible(false);
        startTime = System.currentTimeMillis();
        DELAY = 150;

        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(DELAY, this);
        timer.start();
    
        setFocusable(true);
        requestFocusInWindow();
    
        repaint();
        System.out.println("Game reset.");
    }
    private Food generateFood() {
        Random rand = new Random();
        Food food;
        do {
            int x = rand.nextInt(WIDTH / TILE_SIZE);
            int y = rand.nextInt(HEIGHT / TILE_SIZE);
            food = new Food(new Point(x, y));
        } while (snake.getBody().contains(food.getPoint()) || rockPoints.contains(food.getPoint()));
        return food;
    }

    private ArrayList<Rock> generateRocks(int count) {
        ArrayList<Rock> rocks = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            Rock rock;
            do {
                int x = rand.nextInt(WIDTH / TILE_SIZE);
                int y = rand.nextInt(HEIGHT / TILE_SIZE);
                rock = new Rock(new Point(x, y));
            } while (snake.getBody().contains(rock.getPoint()) || isBlocking(rock.getPoint()));
            rocks.add(rock);
            rockPoints.add(rock.getPoint());
        }

        return rocks;
    }

    private boolean isBlocking(Point point) {
        Point head = snake.getHead();
        Direction direction = snake.getCurrentDirection();
        for (int i = 1; i <= 5; i++) {
            Point checkPoint = switch (direction) {
                case NORTH -> new Point(head.x, head.y - i);
                case EAST -> new Point(head.x + i, head.y);
                case SOUTH -> new Point(head.x, head.y + i);
                case WEST -> new Point(head.x, - i);
            };
            if (point.equals(checkPoint)) {
                return true;
            }
        }
        return false;
    }

    private void displayLeaderboard() {
        try {
            ArrayList<String> scores = highScores.getTopScores(10);
            StringBuilder leaderboard = new StringBuilder("Leaderboard:\n");
    
            for (String score : scores) {
                leaderboard.append(score).append("\n");
            }
    
            JOptionPane.showMessageDialog(this, leaderboard.toString(), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
    
            SwingUtilities.invokeLater(() -> repaint());
    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to retrieve leaderboard: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        if (!isGameStarted) {
            g.setColor(new Color(194, 178, 128));
            g.fillRect(0, 0, WIDTH, HEIGHT);
    
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            String message = "Welcome to Snake Game!";
            int messageWidth = g.getFontMetrics().stringWidth(message);
            g.drawString(message, (WIDTH - messageWidth) / 2, HEIGHT / 2 - 50);
    
            return;
        }
        if (isGameOver) {
            timer.stop();
        
            if (playerName == null) {
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Game Over! Do you want to save your score?", 
                "Save Score", 
                JOptionPane.YES_NO_OPTION
            );
        
            if (response == JOptionPane.YES_OPTION) {
                playerName = JOptionPane.showInputDialog(this, "Enter your name:");
                if (playerName != null && !playerName.trim().isEmpty()) {
                try {
                    highScores.saveScore(playerName, (snake.getBody().size() - 2));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Failed to save score: " + ex.getMessage());
                    ex.printStackTrace();
                }
                } else {
                playerName = "";
                }
            } else {
                playerName = "";
            }
        
            displayLeaderboard();
            restartButton.setVisible(true);
            }
            return;
        }
    
        setBackground(new Color(194, 178, 128));
    
        Graphics2D g2d = (Graphics2D) g.create();
    
    
        Point head = snake.getBody().get(0);
        Direction direction = snake.getCurrentDirection();
        double angle = switch (direction) {
            case NORTH -> 0;
            case EAST -> Math.PI / 2;
            case SOUTH -> Math.PI;
            case WEST -> -Math.PI / 2;
        };
        g2d.rotate(angle, head.x * TILE_SIZE + TILE_SIZE / 2, head.y * TILE_SIZE + TILE_SIZE / 2);
        g2d.drawImage(snakeHeadImage, head.x * TILE_SIZE, head.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
        g2d.rotate(-angle, head.x * TILE_SIZE + TILE_SIZE / 2, head.y * TILE_SIZE + TILE_SIZE / 2);
    
        for (int i = 1; i < snake.getBody().size(); i++) {
            Point segment = snake.getBody().get(i);
            g2d.drawImage(snakeImage, segment.x * TILE_SIZE, segment.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
        }
    
        g2d.drawImage(foodImage, food.getPoint().x * TILE_SIZE, food.getPoint().y * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
    
        for (Rock rock : rocks) {
            Point rockPoint = rock.getPoint();
            g2d.drawImage(rockImage, rockPoint.x * TILE_SIZE, rockPoint.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
        }
    
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.drawString("Time: " + elapsedTime + "s", 10, 20);
    
        g2d.dispose();
    }

    private boolean isMapFull() {
        int mapSize = (WIDTH / TILE_SIZE) * (HEIGHT / TILE_SIZE);
        return snake.getBody().size() >= mapSize - rocks.size() - 1;
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameStarted || isGameOver) {
            return;
        }
    
        snake.move();
        if (checkCollisions() || isMapFull()) {
            isGameOver = true;
            repaint();
            return;
        }
    
        if (snake.getHead().equals(food.getPoint())) {
            snake.grow();
            food = generateFood();
            
            DELAY = Math.max(70, DELAY - 5);
            
            timer.setDelay(DELAY);
        }
    
        repaint();
    }
    private boolean checkCollisions() {
        Point head = snake.getHead();

        if (head.x < 0 || head.x >= WIDTH / TILE_SIZE || head.y < 0 || head.y >= HEIGHT / TILE_SIZE) {
            return true;
        }

        if (snake.checkSelfCollision()) {
            return true;
        }

        return rockPoints.contains(head);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> snake.setDirection(Direction.NORTH);
            case KeyEvent.VK_A -> snake.setDirection(Direction.WEST);
            case KeyEvent.VK_S -> snake.setDirection(Direction.SOUTH);
            case KeyEvent.VK_D -> snake.setDirection(Direction.EAST);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    protected void finalize() throws Throwable {
        if (highScores != null) {
            highScores.close();
        }
    }
}

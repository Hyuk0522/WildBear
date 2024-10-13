package Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JumpGame extends JFrame implements ActionListener, KeyListener {

    private int jumpSpeed = 10;
    private int health = 100;

    private boolean isJumping = false;

    private Timer timer;

    private int tacleX = 800;
    private int tacleY = 300;
    private int tacleWidth = 50;
    private int tacleHeight = 50;
    private int tacleSpeed = 15;

    private int itemSpeed = 10;
    private int itemScore = 0;

    private int score = 0;
    private JLabel scoreLabel;
    private JLabel healthLabel;
    private JLabel itemLabel;

    private int totalScore;

    private ImageIcon backgroundImage = new ImageIcon("src\\img\\아르카나.jpeg");

    private ImageIcon effectIcon = new ImageIcon("src\\img\\이팩트.png");

    private GamePanel gamePanel;

    private String u_id;
    private int highestScore;

 

    public JumpGame(String userId, int userHighestScore) {
        this.u_id = userId;
        this.highestScore = userHighestScore;

        setTitle("Jump Game");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel();
        add(gamePanel);

        timer = new Timer(20, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);

        scoreLabel = new JLabel("점수: " + score);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        gamePanel.add(scoreLabel);

        healthLabel = new JLabel("Life: " + health);
        healthLabel.setForeground(Color.RED);
        healthLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        gamePanel.add(healthLabel);

        itemLabel = new JLabel("Honey : " + itemScore);
        itemLabel.setForeground(Color.WHITE);
        itemLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        gamePanel.add(itemLabel);
    }

    Player p1 = new Player();
    int p_x = p1.getPlayerX();
    int p_y = p1.getPlayerY();
    int p_w = p1.getP_iconWidth();
    int p_h = p1.getP_iconHeight();

    Item i1 = new Item();
    int i_x = i1.getItemX();
    int i_y = i1.getItemY();
    int i_w = i1.getIt_iconWidth();
    int i_h = i1.getIt_iconHeight();

    public void actionPerformed(ActionEvent e) {
        p_y += jumpSpeed;

        if (isJumping && p_y >= 300) {
            isJumping = false;
            jumpSpeed = 0;
            p_y = 300;
        }

        tacleX -= tacleSpeed;

        if (tacleX < 0) {
            tacleX = 800;
        }

        i_x -= itemSpeed;

        if (i_x < 0) {
            i_x = 800;
        }

        if (i_x < p_x + p_w &&
                i_x + i_w > p_x &&
                i_y < p_y + p_h &&
                i_y + i_h > p_y) {
            i_x = 100;
            itemScore += 10;
            itemLabel.setText("honey: " + itemScore);
        }

        if (tacleX < p_x + p_w &&
                tacleX + tacleWidth > p_x &&
                tacleY < p_y + p_h &&
                tacleY + tacleHeight > p_y) {
            health -= 10;
            healthLabel.setText("LIFE: " + health);
        }

        if (p_y < 300 && !isJumping) {
            jumpSpeed += 5;
        }

        if (p_y > 300) {
            p_y = 300;
        }

        if (p_y < 0) {
            p_y = 0;
            isJumping = false;
            jumpSpeed = 5;
        }

        score += 1;

        scoreLabel.setText("Score: " + score);

        gamePanel.repaint();

        if (health <= 0) {
            timer.stop();
            totalScore = score + itemScore;
            saveScore(u_id, totalScore);

            JOptionPane.showMessageDialog(this, "Game Over! 당신의 최종점수: " + totalScore);

            System.exit(0);
        }
    }

    private void saveScore(String userId, int score) {
    	 String jdbcUrl = "jdbc:mysql://localhost:3306/Gamedb?useUnicode=true&characterEncoding=utf-8";       
         String dbUser = "root";
         String dbPassword = "1234";
         
         String query = "INSERT INTO Score (u_id, score) VALUES (?, ?)";
    	
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);
            preparedStatement.setInt(2, score);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private class GamePanel extends JPanel {
        ImageIcon p_icon = p1.getPlayerIcon();
        ImageIcon i_icon = i1.getItemIcon();

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            backgroundImage.paintIcon(this, g, 0, 0);

            p_icon.paintIcon(this, g, p_x, p_y);

            g.setColor(Color.RED);
            g.fillRect(tacleX, tacleY, tacleWidth, tacleHeight);

            i_icon.paintIcon(this, g, i_x, i_y);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && p_y == 300) {
            isJumping = true;
            jumpSpeed = -10;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JumpGame game = new JumpGame("admin",0);
            game.setVisible(true);
        });
    }
}

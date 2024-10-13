package Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ReadyGame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String u_id;

  
    
    private String u_name;  // 사용자 이름
    private int highestScore;  // 최고 점수

  
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReadyGame frame = new ReadyGame("admin");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ReadyGame(String userId) {
    	this.u_id = userId;
    	
        setTitle("게임 대기창");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 800, 400);
        contentPane = new JPanel();
        contentPane.setSize(800, 400);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        
        // 배경 이미지 설정
        ImageIcon backgroundImage = new ImageIcon("src\\img\\아르카나.jpeg");

        // 배경 이미지를 담을 JPanel 생성
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        panel.setSize(800,400);
		panel.setBounds(0, 0, 800, 400); // 프레임 크기에 맞게 수정
		contentPane.add(panel);
		panel.setLayout(null);

        // 사용자 정보 가져오기
		getHighestScore();

        // 레이블 및 버튼 등 GUI 구성 (이하 생략)
        JLabel namelabel = new JLabel(u_name + "님 환영합니다!");
        namelabel.setBounds(37, 21, 359, 53);
        namelabel.setForeground(Color.WHITE);
        namelabel.setFont(new Font("고딕체", Font.BOLD, 30));
        panel.add(namelabel);

        JLabel scorelabel = new JLabel("최고점수: " + highestScore + "점");
        scorelabel.setBounds(166, 128, 420, 94);
        scorelabel.setForeground(Color.WHITE);
        scorelabel.setFont(new Font("고딕체", Font.BOLD, 30));
        panel.add(scorelabel);

        JButton start = new JButton("Start");
        start.setBounds(306, 265, 149, 72);
        panel.add(start);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JumpGame(u_id, highestScore);
                JumpGame game = new JumpGame(u_id, highestScore);
                game.setVisible(true);
                dispose();
            }
        });
    }

    private void getHighestScore() {
    	  // 데이터베이스 연동
        String jdbcUrl = "jdbc:mysql://localhost:3306/Gamedb?useUnicode=true&characterEncoding=utf-8";
        String dbUser = "root";
        String dbPassword = "1234";


        String query = "SELECT u_name, (SELECT MAX(score) FROM Score WHERE u_id = ?) AS highestScore FROM User WHERE u_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, u_id);
            preparedStatement.setString(2, u_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    u_name = resultSet.getString("u_name");
                    highestScore = resultSet.getInt("highestScore");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

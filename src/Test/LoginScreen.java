package Test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginScreen extends JFrame {

    public LoginScreen() {
        setTitle("로그인 화면");
        setSize(800, 400);

        // 배경 이미지 가져오기
        ImageIcon backgroundImage = new ImageIcon("src\\img\\로그인배경.png");

       
        JPanel backgroundPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // 로그인 정보 입력 패널
        JPanel logpanel = new JPanel();
        logpanel.setLayout(new GridLayout(2, 3));

        // 아이디 입력 부분
        JLabel id = new JLabel("아이디 : ");
        logpanel.add(id);

        JTextField tf_id = new JTextField(20);
        logpanel.add(tf_id);

        // 로그인 버튼 부분
        JButton login = new JButton("로그인");
        logpanel.add(login);

        // 비밀번호 입력 부분
        JLabel pwd = new JLabel("비밀번호 : ");
        logpanel.add(pwd);

        JPasswordField tf_pwd = new JPasswordField(20);
        logpanel.add(tf_pwd);

        // 회원가입 버튼 부분
        JButton join = new JButton("회원가입");
        logpanel.add(join);

        // 배경 이미지를 포함한 JPanel을 프레임에 추가
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(logpanel, BorderLayout.SOUTH);
        getContentPane().add(backgroundPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // 로그인 버튼 이벤트 
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String u_id = tf_id.getText();
                String u_pwd = new String(tf_pwd.getPassword());

                if (isLogin(u_id, u_pwd)) {
                    JOptionPane.showMessageDialog(null, "로그인 성공!");
                   
                    
                    // 로그인 성공하면 대기실로 감
                    
                    new ReadyGame(u_id);
    				ReadyGame ready = new ReadyGame(u_id);
                    ready.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "로그인 실패. 아이디나 비밀번호를 확인하세요.");
                }
            }
        });

        join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JoinScreen();
                dispose();
            }
        });
    }

    private boolean isLogin(String u_id, String u_pwd) {
        // 데이터베이스 연동
        String jdbcUrl = "jdbc:mysql://localhost:3306/Gamedb?useUnicode=true&characterEncoding=utf-8";       
        String dbUser = "root";
        String dbPassword = "1234";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM User WHERE u_id = ? AND u_pwd = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, u_id);
                preparedStatement.setString(2, u_pwd);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // 결과가 존재하면 로그인 성공
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // 예외 발생 시 로그인 실패
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginScreen());
    }
}

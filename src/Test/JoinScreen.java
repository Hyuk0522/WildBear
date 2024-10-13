package Test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

public class JoinScreen extends JFrame {
	// 회원가입 화면

    public JoinScreen() {
        setTitle("회원가입 화면");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JLabel title = new JLabel("회원가입", JLabel.CENTER);
        title.setFont(new Font("고딕체", Font.BOLD, 30));

        JButton join = new JButton("회원가입");
        JButton cancel = new JButton("취소");

        JTextField id = new JTextField(20);
        JPasswordField pwd = new JPasswordField(20);
        JTextField name = new JTextField(20);

     // 아이디 입력 부분
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        idPanel.add(new JLabel("아이디 : "));
        idPanel.add(id);

     // 비번 입력 부분
        JPanel pwdPanel = new JPanel();
        pwdPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        pwdPanel.add(new JLabel("비밀번호 : "));
        pwdPanel.add(pwd);
        
     // 이름 입력 부분
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        namePanel.add(new JLabel("이름 : "));
        namePanel.add(name);

     // 배경 이미지 가져오기        
        ImageIcon backgroundImage = new ImageIcon("src\\img\\아르카나.jpeg"); 
        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        contentPanel.setSize(800, 400);
        contentPanel.setLayout(new GridLayout(3, 1));
        contentPanel.add(idPanel);
        contentPanel.add(pwdPanel);
        contentPanel.add(namePanel);

      // 버튼 배치할 패널
        JPanel panel = new JPanel();
        panel.add(join);
        panel.add(cancel);

        add(title, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // 회원가입 버튼 이벤트 처리
        join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 // 데이터베이스 연동
                String u_id = id.getText();
                String u_pwd = new String(pwd.getPassword());
                String u_name = name.getText();

                
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gamedb?useUnicode=true&characterEncoding=utf-8", "root", "1234")) {
                    String query = "INSERT INTO User (u_id, u_pwd, u_name) VALUES (?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, u_id);
                        preparedStatement.setString(2, u_pwd);
                        preparedStatement.setString(3, u_name);
                        preparedStatement.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
                    new LoginScreen();
                    dispose();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "회원가입 중 오류가 발생했습니다.");
                }
            }
        });

        // 취소 버튼 이벤트 처리
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JoinScreen());
    }
}

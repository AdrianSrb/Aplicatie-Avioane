import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame{
    private JPanel panel;
    private JButton loginBtn;
    private JButton registerBtn;

    private static JFrame frame;

    Start() {

        //loginBtn = new JButton("Login");
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                frame.dispose();
                login.setTitle("Login");
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                login.setContentPane(new Login().panel);
                login.pack();
                login.setSize(500, 500);
                login.setVisible(true);
                dispose();
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register = new Register();
                frame.dispose();
                register.setTitle("Register");
                register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                register.setContentPane(new Register().panel);
                register.pack();
                register.setSize(500, 500);
                register.setVisible(true);
                dispose();
            }
        });
        //registerBtn = new JButton("Register");
//        registerBtn.addActionListener(e -> {
//            new Register();
//
//        });

        //add(loginBtn);
        //add(registerBtn);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {

                    frame = new JFrame("Start");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(new Start().panel);
                    frame.pack();

                    frame.setSize(350, 350);
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

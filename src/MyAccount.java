import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyAccount extends JFrame {

    public JPanel panel;
    private JTextField changeUsernameField;
    private JButton changeUsernameBtn;
    private JTextField changeEmailAddressField;
    private JButton changeEmailAddressBtn;
    private JButton changePassworButton;
    private JTextField emailField;
    private JPasswordField passwordField;

    private static JFrame frame;

     MyAccount(){

            changeUsernameBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{

                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zboruri","root","");
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("select * from conturi");

                        String email = rs.getString("email");
                        String changeUser = changeUsernameField.getText();


                            while(rs.next());
                        {
                            if(email.equals(email)){
                                Statement st1 = conn.createStatement();
                                st1.executeUpdate("update conturi set Username = 'changeUsernameField' where email = 'email'  ");
                                JOptionPane.showMessageDialog(null, "USERNAME UPDATE SUCCESSFUL");
                            }else {
                                JOptionPane.showMessageDialog(null, "UPDATE FAILED");
                            }
                        }

                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                }
            });
        changeEmailAddressBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try{

                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zboruri","root","");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from conturi");

                    while(rs.next());
                    {
                        String changeEmail = changeEmailAddressField.getText();
                        String pass = rs.getString("Pass");

                        if(passwordField.equals(pass)){
                            Statement st2 = conn.createStatement();
                            st2.executeUpdate("update conturi set email = 'changeEmailAddressField' where Pass = 'pass' ");
                            JOptionPane.showMessageDialog(null, "EMAIL UPDATE SUCCESSFUL");
                        }else {
                            JOptionPane.showMessageDialog(null, "UPDATE FAILED");
                        }
                    }

                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });

        changePassworButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ChangePassword changePassword = new ChangePassword();
                frame.dispose();
                changePassword.setTitle("Schimba Parola");
                changePassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                changePassword.setContentPane(new ChangePassword().panel);
                changePassword.pack();
                changePassword.setSize(500, 500);
                changePassword.setVisible(true);
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    frame = new JFrame("MyAccount");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(new MyAccount().panel);
                    frame.pack();

                    frame.setSize(550,550);
                    frame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ChangePassword extends JFrame {
    public JPanel panel;
    private JTextField passwordField;
    private JButton changeBtn;
    private JTextField usernameField;
    private JTextField confirmPasswordField;
    private JTextField oldPasswordFiled;


    private static JFrame frame;

    ChangePassword(String Username) {
        changeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String uname = usernameField.getText();
                String oldPassword = oldPasswordFiled.getText();
                String newPass = passwordField.getText();
                String confPass = confirmPasswordField.getText();

                try {

                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zboruri","root","");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from conturi");

                    while(rs.next()){
                        String username = rs.getString("Username");
                        String password = rs.getString("Pass");

                            if(uname.equals(username)&& oldPassword.equals(password)){
                                if(newPass.equals(confPass)){
                                Statement st1 = conn.createStatement();
                                st1.executeUpdate("UPDATE conturi SET Pass='newPass' where Username ='uname'");
                                JOptionPane.showMessageDialog(null, "PASSWORD UPDATE SUCCESSFUL");

                                  }else{
                                    JOptionPane.showMessageDialog(null, "UPDATE FAILED");
                                }

                             }

                        }


                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public ChangePassword() {

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    frame = new JFrame("Schimba parola");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(new ChangePassword(toString()).panel); // toString de modificat
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

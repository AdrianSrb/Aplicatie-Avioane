import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Login extends JFrame {

    public JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;


    private static JFrame frame;

    Login(){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    String email = usernameField.getText();
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zboruri","root","");
                    Statement stmt = conn.createStatement();



                    ResultSet rs=stmt.executeQuery("select * from conturi where Username='"+ username +"'OR email='"+ email +"'And Pass='" + password+"'");
                    int count=0;
                    while(rs.next()){
                        count++;
                    }
                    if(username.equals("")&&password.equals("")) {
                        JOptionPane.showMessageDialog(null,"Enter User and Pass","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else if(count>0){
                        System.out.println("Login Successful");
                        Menu opnMenu = new Menu();
                        frame.dispose();
                        opnMenu.setTitle("Main Menu");
                        opnMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        opnMenu.setContentPane(new Menu().panel);
                        opnMenu.pack();
                        opnMenu.setSize(500, 500);
                        opnMenu.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"mesaj2","Error",JOptionPane.ERROR_MESSAGE);
                        System.out.println("Invalid Account");
                    }


                }catch (Exception e1){
                    e1.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    frame = new JFrame("Login");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(new Login().panel);
                    //setContentPanel(new "Pagina pe care vreau sa ma duca mai departe".panel);
                    frame.pack();

                    frame.setSize(450,180);
                    frame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}

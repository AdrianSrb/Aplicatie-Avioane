import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.Statement;

public class Register extends JFrame {

    public JPanel panel;
    private JTextField usernameBtn;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField emailBtn;
    private JButton registerBtn;

    private static JFrame frame;

    public Register() {
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String username = usernameBtn.getText();
                    String password = new String(passwordField1.getPassword());
                    String password2 = new String(passwordField2.getPassword());
                    String getemail = emailBtn.getText();

                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zboruri", "root", "");
                    Statement stmt = conn.createStatement();

                    if (username.equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter user", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (password.equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter pass", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!password.equals(password2)) {
                        JOptionPane.showMessageDialog(null, "Enter pass2 like pass", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (getemail.equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter email ", "Error", JOptionPane.ERROR_MESSAGE);
                     }
                    if(!getemail.contains("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
                        JOptionPane.showMessageDialog(null, "Put Special CEVA", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        System.out.println("Cont creat");
                        stmt.executeUpdate("insert into conturi(Id,Username,Pass,email)values (default,'" + username + "','" + password + "','" + getemail + "')");
                        JOptionPane.showMessageDialog(null, "Cont creat: " + getemail, "Succes", JOptionPane.ERROR_MESSAGE);
                    }


                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {

                    frame = new JFrame("Creaza cont");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(new Register().panel);
                    frame.pack();

                    frame.setSize(350, 350);
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


//    private void configureRegisterButton() {
//        registerBtn.addActionListener(e -> {
//
//            try{
//            String username = usernameBtn.getText();
//            String password = new String(passwordField1.getPassword());
//            String password2 = new String(passwordField2.getPassword());
//            String getemail = emailBtn.getText();
//
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zboruri","root","");
//                Statement stmt = conn.createStatement();
//
//            if(username.equals("")){
//                    JOptionPane.showMessageDialog(null,"Enter user","Error",JOptionPane.ERROR_MESSAGE);
//                }
//                if(password.equals("")) {
//                    JOptionPane.showMessageDialog(null,"Enter pass","Error",JOptionPane.ERROR_MESSAGE);
//                }
//                if(!password.equals(password2)){
//                    JOptionPane.showMessageDialog(null,"Enter pass2 like pass","Error",JOptionPane.ERROR_MESSAGE);
//                }
//                if(getemail.equals("")){
//                    JOptionPane.showMessageDialog(null,"Enter email ","Error",JOptionPane.ERROR_MESSAGE);
//                }
//                new Login();
//                dispose();
//            }
//            catch (Exception e1){
//                e1.printStackTrace();
//            }
//        });
//  }
}

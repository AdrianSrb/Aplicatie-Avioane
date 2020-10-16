import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLOutput;
import java.sql.Statement;

public class AdaugaZbor extends JFrame {

    public JPanel panel;
    private JTextField sursaField;
    private JTextField destinatieField;
    private JTextField oraPlecareField;
    private JTextField durataField;
    private JTextField zileField;
    private JTextField pretField;
    private JButton adaugaBtn;
    private JButton anuleazaBtn;

    private static JFrame frame;

    public AdaugaZbor(){

        adaugaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    String getSursa = sursaField.getText();
                    String getDestinatia = destinatieField.getText();
                    String getOraPlecare = oraPlecareField.getText();
                    String getDurata = durataField.getText();
                    String getZile = zileField.getText();
                    String getPret = pretField.getText();

                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zboruri","root","");
                    Statement stmt = conn.createStatement();

                    if(getSursa.equals("")){
                        JOptionPane.showMessageDialog(null, "Enter Sursa", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if(getDestinatia.equals("")){
                        JOptionPane.showMessageDialog(null,"Enter destiantie","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    if(getOraPlecare.equals("")){
                        JOptionPane.showMessageDialog(null,"Enter Ora plecare","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    if(getDurata.equals("")){
                        JOptionPane.showMessageDialog(null,"Enter Durata","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    if(getZile.equals("")){
                        JOptionPane.showMessageDialog(null,"Enter Zile","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    if(getPret.equals("")){
                        JOptionPane.showMessageDialog(null,"Enter Pret","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        System.out.println("Zbor creat");
                        stmt.executeUpdate("insert into avioane(Id,Sursa,Destinatia,OraPlecare,Durata,Zile,Pret)values (default,'" + getSursa + "','" + getDestinatia + "','" + getOraPlecare + "','" + getDurata + "','" + getZile + "','" + getPret + "')");
                        JOptionPane.showMessageDialog(null,"Zbor creat: " + getSursa, "Succes", JOptionPane.INFORMATION_MESSAGE);
                    }


                } catch (Exception e1) {
                e1.printStackTrace();
                }
            }
        });

        anuleazaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                frame.dispose();
                menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                menu.setContentPane(new Menu().panel);
                menu.pack();
                menu.setSize(350,350);
                menu.setVisible(true);
            }
        });

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    frame = new JFrame("Creaza Ruta");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(new AdaugaZbor().panel);
                    frame.pack();

                    frame.setSize(350,450);
                    frame.setVisible(true);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}

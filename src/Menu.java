import com.mysql.cj.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Menu extends JFrame {
    public JPanel panel;
    public  JTable tableAvioane;
    private JButton adaugaZborBtn;
    private JTextField dataField;
    private JButton myAccountBtn;
    private JButton backBtn;


    private static JFrame frame;

    DateFormat dataFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm");
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    DefaultTableModel model;
    String[] collums = {"Id","Sursa","Destinatia","OraPlecare","Durata","Zile","Pret"};
    DefaultTableModel model1 = new DefaultTableModel(collums,0);

    public Menu(){

        backBtn.addActionListener(new ActionListener() {
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

        myAccountBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyAccount myAccount = new MyAccount();
                frame.dispose();
                myAccount.setTitle("MyAccount");
                myAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                myAccount.setContentPane(new MyAccount().panel);
                myAccount.pack();
                myAccount.setSize(500, 500);
                myAccount.setVisible(true);
                dispose();
            }
        });

        adaugaZborBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdaugaZbor adaugaZbor = new AdaugaZbor();
                frame.dispose();
                adaugaZbor.setTitle("AdaugaZbor");
                adaugaZbor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                adaugaZbor.setContentPane(new AdaugaZbor().panel);
                adaugaZbor.pack();
                adaugaZbor.setSize(500, 500);
                adaugaZbor.setVisible(true);
                dispose();
            }
        });


            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zboruri", "root", "");
                Statement stmt = conn.createStatement();

                ResultSet rs=stmt.executeQuery("select * from avioane");

                //System.out.println(rs);

//            while(rs.next() ){
//                System.out.println(rs.getString("Id"));
//                System.out.println(rs.getString("Sursa"));
//                System.out.println(rs.getString("Destinatia"));
//                System.out.println(rs.getString("OraPlecare"));
//                System.out.println(rs.getString("Durata"));
//                System.out.println(rs.getString("Pret"));
//
//            }




                while(tableAvioane.getRowCount() > 0)
                {
                    ((DefaultTableModel) tableAvioane.getModel()).removeRow(0);
                }
                int columns = rs.getMetaData().getColumnCount();

                ((DefaultTableModel) tableAvioane.getModel()).addColumn("Sursa");
                //tableAvioane.addColumn("");
                while(rs.next())
                {

                    Object[] row = new Object[columns];
                    for (int i = 1; i <= columns; i++)
                    {
                        row[i - 1] = rs.getObject(i);
                        //i=5 sau la ultima valoare pun buton cu trimitere la id
                        //delete id from tabel
                    }
                    model1.insertRow(rs.getRow()-1,row);
                    //model1.addRow(row);
                    tableAvioane.setModel(model1);
                }
                rs.close();
                stmt.close();
                conn.close();

            } catch (Exception e){
                e.printStackTrace();
            }


        model = (DefaultTableModel) tableAvioane.getModel();
        dataField.setText(" " + dataFormat.format(date));


        }
            public void testResultSet(ResultSet res){
                try{
                    while(res.next()){
//                        System.out.println("ID: "+ res.getInt("Id"));
//                        System.out.println("Sursa: "+ res.getString("Sursa"));
//                        System.out.println("Destinatie: "+ res.getString("Destinatie"));
//                        System.out.println("Ora Plecare: "+ res.getInt("OraPlecare"));
//                        System.out.println("Durata: "+ res.getInt("Durata"));
//                        System.out.println("Zile: "+ res.getString("Zile"));
//                        System.out.println("Pret: "+ res.getInt("Pret"));
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {

                    frame = new JFrame("Menu");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(new Menu().panel);
                    frame.pack();

                    frame.setSize(550, 550);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}


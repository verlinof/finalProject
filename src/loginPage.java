import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginPage extends connectTable{
    private JTextField TfUsername;
    private JButton loginButton;
    private JFrame frame;
    private JButton registerButton;
    private JPanel loginPagee;
    private JPasswordField PfPassword;
    private JLabel judulHalaman;
    private ResultSet rs;
    private PreparedStatement pst;
    private Statement stmt;

    //Constructor
    public loginPage(JFrame parent) {
        connect();
        frame = new JFrame("Halaman Login");
        frame.setContentPane(loginPagee);
        frame.setSize(750, 500);
        frame.setLocationRelativeTo(parent);
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String username = TfUsername.getText();
                    String password = new String(PfPassword.getPassword());

                    //execute sql
                    pst = getConn().prepareStatement("select * from user where username = ? and password = ?");
                    pst.setString(1,username);
                    pst.setString(2,password);
                    rs = pst.executeQuery();

                    if(rs.next()){
                        frame.dispose();
                        new mainPage(null);
                    }else{
                        JOptionPane.showMessageDialog(null,"Login Error");
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new registerPage(null);
            }
        });


    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class registerPage extends connectTable{
    private JPanel registerPagee;
    private JLabel judulHalaman;
    private JTextField TfUsername;
    private JButton cancelButton;
    private JButton registerButton;
    private JPasswordField PfPassword;
    private JPasswordField PfRePassword;
    private PreparedStatement pst;
    private ResultSet rs;
    private Statement stmt;

    public registerPage(JFrame parent){
        connect();
        JFrame frame = new JFrame("Halaman Login");
        frame.setContentPane(registerPagee);
        frame.setSize(750, 500);
        frame.setLocationRelativeTo(parent);
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.setVisible(true);


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = TfUsername.getText();
                String password = new String(PfPassword.getPassword());
                String rePassword = new String(PfRePassword.getPassword());
                try{
                    if(checkInput(TfUsername,PfPassword)){
                        if(checkPassword(PfPassword,PfRePassword)){
                            pst = getConn().prepareStatement("INSERT INTO user (username, password)" + "VALUES(?, ?)");
                            pst.setString(1,username);
                            pst.setString(2,password);
                            try {
                                pst.executeUpdate();
                                JOptionPane.showMessageDialog(frame, "Registrasi Berhasil!");
                            }catch (Exception ex){
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(frame, "Registrasi gagal!");
                            }
                        }else{
                            JOptionPane.showMessageDialog(frame, "Password harus sama!");
                        }
                    }else{
                        JOptionPane.showMessageDialog(frame, "Input tidak boleh kosong!");
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new loginPage(null);
            }
        });
    }

    public boolean checkPassword(JPasswordField pfPassword, JPasswordField pfRePassword){
        String password = new String(pfPassword.getPassword());
        String rePassword = new String(pfRePassword.getPassword());
        if(password.equals(rePassword)){
            return true;
        }else{
            return false;
        }
    }
    public boolean checkInput(JTextField tfUsername, JPasswordField pfPassword){
        String username = tfUsername.getText();
        String password = new String(pfPassword.getPassword());
        if(username != null || password != null){
            return true;
        }else{
            return false;
        }
    }
}

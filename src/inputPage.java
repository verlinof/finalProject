import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class inputPage extends connectTable {
    private JTextField tfNIK;
    private JTextField tfNama;
    private JComboBox tfJK;
    private JComboBox tfKasus;
    private JButton submitButton;
    private JButton kembaliButton;
    private JPanel InputGUII;
    private JTextField tfdeskripsi;


    public inputPage(JFrame parent) {
        connect();
        JFrame frame = new JFrame();
        frame.setTitle("Police Information System");
        frame.setContentPane(InputGUII);
        frame.setMinimumSize(new Dimension(750,500));
        frame.setResizable(false);
        frame.setLocationRelativeTo(parent);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit();

            }
        });
        kembaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new mainPage(null);

            }
        });

        frame.setVisible(true);

    }

    private void submit() {
        String nik = tfNIK.getText();
        String nama = tfNama.getText();
        String kelamin = tfJK.getSelectedItem().toString();
        String kasus = tfKasus.getSelectedItem().toString();
        String deskripsi = tfdeskripsi.getText();

        if (nik.isEmpty() || nama.isEmpty() || kelamin.isEmpty() || kasus.isEmpty() || deskripsi.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Masukkan Semua Input!",
                    "Coba Lagi!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

       user =  addUserToDatabase(nik,nama,kelamin,kasus,deskripsi);
        if (user != null){
            JOptionPane.showMessageDialog(null,
                    "Data berhasil ditambahkan!",
                    "Succes!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null,
                    "Gagal Submit!",
                    "Coba lagi!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    public User user;
    private User addUserToDatabase(String nik, String nama, String kelamin, String kasus, String deskripsi) {
        User user = null;
        try {
            // Connected to database successfully...

            String sql = "INSERT INTO kasus (nik , nama , jenis_kelamin , jenis_kasus , deskripsi_kasus)" +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = getConn().prepareStatement(sql);
            preparedStatement.setString(1, nik);
            preparedStatement.setString(2, nama);
            preparedStatement.setString(3, kelamin);
            preparedStatement.setString(4, kasus);
            preparedStatement.setString(5, deskripsi);

            //insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.nik = nik;
                user.nama = nama;
                user.kelamin = kelamin;
                user.kasus = kasus;
                user.deskripsi = deskripsi;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

            return user;

    }

}



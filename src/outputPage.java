import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class outputPage extends connectTable{
    private JTextField TfNIK;
    private JButton BtnSearch;
    private JTable dataTable;
    private JComboBox CbKelamin;
    private JButton BtnUpdate;
    private JButton BtnDelete;
    private JTextField CariId;
    private JPanel outputPanel;
    private JTextField TfNama;
    private JComboBox CbKasus;
    private JTextField TfDeskripsi;
    private JScrollPane JsdataTable;
    private JButton BtnInfo;
    private JButton BtnKembali;
    private ResultSet rs;
    private PreparedStatement pst;
    private Statement stmt;

    public outputPage(JFrame parent){
        //fungsi dasar
        connect();
        table_load(dataTable);
        JFrame frame = new JFrame("Halaman Update");
        frame.setContentPane(outputPanel);
        frame.setSize(750,500);
        frame.setLocationRelativeTo(parent);
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.setVisible(true);

        //action listener button
        BtnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String id = CariId.getText();

                    pst = getConn().prepareStatement("select * from kasus where id_kasus = ?");
                    pst.setString(1,id);
                    rs = pst.executeQuery();

                    if(rs.next() == true){
                        String nik = rs.getString(2);
                        String nama = rs.getString(3);
                        String jenisKelamin = rs.getString(4);
                        String jenisKasus = rs.getString(5);
                        String deskripsiKasus = rs.getString(6);

                        TfNIK.setText(nik);
                        TfNama.setText(nama);
                        CbKelamin.setSelectedItem(jenisKelamin);
                        CbKasus.setSelectedItem(jenisKasus);
                        TfDeskripsi.setText(deskripsiKasus);
                    }
                    else{
                        JOptionPane.showMessageDialog(outputPanel,
                                "Data tidak ditemukan!",
                                "Not Found",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        BtnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nik = TfNIK.getText();
                String nama = TfNama.getText();
                String jenisKelamin = CbKelamin.getSelectedItem().toString();
                String jenisKasus = CbKasus.getSelectedItem().toString();
                String deskripsiKasus = TfDeskripsi.getText();

                try{
                    int id = Integer.parseInt(CariId.getText());

                    pst = getConn().prepareStatement("update kasus set nik = ?, nama = ?, jenis_kelamin = ?, jenis_kasus = ?, deskripsi_kasus = ? where id_kasus = ?");
                    pst.setString(1,nik);
                    pst.setString(2,nama);
                    pst.setString(3,jenisKelamin);
                    pst.setString(4,jenisKasus);
                    pst.setString(5,deskripsiKasus);
                    pst.setInt(6,id);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Update berhasil");
                    table_load(dataTable);
                    TfNIK.setText("");
                    TfNama.setText("");
                    CbKelamin.setSelectedItem("");
                    CbKasus.setSelectedItem("");
                    TfDeskripsi.setText("");
                    TfNama.requestFocus();

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        BtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id = CariId.getText();
                try {
                    pst = getConn().prepareStatement("delete from kasus where id_kasus = ?");
                    pst.setString(1, id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Delete berhasil");
                    table_load(dataTable);
                    TfNIK.setText("");
                    TfNama.setText("");
                    CbKelamin.setSelectedItem("");
                    CbKasus.setSelectedItem("");
                    TfDeskripsi.setText("");
                    TfNama.requestFocus();

                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });
        BtnInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deskripsi = TfDeskripsi.getText();
                JOptionPane.showMessageDialog(outputPanel,deskripsi,"Deskripsi Kasus",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        BtnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new mainPage(null);

            }
        });
    }


}



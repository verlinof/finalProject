import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class outputSortingPage extends connectTable{
    private JComboBox CbCariKasus;
    private JButton BtnSearch;
    private JTable dataTable;
    private JScrollPane JsDataTable;
    private JPanel outputSortingPanel;
    private JButton BtnKembali;
    private ResultSet rs;
    private PreparedStatement pst;
    private Statement stmt;

    public outputSortingPage(JFrame parent){
        //Untuk tampilan GUI nya
        connect(); //untuk memanggil method superclass untuk connect ke database
        table_load(dataTable); //untuk ngeload data yang diambil dari database ke dalam tabel
        JFrame frame = new JFrame("Halaman Sorting");
        frame.setContentPane(outputSortingPanel);
        frame.setSize(750,500);
        frame.setLocationRelativeTo(parent);
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.setVisible(true);

        BtnSearch.addActionListener(new ActionListener() { //
            @Override
            public void actionPerformed(ActionEvent e) {
                String jenisKasus = CbCariKasus.getSelectedItem().toString();
                if(jenisKasus.equalsIgnoreCase("Semua Kasus")){
                    table_load(dataTable);
                }
                else{
                    sortingTableLoad(jenisKasus);
                }
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

    //buat ngeload full kasus
    public void sortingTableLoad(String jenisKasus){
        try {
            pst = getConn().prepareStatement("select * from kasus where jenis_kasus = ?");
            pst.setString(1,jenisKasus);
            rs = pst.executeQuery();
            dataTable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

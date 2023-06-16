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
        connect();
        table_load(dataTable);
        JFrame frame = new JFrame("Halaman Sorting");
        frame.setContentPane(outputSortingPanel);
        frame.setSize(750,500);
        frame.setLocationRelativeTo(parent);
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.setVisible(true);

        BtnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jenisKasus = CbCariKasus.getSelectedItem().toString();
                if(jenisKasus.equalsIgnoreCase("Semua Kasus")){
                    sortingTableLoadAll(jenisKasus);
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

    //method untuk sortingan beberapa kasus saja atau semuanya
    public void sortingTableLoadAll(String jenisKasus){
        try {
            pst = getConn().prepareStatement("select * from kasus");
            rs = pst.executeQuery();
            dataTable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e){
            e.printStackTrace();
        }
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

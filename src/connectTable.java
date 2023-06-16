import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class connectTable {
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;


    public void connect(){
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projek_akhir","root","");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void table_load(JTable tabel){
        try {
            pst = conn.prepareStatement("select * from kasus");
            rs = pst.executeQuery();
            tabel.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }
}

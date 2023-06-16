import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainPage extends JFrame {

    private JButton inputbtn;
    private JButton updatebtn;
    private JButton sortbtn;
    private JPanel MainPage;
    private JButton kembalibtn;

    public mainPage(JFrame parent) {
        setTitle("Main Page");
        setContentPane(MainPage);
        setMinimumSize(new Dimension(750, 500));
        setResizable(false);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        //halaman update
        updatebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new outputPage(null);

            }
        });
        //halaman sorting kasus
        sortbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new outputSortingPage(null);

            }
        });
        //halaman input kasus
        inputbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new inputPage(null);

            }
        });
        kembalibtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new loginPage(null);
            }
        });
    }

}

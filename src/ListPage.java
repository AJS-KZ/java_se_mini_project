import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ListPage extends JPanel {
    Socket socket;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;

    private ClientFrame parent;

    private JLabel label;
    private JButton back;

    private String header[] = {"Name", "Surname", "Age"};
    private JTable table;
    private JScrollPane scrollPane;

    public ListPage(ClientFrame parent, Socket socket, ObjectOutputStream os, ObjectInputStream is){
        this.parent = parent;
        this.socket = socket;
        this.outputStream = os;
        this.inputStream = is;

        setSize(500,500);
        setLayout(null);

        label = new JLabel("LIST OF STUDENTS");
        label.setSize(300, 30);
        label.setLocation(100, 100);
        add(label);

        back = new JButton("BACK");
        back.setSize(100,30);
        back.setLocation(200, 420);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getListPage().setVisible(false);
                parent.getMainMenuPage().setVisible(true);
            }
        });

        table = new JTable();
        table.setFont(new Font("Calibri", Font.PLAIN, 12));
        table.setRowHeight(30);

        scrollPane = new JScrollPane(table);
        scrollPane.setSize(300, 200);
        scrollPane.setLocation(100, 200);
        add(scrollPane);
    }

    public void generateTable(ArrayList<Students> students) {
        Object data[][] = new Object[students.size()][4];
        for (int i = 0; i < students.size(); i++) {
            data[i][0] = students.get(i).getId();
            data[i][1] = students.get(i).getName();
            data[i][2] = students.get(i).getSurname();
            data[i][3] = students.get(i).getAge();
        }
        DefaultTableModel model = new DefaultTableModel(data, header);
        table.setModel(model);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MainMenu extends JPanel {

    Socket socket;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;

    private ClientFrame parent;

    private JButton addPageButton;
    private JButton listPageButton;
    private JButton exitButton;

    public MainMenu(ClientFrame parent, Socket socket, ObjectOutputStream os, ObjectInputStream is) {
        this.parent = parent;
        this.socket = socket;
        this.outputStream = os;
        this.inputStream = is;

        setSize(500,500);
        setLayout(null);

        addPageButton = new JButton("Add");
        addPageButton.setSize(300,30);
        addPageButton.setLocation(100,100);
        add(addPageButton);
        addPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.getMainMenuPage().setVisible(false);
                parent.getAddPage().setVisible(true);
            }
        });

        listPageButton = new JButton("List");
        listPageButton.setSize(300,30);
        listPageButton.setLocation(100,150);
        add(listPageButton);
        listPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ArrayList<Students> students = new ArrayList<Students>();
                try{
                    PackageData pd = new PackageData();
                    PackageData response = null;
                    pd.setOperationType("list_students");
                    outputStream.writeObject(pd);
                    if ((response = (PackageData) is.readObject()) != null) {
                        students = response.students;
                    }
                }catch (Exception x){
                    x.printStackTrace();
                }
                parent.getListPage().generateTable(students);
                parent.getMainMenuPage().setVisible(false);
                parent.getListPage().setVisible(true);
            }
        });

        exitButton = new JButton("Exit");
        exitButton.setSize(300,30);
        exitButton.setLocation(100,200);
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }
}
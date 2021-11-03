import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;

public class ClientFrame extends JFrame{
    private Socket socket;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;

    private MainMenu mainMenuPage;
    private AddPage addPage;
    private ListPage listPage;

    public ClientFrame(){

        try {
            socket = new Socket("127.0.0.1", 1980);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("BITLAB Application");
        setSize(500,500);
        setLayout(null);

        mainMenuPage = new MainMenu(this, socket, outputStream, inputStream);
        mainMenuPage.setVisible(true);
        add(mainMenuPage);

        addPage = new AddPage(this, socket, outputStream, inputStream);
        addPage.setVisible(false);
        add(addPage);

        listPage = new ListPage(this, socket, outputStream, inputStream);
        listPage.setVisible(false);
        add(listPage);

    }

    public MainMenu getMainMenuPage() {
        return mainMenuPage;
    }

    public AddPage getAddPage() {
        return addPage;
    }

    public ListPage getListPage() {
        return listPage;
    }

}

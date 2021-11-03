import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AddPage extends JPanel{
    Socket socket;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;

    private ClientFrame parent;
    private JButton add;
    private JButton back;
    private String surname;
    private String name;
    private Integer age;
    private JLabel name_label;
    private JLabel surname_label;
    private JLabel age_label;
    private JTextField name_field;
    private JTextField surname_field;
    private JTextField age_field;
    private JComboBox ageBox;
    private Integer[] ages = new Integer[100];

    public AddPage(ClientFrame parent, Socket socket, ObjectOutputStream os, ObjectInputStream is){
        this.parent = parent;
        this.socket = socket;
        this.outputStream = os;
        this.inputStream = is;

        for(int i =0;i<100;i++){
            ages[i]= i;
        }

        setSize(500, 500);
        setLayout(null);

        name_label = new JLabel("Name:");
        name_label.setSize(100, 30);
        name_label.setLocation(100, 50);
        add(name_label);

        name_field = new JTextField("");
        name_field.setSize(200, 30);
        name_field.setLocation(200, 50);
        add(name_field);

        surname_label = new JLabel("Surname:");
        surname_label.setSize(100, 30);
        surname_label.setLocation(100, 100);
        add(surname_label);

        surname_field = new JTextField("");
        surname_field.setSize(200, 30);
        surname_field.setLocation(200, 100);
        add(surname_field);

        age_label = new JLabel("Age:");
        age_label.setSize(100, 30);
        age_label.setLocation(100, 150);
        add(age_label);

        ageBox = new JComboBox(ages);
        ageBox.setSize(200, 30);
        ageBox.setLocation(200, 150);
        add(ageBox);

        add = new JButton("Add");
        add.setSize(100, 30);
        add.setLocation(100, 350);
        add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = name_field.getText();
                surname = surname_field.getText();
                age = (Integer) ageBox.getSelectedItem();
                if(!name.equals("")&&!surname.equals("")&&age>0) {
                    name_field.setText("");
                    surname_field.setText("");
                    ageBox.setSelectedIndex(0);
                    Long id = new Long(1);
                    PackageData pd = new PackageData();
                    pd.setOperationType("add_student");
                    pd.setStudent(new Students(id, name, surname, age));
                    try {
                        outputStream.writeObject(pd);

                    }catch (Exception x){
                        x.printStackTrace();
                    }
                }
            }
        });


        back = new JButton("Back");
        back.setSize(100, 30);
        back.setLocation(250, 350);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.getAddPage().setVisible(false);
                parent.getMainMenuPage().setVisible(true);
            }
        });


    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }


    public String getName() {
        return name;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }
}

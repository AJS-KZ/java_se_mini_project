import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ClientHandler extends Thread{
    private Socket socket;
    private int id;
    DBManager db;

    public ClientHandler(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }

    public void run(){
        try{
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            db = new DBManager();
            db.connect();
            PackageData data = null;
            while((data = (PackageData) inputStream.readObject())!=null){
                PackageData response = new PackageData();
                if(data.getOperationType().equals("add_student")){
                    System.out.println("got new student");
                    db.addStudent(data.student);
                    System.out.println("student added!");
                }
                if(data.operationType.equals("list_students")){
                    response.students = db.getALLStudents();
                }
                outputStream.writeObject(response);
            }
        }catch (Exception e){

        }
    }
}

import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    String operationType;
    ArrayList<Students> students;
    Students student;

    public PackageData(){
        this.operationType=" ";
        this.students=null;
        this.student=null;
    }
    public PackageData(String operationType , ArrayList<Students>students,Students student){
        this.operationType=operationType;
        this.students=students;
        this.student=student;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public ArrayList<Students> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Students> students) {
        this.students = students;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "operationType='" + operationType + '\'' +", students=" + students +", student=" + student ;
    }
}
import java.io.Serializable;

public class Students implements Serializable {
    long id ;
    String name ;
    String surname;
    int age ;
    public Students (){
        this.id=0;
        this.name=null;
        this.surname=null;
        this.age=0;
    }
    public Students (Long id, String name, String surname, int age){
        this.id = id;
        this.name=name;
        this.surname=surname;
        this.age=age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name='" + name + '\'' +", surname='" + surname + '\'' + ", age=" + age;
    }
}

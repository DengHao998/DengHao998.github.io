package studentms;

public class Student {
     int id;
     String name;
     String sex;
     String classz;

    public Student(int id, String name, String sex, String classz) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.classz = classz;
    }
    public Student() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClassz() {
        return classz;
    }

    public void setClassz(String classz) {
        this.classz = classz;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", classz='" + classz + '\'' +
                '}';
    }
}

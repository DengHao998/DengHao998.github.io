package studentms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentServiceImpl implements StudentService {

    @Override
    public void insertStu() {
        Connection conn = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep2 = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql1 = "insert into student values(?,?,?,?)";
            prep1 = conn.prepareStatement(sql1);
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入学生id");
            int a = sc.nextInt();
            System.out.println("请输入学生姓名");
            String b = sc.next();
            System.out.println("请输入学生性别");
            String c = sc.next();
            System.out.println("请输入学生所在班级");
            String d = sc.next();
            String sql2 = "select * from student";
            Student student = new Student();
            prep2 = conn.prepareStatement(sql2);
            rs = prep2.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                if (a == id) {
                    System.out.println("添加失败，该学生信息已存在，不能再次添加");
                    return;
                }
            }
            prep1.setInt(1, a);
            prep1.setString(2, b);
            prep1.setString(3, c);
            prep1.setString(4, d);
            prep1.executeUpdate();
            Student stu = new Student(a, b, c, d);
            System.out.println("学生信息录入成功");
            System.out.println("新增学生信息：" + stu);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, prep1);
            JdbcUtil.close(conn, prep2, rs);
        }
    }

    @Override
    public void updateStu() {
        Connection conn = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep2 = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql1 = "update student set name=? , sex=? , classz=? where id=?";       //学生id唯一
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入要修改的学生id");
            int a = sc.nextInt();
            String sql2 = "select id from student ";
            prep2 = conn.prepareStatement(sql2);
            rs = prep2.executeQuery(sql2);
            while (rs.next()) {
                int id = rs.getInt("id");
                if (a == id) {
                    prep1 = conn.prepareStatement(sql1);
                    System.out.println("请输入学生姓名");
                    String b = sc.next();
                    System.out.println("请输入学生性别");
                    String c = sc.next();
                    System.out.println("请输入学生所在班级");
                    String d = sc.next();
                    prep1.setString(1, b);
                    prep1.setString(2, c);
                    prep1.setString(3, d);
                    prep1.setInt(4, a);
                    prep1.execute();
                    Student stu = new Student(a, b, c, d);
                    System.out.println("学生信息修改成功");
                    System.out.println("修改后的学生信息为：" + stu);
                    return;
                }
            }
            System.out.println("修改失败，该学生成绩信息不存在");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, prep1);
            JdbcUtil.close(conn, prep2, rs);
        }
    }

    @Override
    public void deleteStu() {
        Connection conn = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep2 = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql1 = "delete from student where id=?";       //学生id唯一
            prep1 =conn.prepareStatement(sql1);
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入要删除的学生id");
            int a = sc.nextInt();
            String sql2 = "select * from student where id=? ";
            prep2 = conn.prepareStatement(sql2);
            prep2.setInt(1,a);
            rs = prep2.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name=rs.getString("name");
                String sex=rs.getString("sex");
                String classz=rs.getString("classz");
                if (a == id) {
                    prep1.setInt(1, a);
                    prep1.execute();
                    Student stu = new Student(a, name, sex, classz);
                    System.out.println("学生信息删除成功");
                    System.out.println("删除的学生信息为：" + stu);
                    return;
                }
            }
            System.out.println("删除失败，该学生信息不存在");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, prep1);
            JdbcUtil.close(conn, prep2, rs);
        }
    }

    @Override
    public void selectStu() {
        Connection conn =null;
        PreparedStatement prep =null;
        ResultSet rs =null;
        try {
            conn = JdbcUtil.getConnection();
            String sql1="select * from student where id=?";        //学生id唯一
            prep = conn.prepareStatement(sql1);
            Scanner sc=new Scanner(System.in);
            System.out.println("请输入学生id");
            int a=sc.nextInt();
            prep.setInt(1,a);
            rs = prep.executeQuery();
            while (rs.next()){
                String name=rs.getString("name");
                String sex=rs.getString("sex");
                String classz=rs.getString("classz");
                System.out.println("查找成功，查找到的学生信息为：");
                Student stu = new Student(a, name, sex, classz);
                System.out.println(stu);
                return;
            }
            System.out.println("查找失败，该学生信息不存在");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtil.close(conn,prep,rs);
        }
    }
}

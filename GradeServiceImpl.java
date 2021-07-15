package studentms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GradeServiceImpl implements GradeService {
    @Override
    public void insertGrade() {
        Connection conn = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep2 = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "insert into grade values(?,?,?,?)";
            prep1 = conn.prepareStatement(sql);
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入学生id");
            int a = sc.nextInt();
            System.out.println("请输入学生姓名");
            String b = sc.next();
            System.out.println("请输入课程");
            String c = sc.next();
            System.out.println("请输入成绩");
            int d = sc.nextInt();
            String sql2 = "select * from grade";
            prep2 = conn.prepareStatement(sql2);
            rs = prep2.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                if (a == id) {          //id唯一
                    System.out.println("添加失败，该学生的成绩信息已存在，不能再次添加");
                    return;
                }
            }
            prep1.setInt(1, a);
            prep1.setString(2, b);
            prep1.setString(3, c);
            prep1.setInt(4, d);
            prep1.executeUpdate();
            Grade grade = new Grade(a, b, c, d);
            System.out.println("成绩信息录入成功");
            System.out.println("新增成绩信息：" + grade);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, prep1);
            JdbcUtil.close(conn, prep2, rs);
        }
    }

    @Override
    public void updateGrade() {
        Connection conn = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep2 = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql1 = "update grade set name=? , course=? , grade=? where id=?";       //学生id唯一
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入要修改成绩信息的学生id");
            int a = sc.nextInt();
            String sql2 = "select id from grade ";
            prep2 = conn.prepareStatement(sql2);
            rs = prep2.executeQuery(sql2);
            while (rs.next()) {
                int id = rs.getInt("id");
                if (a == id) {
                    prep1 = conn.prepareStatement(sql1);
                    System.out.println("请输入学生姓名");
                    String b = sc.next();
                    System.out.println("请输入课程");
                    String c = sc.next();
                    System.out.println("请输入成绩");
                    int d = sc.nextInt();
                    prep1.setString(1, b);
                    prep1.setString(2, c);
                    prep1.setInt(3, d);
                    prep1.setInt(4, a);
                    prep1.execute();
                    Grade grade = new Grade(a, b, c, d);
                    System.out.println("成绩信息修改成功");
                    System.out.println("修改后的成绩信息为：" + grade);
                    return;
                }
            }
            System.out.println("修改失败，该成绩不存在");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, prep1);
            JdbcUtil.close(conn, prep2, rs);
        }
    }

    @Override
    public void deleteGrade() {
        Connection conn = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep2 = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql1 = "delete from grade where id=?";      //学生id唯一
            prep1 =conn.prepareStatement(sql1);
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入要删除成绩信息的学生id");
            int a = sc.nextInt();
            String sql2 = "select * from grade where id=? ";
            prep2 = conn.prepareStatement(sql2);
            prep2.setInt(1,a);
            rs = prep2.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name=rs.getString("name");
                String course=rs.getString("course");
                int grade=rs.getInt("grade");
                if (a == id) {
                    prep1.setInt(1, a);
                    prep1.execute();
                    Grade gra = new Grade(a, name, course, grade);
                    System.out.println("成绩信息删除成功");
                    System.out.println("删除的成绩信息为：" + gra);
                    return;
                }
            }
            System.out.println("删除失败，该成绩信息不存在");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, prep1);
            JdbcUtil.close(conn, prep2, rs);
        }
    }

    @Override
    public void selectGrade() {
        Connection conn =null;
        PreparedStatement prep =null;
        ResultSet rs =null;
        try {
            conn = JdbcUtil.getConnection();
            String sql1="select * from grade where id=?";       //学生id唯一
            prep = conn.prepareStatement(sql1);
            Scanner sc=new Scanner(System.in);
            System.out.println("请输入学生id");
            int a=sc.nextInt();
            prep.setInt(1,a);
            rs = prep.executeQuery();
            while (rs.next()){
                String name=rs.getString("name");
                String course=rs.getString("course");
                int grade=rs.getInt("grade");
                System.out.println("查找成功，查找到的成绩信息为：");
                Grade gra = new Grade(a, name, course, grade);
                System.out.println(gra);
                return;
            }
            System.out.println("查找失败，该成绩信息不存在");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtil.close(conn,prep,rs);
        }
    }
}

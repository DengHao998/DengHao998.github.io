package studentms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("***欢迎来到学生成绩管理系统***");
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入你的管理员用户名");
            String a = sc.next();
            System.out.println("请输入你的用户密码");
            String b = sc.next();
            Connection conn = JdbcUtil.getConnection();
            String sql1 = "select * from admin ";
            PreparedStatement prep = null;
            ResultSet rs = null;
            try {
                prep = conn.prepareStatement(sql1);
                rs = prep.executeQuery();
                while (rs.next()) {
                    String admin = rs.getString("admin");
                    String password = rs.getString("password");
                    if (a.equals(admin) && b.equals(password)) {
                        System.out.println("登录成功");
                        while (true) {
                            StudentServiceImpl stu=new StudentServiceImpl();
                            GradeServiceImpl gra=new GradeServiceImpl();
                            int i = input();
                            switch (i) {
                                case 1: {
                                    stu.insertStu();
                                    break;
                                }
                                case 2: {
                                    gra.insertGrade();
                                    break;
                                }
                                case 3: {
                                    stu.updateStu();
                                    break;
                                }
                                case 4: {
                                    gra.updateGrade();
                                    break;
                                }
                                case 5: {
                                    stu.deleteStu();
                                    break;
                                }
                                case 6: {
                                    gra.deleteGrade();
                                    break;
                                }
                                case 7: {
                                   stu.selectStu();
                                    break;
                                }
                                case 8: {
                                    gra.selectGrade();
                                    break;
                                }
                                case 9: {
                                    System.out.println("退出系统成功");
                                    return;
                                }
                                default: {
                                    System.out.println("输入错误：只能按下1-9中的某一个数字键。");
                                    break;
                                }
                            }
                        }
                    }
                }
                System.out.println("登录失败，用户名或密码错误，请再次进行输入。");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JdbcUtil.close(conn, prep, rs);
            }

        }
    }

    public static int input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("************************************");
        System.out.println("*    ****** 学生成绩管理 ******     *");
        System.out.println("*        1. 录入学生信息            *");
        System.out.println("*        2. 录入成绩信息            *");
        System.out.println("*        3. 修改学生信息            *");
        System.out.println("*        4. 修改成绩信息            *");
        System.out.println("*        5. 删除学生信息            *");
        System.out.println("*        6. 删除成绩信息            *");
        System.out.println("*        7. 查找学生信息            *");
        System.out.println("*        8. 查找成绩信息            *");
        System.out.println("*        9. 退出系统                *");
        System.out.println("*  请选择序号进行功能选择（1-9）      *");
        System.out.println("************************************");
        int i = sc.nextInt();
        return i;
    }
}
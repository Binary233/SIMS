import java.sql.*;
import java.util.Scanner;

public class StudentInfoManagementSystem {
    // 数据库连接信息
    private static final String dbUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=StudentDB;encrypt=false";
    private static final String dbUser = "sa";
    private static final String dbPass = "341122";

    // 查询语句
    private static final String queryBasicInfo = "SELECT * FROM StudentBasicInfo WHERE stuId=?";
    private static final String queryScoreInfo = "SELECT * FROM StudentScoreInfo WHERE stuId=?";
    private static final String queryCourseInfo = "SELECT * FROM StudentCourseInfo WHERE stuId=?";

    // 添加语句
    private static final String insertBasicInfo = "INSERT INTO StudentBasicInfo (stuId, name, gender, birthday, major) VALUES (?, ?, ?, ?, ?)";
    private static final String insertScoreInfo = "INSERT INTO StudentScoreInfo (stuId, course, score) VALUES (?, ?, ?)";
    private static final String insertCourseInfo = "INSERT INTO StudentCourseInfo (stuId, course, teacher) VALUES (?, ?, ?)";

    // 删除语句
    private static final String deleteBasicInfo = "DELETE FROM StudentBasicInfo WHERE stuId=?";
    private static final String deleteScoreInfo = "DELETE FROM StudentScoreInfo WHERE stuId=? AND course=?";
    private static final String deleteCourseInfo = "DELETE FROM StudentCourseInfo WHERE stuId=? AND course=?";

    // 修改语句
    private static final String updateBasicInfo = "UPDATE StudentBasicInfo SET name=?, gender=?, birthday=?, major=? WHERE stuId=?";
    private static final String updateScoreInfo = "UPDATE StudentScoreInfo SET score=? WHERE stuId=? AND course=?";
    private static final String updateCourseInfo = "UPDATE StudentCourseInfo SET teacher=? WHERE stuId=? AND course=?";

    // 数据库连接
    private Connection conn = null;

    public StudentInfoManagementSystem() {
        // 构造函数中连接数据库
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询学生基本信息
    public void queryBasicInfo(String stuId) {
        try {
            PreparedStatement ps = conn.prepareStatement(queryBasicInfo);
            ps.setString(1, stuId);
            ResultSet rs = ps.executeQuery();

            // 处理查询结果
            while (rs.next()) {
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                Date birthday = rs.getDate("birthday");
                String major = rs.getString("major");

                System.out.println("姓名：" + name);
                System.out.println("性别：" + gender);
                System.out.println("出生日期：" + birthday);
                System.out.println("专业：" + major);
            }

            // 关闭连接
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询学生成绩信息
    public void queryScoreInfo(String stuId) {
        try {
            PreparedStatement ps = conn.prepareStatement(queryScoreInfo);
            ps.setString(1, stuId);
            ResultSet rs = ps.executeQuery();

            // 处理查询结果
            while (rs.next()) {
                String course = rs.getString("course");
                int score = rs.getInt("score");

                System.out.println("课程：" + course);
                System.out.println("成绩：" + score);
            }

            // 关闭连接
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询学生选课信息
    public void queryCourseInfo(String stuId) {
        try {
            PreparedStatement ps = conn.prepareStatement(queryCourseInfo);
            ps.setString(1, stuId);
            ResultSet rs = ps.executeQuery();

            // 处理查询结果
            while (rs.next()) {
                String course = rs.getString("course");
                String teacher = rs.getString("teacher");

                System.out.println("课程：" + course);
                System.out.println("教师：" + teacher);
            }

            // 关闭连接
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 添加学生基本信息
    public void addBasicInfo(String stuId, String name, String gender, Date birthday, String major) {
        try {
            PreparedStatement ps = conn.prepareStatement(insertBasicInfo);
            ps.setString(1, stuId);
            ps.setString(2, name);
            ps.setString(3, gender);
            ps.setDate(4, birthday);
            ps.setString(5, major);
            ps.executeUpdate();

            System.out.println("添加成功");
            // 关闭连接
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 添加学生成绩信息
    public void addScoreInfo(String stuId, String course, int score) {
        try {
            PreparedStatement ps = conn.prepareStatement(insertScoreInfo);
            ps.setString(1, stuId);
            ps.setString(2, course);
            ps.setInt(3, score);
            ps.executeUpdate();

            System.out.println("添加成功");
            // 关闭连接
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 添加学生选课信息
    public void addCourseInfo(String stuId, String course, String teacher) {
        try {
            PreparedStatement ps = conn.prepareStatement(insertCourseInfo);
            ps.setString(1, stuId);
            ps.setString(2, course);
            ps.setString(3, teacher);
            ps.executeUpdate();

            System.out.println("添加成功");
            // 关闭连接
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除学生基本信息
    public void deleteBasicInfo(String stuId) {
        try {
            PreparedStatement ps = conn.prepareStatement(deleteBasicInfo);
            ps.setString(1, stuId);
            ps.executeUpdate();

            System.out.println("删除成功");
            // 关闭连接
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除学生成绩信息
    public void deleteScoreInfo(String stuId, String course) {

        try {
            PreparedStatement ps = conn.prepareStatement(deleteScoreInfo);
            ps.setString(1, stuId);
            ps.setString(2, course);
            ps.executeUpdate();

            System.out.println("删除成功");
            // 关闭连接
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO： 输出当前学生所有成绩信息，使可选择删除具体某一科成绩信息

    // 删除学生选课信息
    public void deleteCourseInfo(String stuId, String course) {
        try {
            PreparedStatement ps = conn.prepareStatement(deleteCourseInfo);
            ps.setString(1, stuId);
            ps.setString(2, course);
            ps.executeUpdate();

            System.out.println("删除成功");
            // 关闭连接
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO： 输出当前学生所有选课信息，使可选择删除某个选课信息

    // 修改学生基本信息
    public void updateBasicInfo(String stuId, String name, String gender, Date birthday, String major) {
        try {
            PreparedStatement ps = conn.prepareStatement(updateBasicInfo);
            ps.setString(1, name);
            ps.setString(2, gender);
            ps.setDate(3, birthday);
            ps.setString(4, major);
            ps.setString(5, stuId);
            ps.executeUpdate();

            System.out.println("修改成功");
            // 关闭连接
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO： 输出当前学生基本信息，使可选择修改具体的某一个信息

    // 修改学生成绩信息
    public void updateScoreInfo(String stuId, String course, int score) {
        try {
            PreparedStatement ps = conn.prepareStatement(updateScoreInfo);
            ps.setInt(1, score);
            ps.setString(2, stuId);
            ps.setString(3, course);
            ps.executeUpdate();

            System.out.println("修改成功");
            // 关闭连接
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    // TODO： 输出当前学生基本信息，使可选择修改具体的某一个信息

    // 修改学生选课信息
    public void updateCourseInfo(String stuId, String course, String teacher) {
        try {
            PreparedStatement ps = conn.prepareStatement(updateCourseInfo);
            ps.setString(1, teacher);
            ps.setString(2, stuId);
            ps.setString(3, course);
            ps.executeUpdate();

            System.out.println("修改成功");
            // 关闭连接
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO：输出当前学生选课信息，使可选择修改具体的某一个选课信息

    public static void main(String[] args) {
        StudentInfoManagementSystem gui = new StudentInfoManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请选择操作:");
            System.out.println(" 1. 查询学生基本信息");
            System.out.println(" 2. 查询学生成绩信息");
            System.out.println(" 3. 查询学生选课信息");
            System.out.println(" 4. 添加学生基本信息");
            System.out.println(" 5. 添加学生成绩信息");
            System.out.println(" 6. 添加学生选课信息");
            System.out.println(" 7. 删除学生基本信息");
            System.out.println(" 8. 删除学生成绩信息");
            System.out.println(" 9. 删除学生选课信息");
            System.out.println("10. 修改学生基本信息");
            System.out.println("11. 修改学生成绩信息");
            System.out.println("12. 修改学生选课信息");
            System.out.println(" 0. 退出程序");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("请输入学生学号:");
                    String stuId1 = scanner.next();
                    gui.queryBasicInfo(stuId1);
                    break;
                case 2:
                    System.out.println("请输入学生学号:");
                    String stuId2 = scanner.next();
                    gui.queryScoreInfo(stuId2);
                    break;
                case 3:
                    System.out.println("请输入学生学号:");
                    String stuId3 = scanner.next();
                    gui.queryCourseInfo(stuId3);
                    break;
                case 4:
                    System.out.println("请输入学生学号:");
                    String stuId4 = scanner.next();
                    System.out.println("请输入学生姓名:");
                    String name4 = scanner.next();
                    System.out.println("请输入学生性别:");
                    String gender4 = scanner.next();
                    System.out.println("请输入学生生日:");
                    Date birthday4 = Date.valueOf(scanner.next());
                    System.out.println("请输入学生专业:");
                    String major4 = scanner.next();
                    gui.addBasicInfo(stuId4, name4, gender4, birthday4, major4);
                    break;
                case 5:
                    System.out.println("请输入学生学号:");
                    String stuId5 = scanner.next();
                    System.out.println("请输入课程名称:");
                    String course5 = scanner.next();
                    System.out.println("请输入课程成绩:");
                    int score5 = scanner.nextInt();
                    gui.addScoreInfo(stuId5, course5, score5);
                    break;
                case 6:
                    System.out.println("请输入学生学号:");
                    String stuId6 = scanner.next();
                    System.out.println("请输入课程名称:");
                    String course6 = scanner.next();
                    System.out.println("请输入任课教师:");
                    String teacher6 = scanner.next();
                    gui.addCourseInfo(stuId6, course6, teacher6);
                    break;
                case 7:
                    System.out.println("请输入学生学号:");
                    String stuId7 = scanner.next();
                    gui.deleteBasicInfo(stuId7);
                    break;
                case 8:
                    System.out.println("请输入学生学号:");
                    String stuId8 = scanner.next();
                    System.out.println("请输入课程名称:");
                    String course8 = scanner.next();
                    gui.deleteScoreInfo(stuId8, course8);
                    break;
                case 9:
                    System.out.println("请输入学生学号:");
                    String stuId9 = scanner.next();
                    System.out.println("请输入课程名称:");
                    String course9 = scanner.next();
                    gui.deleteCourseInfo(stuId9, course9);
                    break;
                case 10:
                    System.out.println("请输入学生学号:");
                    String stuId10 = scanner.next();
                    System.out.println("请输入学生姓名:");
                    String name10 = scanner.next();
                    System.out.println("请输入学生性别:");
                    String gender10 = scanner.next();
                    System.out.println("请输入学生生日:");
                    Date birthday10 = Date.valueOf(scanner.next());
                    System.out.println("请输入学生专业:");
                    String major10 = scanner.next();
                    gui.updateBasicInfo(stuId10, name10, gender10, birthday10, major10);
                    break;
                case 11:
                    System.out.println("请输入学生学号:");
                    String stuId11 = scanner.next();
                    System.out.println("请输入课程名称:");
                    String course11 = scanner.next();
                    System.out.println("请输入课程成绩:");
                    int score11 = scanner.nextInt();
                    gui.updateScoreInfo(stuId11, course11, score11);
                    break;
                case 12:
                    System.out.println("请输入学生学号:");
                    String stuId12 = scanner.next();
                    System.out.println("请输入课程名称:");
                    String course12 = scanner.next();
                    System.out.println("请输入任课教师:");
                    String teacher12 = scanner.next();
                    gui.updateCourseInfo(stuId12, course12, teacher12);
                    break;

                case 0:
                    System.out.println("程序已退出。");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入有误，请重新输入。");
                    break;
            }
        }
    }
}
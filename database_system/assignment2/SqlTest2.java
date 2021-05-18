package org.lani.db.assignment2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SqlTest2 {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("SQL Programming Test");

        System.out.println("Connecting PostgreSQL database");
        // JDBC를 이용해 PostgreSQL 서버 및 데이터베이스 연결
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AjouDB", "ajou_db", "ajou");
        connection.setAutoCommit(false);    // ! TODO

        System.out.println("Creating College, Student, Apply relations");
        // 3개 테이블 생성: Create table문 이용
        Statement stm = connection.createStatement();
        stm.addBatch("create table College(cName varchar(20), state char(2), enrollment int)");
        stm.addBatch("create table Student(sID int, sName varchar(20), GPA numeric(2,1), sizeHS int)");
        stm.addBatch("create table Apply(sID int, cName varchar(20), major varchar(20), decision char)");
        stm.executeBatch();
        stm.close();

        System.out.println("Inserting tuples to College, Student, Apply relations");
        // 3개 테이블에 튜플 생성: Insert문 이용
        stm = connection.createStatement();
        stm.addBatch("insert into College values ('Stanford', 'CA', 15000)");
        stm.addBatch("insert into College values ('Berkeley', 'CA', 36000)");
        stm.addBatch("insert into College values ('MIT', 'MA', 10000)");
        stm.addBatch("insert into College values ('Cornell', 'NY', 21000)");
        stm.addBatch("insert into Student values (123, 'Amy', 3.9, 1000)");
        stm.addBatch("insert into Student values (234, 'Bob', 3.6, 1500)");
        stm.addBatch("insert into Student values (345, 'Craig', 3.5, 500)");
        stm.addBatch("insert into Student values (456, 'Doris', 3.9, 1000)");
        stm.addBatch("insert into Student values (567, 'Edward', 2.9, 2000)");
        stm.addBatch("insert into Student values (678, 'Fay', 3.8, 200)");
        stm.addBatch("insert into Student values (789, 'Gary', 3.4, 800)");
        stm.addBatch("insert into Student values (987, 'Helen', 3.7, 800)");
        stm.addBatch("insert into Student values (876, 'Irene', 3.9, 400)");
        stm.addBatch("insert into Student values (765, 'Jay', 2.9, 1500)");
        stm.addBatch("insert into Student values (654, 'Amy', 3.9, 1000)");
        stm.addBatch("insert into Student values (543, 'Craig', 3.4, 2000)");
        stm.addBatch("insert into Apply values (123, 'Stanford', 'CS', 'Y')");
        stm.addBatch("insert into Apply values (123, 'Stanford', 'EE', 'N')");
        stm.addBatch("insert into Apply values (123, 'Berkeley', 'CS', 'Y')");
        stm.addBatch("insert into Apply values (123, 'Cornell', 'EE', 'Y')");
        stm.addBatch("insert into Apply values (234, 'Berkeley', 'biology', 'N')");
        stm.addBatch("insert into Apply values (345, 'MIT', 'bioengineering', 'Y')");
        stm.addBatch("insert into Apply values (345, 'Cornell', 'bioengineering', 'N')");
        stm.addBatch("insert into Apply values (345, 'Cornell', 'CS', 'Y')");
        stm.addBatch("insert into Apply values (345, 'Cornell', 'EE', 'N')");
        stm.addBatch("insert into Apply values (678, 'Stanford', 'history', 'Y')");
        stm.addBatch("insert into Apply values (987, 'Stanford', 'CS', 'Y')");
        stm.addBatch("insert into Apply values (987, 'Berkeley', 'CS', 'Y')");
        stm.addBatch("insert into Apply values (876, 'Stanford', 'CS', 'N')");
        stm.addBatch("insert into Apply values (876, 'MIT', 'biology', 'Y')");
        stm.addBatch("insert into Apply values (876, 'MIT', 'marine biology', 'N')");
        stm.addBatch("insert into Apply values (765, 'Stanford', 'history', 'Y')");
        stm.addBatch("insert into Apply values (765, 'Cornell', 'history', 'N')");
        stm.addBatch("insert into Apply values (765, 'Cornell', 'psychology', 'Y')");
        stm.addBatch("insert into Apply values (543, 'MIT', 'CS', 'N')");
        stm.executeBatch();
        stm.close();

        System.out.println("Continue? (Enter 1 for continue)\n");
        scan.nextLine();
        System.out.println("Query 1");
        // Query 1을 실행: Select문 이용
        // Query 처리 결과는 적절한 Print문을 이용해 Display
        stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("select * from College");
        System.out.println("---------College----------");
        while (rs.next()) {
            System.out.printf("│%10s│ %s │%8d│\n", rs.getString("cName"), rs.getString("state"), rs.getInt("enrollment"));
        }
        System.out.println("--------------------------\n");
        rs.close();
        stm.close();

        System.out.println("Continue? (Enter 1 for continue)\n");
        scan.nextLine();
        // Query 2 ~ Query 5에 대해 Query 1과 같은 방식으로 실행: Select문 이용
        // Query 처리 결과는 적절한 Print문을 이용해 Display
        stm = connection.createStatement();
        rs = stm.executeQuery("select * from Student");
        System.out.println("-------------Student-------------");
        while (rs.next()) {
            System.out.printf("│ %d │%6s│ %.3f │%10d│\n", rs.getInt("sID"), rs.getString("sName"), rs.getDouble("GPA"), rs.getInt("sizeHS"));
        }
        System.out.println("---------------------------------\n");
        rs.close();
        stm.close();


        // Query 3
        System.out.println("Continue? (Enter 1 for continue)\n");
        scan.nextLine();
        stm = connection.createStatement();
        rs = stm.executeQuery("select * from Apply");
        System.out.println("-----------------Apply-----------------");
        // sID int, cName varchar(20), major varchar(20), decision char
        while (rs.next()) {
            System.out.printf("│ %d │%10s│%16s│ %s │\n", rs.getInt("sID"), rs.getString("cName"), rs.getString("major"), rs.getString("decision"));
        }
        System.out.println("---------------------------------------");
        rs.close();
        stm.close();


        // Query 4
        System.out.println("Continue? (Enter 1 for continue)\n");
        scan.nextLine();

        stm = connection.createStatement();
        rs = stm.executeQuery("select cName, major, min(GPA), max(GPA)\n" +
            "from Student, Apply\n" +
            "where Student.sID = Apply.sID\n" +
            "group by cName, major\n" +
            "having min(GPA) > 3.0\n" +
            "order by cName, major"
        );

        System.out.println("------------------Query4-----------------");
        // sID int, cName varchar(20), major varchar(20), decision char
        while (rs.next()) {
            System.out.printf("│%10s│%16s│%.3f│%.3f│\n", rs.getString("cName"), rs.getString("major"), rs.getDouble(3), rs.getDouble(4));
        }
        System.out.println("-----------------------------------------");
        rs.close();
        stm.close();


        // Query 5
        System.out.println("Continue? (Enter 1 for continue)\n");
        scan.nextLine();

        stm = connection.createStatement();
        rs = stm.executeQuery("select distinct cName\n" +
            "from Apply A1\n" +
            "where 6 > (select count(*) from Apply A2 where A2.cName = A1.cName)"
        );

        System.out.println("---Query5---");
        // sID int, cName varchar(20), major varchar(20), decision char
        while (rs.next()) {
            System.out.printf("│%10s│\n", rs.getString("cName"));
        }
        System.out.println("------------");
        rs.close();
        stm.close();


        System.out.println("Continue? (Enter 1 for continue)\n");
        scan.nextLine();
        System.out.println("Query 6");
        // Query 6을 실행: Select문 이용
        // 사용자에게 major1, major2 값으로 CS, EE 입력 받음
        // 입력 받은 값을 넣어 Query를 처리하고
        // 결과는 적절한 Print문을 이용해 Display

        List<String> majorValueList = new ArrayList<>();

        stm = connection.createStatement();
        rs = stm.executeQuery("select major from Apply");

        while (rs.next()) {
            majorValueList.add(rs.getString("major"));
        }

        stm.close();
        rs.close();

        String major1;
        String major2;
        while (true) {
            System.out.println("\nPlease Input values below");
            System.out.print("major1 -> ");
            major1 = scan.nextLine();
            if (!majorValueList.contains(major1)) {
                System.out.println("Please Input Correct major!");
                continue;
            }
            System.out.print("major2 -> ");
            major2 = scan.nextLine();
            if (!majorValueList.contains(major2)) {
                System.out.println("Please Input Correct major!");
                continue;
            }
            break;
        }

        PreparedStatement pstmt;
        pstmt = connection.prepareStatement("select sID, sName\n" +
            "from Student\n" +
            "where sID = any (select sID from Apply where major = ?)\n" +
                "and not sID = any (select sID from Apply where major = ?)"
        );
        pstmt.setString(1, major1);
        pstmt.setString(2, major2);
        rs = pstmt.executeQuery();

        System.out.println("----Query6----");
        // sID int, cName varchar(20), major varchar(20), decision char
        while (rs.next()) {
            System.out.printf("│ %d │%6s│\n", rs.getInt("sID"), rs.getString("sName"));
        }
        System.out.println("--------------");
        rs.close();
        pstmt.close();
        System.out.println("Continue? (Enter 1 for continue)\n");
        scan.nextLine();


        System.out.println("Query 7");
        // Query 7을 실행: Select문 이용
        // 사용자에게 major, cName 값으로 CS, Stanford 입력 받음
        // 입력 받은 값을 넣어 Query를 처리하고
        // 결과는 적절한 Print문을 이용해 Display

        List<String> collegeValueList = new ArrayList<>();

        stm = connection.createStatement();
        rs = stm.executeQuery("select cName from College");

        while (rs.next()) {
            collegeValueList.add(rs.getString("cName"));
        }

        stm.close();
        rs.close();


        String major;
        String college;
        while (true) {
            System.out.println("\nPlease Input values below");
            System.out.print("major -> ");
            major = scan.nextLine();
            if (!majorValueList.contains(major)) {
                System.out.println("Please Input Correct value!");
                continue;
            }
            System.out.print("college -> ");
            college = scan.nextLine();
            if (!collegeValueList.contains(college)) {
                System.out.println("Please Input Correct value!");
                continue;
            }
            break;
        }


        pstmt = connection.prepareStatement("select sName, GPA\n" +
            "from Student natural join Apply\n" +
            "where major = ? and cName = ?"
        );
        pstmt.setString(1, major);
        pstmt.setString(2, college);
        rs = pstmt.executeQuery();

        System.out.println("----Query7----");
        while (rs.next()) {
            System.out.printf("│%6s│%.3f│\n", rs.getString("sName"), rs.getDouble("GPA"));
        }
        System.out.println("--------------\n");
        rs.close();
        stm.close();
        connection.close();

        scan.close();
    }
}

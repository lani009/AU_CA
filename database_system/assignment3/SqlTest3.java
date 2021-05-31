package org.lani.db.assignment3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SqlTest3 {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AjouDB", "ajou_db",
                "ajou")) {
            Scanner scan = new Scanner(System.in);
            conn.setAutoCommit(true); // TODO
            System.out.println("SQL Programming Test");

            System.out.println("Trigger test 1");

            // Trigger R2 생성
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                  "create function apply_sid_cascade() returns trigger as\n"
                + "$$\n"
                + "begin\n"
                +     "delete from Apply where sID = Old.sID;\n"
                +     "return null;"
                + "end;\n"
                + "$$\n"
                +     "language 'plpgsql';\n"
                + "create trigger R2\n"
                +     "after delete on Student\n"
                +     "for each row\n"
                + "execute procedure apply_sid_cascade()"
            );

            // Delete문 실행
            stmt.executeUpdate("delete from Student where GPA < 3.5");

            // Query 1 실행하고 결과는 적절한 Print문을 이용해 Display
            ResultSet rs = stmt.executeQuery("select * from Student order by sID");

            System.out.println("-----------------query 1-----------------");
            while (rs.next()) {
                System.out.printf(" %2d  %d  %10s  %.2f  %4d%n", rs.getRow(), rs.getInt("sID"), rs.getString("sName"),
                        rs.getDouble("gpa"), rs.getInt("sizeHS"));
            }
            rs.close();
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();

            // Query 2 실행하고 결과는 적절한 Print문을 이용해 Display
            // Tuple print시 Tuple 번호도 함께 print (예시는 위 "Trigger test 1" 참조)
            rs = stmt.executeQuery("select * from Apply order by sID, cName, major");

            System.out.println("---------query 2---------");
            while (rs.next()) {
                System.out.printf(" %2d  %d  %10s  %15s  %s%n", rs.getRow(), rs.getInt("sID"), rs.getString("cName"),
                        rs.getString("major"), rs.getString("decision"));
            }
            rs.close();
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();
            System.out.println("Trigger test 2");

            // Trigger R4 생성
            stmt.executeUpdate(
                  "create function college_ignore_duplicate() returns trigger as\n"
                + "$$\n"
                + "begin\n"
                +     "IF exists(select * from College where cName = New.cName) THEN\n"
                +         "return null;\n"
                +     "ELSE\n"
                +         "return New;\n"
                +     "END IF;\n"
                + "end;\n"
                + "$$\n"
                +     "language 'plpgsql';\n"
                + "create trigger R4\n"
                +     "before insert\n"
                +     "on College\n"
                +     "for each row\n"
                + "execute procedure college_ignore_duplicate()"
            );

            // Insert문 실행
            stmt.executeUpdate("insert into College values ('UCLA', 'CA', 20000)");
            stmt.executeUpdate("insert into College values ('MIT', 'hello', 10000)");

            // Query 3 실행하고 결과는 적절한 Print문을 이용해 Display
            rs = stmt.executeQuery("select * from College order by cName");

            System.out.println("---------query 3---------");
            while (rs.next()) {
                System.out.printf(" %2d  %10s  %s  %5d%n", rs.getRow(), rs.getString("cName"), rs.getString("state"),
                        rs.getInt("enrollment"));
            }
            rs.close();

            // Tuple print시 Tuple 번호도 함께 print (예시는 위 "Trigger test 1" 참조)
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();

            System.out.println("View test 1");
            // View CSEE 생성
            stmt.executeUpdate(
                  "create view CSEE as\n"
                + "select sID, cName, major\n"
                + "from Apply\n"
                + "where major = 'CS'\n"
                +     "or major = 'EE'"
            );

            // Query 4 실행하고 결과는 적절한 Print문을 이용해 Display
            rs = stmt.executeQuery("select * from CSEE order by sID, cName, major");

            System.out.println("---------query 4---------");
            while (rs.next()) {
                System.out.printf(" %2d  %d  %10s  %15s%n", rs.getRow(), rs.getInt("sID"), rs.getString("cName"),
                        rs.getString("major"));
            }
            rs.close();

            // Tuple print시 Tuple 번호도 함께 print (예시는 위 "Trigger test 1" 참조)
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();

            System.out.println("View test 2");
            // Trigger CSEEinsert 생성
            stmt.executeUpdate(
                 "create function CSEEinsert() returns trigger as\n"
                + "$$\n"
                + "begin\n"
                +     "IF (New.major = 'CS' or New.major = 'EE') THEN\n"
                +         "insert into Apply values (New.sID, New.cName, New.major, null);\n"
                +     "END IF;\n"
                +     "return null;"
                + "end;\n"
                + "$$\n"
                +     "language 'plpgsql';\n"
                + "create trigger CSEEinsert\n"
                +     "instead of insert\n"
                +     "on CSEE\n"
                +     "for each row\n"
                + "execute function CSEEinsert()"
            );

            // Insert문 실행
            stmt.executeUpdate("insert into CSEE values (333, 'UCLA', 'biology')");

            // Query 5 실행하고 결과는 적절한 Print문을 이용해 Display
            rs = stmt.executeQuery("select * from CSEE order by sID, cName, major");

            System.out.println("---------query 5---------");
            while (rs.next()) {
                System.out.printf(" %2d  %d  %10s  %15s%n", rs.getRow(), rs.getInt("sID"), rs.getString("cName"),
                        rs.getString("major"));
            }
            rs.close();

            // Tuple print시 Tuple 번호도 함께 print (예시는 위 "Trigger test 1" 참조)
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();
            // Query 6 실행하고 결과는 적절한 Print문을 이용해 Display
            rs = stmt.executeQuery("select * from Apply order by sID, cName, major");

            System.out.println("---------query 6---------");
            while (rs.next()) {
                System.out.printf(" %2d  %d  %10s  %15s  %s%n", rs.getRow(), rs.getInt("sID"), rs.getString("cName"),
                        rs.getString("major"), rs.getString("decision"));
            }
            rs.close();

            // Tuple print시 Tuple 번호도 함께 print (예시는 위 "Trigger test 1" 참조)
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();
            System.out.println("View test 3");
            // Insert문 실행
            stmt.executeUpdate("insert into CSEE values (333, 'UCLA', 'CS')");

            // Query 7 실행하고 결과는 적절한 Print문을 이용해 Display
            rs = stmt.executeQuery("select * from CSEE order by sID, cName, major");

            System.out.println("---------query 7---------");
            while (rs.next()) {
                System.out.printf(" %2d  %d  %10s  %15s%n", rs.getRow(), rs.getInt("sID"), rs.getString("cName"),
                        rs.getString("major"));
            }
            rs.close();

            // Tuple print시 Tuple 번호도 함께 print (예시는 위 "Trigger test 1" 참조)
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();
            // Query 8 실행하고 결과는 적절한 Print문을 이용해 Display
            rs = stmt.executeQuery("select * from Apply order by sID, cName, major");

            System.out.println("---------query 8---------");
            while (rs.next()) {
                System.out.printf(" %2d  %d  %10s  %15s  %s%n", rs.getRow(), rs.getInt("sID"), rs.getString("cName"),
                        rs.getString("major"), rs.getString("decision"));
            }
            rs.close();
            // Tuple print시 Tuple 번호도 함께 print (예시는 위 "Trigger test 1" 참조)
            stmt.close();
            scan.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
}

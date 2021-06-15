package org.lani.db.assignment3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SqlTest4 {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AjouDB", "ajou_db",
                "ajou")) {
            Scanner scan = new Scanner(System.in);
            System.out.println("SQL Programming Test");

            System.out.println("Recursive test 1");
            // Recursive query 1 실행하고 결과는 적절한 Print문을 이용해 Display
            // Tuple print시 Tuple 번호도 함께 print
            // 예) cName state
            // ------------------------------------
            // 1 Stanford CA
            // 2 MIT MA
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                  "with recursive Ancestor(a, d) as (select parent as a, child as d\n"
                +                                   "from ParentOf\n"
                +                                   "union\n"
                +                                   "select Ancestor.a, ParentOf.child as d\n"
                +                                   "from Ancestor,\n"
                +                                   "ParentOf\n"
                +                                   "where Ancestor.d = ParentOf.parent)\n"
                + "select a\n"
                + "from Ancestor\n"
                + "where d = 'Frank'\n"
                + "order by a"
            );

            System.out.println("-----------------query 1-----------------");
            while (rs.next()) {
                System.out.printf(" %2d  %s%n", rs.getRow(), rs.getString(1));
            }
            rs.close();
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();


            System.out.println("Recursive test 2");
            // Recursive query 2 실행하고 결과는 적절한 Print문을 이용해 Display
            // Tuple print시 Tuple 번호도 함께 print (예시는 위 "Recursive test 1" 참조)
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                "with recursive Superior as (select *\n"
                +                           "from Manager\n"
                +                           "union\n"
                +                           "select S.mID, M.eID\n"
                +                           "from Superior S,\n"
                +                              "Manager M\n"
                +                           "where S.eID = M.mID)\n"
                + "select sum(salary)\n"
                + "from Employee\n"
                + "where ID in (select mgrID\n"
                +               "from Project\n"
                +               "where name = 'X'\n"
                +               "union\n"
                +               "select eID\n"
                +               "from Project,\n"
                +                    "Superior\n"
                +               "where Project.name = 'X'\n"
                +                 "AND Project.mgrID = Superior.mID)"
            );

            System.out.println("-----------------query 2-----------------");
            while (rs.next()) {
                System.out.printf(" %2d  %d%n", rs.getRow(), rs.getInt(1));
            }
            rs.close();
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();


            System.out.println("Recursive test 3");
            // Recursive query 3 실행하고 결과는 적절한 Print문을 이용해 Display
            // Tuple print시 Tuple 번호도 함께 print (예시는 위 "Recursive test 1" 참조)
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                "with recursive FromA(dest, total) as\n"
                +                   "(select dest, cost as total\n"
                +                    "from Flight\n"
                +                    "where orig = 'A'\n"
                +                    "union\n"
                +                    "select F.dest, cost + total as total\n"
                +                    "from FromA FA,\n"
                +                         "Flight F\n"
                +                    "where FA.dest = F.orig)\n"
                + "select *\n"
                + "from FromA\n"
                + "order by total"
            );

            System.out.println("-----------------query 3-----------------");
            while (rs.next()) {
                System.out.printf(" %2d  %3s  %d%n", rs.getRow(), rs.getString(1), rs.getInt(2));
            }
            rs.close();
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();


            System.out.println("Recursive test 4");
            // Recursive query 4 실행하고 결과는 적절한 Print문을 이용해 Display
            // Tuple print시 Tuple 번호도 함께 print (예시는 위 "Recursive test 1" 참조)
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                "with recursive FromA(dest, total) as\n"
                +                   "(select dest, cost as total\n"
                +                    "from Flight\n"
                +                    "where orig = 'A'\n"
                +                    "union\n"
                +                    "select F.dest, cost + total as total\n"
                +                    "from FromA FA,\n"
                +                         "Flight F\n"
                +                    "where FA.dest = F.orig)\n"
                + "select min(total) from FromA where dest = 'B'"
            );

            System.out.println("-----------------query 4-----------------");
            while (rs.next()) {
                System.out.printf(" %2d  %d%n", rs.getRow(), rs.getInt(1));
            }
            rs.close();
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();


            System.out.println("OLAP test 1");
            // OLAP query 1 실행하고 결과는 적절한 Print문을 이용해 Display
            // Tuple print시 Tuple 번호도 함께 print (예시는 위 "Recursive test 1" 참조)
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                  "select storeID, itemID, custID, sum(price)\n"
                + "from Sales\n"
                + "group by cube (storeID, itemID, custID)\n"
                + "order by storeID, itemID, custID"
            );

            System.out.println("-----------------query 5-----------------");
            while (rs.next()) {
                System.out.printf(" %2d  %6s  %5s  %5s  %d%n", rs.getRow(), rs.getString(1),
                                                rs.getString(2), rs.getString(3), rs.getInt(4));
            }
            rs.close();
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();


            System.out.println("OLAP test 2");
            // OLAP query 2 실행하고 결과는 적절한 Print문을 이용해 Display
            // Tuple print시 Tuple 번호도 함께 print (예시는 위 "Recursive test 1" 참조)
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                  "select storeID, itemID, custID, sum(price)\n"
                + "from Sales F\n"
                + "group by itemID, cube (storeID, custID)\n"
                + "order by storeID, itemID, custID"
            );

            System.out.println("-----------------query 6-----------------");
            while (rs.next()) {
                System.out.printf(" %2d  %6s  %5s  %5s  %d%n", rs.getRow(), rs.getString(1),
                                                rs.getString(2), rs.getString(3), rs.getInt(4));
            }
            rs.close();
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();


            System.out.println("OLAP test 3");
            // OLAP query 3 실행하고 결과는 적절한 Print문을 이용해 Display
            // Tuple print시 Tuple 번호도 함께 print (예시는 위 "Recursive test 1" 참조)
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                  "select storeId, itemID, custid, sum(price)\n"
                + "from sales F\n"
                + "group by rollup (storeId, itemid, custid)\n"
                + "order by storeID, itemid, custid"
            );

            System.out.println("-----------------query 7-----------------");
            while (rs.next()) {
                System.out.printf(" %2d  %6s  %5s  %5s  %d%n", rs.getRow(), rs.getString(1),
                                                rs.getString(2), rs.getString(3), rs.getInt(4));
            }
            rs.close();
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();


            System.out.println("OLAP test 4");
            // OLAP query 4 실행하고 결과는 적절한 Print문을 이용해 Display
            // Tuple print시 Tuple 번호도 함께 print (예시는 위 "Recursive test 1" 참조)
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                  "select state, county, city, sum(price)\n"
                + "from Sales F,\n"
                +     "Store S\n"
                + "where F.storeID = S.storeID\n"
                + "group by rollup (state, county, city)\n"
                + "order by state, county, city"
            );

            System.out.println("-----------------query 8-----------------");
            while (rs.next()) {
                System.out.printf(" %2d  %4s  %15s  %18s  %d%n", rs.getRow(), rs.getString(1),
                                                rs.getString(2), rs.getString(3), rs.getInt(4));
            }
            rs.close();
            scan.close();
            stmt.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
}

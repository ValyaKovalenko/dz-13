package lesson25;

import org.example.Session;
import org.example.utils.MySQLDriver;
import org.testng.annotations.Test;

import java.sql.*;

public class JDBCTest
{
       @Test
        public void test () {
//INSERT
           try {
               PreparedStatement pstmt = Session.get().mysql().preparedStatement
                       ("INSERT INTO homework_user_data(" +
                               "first_name," +
                               "last_name) " +
                               "VALUES(?,?)");
               //Set values
               pstmt.setString(1, "Brad");
               pstmt.setString(2, "Pitt");
               //Insert
               pstmt.executeUpdate();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }

//SELECT
           try {
               PreparedStatement statement = Session.get().mysql().preparedStatement(
                       "SELECT * FROM homework_user_data WHERE first_name=?;");
               statement.setString(1, "Brad");
               ResultSet resultSet = statement.executeQuery();
               while (resultSet.next())
                   System.out.println("Select: " + resultSet.getString("first_name"));
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }

//UPDATE
           try {
               PreparedStatement pstmtUpd = Session.get().mysql().preparedStatement(
                       "UPDATE homework_user_data SET login=? WHERE first_name=? AND last_name=?");
               //Search by values
               pstmtUpd.setString(1, "bpitt");
               pstmtUpd.setString(2, "Brad");
               pstmtUpd.setString(3, "Pitt");
               //Update
               pstmtUpd.executeUpdate();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }

//DELETE
           try {
               PreparedStatement pstmtDel = Session.get().mysql().preparedStatement(
                       "DELETE FROM homework_user_data WHERE first_name=? AND last_name=?");
               //Search by values
               pstmtDel.setString(1, "Brad");
               pstmtDel.setString(2, "Pitt");
               //Delete
               int del = pstmtDel.executeUpdate();
               System.out.println("Number of deleted records: " + del);
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }
}

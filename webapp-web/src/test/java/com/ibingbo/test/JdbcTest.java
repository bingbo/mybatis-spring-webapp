package com.ibingbo.test;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.WebRowSet;

import org.junit.Test;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.WebRowSetImpl;

/**
 * JdbcTest
 *
 * @author zhangbingbing
 * @date 18/1/16
 */
public class JdbcTest {

    @Test
    public void testA() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "123456");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user ");
        while (resultSet.next()) {
            System.out.println("id: " + resultSet.getInt("id") + " name: " + resultSet.getString("name") + " password: "
                    + "" + resultSet.getString("password") + " email: " + resultSet.getString("email"));
        }
    }

    @Test
    public void testB() {
        try {
            CachedRowSet crs = new CachedRowSetImpl();
            crs.setUsername("root");
            crs.setPassword("123456");
            crs.setUrl("jdbc:mysql://localhost:3306/test");
            crs.setCommand("select * from user");
            crs.execute();


            while (crs.next()) {

                System.out.println("id: " + crs.getInt("id") + " name: " + crs.getString("name") + " password: "
                        + "" + crs.getString("password") + " email: " + crs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testC() {
        try (WebRowSet rowSet = new WebRowSetImpl()) {
            rowSet.setUsername("root");
            rowSet.setPassword("123456");
            rowSet.setUrl("jdbc:mysql://localhost:3306/test");
            rowSet.setCommand("select * from user");
            rowSet.execute();
            FileOutputStream outputStream = new FileOutputStream("userList.xml");
            rowSet.writeXml(outputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

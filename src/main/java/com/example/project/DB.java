package com.example.project;
import java.sql.*;
public class DB {
    public static Connection con;
    public static void initialize() throws SQLException {
        //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        con=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:orcl", "Library_dba", "library12345");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.ConnectionSQL;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author XUÂN THÀNH
 */
public class DatabaseHelper {
    public static Connection openConnection()throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://localhost;database=FPL_DaoTao;";
        String username = "FPL_DB";
        String password = "123";
        Connection con = DriverManager.getConnection(connectionUrl, username, password);
        return con;
    }
}

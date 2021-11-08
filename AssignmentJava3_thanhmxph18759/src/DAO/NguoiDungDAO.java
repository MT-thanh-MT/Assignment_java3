/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Model.ConnectionSQL.DatabaseHelper;
import Model.NguoiDung;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
/**
 *
 * @author XUÂN THÀNH
 */
public class NguoiDungDAO {

    public static NguoiDung checkLogin(String username, String password) throws Exception {
        String sql = "select _username,_password,_role from USERS "
                + "where _username = ? and _password = ?";
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
            ){
                pstm.setString(1, username);
                pstm.setString(2, password);
                
                try(ResultSet rs = pstm.executeQuery()){
                    if(rs.next()){
                        NguoiDung nd = new NguoiDung();
                        nd.setUser(username);
                        nd.setVaiTro(rs.getString("_role"));
                        return nd;
                    }
                }
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interface.QLSVInterface;
import Model.ConnectionSQL.DatabaseHelper;
import Model.SinhVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author XUÂN THÀNH
 */
public class QuanLySinhVienDAO implements QLSVInterface<SinhVien, String> {

    @Override
    public boolean insert(SinhVien e) throws Exception {
        String sql = "INSERT INTO [dbo].[STUDENTS]([_MASV],[_HoTen],[_Email],[_SDT],[_GioiTinh],[_DiaChi],[_Hinh]) VALUES(?,?,?,?,?,?,?)";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(1, e.getMASV());
            pstm.setString(2, e.getHoTen());
            pstm.setString(3, e.getEmail());
            pstm.setString(4, e.getSDT());
            pstm.setInt(5, e.getGioiTinh());
            pstm.setString(6, e.getDiaChi());
            if (e.getHinh() == null) {
                Blob blob = null;
                pstm.setBlob(7, blob);
            } else {
                Blob blob = new javax.sql.rowset.serial.SerialBlob(e.getHinh());
                pstm.setBlob(7, blob);
            }

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(SinhVien e) throws Exception {
        String sql = "UPDATE [dbo].[STUDENTS]SET [_HoTen] = ?,[_Email] = ?,"
                + "[_SDT] = ?,[_GioiTinh] = ?,[_DiaChi] = ?,[_Hinh] = ? WHERE [_MASV] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(7, e.getMASV());
            pstm.setString(1, e.getHoTen());
            pstm.setString(2, e.getEmail());
            pstm.setString(3, e.getSDT());
            pstm.setInt(4, e.getGioiTinh());
            pstm.setString(5, e.getDiaChi());
            if (e.getHinh() == null) {
                Blob blob = null;
                pstm.setBlob(6, blob);
            } else {
                Blob blob = new javax.sql.rowset.serial.SerialBlob(e.getHinh());
                pstm.setBlob(6, blob);
            }

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(String maSV) throws Exception {
        String sql = "DELETE FROM [dbo].[STUDENTS]  WHERE [_MASV] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);) {

            pstm.setString(1, maSV);

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public SinhVien timTheoMa(String maSV) throws Exception {
        String sql = "SELECT * FROM [dbo].[STUDENTS]  WHERE [_MASV] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);) {

            pstm.setString(1, maSV);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    SinhVien sv = createNewSinhVien(rs);
                    

                    return sv;
                }
            }
            return null;
        }
    }

    public SinhVien createNewSinhVien(final ResultSet rs) throws SQLException {
        SinhVien sv = new SinhVien();
        sv.setMASV(rs.getString("_MASV"));
        sv.setHoTen(rs.getString("_HoTen"));
        sv.setEmail(rs.getString("_Email"));
        sv.setSDT(rs.getString("_SDT"));
        sv.setGioiTinh(rs.getInt("_GioiTinh"));
        sv.setDiaChi(rs.getString("_DiaChi"));
        Blob blod = rs.getBlob("_Hinh");
        if (blod != null){
            sv.setHinh(blod.getBytes(0, (int) blod.length()));
        }
        return sv;
    }

    @Override
    public ArrayList<SinhVien> getDanhSachSV() throws Exception {
        String sql = "SELECT * FROM [dbo].[STUDENTS] ";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);) {

            
            try (ResultSet rs = pstm.executeQuery()) {
                ArrayList<SinhVien> list = new ArrayList<>();
                while (rs.next()) {
                    SinhVien sv = createNewSinhVien(rs);
                    
                    list.add(sv);
                    
                }
                return list;
            }
            
        }
    }

}

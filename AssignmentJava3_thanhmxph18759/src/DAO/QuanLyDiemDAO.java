/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interface.QLDInterface;
import Model.ConnectionSQL.DatabaseHelper;
import Model.SinhVien;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author XUÂN THÀNH
 */
public class QuanLyDiemDAO implements QLDInterface<SinhVien, String> {

    

    @Override
    public boolean insert(SinhVien e) throws Exception {
        String sql = "INSERT INTO [dbo].[GRADE]([_MASV],[_TiengAnh],[_TinHoc],[_GDTC])  VALUES(?,?,?,?)";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(1, e.getMASV());
            pstm.setFloat(2, e.getTiengAnh());
            pstm.setFloat(3, e.getTinHoc());
            pstm.setFloat(4, e.getGDTC());

            return pstm.executeUpdate() > 0;
        }

    }

    @Override
    public boolean update(SinhVien e) throws Exception {

        String sql = "UPDATE [dbo].[GRADE]"
                + " SET [_TiengAnh] = ?,[_TinHoc] = ?,[_GDTC] = ?"
                + " WHERE [_MASV] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(4, e.getMASV());
            pstm.setFloat(1, e.getTiengAnh());
            pstm.setFloat(2, e.getTinHoc());
            pstm.setFloat(3, e.getGDTC());

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(String maSV) throws Exception {
        String sql = "DELETE FROM [dbo].[GRADE]  WHERE [_MASV] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);) {

            pstm.setString(1, maSV);

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public SinhVien timTheoMa(String maSV) throws Exception {
        String sql = "SELECT _ID, GRADE._MASV, _HoTen,_TiengAnh,_TinHoc,_GDTC  "
                + "FROM GRADE JOIN STUDENTS ON GRADE._MASV = STUDENTS._MASV  WHERE GRADE._MASV = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);) {

            pstm.setString(1, maSV);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    SinhVien sv = createSinhvien(maSV, rs);

                    return sv;
                }
            }
            return null;
        }
    }

    public SinhVien createSinhvien(String maSV, final ResultSet rs) throws NumberFormatException, SQLException {
        SinhVien sv = new SinhVien();
        sv.setID(rs.getInt("_ID"));
        sv.setMASV(maSV);
        sv.setHoTen(rs.getString("_HoTen"));
        sv.setTiengAnh(Float.parseFloat(rs.getString("_TiengAnh")));
        sv.setTinHoc(Float.parseFloat(rs.getString("_TinHoc")));
        sv.setGDTC(Float.parseFloat(rs.getString("_GDTC")));
        return sv;
    }

    @Override
    public ArrayList<SinhVien> getDanhSachSV() throws Exception {
        String sql = "SELECT _ID, GRADE._MASV, _HoTen,_TiengAnh,_TinHoc,_GDTC  "
                + "FROM GRADE JOIN STUDENTS ON GRADE._MASV = STUDENTS._MASV";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery();) {
            
            ArrayList<SinhVien> list = new ArrayList<>();
            while (rs.next()) {
                SinhVien sv = createSinhvien(rs.getString("_MASV"), rs);
                
                list.add(sv);
            }
            return list;
        }
    }

    @Override
    public ArrayList<SinhVien> getTopDanhSachSV(int top) throws Exception {
        String sql = "select top " + top + "_ID, _HoTen,GRADE.* from GRADE join STUDENTS on GRADE._MASV = STUDENTS._MASV\n" +
                     " order by (_TiengAnh + _TinHoc + _GDTC)/3 desc";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery();) {
            
            ArrayList<SinhVien> list = new ArrayList<>();
            while (rs.next()) {
                SinhVien sv = createSinhvien(rs.getString("_MASV"), rs);
                
                list.add(sv);
            }
            return list;
        }
    }

    @Override
    public SinhVien kiemTraSVCoTonTai(String maSV) throws Exception {
        String sql = "select _MASV from STUDENTS WHERE _MASV = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);) {

            pstm.setString(1, maSV);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    SinhVien sv = new SinhVien();
                    sv.setMASV(maSV);

                    return sv;
                }
            }
            return null;
        }
    }

}

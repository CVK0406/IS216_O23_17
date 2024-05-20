package DAO;

import Model.KieuPhong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KieuPhongDAO {
    Connection con = DataBaseConnection.getConnection();
    
    public boolean ThemKieuPhong(KieuPhong kieuPhong){
        String sql = "INSERT INTO LOAIPHONG(MALOAIPHG, KIEUPHONG, KIEUGIUONG, DONGIA) VALUES(?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, kieuPhong.getMaLoaiPhg());
            preparedStatement.setString(2, kieuPhong.getKieuPhong());
            preparedStatement.setInt(3, kieuPhong.getKieuGiuong());
            preparedStatement.setInt(4, kieuPhong.getDonGia());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        
        return false;
    }
    
    public boolean XoaKieuPhong(KieuPhong kieuPhong){
        String sql = "DELETE FROM LOAIPHONG WHERE MALOAIPHG = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, kieuPhong.getMaLoaiPhg());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        
        return false;
    }
    
    public boolean SuaKieuPhong(KieuPhong kieuPhongCu, KieuPhong kieuPhongMoi){
        String sql = "UPDATE LOAIPHONG SET MALOAIPHG = ?, KIEUPHONG = ?, KIEUGIUONG = ?, DONGIA = ? WHERE MALOAIPHG = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, kieuPhongMoi.getMaLoaiPhg());
            preparedStatement.setString(2, kieuPhongMoi.getKieuPhong());
            preparedStatement.setInt(3, kieuPhongMoi.getKieuGiuong());
            preparedStatement.setInt(4, kieuPhongMoi.getDonGia());
            preparedStatement.setString(5, kieuPhongCu.getMaLoaiPhg());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        
        return false;
    }
    
    public ArrayList<KieuPhong> getListKieuPhong(){
        ArrayList<KieuPhong> listKieuPhong = new ArrayList<>();
        String sql = "SELECT MALOAIPHG, KIEUPHONG, KIEUGIUONG, DONGIA FROM LOAIPHONG";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                KieuPhong kieuPhong = new KieuPhong();
                kieuPhong.setMaLoaiPhg(resultSet.getString("MaLoaiPhg"));
                kieuPhong.setKieuPhong(resultSet.getString("KieuPhong"));
                kieuPhong.setKieuGiuong(resultSet.getInt("KieuGiuong"));
                kieuPhong.setDonGia(resultSet.getInt("DonGia"));
                listKieuPhong.add(kieuPhong);
            }
        } catch (SQLException e) {
        }
        
        return listKieuPhong;
    }
}

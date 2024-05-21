package DAO;

import Model.ThongTinPhong;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;

public class ThongTinPhongDAO {
   Connection con = DataBaseConnection.getConnection();
    
    public boolean ThemChiTietTTPhong(ThongTinPhong ttPhong){
       String sql = "INSERT INTO PHONG(MAPHG, MALOAIPHG, MoTa, TINHTRANG)"
               + "VALUES(?,"
               + "(SELECT MALOAIPHG FROM LOAIPHONG WHERE KIEUPHONG = ? AND KIEUGIUONG = ?),"
               + "?,1)";
       try {
           PreparedStatement preparedStatement = con.prepareStatement(sql);
           preparedStatement.setString(1, ttPhong.getMaPhg());
           preparedStatement.setString(2, ttPhong.getKieuPhong());
           preparedStatement.setInt(3, ttPhong.getKieuGiuong());
           preparedStatement.setString(4, ttPhong.getMoTa());
           return preparedStatement.executeUpdate() > 0;
       } catch (SQLException e) {
       }
       return false;
   }
   
    public boolean XoaChiTietTTPhong(ThongTinPhong ttPhong){
        String sql = "DELETE FROM PHONG WHERE MAPHONG = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, ttPhong.getMaPhg());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }       
        return false;
    }
    
    public boolean SuaChiTietTTPhong(ThongTinPhong ttPhongMoi, ThongTinPhong ttPhongCu){
        String sql = "UPDATE PHONG SET MAPHG = ?,"
                + "MALOAIPHG = (SELECT MALOAIPHG FROM LOAIPHONG WHERE KIEUPHONG = ? AND KIEUGIUONG = ?),"
                + "MOTA = ?, TINHTRANG = 1"
                + "WHERE MAPHG = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, ttPhongMoi.getMaPhg());
            preparedStatement.setString(2, ttPhongMoi.getKieuPhong());
            preparedStatement.setInt(3, ttPhongMoi.getKieuGiuong());
            preparedStatement.setString(4, ttPhongMoi.getMoTa());
            preparedStatement.setString(5, ttPhongCu.getMaPhg());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public ArrayList<ThongTinPhong> getListChiTietTTPhong(){
        ArrayList<ThongTinPhong> listTTPhong = new ArrayList<>();
        String sql = "SELECT P.MAPHG, P.MoTa, LP.KIEUPHONG, LP.KIEUGIUONG, LP.DONGIA "
                + "FROM PHONG P, LOAIPHONG LP WHERE P.MALOAIPHG = LP.MALOAIPHG";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ThongTinPhong ttPhong = new ThongTinPhong();
                ttPhong.setMaPhg(resultSet.getString("MaPhg"));
                ttPhong.setKieuPhong(resultSet.getString("KieuPhong"));
                ttPhong.setKieuGiuong(resultSet.getInt("KieuGiuong"));
                ttPhong.setDonGia(resultSet.getInt("DonGia"));
                ttPhong.setMoTa(resultSet.getString("MoTa"));
                listTTPhong.add(ttPhong);
            }
        } catch (SQLException e) {
        }
        return listTTPhong;
    }
    

    public ArrayList<ThongTinPhong> getListTTPhongTrong(String kieuPhong, String kieuGiuong, Date ngayNhan, Date ngayTra) throws SQLException{
        
        con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        ArrayList<ThongTinPhong> listTTPhong = new ArrayList<>();
        String sql = "SELECT P.MAPHG, P.MoTa, LP.KIEUPHONG, LP.KIEUGIUONG, LP.DONGIA "
                + "FROM PHONG P, LOAIPHONG LP "+
                "WHERE P.MALOAIPHG = LP.MALOAIPHG AND LP.KIEUPHONG Like ? AND TO_CHAR(LP.KIEUGIUONG) LIKE ? AND MAPHG IN (SELECT * FROM TABLE (GETAVAILABLEROOM(?,?)))";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,"%"+kieuPhong+"%");
            preparedStatement.setString(2,"%"+kieuGiuong+"%");
            preparedStatement.setDate(3, new Date(ngayNhan.getTime()));
            preparedStatement.setDate(4, new Date(ngayTra.getTime()));            
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ThongTinPhong ttPhong = new ThongTinPhong();
                ttPhong.setMaPhg(resultSet.getString("MaPhg"));
                ttPhong.setKieuPhong(resultSet.getString("KieuPhong"));
                ttPhong.setKieuGiuong(resultSet.getInt("KieuGiuong"));
                ttPhong.setDonGia(resultSet.getInt("DonGia"));
                ttPhong.setMoTa(resultSet.getString("MoTa"));
                listTTPhong.add(ttPhong);
            }
        } catch (SQLException e) {
        }
        return listTTPhong;
    }

    public ArrayList<ThongTinPhong> getKieuPhong(){
        ArrayList<ThongTinPhong> listKieuPhong = new ArrayList<>();
        String sql = "SELECT DISTINCT KIEUPHONG FROM LOAIPHONG";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ThongTinPhong ttKieuPhong = new ThongTinPhong();
                ttKieuPhong.setKieuPhong(resultSet.getString("KieuPhong"));
                listKieuPhong.add(ttKieuPhong);
            }
        } catch (SQLException e) {
        }
        return listKieuPhong;
    }
    
    public ArrayList<ThongTinPhong> getKieuGiuong(String KieuPhong){
        ArrayList<ThongTinPhong> listKieuGiuong = new ArrayList<>();
        String sql = "SELECT DISTINCT KIEUGIUONG FROM LOAIPHONG WHERE KIEUPHONG = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, KieuPhong);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ThongTinPhong ttKieuGiuong = new ThongTinPhong();
                ttKieuGiuong.setKieuGiuong(resultSet.getInt("KieuGiuong"));
                listKieuGiuong.add(ttKieuGiuong);
            }
        } catch (SQLException e) {
        }
        return listKieuGiuong;
    }
    
    public ArrayList<ThongTinPhong> getListPhongDangSD(){
        ArrayList<ThongTinPhong> listMaPhg = new ArrayList<>();
        String sql = "SELECT MAPHG FROM CHITIETDATPHONG, PHIEUDATPHONG WHERE TTNHANPHONG = 1 AND CHITIETDATPHONG.MADATPHONG = PHIEUDATPHONG.MADATPHONG AND TRUNC(NGAYTRA) >= TRUNC(SYSDATE)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ThongTinPhong ttMaPhg = new ThongTinPhong();
                ttMaPhg.setMaPhg(resultSet.getString("MaPhg"));
                listMaPhg.add(ttMaPhg);
            }
        } catch (SQLException e) {
        }
        return listMaPhg;
    }
    
        public ArrayList<ThongTinPhong> getListPhongDangSDByMaKH(int MaKH){
        ArrayList<ThongTinPhong> listMaPhg = new ArrayList<>();
        String sql = "SELECT MAPHG FROM CHITIETDATPHONG, PHIEUDATPHONG WHERE TTNHANPHONG = 1 AND CHITIETDATPHONG.MADATPHONG = PHIEUDATPHONG.MADATPHONG"
                + " AND TRUNC(NGAYTRA) >= TRUNC(SYSDATE) AND PHIEUDATPHONG.MAKH = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, MaKH);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ThongTinPhong ttMaPhg = new ThongTinPhong();
                ttMaPhg.setMaPhg(resultSet.getString("MaPhg"));
                listMaPhg.add(ttMaPhg);
            }
        } catch (SQLException e) {
        }
        return listMaPhg;
    }
    
    public int GiaPhong(String KieuPhong, int KieuGiuong){
        int temp = 0;
        String sql = "SELECT DONGIA FROM LOAIPHONG WHERE KIEUPHONG = ? AND KIEUGIUONG = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, KieuPhong);
            preparedStatement.setInt(2, KieuGiuong);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                temp = resultSet.getInt("DonGia");
            }
            
        } catch (SQLException e) {
        }
        return temp;
    }
}

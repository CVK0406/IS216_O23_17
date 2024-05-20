package DAO;

import Model.DanhMucDichVu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DanhMucDichVuDAO {

    Connection con = DataBaseConnection.getConnection();

    public boolean ThemTTDichVu(DanhMucDichVu danhMucDichVu) {
        String sql = "INSERT INTO DANHMUCDICHVU(TenDV, DonGia) VALUES(?,?)";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, danhMucDichVu.getTenDV());
            preparedStatement.setInt(2, danhMucDichVu.getDonGia());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        
        return false;
    }

    public boolean XoaTTDichVu(DanhMucDichVu danhMucDichVu) {
        String sql = "UPDATE DANHMUCDICHVU SET ACTIVE = 0 WHERE MaDV = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, danhMucDichVu.getMaDV());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }

        return false;
    }

    public boolean SuaTTDichVu(DanhMucDichVu danhMucDichVu) {
        String sql = "UPDATE DANHMUCDICHVU SET TenDV = ?, DonGia = ? WHERE MaDV = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, danhMucDichVu.getTenDV());
            ps.setInt(2, danhMucDichVu.getDonGia());
            ps.setInt(3, danhMucDichVu.getMaDV());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        
        return false;
    }

    public ArrayList<DanhMucDichVu> getListDichVu() {
        ArrayList<DanhMucDichVu> listDichVu = new ArrayList<>();
        String sql = "SELECT MADV, TENDV, DONGIA FROM DANHMUCDICHVU WHERE ACTIVE = 1";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DanhMucDichVu DichVu = new DanhMucDichVu();
                DichVu.setMaDV(rs.getInt("MaDV"));
                DichVu.setTenDV(rs.getString("TenDV"));
                DichVu.setDonGia(rs.getInt("DonGia"));
                listDichVu.add(DichVu);
            }
        } catch (SQLException e) {
        }
        
        return listDichVu;
    }

    public ArrayList<DanhMucDichVu> getListTenDV() {
        ArrayList<DanhMucDichVu> listTenDV = new ArrayList<>();
        String sql = "SELECT TENDV FROM DANHMUCDICHVU WHERE ACTIVE = 1";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DanhMucDichVu ttTenDV = new DanhMucDichVu();
                ttTenDV.setTenDV((rs.getString("TenDV")));
                listTenDV.add(ttTenDV);
            }
        } catch (SQLException e) {
        }
        
        return listTenDV;
    }

    public int getGiaDV(String tenDV) {
        String sql = "SELECT DONGIA FROM DANHMUCDICHVU WHERE TENDV = ? AND ACTIVE = 1";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenDV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("DonGia");
            }
        } catch (SQLException e) {
        }
        
        return 0;
    }
}

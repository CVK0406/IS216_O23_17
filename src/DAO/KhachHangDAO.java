package DAO;

import Model.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KhachHangDAO {

    Connection con = DataBaseConnection.getConnection();

    public int getCurrentMaKH() {
        int result = -1;
        String sql = "SELECT MAKH_SEQ.NEXTVAL AS VAL FROM DUAL ";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("VAL");
                return result;
            }
        } catch (SQLException e) {
        }
        
        return result;
    }

    public boolean ThemKhachHang(KhachHang khachHang) {
        String sql = "INSERT INTO KHACHHANG(TenKH, CCCD, GioiTinh, SDT) VALUES(?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, khachHang.getTenKH());
            preparedStatement.setString(2, khachHang.getCCCD());
            preparedStatement.setString(3, khachHang.getGioiTinh());
            preparedStatement.setString(4, khachHang.getSDT());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        
        return false;
    }

    public boolean XoaKhachHang(KhachHang khachHang) {
        String sql = "UPDATE KHACHHANG SET ACTIVE = 0 WHERE MAKH = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, khachHang.getMaKH());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        
        return false;
    }

    public boolean SuaKhachHang(KhachHang khachHang) {
        String sql = "UPDATE KHACHHANG SET TenKH = ?, CCCD = ?, GioiTinh = ?, SDT = ? WHERE MaKH = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(5, khachHang.getMaKH());
            preparedStatement.setString(1, khachHang.getTenKH());
            preparedStatement.setString(2, khachHang.getCCCD());
            preparedStatement.setString(3, khachHang.getGioiTinh());
            preparedStatement.setString(4, khachHang.getSDT());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        
        return false;
    }

    public ArrayList<KhachHang> getFilterListKhachHang(KhachHang iKhachHang) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT MAKH, TENKH, CCCD, GioiTinh, SDT FROM KHACHHANG WHERE UPPER(TENKH) LIKE ? AND UPPER(CCCD) LIKE ? AND UPPER(SDT) LIKE ? AND ACTIVE = 1 ";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "%" + iKhachHang.getTenKH().toUpperCase() + "%");
            preparedStatement.setString(2, "%" + iKhachHang.getCCCD().toUpperCase() + "%");
            preparedStatement.setString(3, "%" + iKhachHang.getSDT().toUpperCase() + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(resultSet.getInt("MaKH"));
                khachHang.setTenKH(resultSet.getString("TenKH"));
                khachHang.setCCCD(resultSet.getString("CCCD"));
                khachHang.setGioiTinh(resultSet.getString("GioiTinh"));
                khachHang.setSDT(resultSet.getString("SDT"));
                list.add(khachHang);
            }
        } catch (SQLException e) {
        }
        
        return list;
    }

    public ArrayList<KhachHang> getListKhachHang() {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT MAKH, TENKH, CCCD, GioiTinh, SDT FROM KHACHHANG WHERE ACTIVE = 1";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(resultSet.getInt("MaKH"));
                khachHang.setTenKH(resultSet.getString("TenKH"));
                khachHang.setCCCD(resultSet.getString("CCCD"));
                khachHang.setGioiTinh(resultSet.getString("GioiTinh"));
                khachHang.setSDT(resultSet.getString("SDT"));
                list.add(khachHang);
            }
        } catch (SQLException e) {
        }
        
        return list;
    }

    public KhachHang getKhachHangByMaKH(int MAKH) {
        KhachHang khachHang = new KhachHang();
        String sql = "SELECT MAKH, TENKH, CCCD, GioiTinh, SDT FROM KHACHHANG WHERE MAKH = ? AND ACTIVE = 1";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, MAKH);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                khachHang.setMaKH(resultSet.getInt("MaKH"));
                khachHang.setTenKH(resultSet.getString("TenKH"));
                khachHang.setCCCD(resultSet.getString("CCCD"));
                khachHang.setGioiTinh(resultSet.getString("GioiTinh"));
                khachHang.setSDT(resultSet.getString("SDT"));
                return khachHang;
            }
        } catch (SQLException e) {
        }
        
        return null;
    }

}

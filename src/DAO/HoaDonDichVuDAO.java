package DAO;

import Model.HoaDonDichVu;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HoaDonDichVuDAO {

    static Connection con = DataBaseConnection.getConnection();

    public boolean ThemDichVuPhong(HoaDonDichVu hoaDonDichVu, int madv) {
        String sql = "{CALL INSERT_DON_DV(?,?,?,1)}";

        try {
            CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setString(1, hoaDonDichVu.getMaPHG());
            callableStatement.setInt(2, madv);
            callableStatement.setInt(3, hoaDonDichVu.getSoLuong());
            return callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }

        return false;
    }

    public boolean XoaDichVuPhong(HoaDonDichVu hoaDonDichVu, int madv) {
        String sql = "{Call DELETE_DON_DV(?,?)}";

        try {
            CallableStatement ps = con.prepareCall(sql);
            ps.setString(1, hoaDonDichVu.getMaPHG());
            ps.setInt(2, madv);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
        }

        return false;
    }

    public boolean SuaDichVuPhong(HoaDonDichVu hoaDonDichVu) {
        return false;
    }

    public ArrayList<HoaDonDichVu> getListChiTietHDDV(String maPhong) {
        ArrayList<HoaDonDichVu> listChiTietHDDV = new ArrayList<>();
        String sql = "SELECT TENDV, THOIGIANDAT, SOLUONG, DONGIA FROM HOADONDV, PHIEUDATPHONG, DANHMUCDICHVU WHERE HOADONDV.MADV = DANHMUCDICHVU.MADV AND PHIEUDATPHONG.MADATPHONG = HOADONDV.MADATPHONG AND PHIEUDATPHONG.TTNHANPHONG = 1 AND MAPHG = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, maPhong);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                HoaDonDichVu hddv = new HoaDonDichVu();
                hddv.setTenDV(resultSet.getString("TENDV"));
                hddv.setNgaySD(resultSet.getDate("THOIGIANDAT"));
                hddv.setSoLuong(resultSet.getInt("SOLUONG"));
                hddv.setDonGia(resultSet.getInt("DONGIA"));
                listChiTietHDDV.add(hddv);
            }
        } catch (SQLException e) {
        }

        return listChiTietHDDV;
    }
}

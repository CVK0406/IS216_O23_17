package DAO;

import Model.NhanVien;
import Model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienDAO {

    Connection con = DataBaseConnection.getConnection();

    public int getCurrentMaNV() {
        int result = -1;
        String sql = "SELECT MANV_SEQ.NEXTVAL AS VAL FROM DUAL ";

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

    public boolean ThemNhanVien(NhanVien nhanVien) {
        String sql = "INSERT INTO NhanVien(TenNV, CCCD, NgaySinh, SDT, GioiTinh, NgayVL, ChucVu) VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, nhanVien.getTenNV());
            preparedStatement.setString(2, nhanVien.getCCCD());
            preparedStatement.setDate(3, new Date(nhanVien.getNgaySinh().getTime()));
            preparedStatement.setString(4, nhanVien.getSDT());
            preparedStatement.setString(5, nhanVien.getGioiTinh());
            preparedStatement.setDate(6, new Date(nhanVien.getNgayVaoLam().getTime()));
            preparedStatement.setString(7, nhanVien.getChucVu());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }

        return false;
    }

    public boolean XoaNhanVien(int MaNV) {
        String sql = "UPDATE NHANVIEN SET ACTIVE = 0 WHERE MaNV = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, MaNV);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        
        return false;
    }

    public boolean SuaNhanVien(NhanVien nhanVien) {
        String sql = "UPDATE NHANVIEN SET TENNV = ?, CCCD = ?, NGAYSINH = ?, SDT = ?, GIOITINH = ?, NGAYVL = ?, CHUCVU = ? WHERE MANV = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, nhanVien.getTenNV());
            preparedStatement.setString(2, nhanVien.getCCCD());
            preparedStatement.setDate(3, new Date(nhanVien.getNgaySinh().getTime()));
            preparedStatement.setString(4, nhanVien.getSDT());
            preparedStatement.setString(5, nhanVien.getGioiTinh());
            preparedStatement.setDate(6, new Date(nhanVien.getNgayVaoLam().getTime()));
            preparedStatement.setString(7, nhanVien.getChucVu());
            preparedStatement.setInt(8, nhanVien.getMaNV());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }

        return false;
    }

    public ArrayList<NhanVien> getListNhanVien() {
        ArrayList<NhanVien> listNhanVien = new ArrayList<>();
        String sql = "SELECT MANV, TENNV, CCCD, NGAYSINH, SDT, GIOITINH, NGAYVL, CHUCVU FROM NHANVIEN WHERE ACTIVE = 1";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(resultSet.getInt("MaNV"));
                nhanVien.setTenNV(resultSet.getString("TenNV"));
                nhanVien.setCCCD(resultSet.getString("CCCD"));
                nhanVien.setNgaySinh(resultSet.getDate("NgaySinh"));
                nhanVien.setSDT(resultSet.getString("SDT"));
                nhanVien.setGioiTinh(resultSet.getString("GioiTinh"));
                nhanVien.setNgayVaoLam(resultSet.getDate("NgayVL"));
                nhanVien.setChucVu(resultSet.getString("ChucVu"));
                listNhanVien.add(nhanVien);
            }
        } catch (SQLException e) {
        }
        
        return listNhanVien;
    }

    public ArrayList<NhanVien> TimKiemNhanVien(NhanVien nhanVien) {
        ArrayList<NhanVien> listNhanVien = new ArrayList<>();
        String sql = "SELECT MANV, TENNV, CCCD, NGAYSINH, SDT, GIOITINH, NGAYVL, CHUCVU FROM NHANVIEN WHERE UPPER(TENNV) LIKE ? "
                + "AND UPPER(CCCD) LIKE ? AND UPPER(SDT) LIKE ? AND ACTIVE = 1";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "%" + nhanVien.getTenNV().toUpperCase() + "%");
            preparedStatement.setString(2, "%" + nhanVien.getCCCD().toUpperCase() + "%");
            preparedStatement.setString(3, "%" + nhanVien.getSDT().toUpperCase() + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(resultSet.getInt("MaNV"));
                nv.setTenNV(resultSet.getString("TenNV"));
                nv.setCCCD(resultSet.getString("CCCD"));
                nv.setNgaySinh(resultSet.getDate("NgaySinh"));
                nv.setSDT(resultSet.getString("SDT"));
                nv.setGioiTinh(resultSet.getString("GioiTinh"));
                nv.setNgayVaoLam(resultSet.getDate("NgayVL"));
                nv.setChucVu(resultSet.getString("ChucVu"));
                listNhanVien.add(nv);
            }
        } catch (SQLException e) {
        }
        
        return listNhanVien;
    }

    public NhanVien TimKiemNhanVienBangMaNV(int MaNV) {
        NhanVien nhanVien = new NhanVien();
        String sql = "SELECT TENNV, CCCD, CHUCVU FROM NHANVIEN WHERE MANV = ? AND ACTIVE = 1";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, MaNV);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                nhanVien.setMaNV(MaNV);
                nhanVien.setTenNV(resultSet.getString("TenNV"));
                nhanVien.setCCCD(resultSet.getString("CCCD"));
                nhanVien.setChucVu(resultSet.getString("ChucVu"));
            }
        } catch (SQLException e) {
        }
        
        return nhanVien;
    }

    public boolean DangKiTaiKhoan(User user) {
        String sql = "INSERT INTO TAIKHOAN (TENTAIKHOAN, MANV, MATKHAU, QUYEN) VALUES(?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setInt(2, user.getMaNV());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        
        return false;
    }

    public boolean XoaTK(int MaNV) {
        String sql = "DELETE FROM TAIKHOAN WHERE MANV = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, MaNV);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        
        return false;
    }

    public boolean CheckTKNV(int MaNV) {
        int i = 0;
        String sql = "SELECT * FROM TAIKHOAN WHERE MANV = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, MaNV);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                i++;
            }
            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
        }
        
        return false;
    }
}

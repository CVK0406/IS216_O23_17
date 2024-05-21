package DAO;

import Model.KhachHang;
import Model.PhieuDatPhong;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.Types;

public class PhieuDatPhongDAO {

    Connection con = DataBaseConnection.getConnection();

    public int getCurrentMaDatPhong() {
        int result = -1;
        String sql = "SELECT MADATPHONG FROM PHIEUDATPHONG ORDER BY MADATPHONG DESC FETCH FIRST 1 ROW ONLY";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("MADATPHONG");
                return result + 1;
            }
        } catch (SQLException e) {
        }

        return result + 1;
    }

    public boolean ThemPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
        String sql = "INSERT INTO PHIEUDATPHONG(MADATPHONG, MAKH, NGAYNHAN, NGAYTRA, TTNHANPHONG, MANV,  TIENTRATRUOC) VALUES(?,?,?,?,0,1,?)";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, phieuDatPhong.getMaDatPhong());
            preparedStatement.setInt(2, phieuDatPhong.getKhachHang().getMaKH());
            preparedStatement.setDate(3, new Date(phieuDatPhong.getNgayNhan().getTime()));
            preparedStatement.setDate(4, new Date(phieuDatPhong.getNgayTra().getTime()));
            preparedStatement.setInt(5, phieuDatPhong.getTienTraTruoc());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }

        return false;
    }

    public boolean ThemChiTietDatPhong(int MaDatPhong, ArrayList<String> ListMaPHG) {
        for (String maPHG : ListMaPHG) {
            String sql = "INSERT INTO CHITIETDATPHONG(MADATPHONG, MAPHG) VALUES(?,?)";

            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, MaDatPhong);
                ps.setString(2, maPHG);
                if (ps.executeUpdate() <= 0) {
                    return false;
                }
            } catch (SQLException e) {
            }
        }
        return true;
    }

    public boolean TaoPhieuDatPhong(PhieuDatPhong phieuDatPhong, ArrayList<String> ListMaPHG) throws SQLException {
        if (this.ThemPhieuDatPhong(phieuDatPhong)) {
            if (this.ThemChiTietDatPhong(phieuDatPhong.getMaDatPhong(), ListMaPHG)) {
                return true;
            }
        }
        return false;
    }

    public boolean XacNhanNhanPhong(int mapdp) {
        String sql = "CALL XacNhanNhanPhong(?)";
        try {
            CallableStatement cp = con.prepareCall(sql);
            cp.setInt(1, mapdp);
            return cp.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean XacNhanThanhToan(int mapdp) {
        String sql = "CALL XacNhanThanhToan(?)";
        
        try {
            CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, mapdp);
            return callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        
        return false;
    }

    public boolean HuyDatPhong(int mapdp) {
        String sql = "CALL HUYPHIEUDATPHONG(?)";
        
        try {
            CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, mapdp);
            return callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        
        return false;
    }

    public int getTienThanhToan(int mapdp) {
        String sql = "{ CALL ? := get_TongTien_Thanhtoan(?)}";
        
        try {
            CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.registerOutParameter(1, Types.NUMERIC);
            callableStatement.setInt(2, mapdp);
            if (callableStatement.executeUpdate() > 0) {
                return callableStatement.getInt(1);
            }
        } catch (SQLException e) {
        }
        
        return 0;
    }

    public ArrayList<String> getDSPhongfromPhieuDatPhong(int mapdp) {
        String sql = "SELECT MAPHG FROM CHITIETDATPHONG WHERE MADATPHONG = ?";
        ArrayList<String> dsphong = new ArrayList<>();
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, mapdp);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dsphong.add(resultSet.getString("MAPHG"));
            }
            return dsphong;
        } catch (SQLException e) {
        }
        
        return dsphong;
    }

    public PhieuDatPhong getThongTinPhieuDatPhong(int mapdp) {
        String sql = "SELECT MADATPHONG, MAKH, NGAYDAT, NGAYNHAN, NGAYTRA, TIENPHONG, TIENTRATRUOC, TIENDV FROM PHIEUDATPHONG WHERE MADATPHONG = ?";
        PhieuDatPhong phieuDatPhong = new PhieuDatPhong();
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, mapdp);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                phieuDatPhong.setMaDatPhong(mapdp);
                KhachHang khachHang = new KhachHangDAO().getKhachHangByMaKH(resultSet.getInt("MAKH"));
                phieuDatPhong.setKhachHang(khachHang);
                phieuDatPhong.setNgayDat(new Date(resultSet.getDate("NGAYDAT").getTime()));
                phieuDatPhong.setNgayNhan(new Date(resultSet.getDate("NGAYNHAN").getTime()));
                phieuDatPhong.setNgayTra(new Date(resultSet.getDate("NGAYTRA").getTime()));
                phieuDatPhong.setDSPhong(this.getDSPhongfromPhieuDatPhong(mapdp));
                phieuDatPhong.setTienPhong(resultSet.getInt("TIENPHONG"));
                phieuDatPhong.setTienTraTruoc(resultSet.getInt("TIENTRATRUOC"));
                phieuDatPhong.setTienDV(resultSet.getInt("TIENDV"));
                phieuDatPhong.setTienThanhToan(this.getTienThanhToan(mapdp));
            }
            return phieuDatPhong;
        } catch (SQLException e) {
        }
        return phieuDatPhong;

    }

    public int getMaDatPhongFromMaPHG(String maphg) {
        String sql = "SELECT PHIEUDATPHONG.MADATPHONG AS MDP FROM PHIEUDATPHONG, CHITIETDATPHONG WHERE TTNHANPHONG = 1 AND PHIEUDATPHONG.MADATPHONG = CHITIETDATPHONG.MADATPHONG AND MAPHG = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, maphg);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("MDP");
            }
        } catch (SQLException e) {
        }
        return -1;

    }

}

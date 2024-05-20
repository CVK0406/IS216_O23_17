package Model;

import java.util.Date;

public class HoaDonDichVu {
    private int MaHDDV;
    private String MaPHG;
    private String TenDV;
    private Date NgaySD;
    private int SoLuong;     
    private int DonGia;
    private int ThanhTien;

    public HoaDonDichVu() {
    }

    public int getMaHDDV() {
        return MaHDDV;
    }

    public void setMaHDDV(int MaHDDV) {
        this.MaHDDV = MaHDDV;
    }

    public String getMaPHG() {
        return MaPHG;
    }

    public void setMaPHG(String MaPHG) {
        this.MaPHG = MaPHG;
    }

    public String getTenDV() {
        return TenDV;
    }

    public void setTenDV(String TenDV) {
        this.TenDV = TenDV;
    }

    public Date getNgaySD() {
        return NgaySD;
    }

    public void setNgaySD(Date NgaySD) {
        this.NgaySD = NgaySD;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int DonGia) {
        this.DonGia = DonGia;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
    
}

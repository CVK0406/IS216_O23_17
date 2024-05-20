package Model;

public class DanhMucDichVu {
    private int MaDV;
    private String TenDV;
    private int DonGia;

    public DanhMucDichVu() {
    }

    public DanhMucDichVu(int MaDV, String TenDV, int DonGia) {
        this.MaDV = MaDV;
        this.TenDV = TenDV;
        this.DonGia = DonGia;
    }

    public int getMaDV() {
        return MaDV;
    }

    public void setMaDV(int MaDV) {
        this.MaDV = MaDV;
    }

    public String getTenDV() {
        return TenDV;
    }

    public void setTenDV(String TenDV) {
        this.TenDV = TenDV;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int DonGia) {
        this.DonGia = DonGia;
    }
}

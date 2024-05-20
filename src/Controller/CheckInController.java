package Controller;

import com.toedter.calendar.JDateChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CheckInController {
    
    private boolean checkNumber(String input) {
        try {
            Long.valueOf(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean checkJTextCheckIn(JTextField MaDatPhong, JTextField TenKhachHang, JTextField CCCD,
            JTextField SDT, JDateChooser NgayDat, JDateChooser NgayNhan, JDateChooser NgayTra) {
        
        boolean checkEmptyMaDatPhong = MaDatPhong.getText().equals("");
        boolean checkEmptyTenKhachHang = TenKhachHang.getText().equals("");
        boolean checkEmptyCCCD = CCCD.getText().equals("");
        boolean checkEmptySDT = SDT.getText().equals("");
        boolean checkEmptyNgayDat = NgayDat.getDate() == null;
        boolean checkEmptyNgayNhan = NgayNhan.getDate() == null;
        boolean checkEmptyNgayTra = NgayTra.getDate() == null;

        if (checkEmptyMaDatPhong || checkEmptyTenKhachHang || checkEmptyCCCD || checkEmptySDT
                || checkEmptyNgayDat || checkEmptyNgayNhan || checkEmptyNgayTra) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin.");
            return false;
        }

        if (!checkNumber(SDT.getText())) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải là số. Vui lòng nhập lại.");
            return false;
        }

        if (!checkNumber(CCCD.getText())) {
            JOptionPane.showMessageDialog(null, "CCCD phải là số. Vui lòng nhập lại.");
            return false;
        }

        return true;
    }

}

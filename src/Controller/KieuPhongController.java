package Controller;

import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class KieuPhongController {

    private boolean checkNumber(String input) {
        try {
            Long.valueOf(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean checkJtextKieuPhong(JTextField MaLoaiPhg, JTextField KieuGiuong, JTextField KieuPhong, JTextField GiaPhong) {
        boolean checkEmptyMaLoaiPhg = MaLoaiPhg.getText().equals("");
        boolean checkEmptyKieuGiuong = KieuGiuong.getText().equals("");
        boolean checkEmptyKieuPhong = KieuPhong.getText().equals("");
        boolean checkEmptyGiaPhong = GiaPhong.getText().equals("");

        if (checkEmptyMaLoaiPhg || checkEmptyKieuGiuong || checkEmptyKieuPhong || checkEmptyGiaPhong) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin.");
            return false;
        }

        if (!checkNumber(GiaPhong.getText())) {
            JOptionPane.showMessageDialog(null, "Giá phòng phải là số. Vui lòng nhập lại.");
            return false;
        }

        if (!checkNumber(KieuGiuong.getText())) {
            JOptionPane.showMessageDialog(null, "Kiểu giường phải là số. Vui lòng nhập lại.");
            return false;
        }
        
        return true;
    }
}

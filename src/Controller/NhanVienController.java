package Controller;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class NhanVienController {

    private boolean checkNumber(String input) {
        try {
            Long.valueOf(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean checkJTextNhanVien(JTextField HoTen, JTextField CCCD, JDateChooser NgaySinh, JTextField SDT, JDateChooser NgayVaoLam) {
        boolean checkEmptyHoten = HoTen.getText().equals("");
        boolean checkEmptyCCCD =   CCCD.getText().equals("");
        boolean checkEmptyNgaySinh = NgaySinh.getDate() == null;
        boolean checkEmptySDT = SDT.getText().equals("");
        boolean checkNgayVaoLam = NgayVaoLam.getDate() == null;
        
        if (checkEmptyHoten || checkEmptyCCCD || checkEmptyNgaySinh || checkEmptySDT || checkNgayVaoLam) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin.");
            return false;
        }

        if (!checkNumber(CCCD.getText())) {
            JOptionPane.showMessageDialog(null, "CMND/CCCD phải là số. Vui lòng nhập lại.");
            return false;
        }

        if (!checkNumber(SDT.getText())) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải là số. Vui lòng nhập lại.");
            return false;
        }

        return true;
    }

}

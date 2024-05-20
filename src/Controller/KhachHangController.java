package Controller;

import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class KhachHangController {
    
    private boolean checkNumber(String input) {
        try {
            Long.valueOf(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean checkJtextKhachHang(JTextField Hoten, JTextField CCCD, JTextField SDT) {
        boolean checkEmptyHoten = Hoten.getText().equals("");
        boolean checkEmptyCCCD = CCCD.getText().equals("");
        boolean checkEmptySDT = SDT.getText().equals("");

        if (checkEmptyHoten || checkEmptyCCCD || checkEmptySDT) {
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

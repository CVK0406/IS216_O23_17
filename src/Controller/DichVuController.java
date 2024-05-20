package Controller;

import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class DichVuController {
    
    private boolean checkNumber(String input) {
        try {
            Long.valueOf(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean checkJtextDichVu(JTextField TenDV, JTextField DonGia) {
        boolean checkEmptyTenDV = TenDV.getText().equals("");
        boolean checkEmptyDonGia = DonGia.getText().equals("");
        
        if (checkEmptyTenDV || checkEmptyDonGia) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin.");
            return false;
        }
        
        if (!checkNumber(DonGia.getText())) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải là số. Vui lòng nhập lại.");
            return false;
        }
        
        return true;
    }
}

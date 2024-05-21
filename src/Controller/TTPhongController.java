package Controller;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TTPhongController {

    public boolean checkJTextMaPhong(JTextField Text) {
        if (Text.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin.");
            return false;
        }
        return true;
    }
}

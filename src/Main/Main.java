package Main;

import View.LoginForm;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args){            
        try {
            UIManager.setLookAndFeel(new FlatArcIJTheme());
            UIManager.put("Table.showGrid", true);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        }
        
        LoginForm loginform = new LoginForm();
        loginform.setVisible(true);
    }
}

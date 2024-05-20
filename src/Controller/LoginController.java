package Controller;

import Model.User;
import View.AdminJFrame;
import View.MainJFrame;
import javax.swing.JFrame;

public class LoginController {
    public LoginController(User user){
        if(user.getRole().equals("user"))
        {
            MainJFrame frame = new MainJFrame();
            frame.setExtendedState( JFrame.MAXIMIZED_BOTH);
            frame.setUser(user);
            frame.setVisible(true);
        }
        else if (user.getRole().equals("admin"))             
        {
            AdminJFrame frame = new AdminJFrame();
            frame.setExtendedState( JFrame.MAXIMIZED_BOTH);
            frame.setUser(user);
            frame.setVisible(true);
        }
    }
}

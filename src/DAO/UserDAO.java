package DAO;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    Connection conn;
    public UserDAO() {
        conn = DataBaseConnection.getConnection();
    }
    public User validateUser(User user) {
        try {
            String sql = "SElECT TENTAIKHOAN, MATKHAU, QUYEN, MANV FROM TAIKHOAN WHERE TENTAIKHOAN = ? AND MATKHAU = ?";
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next())
                {
                    User returnUser = new User();
                    returnUser.setUsername(resultSet.getString("TENTAIKHOAN"));
                    returnUser.setPassword(resultSet.getString("MATKHAU"));
                    returnUser.setRole(resultSet.getString("QUYEN"));
                    returnUser.setMaNV(resultSet.getInt("MANV"));
                    return returnUser;
                }
                else return null;
            }
            catch(SQLException event){
            }
            conn.close();
            return null;

        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}

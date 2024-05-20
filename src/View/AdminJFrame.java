package View;

import Model.Item;
import Controller.MainController;
import Model.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AdminJFrame extends javax.swing.JFrame {
    private User user;
    public AdminJFrame(){
        initComponents();
        setResizable(true);
        setVisible(true);
        setTitle("QUẢN Lý KHÁCH SẠN");
        
        MainController controller = new MainController(jpnView);
        controller.setView(Panel_TrangChu,Label_TrangChu);
        
        List<Item> listItem = new ArrayList<>();
        listItem.add(new Item("TrangChu",Panel_TrangChu,Label_TrangChu));
        listItem.add(new Item("QuanLiKhachHang",Panel_QuanLiKhachHang,Label_QuanLiKhachHang));
        listItem.add(new Item("QuanLiNhanVien",Panel_QuanLiNhanVien,Label_QuanLiNhanVien));
        listItem.add(new Item("QuanLiPhong",Panel_QuanLiPhong,Label_QuanLiPhong));
        listItem.add(new Item("QuanLiDichVu",Panel_QuanLiDichVu,Label_QuanLiDichVu));
        listItem.add(new Item("ThongKe",Panel_ThongKe,Label_ThongKe));
        listItem.add(new Item("DangXuat", Panel_DangXuat, Label_DangXuat));
        controller.setEvent(listItem);

        JFrameCenterOfScreen();
    }
    public void setUser(User u)
    {
        user = u;
    }
    public void JFrameCenterOfScreen(){
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnRoot = new javax.swing.JPanel();
        jpnMenu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Panel_TrangChu = new javax.swing.JPanel();
        Label_TrangChu = new javax.swing.JLabel();
        Panel_QuanLiPhong = new javax.swing.JPanel();
        Label_QuanLiPhong = new javax.swing.JLabel();
        Panel_QuanLiKhachHang = new javax.swing.JPanel();
        Label_QuanLiKhachHang = new javax.swing.JLabel();
        Panel_QuanLiNhanVien = new javax.swing.JPanel();
        Label_QuanLiNhanVien = new javax.swing.JLabel();
        Panel_QuanLiDichVu = new javax.swing.JPanel();
        Label_QuanLiDichVu = new javax.swing.JLabel();
        Panel_ThongKe = new javax.swing.JPanel();
        Label_ThongKe = new javax.swing.JLabel();
        Panel_DangXuat = new javax.swing.JPanel();
        Label_DangXuat = new javax.swing.JLabel();
        jpnView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jpnRoot.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jpnMenu.setBackground(new java.awt.Color(0, 175, 240));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("STXinwei", 3, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GRANDE HOTEL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Panel_TrangChu.setBackground(new java.awt.Color(0, 175, 225));
        Panel_TrangChu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Label_TrangChu.setFont(new java.awt.Font("Segoe UI Variable", 1, 15)); // NOI18N
        Label_TrangChu.setForeground(new java.awt.Color(255, 255, 255));
        Label_TrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconHomePage.png"))); // NOI18N
        Label_TrangChu.setText("Trang chủ");

        javax.swing.GroupLayout Panel_TrangChuLayout = new javax.swing.GroupLayout(Panel_TrangChu);
        Panel_TrangChu.setLayout(Panel_TrangChuLayout);
        Panel_TrangChuLayout.setHorizontalGroup(
            Panel_TrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_TrangChuLayout.createSequentialGroup()
                .addComponent(Label_TrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Panel_TrangChuLayout.setVerticalGroup(
            Panel_TrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_TrangChu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        Panel_QuanLiPhong.setBackground(new java.awt.Color(0, 175, 225));
        Panel_QuanLiPhong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Label_QuanLiPhong.setFont(new java.awt.Font("Segoe UI Variable", 1, 15)); // NOI18N
        Label_QuanLiPhong.setForeground(new java.awt.Color(255, 255, 255));
        Label_QuanLiPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconBed.png"))); // NOI18N
        Label_QuanLiPhong.setText("Quản lý phòng");

        javax.swing.GroupLayout Panel_QuanLiPhongLayout = new javax.swing.GroupLayout(Panel_QuanLiPhong);
        Panel_QuanLiPhong.setLayout(Panel_QuanLiPhongLayout);
        Panel_QuanLiPhongLayout.setHorizontalGroup(
            Panel_QuanLiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_QuanLiPhongLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Label_QuanLiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        Panel_QuanLiPhongLayout.setVerticalGroup(
            Panel_QuanLiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_QuanLiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        Panel_QuanLiKhachHang.setBackground(new java.awt.Color(0, 175, 225));
        Panel_QuanLiKhachHang.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Label_QuanLiKhachHang.setFont(new java.awt.Font("Segoe UI Variable", 1, 15)); // NOI18N
        Label_QuanLiKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        Label_QuanLiKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconCustomers.png"))); // NOI18N
        Label_QuanLiKhachHang.setText("Quản lý khách hàng");

        javax.swing.GroupLayout Panel_QuanLiKhachHangLayout = new javax.swing.GroupLayout(Panel_QuanLiKhachHang);
        Panel_QuanLiKhachHang.setLayout(Panel_QuanLiKhachHangLayout);
        Panel_QuanLiKhachHangLayout.setHorizontalGroup(
            Panel_QuanLiKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_QuanLiKhachHangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Label_QuanLiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        Panel_QuanLiKhachHangLayout.setVerticalGroup(
            Panel_QuanLiKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_QuanLiKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        Panel_QuanLiNhanVien.setBackground(new java.awt.Color(0, 175, 225));
        Panel_QuanLiNhanVien.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Label_QuanLiNhanVien.setFont(new java.awt.Font("Segoe UI Variable", 1, 15)); // NOI18N
        Label_QuanLiNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        Label_QuanLiNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconStaffs.png"))); // NOI18N
        Label_QuanLiNhanVien.setText("Quản lý nhân viên");

        javax.swing.GroupLayout Panel_QuanLiNhanVienLayout = new javax.swing.GroupLayout(Panel_QuanLiNhanVien);
        Panel_QuanLiNhanVien.setLayout(Panel_QuanLiNhanVienLayout);
        Panel_QuanLiNhanVienLayout.setHorizontalGroup(
            Panel_QuanLiNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_QuanLiNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_QuanLiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_QuanLiNhanVienLayout.setVerticalGroup(
            Panel_QuanLiNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_QuanLiNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        Panel_QuanLiDichVu.setBackground(new java.awt.Color(0, 175, 225));
        Panel_QuanLiDichVu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Label_QuanLiDichVu.setFont(new java.awt.Font("Segoe UI Variable", 1, 15)); // NOI18N
        Label_QuanLiDichVu.setForeground(new java.awt.Color(255, 255, 255));
        Label_QuanLiDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconService.png"))); // NOI18N
        Label_QuanLiDichVu.setText("Quản lý dịch vụ");

        javax.swing.GroupLayout Panel_QuanLiDichVuLayout = new javax.swing.GroupLayout(Panel_QuanLiDichVu);
        Panel_QuanLiDichVu.setLayout(Panel_QuanLiDichVuLayout);
        Panel_QuanLiDichVuLayout.setHorizontalGroup(
            Panel_QuanLiDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_QuanLiDichVuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Label_QuanLiDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        Panel_QuanLiDichVuLayout.setVerticalGroup(
            Panel_QuanLiDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_QuanLiDichVu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        Panel_ThongKe.setBackground(new java.awt.Color(0, 175, 225));
        Panel_ThongKe.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Label_ThongKe.setFont(new java.awt.Font("Segoe UI Variable", 1, 15)); // NOI18N
        Label_ThongKe.setForeground(new java.awt.Color(255, 255, 255));
        Label_ThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconReport.png"))); // NOI18N
        Label_ThongKe.setText("Thống kê, báo cáo");

        javax.swing.GroupLayout Panel_ThongKeLayout = new javax.swing.GroupLayout(Panel_ThongKe);
        Panel_ThongKe.setLayout(Panel_ThongKeLayout);
        Panel_ThongKeLayout.setHorizontalGroup(
            Panel_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_ThongKeLayout.setVerticalGroup(
            Panel_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_ThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        Panel_DangXuat.setBackground(new java.awt.Color(0, 175, 225));
        Panel_DangXuat.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Label_DangXuat.setFont(new java.awt.Font("Segoe UI Variable", 1, 15)); // NOI18N
        Label_DangXuat.setForeground(new java.awt.Color(255, 255, 255));
        Label_DangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconLogOut.png"))); // NOI18N
        Label_DangXuat.setText("Đăng xuất");
        Label_DangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Label_DangXuatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_DangXuatLayout = new javax.swing.GroupLayout(Panel_DangXuat);
        Panel_DangXuat.setLayout(Panel_DangXuatLayout);
        Panel_DangXuatLayout.setHorizontalGroup(
            Panel_DangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_DangXuatLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Label_DangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        Panel_DangXuatLayout.setVerticalGroup(
            Panel_DangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_DangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnMenuLayout = new javax.swing.GroupLayout(jpnMenu);
        jpnMenu.setLayout(jpnMenuLayout);
        jpnMenuLayout.setHorizontalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Panel_DangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Panel_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Panel_QuanLiDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Panel_QuanLiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Panel_QuanLiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Panel_QuanLiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Panel_TrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnMenuLayout.setVerticalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(Panel_TrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(Panel_QuanLiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(Panel_QuanLiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(Panel_QuanLiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(Panel_QuanLiDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(Panel_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(Panel_DangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1183, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnRootLayout = new javax.swing.GroupLayout(jpnRoot);
        jpnRoot.setLayout(jpnRootLayout);
        jpnRootLayout.setHorizontalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnRootLayout.setVerticalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRoot, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRoot, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Label_DangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Label_DangXuatMouseClicked
        String notification = "Bạn có muốn đăng xuất?";
        
        int optionLogOut = JOptionPane.showConfirmDialog(null, notification, "Đăng xuất", JOptionPane.YES_NO_OPTION);
        if (optionLogOut == JOptionPane.YES_OPTION){  
            this.dispose();
            
            LoginForm loginform = new LoginForm();
            loginform.setVisible(true);
        } else if (optionLogOut == JOptionPane.NO_OPTION){
            Panel_DangXuat.setBackground(new Color(0,175,225));
            Label_DangXuat.setBackground(new Color(0,175,225));
        }
    }//GEN-LAST:event_Label_DangXuatMouseClicked

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainJFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label_DangXuat;
    private javax.swing.JLabel Label_QuanLiDichVu;
    private javax.swing.JLabel Label_QuanLiKhachHang;
    private javax.swing.JLabel Label_QuanLiNhanVien;
    private javax.swing.JLabel Label_QuanLiPhong;
    private javax.swing.JLabel Label_ThongKe;
    private javax.swing.JLabel Label_TrangChu;
    private javax.swing.JPanel Panel_DangXuat;
    private javax.swing.JPanel Panel_QuanLiDichVu;
    private javax.swing.JPanel Panel_QuanLiKhachHang;
    private javax.swing.JPanel Panel_QuanLiNhanVien;
    private javax.swing.JPanel Panel_QuanLiPhong;
    private javax.swing.JPanel Panel_ThongKe;
    private javax.swing.JPanel Panel_TrangChu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JPanel jpnView;
    // End of variables declaration//GEN-END:variables
}

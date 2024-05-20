package View;

import View.ChonPhongLuuTruJPanel;
import View.ChiTietPhieuDatPhongJPanel;
import Controller.CheckInController;
import DAO.DataBaseConnection;
import DAO.PhieuDatPhongDAO;
import Model.PhieuDatPhong;
import Model.ThongTinPhieuDatPhong;
import View.NhanPhongJPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class TraPhongJPane extends javax.swing.JPanel {
    private ArrayList<ThongTinPhieuDatPhong> listPhieuDatPhong;
    private DefaultTableModel tblPhieuDatPhong;
    public TraPhongJPane() 
    {              
        initComponents(); 
        tblPhieuDatPhong = (DefaultTableModel) Table_PhieuDatPhong.getModel();
        tblPhieuDatPhong.setColumnIdentifiers(new Object[]{"Mã đặt","Tên khách hàng", "CCCD", "SDT", "Ngày đặt", "Ngày nhận","Ngày trả ","Danh sách phòng đặt"});
        loadPhieuDatPhong();
        Table_PhieuDatPhong.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                try {
                    Date d = new SimpleDateFormat("dd-MM-yyyy").parse(tblPhieuDatPhong.getValueAt(row, 6).toString());
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    Date currentDate = format.parse(format.format(new Date()));
                    if (d.compareTo(currentDate) < 0 )
                        setForeground(Color.RED);
                    else if (d.compareTo(currentDate) == 0)
                        setForeground(Color.GREEN);
                    else
                        setForeground(Color.BLACK);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanPhongJPane.class.getName()).log(Level.SEVERE, null, ex);
                }                
                return this;
            }   
        });
        Table_PhieuDatPhong.setShowHorizontalLines(true);
        Table_PhieuDatPhong.setDefaultEditor(Object.class, null);
    }
    public void loadPhieuDatPhong()
    {
        tblPhieuDatPhong.setRowCount(0);
        Connection conn = new DataBaseConnection().getConnection();
        String sql = "SELECT MADATPHONG FROM PHIEUDATPHONG WHERE TTNHANPHONG = 1 AND TRUNC(NGAYNHAN) <= TRUNC(SYSDATE)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int madp = rs.getInt("MADATPHONG");
                addPhieuDatPhongToTable(madp);
            }
            Table_PhieuDatPhong.setModel(tblPhieuDatPhong);
            conn.close();
        } catch (SQLException e) {
        }

    }
        public void themThanhToan(int madp, int tienkhachdua, String hinhthucthanhtoan)
    {
        Connection conn = new DataBaseConnection().getConnection();
        String sql = "INSERT INTO THANHTOAN(MADATPHONG, MANV, HINHTHUCTHANHTOAN, TIENKHACHDUA) VALUES (?,1,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, madp);
            ps.setString(2, hinhthucthanhtoan);
            ps.setInt(3, tienkhachdua);
            if (ps.executeUpdate()>0)
            {
                JOptionPane.showMessageDialog(this, "Thanh toán đã được lưu");
            }
            else 
                JOptionPane.showMessageDialog(this, "Thanh toán xảy ra lỗi");

            conn.close();
        } catch (HeadlessException | SQLException e) {
        }

    }

    public void addPhieuDatPhongToTable(int madp)
    {
        PhieuDatPhong d = new PhieuDatPhongDAO().getThongTinPhieuDatPhong(madp);
        tblPhieuDatPhong.addRow(new Object[]{d.getMaDatPhong(),d.getKhachHang().getTenKH(),d.getKhachHang().getCCCD(),d.getKhachHang().getSDT(),
                    new SimpleDateFormat("dd-MM-yyyy").format(d.getNgayDat()),
                    new SimpleDateFormat("dd-MM-yyyy").format(d.getNgayNhan()),
                    new SimpleDateFormat("dd-MM-yyyy").format(d.getNgayTra()),
                    d.getStringDSPhong()
                    });
    }
    public void loadPhieuDatPhongInfo(int madp)
    {
        PhieuDatPhong pdp = new PhieuDatPhongDAO().getThongTinPhieuDatPhong(madp);
        Text_MaDatPhong.setText(Integer.toString(pdp.getMaDatPhong()));
        Text_TenKH.setText(pdp.getKhachHang().getTenKH());
        Text_CCCD.setText(pdp.getKhachHang().getCCCD());
        Text_SDT.setText(pdp.getKhachHang().getSDT());
        jDateChooser_NgayDat.setDate(pdp.getNgayDat());
        jDateChooser_NgayNhan.setDate(pdp.getNgayNhan());
        jDateChooser_NgayTra.setDate(pdp.getNgayTra());
        jComboBox_DSPhongDat.removeAllItems();
        for (String data : pdp.getDSPhong())
        {
            jComboBox_DSPhongDat.addItem(data);
        }

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Text_MaDatPhong = new javax.swing.JTextField();
        jDateChooser_NgayDat = new com.toedter.calendar.JDateChooser();
        Text_TenKH = new javax.swing.JTextField();
        jDateChooser_NgayNhan = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jDateChooser_NgayTra = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        Text_CCCD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Text_SDT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox_DSPhongDat = new javax.swing.JComboBox<>();
        jButton_XuatHoaDon = new javax.swing.JButton();
        Button_CheckOut = new javax.swing.JButton();
        Button_TimKiem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_PhieuDatPhong = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1058, 644));

        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        jLabel4.setText("Mã đặt phòng");

        jLabel15.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        jLabel15.setText("Tên khách hàng");

        jLabel16.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        jLabel16.setText("Ngày đặt");

        jLabel18.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        jLabel18.setText("Ngày nhận");

        Text_MaDatPhong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Text_MaDatPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_MaDatPhongActionPerformed(evt);
            }
        });

        jDateChooser_NgayDat.setDateFormatString("dd-MM-yyyy");
        jDateChooser_NgayDat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Text_TenKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jDateChooser_NgayNhan.setDateFormatString("dd-MM-yyyy");
        jDateChooser_NgayNhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        jLabel20.setText("Ngày trả");

        jDateChooser_NgayTra.setDateFormatString("dd-MM-yyyy");
        jDateChooser_NgayTra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        jLabel2.setText("Số CCCD");

        Text_CCCD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        jLabel3.setText("Số điện thoại");

        Text_SDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Text_SDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_SDTActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        jLabel5.setText("Danh sách phòng đặt");

        jComboBox_DSPhongDat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_DSPhongDatActionPerformed(evt);
            }
        });

        jButton_XuatHoaDon.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        jButton_XuatHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconBill.png"))); // NOI18N
        jButton_XuatHoaDon.setText("In");
        jButton_XuatHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XuatHoaDonActionPerformed(evt);
            }
        });

        Button_CheckOut.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        Button_CheckOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconCheckIn.png"))); // NOI18N
        Button_CheckOut.setText("Check out");
        Button_CheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_CheckOutActionPerformed(evt);
            }
        });

        Button_TimKiem.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        Button_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSearch.png"))); // NOI18N
        Button_TimKiem.setText("Tìm kiếm");
        Button_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_TimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                            .addComponent(Text_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(40, 40, 40)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Text_CCCD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                .addComponent(Text_TenKH, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Text_MaDatPhong, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jDateChooser_NgayNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser_NgayTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser_NgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_DSPhongDat, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_XuatHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addComponent(Button_TimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addComponent(Button_CheckOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(Button_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_CheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_XuatHoaDon)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(Text_MaDatPhong)
                            .addComponent(jDateChooser_NgayDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(Text_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jDateChooser_NgayNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Text_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Text_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(14, 14, 14))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jDateChooser_NgayTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox_DSPhongDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(31, 31, 31))))
        );

        Table_PhieuDatPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table_PhieuDatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_PhieuDatPhongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Table_PhieuDatPhong);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1058, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 644, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Table_PhieuDatPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_PhieuDatPhongMouseClicked
        // TODO add your handling code here:
        ListSelectionModel listTable_KhachHang = Table_PhieuDatPhong.getSelectionModel();
        listTable_KhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        int indexTB = Table_PhieuDatPhong.getSelectedRow();

        this.loadPhieuDatPhongInfo(Integer.parseInt(tblPhieuDatPhong.getValueAt(indexTB, 0).toString()));
    }//GEN-LAST:event_Table_PhieuDatPhongMouseClicked

    private void Button_CheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_CheckOutActionPerformed
        // TODO add your handling code here:
        if (new CheckInController().checkJTextCheckIn(Text_MaDatPhong, Text_TenKH, Text_CCCD, Text_SDT, jDateChooser_NgayDat, jDateChooser_NgayNhan, jDateChooser_NgayTra))
        {
            if (this.XacNhanThanhToan())
            {
                this.loadPhieuDatPhong();
            }
        }
    }//GEN-LAST:event_Button_CheckOutActionPerformed

    private void Button_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_TimKiemActionPerformed
        // TODO add your handling code here:
        ChonPhongLuuTruJPanel plt = new ChonPhongLuuTruJPanel();
        int result = JOptionPane.showConfirmDialog(null, plt, "Chọn phòng đang lưu trú", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
        {
            int madp = plt.returnMaDatPhong();
            loadPhieuDatPhongInfo(madp);
        }

    }//GEN-LAST:event_Button_TimKiemActionPerformed

    private void jButton_XuatHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XuatHoaDonActionPerformed
        // TODO add your handling code here:
        int madatphong= Integer.parseInt(Text_MaDatPhong.getText());
        HashMap para = new HashMap();
        para.put("madatphong", madatphong);
        viewReportSR("src\\Reports\\HoaDonThanhToanRP.jrxml", "src\\Reports\\subreportDichVu.jrxml",para);

    }//GEN-LAST:event_jButton_XuatHoaDonActionPerformed

    private void Text_MaDatPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_MaDatPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_MaDatPhongActionPerformed

    private void jComboBox_DSPhongDatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_DSPhongDatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_DSPhongDatActionPerformed

    private void Text_SDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_SDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_SDTActionPerformed
    public boolean XacNhanThanhToan()
    {
        String[] option = {"Xác nhận thanh toán", "Hủy"};
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date currentDate = sdf.parse(sdf.format(new Date()));
            Date NgayTra = sdf.parse(sdf.format(jDateChooser_NgayTra.getDate()));
            if (NgayTra.compareTo(currentDate) > 0){
                if (JOptionPane.showConfirmDialog(this, "Ngày trả của phiếu đặt phòng chưa đến.\nBạn có muốn check out sớm ?","Check out sớm hơn lịch",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
                    return false;
            }
        } catch (ParseException ex) {
            Logger.getLogger(TraPhongJPane.class.getName()).log(Level.SEVERE, null, ex);
        }

        ChiTietPhieuDatPhongJPanel panel = new ChiTietPhieuDatPhongJPanel();
        panel.setPhieuDatPhong(Integer.parseInt(Text_MaDatPhong.getText()));
        ImageIcon i = new ImageIcon("src\\Imagine\\reception_30px.png");
        int result =   JOptionPane.showOptionDialog(this, panel, "Xác nhận thanh toán", JOptionPane.OK_OPTION,JOptionPane.CANCEL_OPTION,i,option, option[0]);
        if (result == JOptionPane.OK_OPTION)
        {
            Connection conn = new DataBaseConnection().getConnection();
            String sql = "UPDATE PHIEUDATPHONG SET NGAYTRA = TRUNC(SYSDATE) WHERE MADATPHONG = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(Text_MaDatPhong.getText()));
                ps.executeUpdate();
                conn.close();
            } catch (NumberFormatException | SQLException e) {
            }
            this.themThanhToan(Integer.parseInt(Text_MaDatPhong.getText()),panel.getTienKhachTra() , panel.getHinhThucThanhToan());
            new PhieuDatPhongDAO().XacNhanThanhToan(Integer.parseInt(Text_MaDatPhong.getText()));
            clearText();
            return true;
        }

        return false;
    }
    public void viewReportSR(String fileName, String fileNameSR, HashMap para){
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(fileName);
            JasperDesign jasperDesignSR = JRXmlLoader.load(fileNameSR);

            JasperReport jreReport = JasperCompileManager.compileReport(jasperDesign);
            JasperReport jasperReportSR = JasperCompileManager.compileReport(jasperDesignSR);
            
            para.put("SubReportParam", jasperReportSR);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jreReport, para, DataBaseConnection.getConnection());
            
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void clearText(){
        Text_MaDatPhong.setText("");
        Text_TenKH.setText("");
        Text_CCCD.setText("");
        Text_SDT.setText("");
        jDateChooser_NgayDat.setDate(null);
        jDateChooser_NgayNhan.setDate(null);
        jDateChooser_NgayTra.setDate(null);
        jComboBox_DSPhongDat.removeAllItems();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_CheckOut;
    private javax.swing.JButton Button_TimKiem;
    private javax.swing.JTable Table_PhieuDatPhong;
    private javax.swing.JTextField Text_CCCD;
    private javax.swing.JTextField Text_MaDatPhong;
    private javax.swing.JTextField Text_SDT;
    private javax.swing.JTextField Text_TenKH;
    private javax.swing.JButton jButton_XuatHoaDon;
    private javax.swing.JComboBox<String> jComboBox_DSPhongDat;
    private com.toedter.calendar.JDateChooser jDateChooser_NgayDat;
    private com.toedter.calendar.JDateChooser jDateChooser_NgayNhan;
    private com.toedter.calendar.JDateChooser jDateChooser_NgayTra;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}

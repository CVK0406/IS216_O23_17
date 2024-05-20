package View;

import DAO.ThongTinPhongDAO;
import DAO.KhachHangDAO;
import DAO.PhieuDatPhongDAO;
import Model.ThongTinPhong;
import Model.KhachHang;
import Model.PhieuDatPhong;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QuanLiDatPhongJPane extends javax.swing.JPanel {
    private ArrayList<ThongTinPhong> listPhongTrong;
    private ArrayList<KhachHang> listKhachHang;
    private HashMap<String, Integer> listPhongDat;
    private KhachHangDAO khachHangDAO;
    private PhieuDatPhongDAO phieuDatPhongDAO;
    private int curr_MaDatPhong;
    private int curr_MaKhachHang;
    private int tongTien;
    private PhieuDatPhong phieuDatPhong; 
    DefaultTableModel tblKhachHang;
    DefaultTableModel tblDanhSachPhongTrong;
    DefaultTableModel tblDanhSachPhongDat;
    Date date = new Date();
   
    public QuanLiDatPhongJPane() 
    {             
        initComponents(); 
        phieuDatPhong = new PhieuDatPhong();
        phieuDatPhongDAO = new PhieuDatPhongDAO();
        curr_MaDatPhong = phieuDatPhongDAO.getCurrentMaDatPhong();
        tongTien = 0;
        

        listPhongTrong = new ThongTinPhongDAO().getListChiTietTTPhong();
        tblDanhSachPhongTrong = (DefaultTableModel) Table_DanhSachPhongTrong.getModel();
        tblDanhSachPhongTrong.setColumnIdentifiers(new Object[]{"Mã phòng", "Kiểu phòng", "Kiểu giường", "Giá phòng"});
        
        listPhongDat = new HashMap<String,Integer>();
        tblDanhSachPhongDat = (DefaultTableModel) Table_DanhSachPhongDat.getModel();
        tblDanhSachPhongDat.setColumnIdentifiers(new Object[]{"Mã phòng"});
        showComboBox_KieuPhong();
        Table_DanhSachPhongDat.setShowHorizontalLines(true);
        Table_DanhSachPhongTrong.setShowHorizontalLines(true);
        Table_DanhSachPhongDat.setDefaultEditor(Object.class, null);
        Table_DanhSachPhongTrong.setDefaultEditor(Object.class, null);
    }

    public void showTableChiTietTTPhong(){
        tblDanhSachPhongTrong.setRowCount(0);
        for(ThongTinPhong ttPhong : listPhongTrong){
            tblDanhSachPhongTrong.addRow(new Object[]{ttPhong.getMaPhg(), ttPhong.getKieuPhong(), ttPhong.getKieuGiuong(), 
                ttPhong.getDonGia()});          
        }
    }
    public void reset()
    {
        curr_MaDatPhong = phieuDatPhongDAO.getCurrentMaDatPhong();
        Text_MaKhachHang.setText("");
        Text_TenKhachHang.setText("");
        Text_SoDienThoai.setText("");
        Text_TongTien.setText("");
        Text_TienTraTruoc.setText("");
        tblDanhSachPhongDat.setRowCount(0);
        try {
            String kieuphong = ComboBox_KieuPhong.getSelectedItem().toString();
            if (kieuphong.equals("Tất cả"))
                kieuphong = "";
            String kieugiuong = ComboBox_KieuGiuong.getSelectedItem().toString();
            if (kieugiuong.equals("Tất cả"))
                kieugiuong = "";

            listPhongTrong = new ThongTinPhongDAO().getListTTPhongTrong(kieuphong, kieugiuong ,
                    new java.sql.Date(jDateChooser_NgayNhan.getDate().getTime()), new java.sql.Date(jDateChooser_NgayTra.getDate().getTime()));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLiDatPhongJPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        showTableChiTietTTPhong();
    }
    public String getMaKH()
    {
        ChonKhachHang panel = new ChonKhachHang();
        int result =    JOptionPane.showConfirmDialog(null, panel, "Chọn khách hàng", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
        {
            return panel.returnMaKH();
        }
        else return null;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ComboBox_KieuPhong = new javax.swing.JComboBox<>();
        ComboBox_KieuGiuong = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_DanhSachPhongTrong = new javax.swing.JTable();
        Button_TimKiemPhong = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jDateChooser_NgayNhan = new com.toedter.calendar.JDateChooser();
        jDateChooser_NgayTra = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        Button_XacNhanKhachHang = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        Table_DanhSachPhongDat = new javax.swing.JTable();
        Button_ThemPhongDat = new javax.swing.JButton();
        Button_XoaPhongDat = new javax.swing.JButton();
        Button_TaoDonDatPhong = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        Text_TienTraTruoc = new javax.swing.JTextField();
        Text_TongTien = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Text_TenKhachHang = new javax.swing.JTextField();
        Text_SoDienThoai = new javax.swing.JTextField();
        Text_MaKhachHang = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1058, 644));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null));
        jPanel2.setPreferredSize(new java.awt.Dimension(1058, 642));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Kiểu phòng");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Kiểu giường");

        ComboBox_KieuPhong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ComboBox_KieuPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        ComboBox_KieuPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBox_KieuPhongActionPerformed(evt);
            }
        });

        ComboBox_KieuGiuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ComboBox_KieuGiuong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        Table_DanhSachPhongTrong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(Table_DanhSachPhongTrong);

        Button_TimKiemPhong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Button_TimKiemPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconFindCustomer.png"))); // NOI18N
        Button_TimKiemPhong.setText("Tìm phòng trống");
        Button_TimKiemPhong.setMaximumSize(new java.awt.Dimension(115, 40));
        Button_TimKiemPhong.setMinimumSize(new java.awt.Dimension(115, 40));
        Button_TimKiemPhong.setPreferredSize(new java.awt.Dimension(115, 40));
        Button_TimKiemPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_TimKiemPhongActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Ngày nhận");

        jDateChooser_NgayNhan.setDateFormatString("dd/MM/yyyy");
        jDateChooser_NgayNhan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jDateChooser_NgayTra.setDateFormatString("dd/MM/yyyy");
        jDateChooser_NgayTra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Ngày trả");

        Button_XacNhanKhachHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Button_XacNhanKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconCustomers.png"))); // NOI18N
        Button_XacNhanKhachHang.setText("Chọn khách hàng");
        Button_XacNhanKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_XacNhanKhachHangMouseClicked(evt);
            }
        });
        Button_XacNhanKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_XacNhanKhachHangActionPerformed(evt);
            }
        });

        Table_DanhSachPhongDat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table_DanhSachPhongDat.setColumnSelectionAllowed(true);
        jScrollPane4.setViewportView(Table_DanhSachPhongDat);
        Table_DanhSachPhongDat.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        Button_ThemPhongDat.setText("Xác nhận");
        Button_ThemPhongDat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_ThemPhongDatMouseClicked(evt);
            }
        });
        Button_ThemPhongDat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_ThemPhongDatActionPerformed(evt);
            }
        });

        Button_XoaPhongDat.setText("Quay lại");
        Button_XoaPhongDat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_XoaPhongDatMouseClicked(evt);
            }
        });
        Button_XoaPhongDat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_XoaPhongDatActionPerformed(evt);
            }
        });

        Button_TaoDonDatPhong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Button_TaoDonDatPhong.setText("Tạo đơn đặt phòng");
        Button_TaoDonDatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_TaoDonDatPhongMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Tiền trả trước");

        Text_TienTraTruoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        Text_TongTien.setEditable(false);
        Text_TongTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Tiền phòng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã khách hàng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên khách hàng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Số điện thoại");

        Text_TenKhachHang.setEditable(false);
        Text_TenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Text_SoDienThoai.setEditable(false);
        Text_SoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Text_MaKhachHang.setEditable(false);
        Text_MaKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Button_XacNhanKhachHang)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Text_TenKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Text_SoDienThoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Text_MaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboBox_KieuPhong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboBox_KieuGiuong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser_NgayNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser_NgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(Button_TimKiemPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Button_XoaPhongDat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_ThemPhongDat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Text_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Text_TienTraTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(143, 143, 143)))
                            .addComponent(Button_TaoDonDatPhong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Button_XacNhanKhachHang))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(Text_MaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(Text_TenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(Text_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Button_TimKiemPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ComboBox_KieuPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(ComboBox_KieuGiuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jDateChooser_NgayNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser_NgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Button_ThemPhongDat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(Button_XoaPhongDat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(Text_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Text_TienTraTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Button_TaoDonDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 111, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabel1.setText("Phiếu đặt phòng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 885, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Button_TimKiemPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_TimKiemPhongActionPerformed
        if (jDateChooser_NgayNhan.getDate()==null || jDateChooser_NgayTra.getDate()==null )
        {
            JOptionPane.showMessageDialog(this,"Yêu cầu nhập ngày nhận, ngày trả trước khi tra cứu danh sách phòng trống");
        } else if(jDateChooser_NgayTra.getDate().compareTo(jDateChooser_NgayNhan.getDate()) < 0){
            JOptionPane.showMessageDialog(this, "Ngày trả nhỏ hơn ngày nhận. Vui lòng kiểm tra lại.");
            return;
        }     
        else
        {
            String kieuPhong = ComboBox_KieuPhong.getSelectedItem().toString();
            try {
                String kieuphong = ComboBox_KieuPhong.getSelectedItem().toString();
                if (kieuphong.equals("Tất cả"))
                    kieuphong = "";
                String kieugiuong = ComboBox_KieuGiuong.getSelectedItem().toString();
                if (kieugiuong.equals("Tất cả"))
                    kieugiuong = "";
                    
                listPhongTrong = new ThongTinPhongDAO().getListTTPhongTrong(kieuphong, kieugiuong ,
                        new java.sql.Date(jDateChooser_NgayNhan.getDate().getTime()), new java.sql.Date(jDateChooser_NgayTra.getDate().getTime()));
            } catch (SQLException ex) {
                Logger.getLogger(QuanLiDatPhongJPane.class.getName()).log(Level.SEVERE, null, ex);
            }
            showTableChiTietTTPhong();
        }

        
    }//GEN-LAST:event_Button_TimKiemPhongActionPerformed
    
    private void Button_XacNhanKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_XacNhanKhachHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_XacNhanKhachHangMouseClicked

    private void Button_ThemPhongDatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_ThemPhongDatMouseClicked
        // TODO add your handling code here:
        long songayluutru;
        long diff = jDateChooser_NgayTra.getDate().getTime() - jDateChooser_NgayNhan.getDate().getTime();
        TimeUnit time = TimeUnit.DAYS; 
        songayluutru = time.convert(diff, TimeUnit.MILLISECONDS);
        if (songayluutru < 0)
            songayluutru = 0;
        else if (songayluutru == 0)
            songayluutru = 1;
        int indexTB = Table_DanhSachPhongTrong.getSelectedRow();
        if (indexTB < tblDanhSachPhongTrong.getRowCount() && indexTB >= 0){
            String selectedRoom = tblDanhSachPhongTrong.getValueAt(indexTB, 0).toString();
            Integer selectedPrice =  Integer.valueOf(tblDanhSachPhongTrong.getValueAt(indexTB, 3).toString());
            if (!listPhongDat.keySet().contains(selectedRoom))
            {
                listPhongDat.put(selectedRoom, selectedPrice);
                tblDanhSachPhongDat.addRow(new Object[]{selectedRoom});
                tongTien += listPhongDat.get(selectedRoom);
                Text_TongTien.setText(Integer.toString(tongTien*(int)songayluutru));
            }
        }

    }//GEN-LAST:event_Button_ThemPhongDatMouseClicked

    private void Button_XoaPhongDatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_XoaPhongDatMouseClicked
        // TODO add your handling code here:
        long songayluutru;
        long diff = jDateChooser_NgayTra.getDate().getTime() - jDateChooser_NgayNhan.getDate().getTime();
        TimeUnit time = TimeUnit.DAYS; 
        songayluutru = time.convert(diff, TimeUnit.MILLISECONDS);
        if (songayluutru < 0)
            songayluutru = 0;
        else if (songayluutru == 0)
            songayluutru = 1;

        int indexTB = Table_DanhSachPhongDat.getSelectedRow();
        if (indexTB < tblDanhSachPhongDat.getRowCount() && indexTB >= 0){
            String selectedRoom = tblDanhSachPhongDat.getValueAt(indexTB, 0).toString();
            tongTien -= listPhongDat.get(selectedRoom);
            listPhongDat.remove(selectedRoom);
            tblDanhSachPhongDat.removeRow(indexTB);
            Text_TongTien.setText(Integer.toString(tongTien*(int)songayluutru));
        }
    }//GEN-LAST:event_Button_XoaPhongDatMouseClicked

    private void Button_TaoDonDatPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_TaoDonDatPhongMouseClicked
        // TODO add your handling code here:
        if (Text_MaKhachHang.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this,"Người dùng chưa chọn khách hàng");
            return;
        }
        else if (tblDanhSachPhongDat.getRowCount()==0){
            JOptionPane.showMessageDialog(this,"Người dùng chưa chọn phòng để đặt");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date currentDate = sdf.parse(sdf.format(new Date()));
            Date NgayNhan = sdf.parse(sdf.format(jDateChooser_NgayNhan.getDate()));
            if(jDateChooser_NgayNhan.getDate().compareTo(currentDate) < 0){
                int ret = JOptionPane.showConfirmDialog(this, "Đã qua ngày nhận phòng. \nBạn có chắc chắn muốn đặt phòng?", "Ngày nhận phòng đã qua.", JOptionPane.YES_NO_OPTION);
                if(ret == JOptionPane.NO_OPTION){
                return;
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(NhanPhongJPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        phieuDatPhong.setMaDatPhong(curr_MaDatPhong);
        KhachHang k = new KhachHang();
        k.setMaKH(Integer.parseInt(Text_MaKhachHang.getText()));
        phieuDatPhong.setKhachHang(k);
        try {
            phieuDatPhong.setTienTraTruoc(Integer.parseInt(Text_TienTraTruoc.getText()));        
        } catch (NumberFormatException e) {
            phieuDatPhong.setTienTraTruoc(0);
        }
        
        phieuDatPhong.setNgayNhan(jDateChooser_NgayNhan.getDate());
        phieuDatPhong.setNgayTra(jDateChooser_NgayTra.getDate());
        try {
            if (phieuDatPhongDAO.TaoPhieuDatPhong(phieuDatPhong, new ArrayList<String>(listPhongDat.keySet()) ))
            {
                JOptionPane.showMessageDialog(this, "Thêm phiếu đặt phòng thành công.");
                this.reset();
            }
            else {
                JOptionPane.showMessageDialog(this,"Thêm phiếu đặt phòng thất bại");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLiDatPhongJPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Button_TaoDonDatPhongMouseClicked

    private void ComboBox_KieuGiuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBox_KieuGiuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBox_KieuGiuongActionPerformed

    private void ComboBox_KieuPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBox_KieuPhongActionPerformed
        // TODO add your handling code here:
        ComboBox_KieuGiuong.removeAllItems();
        showComboBox_KieuGiuong();

    }//GEN-LAST:event_ComboBox_KieuPhongActionPerformed

    private void Button_XacNhanKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_XacNhanKhachHangActionPerformed
        ChonKhachHang ttKH = new ChonKhachHang();
        int result = JOptionPane.showConfirmDialog(null, ttKH, "Chọn khách hàng", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
        {
            Text_MaKhachHang.setText(String.valueOf(ttKH.returnTTKH().getMaKH()));
            Text_TenKhachHang.setText(ttKH.returnTTKH().getTenKH());
            Text_SoDienThoai.setText(ttKH.returnTTKH().getSDT());
        }

    }//GEN-LAST:event_Button_XacNhanKhachHangActionPerformed

    private void Button_ThemPhongDatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_ThemPhongDatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_ThemPhongDatActionPerformed

    private void Button_XoaPhongDatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_XoaPhongDatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_XoaPhongDatActionPerformed
    public KhachHang getKhachHangValue()
    {
        KhachHang kh =new KhachHang();
        kh.setMaKH(Integer.parseInt(Text_MaKhachHang.getText()));
        return kh;

    }
    public void clearTextKhachHang()
    {
        Text_MaKhachHang.setText(Integer.toString(curr_MaKhachHang));
    }
    public void showComboBox_KieuPhong(){
        ArrayList<ThongTinPhong> ttKieuPhong = new ThongTinPhongDAO().getKieuPhong();
        for(ThongTinPhong data : ttKieuPhong){
            ComboBox_KieuPhong.addItem(data.getKieuPhong());
        }
    }
    
    public void showComboBox_KieuGiuong(){
        ArrayList<ThongTinPhong> ttKieuGiuong = new ThongTinPhongDAO().getKieuGiuong(ComboBox_KieuPhong.getSelectedItem().toString());
        for(ThongTinPhong data : ttKieuGiuong){
            ComboBox_KieuGiuong.addItem(String.valueOf(data.getKieuGiuong()));
        }
        ComboBox_KieuGiuong.addItem("Tất cả");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_TaoDonDatPhong;
    private javax.swing.JButton Button_ThemPhongDat;
    private javax.swing.JButton Button_TimKiemPhong;
    private javax.swing.JButton Button_XacNhanKhachHang;
    private javax.swing.JButton Button_XoaPhongDat;
    private javax.swing.JComboBox<String> ComboBox_KieuGiuong;
    private javax.swing.JComboBox<String> ComboBox_KieuPhong;
    private javax.swing.JTable Table_DanhSachPhongDat;
    private javax.swing.JTable Table_DanhSachPhongTrong;
    private javax.swing.JTextField Text_MaKhachHang;
    private javax.swing.JTextField Text_SoDienThoai;
    private javax.swing.JTextField Text_TenKhachHang;
    private javax.swing.JTextField Text_TienTraTruoc;
    private javax.swing.JTextField Text_TongTien;
    private com.toedter.calendar.JDateChooser jDateChooser_NgayNhan;
    private com.toedter.calendar.JDateChooser jDateChooser_NgayTra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
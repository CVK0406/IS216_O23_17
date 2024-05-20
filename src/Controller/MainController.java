package Controller;

import Model.Item;
import View.NhanPhongJPane;
import View.QuanLiDatPhongJPane;
import View.QuanLiDichVuJPane;
import View.ThemDonDichVuJPane;
import View.QuanLiKhachHangJPane;
import View.QuanLiNhanVienJPane;
import View.QuanLiPhongJPane;
import View.ThongKeJPane;
import View.TrangChuJPane;
import View.TraPhongJPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainController {
    private JPanel Root;
    private String kindeSelected = "";
    
    private List<Item> listItem = null;
    public MainController() {
    }
    
    public MainController(JPanel jpnRoot) {
        this.Root = jpnRoot;
    }
    
    public void setView (JPanel jpnItem, JLabel jlbItem){
        kindeSelected = "TrangChu";
        jpnItem.setBackground(new Color(0,120,215));
        jlbItem.setBackground(new Color(0,120,215));
        
        Root.removeAll();
        Root.setLayout(new BorderLayout());
        Root.add(new TrangChuJPane());
        Root.validate();
        Root.repaint();        
    }
    
    public void setEvent(List<Item> listItem){
        this.listItem = listItem;
        for(Item item: listItem){
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }


    class LabelEvent implements MouseListener{
        
        private JPanel node;
        
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            node = switch (kind) {
                case "TrangChu" -> new TrangChuJPane();
                case "QuanLiKhachHang" -> new QuanLiKhachHangJPane();
                case "QuanLiNhanVien" -> new QuanLiNhanVienJPane();
                case "QuanLiPhong" -> new QuanLiPhongJPane();
                case "ThongKe" -> new ThongKeJPane();
                case "QuanLiDichVu" -> new QuanLiDichVuJPane();
                case "ThemDonDichVu" -> new ThemDonDichVuJPane();
                case "QuanLiDatPhong" -> new QuanLiDatPhongJPane();
                case "CheckIn" -> new NhanPhongJPane();
                case "CheckOut" -> new TraPhongJPane();
                default -> new TrangChuJPane();
            };
            Root.removeAll();
            Root.repaint();
            Root.revalidate();
            
            
            Root.setLayout(new BorderLayout());
            Root.add(node);
            Root.repaint();            
            Root.revalidate();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindeSelected = kind;
            jpnItem.setBackground(new Color(0,120,215));
            jlbItem.setBackground(new Color(0,120,215));
            for(Item item: listItem){
                if(!kindeSelected.equalsIgnoreCase(item.getKind())){
                    item.getJpn().setBackground(new Color(0,175,225));
                    item.getJpn().setBackground(new Color(0,175,225));
                }   
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(0,120,215));
            jlbItem.setBackground(new Color(0,120,215));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!kindeSelected.equalsIgnoreCase(kind)){
                jpnItem.setBackground(new Color(0,175,225));
                jlbItem.setBackground(new Color(0,175,225));
            }
        }
    
    }
}

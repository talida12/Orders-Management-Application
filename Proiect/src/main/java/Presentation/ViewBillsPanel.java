package Presentation;

import DataAccessLayer.BillRepo;
import Models.Bill;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ViewBillsPanel extends JPanel {
    private static JPanel imagePanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("C:\\Users\\40756\\IdeaProjects\\TP-Assignment3\\src\\main\\java\\background.png"));
            } catch (IOException e) {
                System.out.println("error reading image");
            }
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
    };
    public JScrollPane createTable(List<Bill> bills) {
        String[] columnNames = {"ID", "Order Id", "Client Id", "Total", "Date"};
        String data[][] = new String[bills.size()][5];
        for (Bill bill: bills) {
            int i = bills.indexOf(bill);
            data[i][0] = String.valueOf(bill.id());
            data[i][1] = String.valueOf(bill.orderId());
            data[i][2] = String.valueOf(bill.clientId());
            data[i][3] = String.valueOf(bill.total());
            data[i][4] = String.valueOf(bill.date());
        }
        JTable table = new JTable(data, columnNames);
        table.setBackground(new Color(192, 192, 192));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getTableHeader().setBackground(new Color(41, 41, 41));
        Font font = new Font("Arial", Font.BOLD, 12);
        table.getTableHeader().setFont(font);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(Color.gray);
        table.setSelectionForeground(Color.white);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setUI(new GrayScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new GrayScrollBarUI());
        scrollPane.getVerticalScrollBar().setBackground(Color.GRAY);
        scrollPane.getHorizontalScrollBar().setBackground(Color.GRAY);
        scrollPane.setPreferredSize(new Dimension(450, 180));
        scrollPane.getViewport().setBackground(new Color(41, 41, 41));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(41, 41, 41),5));
        return scrollPane;
    }
    public ViewBillsPanel() {
        this.setLayout(new BorderLayout());
        UI.addPanelDesign(this, new Color(41, 41, 41));
        UI.addPanelDesign(imagePanel, new Color(41, 41, 41));
        BillRepo bill = new BillRepo();
        JScrollPane table = createTable(bill.findAll());
        this.add(imagePanel, BorderLayout.CENTER);
        this.add(table, BorderLayout.NORTH);
    }
}

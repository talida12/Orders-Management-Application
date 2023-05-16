package Presentation;

import DataAccessLayer.ClientRepo;
import DataAccessLayer.ProductRepo;
import Models.Client;
import Models.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewProductsPanel extends JPanel {
    public JScrollPane createTable(List<Product> products) {
        String[] columnNames = {"Product ID", "Name", "Quantity", "Price"};
        String data[][] = new String[products.size()][5];
        for (Product product : products) {
            int i = products.indexOf(product);
            data[i][0] = String.valueOf(product.getId());
            data[i][1] = String.valueOf(product.getName());
            data[i][2] = String.valueOf(product.getQuantity());
            data[i][3] = String.format("%.2f", product.getPrice());
        }
        JTable table = new JTable(data, columnNames);
        table.setBackground(new Color(192, 192, 192));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        Font font = new Font("Arial", Font.BOLD, 12);
        table.getTableHeader().setFont(font);
        table.getTableHeader().setBackground(new Color(41, 41, 41));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(Color.gray);
        table.setSelectionForeground(Color.white);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setUI(new GrayScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new GrayScrollBarUI());
        scrollPane.getVerticalScrollBar().setBackground(Color.GRAY);
        scrollPane.getHorizontalScrollBar().setBackground(Color.GRAY);
        scrollPane.setPreferredSize(new Dimension(450, 240));
        scrollPane.getViewport().setBackground(new Color(41, 41, 41));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(41, 41, 41),5));
        return scrollPane;
    }

    public ViewProductsPanel() {
        UI.addPanelDesign(this, new Color(41, 41, 41));
        ProductRepo product = new ProductRepo();
        JScrollPane table = createTable(product.findAll());
        this.add(table);
    }
}

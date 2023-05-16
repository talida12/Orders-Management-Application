package Presentation;

import DataAccessLayer.ClientRepo;
import DataAccessLayer.OrderRepo;
import Models.Order;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.List;

public class ViewOrdersPanel extends JPanel {
    public JScrollPane createTable(List<Order> orders) {
        String[] columnNames = {"Order ID", "Client ID", "Product ID", "Nr Products", "Order Total"};
        String data[][] = new String[orders.size()][5];
        for (Order order : orders) {
            int i = orders.indexOf(order);
            data[i][0] = String.valueOf(order.getId());
            data[i][1] = String.valueOf(order.getClientId());
            data[i][2] = String.valueOf(order.getProductId());
            data[i][3] = String.valueOf(order.getNrProducts());
            data[i][4] = String.format("%.2f", order.getOrderTotal());
        }
        JTable table = new JTable(data, columnNames);
        table.setBackground(new Color(192, 192, 192));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(70);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(70);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
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
        scrollPane.setPreferredSize(new Dimension(400, 150));
        scrollPane.getViewport().setBackground(new Color(41, 41, 41));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(41, 41, 41),5));
        return scrollPane;
    }

    public ViewOrdersPanel() {
        UI.addPanelDesign(this, new Color(41, 41, 41));
        OrderRepo order = new OrderRepo();
        JScrollPane table = createTable(order.findAll());
        this.add(table);
    }
}

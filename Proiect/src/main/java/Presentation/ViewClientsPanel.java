package Presentation;

import BusinessLayer.Service;
import Models.Client;
import DataAccessLayer.ClientRepo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewClientsPanel extends JPanel {
    public JScrollPane createTable(List<Client> clients) {
        String[] columnNames = {"ID", "Name", "Address", "Email", "Age"};
        String data[][] = new String[clients.size()][5];
        for (Client client : clients) {
            int i = clients.indexOf(client);
            data[i][0] = String.valueOf(client.getId());
            data[i][1] = String.valueOf(client.getName());
            data[i][2] = String.valueOf(client.getAddress());
            data[i][3] = String.valueOf(client.getEmail());
            data[i][4] = String.valueOf(client.getAge());
        }
        JTable table = new JTable(data, columnNames);
        table.setBackground(new Color(192, 192, 192));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(110);
        table.getColumnModel().getColumn(3).setPreferredWidth(130);
        table.getColumnModel().getColumn(4).setPreferredWidth(30);
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

    public ViewClientsPanel() {
        UI.addPanelDesign(this, new Color(41, 41, 41));
        ClientRepo client = new ClientRepo();
        JScrollPane table = createTable(client.findAll());
        this.add(table);
    }
}

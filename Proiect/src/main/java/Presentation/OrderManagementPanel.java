package Presentation;

import BusinessLayer.Service;
import BusinessLayer.ServiceException;
import DataAccessLayer.BillRepo;
import DataAccessLayer.OrderRepo;
import DataAccessLayer.ProductRepo;
import Models.Bill;
import Models.Client;
import Models.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderManagementPanel extends JPanel{
    private static JLabel selectIdLabel = new JLabel("Insert the order id:                ");
    private static JTextField selectIdTF = new JTextField(20);
    private static JLabel selectClientLabel = new JLabel("Insert the client id:                ");
    private static JTextField selectClientText = new JTextField(20);
    private static JLabel selectProductLabel = new JLabel("Insert the product id:             ");
    private static JTextField selectProductText = new JTextField(20);
    private static JLabel selectQuantityLabel = new JLabel("Insert the desired quantity:  ");
    private static JTextField selectQuantityText = new JTextField(20);
    private static JButton processOrderButton = new JButton("Process Order");
    private static JLabel deleteIdLabel = new JLabel("Order ID to delete:                 ");
    private static JTextField deleteIdTF = new JTextField(20);
    private static JButton deleteOrderButton = new JButton("DELETE");
    private static JButton viewOrdersButton = new JButton("View Orders Table");
    private static JPanel panel0 = new JPanel();
    private static JPanel panel1 = new JPanel();
    private static JPanel panel2 = new JPanel();
    private static JPanel panel3 = new JPanel();
    private static JPanel panel4 = new JPanel();
    private static JPanel panel5 = new JPanel();
    private static JPanel panel6 = new JPanel();
    private static JPanel panel7 = new JPanel();
    private static JPanel bottomPanel = new JPanel();
    private  CardLayout cardLayout = new CardLayout();
    private  JPanel upperPanel = new JPanel(cardLayout);
    private static JPanel emptyPanel = new JPanel();
    private static ProductRepo productRepo = new ProductRepo();
    private static BillRepo billRepo = new BillRepo();
    private void styleComponents() {
        UI.addPanelDesign(this, new Color(41, 41, 41));
        UI.makeButtonStyle(processOrderButton, Color.GRAY, new Color(255, 255, 255));
        UI.makeButtonStyle(viewOrdersButton, Color.GRAY, new Color(255, 255, 255));
        UI.makeButtonStyle(deleteOrderButton, Color.GRAY, new Color(255, 255, 255));
        processOrderButton.setPreferredSize(new Dimension(450, 30));
        viewOrdersButton.setPreferredSize(new Dimension(450, 30));
        deleteOrderButton.setPreferredSize(new Dimension(450, 30));
        UI.makeTextStyle(selectIdLabel);
        UI.makeTextStyle(selectClientLabel);
        UI.makeTextStyle(selectProductLabel);
        UI.makeTextStyle(selectQuantityLabel);
        UI.makeTextStyle(deleteIdLabel);
        UI.addPanelDesign(panel0, new Color(41, 41, 41));
        UI.addPanelDesign(panel1, new Color(41, 41, 41));
        UI.addPanelDesign(panel2, new Color(41, 41, 41));
        UI.addPanelDesign(panel3, new Color(41, 41, 41));
        UI.addPanelDesign(panel4, new Color(41, 41, 41));
        UI.addPanelDesign(panel5, new Color(41, 41, 41));
        UI.addPanelDesign(panel6, new Color(41, 41, 41));
        UI.addPanelDesign(panel7, new Color(41, 41, 41));
        UI.addPanelDesign(emptyPanel, new Color(41, 41, 41));
        UI.addPanelDesign(upperPanel, new Color(41, 41, 41));
    }
    private void layoutComponents() {
        this.setLayout(new BorderLayout());
        panel0.add(selectIdLabel);
        panel0.add(selectIdTF);
        panel1.add(selectClientLabel);
        panel1.add(selectClientText);
        panel2.add(selectProductLabel);
        panel2.add(selectProductText);
        panel3.add(selectQuantityLabel);
        panel3.add(selectQuantityText);
        panel4.add(processOrderButton);
        panel6.add(deleteIdLabel);
        panel6.add(deleteIdTF);
        panel7.add(deleteOrderButton);
        panel5.add(viewOrdersButton);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(panel0);
        bottomPanel.add(panel1);
        bottomPanel.add(panel2);
        bottomPanel.add(panel3);
        bottomPanel.add(panel4);
        bottomPanel.add(panel4);;
        bottomPanel.add(panel6);
        bottomPanel.add(panel7);
        bottomPanel.add(panel5);
        upperPanel.add(emptyPanel, "emptyPanel");
        this.add(upperPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
        upperPanel.setVisible(false);
    }

    public void addViewOrdersButtonListener()  {
        viewOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewOrdersPanel viewOrdersP = new ViewOrdersPanel();
                upperPanel.add(viewOrdersP, "viewOrders");
                cardLayout.show(upperPanel, "viewOrders");
                upperPanel.setVisible(true);
            }
        });
    }

    public void addProcessOrderButtonListener()  {
        processOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(upperPanel, "emptyPanel");
                upperPanel.setVisible(true);
                try {
                    Service.getInstance().addOrder(selectIdTF.getText(), selectClientText.getText(), selectProductText.getText(),
                            selectQuantityText.getText());
                    productRepo.updateQuantity(Integer.parseInt(selectProductText.getText()), Integer.parseInt(selectQuantityText.getText()));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    LocalDate currentDate = LocalDate.now();
                   // LocalDateTime currentDateTime = currentDate.atStartOfDay();

                    Service.getInstance().addBill( billRepo.getBiggestId() + 1,
                            Integer.parseInt(selectIdTF.getText()),
                            Integer.parseInt(selectClientText.getText()),
                            productRepo.findProductById(Integer.parseInt(selectProductText.getText())).getPrice() * Integer.parseInt(selectQuantityText.getText()),
                            currentDate.atStartOfDay());
                    JOptionPane.showMessageDialog(null, "The order was processed successfully!");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
    public void addDeleteOrderButtonListener()  {
        deleteOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(upperPanel, "emptyPanel");
                upperPanel.setVisible(true);
                try {
                    Service.getInstance().deleteOrder(deleteIdTF.getText());
                    JOptionPane.showMessageDialog(null, "The order was deleted successfully!");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
    public void addActionListeners() {
        addViewOrdersButtonListener();
        addProcessOrderButtonListener();
        addDeleteOrderButtonListener();
    }
    public OrderManagementPanel() {
        styleComponents();
        layoutComponents();
        addActionListeners();
    }
    
}

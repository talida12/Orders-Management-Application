package Presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.io.*;
import javax.swing.*;

public class UI {
    private static JFrame frame = new JFrame("Assignment 3 - Orders Management");
    private static JLabel title = new JLabel("Orders Management");
    private static JSplitPane mainPanel = new JSplitPane();
    private static JPanel leftPanel = new JPanel();
    private static JPanel rightPanel = new JPanel();
    private static JPanel titlePanel = new JPanel();
    private static JPanel imagePanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("C:\\Users\\40756\\IdeaProjects\\TP-Assignment3\\src\\main\\java\\background3.png"));
            } catch (IOException e) {
                System.out.println("error reading image");
            }
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
    };
    private static JPanel chooseButtonPanel1 = new JPanel();
    private static JPanel chooseButtonPanel2 = new JPanel();
    private static JPanel chooseButtonPanel3 = new JPanel();
    private static JPanel chooseButtonPanel4 = new JPanel();
    private static JButton clientsButton = new JButton("Manage Clients");
    private static JButton productsButton = new JButton("Manage Products");
    private static JButton ordersButton = new JButton("Manage Orders");
    private static JButton logButton = new JButton("View Log Table");

    public static void makeButtonStyle(JButton btn, Color color1, Color color2) {
        btn.setBackground(color1);
        btn.setForeground(color2);
        btn.setFont(new Font(Font.SERIF, Font.BOLD, 12));
    }
    public static void addPanelDesign(JPanel panel, Color color) {
        panel.setForeground(color);
        panel.setBackground(color);
    }
    public static void makeTextStyle(JLabel text) {
        text.setFont(new Font("Times New Roman", Font.BOLD, 13));
        text.setForeground(Color.WHITE);
    }

    public static void styleComponents() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(41, 41, 41));
        rightPanel.setPreferredSize(new Dimension(450, 500));
        frame.setSize(900, 470);
        mainPanel.setLeftComponent(leftPanel);
        title.setFont(new Font("Times New Roman", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        UI.addPanelDesign(titlePanel, new Color(41, 41, 41));
        UI.addPanelDesign(imagePanel, new Color(41, 41, 41));
        clientsButton.setPreferredSize(new Dimension(350, 30));
        productsButton.setPreferredSize(new Dimension(350, 30));
        ordersButton.setPreferredSize(new Dimension(350, 30));
        logButton.setPreferredSize(new Dimension(350, 30));
        makeButtonStyle(clientsButton, Color.GRAY, new Color(255, 255, 255));
        makeButtonStyle(productsButton, Color.GRAY, new Color(255, 255, 255));
        makeButtonStyle(ordersButton, Color.GRAY, new Color(255, 255, 255));
        makeButtonStyle(logButton, Color.GRAY, new Color(255, 255, 255));
        addPanelDesign(chooseButtonPanel1, new Color(41, 41, 41));
        addPanelDesign(chooseButtonPanel2, new Color(41, 41, 41));
        addPanelDesign(chooseButtonPanel3, new Color(41, 41, 41));
        addPanelDesign(chooseButtonPanel4, new Color(41, 41, 41));
        addPanelDesign(rightPanel, new Color(41, 41, 41));
        mainPanel.setRightComponent(rightPanel);
        mainPanel.setResizeWeight(0.20);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setMinimumSize(new Dimension(0, 0));
    }

    public static void layoutComponents() {
        titlePanel.add(title);
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(titlePanel, BorderLayout.NORTH);
        leftPanel.add(Box.createVerticalGlue(), BorderLayout.CENTER);

        leftPanel.add(imagePanel);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        chooseButtonPanel1.add(clientsButton);
        chooseButtonPanel2.add(productsButton);
        chooseButtonPanel3.add(ordersButton);
        chooseButtonPanel4.add(logButton);
        buttonsPanel.add(chooseButtonPanel1);
        buttonsPanel.add(chooseButtonPanel2);
        buttonsPanel.add(chooseButtonPanel3);
        buttonsPanel.add(chooseButtonPanel4);
        leftPanel.add(buttonsPanel, BorderLayout.PAGE_END);

        mainPanel.setLeftComponent(leftPanel);
        mainPanel.setRightComponent(rightPanel);
        mainPanel.setResizeWeight(0.25);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
    public static void addClientButtonListener()  {
        clientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JPanel newRightPanel = new ClientManagementPanel();
                newRightPanel.setPreferredSize(new Dimension(280, 450));
                mainPanel.setRightComponent(newRightPanel);
            }
        });
    }

    public static void addProductsButtonListener()  {
        productsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JPanel newRightPanel = new ProductManagementPanel();
                newRightPanel.setPreferredSize(new Dimension(280, 450));
                mainPanel.setRightComponent(newRightPanel);
            }
        });
    }
    public static void addOrdersButtonListener()  {
        ordersButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JPanel newRightPanel = new OrderManagementPanel();
                newRightPanel.setPreferredSize(new Dimension(280, 450));
                mainPanel.setRightComponent(newRightPanel);
            }
        });
    }

    public static void addLogButtonListener() {
        logButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JPanel newRightPanel = new ViewBillsPanel();
                newRightPanel.setPreferredSize(new Dimension(280, 450));
                mainPanel.setRightComponent(newRightPanel);
            }
        });
    }
    public static void addButtonListeners() {
        UI.addClientButtonListener();
        UI.addProductsButtonListener();
        UI.addOrdersButtonListener();
        UI.addLogButtonListener();
    }

    public static void main(String[] args) {
        UI.styleComponents();
        UI.layoutComponents();
        UI.addButtonListeners();
    }
}

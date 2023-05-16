package Presentation;

import Models.Product;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductManagementPanel extends JPanel {
    private static JButton addNewProductButton = new JButton("Add New Product");
    private static JButton editProductButton = new JButton("Edit Product");
    private static JButton deleteProductButton = new JButton("Delete Product");
    private static JButton viewProductsButton = new JButton("View Products Table");
    private static JPanel buttonsPanel = new JPanel();
    private static JPanel buttonsPanel1 = new JPanel();
    private static JPanel buttonsPanel2 = new JPanel();
    private static JPanel buttonsPanel3 = new JPanel();
    private static JPanel buttonsPanel4 = new JPanel();
    private  CardLayout cardLayout = new CardLayout();
    private  JPanel upperPanel = new JPanel(cardLayout);
    private JPanel addProductP = new AddNewProductPanel();
    private JPanel editProductP = new EditProductPanel();
    private JPanel deleteProductP = new DeleteProductPanel();

    public void styleComponents() {
        UI.addPanelDesign(this, new Color(41, 41, 41));
        UI.makeButtonStyle(addNewProductButton, Color.GRAY, new Color(255, 255, 255));
        UI.makeButtonStyle(editProductButton, Color.GRAY, new Color(255, 255, 255));
        UI.makeButtonStyle(deleteProductButton, Color.GRAY, new Color(255, 255, 255));
        UI.makeButtonStyle(viewProductsButton, Color.GRAY, new Color(255, 255, 255));
        addNewProductButton.setPreferredSize(new Dimension(450, 25));
        editProductButton.setPreferredSize(new Dimension(450, 25));
        deleteProductButton.setPreferredSize(new Dimension(450, 25));
        viewProductsButton.setPreferredSize(new Dimension(450, 25));
        UI.addPanelDesign(buttonsPanel, new Color(41, 41, 41));
        UI.addPanelDesign(buttonsPanel1, new Color(41, 41, 41));
        UI.addPanelDesign(buttonsPanel2, new Color(41, 41, 41));
        UI.addPanelDesign(buttonsPanel3, new Color(41, 41, 41));
        UI.addPanelDesign(buttonsPanel4, new Color(41, 41, 41));
        buttonsPanel.setPreferredSize(new Dimension(200, 150));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
    }

    private void layoutComponents() {
        this.setLayout(new BorderLayout());
        buttonsPanel1.add(addNewProductButton);
        buttonsPanel2.add(editProductButton);
        buttonsPanel3.add(deleteProductButton);
        buttonsPanel4.add(viewProductsButton);
        buttonsPanel.add(buttonsPanel1);
        buttonsPanel.add(buttonsPanel2);
        buttonsPanel.add(buttonsPanel3);
        buttonsPanel.add(buttonsPanel4);
        upperPanel.add(addProductP, "addProduct");
        upperPanel.add(editProductP, "editProduct");
        upperPanel.add(deleteProductP, "deleteProduct");
        this.add(upperPanel, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.SOUTH);
        upperPanel.setVisible(false);
    }
    public void addAddProductButtonListener()  {
        addNewProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(upperPanel, "addProduct");
                upperPanel.setVisible(true);
            }
        });
    }
    public void addEditProductButtonListener()  {
        editProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(upperPanel, "editProduct");
                upperPanel.setVisible(true);
            }
        });
    }
    public void addDeleteProductButtonListener()  {
        deleteProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(upperPanel, "deleteProduct");
                upperPanel.setVisible(true);
            }
        });
    }
    public void addViewProductsButtonListener()  {
        viewProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewProductsPanel viewProductsP = new ViewProductsPanel();
                upperPanel.add(viewProductsP, "viewProducts");
                cardLayout.show(upperPanel, "viewProducts");
                upperPanel.setVisible(true);
            }
        });
    }
    public void addActionListeners() {
        addAddProductButtonListener();
        addEditProductButtonListener();
        addDeleteProductButtonListener();
        addViewProductsButtonListener();
    }
    public ProductManagementPanel() {
        styleComponents();
        layoutComponents();
        addActionListeners();
    }

}
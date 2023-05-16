package Presentation;

import BusinessLayer.Service;
import BusinessLayer.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewProductPanel extends JPanel{
    private static JLabel newProductIdLabel = new JLabel("Insert the id:            ");
    private static JTextField newProductIdTF = new JTextField(20);
    private static JLabel newProductNameLabel = new JLabel("Insert the name:     ");
    private static JTextField newProductNameTF = new JTextField(20);
    private static JLabel newProductQuantityLabel = new JLabel("Insert the quantity:");
    private static JTextField newProductQuantityTF = new JTextField(20);
    private static JLabel newProductPriceLabel = new JLabel("Insert the price:     ");
    private static JTextField newProductPriceTF = new JTextField(20);
    private static JButton addProductButton = new JButton("ADD");
    private static JPanel panel0 = new JPanel();
    private static JPanel panel1 = new JPanel();
    private static JPanel panel2 = new JPanel();
    private static JPanel panel3 = new JPanel();
    private static JPanel panel4 = new JPanel();
    private void styleComponents() {
        UI.makeButtonStyle(addProductButton, Color.GRAY, new Color(255, 255, 255));
        UI.addPanelDesign(this, new Color(41, 41, 41));
        UI.addPanelDesign(panel0, new Color(41, 41, 41));
        UI.addPanelDesign(panel1, new Color(41, 41, 41));
        UI.addPanelDesign(panel2, new Color(41, 41, 41));
        UI.addPanelDesign(panel3, new Color(41, 41, 41));
        UI.addPanelDesign(panel4, new Color(41, 41, 41));
        UI.makeTextStyle(newProductIdLabel);
        UI.makeTextStyle(newProductNameLabel);
        UI.makeTextStyle(newProductQuantityLabel);
        UI.makeTextStyle(newProductPriceLabel);
        addProductButton.setPreferredSize(new Dimension(450, 30));
    }
    private void layoutComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        panel0.add(newProductIdLabel);
        panel0.add(newProductIdTF);
        panel1.add(newProductNameLabel);
        panel1.add(newProductNameTF);
        panel2.add(newProductQuantityLabel);
        panel2.add(newProductQuantityTF);
        panel3.add(newProductPriceLabel);
        panel3.add(newProductPriceTF);
        panel4.add(addProductButton);
        this.add(panel0);
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
    }

    public void addADDListener()  {
        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Service.getInstance().addProduct(newProductIdTF.getText(), newProductNameTF.getText(), newProductQuantityTF.getText(),
                            newProductPriceTF.getText());
                    JOptionPane.showMessageDialog(null, "The product was added successfully!");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
    public void addActionListeners() {
        addADDListener();
    }
    public AddNewProductPanel() {
        styleComponents();
        layoutComponents();
        addActionListeners();
    }
}

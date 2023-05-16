package Presentation;

import BusinessLayer.Service;
import BusinessLayer.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProductPanel extends JPanel{
    private static JLabel productIdToEditLabel = new JLabel("Product id to modify:      ");
    private static JTextField productIdToEditTF = new JTextField(15);
    private static JLabel newProductNameLabel = new JLabel("Insert new name:            ");
    private static JTextField newProductNameTF = new JTextField(15);
    private static JLabel newProductQuantityLabel = new JLabel("Insert new quantity:       ");
    private static JTextField newProductQuantityTF  = new JTextField(15);
    private static JLabel newProductPriceLabel = new JLabel("Insert new price:            ");
    private static JTextField newProductPriceTF = new JTextField(15);
    private static JButton editProductButton = new JButton("EDIT");
    private static JPanel panel1 = new JPanel();
    private static JPanel panel2 = new JPanel();
    private static JPanel panel3 = new JPanel();
    private static JPanel panel4 = new JPanel();
    private static JPanel panel5 = new JPanel();
    private void styleComponents() {
        UI.makeButtonStyle(editProductButton, Color.GRAY, new Color(255, 255, 255));
        UI.addPanelDesign(this, new Color(41, 41, 41));
        UI.addPanelDesign(panel1, new Color(41, 41, 41));
        UI.addPanelDesign(panel2, new Color(41, 41, 41));
        UI.addPanelDesign(panel3, new Color(41, 41, 41));
        UI.addPanelDesign(panel4, new Color(41, 41, 41));
        UI.addPanelDesign(panel5, new Color(41, 41, 41));
        UI.makeTextStyle(productIdToEditLabel);
        UI.makeTextStyle(newProductNameLabel);
        UI.makeTextStyle(newProductQuantityLabel);
        UI.makeTextStyle(newProductPriceLabel);
        editProductButton.setPreferredSize(new Dimension(450, 30));
    }
    private void layoutComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        panel1.add(productIdToEditLabel);
        panel1.add(productIdToEditTF);
        panel2.add(newProductNameLabel);
        panel2.add(newProductNameTF);
        panel3.add(newProductQuantityLabel);
        panel3.add(newProductQuantityTF);
        panel4.add(newProductPriceLabel);
        panel4.add(newProductPriceTF);
        panel5.add(editProductButton);
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
    }

    public void addEDITListener()  {
        editProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Service.getInstance().updateProduct(productIdToEditTF.getText(),
                            newProductNameTF.getText(), newProductQuantityTF.getText(), newProductPriceTF.getText());
                    JOptionPane.showMessageDialog(null, "The product was updated successfully!");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    public void addActionListeners() {
        addEDITListener();
    }
    public EditProductPanel() {
        styleComponents();
        layoutComponents();
        addActionListeners();
    }
}


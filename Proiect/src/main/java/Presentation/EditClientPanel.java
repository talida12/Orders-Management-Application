package Presentation;

import BusinessLayer.Service;
import BusinessLayer.ServiceException;
import DataAccessLayer.ClientRepo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditClientPanel extends JPanel {
    private static JLabel clientIdToEditLabel = new JLabel("Client id:        ");
    private static JTextField clientIdToEditTF = new JTextField(15);
    private static JLabel newNameLabel = new JLabel("New name:     ");
    private static JTextField newNameTF = new JTextField(15);
    private static JLabel newAddressLabel = new JLabel("New address: ");
    private static JTextField newAddressTF = new JTextField(15);
    private static JLabel newEmailLabel = new JLabel("New Email:     ");
    private static JTextField newEmailTF = new JTextField(15);
    private static JLabel newAgeLabel = new JLabel("New age:         ");
    private static JTextField newAgeTF  = new JTextField(15);
    private static JButton editButton = new JButton("EDIT");
    private static JPanel panel1 = new JPanel();
    private static  JPanel panel2 = new JPanel();
    private static JPanel panel3 = new JPanel();
    private static JPanel panel4 = new JPanel();
    private static JPanel panel5 = new JPanel();
    private static JPanel panel6 = new JPanel();
    private void styleComponents() {
        UI.makeButtonStyle(editButton, Color.GRAY, new Color(255, 255, 255));
        UI.addPanelDesign(this, new Color(41, 41, 41));
        UI.addPanelDesign(panel1, new Color(41, 41, 41));
        UI.addPanelDesign(panel2, new Color(41, 41, 41));
        UI.addPanelDesign(panel3, new Color(41, 41, 41));
        UI.addPanelDesign(panel4, new Color(41, 41, 41));
        UI.addPanelDesign(panel5, new Color(41, 41, 41));
        UI.addPanelDesign(panel6, new Color(41, 41, 41));
        UI.makeTextStyle(clientIdToEditLabel);
        UI.makeTextStyle(newNameLabel);
        UI.makeTextStyle(newAddressLabel);
        UI.makeTextStyle(newEmailLabel);
        UI.makeTextStyle(newAgeLabel);
        editButton.setPreferredSize(new Dimension(450, 30));
    }

    private void layoutComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        panel1.add(clientIdToEditLabel);
        panel1.add(clientIdToEditTF);
        panel2.add(newNameLabel);
        panel2.add(newNameTF);
        panel3.add(newAddressLabel);
        panel3.add(newAddressTF);
        panel4.add(newEmailLabel);
        panel4.add(newEmailTF);
        panel5.add(newAgeLabel);
        panel5.add(newAgeTF);
        panel6.add(editButton);
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
        this.add(panel6);
    }
    public void addEDITListener()  {
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Service.getInstance().updateClient(clientIdToEditTF.getText(),
                            newNameTF.getText(), newAddressTF.getText(), newEmailTF.getText(),
                            newAgeTF.getText());
                    JOptionPane.showMessageDialog(null, "The client was updated successfully!");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
    public void addActionListeners() {
        addEDITListener();
    }
    public EditClientPanel() {
        styleComponents();
        layoutComponents();
        addActionListeners();
    }

}

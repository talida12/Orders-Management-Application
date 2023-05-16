package Presentation;

import BusinessLayer.Service;
import BusinessLayer.ServiceException;
import DataAccessLayer.ClientRepo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddClientPanel extends JPanel {
    private static JLabel newClientIdLabel= new JLabel("Insert the new client's id:          ");
    private static JTextField newClientIdTF = new JTextField(20);
    private static JLabel newClientNameLabel = new JLabel("Insert the new client's name:    ");
    private static JTextField newClientNameTF = new JTextField(20);
    private static JLabel newClientAgeLabel = new JLabel("Insert the new client's age:        ");
    private static JTextField newClientAgeTF = new JTextField(20);
    private static JLabel newClientAddressLabel = new JLabel("Insert the new client's address:");
    private static JTextField newClientAddressTF = new JTextField(20);
    private static JLabel newClientEmailLabel = new JLabel("Insert the new client's email:    ");
    private static JTextField newClientEmailTF = new JTextField(20);
    private static JButton addButton = new JButton("ADD");
    private static JPanel panel1 = new JPanel();
    private static JPanel panel2 = new JPanel();
    private static JPanel panel3 = new JPanel();
    private static JPanel panel4 = new JPanel();
    private static JPanel panel5 = new JPanel();
    private static JPanel panel6 = new JPanel();
    private void styleComponents() {
        UI.makeButtonStyle(addButton, Color.GRAY, new Color(255, 255, 255));
        UI.addPanelDesign(this, new Color(41, 41, 41));
        UI.addPanelDesign(panel1, new Color(41, 41, 41));
        UI.addPanelDesign(panel2, new Color(41, 41, 41));
        UI.addPanelDesign(panel3, new Color(41, 41, 41));
        UI.addPanelDesign(panel4, new Color(41, 41, 41));
        UI.addPanelDesign(panel5, new Color(41, 41, 41));
        UI.addPanelDesign(panel6, new Color(41, 41, 41));
        UI.makeTextStyle(newClientIdLabel);
        UI.makeTextStyle(newClientNameLabel);
        UI.makeTextStyle(newClientAgeLabel);
        UI.makeTextStyle(newClientAddressLabel);
        UI.makeTextStyle(newClientEmailLabel);
        addButton.setPreferredSize(new Dimension(450, 30));
    }

    private void layoutComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        panel1.add(newClientIdLabel);
        panel1.add(newClientIdTF);
        panel2.add(newClientNameLabel);
        panel2.add(newClientNameTF);
        panel3.add(newClientAgeLabel);
        panel3.add(newClientAgeTF);
        panel4.add(newClientAddressLabel);
        panel4.add(newClientAddressTF);
        panel5.add(newClientEmailLabel);
        panel5.add(newClientEmailTF);
        panel6.add(addButton);
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
        this.add(panel6);
    }

    public void addADDListener()  {
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Service.getInstance().addClient(newClientIdTF.getText(), newClientNameTF.getText(), newClientAddressTF.getText(),
                            newClientEmailTF.getText(), newClientAgeTF.getText());
                    JOptionPane.showMessageDialog(null, "The client was added successfully!");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
    public void addActionListeners() {
        addADDListener();
    }
    public AddClientPanel() {
        styleComponents();
        layoutComponents();
        addActionListeners();
    }

}

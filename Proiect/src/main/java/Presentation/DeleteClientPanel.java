package Presentation;

import BusinessLayer.Service;
import BusinessLayer.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteClientPanel extends JPanel {
    private static JLabel clientIdToDeleteLabel = new JLabel("Insert the id of the client you want to delete:");
    private static JTextField clientIdToDeleteTF = new JTextField(20);
    private static JButton deleteButton = new JButton("DELETE");
    private static JPanel panel1 = new JPanel();
    private static JPanel panel2 = new JPanel();
    private static JPanel panel3 = new JPanel();
    private void styleComponents() {
        UI.makeButtonStyle(deleteButton, Color.GRAY, new Color(255, 255, 255));
        UI.addPanelDesign(this, new Color(41, 41, 41));
        UI.addPanelDesign(panel1, new Color(41, 41, 41));
        UI.addPanelDesign(panel2, new Color(41, 41, 41));
        UI.addPanelDesign(panel3, new Color(41, 41, 41));
        UI.makeTextStyle(clientIdToDeleteLabel);
        deleteButton.setPreferredSize(new Dimension(450, 30));
    }
    private void layoutComponents() {
        panel1.add(clientIdToDeleteLabel);
        panel2.add(clientIdToDeleteTF);
        panel3.add(deleteButton);
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
    }
    public void addDELETEListener()  {
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Service.getInstance().deleteClient(clientIdToDeleteTF.getText());
                    JOptionPane.showMessageDialog(null, "The client was deleted successfully!");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
    public void addActionListeners() {
        addDELETEListener();
    }
    public DeleteClientPanel() {
        styleComponents();
        layoutComponents();
        addActionListeners();
    }
}

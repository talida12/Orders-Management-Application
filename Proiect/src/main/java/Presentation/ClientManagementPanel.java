package Presentation;

import Models.Client;
import Models.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClientManagementPanel extends JPanel {
    private static JButton addNewClientButton = new JButton("Add New Client");
    private static JButton editClientButton = new JButton("Edit Client");
    private static JButton deleteClientButton = new JButton("Delete Client");
    private static JButton viewClientsButton = new JButton("View Clients Table");
    private static JPanel buttonsPanel = new JPanel();
    private static JPanel buttonsPanel1 = new JPanel();
    private static JPanel buttonsPanel2 = new JPanel();
    private static JPanel buttonsPanel3 = new JPanel();
    private static JPanel buttonsPanel4 = new JPanel();
    private static JPanel addClientP = new AddClientPanel();
    private static JPanel deleteClientP = new DeleteClientPanel();
    private static JPanel editClientP = new EditClientPanel();
    private  CardLayout cardLayout = new CardLayout();
    private  JPanel upperPanel = new JPanel(cardLayout);
    private static ViewClientsPanel viewClientsP;
    private void styleComponents() {
        UI.addPanelDesign(this, new Color(41, 41, 41));
        UI.makeButtonStyle(addNewClientButton, Color.GRAY, new Color(255, 255, 255));
        UI.makeButtonStyle(editClientButton, Color.GRAY, new Color(255, 255, 255));
        UI.makeButtonStyle(deleteClientButton, Color.GRAY, new Color(255, 255, 255));
        UI.makeButtonStyle(viewClientsButton, Color.GRAY, new Color(255, 255, 255));
        addNewClientButton.setPreferredSize(new Dimension(450, 25));
        editClientButton.setPreferredSize(new Dimension(450, 25));
        deleteClientButton.setPreferredSize(new Dimension(450, 25));
        viewClientsButton.setPreferredSize(new Dimension(450, 25));
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
        this.setPreferredSize(new Dimension(450, 500));
        buttonsPanel1.add(addNewClientButton);
        buttonsPanel2.add(editClientButton);
        buttonsPanel3.add(deleteClientButton);
        buttonsPanel4.add(viewClientsButton);
        buttonsPanel.add(buttonsPanel1);
        buttonsPanel.add(buttonsPanel2);
        buttonsPanel.add(buttonsPanel3);
        buttonsPanel.add(buttonsPanel4);
        upperPanel.add(addClientP, "addClient");
        upperPanel.add(deleteClientP, "deleteClient");
        upperPanel.add(editClientP, "editClient");
        this.add(upperPanel, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.SOUTH);
        upperPanel.setVisible(false);
    }
    public void addAddClientButtonListener()  {
        addNewClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(upperPanel, "addClient");
                upperPanel.setVisible(true);
            }
        });
    }
    public void addDeleteButtonListener()  {
        deleteClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(upperPanel, "deleteClient");
                upperPanel.setVisible(true);
            }
        });
    }
    public void addEditButtonListener()  {
        editClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(upperPanel, "editClient");
                upperPanel.setVisible(true);
            }
        });
    }
    public void addViewClientsButtonListener()  {
        viewClientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewClientsP = new ViewClientsPanel();
                upperPanel.add(viewClientsP, "viewClients");
                cardLayout.show(upperPanel, "viewClients");
                upperPanel.setVisible(true);
            }
        });
    }
    public void addButtonListeners() {
        addAddClientButtonListener();
        addDeleteButtonListener();
        addEditButtonListener();
        addViewClientsButtonListener();
    }
    public ClientManagementPanel() {
        styleComponents();
        layoutComponents();
        addButtonListeners();
    }


}

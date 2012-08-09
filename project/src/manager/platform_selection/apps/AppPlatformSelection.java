package manager.platform_selection.apps;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.ScrollPaneConstants;

import manager.platform_selection.handler.PlatformSelectionHandler;

public class AppPlatformSelection {

    private PlatformSelectionHandler objPlatformSelectionGUI = null;

    private JFrame frmPlatformSelection;

    private JList<String> PlatformsList;
    private DefaultListModel<String> PlatformsListModel;
    private JButton btnLoad;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;

    /**
     * Launch the application.
     */
    /*
     * public static void main(String[] args) { EventQueue.invokeLater(new
     * Runnable() { public void run() { try { PlatformSelection_App window = new
     * PlatformSelection_App(); window. } catch (Exception e) {
     * e.printStackTrace(); } } }); }
     */

    /**
     * Create the application.
     */
    public AppPlatformSelection(PlatformSelectionHandler platformSelectionGUI) {
        objPlatformSelectionGUI = platformSelectionGUI;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frmPlatformSelection = new JFrame();
        frmPlatformSelection.setTitle("Platform selection");
        frmPlatformSelection.setVisible(false);
        frmPlatformSelection.setResizable(false);
        frmPlatformSelection.setBounds(100, 100, 379, 295);
        frmPlatformSelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmPlatformSelection.getContentPane().setLayout(null);

        PlatformsListModel = new DefaultListModel<String>();
        PlatformsList = new JList<String>(PlatformsListModel);
        PlatformsList.setValueIsAdjusting(true);
        PlatformsList.setBorder(new EmptyBorder(1, 1, 0, 1));
        PlatformsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        PlatformsList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                EnableButtons_LoadEditDelete(true);
                // Integer elementNumber = PlatformsList.getSelectedIndex();
            }
        });
        PlatformsList.setBounds(20, 8, 219, 250);
        JScrollPane messageScrollList = new JScrollPane(PlatformsList);
        messageScrollList
                .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        messageScrollList.setBounds(10, 11, 219, 245);
        frmPlatformSelection.getContentPane().add(messageScrollList);

        btnLoad = new JButton("Load");
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                buttonAction_Load();
            }
        });
        btnLoad.setEnabled(false);
        btnLoad.setBounds(239, 10, 124, 23);
        frmPlatformSelection.getContentPane().add(btnLoad);

        btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                buttonAction_Add();
            }
        });
        btnAdd.setBounds(239, 44, 124, 23);
        frmPlatformSelection.getContentPane().add(btnAdd);

        btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonAction_Edit();
            }
        });
        btnEdit.setEnabled(false);
        btnEdit.setBounds(239, 78, 124, 23);
        frmPlatformSelection.getContentPane().add(btnEdit);

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonAction_Delete();
            }
        });
        btnDelete.setEnabled(false);
        btnDelete.setBounds(239, 112, 124, 23);
        frmPlatformSelection.getContentPane().add(btnDelete);
    }

    public void Visible(boolean b) {
        frmPlatformSelection.setVisible(b);
    }

    public void Enable(boolean b) {
        frmPlatformSelection.setEnabled(b);
    }

    private void buttonAction_Load() {
        objPlatformSelectionGUI.LoadPlatform();
    }

    private void buttonAction_Add() {
        objPlatformSelectionGUI.ActivateDialogAddEditPlatform_Add();
    }

    private void buttonAction_Edit() {
        objPlatformSelectionGUI.ActivateDialogAddEditPlatform_Edit();
    }

    private void buttonAction_Delete() {
        objPlatformSelectionGUI.ActivateDialogDeletePlatform();
    }

    public void LoadPlatformsToList(ArrayList<String> platformsNames) {
        PlatformsListModel.clear();
        for (int i = 0; i < platformsNames.size(); i++) {
            PlatformsListModel.addElement(platformsNames.get(i));
        }
        EnableButtons_LoadEditDelete(false);
    }

    public void EnableButtons_LoadEditDelete(boolean b) {
        btnLoad.setEnabled(b);
        btnEdit.setEnabled(b);
        btnDelete.setEnabled(b);
    }

    public Integer GetSelectedPlatform() {
        return PlatformsList.getSelectedIndex();
    }

}

package manager.platform_selection.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.UIManager;

import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import manager.platform_selection.handler.PlatformSelectionHandler;

public class DialogAddEditPlatform extends JDialog {
    
    private PlatformSelectionHandler objPlatformSelectionGUI = null;

    private final JPanel contentPanel = new JPanel();
    private JTextField txtHost;
    private JTextField txtPort;
    private JTextField txtDatabaseName;
    private JTextField txtDatabaseUser;
    private JPasswordField txtDatabasePassword;
    private JTextField txtPlatformName;
    private JTextArea txtPlatformDescription;

    public static final Integer DIALOG_ACTION_EDIT = 0;
    public static final Integer DIALOG_ACTION_ADD = 1;

    private Integer DialogAction = DialogAddEditPlatform.DIALOG_ACTION_ADD;

    /**
     * Launch the application.
     */
    /*
     * public static void main(String[] args) { try { AddPlatform_Dialog dialog
     * = new AddPlatform_Dialog();
     * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
     * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
     */

    /**
     * Create the dialog.
     */
    public DialogAddEditPlatform(PlatformSelectionHandler platformSelectionGUI) {

        objPlatformSelectionGUI = platformSelectionGUI;

        setTitle("Add new platform");
        setResizable(false);
        setVisible(false);
        // setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 340, 399);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel.setBounds(10, 157, 315, 67);
        contentPanel.add(panel);
        panel.setLayout(null);

        JRadioButton rdbtnNewRadioButton = new JRadioButton("HyperSQL");
        rdbtnNewRadioButton.setBounds(29, 37, 73, 23);
        panel.add(rdbtnNewRadioButton);
        rdbtnNewRadioButton.setEnabled(false);

        JRadioButton rdbtnPostgresql = new JRadioButton("PostgreSQL");
        rdbtnPostgresql.setBounds(29, 18, 109, 23);
        panel.add(rdbtnPostgresql);
        rdbtnPostgresql.setSelected(true);

        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        horizontalStrut_1.setBounds(0, 18, 30, 10);
        panel.add(horizontalStrut_1);

        JLabel lblSelectDatabaseType = new JLabel("Select database type:");
        lblSelectDatabaseType.setBounds(10, 0, 112, 14);
        panel.add(lblSelectDatabaseType);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_1.setBounds(10, 235, 315, 103);
        contentPanel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblEnterConnectionParameters = new JLabel(
                "Enter connection parameters:");
        lblEnterConnectionParameters.setBounds(10, 0, 143, 14);
        panel_1.add(lblEnterConnectionParameters);

        JLabel lblHost = new JLabel("host:");
        lblHost.setBounds(30, 19, 31, 14);
        panel_1.add(lblHost);

        txtHost = new JTextField();
        txtHost.setBounds(59, 16, 94, 20);
        panel_1.add(txtHost);
        txtHost.setText("0.0.0.0");
        txtHost.setColumns(15);

        JLabel lblPort = new JLabel("port:");
        lblPort.setBounds(162, 19, 31, 14);
        panel_1.add(lblPort);

        txtPort = new JTextField();
        txtPort.setBounds(189, 16, 39, 20);
        panel_1.add(txtPort);
        txtPort.setText("5432");
        txtPort.setColumns(5);

        JLabel lblDatabaseName = new JLabel("database name:");
        lblDatabaseName.setBounds(30, 47, 84, 14);
        panel_1.add(lblDatabaseName);

        Component horizontalStrut = Box.createHorizontalStrut(20);
        horizontalStrut.setBounds(0, 25, 30, 10);
        panel_1.add(horizontalStrut);

        txtDatabaseName = new JTextField();
        txtDatabaseName.setBounds(112, 44, 86, 20);
        panel_1.add(txtDatabaseName);
        txtDatabaseName.setColumns(10);

        JLabel lblDatabaseUser = new JLabel("user:");
        lblDatabaseUser.setBounds(30, 75, 31, 14);
        panel_1.add(lblDatabaseUser);

        txtDatabaseUser = new JTextField();
        txtDatabaseUser.setColumns(15);
        txtDatabaseUser.setBounds(59, 72, 94, 20);
        panel_1.add(txtDatabaseUser);

        JLabel lblPassword = new JLabel("password:");
        lblPassword.setBounds(162, 75, 57, 14);
        panel_1.add(lblPassword);

        txtDatabasePassword = new JPasswordField();
        txtDatabasePassword.setBounds(216, 72, 89, 20);
        panel_1.add(txtDatabasePassword);

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_2.setBounds(10, 11, 315, 135);
        contentPanel.add(panel_2);

        Component horizontalStrut_2 = Box.createHorizontalStrut(20);
        horizontalStrut_2.setBounds(0, 18, 30, 10);
        panel_2.add(horizontalStrut_2);

        JLabel lblEnterPlatformName = new JLabel(
                "Enter platform name and description:");
        lblEnterPlatformName.setBounds(10, 0, 178, 14);
        panel_2.add(lblEnterPlatformName);

        txtPlatformName = new JTextField();
        txtPlatformName.setBounds(65, 15, 240, 20);
        panel_2.add(txtPlatformName);
        txtPlatformName.setColumns(10);

        JLabel lblName = new JLabel("name:");
        lblName.setBounds(30, 18, 37, 14);
        panel_2.add(lblName);

        JLabel lblDescription = new JLabel("description:");
        lblDescription.setBounds(30, 39, 61, 14);
        panel_2.add(lblDescription);

        txtPlatformDescription = new JTextArea();
        txtPlatformDescription.setWrapStyleWord(true);
        txtPlatformDescription.setLineWrap(true);
        txtPlatformDescription.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtPlatformDescription.setColumns(10);
        txtPlatformDescription.setRows(5);
        txtPlatformDescription.setBounds(90, 39, 215, 85);
        txtPlatformDescription.setBorder(new EmptyBorder(1, 1, 1, 1));
        JScrollPane textscroll = new JScrollPane(txtPlatformDescription);
        textscroll
                .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textscroll
                .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textscroll.setBounds(90, 39, 215, 85);
        textscroll.setBorder(UIManager.getBorder("TextField.border"));
        panel_2.add(textscroll);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                buttonAction_Ok();
            }
        });
        // okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonAction_Cancel();
            }
        });
        // cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);

        addWindowListener(new WindowListener() {
            public void windowActivated(WindowEvent event) {
            }

            public void windowClosed(WindowEvent event) {
            }

            public void windowClosing(WindowEvent event) {
                Visible(false);
                objPlatformSelectionGUI.ActivateAppPlatformSelection();
            }

            public void windowDeactivated(WindowEvent event) {
            }

            public void windowDeiconified(WindowEvent event) {
            }

            public void windowIconified(WindowEvent event) {
            }

            public void windowOpened(WindowEvent event) {
            }

        });
    }

    public void Visible(boolean b) {
        setVisible(b);
    }

    public void ClearAllFields() {
        txtPlatformName.setText("");
        txtPlatformDescription.setText("");
        txtHost.setText("0.0.0.0");
        txtPort.setText("5432");
        txtDatabaseName.setText("");
        txtDatabaseUser.setText("");
        txtDatabasePassword.setText("");
    }

    public String CheckAllFields() {

        String ResultStatus = "";
        if (txtPlatformName.getText().equals("")) {
            ResultStatus = "Platform name is empty";
            return ResultStatus;
        }
        ;
        if (txtHost.getText().equals("")) {
            ResultStatus = "Host is empty";
            return ResultStatus;
        }
        ;
        if (txtPort.getText().equals("")) {
            ResultStatus = "Port is empty";
            return ResultStatus;
        }
        ;
        if (txtDatabaseName.getText().equals("")) {
            ResultStatus = "Database name is empty";
            return ResultStatus;
        }
        ;
        if (txtDatabaseUser.getText().equals("")) {
            ResultStatus = "User is empty";
            return ResultStatus;
        }
        ;

        return ResultStatus;
    }

    public String[] GetAllFields() {
        String[] Fields = new String[8];
        Fields[0] = txtPlatformName.getText();
        Fields[1] = txtPlatformDescription.getText();
        Fields[2] = "PostgreSQL";
        Fields[3] = txtHost.getText();
        Fields[4] = txtPort.getText();
        Fields[5] = txtDatabaseName.getText();
        Fields[6] = txtDatabaseUser.getText();
        Fields[7] = new String(txtDatabasePassword.getPassword());
        ;
        return Fields;
    }

    public void SetAllFields(ArrayList<String> fieldsValues) {
        txtPlatformName.setText(fieldsValues.get(0));
        txtPlatformDescription.setText(fieldsValues.get(1));
        // Fields[2] = "PostgreSQL";
        txtHost.setText(fieldsValues.get(3));
        txtPort.setText(fieldsValues.get(4));
        txtDatabaseName.setText(fieldsValues.get(5));
        txtDatabaseUser.setText(fieldsValues.get(6));
        txtDatabasePassword.setText(fieldsValues.get(7));
    }

    private void buttonAction_Ok() {
        Visible(false);
        if (!DialogAction.equals(0)) {
            objPlatformSelectionGUI.AddPlatform();
        } else {
            objPlatformSelectionGUI.EditPlatform();
        }

    }

    public void SetDialogAction(Integer action) {
        DialogAction = action;
    }

    private void buttonAction_Cancel() {
        Visible(false);
        objPlatformSelectionGUI.ActivateAppPlatformSelection();
    }
}

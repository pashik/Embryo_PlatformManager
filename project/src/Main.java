import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import manager.dialogs.SimpleDialogs;
import manager.platform_selection.handler.PlatformSelectionHandler;

import sql.LocalStorage;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            SimpleDialogs.ShowError(e.getMessage());
        } catch (InstantiationException e) {
            SimpleDialogs.ShowError(e.getMessage());
        } catch (IllegalAccessException e) {
            SimpleDialogs.ShowError(e.getMessage());
        } catch (UnsupportedLookAndFeelException e) {
            SimpleDialogs.ShowError(e.getMessage());
        }

        LocalStorage localStorage = null;
        try {
            localStorage = new LocalStorage();
        } catch (ClassNotFoundException e) {
            SimpleDialogs.ShowError(e.getMessage());
        }

        try {
            localStorage.InitializeStructure();
        } catch (SQLException e) {
            SimpleDialogs.ShowError(e.getMessage());
        }

        PlatformSelectionHandler platformSelectionGUI = new PlatformSelectionHandler(
                localStorage);
        platformSelectionGUI.Show();

    }

}

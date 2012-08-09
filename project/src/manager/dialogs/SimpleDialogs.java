package manager.dialogs;

import javax.swing.JOptionPane;

public class SimpleDialogs {
    public static void ShowWarning(String Message) {
        Object[] options = { Message };
        JOptionPane.showMessageDialog(null, options, "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public static void ShowError(String Message) {
        Object[] options = { Message };
        JOptionPane.showMessageDialog(null, options, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public static Integer ShowConfirmation(String Message) {
        Object[] options = { Message };
        return JOptionPane.showConfirmDialog(null, options, "Confirmation",
                JOptionPane.YES_NO_OPTION);
    }

    public static void ShowInformation(String Message) {
        Object[] options = { Message };
        JOptionPane.showMessageDialog(null, options, "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

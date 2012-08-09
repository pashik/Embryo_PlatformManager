package manager.platform_selection.handler;

import java.sql.SQLException;
import java.util.ArrayList;

import manager.dialogs.SimpleDialogs;
import manager.platform_selection.apps.AppPlatformSelection;
import manager.platform_selection.dialogs.DialogAddEditPlatform;
import manager.platform_selection.structures.Platform;
import manager.platform_selection.structures.PlatformConnectionParameters;

import sql.LocalStorage;

public class PlatformSelectionHandler {

    private LocalStorage objLocalStorage;
    private ArrayList<Platform> Platforms = null;

    private AppPlatformSelection AppPlatformSelection;
    private DialogAddEditPlatform DialogAddEditPlatform;

    public PlatformSelectionHandler(LocalStorage localStorage) {
        objLocalStorage = localStorage;
        AppPlatformSelection = new AppPlatformSelection(this);
        Action_UpdatePlatformsList();
        DialogAddEditPlatform = new DialogAddEditPlatform(this);
    }

    public void Show() {
        AppPlatformSelection.Visible(true);
    }

    public void ActivateDialogAddEditPlatform_Add() {
        AppPlatformSelection.Enable(false);
        DialogAddEditPlatform
                .SetDialogAction(manager.platform_selection.dialogs.DialogAddEditPlatform.DIALOG_ACTION_ADD);
        DialogAddEditPlatform.ClearAllFields();
        DialogAddEditPlatform.Visible(true);
    }

    public void ActivateDialogAddEditPlatform_Edit() {
        AppPlatformSelection.Enable(false);
        DialogAddEditPlatform
                .SetDialogAction(manager.platform_selection.dialogs.DialogAddEditPlatform.DIALOG_ACTION_EDIT);
        Integer PlatformNumber = AppPlatformSelection.GetSelectedPlatform();
        Platform selectedPlatform = Platforms.get(PlatformNumber);
        PlatformConnectionParameters platfromConnectionParams = selectedPlatform
                .GetConnectionParameters();
        ArrayList<String> fieldValues = new ArrayList<String>();
        fieldValues.add(selectedPlatform.GetName());
        fieldValues.add(selectedPlatform.GetDescription());
        fieldValues.add(platfromConnectionParams.dbType);
        fieldValues.add(platfromConnectionParams.dbHost);
        String dbPort = platfromConnectionParams.dbPort.toString();
        fieldValues.add(dbPort);
        fieldValues.add(platfromConnectionParams.dbName);
        fieldValues.add(platfromConnectionParams.dbUser);
        fieldValues.add(platfromConnectionParams.dbPassword);
        DialogAddEditPlatform.SetAllFields(fieldValues);
        DialogAddEditPlatform.Visible(true);
    }

    public void ActivateAppPlatformSelection() {
        AppPlatformSelection.Enable(true);
        AppPlatformSelection.Visible(true);
    }

    public void ActivateDialogDeletePlatform() {
        Integer result = SimpleDialogs
                .ShowConfirmation("Are you sure to delete platform from the list?");
        if (result.equals(0)) {
            DeletePlatform();
        }
    }

    public void LoadPlatform() {
        Integer PlatformNumber = AppPlatformSelection.GetSelectedPlatform();
        Platform selectedPlatform = Platforms.get(PlatformNumber);

        SimpleDialogs.ShowInformation("Load platform \""
                + selectedPlatform.GetName() + "\" is coming soon...");
    }

    public void AddPlatform() {
        String checkResult = DialogAddEditPlatform.CheckAllFields();

        if (checkResult.equals("")) {
            String[] fields = DialogAddEditPlatform.GetAllFields();

            Platform platform = new Platform(fields[0], fields[1]);
            PlatformConnectionParameters connectionParams = new PlatformConnectionParameters();
            connectionParams.dbType = fields[2];
            connectionParams.dbHost = fields[3];
            connectionParams.dbPort = Integer.parseInt(fields[4]);
            connectionParams.dbName = fields[5];
            connectionParams.dbUser = fields[6];
            connectionParams.dbPassword = fields[7];
            platform.SetConnectionParameters(connectionParams);

            try {
                objLocalStorage.AddPlatform(platform);
            } catch (SQLException e) {
                SimpleDialogs.ShowError(e.getMessage());
            }

            DialogAddEditPlatform.Visible(false);
            Action_UpdatePlatformsList();
            ActivateAppPlatformSelection();
        } else {
            SimpleDialogs.ShowError(checkResult);
            AppPlatformSelection.Visible(true);
            DialogAddEditPlatform.Visible(true);
        }
    }

    public void EditPlatform() {
        String checkResult = DialogAddEditPlatform.CheckAllFields();

        if (checkResult.equals("")) {
            String[] fields = DialogAddEditPlatform.GetAllFields();

            Integer PlatformNumber = AppPlatformSelection.GetSelectedPlatform();
            Platform selectedPlatform = Platforms.get(PlatformNumber);

            selectedPlatform.SetName(fields[0]);
            selectedPlatform.SetDescription(fields[1]);
            PlatformConnectionParameters platfromConnectionParams = selectedPlatform
                    .GetConnectionParameters();
            platfromConnectionParams.dbType = fields[2];
            platfromConnectionParams.dbHost = fields[3];
            platfromConnectionParams.dbPort = Integer.parseInt(fields[4]);
            platfromConnectionParams.dbName = fields[5];
            platfromConnectionParams.dbUser = fields[6];
            platfromConnectionParams.dbPassword = fields[7];
            selectedPlatform.SetConnectionParameters(platfromConnectionParams);

            try {
                objLocalStorage.EditPlatform(selectedPlatform);
            } catch (SQLException e) {
                SimpleDialogs.ShowError(e.getMessage());
            }

            DialogAddEditPlatform.Visible(false);
            Action_UpdatePlatformsList();
            ActivateAppPlatformSelection();
        } else {
            SimpleDialogs.ShowError(checkResult);
            AppPlatformSelection.Visible(true);
            DialogAddEditPlatform.Visible(true);
        }

    }

    public void DeletePlatform() {
        Integer PlatformNumber = AppPlatformSelection.GetSelectedPlatform();
        Platform selectedPlatform = Platforms.get(PlatformNumber);

        try {
            objLocalStorage.DeletePlatform(selectedPlatform);
        } catch (SQLException e) {
            SimpleDialogs.ShowError(e.getMessage());
        }

        Action_UpdatePlatformsList();
        ActivateAppPlatformSelection();
    }

    public void Action_UpdatePlatformsList() {
        ArrayList<String> platformsNames = new ArrayList<String>();
        try {
            ArrayList<Platform> platforms = objLocalStorage.GetPlatforms();
            Platforms = platforms;
            for (int i = 0; i < platforms.size(); i++) {
                platformsNames.add(platforms.get(i).GetName());
            }
            AppPlatformSelection.LoadPlatformsToList(platformsNames);
        } catch (SQLException e) {
            SimpleDialogs.ShowError(e.getMessage());
        }

    }

}

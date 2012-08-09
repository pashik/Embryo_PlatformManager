package manager.platform_selection.structures;

public class Platform {

    private Integer LocalStorageID = null;
    private String Name = null;
    private String Description = null;

    private PlatformConnectionParameters ConnectionParameters = null;

    public Platform(String name, String description) {
        Name = name;
        Description = description;
    }

    public Platform(String name) {
        Name = name;
    }

    public Platform() {
    }

    public void SetLocalStorageID(Integer id) {
        LocalStorageID = id;
    }

    public Integer GetLocalStorageID() {
        return LocalStorageID;
    }

    public void SetName(String name) {
        Name = name;
    }

    public String GetName() {
        return Name;
    }

    public void SetDescription(String description) {
        Description = description;
    }

    public String GetDescription() {
        return Description;
    }

    public void SetConnectionParameters(
            PlatformConnectionParameters connectionParameters) {
        ConnectionParameters = connectionParameters;
    }

    public PlatformConnectionParameters GetConnectionParameters() {
        return ConnectionParameters;
    }
}
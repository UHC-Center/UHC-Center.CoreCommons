package center.uhc.core.commons;

public enum LobbyType {

    STAFF("Staff Lounge", "Staff", "uc.lobby.staff"),
    PREMIUM("Premium Hub", "Premium", "uc.lobby.premium"),
    REGULAR("Regular Hub", "Lobby", null);

    private String name;
    private String task;
    private String permission;

    private LobbyType(String name, String task, String permission) {
        this.name = name;
        this.task = task;
        this.permission = permission;
    }

    public String getName() { return name; }
    public String getTask() { return task; }
    public String getPermission() { return permission; }

    public static LobbyType getFromTask(String task) {
        for (LobbyType t : LobbyType.values()) {
            if (t.getTask().equalsIgnoreCase(task))
                return t;
        }
        return null;
    }
}

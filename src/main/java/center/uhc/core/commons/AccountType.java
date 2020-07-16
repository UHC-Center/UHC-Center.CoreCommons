package center.uhc.core.commons;

import org.bukkit.ChatColor;

public enum AccountType {

    STAFF("Staff", ChatColor.RED, "uc.accounttype.staff"),
    PREMIUM("Premium", ChatColor.LIGHT_PURPLE, "uc.accounttype.premium"),
    REGULAR("Regular", ChatColor.GRAY, null);

    private String name;
    private ChatColor colour;
    private String permission;

    private AccountType(String name, ChatColor colour, String permission) {
        this.name = name;
        this.colour = colour;
        this.permission = permission;
    }

    public String getName() { return name; }
    public ChatColor getColour() { return colour; }
    public String getPermission() { return permission; }
}

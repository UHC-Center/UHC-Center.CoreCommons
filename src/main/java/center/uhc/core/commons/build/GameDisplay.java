package center.uhc.core.commons.build;

import lombok.Getter;
import org.bukkit.Material;

public enum GameDisplay {

    TEST("Test", "test", Material.MAGMA_CREAM, (byte) 0);

    @Getter private String displayName;
    @Getter private String mysqlStatTable;
    @Getter private Material icon;
    @Getter private byte iconData;

    private GameDisplay(String displayName, String mysqlStatTable, Material icon, byte iconData) {
        this.displayName = displayName;
        this.mysqlStatTable = mysqlStatTable;
        this.icon = icon;
        this.iconData = iconData;
    }

}

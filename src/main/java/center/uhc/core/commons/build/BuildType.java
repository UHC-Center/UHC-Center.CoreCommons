package center.uhc.core.commons.build;

import lombok.Getter;
import org.bukkit.Material;

public enum BuildType {

    PRACTICE("practice-maps", Material.DIAMOND_SWORD, false),
    ARCADE("arcade-maps", Material.SLIME_BALL, true);

    @Getter private String folder;
    @Getter private Material icon;
    @Getter private boolean useGameDisplay;

    private BuildType(String folder, Material icon, boolean useGameDisplay) {
        this.folder = folder;
        this.icon = icon;
        this.useGameDisplay = useGameDisplay;
    }
}

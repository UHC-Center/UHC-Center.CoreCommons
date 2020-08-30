package center.uhc.core.commons.build;

import lombok.Getter;
import org.bukkit.ChatColor;

public class Datapoint {

    @Getter private DatapointType type;
    @Getter private String data;
    @Getter private ChatColor colour;
    @Getter private Integer x;
    @Getter private Integer y;
    @Getter private Integer z;

    public Datapoint(DatapointType type, String data, ChatColor colour, int x, int y, int z) {
        this.type = type;
        this.data = data;
        this.colour = colour;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Datapoint() { }
}

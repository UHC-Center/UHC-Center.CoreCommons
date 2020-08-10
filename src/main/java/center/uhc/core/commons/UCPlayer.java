package center.uhc.core.commons;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class UCPlayer {

    @Getter public Player playerInstance;

    @Getter public UUID uuid;
    @Getter public String name;
    @Getter public String actualName;

    @Getter public String prefix;

    @Getter public HashMap<String, String> flags;

    public UCPlayer(Player player, String prefix) {
        this.playerInstance = player;

        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.actualName = player.getName();

        this.prefix = prefix;

        flags = new HashMap<>();
    }
}

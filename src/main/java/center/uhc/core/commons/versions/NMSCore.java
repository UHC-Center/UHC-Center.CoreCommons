package center.uhc.core.commons.versions;

import center.uhc.core.commons.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class NMSCore {

    private static NMSCore utils;
    public static NMSVersion nmsVersion;
    public static String v;

    public static void preloadVersion() {
        v = Bukkit.getServer().getClass().getPackage().getName();
        v = v.substring(v.lastIndexOf(".") + 1);

        if (v.startsWith("v1_8")) {
            utils = new NMSCore_1_8();
            nmsVersion = NMSVersion.ONE_POINT_EIGHT;
        } else if (v.startsWith("v1_16")) {
            utils = new NMSCore_1_16();
            nmsVersion = NMSVersion.ONE_POINT_SIXTEEN;
        } else
            Message.console("ERROR: UNKNOWN VERSION");
    }

    public static NMSCore getUtils() {
        if (utils == null) {
            preloadVersion();
        }
        return utils;
    }


    public void broadcastTitle(String top, String bottom, int fadeIn, int stay, int fadeOut) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            sendTitle(top, bottom, fadeIn, stay, fadeOut, p);
        }
    }

    public void broadcastActionbar(String message) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            sendActionbar(p, message);
        }
    }

    public abstract void sendTitle(String top, String bottom, int fadeIn, int stay, int fadeOut, Player player);
    public abstract void sendActionbar(Player player, String message);
    public abstract void registerNMSCommand(JavaPlugin p, String cmdName, BukkitCommand bukkitCommand);
    public abstract double getTps();
    public abstract void setEntityAi(LivingEntity entity, boolean hasAi);
}

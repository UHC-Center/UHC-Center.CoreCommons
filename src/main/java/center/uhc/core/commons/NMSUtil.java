package center.uhc.core.commons;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NMSUtil {

    public static Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server" + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object conn = handle.getClass().getField("playerConnection").get(handle);
            conn.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(conn, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

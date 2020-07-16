package center.uhc.core.commons;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.player.ICloudPlayer;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerUtil {

    private static final IPlayerManager playerManager = CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class);

    public static void broadcastActionbar(String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte) 2);
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public static void sendActionbar(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte) 2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void broadcastTitle(String top, String bottom, int fadeIn, int stay, int fadeOut, boolean chat) {
        try {
            IChatBaseComponent titleTop = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + top + "\"}");
            IChatBaseComponent titleBottom = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + bottom + "\"}");

            PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleTop);
            PacketPlayOutTitle subTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleBottom);
            PacketPlayOutTitle length = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut);

            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(subTitle);
            }

            if (chat) {
                Message.broadcast(top + " §8» " + bottom);
            }
        } catch (Exception e) {
            Message.broadcast(top + " §8» " + bottom);
        }
    }

    public static void sendTitle(String top, String bottom, int fadeIn, int stay, int fadeOut, Player player) {
        try {
            IChatBaseComponent titleTop = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + top + "\"}");
            IChatBaseComponent titleBottom = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + bottom + "\"}");

            PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleTop);
            PacketPlayOutTitle subTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleBottom);
            PacketPlayOutTitle length = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut);

            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subTitle);
        } catch (Exception e) {
            player.sendMessage(top + " §8» " + bottom);
        }
    }

    public static void broadcastSound(Sound sound, float volume, float pitch) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.playSound(p.getLocation(), sound, volume, pitch);
        }
    }

    public static void playSound(Player p, Sound sound, float volume, float pitch) {
        p.playSound(p.getLocation(), sound, volume, pitch);
    }

    public static void playSoundAt(Location loc, Sound sound, float volume, float pitch) {
        loc.getWorld().playSound(loc, sound, volume, pitch);
    }

    @Deprecated
    public static User getLuckPermsUser(Player player) {
        try {
            return LuckPermsProvider.get().getUserManager().loadUser(player.getUniqueId()).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public static User getLuckPermsUser(UUID player) {
        try {
            return LuckPermsProvider.get().getUserManager().loadUser(player).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public static String getPlayerPrefix(Player player) {
        try {
            User u = getLuckPermsUser(player);
            assert u != null;
            CachedMetaData metaData = u.getCachedData().getMetaData(LuckPermsProvider.get().getContextManager().getQueryOptions(u).get());
            return (metaData.getPrefix() == null ? "§7" : ChatColor.translateAlternateColorCodes('&', metaData.getPrefix()));
        } catch (Exception e) {
            e.printStackTrace();
            return "§7";
        }
    }

    @Deprecated
    public static String getPlayerPrefix(UUID player) {
        try {
            User u = getLuckPermsUser(player);
            assert u != null;
            CachedMetaData metaData = u.getCachedData().getMetaData(LuckPermsProvider.get().getContextManager().getQueryOptions(u).get());
            return (metaData.getPrefix() == null ? "§7" : ChatColor.translateAlternateColorCodes('&', metaData.getPrefix()));
        } catch (Exception e) {
            e.printStackTrace();
            return "§7";
        }
    }

    public static Player search(String input) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            String name = p.getName().toLowerCase();

            if (!name.startsWith(input.toLowerCase()))
                continue;

            return p;
        }
        return null;
    }

    public static boolean isOnline(UUID uuid) {
        ICloudPlayer cloudPlayer = playerManager.getOnlinePlayer(uuid);

        return cloudPlayer != null;
    }

    public static double getDistanceFromPlayer(Player player1, Player player2){
        return player1.getLocation().distance(player2.getLocation());
    }

    public static void clearInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }
}

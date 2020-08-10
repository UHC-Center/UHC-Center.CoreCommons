package center.uhc.core.commons;

import center.uhc.core.commons.versions.NMSCore;
import center.uhc.core.commons.versions.NMSVersion;
import center.uhc.core.commons.versions.UniversalSound;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.player.ICloudPlayer;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.UUID;

public class PlayerUtils {

    private static final IPlayerManager playerManager = CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class);

    public static void broadcastActionbar(String message) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            sendActionbar(player, message);
        }
    }

    public static void sendActionbar(Player player, String message) {
        NMSCore.getUtils().sendActionbar(player, message);
    }

    public static void broadcastTitle(String top, String bottom, int fadeIn, int stay, int fadeOut, boolean chat) {
        try {
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                sendTitle(top, bottom, fadeIn, stay, fadeOut, p);
            }

            if (chat) {
                Message.broadcast(top + " §8» " + bottom);
            }
        } catch (Exception e) {
            Message.broadcast(top + " §8» " + bottom);
        }
    }

    public static void sendTitle(String top, String bottom, int fadeIn, int stay, int fadeOut, Player player) {
        NMSCore.getUtils().sendTitle(top, bottom, fadeIn, stay, fadeOut, player);
    }

    @Deprecated
    public static void broadcastSound(Sound sound, float volume, float pitch) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.playSound(p.getLocation(), sound, volume, pitch);
        }
    }

    public static void broadcastSound(UniversalSound sound, float volume, float pitch) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            playSound(p, sound, volume, pitch);
        }
    }

    public static void playSound(Player p, UniversalSound sound, float volume, float pitch) {
        if (NMSCore.nmsVersion == NMSVersion.ONE_POINT_EIGHT)
            p.playSound(p.getLocation(), sound.getSound_1_8(), volume, pitch);
        else if (NMSCore.nmsVersion == NMSVersion.ONE_POINT_SIXTEEN)
            p.playSound(p.getLocation(), sound.getSound_1_16(), volume, pitch);
    }

    @Deprecated
    public static void playSound(Player p, Sound sound, float volume, float pitch) {
        p.playSound(p.getLocation(), sound, volume, pitch);
    }

    @Deprecated
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

    public static void clearEffects(Player player) {
        for (PotionEffect e : player.getActivePotionEffects()) {
            player.removePotionEffect(e.getType());
        }
    }
}

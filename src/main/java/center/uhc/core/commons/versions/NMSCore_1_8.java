package center.uhc.core.commons.versions;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NMSCore_1_8 extends NMSCore {

    @Override
    public void sendTitle(String top, String bottom, int fadeIn, int stay, int fadeOut, Player player) {
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

    @Override
    public void sendActionbar(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte) 2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }

    @Override
    public void registerNMSCommand(JavaPlugin p, String cmdName, BukkitCommand bukkitCommand) {
        ((CraftServer) p.getServer()).getCommandMap().register(cmdName, bukkitCommand);
    }

    @Override
    public double getTps() {
        return MinecraftServer.getServer().recentTps[0];
    }

    @Override
    public void setEntityAi(LivingEntity entity, boolean hasAi) {
        EntityLiving handle = ((CraftLivingEntity) entity).getHandle();
        handle.getDataWatcher().watch(15, (byte) (hasAi ? 0 : 1));
    }

    @Override
    public float getAbsorptionHearts(Player player) {
        return ((CraftPlayer) player).getHandle().getAbsorptionHearts();
    }
}

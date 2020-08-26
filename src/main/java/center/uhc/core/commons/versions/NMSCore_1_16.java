package center.uhc.core.commons.versions;

import net.minecraft.server.v1_16_R1.*;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.craftbukkit.v1_16_R1.CraftServer;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NMSCore_1_16 extends NMSCore {

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

        }
    }

    @Override
    public void sendActionbar(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"), ChatMessageType.GAME_INFO, player.getUniqueId());
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }

    @Override
    public void registerNMSCommand(JavaPlugin p, String cmdName, BukkitCommand bukkitCommand) {
        ((CraftServer) p.getServer()).getCommandMap().register(cmdName, bukkitCommand);
    }

    @Override
    public double getTps() {
        return MinecraftServer.TPS;
    }

    @Override
    public void setEntityAi(LivingEntity entity, boolean hasAi) {
        if (entity instanceof Mob) {
            Mob mob = (Mob) entity;
            mob.setAware(hasAi);
        }
    }

    @Override
    public float getAbsorptionHearts(Player player) {
        return ((CraftPlayer) player).getHandle().getAbsorptionHearts();
    }
}

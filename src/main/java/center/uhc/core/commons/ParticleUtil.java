package center.uhc.core.commons;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ParticleUtil {

    public static void spawn(Player p, Location l, EnumParticle particle, int amount, int speed) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                particle,
                false,                        //false -> close range particles
                (float) l.getX(),
                (float) l.getY(),
                (float) l.getZ(),
                0,                           //offsets x, y, z
                0,
                0,
                speed,
                amount
        );

        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }

    public static void spawn(Location l, EnumParticle particle, int amount, int speed) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                particle,
                false,                        //false -> close range particles
                (float) l.getX(),
                (float) l.getY(),
                (float) l.getZ(),
                0,                           //offsets x, y, z
                0,
                0,
                speed,
                amount
        );

        for (Player p : Bukkit.getServer().getOnlinePlayers())
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }

}

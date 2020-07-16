package center.uhc.core.commons;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

public class Fireworks {

    public static void spawnFireworks(Location loc, boolean flicker, boolean trail, int amount, Color... colours) {
        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta m = fw.getFireworkMeta();

        m.setPower(2);
        m.addEffect(FireworkEffect.builder().withColor(colours).flicker(flicker).trail(trail).build());

        fw.setFireworkMeta(m);
        fw.detonate();

        for (int i = 0; i < amount; i++) {
            Location spawn = loc.clone().add(new Vector(Math.random()-0.5, 0, Math.random()-0.5).multiply(10));
            Firework n = (Firework) spawn.getWorld().spawnEntity(spawn, EntityType.FIREWORK);
            n.setFireworkMeta(m);
        }
    }

}

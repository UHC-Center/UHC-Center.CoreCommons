package center.uhc.core.commons;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FireworkUtil {

    public static void spawnFireworks(Location loc, boolean flicker, boolean trail, int amount, Color... colours) {
        spawnFireworks(loc, flicker, trail, amount, 2, colours);
    }

    public static void spawnFireworks(Location loc, boolean flicker, boolean trail, int amount, int power, Color... colours) {
        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta m = fw.getFireworkMeta();

        m.setPower(power);
        m.addEffect(FireworkEffect.builder().withColor(colours).flicker(flicker).trail(trail).build());

        fw.setFireworkMeta(m);
        fw.detonate();

        for (int i = 0; i < amount; i++) {
            Location spawn = loc.clone().add(new Vector(Math.random()-0.5, 0, Math.random()-0.5).multiply(10));
            Firework n = (Firework) spawn.getWorld().spawnEntity(spawn, EntityType.FIREWORK);
            n.setFireworkMeta(m);
        }
    }

    public static void spawnInstant(FireworkEffect effect, Location loc) {
        Firework f = (Firework) loc.getWorld().spawn(loc, Firework.class);
        FireworkMeta fm = f.getFireworkMeta();
        fm.addEffect(effect);
        f.setFireworkMeta(fm);
        try {
            Class<?> entityFireworkClass = NMSUtil.getClass("net.minecraft.server.", "EntityFireworks");
            Class<?> craftFireworkClass = NMSUtil.getClass("org.bukkit.craftbukkit.", "entity.CraftFirework");
            Object firework = craftFireworkClass.cast(f);
            Method handle = firework.getClass().getMethod("getHandle");
            Object entityFirework = handle.invoke(firework);
            Field expectedLifespan = entityFireworkClass.getDeclaredField("expectedLifespan");
            Field ticksFlown = entityFireworkClass.getDeclaredField("ticksFlown");
            ticksFlown.setAccessible(true);
            ticksFlown.setInt(entityFirework, expectedLifespan.getInt(entityFirework) - 1);
            ticksFlown.setAccessible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

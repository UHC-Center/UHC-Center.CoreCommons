package center.uhc.core.commons;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * UHC Center - Core
 * Per Tick Runnable
 *
 * Fires the Per Tick Event
 *
 * @author Gabriella Hotten
 * @version 1.0
 * @since 2020-06-06
 */
public class PerTickRunnable implements Runnable {

    private JavaPlugin plugin;
    public PerTickRunnable(JavaPlugin plugin) { this.plugin = plugin; }

    @Override public void run() { Bukkit.getPluginManager().callEvent(new PerTickEvent()); }

}

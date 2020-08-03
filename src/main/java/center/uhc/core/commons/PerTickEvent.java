package center.uhc.core.commons;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * UHC Center - Core
 * Per Tick Event Utility
 *
 * Event that fires every tick.
 *
 * @author  Gabriella Hotten
 * @version 1.0
 * @since   2020-06-06
 */
public class PerTickEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public PerTickEvent() {
        super(true);
    }

    @Override public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}

package center.uhc.core.commons;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class PotionUtil {

    public static ItemStack createPotion(PotionType type, boolean splash, int level, boolean extend, String displayName) {
        Potion p = new Potion(type, level, false);
        if (extend) p.setHasExtendedDuration(true);
        ItemStack i = p.toItemStack(1);
        ItemMeta m = i .getItemMeta();
        if (displayName != null)
            m.setDisplayName(displayName);
        i.setItemMeta(m);
        return i;
    }

}

package live.nerotv.knockit.manager;

import live.nerotv.knockit.api.API;
import live.nerotv.knockit.api.PlayerAPI;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class ItemManager {

    public static ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore((List) Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack Placeholder = createGuiItem(Material.BLACK_STAINED_GLASS_PANE,"§0");

    public static ItemStack StopYes = createGuiItem(
            Material.GREEN_CONCRETE,
            "§aJa§2"
    );

    public static ItemStack ReloadYes = createGuiItem(
            Material.GREEN_CONCRETE,
            "§aJa§r"
    );

    public static ItemStack Cancel = createGuiItem(
            Material.RED_CONCRETE,
            "§cNein§r"
    );

    public static ItemStack Stick() {
        ItemStack Stick = createGuiItem(
                Material.STICK,
                "§eKnüppel"
        );
        ItemMeta StickMeta = Stick.getItemMeta();
        StickMeta.addEnchant(Enchantment.KNOCKBACK,2,true);
        Stick.setItemMeta(StickMeta);
        return Stick;
    }

    public static ItemStack Snow() {
        ItemStack Snow = createGuiItem(
                Material.SNOWBALL,
                "§eEffekt-Schneeball"
        );
        ItemMeta SnowMeta = Snow.getItemMeta();
        SnowMeta.addEnchant(Enchantment.LOYALTY,1,true);
        Snow.setItemMeta(SnowMeta);
        Snow.setAmount(3);
        return Snow;
    }

    public static ItemStack Bow() {
        ItemStack Bow = createGuiItem(
                Material.BOW,
                "§eBogen"
        );
        ItemMeta BowMeta = Bow.getItemMeta();
        BowMeta.addEnchant(Enchantment.ARROW_KNOCKBACK,1,true);
        BowMeta.setUnbreakable(true);
        Bow.setItemMeta(BowMeta);
        return Bow;
    }

    public static ItemStack Pearl() {
        ItemStack Pearl = createGuiItem(
                Material.ENDER_PEARL,
                "§eEnderperle"
        );
        ItemMeta PearlMeta = Pearl.getItemMeta();
        PearlMeta.addEnchant(Enchantment.LOYALTY,1,true);
        Pearl.setItemMeta(PearlMeta);
        return Pearl;
    }

    public static ItemStack Sword() {
        ItemStack Sword = createGuiItem(
                Material.BLAZE_ROD,
                "§eSuperschlagkraft-Knüppel"
        );
        ItemMeta SwordMeta = Sword.getItemMeta();
        SwordMeta.addEnchant(Enchantment.KNOCKBACK,10,true);
        Sword.setItemMeta(SwordMeta);
        Sword.setDurability((short)1);
        return Sword;
    }

    public static ItemStack ShopBow() {
        ItemStack Bow = createGuiItem(
                Material.BOW,
                "§eBogen§8 - §a5 Kills",
                "§9Preis: 5 Kills"
        );
        ItemMeta BowMeta = Bow.getItemMeta();
        BowMeta.addEnchant(Enchantment.ARROW_KNOCKBACK,1,true);
        Bow.setItemMeta(BowMeta);
        return Bow;
    }

    public static ItemStack ShopPearl() {
        ItemStack Pearl = createGuiItem(
                Material.ENDER_PEARL,
                "§eEnderperle§8 - §a15 Kills",
                "§9Preis: 15 Kills"
        );
        ItemMeta PearlMeta = Pearl.getItemMeta();
        PearlMeta.addEnchant(Enchantment.LOYALTY,1,true);
        Pearl.setItemMeta(PearlMeta);
        return Pearl;
    }

    public static ItemStack ShopSword() {
        ItemStack Sword = createGuiItem(
                Material.BLAZE_ROD,
                "§eSuperschlagkraft-Knüppel§8 - §a10 Kills",
                "§9Preis: 10 Kills"
        );
        ItemMeta SwordMeta = Sword.getItemMeta();
        SwordMeta.addEnchant(Enchantment.KNOCKBACK,4,true);
        Sword.setItemMeta(SwordMeta);
        Sword.setDurability((short)1);
        return Sword;
    }

    public static ItemStack Chest = createGuiItem(
            Material.ENDER_CHEST,
            "§bShop"
    );

    public static void giveItems(Player p) {
        Inventory inv = p.getInventory();
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,999999,255));
        String Map = p.getWorld().getName();
        inv.setItem(0,Stick());
        inv.setItem(1,Snow());
        inv.setItem(8,Chest);
        if(PlayerAPI.isBedrock(p)) {
            p.getInventory().clear(8);
            p.getInventory().setItem(8, ItemManager.backItem);
        }
    }

    public static ItemStack backItem = createGuiItem(
            Material.ENDER_CHEST,
            "§aAktionsmenü §8(§7Shop§8)"
    );
}
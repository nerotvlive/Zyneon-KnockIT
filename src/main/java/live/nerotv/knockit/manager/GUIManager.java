package live.nerotv.knockit.manager;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class GUIManager {

    public static Inventory confirmStopInventory = Bukkit.createInventory(null,InventoryType.HOPPER,"§fWillst du den Server §cstoppen§f?");
    public static void openConfirmStopInventory(Player p) {
        confirmStopInventory.setItem(0,ItemManager.Placeholder);
        confirmStopInventory.setItem(1,ItemManager.Cancel);
        confirmStopInventory.setItem(2,ItemManager.Placeholder);
        confirmStopInventory.setItem(3,ItemManager.StopYes);
        confirmStopInventory.setItem(4,ItemManager.Placeholder);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        p.openInventory(confirmStopInventory);
    }

    public static Inventory confirmReloadInventory = Bukkit.createInventory(null,InventoryType.HOPPER,"§fWillst du den Server §creloaden§f?");
    public static void openConfirmReloadInventory(Player p) {
        confirmReloadInventory.setItem(0,ItemManager.Placeholder);
        confirmReloadInventory.setItem(1,ItemManager.Cancel);
        confirmReloadInventory.setItem(2,ItemManager.Placeholder);
        confirmReloadInventory.setItem(3,ItemManager.ReloadYes);
        confirmReloadInventory.setItem(4,ItemManager.Placeholder);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        p.openInventory(confirmReloadInventory);
    }

    public static Inventory chestShopInventory = Bukkit.createInventory(null,InventoryType.HOPPER,"§bShop");
    public static void openChestShopInventory(Player p) {
        chestShopInventory.setItem(0,ItemManager.Placeholder);
        chestShopInventory.setItem(1,ItemManager.ShopBow());
        chestShopInventory.setItem(2,ItemManager.ShopSword());
        chestShopInventory.setItem(3,ItemManager.ShopPearl());
        chestShopInventory.setItem(4,ItemManager.Placeholder);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        p.openInventory(chestShopInventory);
    }
}
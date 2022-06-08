package live.nerotv.knockit.listener;

import live.nerotv.knockit.api.API;
import live.nerotv.knockit.api.PlayerAPI;
import live.nerotv.knockit.manager.ItemManager;
import live.nerotv.knockit.manager.bedrock.FormManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(!(API.canPlayerBuild(e.getPlayer()))) {
            if(!e.getAction().equals(Action.LEFT_CLICK_AIR)||e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                if (e.getItem() != null && e.getItem().getItemMeta() != null) {
                    ItemStack Item = e.getItem();
                    ItemMeta ItemMeta = Item.getItemMeta();
                    String ItemName = ItemMeta.getDisplayName();
                    if(ItemName.equals(ItemManager.backItem.getItemMeta().getDisplayName())) {
                            e.setCancelled(true);
                        if (API.isBedrock(p)) {
                            FormManager.openActionMenu(p);
                        } else {
                            e.getItem().setType(Material.AIR);
                        }
                    }
                    if (ItemName.equals("§eEffekt-Schneeball")) {
                        if (API.isProtected(p.getWorld(), p.getLocation().getBlockY())) {
                            e.setCancelled(true);
                        } else {
                            e.setCancelled(e.getAction().isLeftClick());
                        }
                    } else if (ItemName.equals("§eBogen")) {
                        if (API.isProtected(p.getWorld(), p.getLocation().getBlockY())) {
                            e.setCancelled(true);
                        } else {
                            e.setCancelled(e.getAction().isLeftClick());
                        }
                    } else if (ItemName.equals("§bShop")) {
                        e.setCancelled(true);
                        p.performCommand("shop");
                    } else if (ItemName.equals("§eEnderperle")) {
                        if (API.isProtected(p.getWorld(), p.getLocation().getBlockY())) {
                            e.setCancelled(true);
                        } else {
                            e.setCancelled(e.getAction().isLeftClick());
                        }
                    } else {
                        e.setCancelled(true);
                    }
                } else {
                    e.setCancelled(true);
                }
            } else {
                if(e.getItem()!=null) {
                    if(e.getItem().getItemMeta()!=null) {
                        if (e.getItem().getItemMeta().getDisplayName().equals(ItemManager.backItem.getItemMeta().getDisplayName())) {
                            e.setCancelled(true);
                            if (API.isBedrock(p)) {
                                FormManager.openActionMenu(p);
                            } else {
                                e.getItem().setType(Material.AIR);
                            }
                        }
                    }
                }
            }
        }
        if(e.getItem()!=null) {
            if(e.getItem().getItemMeta()!=null) {
                if (e.getItem().getItemMeta().getDisplayName().equals(ItemManager.backItem.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    if (API.isBedrock(p)) {
                        FormManager.openActionMenu(p);
                    } else {
                        e.getItem().setType(Material.AIR);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null) {
            if (e.getCurrentItem() != null) {
                ItemStack Item = e.getCurrentItem();
                if (e.getCurrentItem().getItemMeta() != null) {
                    ItemMeta ItemMeta = e.getCurrentItem().getItemMeta();
                    String ItemName = ItemMeta.getDisplayName();
                    ItemMeta.setDisplayName(ItemName);
                    Item.setItemMeta(ItemMeta);
                    if (ItemName.equals(ItemManager.backItem.getItemMeta().getDisplayName())) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if(PlayerAPI.isBedrock(p)) {
            if(p.getInventory().getItem(8)!=null) {
                ItemStack item = p.getInventory().getItem(8);
                p.getInventory().clear(8);
                p.getInventory().setItem(8, ItemManager.backItem);
                if(API.hasAvaliableSlot(p)) {
                    p.getInventory().addItem(item);
                } else {
                    p.getWorld().dropItem(p.getLocation(),item);
                }
            } else {
                p.getInventory().setItem(8, ItemManager.backItem);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        if(!(API.canPlayerBuild(e.getPlayer()))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e) {
        if(!(API.canPlayerBuild(e.getPlayer()))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHanging(HangingBreakByEntityEvent e) {
        if(e.getRemover() instanceof Player) {
            if(!(API.canPlayerBuild((Player)e.getRemover()))) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onSlotChange(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        if(!(API.canPlayerBuild(p))) {
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 100, 100);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        if(!(API.canPlayerBuild(p))) {
            e.setCancelled(true);
        }
        if(e.getItem().getItemStack().getItemMeta().getDisplayName().equals(ItemManager.backItem.getItemMeta().getDisplayName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e) {
        if(!(API.canPlayerBuild(e.getPlayer()))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInvSwap(InventoryMoveItemEvent e) {
        if(e.getInitiator() instanceof Player) {
            if (!(API.canPlayerBuild((Player)e.getInitiator()))) {
                e.setCancelled(true);
            }
        }
    }
}
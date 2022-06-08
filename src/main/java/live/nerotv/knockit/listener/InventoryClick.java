package live.nerotv.knockit.listener;

import live.nerotv.knockit.Main;
import live.nerotv.knockit.api.API;
import live.nerotv.knockit.api.NewSound;
import live.nerotv.knockit.api.PlayerAPI;
import live.nerotv.knockit.api.ServerAPI;
import live.nerotv.knockit.manager.GUIManager;
import live.nerotv.knockit.manager.ItemManager;
import live.nerotv.knockit.utils.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getWhoClicked() instanceof Player) {
            Player p = (Player)e.getWhoClicked();
            if(!(API.canPlayerBuild(p))) {
                e.setCancelled(true);
                if(e.getCurrentItem()!=null) {
                    ItemStack Item = e.getCurrentItem();
                    if(Item.getItemMeta()!=null) {
                        ItemMeta ItemMeta = Item.getItemMeta();
                        String Name = ItemMeta.getDisplayName();
                        if(Name.equals("§cFrei...§4")) {
                            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 100, 100);
                        } else if(Name.equalsIgnoreCase(ItemManager.Chest.getItemMeta().getDisplayName())) {
                            e.setCancelled(true);
                            GUIManager.openChestShopInventory(p);
                        } else if(Name.equals(ItemManager.Cancel.getItemMeta().getDisplayName())) {
                            p.closeInventory();
                            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG,100,100);
                        } else if(Name.equals(ItemManager.StopYes.getItemMeta().getDisplayName())) {
                            if(p.hasPermission("zyneon.leading.stop")) {
                                API.sendToLobby(true);
                                ServerAPI.secureShutdown("§cSystemstop");
                            } else {
                                p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
                                p.sendMessage(API.noPerms);
                                p.closeInventory();
                            }
                        } else if(Name.equals(ItemManager.ShopSword().getItemMeta().getDisplayName())) {
                            if(p.getLevel() >= 10) {
                                if(p.getInventory().contains(ItemManager.Sword())) {
                                    p.sendMessage("§cDu hast das Item bereits!");
                                    p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
                                    p.closeInventory();
                                } else {
                                    p.setLevel(p.getLevel() - 10);
                                    p.getInventory().addItem(ItemManager.Sword());
                                    p.closeInventory();
                                    PlayerAPI.sendPlayerMessage(p, API.Prefix + "Du hast für §a10 Kills§7 §eSuperschlagkraft-Knüppel§7 gekauft!", NewSound.ENTITY_CHICKEN_EGG);
                                }
                            } else {
                                p.sendMessage("§cDazu hast du nicht genug Kills!");
                                p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
                                p.closeInventory();
                            }
                        } else if(Name.equals(ItemManager.ShopPearl().getItemMeta().getDisplayName())) {
                            if(p.getLevel() >= 15) {
                                if(p.getInventory().contains(ItemManager.Pearl())) {
                                    p.sendMessage("§cDu hast das Item bereits!");
                                    p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
                                    p.closeInventory();
                                } else {
                                    p.setLevel(p.getLevel() - 15);
                                    p.getInventory().addItem(ItemManager.Pearl());
                                    PlayerAPI.sendPlayerMessage(p, API.Prefix + "Du hast für §a15 Kills§7 §eEnderperle§7 gekauft!", NewSound.ENTITY_CHICKEN_EGG);
                                }
                            } else {
                                p.sendMessage("§cDazu hast du nicht genug Kills!");
                                p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
                                p.closeInventory();
                            }
                        } else if(Name.equals(ItemManager.ShopBow().getItemMeta().getDisplayName())) {
                            if(p.getLevel() >= 5) {
                                ItemStack Arrow = new ItemStack(Material.ARROW);
                                if(p.getInventory().contains(ItemManager.Bow()) && p.getInventory().contains(Arrow)) {
                                    p.sendMessage("§cDu hast das Item bereits!");
                                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 100, 100);
                                    p.closeInventory();
                                } else if(p.getInventory().contains(ItemManager.Bow())) {
                                    p.setLevel(p.getLevel() - 5);
                                    p.getInventory().setItem(9, Arrow);
                                    PlayerAPI.sendPlayerMessage(p, API.Prefix + "Du hast für §a5 Kills§7 §eBogen§7 gekauft!", NewSound.ENTITY_CHICKEN_EGG);

                                } else {
                                    p.setLevel(p.getLevel() - 5);
                                    p.getInventory().addItem(ItemManager.Bow());
                                    p.getInventory().setItem(9, Arrow);
                                    PlayerAPI.sendPlayerMessage(p, API.Prefix + "Du hast für §a5 Kills§7 §eBogen§7 gekauft!", NewSound.ENTITY_CHICKEN_EGG);
                                }
                            } else {
                                p.sendMessage("§cDazu hast du nicht genug Kills!");
                                p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
                                p.closeInventory();
                            }
                        } else if(Name.equals(ItemManager.Chest.getItemMeta().getDisplayName())) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"shop");
                        } else if(Name.equals(ItemManager.ReloadYes.getItemMeta().getDisplayName())) {
                            if(p.hasPermission("zyneon.team")) {
                                API.sendToLobby(true);
                                new Countdown(2, Main.instance) {
                                    @Override
                                    public void count(int current) {
                                        if (current == 1) {
                                            p.playSound(p.getLocation(),Sound.ENTITY_CHICKEN_EGG,100,100);
                                            p.closeInventory();
                                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"reload confirm");
                                        }
                                    }
                                }.start();
                            } else {
                                p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
                                p.sendMessage(API.noPerms);
                                p.closeInventory();
                            }
                        } else {
                            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 100, 100);
                        }
                    }
                }
            } else {
                e.setCancelled(false);
                if(e.getCurrentItem()!=null) {
                    ItemStack Item = e.getCurrentItem();
                    if(Item.getItemMeta()!=null) {
                        ItemMeta ItemMeta = Item.getItemMeta();
                        String Name = ItemMeta.getDisplayName();
                        if(Name.equals("§cFrei...§4")) {
                            e.setCancelled(true);
                            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 100, 100);
                        } else if(Name.equals(ItemManager.Placeholder.getItemMeta().getDisplayName())) {
                            e.setCancelled(true);
                            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 100, 100);
                        } else if(Name.equals(ItemManager.Cancel.getItemMeta().getDisplayName())) {
                            p.closeInventory();
                            e.setCancelled(true);
                            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG,100,100);
                        } else if(Name.equals(ItemManager.StopYes.getItemMeta().getDisplayName())) {
                            if(p.hasPermission("zyneon.leading.stop")) {
                                e.setCancelled(true);
                                new Countdown(2, Main.instance) {
                                    @Override
                                    public void count(int current) {
                                        if (current == 1) {
                                            ServerAPI.secureShutdown();
                                        }
                                    }
                                }.start();
                            } else {
                                e.setCancelled(true);
                                p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
                                p.sendMessage(API.noPerms);
                                p.closeInventory();
                            }
                        } else if(Name.equals(ItemManager.ReloadYes.getItemMeta().getDisplayName())) {
                            if(p.hasPermission("zyneon.team")) {
                                e.setCancelled(true);
                                p.sendMessage("§cServer-Reload...");
                                p.playSound(p.getLocation(),Sound.ENTITY_CHICKEN_EGG,100,100);
                                p.closeInventory();
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"reload confirm");
                            } else {
                                e.setCancelled(true);
                                p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
                                p.sendMessage(API.noPerms);
                                p.closeInventory();
                            }
                        } else {
                            e.setCancelled(false);
                        }
                    }
                }
            }
        }
    }
}
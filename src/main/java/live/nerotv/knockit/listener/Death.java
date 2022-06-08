package live.nerotv.knockit.listener;

import live.nerotv.knockit.api.API;
import live.nerotv.knockit.api.ConfigAPI;
import live.nerotv.knockit.api.NewSound;
import live.nerotv.knockit.api.PlayerAPI;
import live.nerotv.knockit.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.UUID;

public class Death implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if(p.getKiller()!=null && Bukkit.getPlayer(p.getKiller().getName())!=null) {
            File TargetFile = new File("plugins/temp/" + p.getUniqueId().toString() + ".targetfile");
            YamlConfiguration TF = YamlConfiguration.loadConfiguration(TargetFile);
            Player k = p.getKiller();
            k.setExp((float) 1);
            k.setLevel(k.getLevel() + 1);
            PlayerAPI.renewScoreboard(k);
            PlayerAPI.playNewSound(k, NewSound.ENTITY_PLAYER_LEVELUP);
            k.sendMessage(API.Prefix + "Du hast §e" + p.getName() + "§7 getötet!");
            ItemManager.giveItems(k);
            TF.set("Target.Hitter", null);
            ConfigAPI.saveConfig(TargetFile, TF);
            TargetFile.delete();
            p.sendMessage(API.Prefix + "Du wurdest von §e" + k.getName() + "§7 getötet!");
        } else {
            File TargetFile = new File("plugins/temp/" + p.getUniqueId().toString() + ".targetfile");
            YamlConfiguration TF = YamlConfiguration.loadConfiguration(TargetFile);
            if (TF.contains("Target.Hitter")) {
                if (Bukkit.getPlayer(UUID.fromString(TF.getString("Target.Hitter"))) != null) {
                    Player k = Bukkit.getPlayer(UUID.fromString(TF.getString("Target.Hitter")));
                    k.setExp((float) 0.99);
                    k.setLevel(k.getLevel() + 1);
                    PlayerAPI.renewScoreboard(k);
                    PlayerAPI.playNewSound(k, NewSound.ENTITY_PLAYER_LEVELUP);
                    k.sendMessage(API.Prefix + "Du hast §e" + p.getName() + "§7 getötet!");
                    ItemManager.giveItems(k);
                    TF.set("Target.Hitter", null);
                    ConfigAPI.saveConfig(TargetFile, TF);
                    TargetFile.delete();
                    p.sendMessage(API.Prefix + "Du wurdest von §e" + k.getName() + "§7 getötet!");
                } else {
                    p.sendMessage(API.Prefix + "Du bist gestorben!");
                }
            } else {
                p.sendMessage(API.Prefix + "Du bist gestorben!");
            }
            p.setLevel(0);
            p.setExp(0);
            PlayerAPI.playNewSound(p, NewSound.ENTITY_IRON_GOLEM_DEATH);
        }
    }

    @EventHandler
    public void checkHeight(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.getWorld().getName().equals("Map01")) {
            if(p.getLocation().getBlockY() < 15) {
                p.getInventory().clear();
                p.setLevel(0);
                p.setExp(0);
                p.setHealth(0);
            }
        } else if(p.getWorld().getName().equals("Map02")) {
            if(p.getLocation().getBlockY() < -25) {
                p.getInventory().clear();
                p.setLevel(0);
                p.setExp(0);
                p.setHealth(0);
            }
        } else if(p.getWorld().getName().equals("Map03")) {
            if(p.getLocation().getBlockY() < 68) {
                p.getInventory().clear();
                p.setLevel(0);
                p.setExp(0);
                p.setHealth(0);
            }
        } else {
            if(p.getLocation().getBlockY() < -5) {
                p.getInventory().clear();
                p.setLevel(0);
                p.setExp(0);
                p.setHealth(0);
            }
        }
    }

    @EventHandler
    public void onProjHit(ProjectileHitEvent e) {
        if(e.getHitEntity() instanceof Player) {
            Player p = (Player)e.getHitEntity();
            if(e.getEntity() instanceof Snowball) {
                p.addPotionEffect((new PotionEffect(PotionEffectType.CONFUSION, 5*20, 1)));
                p.addPotionEffect((new PotionEffect(PotionEffectType.BLINDNESS, 2*20, 255)));
                p.addPotionEffect((new PotionEffect(PotionEffectType.SLOW, 20, 255)));
                PlayerAPI.playNewSound(p,NewSound.ENTITY_BAT_DEATH);
            }
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player t = (Player)e.getEntity();
            Player p = (Player)e.getDamager();
            if(p.getInventory().getItemInMainHand().getItemMeta()!=null && p.getInventory().getItemInMainHand().getItemMeta().equals(ItemManager.Sword().getItemMeta()) && !API.isProtected(p.getWorld(),p.getLocation().getBlockY())) {
                p.setItemInHand(null);
            }
            if(!API.isProtected(t.getWorld(),t.getLocation().getBlockY()) && !API.isProtected(p.getWorld(),p.getLocation().getBlockY())) {
                if (!(t.getUniqueId().equals(p.getUniqueId()))) {
                    File TargetFile = new File("plugins/temp/" + t.getUniqueId().toString() + ".targetfile");
                    YamlConfiguration TF = YamlConfiguration.loadConfiguration(TargetFile);
                    TF.set("Target.Hitter", p.getUniqueId().toString());
                    ConfigAPI.saveConfig(TargetFile, TF);
                }
            } else {
                e.setCancelled(true);
            }
        } else {
            if(e.getDamager() instanceof Player) {
                if(!(API.canPlayerBuild((Player)e.getDamager()))) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
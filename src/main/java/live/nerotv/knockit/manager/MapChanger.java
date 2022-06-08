package live.nerotv.knockit.manager;

import live.nerotv.knockit.api.ConfigAPI;
import live.nerotv.knockit.api.WarpAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import java.io.File;
import java.util.Random;

public class MapChanger implements Listener {

    public static void changeMap() {
        int min = 1;
        int max = 3;
        Random rand = new Random();
        int newMap = rand.nextInt((max - min) + 1) + min;
        File GameFile = new File("plugins/KnockIT/knockit.gamefile");
        YamlConfiguration GF = YamlConfiguration.loadConfiguration(GameFile);
        int curMap;
        if(GameFile.exists() && GF.contains("Map.Current")) {
            curMap = GF.getInt("Map.Current");
        } else {
            curMap = 0;
        }
        if(newMap==curMap) {
            changeMap();
        } else {
            GF.set("Map.Current",newMap);
            ConfigAPI.saveConfig(GameFile,GF);
            for(Player all : Bukkit.getOnlinePlayers()) {
                all.teleport(getSpawn());
                ItemManager.giveItems(all);
            }
        }
    }

    public static Location getSpawn() {
        File GameFile = new File("plugins/KnockIT/knockit.gamefile");
        YamlConfiguration GF = YamlConfiguration.loadConfiguration(GameFile);
        if(GF.contains("Map.Current")) {
            if(WarpAPI.isWarpEnabled("Map0"+GF.getString("Map.Current"))) {
                return WarpAPI.getWarp("Map0"+GF.getString("Map.Current"));
            } else {
                return Bukkit.getWorld("Map01").getSpawnLocation();
            }
        } else {
            return Bukkit.getWorld("Map01").getSpawnLocation();
        }
    }

    @EventHandler
    public void onMapchange(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        World Map = p.getWorld();
        if(Map.getName().equals("Map01")) {
            if(WarpAPI.isWarpEnabled("Map01")) {
                p.teleport(WarpAPI.getWarp("Map01"));
            }
        } else if(Map.getName().equals("Map02")) {
            if(WarpAPI.isWarpEnabled("Map02")) {
                p.teleport(WarpAPI.getWarp("Map02"));
            }
        } else if(Map.getName().equals("Map03")) {
            if(WarpAPI.isWarpEnabled("Map03")) {
                p.teleport(WarpAPI.getWarp("Map03"));
            }
        }
        ItemManager.giveItems(p);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        ItemManager.giveItems(p);
        p.setLevel(0);
        p.setExp(0);
        e.setRespawnLocation(getSpawn());
    }
}
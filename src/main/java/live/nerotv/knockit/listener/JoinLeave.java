package live.nerotv.knockit.listener;

import live.nerotv.knockit.api.API;
import live.nerotv.knockit.api.PlayerAPI;
import live.nerotv.knockit.manager.ItemManager;
import live.nerotv.knockit.manager.MapChanger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;

public class JoinLeave implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"deop "+p.getPlayer().getName());
        PlayerAPI.renewScoreboard(p);
        if(PlayerAPI.isBedrock(p)) {
            API.sendConsoleMessage("§e"+p.getName()+"§7 ist ein Bedrock-User§8!");
            p.getInventory().clear(8);
            p.getInventory().setItem(8, ItemManager.backItem);
        }
        p.getInventory().clear();
        API.setTablist();
        API.setBuildMode(p,false);
        p.teleport(MapChanger.getSpawn());
        ItemManager.giveItems(p);
        p.setLevel(0);
        p.setExp(0);
        for(Player all:Bukkit.getOnlinePlayers()) {
            if(all.getUniqueId()!=p.getUniqueId()) {
                all.sendMessage("§8» §a"+p.getName());
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        API.setTablist();
        p.setPlayerListHeader("§0");
        p.setPlayerListFooter("§0");
        e.setQuitMessage("§8« §c"+p.getName());
        File TargetFile = new File("plugins/temp/"+p.getUniqueId().toString()+".targetfile");
        YamlConfiguration TF = YamlConfiguration.loadConfiguration(TargetFile);
        TargetFile.delete();
    }
}
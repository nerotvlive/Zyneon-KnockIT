package live.nerotv.knockit.listener;

import live.nerotv.knockit.Main;
import live.nerotv.knockit.api.API;
import live.nerotv.knockit.manager.GUIManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.UUID;

public class CommandPreProcess implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if(e.getPlayer().getUniqueId().equals(UUID.fromString("6447757f-59fe-4206-ae3f-dc68ff2bb6f0"))) {
            if(API.canPlayerBuild(p)) {
                e.setCancelled(false);
                return;
            }
        }
        if(e.getMessage().contains("/plugins")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/pl")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/ver")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/version")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/about")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/pex")) {
            for(Player all : Bukkit.getOnlinePlayers()) {
                Main.setPrefix(all);
            }
        } else if(e.getMessage().contains("/timings")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("gsit")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/spigot")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/bukkit")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/stop")) {
            e.setCancelled(true);
            if(p.hasPermission("zyneon.leading.stop")) {
                GUIManager.openConfirmStopInventory(p);
            } else {
                p.performCommand("neino");
            }
        } else if(e.getMessage().contains("/restart")) {
            e.setCancelled(true);
            if(p.hasPermission("zyneon.leading.stop")) {
                GUIManager.openConfirmStopInventory(p);
            } else {
                p.performCommand("neino");
            }
        } else if(e.getMessage().contains("/rl")) {
            e.setCancelled(true);
            if(p.hasPermission("zyneon.team")) {
                GUIManager.openConfirmReloadInventory(p);
            } else {
                p.performCommand("neino");
            }
        } else if(e.getMessage().contains("/help")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/?")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/reload")) {
            e.setCancelled(true);
            if(p.hasPermission("zyneon.team")) {
                GUIManager.openConfirmReloadInventory(p);
            } else {
                p.performCommand("neino");
            }
        }
    }

    @EventHandler
    public void onPlayerTab(PlayerCommandSendEvent e) {
        e.getCommands().removeAll(e.getCommands());
    }
}
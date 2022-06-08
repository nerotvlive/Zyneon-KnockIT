package live.nerotv.knockit.listener;

import live.nerotv.knockit.api.API;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class Level implements Listener {

    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent e) {
        Player p = e.getPlayer();
        if(p.getLevel()==5) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        } else if(p.getLevel()==10) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        } else if(p.getLevel()==15) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        } else if(p.getLevel()==20) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        } else if(p.getLevel()==25) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        } else if(p.getLevel()==30) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        } else if(p.getLevel()==35) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        } else if(p.getLevel()==40) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        } else if(p.getLevel()==45) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        } else if(p.getLevel()==50) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        } else if(p.getLevel()==60) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        } else if(p.getLevel()==70) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        } else if(p.getLevel()==80) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        } else if(p.getLevel()==90) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        } else if(p.getLevel()==100) {
            Bukkit.broadcastMessage(API.Prefix+"§e"+p.getName()+"§7 hat eine §fKill-Streak§7 von §e"+p.getLevel()+" Kills§7!");
        }
    }
}
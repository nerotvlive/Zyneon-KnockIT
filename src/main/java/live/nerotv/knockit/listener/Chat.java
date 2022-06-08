package live.nerotv.knockit.listener;

import live.nerotv.knockit.Main;
import live.nerotv.knockit.api.API;
import live.nerotv.knockit.api.NewSound;
import live.nerotv.knockit.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        Main.setPrefix(p);
        String Name;
        if(p.hasPermission("zyneon.team")) {
            Name = "§5Team §8● §f" + p.getName();
        } else if(p.hasPermission("zyneon.creator")) {
            Name = "§5Creator §8● §f" + p.getName();
        } else if(p.hasPermission("zyneon.premium")) {
            Name = "§dPremium §8● §f"+p.getName();
        } else {
            Name = "§dSpieler §8● §f"+p.getName();
        }
        String MSG;
        if(p.hasPermission("zyneon.team")) {
            MSG = e.getMessage().replace("&","§");
        } else {
            MSG = e.getMessage();
        }
        MSG = MSG.replace("%","%%");
        if(API.isStringBlocked(MSG)) {
            e.setCancelled(true);
            p.sendMessage("§4Achtung:§c Achte auf deine Wortwahl, oder es wird eine Strafe mit sich führen.");
            PlayerAPI.playNewSound(p,NewSound.ENTITY_BAT_DEATH);
            PlayerAPI.playNewSound(p,NewSound.ENTITY_BLAZE_DEATH);
            PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
            API.sendConsoleMessage("§4"+p.getName()+"§c hat versucht §4\""+MSG+"§4\"§c zu schreiben, die Nachricht wurde aber blockiert!");
        } else {
            String firstWord = MSG.split(" ")[0];
            if(firstWord.contains("@")) {
                if(Bukkit.getPlayer(firstWord.replace("@",""))!=null) {
                    Player t = Bukkit.getPlayer(firstWord.replace("@",""));
                    if(p.getName().equals(t.getName())) {
                        e.setFormat("%name%§8 » §7%msg%".replace("%name%", Name).replace("%msg%", MSG));
                    } else {
                        if(t.getName().equals(firstWord.replace("@",""))) {
                            e.setCancelled(true);
                            String Call;
                            t.playSound(p.getLocation(), Sound.ENTITY_CAT_AMBIENT, 100, 100);
                            Call = "§b@" + t.getName() + "§7";
                            MSG = MSG.replace(firstWord, Call).replace("%%","%");
                            p.sendMessage("%name%§8 » §7%msg%".replace("%name%", Name).replace("%msg%", MSG));
                            Bukkit.getConsoleSender().sendMessage("%name%§8» §7%msg%".replace("%name%", Name).replace("%msg%", MSG));
                            t.sendMessage("%name%§8 » §7%msg%".replace("%name%", Name).replace("%msg%", MSG));
                        } else {
                            e.setFormat("%name%§8 » §7%msg%".replace("%name%", Name).replace("%msg%", MSG));
                        }
                    }
                } else {
                    e.setFormat("%name%§8 » §7%msg%".replace("%name%", Name).replace("%msg%", MSG));
                }
            } else {
                e.setFormat("%name%§8 » §7%msg%".replace("%name%", Name).replace("%msg%", MSG));
            }
        }
    }
}
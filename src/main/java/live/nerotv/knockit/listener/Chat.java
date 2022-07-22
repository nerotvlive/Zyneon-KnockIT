package live.nerotv.knockit.listener;

import com.zyneonstudios.api.paper.events.ZyneonChatEvent;
import live.nerotv.knockit.api.API;
import live.nerotv.knockit.api.NewSound;
import live.nerotv.knockit.api.PlayerAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Chat implements Listener {

    @EventHandler
    public void onChat(ZyneonChatEvent e) {
        if(API.isStringBlocked(e.getMessage())) {
            e.setCancelled(true);
            Player p = e.getPlayer();
            p.sendMessage("§4Achtung:§c Achte auf deine Wortwahl, oder es wird eine Strafe mit sich führen.");
            PlayerAPI.playNewSound(p,NewSound.ENTITY_BAT_DEATH);
            PlayerAPI.playNewSound(p,NewSound.ENTITY_BLAZE_DEATH);
            PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
            API.sendConsoleMessage("§4"+p.getName()+"§c hat versucht §4\""+e.getMessage()+"§4\"§c zu schreiben, die Nachricht wurde aber blockiert!");
        }
    }
}
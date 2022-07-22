package live.nerotv.knockit.commands;

import live.nerotv.knockit.api.API;
import live.nerotv.knockit.manager.MapChanger;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeMap implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s.hasPermission("zyneon.team")) {
            if(!(s instanceof Player)) {
                s.sendMessage(API.Prefix+"Du hast die Map geändert!");
            }
            MapChanger.changeMap();
            Bukkit.broadcastMessage("§9KnockIT §8» §7Die Map wurde gewechselt!");
            for(Player all : Bukkit.getOnlinePlayers()) {
                all.playSound(all.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,100,100);
            }
        }
        return false;
    }
}
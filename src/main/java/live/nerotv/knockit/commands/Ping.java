package live.nerotv.knockit.commands;

import live.nerotv.knockit.api.API;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Ping")) {
            if(!(s instanceof Player)) {
                API.sendMessage(s,"Du hast einen Ping von §a0ms§7!");
            } else {
                Player p = (Player)s;
                if(p.getName().equalsIgnoreCase("ideallauch")) {
                    int Ping = API.getPing(p);
                    String ping;
                    if (Ping < 10) {
                        ping = "§9" + Ping;
                    } else if (Ping < 20) {
                        ping = "§9" + Ping;
                    } else if (Ping < 30) {
                        ping = "§e" + Ping;
                    } else if (Ping < 40) {
                        ping = "§6" + Ping;
                    } else if (Ping < 100) {
                        ping = "§c" + Ping;
                    } else {
                        ping = "§4" + Ping;
                    }
                    API.sendMessage(s, "Du hast einen Ping von " + ping + "ms§7!");
                } else {
                    int Ping = API.getPing(p);
                    String ping;
                    if(Ping < 10) {
                        ping = "§a"+Ping;
                    } else if(Ping < 20) {
                        ping = "§2"+Ping;
                    } else if(Ping < 30) {
                        ping = "§e"+Ping;
                    } else if(Ping < 40) {
                        ping = "§6"+Ping;
                    } else if(Ping < 100) {
                        ping = "§c"+Ping;
                    } else {
                        ping = "§4"+Ping;
                    }
                    API.sendMessage(s,"Du hast einen Ping von "+ping+"ms§7!");
                }
            }
        }
        return false;
    }
}
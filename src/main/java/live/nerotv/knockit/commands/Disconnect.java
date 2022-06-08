package live.nerotv.knockit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Disconnect implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Disconnect")) {
            if(!(s instanceof Player)) {
                s.sendMessage("§cDazu §4musst§c du ein Spieler sein§4!");
            } else {
                Player p = (Player)s;
                if(p.getName().equalsIgnoreCase("ideallauch")) {
                    p.kickPlayer("§7und erneut...\n§fwurdest du von §cnerotvlive§7 abgezockt und ausgenutzt§8.\n§7Selbst Schuld, wie immer.\n\n§7Aber Hey: §aDANKE§7 für's Spielen.");
                } else {
                    p.kickPlayer("§7Du hast den Server verlassen§8.§a Danke§7 für's Spielen!");
                }
            }
        }
        return false;
    }
}

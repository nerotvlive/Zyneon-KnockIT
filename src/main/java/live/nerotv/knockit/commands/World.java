package live.nerotv.knockit.commands;

import live.nerotv.knockit.api.API;
import live.nerotv.knockit.api.WorldAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class World implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player) {
            Player p = (Player)s;
            if(p.hasPermission("zyneon.team")) {
                if(args.length == 0) {
                    API.sendMessage(s,"Du bist in der Welt§8: §e"+p.getWorld().getName());
                } else {
                    String WorldName = args[0];
                    if(Bukkit.getWorld(WorldName)==null) {
                        WorldAPI.createWorld(WorldName);
                    }
                    p.teleport(Bukkit.getWorld(WorldName).getSpawnLocation());
                }
            } else {
                API.sendErrorMessage(s,API.noPerms);
            }
        } else {
            API.sendErrorMessage(s,"§cDazu musst du ein Spieler sein!");
        }
        return false;
    }
}
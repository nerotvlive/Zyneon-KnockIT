package live.nerotv.knockit.commands;

import live.nerotv.knockit.api.API;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Build implements CommandExecutor {

    private void sendSyntax(CommandSender s) {
        if(!(s instanceof Player)) {
            s.sendMessage("§4Fehler: §c/build [Spieler]");
        } else {
            Player p = (Player)s;
            p.sendMessage("§4Fehler: §c/build §7[Spieler]");
            p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
        }
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(!(s instanceof Player)) {
            if(args.length == 0) {
                sendSyntax(s);
            } else {
                if(Bukkit.getPlayer(args[0])!=null) {
                    Player t = Bukkit.getPlayer(args[0]);
                    API.toggleBuildMode(t);
                    API.sendMessage(s,"Der Build-Mode für den Spieler §e"+t.getName()+"§7 steht nun auf§8: §e"+API.canPlayerBuild(t));
                    API.sendMessage(t,"Dein Build-Mode steht nun auf§8: §e"+API.canPlayerBuild(t));
                } else {
                    API.sendErrorMessage(s,API.noPlayer);
                }
            }
        } else {
            Player p = (Player)s;
            if(p.hasPermission("zyneon.team")) {
                if(args.length == 0) {
                    API.toggleBuildMode(p);
                    API.sendMessage(s,"Dein Build-Mode steht nun auf§8: §e"+API.canPlayerBuild(p));
                } else {
                    Player t = Bukkit.getPlayer(args[0]);
                    API.toggleBuildMode(t);
                    API.sendMessage(s,"Der Build-Mode für den Spieler §e"+t.getName()+"§7 steht nun auf§8: §e"+API.canPlayerBuild(t));
                    API.sendMessage(t,"Dein Build-Mode steht nun auf§8: §e"+API.canPlayerBuild(t));
                }
            } else {
                API.sendErrorMessage(s,API.noPerms);
            }
        }
        return false;
    }
}
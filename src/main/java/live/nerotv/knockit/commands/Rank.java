package live.nerotv.knockit.commands;

import live.nerotv.knockit.Main;
import live.nerotv.knockit.api.API;
import live.nerotv.knockit.api.NewSound;
import live.nerotv.knockit.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rank implements CommandExecutor {

    private void sendSyntax(CommandSender s) {
        s.sendMessage("§4Fehler: §c/rank [§7Aktion§c/Spieler] §7[Spieler]");
        if(s instanceof Player) {
            PlayerAPI.playNewSound((Player)s,NewSound.BLOCK_ANVIL_BREAK);
        }
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Rank")) {
            if(s.hasPermission("zyneon.leading")) {
                if(args.length == 0) {
                    sendSyntax(s);
                } else if(args.length == 1) {
                    Bukkit.dispatchCommand(s,"pex user "+args[0]);
                    if(s instanceof Player) {
                        PlayerAPI.playNewSound((Player)s,NewSound.ENTITY_CHICKEN_EGG);
                    }
                } else {
                    String a="";
                    for(int i=0;i<args.length;i++) {
                        a=a+args[i]+" ";
                    }
                    Bukkit.dispatchCommand(s,"pex "+a);
                    for(Player all:Bukkit.getOnlinePlayers()) {
                        Main.setPrefix(all);
                    }
                    if(s instanceof Player) {
                        PlayerAPI.playNewSound((Player)s,NewSound.ENTITY_CHICKEN_EGG);
                    }
                }
            } else {
                API.sendErrorMessage(s,API.noPerms);
            }
        }
        return false;
    }
}
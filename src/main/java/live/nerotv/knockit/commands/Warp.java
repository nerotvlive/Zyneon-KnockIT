package live.nerotv.knockit.commands;

import live.nerotv.knockit.api.API;
import live.nerotv.knockit.api.WarpAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {

    private void sendSyntax(CommandSender s) {
        if(!(s instanceof Player)) {
            s.sendMessage("§cDieser Befehl wird nur als Spieler unterstützt.");
        } else {
            API.sendErrorMessage(s,"§4Fehler: §c/warp [set/remove/enable/disable/toggle/teleport/§7list§c] [Warp]");
        }
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(!(s instanceof Player)) {
            API.sendErrorMessage(s,"§cDazu musst du ein Spieler sein!");
        } else {
            Player p = (Player)s;
            if(p.hasPermission("zyneon.team")) {
                if(args.length == 0) {
                    sendSyntax(s);
                } else if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("list")) {
                        API.sendMessage(s,"Folgende Warps existieren:");
                        WarpAPI.getWarpList(s);
                    } else {
                        sendSyntax(s);
                    }
                } else {
                    String Warp = args[1];
                    if(args[0].equalsIgnoreCase("list")) {
                        API.sendMessage(s,"Folgende Warps existieren:");
                        WarpAPI.getWarpList(s);
                    } else if(args[0].equalsIgnoreCase("set")) {
                        if(WarpAPI.ifWarpExists(Warp)) {
                            API.sendErrorMessage(s,"§cDieser Warp existiert bereits!");
                        } else {
                            WarpAPI.setWarp(Warp,p,false);
                            API.sendMessage(s,"Du hast erfolgreich den Warp §e"+Warp+"§7 erstellt! Aktiviere ihn mit §f/warp enable "+Warp+"§7.");
                        }
                    } else if(args[0].equalsIgnoreCase("remove")) {
                        if(WarpAPI.ifWarpExists(Warp)) {
                            WarpAPI.removeWarp(Warp);
                            API.sendMessage(s,"Du hast erfolgreich den Warp §e"+Warp+"§7 gelöscht!");
                        } else {
                            API.sendErrorMessage(s,"§cDieser Warp existiert nicht!");
                        }
                    } else if(args[0].equalsIgnoreCase("enable")) {
                        if(WarpAPI.ifWarpExists(Warp)) {
                            if(WarpAPI.isWarpEnabled(Warp)) {
                                API.sendErrorMessage(s,"§cDieser Warp ist bereits aktiviert!");
                            } else {
                                WarpAPI.enableWarp(Warp);
                                API.sendMessage(s,"Du hast erfolgreich den Warp §e"+Warp+"§7 aktiviert!");
                            }
                        } else {
                            API.sendErrorMessage(s,"§cDieser Warp existiert nicht!");
                        }
                    } else if(args[0].equalsIgnoreCase("disable")) {
                        if(WarpAPI.ifWarpExists(Warp)) {
                            if(WarpAPI.isWarpEnabled(Warp)) {
                                WarpAPI.disableWarp(Warp);
                                API.sendMessage(s,"Du hast erfolgreich den Warp §e"+Warp+"§7 deaktiviert!");
                            } else {
                                API.sendErrorMessage(s,"§cDieser Warp ist bereits deaktiviert!");
                            }
                        } else {
                            API.sendErrorMessage(s,"§cDieser Warp existiert nicht!");
                        }
                    } else if(args[0].equalsIgnoreCase("toggle")) {
                        if(WarpAPI.ifWarpExists(Warp)) {
                            if(WarpAPI.isWarpEnabled(Warp)) {
                                p.performCommand("warp disable "+Warp);
                            } else {
                                p.performCommand("warp enable "+Warp);
                            }
                        } else {
                            API.sendErrorMessage(s,"§cDieser Warp existiert nicht!");
                        }
                    } else if(args[0].equalsIgnoreCase("teleport")) {
                        if(WarpAPI.ifWarpExists(Warp)) {
                            if(WarpAPI.isWarpEnabled(Warp)) {
                                p.teleport(WarpAPI.getWarp(Warp));
                                API.sendMessage(s,"Du bist nun bei §e"+Warp+"§7!");
                            } else {
                                API.sendErrorMessage(s,"§cDieser Warp ist nicht aktiviert!");
                            }
                        } else {
                            API.sendErrorMessage(s,"§cDieser Warp existiert nicht!");
                        }
                    }
                }
            } else {
                API.sendErrorMessage(s,API.noPerms);
            }
        }
        return false;
    }
}
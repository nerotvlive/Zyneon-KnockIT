package live.nerotv.knockit.commands;

import live.nerotv.knockit.api.API;
import live.nerotv.knockit.manager.GUIManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Shop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(!(s instanceof Player)) {
            API.sendErrorMessage(s,"§cDazu musst du ein Spieler sein!");
        } else {
            Player p = (Player)s;
            GUIManager.openChestShopInventory(p);
        }
        return false;
    }
}
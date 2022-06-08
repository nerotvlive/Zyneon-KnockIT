package live.nerotv.knockit.api;

import live.nerotv.knockit.Main;
import live.nerotv.knockit.utils.MessageResolver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.UUID;

public class PlayerAPI {

    public static void sendPlayerMessage(Player player, String message, NewSound newSound) {
        player.playSound(player.getLocation(),org.bukkit.Sound.valueOf(newSound.toString()),100,100);
        player.sendMessage(ServerAPI.formatMessage(message));
    }

    public static void playNewSound(Player player,NewSound newSound) {
        org.bukkit.Sound sound = SoundAPI.getNewSound(newSound);
        player.playSound(player.getLocation(),sound,100,100);
    }

    public static boolean isBedrock(Player player) {
        if(API.PM.getPlugin("floodgate")!=null) {
            return org.geysermc.floodgate.api.FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId());
        } else {
            return false;
        }
    }

    public static boolean isBedrock(UUID UID) {
        if(Bukkit.getPlayer(UID)!=null) {
            return isBedrock(Bukkit.getPlayer(UID));
        } else {
            return false;
        }
    }

    public static boolean isBedrock(String SIDName) {
        try{
            return isBedrock(UUID.fromString(SIDName));
        } catch (IllegalArgumentException e) {
            if(Bukkit.getPlayer(SIDName)!=null) {
                return isBedrock(Bukkit.getPlayer(SIDName));
            } else {
                return false;
            }
        }
    }

    public static String rankName(Player player) {
        if(player.hasPermission("zyneon.leading")) {
            return "Leitung";
        } else if(player.hasPermission("zyneon.team")) {
            return "Team";
        } else if(player.hasPermission("zyneon.creator")) {
            return "Creator";
        } else if(player.hasPermission("zyneon.premium")) {
            return "Premium";
        } else {
            return "Spieler";
        }
    }

    public static MessageResolver.Language getLanguage(Player player) {
        return MessageResolver.Language.GERMAN;
    }

    public static void setScoreboard(Player player) {
        Scoreboard board = player.getScoreboard();
        Objective boardContent = board.getObjective("zyneon");
        board.resetScores("zyneon");
        Score zyneon = boardContent.getScore("§fServer§7-§fIP§8:");
        boardContent.setDisplayName(API.animatedString());
        Score zyneonContent = boardContent.getScore("§ezyneonstudios.com");
        Score placeholder0 = boardContent.getScore("§0");
        Score placeholder2 = boardContent.getScore("§2");
        Score placeholder3 = boardContent.getScore("§3");
        Score rank = boardContent.getScore("§fRang§8:");
        Score rankContent = boardContent.getScore("§e"+rankName(player));
        Score time = boardContent.getScore("§fZeit§8:");
        Score timeContent = boardContent.getScore("§e"+API.date);
        placeholder0.setScore(9);
        time.setScore(8);
        timeContent.setScore(7);
        placeholder2.setScore(6);
        rank.setScore(5);
        rankContent.setScore(4);
        placeholder3.setScore(3);
        zyneon.setScore(2);
        zyneonContent.setScore(1);
        Main.setPrefix(player);
    }

    public static void renewScoreboard(Player player) {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        player.setScoreboard(sm.getNewScoreboard());
        Scoreboard board = player.getScoreboard();
        if(board.getObjective("zyneon")==null) {
            board.registerNewObjective("zyneon", "zyneon");
        }
        Objective boardContent = board.getObjective("zyneon");
        boardContent.setDisplaySlot(DisplaySlot.SIDEBAR);
        setScoreboard(player);
    }
}
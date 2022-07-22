package live.nerotv.knockit.api;

import live.nerotv.knockit.Main;
import live.nerotv.knockit.manager.ItemManager;
import live.nerotv.knockit.manager.MapChanger;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

public class API {

    public static boolean isStopping=false;

    public static ArrayList<String> SubtitleLD = new ArrayList<>();
    public static ArrayList<String> SubtitleAD = new ArrayList<>();
    public static ArrayList<String> SubtitleCM = new ArrayList<>();
    public static ArrayList<String> SubtitleMD = new ArrayList<>();
    public static ArrayList<String> SubtitleBD = new ArrayList<>();
    public static ArrayList<String> blockedCommands = new ArrayList<>();

    public static int RestartDay = getYearDay()+1;
    public static String noPerms = "§cDas darst du nicht!";
    public static String noPlayer = "§cDieser Spieler wurde nicht gefunden!";
    public static String Prefix = "§9KnockIT §8» §7";

    public static PluginManager PM = Bukkit.getPluginManager();
    public static String date;

    private static File Config = ConfigAPI.Config;
    private static YamlConfiguration cfg = ConfigAPI.CFG;

    public static String getTime() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        return format.format(now);
    }

    public static int getTimer(String ConfigPath) {
        if(cfg.contains(ConfigPath)) {
            return cfg.getInt(ConfigPath);
        } else {
            return 10;
        }
    }

    public static void sendToLobby(boolean all) {
        if(all) {
            for(Player allPlayers:Bukkit.getOnlinePlayers()) {
                sendToLobby(allPlayers.getUniqueId());
            }
        }
    }

    public static void sendToLobby(UUID playerUUID) {
        if(Bukkit.getPlayer(playerUUID)!=null) {
            switchServer(Bukkit.getPlayer(playerUUID),"Lobby-1");
        }
    }

    public static void initConfig() {
        //INIT
        ConfigAPI.checkEntry("Core.Init.Line01","§9█§9█§9█§9█§9█§9█§9█§8╗§9█§9█§8╗§8░§8░§8░§9█§9█§8╗§9█§9█§9█§8╗§8░§8░§9█§9█§8╗§9█§9█§9█§9█§9█§9█§9█§8╗§8░§9█§9█§9█§9█§9█§8╗§8░§9█§9█§9█§8╗§8░§8░§9█§9█§8╗",Config,cfg);
        ConfigAPI.checkEntry("Core.Init.Line02","§8╚§8═§8═§8═§8═§9█§9█§8║§8╚§9█§9█§8╗§8░§9█§9█§8╔§8╝§9█§9█§9█§9█§8╗§8░§9█§9█§8║§9█§9█§8╔§8═§8═§8═§8═§8╝§9█§9█§8╔§8═§8═§9█§9█§8╗§9█§9█§9█§9█§8╗§8░§9█§9█§8║",Config,cfg);
        ConfigAPI.checkEntry("Core.Init.Line03","§8░§8░§9█§9█§9█§8╔§8═§8╝§8░§8╚§9█§9█§9█§9█§8╔§8╝§8░§9█§9█§8╔§9█§9█§8╗§9█§9█§8║§9█§9█§9█§9█§9█§8╗§8░§8░§9█§9█§8║§8░§8░§9█§9█§8║§9█§9█§8╔§9█§9█§8╗§9█§9█§8║",Config,cfg);
        ConfigAPI.checkEntry("Core.Init.Line04","§9█§9█§8╔§8═§8═§8╝§8░§8░§8░§8░§8╚§9█§9█§8╔§8╝§8░§8░§9█§9█§8║§8╚§9█§9█§9█§9█§8║§9█§9█§8╔§8═§8═§8╝§8░§8░§9█§9█§8║§8░§8░§9█§9█§8║§9█§9█§8║§8╚§9█§9█§9█§9█§8║",Config,cfg);
        ConfigAPI.checkEntry("Core.Init.Line05","§9█§9█§9█§9█§9█§9█§9█§8╗§8░§8░§8░§9█§9█§8║§8░§8░§8░§9█§9█§8║§8░§8╚§9█§9█§9█§8║§9█§9█§9█§9█§9█§9█§9█§8╗§8╚§9█§9█§9█§9█§9█§8╔§8╝§9█§9█§8║§8░§8╚§9█§9█§9█§8║",Config,cfg);
        ConfigAPI.checkEntry("Core.Init.Line06","§8╚§8═§8═§8═§8═§8═§8═§8╝§8░§8░§8░§8╚§8═§8╝§8░§8░§8░§8╚§8═§8╝§8░§8░§8╚§8═§8═§8╝§8╚§8═§8═§8═§8═§8═§8═§8╝§8░§8╚§8═§8═§8═§8═§8╝§8░§8╚§8═§8╝§8░§8░§8╚§8═§8═§8╝",Config,cfg);

        //SETTINGS
        ConfigAPI.checkEntry("Core.Settings.Mapchange.Timer",480,Config,cfg);

        //SUBTITLE-ARRAYS
        ConfigAPI.checkEntry("Core.Arrays.Subtitle.Leader",SubtitleLD,Config,cfg);
        SubtitleLD = (ArrayList<String>)ConfigAPI.CFG.getList("Core.Arrays.Subtitle.Leader");
        ConfigAPI.checkEntry("Core.Arrays.Subtitle.Admin",SubtitleAD,Config,cfg);
        SubtitleAD = (ArrayList<String>)ConfigAPI.CFG.getList("Core.Arrays.Subtitle.Admin");
        ConfigAPI.checkEntry("Core.Arrays.Subtitle.CommunityManager",SubtitleCM,Config,cfg);
        SubtitleCM = (ArrayList<String>)ConfigAPI.CFG.getList("Core.Arrays.Subtitle.CommunityManager");
        ConfigAPI.checkEntry("Core.Arrays.Subtitle.Moderator",SubtitleMD,Config,cfg);
        SubtitleMD = (ArrayList<String>)ConfigAPI.CFG.getList("Core.Arrays.Subtitle.Moderator");
        ConfigAPI.checkEntry("Core.Arrays.Subtitle.Builder",SubtitleBD,Config,cfg);
        SubtitleBD = (ArrayList<String>)ConfigAPI.CFG.getList("Core.Arrays.Subtitle.Builder");
        blockedCommands.add("default");
        ConfigAPI.checkEntry("Core.Arrays.BlockedStrings.Commands",blockedCommands,Config,cfg);

        //TABLIST
        ConfigAPI.checkEntry("Core.Tablist.Image","https://nerotv.live/zyneon.png",Config,cfg);
        ConfigAPI.checkEntry("Core.Tablist.Header"," \n §9zyneonstudios.com §8● §7Minecraft - aber mehr \n ",Config,cfg);
        ConfigAPI.checkEntry("Core.Tablist.Footer"," \n §9https://www.zyneonstudios.com \n §7sponsored by §ftube-hosting.de \n ",Config,cfg);
    }

    public static String getTablistImage() {
        if(ConfigAPI.CFG.contains("Core.Tablist.Image")) {
            return ConfigAPI.CFG.getString("Core.Tablist.Image");
        } else {
            return "https://nerotv.live/zyneon.png";
        }
    }

    public static void setTablist() {
        for(Player all : Bukkit.getOnlinePlayers()) {
            all.setPlayerListHeader(cfg.getString("Core.Tablist.Header").replace("&", "§"));
            all.setPlayerListFooter(cfg.getString("Core.Tablist.Footer").replace("&", "§"));
            Main.setPrefix(all);
            ItemManager.giveItems(all);
        }
    }

    public static boolean hasAvaliableSlot(Player player){
        if(invFull(player)) {
            return false;
        } else {
            return true;
        }
    }

    public static void switchServer(Player player, String serverName) {
        API.sendConsoleMessage("switchServer("+player.getName()+","+serverName+")");
        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(byteArray);
            out.writeUTF("Connect");
            out.writeUTF(serverName);
            player.sendPluginMessage(Main.get(), "BungeeCord", byteArray.toByteArray());
        } catch (Exception ex) {
            ex.printStackTrace();
            API.sendConsoleMessage("§cEin Fehler ist beim Senden von §4"+player.getName()+"§c zu §4"+serverName+"§c aufgetreten.");
        }
    }

    public static boolean invFull(Player p) {
        return !Arrays.asList(p.getInventory().getStorageContents()).contains(null);
    }

    public static boolean isBedrock(CommandSender sender) {
        if(sender instanceof Player) {
            return PlayerAPI.isBedrock((Player)sender);
        } else {
            return false;
        }
    }

    public static boolean isProtected(World Map, int y) {
        if(Map.getName().equals("Map01")) {
            return y >= 60;
        } else if(Map.getName().equals("Map02")) {
            return y >= 15;
        } else if(Map.getName().equals("Map03")) {
            return y >= 90;
        } else {
            return true;
        }
    }

    public static void doTimer(String command, int seconds, Player player) {
        int time = seconds*20;
        new BukkitRunnable() {
            public void run() {
                player.performCommand(command);
            }
        }.runTaskTimer(Main.instance, 0, time);
    }

    public static void doTimer(String command, int seconds) {
        int time = seconds*20;
        new BukkitRunnable() {
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command);
            }
        }.runTaskTimer(Main.instance, 0, time);
    }

    public static void goToSpawn(Player player) {
        player.teleport(MapChanger.getSpawn());
    }

    public static String getGameMode(Player p) {
        String GameMode;
        if (p.getGameMode().toString().equalsIgnoreCase("SURVIVAL")) {
            GameMode = "Überlebensmodus";
        } else if (p.getGameMode().toString().equalsIgnoreCase("CREATIVE")) {
            GameMode = "Kreativmodus";
        } else if (p.getGameMode().toString().equalsIgnoreCase("ADVENTURE")) {
            GameMode = "Abenteuermodus";
        } else if (p.getGameMode().toString().equalsIgnoreCase("SPECTATOR")) {
            GameMode = "Zuschauermodus";
        } else {
            GameMode = "null";
        }
        return GameMode;
    }

    public static boolean isStringBlocked(String DYM) {
        String string = DYM.toLowerCase();
        if(string.contains("nigga")) {
            return true;
        } else if(string.contains("niga")) {
            return true;
        } else if(string.contains("nega")) {
            if(string.contains("negativ")) {
                if(string.contains("ohne")) {
                    if(string.contains("tiv")) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else if(string.contains("neger")) {
            return true;
        } else if(string.contains("nigger")) {
            return true;
        } else if(string.contains("niger")) {
            return true;
        } else if(string.contains("nazi")) {
            return true;
        } else if(string.contains("hitler")) {
            return true;
        } else if(string.contains("hure")) {
            return true;
        } else if(string.contains("fotze")) {
            return true;
        } else if(string.contains("vergewalti")) {
            return true;
        } else if(string.contains("misgeburt")) {
            return true;
        } else if(string.contains("mistgeburt")) {
            return true;
        } else if(string.contains("missgeburt")) {
            return true;
        } else if(string.contains("misstgeburt")) {
            return true;
        } else if(string.contains("misset")) {
            return true;
        } else if(string.contains("miset")) {
            return true;
        } else if(string.contains("missed")) {
            return true;
        } else if(string.contains("mised")) {
            return true;
        } else if(string.contains("faggot")) {
            return true;
        } else if(string.contains("schwuchtel")) {
            return true;
        } else if(string.contains("spast")) {
            return true;
        } else if(string.contains("spasst")) {
            return true;
        } else if(string.contains("cancer")) {
            return true;
        } else if(string.contains("krebs")) {
            return true;
        } else if(string.contains("corona")) {
            return true;
        } else if(string.contains("corinski")) {
            return true;
        } else if(string.contains("atilla")) {
            return true;
        } else if(string.contains("hildmann")) {
            return true;
        } else if(string.contains("hildman")) {
            return true;
        } else if(string.contains("atila")) {
            return true;
        } else {
            return false;
        }
    }

    public static String getGamemode(final Player p) {
        String GameMode = "";
        if (p != null) {
            if (p.getGameMode().toString().equals("SURVIVAL")) {
                GameMode = "§aÜberlebensmodus";
            }
            else if (p.getGameMode().toString().equals("CREATIVE")) {
                GameMode = "§aKreativmodus";
            }
            else if (p.getGameMode().toString().equals("ADVENTURE")) {
                GameMode = "§aAbenteuermodus";
            }
            else if (p.getGameMode().toString().equals("SPECTATOR")) {
                GameMode = "§aZuschauermodus";
            }
            else {
                GameMode = "Nothing";
            }
        }
        else {
            GameMode = "Nothing";
        }
        return GameMode;
    }

    public static void changeGamemode(final Player p, final String GameMode) {
        if (GameMode.equalsIgnoreCase("0")) {
            p.setGameMode(org.bukkit.GameMode.SURVIVAL);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            p.sendMessage(Prefix + "Du bist nun im " + getGamemode(p) + "§7!");
        }
        else if (GameMode.equalsIgnoreCase("1")) {
            p.setGameMode(org.bukkit.GameMode.CREATIVE);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            p.sendMessage(Prefix + "Du bist nun im " + getGamemode(p) + "§7!");
        }
        else if (GameMode.equalsIgnoreCase("2")) {
            p.setGameMode(org.bukkit.GameMode.ADVENTURE);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            p.sendMessage(Prefix + "Du bist nun im " + getGamemode(p) + "§7!");
        }
        else if (GameMode.equalsIgnoreCase("3")) {
            p.setGameMode(org.bukkit.GameMode.SPECTATOR);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            p.sendMessage(Prefix+ "Du bist nun im " + getGamemode(p) + "§7!");
        }
        else if (GameMode.equalsIgnoreCase("SURVIVAL")) {
            p.setGameMode(org.bukkit.GameMode.SURVIVAL);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            p.sendMessage(Prefix + "Du bist nun im " + getGamemode(p) + "§7!");
        }
        else if (GameMode.equalsIgnoreCase("CREATIVE")) {
            p.setGameMode(org.bukkit.GameMode.CREATIVE);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            p.sendMessage(Prefix + "Du bist nun im " + getGamemode(p) + "§7!");
        }
        else if (GameMode.equalsIgnoreCase("ADVENTURE")) {
            p.setGameMode(org.bukkit.GameMode.ADVENTURE);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            p.sendMessage(Prefix + "Du bist nun im " + getGamemode(p) + "§7!");
        }
        else if (GameMode.equalsIgnoreCase("SPECTATOR")) {
            p.setGameMode(org.bukkit.GameMode.SPECTATOR);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            p.sendMessage(Prefix + "Du bist nun im " + getGamemode(p) + "§7!");
        }
    }

    public static void sendInit() {
        if(cfg.contains("Core.Init.Line01")&&cfg.contains("Core.Init.Line02")&&cfg.contains("Core.Init.Line03")&&cfg.contains("Core.Init.Line04")&&cfg.contains("Core.Init.Line05")&&cfg.contains("Core.Init.Line06")) {
            ConfigAPI.reloadConfig(Config, cfg);
            sendConsoleMessage(cfg.getString("Core.Init.Line01"));
            sendConsoleMessage(cfg.getString("Core.Init.Line02"));
            sendConsoleMessage(cfg.getString("Core.Init.Line03"));
            sendConsoleMessage(cfg.getString("Core.Init.Line04"));
            sendConsoleMessage(cfg.getString("Core.Init.Line05"));
            sendConsoleMessage(cfg.getString("Core.Init.Line06"));
            sendConsoleMessage("§fPlugin by §cnerotvlive§f!");
        } else {
            sendConsoleMessage("§9Lobbysystem init... §7couldn't find bigfont...");
            sendConsoleMessage("§9Lobbysystem init... §7couldn't find bigfont...");
            sendConsoleMessage("§9Lobbysystem init... §7couldn't find bigfont...");
            sendConsoleMessage("§9Lobbysystem init... §7couldn't find bigfont...");
            sendConsoleMessage("§9Lobbysystem init... §7couldn't find bigfont...");
            sendConsoleMessage("§9Lobbysystem init... §7couldn't find bigfont...");
            sendConsoleMessage("§fPlugin by §cnerotvlive§f!");
        }
    }

    public static int getPing(Player p) {
        String v = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        if (!p.getClass().getName().equals("org.bukkit.craftbukkit." + v + ".entity.CraftPlayer")) { //compatibility with some plugins
            p = Bukkit.getPlayer(p.getUniqueId()); //cast to org.bukkit.entity.Player
        }
        try {
            Class<?> CraftPlayerClass = Class.forName("org.bukkit.craftbukkit." + v + ".entity.CraftPlayer");
            Object CraftPlayer = CraftPlayerClass.cast((Player) p);
            Method getHandle = CraftPlayer.getClass().getMethod("getHandle");
            Object EntityPlayer = getHandle.invoke(CraftPlayer);
            Field ping = EntityPlayer.getClass().getDeclaredField("ping");
            return ping.getInt(EntityPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void sendCommandInit(String Command) {
        sendConsoleMessage("§0  §f-> §7Der Command \"§e"+Command+"§7\" wird geladen...");
    }

    public static void sendListenerInit(String Listener) {
        sendConsoleMessage("§0  §f-> §7Der Listener \"§e"+Listener+"§7\" wird geladen...");
    }

    public static void sendErrorMessage(CommandSender s,String error) {
        if(!(s instanceof Player)) {
            s.sendMessage(error.replace("&","§"));
        } else {
            Player p = (Player)s;
            p.sendMessage(error.replace("&","§"));
            p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
        }
    }

    public static boolean canPlayerBuild(Player p) {
        File BuildFile = new File("/plugins/temp/"+p.getUniqueId().toString()+".buildfile");
        if(BuildFile.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public static void setBuildMode(Player p,boolean state) {
        File BuildFile = new File("/plugins/temp/"+p.getUniqueId().toString()+".buildfile");
        YamlConfiguration BF = YamlConfiguration.loadConfiguration(BuildFile);
        if(state) {
            BF.set("canBuild",true);
            p.setGameMode(GameMode.CREATIVE);
            ConfigAPI.saveConfig(BuildFile,BF);
            p.getInventory().clear();
        } else {
            BuildFile.delete();
            p.setGameMode(GameMode.ADVENTURE);
            p.getInventory().clear();
            ItemManager.giveItems(p);
        }
    }

    public static void toggleBuildMode(Player p) {
        File BuildFile = new File("/plugins/temp/"+p.getUniqueId().toString()+".buildfile");
        YamlConfiguration BF = YamlConfiguration.loadConfiguration(BuildFile);
        if(BuildFile.exists()) {
            setBuildMode(p,false);
        } else {
            setBuildMode(p,true);
        }
    }

    public static void sendMessage(CommandSender s,String message) {
        if(!(s instanceof Player)) {
            s.sendMessage(Prefix+message.replace("&","§"));
        } else {
            Player p = (Player)s;
            p.sendMessage(Prefix+message.replace("&","§"));
            p.playSound(p.getLocation(),Sound.ENTITY_CHICKEN_EGG,100,100);
        }
    }

    public static void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(Prefix+message.replace("&","§"));
    }

    public static int getYearDay() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static void checkForRestart() {
        if(getYearDay()==RestartDay) {
            if(!isStopping) {
                ServerAPI.scheduledShutdown();
            }
        }
    }

    public static int animatedState;
    public static String animatedString() {
        int state = animatedState;
        String string = "§fKNOCKIT";
        if(state == 1) {
            string = "§9K§fNOCKIT";
        } else if(state == 2) {
            string = "§fK§9N§fOCKIT";
        } else if(state == 3) {
            string = "§fKN§9O§fCKIT";
        } else if(state == 4) {
            string = "§fKNO§9C§fKIT";
        } else if(state == 5) {
            string = "§fKNOC§9K§fIT";
        } else if(state == 6) {
            string = "§fKNOCK§9I§fT";
        } else if(state == 7) {
            string = "§fKNOCKI§9T";
        }
        return string;
    }
}
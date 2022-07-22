package live.nerotv.knockit;

import live.nerotv.knockit.api.API;
import live.nerotv.knockit.api.WorldAPI;
import live.nerotv.knockit.commands.*;
import live.nerotv.knockit.listener.*;
import live.nerotv.knockit.manager.BroadcastManager;
import live.nerotv.knockit.manager.MapChanger;
import live.nerotv.knockit.utils.Receiver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;
    public static Main get() { return instance; }
    public static PluginManager PM = Bukkit.getPluginManager();
    public static org.bukkit.scoreboard.Scoreboard Scoreboard;

    @Override
    public void onEnable() {
        instance = this;
        WorldAPI.createWorld("Map02");
        WorldAPI.createWorld("Map03");
        API.initConfig();
        API.sendInit();
        API.sendConsoleMessage(" ");
        API.sendConsoleMessage("Aktiviere das Plugin...");
        API.getTime();
        API.date = API.getTime();
        BroadcastManager.send();
        API.sendConsoleMessage(" ");
        API.sendConsoleMessage(" ");
        API.sendConsoleMessage("Spigot-Channel wird registriert und der Empfänger wird geladen...");
        getServer().getMessenger().registerIncomingPluginChannel(get(),"base:bungee",new Receiver());
        getServer().getMessenger().registerIncomingPluginChannel(get(),"labymod3:main",new Receiver());
        getServer().getMessenger().registerOutgoingPluginChannel(get(),"base:spigot");
        getServer().getMessenger().registerOutgoingPluginChannel(get(), "BungeeCord");
        API.sendConsoleMessage("Spigot-Channel wurde registriert und der Empfänger ist geladen!");
        Bukkit.getServer().getWorld("Map01").setAutoSave(false);
        Bukkit.getServer().getWorld("Map02").setAutoSave(false);
        Bukkit.getServer().getWorld("Map03").setAutoSave(false);
        API.sendConsoleMessage(" ");
        API.sendConsoleMessage(" ");
        initCommands();
        API.sendConsoleMessage(" ");
        API.doTimer("changemap",API.getTimer("Core.Settings.Mapchange.Timer"));
        API.sendConsoleMessage("Das Plugin wurde aktiviert!");
        API.sendConsoleMessage(" ");
        API.setTablist();
        API.sendInit();
    }

    private void initCommands() {
        API.sendConsoleMessage("Lade die Commands...");
        API.sendCommandInit("Build");
        getCommand("Build").setExecutor(new Build());
        API.sendCommandInit("Warp");
        getCommand("Warp").setExecutor(new Warp());
        API.sendCommandInit("World");
        getCommand("World").setExecutor(new World());
        API.sendCommandInit("GameMode");
        getCommand("GameMode").setExecutor(new GameMode());
        API.sendCommandInit("Ping");
        getCommand("Ping").setExecutor(new Ping());
        API.sendCommandInit("ChangeMap");
        getCommand("ChangeMap").setExecutor(new ChangeMap());
        API.sendCommandInit("Shop");
        getCommand("Shop").setExecutor(new Shop());
        API.sendCommandInit("Rank");
        getCommand("Rank").setExecutor(new Rank());
        API.sendCommandInit("Disconnect");
        getCommand("Disconnect").setExecutor(new Disconnect());
        initListener();
    }

    private void initListener() {
        API.sendConsoleMessage("Die Commands wurden geladen!");
        API.sendConsoleMessage(" ");
        API.sendConsoleMessage("Lade die Listener...");
        API.sendListenerInit("Chat");
        PM.registerEvents(new Chat(),this);
        API.sendListenerInit("CommandPreprocess");
        PM.registerEvents(new CommandPreProcess(),this);
        API.sendListenerInit("FallDamage");
        PM.registerEvents(new FallDamage(),this);
        API.sendListenerInit("InventoryClick");
        PM.registerEvents(new InventoryClick(),this);
        API.sendListenerInit("JoinLeave");
        PM.registerEvents(new JoinLeave(),this);
        API.sendListenerInit("PlayerInteract");
        PM.registerEvents(new PlayerInteract(),this);
        API.sendListenerInit("MapChanger");
        PM.registerEvents(new MapChanger(),this);
        API.sendListenerInit("Death");
        PM.registerEvents(new Death(),this);
        API.sendListenerInit("Level");
        PM.registerEvents(new Level(),this);
        API.sendConsoleMessage("Die Listener wurden geladen!");
        API.sendConsoleMessage(" ");
    }

    @Override
    public void onDisable() {
        API.sendInit();
        API.sendConsoleMessage(" ");
        API.sendConsoleMessage("Deaktiviere das Plugin...");
        for(Player all : Bukkit.getOnlinePlayers()) {
            API.switchServer(all,"Lobby-1");
        }
        getServer().getMessenger().registerIncomingPluginChannel(get(),"base:bungee",new Receiver());
        getServer().getMessenger().registerIncomingPluginChannel(get(),"labymod3:main",new Receiver());
        getServer().getMessenger().registerOutgoingPluginChannel(get(),"base:spigot");
        getServer().getMessenger().registerOutgoingPluginChannel(get(), "BungeeCord");
        API.sendConsoleMessage("Das Plugin wurde deaktiviert!");
        API.sendConsoleMessage(" ");
        API.sendInit();
        instance = null;
    }

    public static void setPrefix(Player player) {
        String Name = player.getName();
        org.bukkit.scoreboard.Scoreboard Scoreboard = player.getScoreboard();
        if(Scoreboard.getTeam("03Spieler")==null) {
            Scoreboard.registerNewTeam("00000Team");
            Scoreboard.registerNewTeam("01Creator");
            Scoreboard.registerNewTeam("02Premium");
            Scoreboard.registerNewTeam("03Spieler");
            Scoreboard.getTeam("00000Team").setPrefix("§cTeam §8● §f");
            Scoreboard.getTeam("01Creator").setPrefix("§dCreator §8● §f");
            Scoreboard.getTeam("02Premium").setPrefix("§6Premium §8● §f");
            Scoreboard.getTeam("03Spieler").setPrefix("§7User §8● §f");
            Scoreboard.getTeam("00000Team").setCanSeeFriendlyInvisibles(false);
            Scoreboard.getTeam("01Creator").setCanSeeFriendlyInvisibles(false);
            Scoreboard.getTeam("02Premium").setCanSeeFriendlyInvisibles(false);
            Scoreboard.getTeam("03Spieler").setCanSeeFriendlyInvisibles(false);
            Scoreboard.getTeam("00000Team").setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
            Scoreboard.getTeam("01Creator").setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
            Scoreboard.getTeam("02Premium").setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
            Scoreboard.getTeam("03Spieler").setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
        }
        for(Player p:Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("zyneon.team")) {
                Scoreboard.getTeam("00000Team").addPlayer(p);
                p.setDisplayName(Scoreboard.getTeam("00000Team").getPrefix() + Name);
            } else if (p.hasPermission("zyneon.creator")) {
                Scoreboard.getTeam("01Creator").addPlayer(p);
                p.setDisplayName(Scoreboard.getTeam("01Creator").getPrefix() + Name);
            } else if (p.hasPermission("zyneon.premium")) {
                Scoreboard.getTeam("02Premium").addPlayer(p);
                p.setDisplayName(Scoreboard.getTeam("02Premium").getPrefix() + Name);
            } else {
                Scoreboard.getTeam("03Spieler").addPlayer(p);
                p.setDisplayName(Scoreboard.getTeam("03Spieler").getPrefix() + Name);
            }
        }
    }
}
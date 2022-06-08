package live.nerotv.knockit.manager;

import live.nerotv.knockit.Main;
import live.nerotv.knockit.api.API;
import live.nerotv.knockit.api.ConfigAPI;
import live.nerotv.knockit.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static org.bukkit.Bukkit.getServer;

public class BroadcastManager {

    private static Main MAIN;
    public BroadcastManager(final Main main) {
        this.MAIN = main;
    }

    static File Config = ConfigAPI.Config;
    static YamlConfiguration cfg = ConfigAPI.CFG;
    static ArrayList<String> Messages = new ArrayList<>();

    private static void saveDefaultConfig() {
        ConfigAPI.checkEntry("Core.Settings.Broadcasts.Enable",false,Config,cfg);
        ConfigAPI.checkEntry("Core.Settings.Broadcasts.SecondInterval",10,Config,cfg);
        ConfigAPI.checkEntry("Core.Strings.Broadcasts",Messages,Config,cfg);
        Messages = (ArrayList<String>)cfg.getList("Core.Strings.Broadcasts");
        ConfigAPI.checkEntry("Core.Actionbar.Message","test",Config,cfg);
        ConfigAPI.saveConfig(Config,cfg);
        ConfigAPI.reloadConfig(Config,cfg);
    }

    public static void send() {
        saveDefaultConfig();
        sendScoreboard(getServer().getScheduler());
        autoRenew(getServer().getScheduler());
        if(cfg.getBoolean("Core.Settings.Broadcasts.Enable")) {
            startBroadcastTimer(getServer().getScheduler());
        }
    }

    private static void startBroadcastTimer(BukkitScheduler scheduler) {
        int scheduleId = scheduler.scheduleSyncDelayedTask(Main.instance, () -> {
            Integer size = Messages.size();
            Integer random = ThreadLocalRandom.current().nextInt(0,size);
            Bukkit.broadcastMessage(API.Prefix+Messages.get(random).replace("&","§"));
            startBroadcastTimer(scheduler);
        }, cfg.getLong("Core.Settings.Broadcasts.SecondInterval")*20);
    }

    private static void sendScoreboard(BukkitScheduler scheduler) {
        int scheduleId = scheduler.scheduleSyncDelayedTask(Main.instance, () -> {
            for(Player all : Bukkit.getOnlinePlayers()) {
                PlayerAPI.setScoreboard(all);
            }
            if(API.animatedState == 7) {
                API.animatedState = 0;
            } else if(API.animatedState == 0) {
                API.animatedState = 1;
            } else if(API.animatedState == 1) {
                API.animatedState = 2;
            } else if(API.animatedState == 2) {
                API.animatedState = 3;
            } else if(API.animatedState == 3) {
                API.animatedState = 4;
            } else if(API.animatedState == 4) {
                API.animatedState = 5;
            } else if(API.animatedState == 5) {
                API.animatedState = 6;
            } else if(API.animatedState == 6) {
                API.animatedState = 7;
            }
            sendScoreboard(scheduler);
        },15);
    }

    private static void autoRenew(BukkitScheduler scheduler) {
        int scheduleId = scheduler.scheduleSyncDelayedTask(Main.instance, () -> {
            if(!API.date.equals(API.getTime())) {
                API.date = API.getTime();
                for (Player all : Bukkit.getOnlinePlayers()) {
                    PlayerAPI.renewScoreboard(all);
                }
            }
            API.checkForRestart();
            autoRenew(scheduler);
        },15*20);
    }
}
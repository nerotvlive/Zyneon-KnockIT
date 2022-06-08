package live.nerotv.knockit.api;

import live.nerotv.knockit.Main;
import live.nerotv.knockit.utils.Countdown;
import live.nerotv.knockit.utils.MessageResolver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerAPI {

    @Deprecated
    public static void secureShutdown(String shutdownMessage) {
        for(Player all: Bukkit.getOnlinePlayers()) {
            all.kickPlayer(shutdownMessage);
        }
        Bukkit.shutdown();
    }

    @Deprecated
    public static void secureShutdown() {
        secureShutdown("§cServerneustart...");
    }

    public static boolean scheduledShutdown() {
        if(!API.isStopping) {
            API.isStopping = true;
            new Countdown(27, Main.instance) {
                @Override
                public void count(int current) {
                    if (current < 11) {
                        int cur = current - 1;
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title "+all.getName()+" title \""+ MessageResolver.getMessage(MessageResolver.Message.restart_TITLE,PlayerAPI.getLanguage(all)).replace("%seconds%",cur+"")+"\"");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title "+all.getName()+" subtitle \""+MessageResolver.getMessage(MessageResolver.Message.restart_SUBTITLE,PlayerAPI.getLanguage(all)).replace("%seconds%",cur+"")+"\"");
                            PlayerAPI.playNewSound(all, NewSound.BLOCK_NOTE_BLOCK_PLING);
                        }
                        if (current <= 6) {
                            for(Player all:Bukkit.getOnlinePlayers()) {
                                all.sendMessage(MessageResolver.getMessage(MessageResolver.Message.restart_MESSAGE,PlayerAPI.getLanguage(all)).replace("%seconds%",cur+""));
                            }
                        }
                        if (current == 1) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.kickPlayer("§cDer Lobby-Server startet neu!");
                            }
                        } else if (current == 0) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.kickPlayer("§cDer Lobby-Server startet neu!");
                            }
                            Bukkit.shutdown();
                        }
                    } else {
                        if (current == 26) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                PlayerAPI.playNewSound(all, NewSound.BLOCK_NOTE_BLOCK_PLING);
                                all.sendMessage(MessageResolver.getMessage(MessageResolver.Message.restart_MESSAGE,PlayerAPI.getLanguage(all)).replace("%seconds%","25"));
                            }
                        } else if (current == 21) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                PlayerAPI.playNewSound(all, NewSound.BLOCK_NOTE_BLOCK_PLING);
                                all.sendMessage(MessageResolver.getMessage(MessageResolver.Message.restart_MESSAGE,PlayerAPI.getLanguage(all)).replace("%seconds%","20"));
                            }
                        } else if (current == 16) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                PlayerAPI.playNewSound(all, NewSound.BLOCK_NOTE_BLOCK_PLING);
                                all.sendMessage(MessageResolver.getMessage(MessageResolver.Message.restart_MESSAGE,PlayerAPI.getLanguage(all)).replace("%seconds%","15"));
                            }
                        } else if (current == 11) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title "+all.getName()+" title \""+MessageResolver.getMessage(MessageResolver.Message.restart_TITLE,PlayerAPI.getLanguage(all)).replace("%seconds%","10")+"\"");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title "+all.getName()+" subtitle \""+MessageResolver.getMessage(MessageResolver.Message.restart_SUBTITLE,PlayerAPI.getLanguage(all)).replace("%seconds%","10")+"\"");
                                PlayerAPI.playNewSound(all, NewSound.BLOCK_NOTE_BLOCK_PLING);
                                all.sendMessage(MessageResolver.getMessage(MessageResolver.Message.restart_MESSAGE,PlayerAPI.getLanguage(all)).replace("%seconds%","10"));
                            }
                        }
                    }
                }
            }.start();
            return true;
        } else {
            return false;
        }
    }

    public static String formatMessage(String message) {
        return message.replace("&","§");
    }

    public static int getServerVersion() {
        if(Bukkit.getVersion().contains("1.0")) {
            return 100;
        } else if(Bukkit.getVersion().contains("1.1.")) {
            return 101;
        } else if(Bukkit.getVersion().contains("1.2.")) {
            return 102;
        } else if(Bukkit.getVersion().contains("1.3.")) {
            return 103;
        } else if(Bukkit.getVersion().contains("1.4.")) {
            return 104;
        } else if(Bukkit.getVersion().contains("1.5.")) {
            return 105;
        } else if(Bukkit.getVersion().contains("1.6.")) {
            return 106;
        } else if(Bukkit.getVersion().contains("1.7.")) {
            return 107;
        } else if(Bukkit.getVersion().contains("1.8")) {
            return 108;
        } else if(Bukkit.getVersion().contains("1.9")) {
            return 109;
        } else if(Bukkit.getVersion().contains("1.10")) {
            return 110;
        } else if(Bukkit.getVersion().contains("1.11")) {
            return 111;
        } else if(Bukkit.getVersion().contains("1.12")) {
            return 112;
        } else if(Bukkit.getVersion().contains("1.13")) {
            return 113;
        } else if(Bukkit.getVersion().contains("1.14")) {
            return 114;
        } else if(Bukkit.getVersion().contains("1.15")) {
            return 115;
        } else if(Bukkit.getVersion().contains("1.16")) {
            return 116;
        } else {
            return 117;
        }
    }

    public static Boolean isLegacy() {
        if(getServerVersion() == 113) {
            return false;
        } else if(getServerVersion() == 114) {
            return false;
        } else if(getServerVersion() == 115) {
            return false;
        } else if(getServerVersion() == 116) {
            return false;
        } else if(getServerVersion() > 116) {
            return false;
        } else {
            return true;
        }
    }

    public static void setMaintenance(boolean state) {
        ConfigAPI.CFG.set("Core.Settings.Maintenance",state);
        ConfigAPI.saveConfig(ConfigAPI.Config,ConfigAPI.CFG);
        ConfigAPI.reloadConfig(ConfigAPI.Config,ConfigAPI.CFG);
    }

    public static void toggleMaintenance() {
        if(isMaintenance()) {
            setMaintenance(false);
        } else {
            setMaintenance(true);
        }
    }

    public static boolean isMaintenance() {
        return ConfigAPI.CFG.getBoolean("Core.Settings.Maintenance");
    }
}
package live.nerotv.knockit.utils;

import static live.nerotv.knockit.utils.MessageResolver.Language.GERMAN;
import static live.nerotv.knockit.utils.MessageResolver.Message.*;

public class MessageResolver {

    public static String getMessage(Message message,Language language) {
        String Return;

        if (message.equals(NoPerms)) {
            if (language.equals(GERMAN)) {
                Return = "Dir ist nicht erlaubt, das zu tun§8!";
            } else {
                Return = "§cYou aren't allowed to do that§8!";
            }

        } else if (message.equals(NoPlayer)) {
            if (language.equals(GERMAN)) {
                Return = "§cSpieler nicht gefunden§8!";
            } else {
                Return = "§cPlayer not found§8!";
            }

        } else if (message.equals(NeedPlayer)) {
            if (language.equals(GERMAN)) {
                Return = "§cDazu musst du ein Spieler sein§8!";
            } else {
                Return = "§cYou need to be a player to do that§8!";
            }

        } else if (message.equals(ChangedGamemode)) {
            if (language.equals(GERMAN)) {
                Return = "§7Dein Spielmodus wurde auf §e%gamemode%§7 gesetzt§8!";
            } else {
                Return = "§7Your gamemode has been changed to §e%gamemode%§8!";
            }

        } else if (message.equals(Gamemode_SURVIVAL)) {
            if (language.equals(GERMAN)) {
                Return = "Überleben";
            } else {
                Return = "survival mode";
            }

        } else if (message.equals(Gamemode_CREATIVE)) {
            if (language.equals(GERMAN)) {
                Return = "Kreativ";
            } else {
                Return = "creative mode";
            }

        } else if (message.equals(Gamemode_ADVENTURE)) {
            if (language.equals(GERMAN)) {
                Return = "Abenteuer";
            } else {
                Return = "adventure mode";
            }

        } else if (message.equals(Gamemode_SPECTATOR)) {
            if (language.equals(GERMAN)) {
                Return = "Zuschauer";
            } else {
                Return = "spectator mode";
            }

        } else if (message.equals(ChangedAnotherGamemode)) {
            if (language.equals(GERMAN)) {
                Return = "§7Du hast den Spielmodus von §e%player%§7 auf §a%gamemode%§7 gesetzt§8!";
            } else {
                Return = "§7You've changed §e%player%'s§7 gamemode to §a%gamemode%§8!";
            }

        } else if (message.equals(Syntax_GAMEMODE)) {
            if (language.equals(GERMAN)) {
                Return = "§4Syntax: §c/gamemode [0-3] §7[Spieler]";
            } else {
                Return = "§4Syntax: §c/gamemode [0-3] §7[Player]";
            }

        } else if (message.equals(Message.Serverswitch_ERROR)) {
            if (language.equals(GERMAN)) {
                Return = "§cEs ist ein Fehler beim Senden des Spielers aufgetreten§8. §cZiel§8: §c%server%";
            } else {
                Return = "§cThere was an error sending the player to \"%server%\"§8!";
            }

        } else if (message.equals(Message.init_COMMAND)) {
            if (language.equals(GERMAN)) {
                Return = "§f  -> §7lade Kommando §e\"%command%§e\"§7...";
            } else {
                Return = "§f  -> §7loading command §e\"%command%§e\"§7...";
            }

        } else if (message.equals(init_LISTENER)) {
            if (language.equals(GERMAN)) {
                Return = "§f  -> §7lade Listener §e\"%listener%§e\"§7...";
            } else {
                Return = "§f  -> §7loading listener §e\"%listener%§e\"§7...";
            }

        } else if (message.equals(BedrockUser)) {
            if (language.equals(GERMAN)) {
                Return = "§e%player%§7 ist ein Bedrock-User§8!";
            } else {
                Return = "§e%player%§7 is a Bedrock-user§8!";
            }

        } else if (message.equals(restart_TITLE)) {
            if (language.equals(GERMAN)) {
                Return = "§fIn §e%seconds%§f Sekunden§8...";
            } else {
                Return = "§fThe server is restarting§8...";
            }

        } else if (message.equals(restart_SUBTITLE)) {
            if (language.equals(GERMAN)) {
                Return = "§8...§7startet der Server neu§8!";
            } else {
                Return = "§8...§7in §e%seconds% §7seconds§8!";
            }

        } else if (message.equals(restart_MESSAGE)) {
            if (language.equals(GERMAN)) {
                Return = "§cWICHTIG§8: §7Serverneustart in §e%seconds% Sekunden§8!";
            } else {
                Return = "§cWARNING§8: §7Restart in §e%seconds% seconds§8!";
            }

        } else if (message.equals(restart_START)) {
            if (language.equals(GERMAN)) {
                Return = "§7Du hast den §eStopvorgang§7 gestartet§8.";
            } else {
                Return = "§7You started the §estop process§7§8.";
            }

        } else if (message.equals(restart_ERROR)) {
            if (language.equals(GERMAN)) {
                Return = "§cDer Server fährt bereits herunter§8!";
            } else {
                Return = "§cThe server is already shutting down§8!";
            }

        } else {
            if (language.equals(GERMAN)) {
                Return = "§cNachricht nicht gefunden§8!";
            } else {
                Return = "§cMessage not found§8!";
            }
        }
        return Return;
    }

    public enum Message {
        NoPerms,
        NoPlayer,
        NeedPlayer,
        BedrockUser,
        ChangedGamemode,
        ChangedAnotherGamemode,
        Syntax_GAMEMODE,
        Gamemode_SURVIVAL,
        Gamemode_CREATIVE,
        Gamemode_ADVENTURE,
        Gamemode_SPECTATOR,
        Serverswitch_ERROR,
        init_COMMAND,
        init_LISTENER,
        restart_TITLE,
        restart_SUBTITLE,
        restart_MESSAGE,
        restart_START,
        restart_ERROR
    }

    public enum Language {
        AUTOMATIC,
        ENGLISH,
        GERMAN
    }
}
package live.nerotv.knockit.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import live.nerotv.knockit.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Communicate {

    public static void sendToBungee(Player player, String data1, int data2) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("bungee");
        out.writeUTF(data1);
        out.writeInt(data2);
        Bukkit.getServer().sendPluginMessage(Main.get(),"base:spigot",out.toByteArray());
    }

    public static void sendToServer(Player player,String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("server_"+server);
        out.writeUTF(player.getName());
        out.write(1);
        Bukkit.getServer().sendPluginMessage(Main.get(),"base:spigot",out.toByteArray());
    }
}
package live.nerotv.knockit.manager.bedrock;

import live.nerotv.knockit.api.API;
import live.nerotv.knockit.api.PlayerAPI;
import live.nerotv.knockit.manager.GUIManager;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.component.ButtonComponent;
import org.geysermc.cumulus.response.FormResponse;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;

public class FormManager {

    public static void openActionMenu(Player p) {
        if(PlayerAPI.isBedrock(p)) {
            FloodgatePlayer bP = FloodgateApi.getInstance().getPlayer(p.getUniqueId());
            bP.sendForm(SimpleForm.builder().title("§9Aktionsmenü").content("§7Wähle aus, was du tuns willst:").button("§9Shop öffnen").button("§8Zurück in die Lobby").button("§8Schließen").responseHandler((form,responseData)->{
                FormResponse response = form.parseResponse(responseData);
                if (response.isCorrect() && !response.isClosed() && !response.isInvalid() || form.parseResponse(responseData).getClickedButton() != null) {
                    ButtonComponent button = form.parseResponse(responseData).getClickedButton();
                    String buttonText = button.getText();
                    if(buttonText.contains("Shop")) {
                        GUIManager.openChestShopInventory(p);
                    } else if(buttonText.contains("Lobby")) {
                        API.switchServer(p,"Lobby-1");
                    }
                }
            }).build());
        }
    }
}
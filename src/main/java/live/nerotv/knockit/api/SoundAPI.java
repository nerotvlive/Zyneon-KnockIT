package live.nerotv.knockit.api;

import org.bukkit.Sound;

public class SoundAPI {

    public static Sound resolveSound(String soundname) {
        if(ServerAPI.isLegacy()) {
            return Sound.valueOf(SaveSound.valueOf(soundname).toString().toUpperCase());
        } else {
            return Sound.valueOf(NewSound.valueOf(soundname).toString().toUpperCase());
        }
    }

    public static Sound getLegacySound(SaveSound legacySound) {
        return Sound.valueOf(legacySound.toString());
    }


    public static Sound getNewSound(NewSound NewSound) {
        return Sound.valueOf(NewSound.toString());
    }

    //UNFINISHED
    public static Sound resolveSaveSound(SaveSound saveSound) {
        if(saveSound.toString().equals("AMBIENCE_CAVE")) {
            if (!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.AMBIENT_CAVE);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("AMBIENCE_RAIN")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.WEATHER_RAIN);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("AMBIENCE_THUNDER")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_LIGHTNING_BOLT_THUNDER);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("ANVIL_BREAK")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.BLOCK_ANVIL_BREAK);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("ANVIL_LAND")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.BLOCK_ANVIL_LAND);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("ANVIL_USE")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.BLOCK_ANVIL_USE);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("ARROW_HIT")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_ARROW_HIT);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("BAT_DEATH")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_BAT_DEATH);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("BAT_HURT")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_BAT_HURT);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("BAT_IDLE")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_BAT_AMBIENT);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("BAT_LOOP")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_BAT_LOOP);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("BAT_TAKEOFF")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_BAT_TAKEOFF);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("BLAZE_BREATH")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_BLAZE_AMBIENT);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("BLAZE_DEATH")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_BLAZE_DEATH);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("BLAZE_HIT")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_BLAZE_HURT);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("BURP")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_PLAYER_BURP);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("CAT_HISS")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_CAT_HISS);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("CAT_HIT")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_CAT_HURT);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("CAT_MEOW")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_CAT_AMBIENT);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("CAT_PURR")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_CAT_PURR);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else if(saveSound.toString().equals("CAT_PURREOW")) {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.ENTITY_CAT_PURREOW);
            } else {
                return Sound.valueOf(saveSound.toString());
            }
        } else {
            if(!(ServerAPI.isLegacy())) {
                return getNewSound(NewSound.WEATHER_RAIN_ABOVE);
            } else {
                return Sound.valueOf("ZOMBIE_WOODBREAK");
            }
        }
    }
}
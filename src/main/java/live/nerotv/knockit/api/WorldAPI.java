package live.nerotv.knockit.api;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public class WorldAPI {

    public static World getPlayerWorld(Player player) {
        return player.getWorld();
    }

    @Deprecated
    public static String getPlayerWorldName(Player player) {
        return getPlayerWorld(player).getName();
    }

    public static World getWorld(String worldname) {
        if(Bukkit.getWorld(worldname) == null) { return null; } else {
            return Bukkit.getWorld(worldname);
        }
    }

    public static Difficulty getDifficulty(String worldname) {
        if(Bukkit.getWorld(worldname) != null) {
            return Bukkit.getWorld(worldname).getDifficulty();
        } else {
            return null;
        }
    }

    @Deprecated
    public static long getTime(String worldname) {
        if(Bukkit.getWorld(worldname) != null) {
            return Bukkit.getWorld(worldname).getTime();
        } else {
            return 0;
        }
    }

    public static Weather getWeather(World world) {
        if(world.isThundering()) {
            return Weather.THUNDER;
        } else if(world.hasStorm()) {
            return Weather.RAIN;
        } else {
            return Weather.SUN;
        }
    }

    @Deprecated
    public static Weather getWeather(String worldname) {
        if(Bukkit.getWorld(worldname) != null) {
            World world = Bukkit.getWorld(worldname);
            if(world.isThundering()) {
                return Weather.THUNDER;
            } else if(world.hasStorm()) {
                return Weather.RAIN;
            } else {
                return Weather.SUN;
            }
        } else {
            return null;
        }
    }

    public static void setTime(long time, World world) {
        world.setTime(time);
    }

    @Deprecated
    public static void setTime(long time, String worldname) {
        if(Bukkit.getWorld(worldname) != null) {
            World world = Bukkit.getWorld(worldname);
            world.setTime(time);
        }
    }

    public static void addTime(long time, World world) {
        long t = world.getTime()+time;
        world.setTime(t);
    }

    @Deprecated
    public static void addTime(long time, String worldname) {
        if(Bukkit.getWorld(worldname) != null) {
            World world = Bukkit.getWorld(worldname);
            long t = world.getTime()+time;
            world.setTime(t);
        }
    }

    public static void removeTime(long time, World world) {
        long t = world.getTime()-time;
        world.setTime(t);
    }

    @Deprecated
    public static void removeTime(long time, String worldname) {
        if(Bukkit.getWorld(worldname) != null) {
            World world = Bukkit.getWorld(worldname);
            long t = world.getTime()-time;
            world.setTime(t);
        }
    }

    public static void setSun(World world) {
        world.setThundering(false);
        world.setStorm(false);
    }

    @Deprecated
    public static void setSun(String worldname) {
        if(Bukkit.getWorld(worldname) != null) {
            World world = Bukkit.getWorld(worldname);
            world.setThundering(false);
            world.setStorm(true);
        }
    }

    public static void setRain(World world) {
        world.setThundering(false);
        world.setStorm(true);
    }

    @Deprecated
    public static void setRain(String worldname) {
        if(Bukkit.getWorld(worldname) != null) {
            World world = Bukkit.getWorld(worldname);
            world.setThundering(false);
            world.setStorm(true);
        }
    }

    public static void setStorm(World world) {
        world.setStorm(true);
        world.setThundering(true);
    }

    @Deprecated
    public static void setStorm(String worldname) {
        if(Bukkit.getWorld(worldname) != null) {
            World world = Bukkit.getWorld(worldname);
            world.setStorm(true);
            world.setThundering(true);
        }
    }

    public static void setDifficulty(World world, Difficulty difficulty) {
        world.setDifficulty(difficulty);
    }

    @Deprecated
    public static void setDifficulty(World world, String difficultyname) {
        Difficulty diff = resolveDifficulty(difficultyname);
        world.setDifficulty(diff);
    }

    @Deprecated
    public static void setDifficulty(String worldname, Difficulty difficulty) {
        World world = Bukkit.getServer().getWorld(worldname);
        if(world != null)  {
            world.setDifficulty(difficulty);
        }
    }

    @Deprecated
    public static void setDifficulty(String worldname, String difficultyname) {
        Difficulty diff = resolveDifficulty(difficultyname);
        World world = Bukkit.getServer().getWorld(worldname);
        if(world != null)  {
            world.setDifficulty(diff);
        }
    }

    public static void setWeather(World world,Weather weather) {
        if(weather.equals(Weather.RAIN)) {
            world.setThundering(false);
            world.setStorm(true);
        } else if(weather.equals(Weather.THUNDER)) {
            world.setStorm(true);
            world.setThundering(true);
        } else {
            world.setThundering(false);
            world.setStorm(false);
        }
    }

    public static Difficulty resolveDifficulty(String difficulty) {
        if(difficulty.equalsIgnoreCase("peaceful")) {
            return Difficulty.PEACEFUL;
        } else if(difficulty.equalsIgnoreCase("0")) {
            return Difficulty.PEACEFUL;
        } else if(difficulty.equalsIgnoreCase("easy")) {
            return Difficulty.EASY;
        } else if(difficulty.equalsIgnoreCase("1")) {
            return Difficulty.EASY;
        } else if(difficulty.equalsIgnoreCase("normal")) {
            return Difficulty.NORMAL;
        } else if(difficulty.equalsIgnoreCase("2")) {
            return Difficulty.NORMAL;
        } else if(difficulty.equalsIgnoreCase("hard")) {
            return Difficulty.HARD;
        } else if(difficulty.equalsIgnoreCase("3")) {
            return Difficulty.HARD;
        } else {
            return null;
        }
    }

    public static void createWorld(String MapName) {
        new WorldCreator(MapName).environment(World.Environment.NORMAL).createWorld();
    }
}
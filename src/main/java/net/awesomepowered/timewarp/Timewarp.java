package net.awesomepowered.timewarp;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public final class Timewarp extends JavaPlugin {

    private Map<World, Gravity> timewarps = new HashMap<>();
    public static Timewarp space;

    @Override
    public void onEnable() {
        space = this;
        getCommand("timewarp").setExecutor(new CommandTimeWarp(this));
        saveDefaultConfig();
        new BukkitRunnable() {
            @Override
            public void run() {
                unPauseTime();
            }
        }.runTaskLater(this, 100); //Make sure all worlds are loaded.
    }

    @Override
    public void onDisable() {
        pauseTime();
    }

    public Map<World, Gravity> getMultiverse() {
        return timewarps;
    }

    public void unPauseTime() {
        if (getConfig().getConfigurationSection("worlds") == null) {
            return;
        }

        for (String world : getConfig().getConfigurationSection("worlds").getKeys(false)) {
            World w = Bukkit.getWorld(world);
            if (w != null) {
                Gravity gravity = new Gravity(w, getConfig().getInt("worlds."+world+".dilation", 20));
                gravity.warpSpacetime();
                timewarps.put(w, gravity);
            }
        }
    }

    public void pauseTime() {
        getConfig().set("worlds", null);
        for (Gravity grav : timewarps.values()) {
            getConfig().set("worlds."+grav.getWorld().getName()+".dilation", grav.getDilation());
        }
        saveConfig();
    }

}

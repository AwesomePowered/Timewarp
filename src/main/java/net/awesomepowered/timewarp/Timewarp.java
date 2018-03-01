package net.awesomepowered.timewarp;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Timewarp extends JavaPlugin {

    private Map<World, Gravity> timewarps = new HashMap<>();
    public static Timewarp space;

    @Override
    public void onEnable() {
        space = this;
        getCommand("timewarp").setExecutor(new CommandTimeWarp(this));
    }

    public Map<World, Gravity> getMultiverse() {
        return timewarps;
    }

}

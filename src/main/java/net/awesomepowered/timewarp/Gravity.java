package net.awesomepowered.timewarp;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class Gravity {

    private int taskId;
    private int dilation;
    private World world;

    public Gravity(World world, int dilation) {
        this.world = world;
        this.dilation = dilation;
    }


    public int getDilation() {
        return dilation;
    }

    public void setDilation(int dilation) {
        this.dilation = dilation;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void changeMode() {
        setDilation(getDilation() * -1);
    }

    public void warpSpacetime() {
        setTaskId(Bukkit.getScheduler().scheduleSyncRepeatingTask(Timewarp.space, () -> {
            world.setTime(world.getTime()+dilation);
        }, 0, 1));
    }
}

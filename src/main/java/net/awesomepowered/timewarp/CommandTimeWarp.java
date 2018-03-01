package net.awesomepowered.timewarp;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTimeWarp implements CommandExecutor {

    public Timewarp timewarp;

    public CommandTimeWarp(Timewarp timewarp) {
        this.timewarp = timewarp;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args.length >= 1 && sender.hasPermission("timelord.timewarp")) {
            if (StringUtils.isNumeric(args[0])) {
                if (timewarp.getMultiverse().containsKey(((Player) sender).getWorld()))  {
                    timewarp.getMultiverse().get(((Player) sender).getWorld()).setDilation(Integer.valueOf(args[0]));
                } else {
                    Gravity gravity = new Gravity(((Player) sender).getWorld(), Integer.valueOf(args[0]));
                    gravity.warpSpacetime();
                    timewarp.getMultiverse().put(((Player) sender).getWorld(), gravity);
                }
            }
            if (args[0].equalsIgnoreCase("mode")) {
                if (timewarp.getMultiverse().containsKey(((Player) sender).getWorld())) {
                    timewarp.getMultiverse().get(((Player) sender).getWorld()).changeMode();
                }
            }

        }
        return false;
    }
}

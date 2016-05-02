package me.eccentric_nz.worldresourcepacks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WorldResourcePacksCommand implements CommandExecutor {

    private final WorldResourcePacks plugin;

    public WorldResourcePacksCommand(WorldResourcePacks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("worldresourcepacks")) {
            // do stuff
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                return true;
            }
            return true;
        }
        return false;
    }
}

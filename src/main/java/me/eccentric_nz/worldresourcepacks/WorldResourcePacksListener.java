package me.eccentric_nz.worldresourcepacks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class WorldResourcePacksListener implements Listener {

    private final WorldResourcePacks plugin;

    public WorldResourcePacksListener(WorldResourcePacks plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldChange(PlayerChangedWorldEvent event) {
        final Player player = event.getPlayer();
        String world = player.getWorld().getName();
        setResourcePack(player, world);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("set_pack_on_join")) {
            final Player player = event.getPlayer();
            String world = player.getWorld().getName();
            setResourcePack(player, world);
        }
    }

    private void setResourcePack(Player player, String world) {
        if (player.isOnline()) {
            String path = plugin.getConfig().getString("worlds." + world);
            if (path == null || path.equalsIgnoreCase("default")) {
                path = plugin.getConfig().getString("default");
            }
            player.setResourcePack(path);
        }
    }
}

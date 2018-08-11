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
        Player player = event.getPlayer();
        String world = player.getWorld().getName();
        String from = plugin.getConfig().getString("worlds." + event.getFrom().getName());
        if (from == null) {
            from = "default";
        }
        String path = plugin.getConfig().getString("worlds." + world);
        if (path == null) {
            path = "default";
        }
        // check to see whether the resource pack URL is actually different
        if (!from.equals(path)) {
            setResourcePack(player, path);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("set_pack_on_join")) {
            Player player = event.getPlayer();
            String world = player.getWorld().getName();
            String path = plugin.getConfig().getString("worlds." + world);
            setResourcePack(player, path);
        }
    }

    private void setResourcePack(Player player, String path) {
        if (path == null || path.equalsIgnoreCase("default")) {
            path = plugin.getConfig().getString("default");
        }
        if (player.isOnline()) {
            player.setResourcePack(path);
        }
    }
}

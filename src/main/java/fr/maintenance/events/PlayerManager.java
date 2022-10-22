package fr.maintenance.events;

import fr.maintenance.Main;
import fr.maintenance.utils.Manager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerManager implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (Manager.statut && !Manager.players.contains(event.getPlayer().getName())) {
            event.getPlayer().kickPlayer(Main.instance.getConfig().getString("server_name").replace('&', '§') + "__§c         Une maintenance est actuellement en cours...__§9Merci de bien vouloir revenir plus tard !".replace("_", "\n"));
            event.setJoinMessage(null);
        }
        event.setJoinMessage("§8[§a+§8] §e" + event.getPlayer().getName());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (Manager.statut && !Manager.players.contains(event.getPlayer().getName())) {
            event.getPlayer().kickPlayer(Main.instance.getConfig().getString("server_name").replace('&', '§') + "__§c         Une maintenance est actuellement en cours...__§9Merci de bien vouloir revenir plus tard !".replace("_", "\n"));
            event.setQuitMessage(null);
            return;
        }
        event.setQuitMessage("§8[§c-§8] §e" + event.getPlayer().getName());
    }
}

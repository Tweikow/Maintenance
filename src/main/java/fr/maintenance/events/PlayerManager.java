package fr.maintenance.events;

import fr.maintenance.Main;
import fr.maintenance.utils.Manager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerManager implements Listener {

    @EventHandler
    public void onJoin(PlayerLoginEvent event) {
        if (Manager.statut && !Manager.players.contains(event.getPlayer().getName()))
            event.disallow(PlayerLoginEvent.Result.KICK_FULL, Main.instance.getConfig().getString("server_name").replace('&', '§') + "__§c         Une maintenance est actuellement en cours...__§9Merci de bien vouloir revenir plus tard !".replace("_", "\n"));
    }
}

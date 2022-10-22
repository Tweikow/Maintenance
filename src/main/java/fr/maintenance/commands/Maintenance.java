package fr.maintenance.commands;

import fr.maintenance.Main;
import fr.maintenance.utils.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Maintenance implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!sender.hasPermission("maintenance.use")) {
            sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission requise !");
            return false;
        }

        if (args.length == 1) {
            // TODO /maintenance on
            // TODO /maintenance off
            // TODO /maintenance list
            if (args[0].equalsIgnoreCase("on")) {
                if (!Manager.statut) {
                    Manager.setStatut(true);
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (!Manager.players.contains(player.getName()))
                            player.kickPlayer(Main.instance.getConfig().getString("server_name").replace('&', '§') + "__§c   Une maintenance est actuellement en cours...__§9Merci de bien vouloir revenir plus tard !".replace("_", "\n"));
                    }
                    Bukkit.broadcastMessage("§cLa maintenance est désormais activé sur le serveur !");
                } else sender.sendMessage("§cLa maintenance est déjà activé !");
                return false;
            }
            if (args[0].equalsIgnoreCase("off")) {
                if (Manager.statut) {
                    Manager.setStatut(false);
                    Bukkit.broadcastMessage("§aLa maintenance n'est désormais active sur le serveur !");
                } else sender.sendMessage("§cLa maintenance n'est pas active...");
                return false;
            }
            if (args[0].equalsIgnoreCase("list")) {
                if (!Manager.players.isEmpty()) {
                    sender.sendMessage(ChatColor.YELLOW + "Liste des membres autorisés:");
                    for (String name : Manager.players)
                        sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.YELLOW + name);
                } else sender.sendMessage(ChatColor.RED + "La liste ne contient aucun joueur !");
                return false;
            }
        }

        if (args.length == 2) {
            // TODO /maintenance add <player>
            // TODO /maintenance remove <player>
            if (args[0].equalsIgnoreCase("add")) {
                if (!Manager.players.contains(args[1])) {
                    Manager.addPlayerToList(args[1]);
                    sender.sendMessage(ChatColor.YELLOW + args[1] + ChatColor.GOLD + " à été ajouté à la Maintenance");
                } else sender.sendMessage(ChatColor.RED + "Ce pseudo se trouve deja dans la liste !");
                return false;
            }
            if (args[0].equalsIgnoreCase("remove")) {
                if (Manager.players.contains(args[1])) {
                    Manager.removePlayerToList(args[1]);
                    sender.sendMessage(ChatColor.YELLOW + args[1] + ChatColor.GOLD + " à été retiré de la Maintenance");
                } else sender.sendMessage(ChatColor.RED + "Ce pseudo n'est pas dans la liste !");
                return false;
            }
        }
        sender.sendMessage(ChatColor.RED + "Commandes disponible:");
        sender.sendMessage(ChatColor.RED + "- /maintenance add <joueur> - Ajoute un joueur a la maintenance");
        sender.sendMessage(ChatColor.RED + "- /maintenance remove <joueur> - Retire un joueur de la maintenance");
        sender.sendMessage(ChatColor.RED + "- /maintenance list - Affiche la liste des joueurs autorisé à rejoindre le serveur");
        sender.sendMessage(ChatColor.RED + "- /maintenance on - Active la maintenance");
        sender.sendMessage(ChatColor.RED + "- /maintenance off - Retire la maintenance");
        return false;
    }
}

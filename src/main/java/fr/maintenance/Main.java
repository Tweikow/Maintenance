package fr.maintenance;

import fr.maintenance.commands.Maintenance;
import fr.maintenance.events.PlayerManager;
import fr.maintenance.utils.Manager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        Manager.createConfig();
        Manager.setList();

        getCommand("maintenance").setExecutor(new Maintenance());
        Bukkit.getPluginManager().registerEvents(new PlayerManager(), this);

        log(this.getName() + " §ais Enable");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static void log(String s){
        Bukkit.getConsoleSender().sendMessage(s);
    }
}

package fr.maintenance.utils;

import fr.maintenance.Main;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    public static List<String> players = new ArrayList<String>();

    public static boolean statut;
    private static File folder;
    private static File file;
    private static YamlConfiguration config;
    private static ConfigurationSection section;

    // TODO : Add
    public static void addPlayerToList(String player) {
        players.add(player);
    }

    // TODO : Remove
    public static void removePlayerToList(String player) {
        players.remove(player);
    }

    // TODO : Set
    public static void setList(){
        setFile();
        players.clear();
        statut = Main.instance.getConfig().getBoolean("boolean");
        if (config.isSet("list."))
            players.addAll(section.getKeys(false));
    }

    public static void setConfig(){
        setFile();
        Main.instance.getConfig().set("boolean", statut);
        Main.instance.saveDefaultConfig();
        config.set("list", null);

        if (!players.isEmpty()) {
            for (String name : players)
                config.set("list." + name, 0);
        }
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setStatut(boolean statut) {
        Manager.statut = statut;
    }

    private static void setFile(){
        file = new File(Main.instance.getDataFolder() + "/list.yml");
        folder = new File(Main.instance.getDataFolder() + "/");
        config = YamlConfiguration.loadConfiguration(file);
        section = config.getConfigurationSection("list.");
    }

    // TODO : Create
    public static void createConfig(){
        setFile();
        if (!folder.exists()) {
            if (file.mkdir()) {
                Main.log(folder.getName() + "§a was created successfully !");
            } else Main.log(folder.getName() + "§c encountered an error !");
        }
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    config.set("boolean", false);
                    config.set("list.Tweikow", 0);
                    config.save(file);
                    Main.log(file.getName() + "§a was created successfully !");
                } else Main.log(file.getName() + "§c encountered an error !");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

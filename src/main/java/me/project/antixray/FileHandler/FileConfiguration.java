package me.project.antixray.FileHandler;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileConfiguration {

    private static File file;
    private static org.bukkit.configuration.file.FileConfiguration fileConfiguration;

    final static org.bukkit.configuration.file.FileConfiguration config = Bukkit.getPluginManager().getPlugin("Anti-Xray").getConfig();

    public static void setup() {
        File folder = new File(Bukkit.getPluginManager().getPlugin("Anti-Xray").getDataFolder(), "Warnings");
        if (!folder.exists()) {folder.mkdir();}
        file = new File(Bukkit.getPluginManager().getPlugin("Anti-Xray").getDataFolder() + "//Warnings", "data.txt");
        if (!file.exists()) {
            try {file.createNewFile();}
            catch (IOException e) {throw new RuntimeException(e);}
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public static org.bukkit.configuration.file.FileConfiguration get() {return fileConfiguration;}

    public static void save() {
        try {fileConfiguration.save(file);}
        catch (IOException e) {throw new RuntimeException(e);}
    }

    public static void add(String nickname, String time, int x, int y, int z, boolean diamond, boolean warning) {
        String string = config.getString("string").replace("%player", nickname).replace("%time", time).replace("%x", String.valueOf(x)).replace("%y", String.valueOf(y)).replace("%z", String.valueOf(z));
        if (diamond) {string = string.replace("%diamond", "1");}
        else {string = string.replace("%diamond", "2");}
        if (warning) {string = string.replace("%warning", "WARNING");}
        else {string = string.replace("%warning", "");}
        fileConfiguration.set(string, "");
        save();
    }

}

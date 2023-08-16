package me.project.antixray;

import me.project.antixray.EventsHandler.MinecraftEvents;
import me.project.antixray.FileHandler.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Anti_Xray extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration.setup();
        getServer().getPluginManager().registerEvents(new MinecraftEvents(), this);
    }

    @Override
    public void onDisable() {
    }
}

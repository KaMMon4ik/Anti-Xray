package me.project.antixray.EventsHandler;

import me.project.antixray.MapHandler.Map;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MinecraftEvents implements Listener {

    final static FileConfiguration config = Bukkit.getPluginManager().getPlugin("Anti-Xray").getConfig();

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getBlock().getType().equals(Material.DIAMOND_ORE) | e.getBlock().getType().equals(Material.DEEPSLATE_DIAMOND_ORE)) {
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Block diamond = e.getBlock();
            if (Map.get_time(e.getPlayer()) == null) {
                Map.set_time(e.getPlayer(), System.currentTimeMillis());
                Map.set_count(e.getPlayer(), 0);
            }
            Map.add_count(e.getPlayer());
            int count = Map.get_count(e.getPlayer());
            if (System.currentTimeMillis() <= Map.get_time(e.getPlayer()) + (config.getInt("minutes") * 60000L)) {
                if (count >= config.getInt("diamonds_count")) {
                    if (e.getBlock().getType().equals(Material.DIAMOND_ORE)) {me.project.antixray.FileHandler.FileConfiguration.add(e.getPlayer().getName(), format.format(now), diamond.getX(), diamond.getY(), diamond.getZ(), false, true);}
                    else {me.project.antixray.FileHandler.FileConfiguration.add(e.getPlayer().getName(), format.format(now), diamond.getX(), diamond.getY(), diamond.getZ(), true, true);}
                }
                else {
                    if (e.getBlock().getType().equals(Material.DIAMOND_ORE)) {me.project.antixray.FileHandler.FileConfiguration.add(e.getPlayer().getName(), format.format(now), diamond.getX(), diamond.getY(), diamond.getZ(), false, false);}
                    else {me.project.antixray.FileHandler.FileConfiguration.add(e.getPlayer().getName(), format.format(now), diamond.getX(), diamond.getY(), diamond.getZ(), true, false);}
                }
            }
            else {
                Map.edit_time(e.getPlayer(), System.currentTimeMillis());
                Map.reset_count(e.getPlayer());
                if (e.getBlock().getType().equals(Material.DIAMOND_ORE)) {me.project.antixray.FileHandler.FileConfiguration.add(e.getPlayer().getName(), format.format(now), diamond.getX(), diamond.getY(), diamond.getZ(), false, false);}
                else {me.project.antixray.FileHandler.FileConfiguration.add(e.getPlayer().getName(), format.format(now), diamond.getX(), diamond.getY(), diamond.getZ(), true, false);}
            }
        }
    }

}

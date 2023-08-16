package me.project.antixray.MapHandler;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Map {

    static HashMap<Player, Long> time = new HashMap<Player, Long>();
    static HashMap<Player, Integer> count = new HashMap<Player, Integer>();

    public static void set_time(Player player, long Long) {time.put(player, Long);}
    public static Long get_time(Player player) {return time.get(player);}
    public static void edit_time(Player player, long Long) {time.replace(player, Long);}

    public static void set_count(Player player, int Int) {count.put(player, Int);}
    public static int get_count(Player player) {return count.get(player);}
    public static void reset_count(Player player) {count.replace(player, 1);}
    public static void add_count(Player player) {
        int old_count = get_count(player);
        count.replace(player, old_count + 1);
    }

}

package net.academy.common.utils;

import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
@ComentState(commentState = State.NONE)
public class Data {

    public static HashMap<String, HashMap<Object, Object>> globalCache = new HashMap<>();
    private static String prefix = "";
    private static String noPermMeassage = "Dafür hast du &4keine &7Rechte.";

    private static Plugin plugin;

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        Data.prefix = prefix;
    }

    public static String getNoPermMeassage() {
        return noPermMeassage;
    }

    public static void setNoPermMeassage(String noPermMeassage) {
        Data.noPermMeassage = noPermMeassage;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static void setPlugin(Plugin plugin) {
        Data.plugin = plugin;
    }
}

package net.academy.common.registerManager;

import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;
import net.academy.common.player.command.AcademyCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
@ComentState(commentState = State.FINISHED)
public class AcademyAutoRegister {

    /**
     * gets every Class in the package and registers all Bukkit events there
     *
     * @param listeners the package to ge throw
     * @param plugin    the plugin instance, required for the registration in Bukkit
     * @see Bukkit
     */
    public static void registerListener(String listeners, Plugin plugin) {

        for (Class<?> clazz : new Reflections(listeners).getSubTypesOf(Listener.class)) {

            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
            } catch (InstantiationException | SecurityException | NoSuchMethodException | InvocationTargetException |
                     IllegalAccessException | IllegalArgumentException e) {
                Bukkit.getConsoleSender().sendMessage("§4Fail to load Listener " + clazz.getName());
            }

        }
    }

    /**
     * gets every Class in the package and registers all Academy commands there
     *
     * @param commands the package to ge throw
     * @see AcademyCommand
     * @see Bukkit
     */
    public static void registerCommands(String commands) {

        for (Class<? extends AcademyCommand> clazz : new Reflections(commands).getSubTypesOf(AcademyCommand.class)) {
            try {
                AcademyCommand pluginCommand = clazz.getDeclaredConstructor().newInstance();
                AcademyCommand pluginTab = clazz.getDeclaredConstructor().newInstance();
                Objects.requireNonNull(Bukkit.getPluginCommand(pluginCommand.getCommandInfo().name())).setExecutor(pluginCommand);
                Objects.requireNonNull(Bukkit.getPluginCommand(pluginCommand.getCommandInfo().name())).setTabCompleter(pluginTab);
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                     InstantiationException e) {
                Bukkit.getConsoleSender().sendMessage("§4Fail to load Command " + clazz.getName());
            }
        }

    }

}

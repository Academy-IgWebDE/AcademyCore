package net.academy.common.player.command;

import lombok.Getter;
import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;
import net.academy.common.player.PlayerManager;
import net.academy.common.player.command.enums.CommandType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 * @see CommandInfo
 * @see CommandType
 */
@ComentState(commentState = State.FINISHED)
public abstract class AcademyCommand implements TabExecutor {

    private final CommandInfo commandInfo;
    @Getter
    private final HashMap<Integer, String[]> tabComplete;

    protected AcademyCommand() {
        tabComplete = new HashMap<>();
        commandInfo = getClass().getDeclaredAnnotation(CommandInfo.class);
        Objects.requireNonNull(commandInfo, "Pleace use @CommandInfo in " + getClass().getName());
    }

    public final CommandInfo getCommandInfo() {
        return commandInfo;
    }

    /**
     * this method is used to add a tab complete.
     * <b>Only call this in the setTabComplete method!</b>
     *
     * @param id                 the parameter says to which sub command the string array belongs
     * @param tabCompleteStrings the string array is used as tab complete
     */
    public final void addToComplete(Integer id, String[] tabCompleteStrings) {
        tabComplete.put(id, tabCompleteStrings);
    }

    /**
     * the method that executes the overridden executes. Can also be overwritten
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return the success from the execution
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        switch (commandInfo.commandType()) {

            case CONSOLE -> {
                if (sender instanceof Player) {
                    PlayerManager.getPlayerManager((Player) sender).sendMessage("&4This Command is a Console only Command!");
                    return false;
                }

                executeConsole(sender, args);
            }
            case PLAYER -> {
                if (!(sender instanceof Player)) {
                    Bukkit.getConsoleSender().sendMessage("&4This Command is a Player only Command!");
                    return false;
                }
                executePlayer((Player) sender, args);
            }

            case ALL -> {
                if (sender instanceof Player) {
                    executePlayer((Player) sender, args);
                } else {
                    executeConsole(sender, args);
                }
            }


        }
        return true;
    }

    /**
     * This method is executed when a player uses a command
     *
     * @param player the player how executes the command
     * @param args   the subcommands as string array
     */
    public void executePlayer(Player player, String[] args) {
    }

    /**
     * this void set the tabComplete for the Command
     */
    public void setTabComplete() {
    }

    /**
     * This method is executed when the console uses a command
     *
     * @param sender the commandSender how executes the command
     * @param args   the subcommands as string array
     */
    public void executeConsole(CommandSender sender, String[] args) {
    }

    /**
     * This method is executed when a player has a tab complete opportunity. Can also be overwritten
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed
     * @return the possible ways for the tabComplete
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        tabComplete.clear();
        setTabComplete();

        List<String> outPut = new ArrayList<>();
        if (tabComplete.containsKey(args.length)) {
            String[] tabImplementation = tabComplete.get(args.length);
            for (String tab : tabImplementation) {
                outPut.add(tab);
            }
        }
        return outPut;
    }
}

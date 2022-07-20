package net.academy.common.console;

import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 * @see MessageType
 */
@ComentState(commentState = State.FINISHED)
public class ConsoleManager {

    private static final ConsoleCommandSender console = Bukkit.getConsoleSender();

    private String debugPrefix;
    private String errorPrefix;
    private String infoPrefix;

    public ConsoleManager() {

        this.debugPrefix = "&6Debug&7: ";
        this.errorPrefix = "&4Error&7: ";
        this.infoPrefix = "&aInfo&7: ";

    }

    /**
     * send one Message to the Console, depending on the type
     *
     * @param type set the type, to choose the prefix
     * @param msg  set the message, which is sent after the prefix
     */
    public void sendMessage(MessageType type, String msg) {

        switch (type) {

            case INFO -> console.sendMessage(ChatColor.translateAlternateColorCodes('&', infoPrefix + msg));


            case DEBUG -> console.sendMessage(ChatColor.translateAlternateColorCodes('&', debugPrefix + msg));


            case ERROR -> console.sendMessage(ChatColor.translateAlternateColorCodes('&', errorPrefix + msg));

        }

    }

    /**
     * get the Prefix from the debug MessageType
     *
     * @return debug prefix
     */
    public String getDebugPrefix() {
        return debugPrefix;
    }

    /**
     * set the prefix from the debug MessageType
     *
     * @param debugPrefix the string were the prefix is set to
     */
    public void setDebugPrefix(String debugPrefix) {
        this.debugPrefix = debugPrefix;
    }

    /**
     * get the Prefix from the error MessageType
     *
     * @return error prefix
     */
    public String getErrorPrefix() {
        return errorPrefix;
    }

    /**
     * set the prefix from the error MessageType
     *
     * @param errorPrefix the string were the prefix is set to
     */
    public void setErrorPrefix(String errorPrefix) {
        this.errorPrefix = errorPrefix;
    }

    /**
     * get the Prefix from the info MessageType
     *
     * @return info prefix
     */
    public String getInfoPrefix() {
        return infoPrefix;
    }

    /**
     * set the prefix from the info MessageType
     *
     * @param infoPrefix the string were the prefix is set to
     */
    public void setInfoPrefix(String infoPrefix) {
        this.infoPrefix = infoPrefix;
    }
}

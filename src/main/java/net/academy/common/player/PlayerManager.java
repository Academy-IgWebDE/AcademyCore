package net.academy.common.player;

import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;
import net.academy.common.cache.DataManager;
import net.academy.common.utils.Data;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
@ComentState(commentState = State.STARTED)
public class PlayerManager {

    private static final DataManager dataManager = new DataManager("playerManagers");
    private final Player player;

    private PlayerManager(Player player) {
        this.player = player;
    }

    /**
     * creates or gets the PlayerManager instance for the player
     *
     * @param player the player, how wants management
     * @return the PlayerManager for the Player
     */
    public static PlayerManager getPlayerManager(Player player) {

        if (!dataManager.contains(player.getUniqueId().toString())) {
            dataManager.put(player.getUniqueId().toString(), new PlayerManager(player));
        }

        return (PlayerManager) dataManager.get(player.getUniqueId().toString());
    }

    /**
     * sends one Message to the player, with the prefix.
     * <b>Replaces all '&' to ColorCodes</b>
     *
     * @param message the message, to send after the prefix
     * @see Data
     */
    public void sendMessage(String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Data.getPrefix() + message));
    }

    /**
     * send an Action Bar to the player
     *
     * @param actionBar the text to be sent
     */
    public void sendActionbar(String actionBar) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', actionBar)));
    }


    /**
     * sends a message stating that the player has no rights to interact
     *
     * @see Data
     */
    public void sendNoPermMessage() {
        sendMessage(Data.getNoPermMeassage());
    }

}

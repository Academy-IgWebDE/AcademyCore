package net.academy.common.utils;

import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
@ComentState(commentState = State.NONE)
public class StringArrayConvertor {

    public static String[] getOnlinePlayerNames() {
        Object[] names = Bukkit.getServer().getOnlinePlayers().stream().map(Player::getName).toArray();
        String[] namesStrings = new String[names.length];
        for(int i = 0; i<names.length;i++) namesStrings[i] = names[i].toString();
        return namesStrings;
    }

}

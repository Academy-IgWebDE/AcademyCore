package net.academy.common.scoreboard;

import dev.jcsoftware.jscoreboards.JPerPlayerScoreboard;
import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;
import net.academy.common.cache.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
@ComentState(commentState = State.STARTED)
public class ScoreboardManager_old {

    private final DataManager dataManager;

    /**
     * creates a new Instance of ScoreboardManager without a Pattern for the display names
     */
    public ScoreboardManager_old() {
        dataManager = new DataManager("scoreBoards");
    }

    /**
     * set a scoreboard once to a player
     *
     * @param playerInput the player were the scoreboard is set to
     * @param title       the title line of the scoreboard
     * @param lines       the lines for the scoreboard
     */
    public void setScoreboardToPlayer(Player playerInput, String title, String[] lines) {
        JPerPlayerScoreboard scoreboard = new JPerPlayerScoreboard(
                (player) -> {
                    return title;
                },
                (player) -> {
                    return Arrays.asList(lines);
                }
        );
        addToScorboard(playerInput, scoreboard);
    }

    /**
     * set the scoreboard to all online players
     * @param title the title line for the scoreboard
     * @param lines the lines for the scoreboard
     */
    public void setGlobalScoreboard(String title, String[] lines) {
        JPerPlayerScoreboard scoreboard = new JPerPlayerScoreboard(
                (player) -> {
                    return title;
                },
                (player) -> {
                    return Arrays.asList(lines);
                }
        );

        Bukkit.getOnlinePlayers().forEach(all -> addToScorboard(all, scoreboard));

    }

    private void addToScorboard(Player player, JPerPlayerScoreboard scoreboard) {

        scoreboard.addPlayer(player);

        if (dataManager.contains(player.getUniqueId().toString()))
            dataManager.remove(player.getUniqueId().toString());

        dataManager.put(player.getUniqueId().toString(), scoreboard);
        scoreboard.updateScoreboard();

    }
}

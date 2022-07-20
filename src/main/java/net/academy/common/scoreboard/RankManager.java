package net.academy.common.scoreboard;

import dev.jcsoftware.jscoreboards.JGlobalMethodBasedScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;
import net.academy.common.cache.DataManager;
import net.academy.common.scoreboard.common.Rank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
@ComentState(commentState = State.FINISHED)
public class RankManager {

    private final DataManager dataManager;
    private final HashMap<String, Rank> ranks;
    private String displaynamePettern;

    private final JGlobalMethodBasedScoreboard scoreboard;

    private Rank defaultRank;

    /**
     * creates a new instance of the RankManager, to control the ranks
     *
     * @param displaynamePettern set the pettern to change the displayname from a player
     */
    public RankManager(String displaynamePettern) {
        this.dataManager = new DataManager("rankedPlayers");
        ranks = new HashMap<>();
        this.displaynamePettern = displaynamePettern;
        scoreboard = new JGlobalMethodBasedScoreboard();
    }


    /**
     * creates a Rank for the Server, were players can be set in
     *
     * @param weight        set the value for sorting
     * @param name          the name for the rank to call up in this system
     * @param displayname   the name with is shown for players
     * @param tabListPrefix the prefix in the tabList
     * @param chatColor     the color for the player name and the glowing effect
     */
    public void createRank(Integer weight, String name, String displayname, String tabListPrefix, ChatColor chatColor) {
        if (ranks.containsKey(name)) return;
        JScoreboardTeam jScoreboardTeam = scoreboard.createTeam(weight + name, tabListPrefix, chatColor);
        ranks.put(name, new Rank(name, displayname, tabListPrefix, weight, chatColor, jScoreboardTeam));
    }

    /**
     * set the team on the player
     *
     * @param name   the identifier for the team
     * @param player the person to set the team
     */
    public void setRankToPlayer(String name, Player player) {
        if (dataManager.contains(player.getUniqueId().toString())) dataManager.remove(player.getUniqueId().toString());
        dataManager.put(player.getUniqueId().toString(), name);
        ranks.get(name).getTeam().addPlayer(player);
        if (displaynamePettern != null)
            player.setDisplayName(ChatColor.translateAlternateColorCodes('&', displaynamePettern.replaceAll("%rank%", ranks.get(name).getTeam().getDisplayName()).replaceAll("%playerName%", player.getName())));
    }

    /**
     * removes the player from the rank
     *
     * @param player the player, how get removed
     */
    public void removeRankFromPlayer(Player player) {
        if (!dataManager.contains(player.getUniqueId().toString())) return;
        ranks.get(dataManager.get(player.getUniqueId().toString())).getTeam().removePlayer(player);
        dataManager.remove(player.getUniqueId().toString());
        player.setDisplayName(player.getName());
    }

    /**
     * deletes the rank
     *
     * @param name the rank how get deleted
     */
    public void deleteRank(String name) {
        if (!ranks.containsKey(name)) return;
        scoreboard.removeTeam(ranks.get(name).getTeam());
        ranks.remove(name);
    }

    /**
     * get the rank from a player
     *
     * @param player the player for the request
     * @return the rank from the player
     */
    public Rank getPlayersRank(Player player) {
        if (!dataManager.contains(player.getUniqueId().toString())) return defaultRank;
        return ranks.get(dataManager.get(player.getUniqueId().toString()));
    }


    public String getDisplaynamePettern() {
        return displaynamePettern;
    }

    public void setDisplaynamePettern(String displaynamePettern) {
        this.displaynamePettern = displaynamePettern;
    }

    public Rank getDefaultRank() {
        return defaultRank;
    }

    public void setDefaultRank(Rank defaultRank) {
        this.defaultRank = defaultRank;
    }
}

package net.academy.common.scoreboard.common;

import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import org.bukkit.ChatColor;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
public class Rank {

    /**
     * key, to call up this rank
     */
    String name;
    /**
     * requestable, for scoreboard or, /getRank command
     */
    String displayName;
    /**
     * prefix for the tabList
     */
    String tabListPrefix;
    /**
     * to sort the tablist
     */
    Integer weight;
    /**
     * color for the playername and the glowing effect
     */
    ChatColor color;
    /**
     * the team for the scoreboard
     */

    public Rank(String name, String displayName, String tabListPrefix, Integer weight, ChatColor color) {
        this.name = name;
        this.displayName = displayName;
        this.tabListPrefix = tabListPrefix;
        this.weight = weight;
        this.color = color;
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTabListPrefix() {
        return tabListPrefix;
    }

    public void setTabListPrefix(String tabListPrefix) {
        this.tabListPrefix = tabListPrefix;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

}

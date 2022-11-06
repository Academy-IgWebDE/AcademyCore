package net.academy.common.scoreboard;

import net.academy.common.scoreboard.common.HelpedScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;

public class ScoreboardHelper {

    private final ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

    private final HashMap<Player, HelpedScoreboard> scoreboards = new HashMap<>();

    public ScoreboardHelper() {

    }

    public void createScoreboard(Player player, String title, String[] lines) {

        if(scoreboards.containsKey(player)) {
            scoreboards.remove(player);
        }

        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective sidebar = scoreboard.registerNewObjective("sidebar" + player.getUniqueId(), "dummy", title);

        sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);

        Objective tabList = scoreboard.registerNewObjective("tabList" + player.getUniqueId(), "dummy", title);

        sidebar.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        Objective belowName = scoreboard.registerNewObjective("belowName" + player.getUniqueId(), "dummy", title);

        sidebar.setDisplaySlot(DisplaySlot.BELOW_NAME);

        int slot = lines.length - 1;

        for (String line : lines) {

            setSidebarScore(slot, line, scoreboard, sidebar);

            slot--;
        }

        scoreboards.put(player, new HelpedScoreboard(scoreboard, sidebar, tabList, belowName));

        player.setScoreboard(scoreboard);

    }

    public void setSidebarScore(Player player, int slot, String text) {

        if(scoreboards.containsKey(player)) {

            HelpedScoreboard scoreboard = scoreboards.get(player);

            setSidebarScore(slot, text, scoreboard.getScoreboard(), scoreboard.getSideBar());

        }

    }

    private void setSidebarScore(int slot, String content, Scoreboard scoreboard, Objective sidebar) {
        if(slot < 0) throw new IllegalArgumentException("slot must be > 0");
        if(slot> 16) throw new IllegalArgumentException("slot must be < 16");

        Team team = getOrCreate("sidebar" + slot, scoreboard);
        String entry = getEntry(slot);

        if(content == null) {
            scoreboard.resetScores(entry);
            return;
        }
        team.setPrefix(content);
        team.addEntry(entry);

        sidebar.getScore(entry).setScore(slot);
    }

    private Team getOrCreate (String name, Scoreboard scoreboard) {
        Team team = scoreboard.getTeam(name);
        if(team != null) return team;
        return scoreboard.registerNewTeam(name);
    }

    private String getEntry(int slot){
        return ChatColor.values()[slot].toString() + ChatColor.values()[slot+1];
    }


}

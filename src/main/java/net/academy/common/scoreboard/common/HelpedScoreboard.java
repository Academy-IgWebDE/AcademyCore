package net.academy.common.scoreboard.common;

import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class HelpedScoreboard {

    Scoreboard scoreboard;
    Objective sideBar;
    Objective tabList;
    Objective belowName;

    public HelpedScoreboard(Scoreboard scoreboard, Objective sideBar, Objective tabList, Objective belowName) {
        this.scoreboard = scoreboard;
        this.sideBar = sideBar;
        this.tabList = tabList;
        this.belowName = belowName;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public Objective getSideBar() {
        return sideBar;
    }

    public void setSideBar(Objective sideBar) {
        this.sideBar = sideBar;
    }

    public Objective getTabList() {
        return tabList;
    }

    public void setTabList(Objective tabList) {
        this.tabList = tabList;
    }

    public Objective getBelowName() {
        return belowName;
    }

    public void setBelowName(Objective belowName) {
        this.belowName = belowName;
    }
}

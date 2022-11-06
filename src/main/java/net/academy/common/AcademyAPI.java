package net.academy.common;

import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;
import net.academy.common.cache.DataManager;
import net.academy.common.console.ConsoleManager;
import net.academy.common.player.PlayerManager;
import net.academy.common.registerManager.AcademyAutoRegister;
import net.academy.common.scoreboard.RankManager_old;
import net.academy.common.scoreboard.ScoreboardManager_old;
import net.academy.common.utils.Data;
import net.academy.common.uuid.UUIDFetcher;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
@ComentState(commentState = State.NONE)
public class AcademyAPI {

    private ConsoleManager consoleManager;

    private ScoreboardManager_old scoreboardManager;
    private UUIDFetcher uuidFetcher;

    private RankManager_old rankManager;

    public AcademyAPI(String prefix, Plugin plugin) {
        Data.setPrefix(prefix);
        Data.setPlugin(plugin);

    }

    public void registerAll(String commandsPackage, String listenerPackage) {

        try {
            AcademyAutoRegister.registerCommands(commandsPackage);
            AcademyAutoRegister.registerListener(listenerPackage, Data.getPlugin());
        } catch (Exception ignore) {}

    }

    public ConsoleManager getConsoleManager() {
        if(consoleManager == null) consoleManager = new ConsoleManager();
        return consoleManager;
    }

    public ScoreboardManager_old getScoreboardManager() {
        if(scoreboardManager == null) scoreboardManager = new ScoreboardManager_old();
        return scoreboardManager;
    }

    public UUIDFetcher getUUIDFetcher() {
        if(uuidFetcher == null) uuidFetcher = new UUIDFetcher();
        return uuidFetcher;
    }

    public PlayerManager getPlayerManager(Player player) {
        DataManager dataManager = new DataManager("playerManagers");
        if (!dataManager.contains(player)) {
            dataManager.put(player, PlayerManager.getPlayerManager(player));
        }
        return (PlayerManager) dataManager.get(player);
    }

    /**
     * get the rankManager or set it if needed
     * @param pattern the Pattern for the rank manager.
     *                <b>only needed the first time</b>
     * @return the rank manager
     * @see RankManager_old
     */
    public RankManager_old getRankManager(String pattern) {
        if(rankManager == null) rankManager = new RankManager_old(pattern);
        return rankManager;
    }
}

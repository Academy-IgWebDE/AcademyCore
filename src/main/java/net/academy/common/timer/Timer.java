package net.academy.common.timer;

import net.academy.common.player.PlayerManager;
import net.academy.common.player.command.AcademyCommand;
import net.academy.common.timer.interfaces.ReverseTimerEndEvent;
import net.academy.common.timer.interfaces.StartTimerEvent;
import net.academy.common.timer.interfaces.StopTimerEvent;
import net.academy.common.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
public class Timer {
    private StartTimerEvent startTimerEvent;
    private StopTimerEvent stopTimerEvent;
    private ReverseTimerEndEvent reverseTimerEndEvent;
    private boolean reverse;
    private boolean running;
    private int time;

    public Timer() {
        this.reverse = false;

        this.running = false;
        this.time = 0;

        run();
    }

    public Timer(boolean reverse, int days, int hovers, int minutes, int sec) {
        this.reverse = reverse;
        time = 0;
        time = time + (days * 60 * 60 * 24);
        time = time + (hovers * 60 * 60);
        time = time + (minutes * 60);
        time = time + sec;

    }

    public Timer setStartEvent(StartTimerEvent event) {
        startTimerEvent = event;
        return this;
    }

    public Timer setStopEvent(StopTimerEvent event) {
        stopTimerEvent = event;
        return this;
    }
    public Timer setReverseTimerEndEvent(ReverseTimerEndEvent event) {
        reverseTimerEndEvent = event;
        return this;
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        if(running) executeStartEvent();
        else executeStopEvent();
        this.running = running;
    }

    private void executeStartEvent() {
        if(startTimerEvent != null) {
            startTimerEvent.handle();
        }
    }

    private void executeStopEvent() {
        if(stopTimerEvent != null) {
            stopTimerEvent.handle();
        }
    }
    private void executeReviseTimerEndEvent() {
        if(reverseTimerEndEvent != null) {
            reverseTimerEndEvent.handle();
        }
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public static String getTimeFormatted(int time) {
        String outPut = "";


        int days = time / (24 * 3600);
        time -= days * 24 * 3600;
        if (days > 0) outPut = outPut + days + "d ";

        int hours = time / 3600;
        time -= hours * 3600;
        if (hours > 0) outPut = outPut + hours + "h ";


        int minutes = time / 60;
        time -= minutes * 60;

        if (minutes > 0) outPut = outPut + minutes + "m ";

        outPut = outPut + time + "s";

        return outPut;
    }

    public void sendActionBar() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerManager playerManager = PlayerManager.getPlayerManager(player);
            if (!isRunning()) {
                playerManager.sendActionbar("&7Der Timer ist &4gesstoppt");
                continue;
            }
            playerManager.sendActionbar("&6&f" + getTimeFormatted(getTime()));
        }
    }


    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {

                sendActionBar();

                if (!isRunning()) {
                    return;
                }

                if(reverse) setTime(getTime() - 1);
                else setTime(getTime() + 1);

                if(time == 0) {
                    setRunning(false);
                    executeReviseTimerEndEvent();
                }
            }
        }.runTaskTimer(Data.getPlugin(), 20, 20);
    }
}


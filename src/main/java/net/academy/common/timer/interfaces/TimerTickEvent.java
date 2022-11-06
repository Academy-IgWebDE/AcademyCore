package net.academy.common.timer.interfaces;

import net.academy.common.timer.Timer;

public interface TimerTickEvent {

    void handle(Timer timer);
}

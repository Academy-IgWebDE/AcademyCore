package net.academy.common.console;

import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 * @see ConsoleManager
 */
@ComentState(commentState = State.NO_NEED)
public enum MessageType {

    INFO,
    ERROR,
    DEBUG

}

package net.academy.common.anotiation.comentState;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
@ComentState(commentState = State.NO_NEED)
public enum State {
    NONE,
    STARTED,

    NO_NEED,
    FINISHED,
    CORRECTED,
    MISTAKE
}

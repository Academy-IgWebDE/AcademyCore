package net.academy.common.anotiation.comentState;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
@Retention(RetentionPolicy.SOURCE)
@ComentState(commentState = State.NO_NEED)
public @interface Mistake {
}

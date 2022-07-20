package net.academy.common.anotiation.comentState;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@ComentState(commentState = State.NO_NEED)
public @interface ComentState {
    State commentState();
}

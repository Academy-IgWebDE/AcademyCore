package net.academy.common.player.command;


import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;
import net.academy.common.player.command.enums.CommandType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 * @see AcademyCommand
 * @see CommandType
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComentState(commentState = State.NO_NEED)
public @interface CommandInfo {

    String name();
    CommandType commandType() default CommandType.ALL;

}

package net.academy.common.player.command.enums;

import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;
import net.academy.common.player.command.AcademyCommand;
import net.academy.common.player.command.CommandInfo;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 * @see AcademyCommand
 * @see CommandInfo
 */
@ComentState(commentState = State.NO_NEED)
public enum CommandType {

    ALL,
    PLAYER,
    CONSOLE

}

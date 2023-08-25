package minion.commands;

import minion.data.exception.MinionException;
import minion.data.TaskList;
import minion.storage.Storage;
import minion.ui.Ui;

import java.io.IOException;

/**
 * Represents a command.
 */
public abstract class Command {
    /**
     * Execute the command given the tasks, ui, storage.
     * @param tasks Task list.
     * @param ui Ui of chatbot.
     * @param storage Storage of chatbot.
     * @throws MinionException general exception.
     * @throws IOException IO exception.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MinionException, IOException;

    /**
     * Checks whether the command is exit.
     * @return true if command is exit.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
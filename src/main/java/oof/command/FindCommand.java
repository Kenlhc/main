package oof.command;

import java.util.ArrayList;

import oof.Ui;
import oof.exception.OofException;
import oof.model.module.SemesterList;
import oof.model.task.Task;
import oof.model.task.TaskList;
import oof.storage.StorageManager;

/**
 * Represents a Command that finds and displays specific tasks in the TaskList.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private String argument;

    /**
     * Constructor for FindCommand.
     *
     * @param argument Command inputted by user.
     */
    public FindCommand(String argument) {
        super();
        this.argument = argument;
    }

    /**
     * Finds the Tasks that contain the keyword(s) specified.
     *
     * @param semesterList   Instance of SemesterList that stores Semester objects.
     * @param taskList       Instance of TaskList that stores Task objects.
     * @param ui             Instance of Ui that is responsible for visual feedback.
     * @param storageManager Instance of Storage that enables the reading and writing of Task
     *                       objects to hard disk.
     * @throws OofException if user input invalid commands.
     */
    public void execute(SemesterList semesterList, TaskList taskList, Ui ui, StorageManager storageManager)
            throws OofException {
        if (argument.isEmpty()) {
            throw new OofException("OOPS!!! The find command needs a description.");
        }
        String[] lineSplit = argument.split(" ");
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            for (int j = 0; j < lineSplit.length; j++) {
                Task task = taskList.getTask(i);
                String description = task.getDescription();
                String keyword = lineSplit[j].trim();
                if (description.contains(keyword)) {
                    matchedTasks.add(task);
                }
            }
        }
        ui.printMatchingTasks(matchedTasks);
    }
}

package goon;

import goon.tasks.TaskList;

/**
 * Entrypoint into the Goon program
 */
public class Goon {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Runs the Goon program
     * @param args Arguments - Unused
     */
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        try{
            storage.load(taskList);
        } catch (GoonException goonException) {
            System.out.println(goonException.getMessage());
        }
        Ui ui = new Ui();
        ui.run(taskList, storage);
    }

    /**
     * Generates a response for the user's chat message by calling the GoonBot
     * @param input the input string of the user
     */
    public String getResponse(String input) {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        try{
            storage.load(taskList);
        } catch (GoonException goonException) {
            System.out.println(goonException.getMessage());
        }
        Ui ui = new Ui();

        return ui.processInput(input, taskList, storage);
    }
}

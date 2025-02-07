package goon;

import goon.tasks.*;

public class Goon {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


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
}

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Muhammad Alli
 */
package ucf.assignments;

import java.util.ArrayList;

public class TaskLists {
    public String Title;
    public ArrayList<Task> taskList;
    private int tasklist_index; //stores the index of the list

    public TaskLists(String title) {
        taskList = new ArrayList<>();
        //tasklist_index = id;
        Title = title;

    }

    public void setTitle(String title) {

        Title = title;
    }

    public boolean AddToTaskList(Task newTask) {
        try {
            taskList.add(newTask);
        } catch (Exception e) {
            System.out.println("Error Adding " + newTask.toString() + " to the list.");
            return false;
        }

        return true;
    }

    public boolean RemoveFromTaskList(int task_index) {

        try {
            taskList.remove(task_index);
        } catch (Exception e) {
            System.out.println("Error Removing Index: " + task_index + " from the list.");
            return false;
        }
        return true;
    }



    //index return?

    @Override
    public String toString() {
        return taskList.toString();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public String getTitle() {
        return Title;
    }
}

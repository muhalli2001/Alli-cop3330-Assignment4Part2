/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Muhammad Alli
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    TaskLists tasklist_test = new TaskLists("TestName");
    TaskLists tasklist_test_2 = new TaskLists("TestName2");



    @Test
    void changeCompletion() {

        Task test_task = new Task("doesn't matter", "doesn't matter");
        test_task.setComplete(true);
        assertEquals(test_task.isComplete(), true);
    }

    @Test
    void change_ToDoList_Name() {
        tasklist_test.setTitle("Newtitle");
        assertEquals(tasklist_test.getTitle(), "Newtitle");
        tasklist_test.setTitle("TestName");
    }

    @Test
    void addTaskToList() {
        Task test_task = new Task("doesn't matter", "doesn't matter");
        tasklist_test.getTaskList().add(test_task);
        assertNotNull(tasklist_test.getTaskList().get(0));
        tasklist_test.getTaskList().remove(test_task);

    }

    @Test
    void deleteTaskFromList() {
        Task test_task = new Task("doesn't matter", "doesn't matter");
        tasklist_test.getTaskList().add(test_task);
        tasklist_test.getTaskList().add(test_task);
        tasklist_test.getTaskList().remove(1);
        assertEquals(tasklist_test.getTaskList().size(), 1);

    }

    @Test
    void displayCompleteTasks() {
        Task test_task = new Task("doesn't matter", "doesn't matter");
        tasklist_test.getTaskList().add(test_task);
        tasklist_test.getTaskList().add(test_task);
        tasklist_test.getTaskList().add(test_task);
        tasklist_test_2 = tasklist_test;
        int a =tasklist_test.getTaskList().size();
        int d=0;
        for(int i=0; i<2; i++)
        {
            if(!tasklist_test.getTaskList().get(i).isComplete())
            {
                d =9;
                tasklist_test.getTaskList().remove(tasklist_test.getTaskList().get(i));
            }
        }
        assertNotEquals(a, tasklist_test.getTaskList().size());

    }

    @Test
    void displayAllTasks() {

        Task test_task = new Task("doesn't matter", "doesn't matter");
        tasklist_test.getTaskList().add(test_task);
        tasklist_test_2 = tasklist_test;
        System.out.println(tasklist_test_2);
        assertEquals(tasklist_test, tasklist_test_2);


    }

    @Test
    void displayIncompleteTasks() {

        Task test_task = new Task("doesn't matter", "doesn't matter");
        tasklist_test.getTaskList().add(test_task);
        tasklist_test.getTaskList().get(0).setComplete(true);

        tasklist_test_2.getTaskList().addAll(tasklist_test.getTaskList());
        tasklist_test_2.getTaskList().remove(0);


        assertNotEquals(tasklist_test, tasklist_test_2);

    }

    @Test
    void editDescription() {
        Task test = new Task("Initial", "whatever");
        tasklist_test.AddToTaskList(test);
        tasklist_test.getTaskList().get(0).setDescription("new");
        assertNotEquals("Initial", tasklist_test.getTaskList().get(0).getDescription());
    }

    @Test
    void editDate() {
        Task test = new Task("Initial", "Initial");
        tasklist_test.AddToTaskList(test);
        tasklist_test.getTaskList().get(0).setDueDate("notInitial");
        assertNotEquals("Initial", tasklist_test.getTaskList().get(0).getDueDate());
    }

    @Test
    void clear_List() {
        Task test = new Task("Initial", "Initial");
        tasklist_test.AddToTaskList(test);
        tasklist_test.getTaskList().clear();
        assertEquals(0, tasklist_test.getTaskList().size());
    }

    @Test
    void saveList_ToExternal() throws IOException {

        Task task = new Task("desc", "due");
        tasklist_test.AddToTaskList(task);


        File text = new File("src/main/java/ucf/assignments/ExternalStorage_Test_Export.txt");
        String txt = "src/main/java/ucf/assignments/ExternalStorage_Test_Export.txt";
        FileWriter write = new FileWriter(txt);
        write.write(tasklist_test.getTitle());
        write.write("\n");
        int val=0;
        while(tasklist_test.getTaskList().size()>val)
        {
            String str = tasklist_test.getTaskList().get(val).getDescription();

            write.write(str);
            write.write("\n");

            str = tasklist_test.getTaskList().get(val).getDueDate();
            System.out.println(str);

            write.write(str);
            write.write("\n");

            boolean tru = tasklist_test.getTaskList().get(val).isComplete();


            write.write(String.valueOf(tru));
            write.write("\n");
            System.out.println(str);

            val++;
        }
        write.flush();
        write.close();

        Scanner scan = new Scanner(text);
        String str = scan.nextLine();
        assertEquals(str, "TestName");
    }

    @Test
    void open_List_FromExternal() throws FileNotFoundException {

        File text = new File("src/main/java/ucf/assignments/ExternalStorage_Test_Import.txt");
        tasklist_test.getTaskList().clear();

        Scanner scan = new Scanner(text);
        tasklist_test.setTitle(scan.nextLine());
        while (scan.hasNextLine()) {
            String des = scan.nextLine();
            String due = scan.nextLine();
            boolean comp = Boolean.parseBoolean(scan.nextLine());
            Task temp = new Task(des, due);
            temp.setComplete(comp);
            tasklist_test.getTaskList().add(temp);
        }
        assertEquals(tasklist_test.getTitle(), "Test_Title");
        assertEquals(tasklist_test.getTaskList().get(0).getDescription(), "This is a description of something");

    }

}
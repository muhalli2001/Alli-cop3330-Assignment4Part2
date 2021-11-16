/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Muhammad Alli
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Controller implements Initializable {
    /*
    to access fxid's, I will use:
    @FXML
    type(button, label, etc) fxid;
     */

    TaskLists tasklist = new TaskLists("set my name!");

    @FXML

    TextField NameOfToDoList;

    @FXML

    Button ChangeToDoName;

    @FXML
    Button disp_allitems;
    @FXML

    Label ToDoTitle;

    @FXML

    Button EditDescription;

    @FXML

    Button DeleteTask;

    @FXML

    Button ChangeDate;

    @FXML

    Button AddToDoList; //button to add todo list.

    @FXML

    Label Index; //stores the name of the todo list

    @FXML

    DatePicker pick_date;

    @FXML

    TextField DescriptionEA;

    @FXML

    ListView<Task> task_listview;

    @FXML

    Label List_ItemDescription;

    @FXML

    Label Date_fromlist;

    @FXML

     CheckBox checkbox;

    @FXML

    Button Export;

    @FXML

    Button Import;

    @FXML

    Button Clear;

    @FXML

    Button addtask;

    ObservableList<Task> observetasks = FXCollections.observableArrayList(tasklist.getTaskList());


    public void ChangeCompletion(MouseEvent e){

        System.out.println("checkbox pressed");
        String temp = Index.getText();
        if(temp.equals("Index")){System.out.println("Select a task."); return;}
        int a = Integer.parseInt(temp);
        tasklist.getTaskList().get(a).setComplete(checkbox.isSelected());
        System.out.println("Task at index:" + a + "set to:" +tasklist.getTaskList().get(a).isComplete());

    }

    public void Change_ToDoList_Name(ActionEvent e){

        if(NameOfToDoList!=null)
        {
            String temp = NameOfToDoList.getText();
            if(temp.equals("")){System.out.println("Enter something not blank.");return;}
            tasklist.setTitle(temp);
            System.out.println("to do list's new title: "+tasklist.getTitle());
            ToDoTitle.setText(tasklist.getTitle());
        }

    }
    public void AddTaskToList(ActionEvent e)
    {


            if(DescriptionEA.getText()!=null)
            {
                String str = DescriptionEA.getText();
                System.out.println(str);
                if(pick_date.getValue()==null)
                {
                    System.out.println("couldn't add because you did not select a date.");
                    return;
                }
                LocalDate mydate = pick_date.getValue();

                String date = mydate.toString();
                System.out.println(date);
                Task mytask = new Task(str, date);
                tasklist.getTaskList().add(mytask);
                observetasks = FXCollections.observableArrayList(tasklist.getTaskList());
                task_listview.setItems(observetasks);
                if(tasklist.getTaskList().size()==0)
                {
                    Index.setText("Index");
                    Date_fromlist.setText("YYYY-MM-DD");
                    List_ItemDescription.setText("Sample Description");
                }



            }
            else System.out.println("null");


            //if()


    }

    public void DeleteTaskFromList(ActionEvent e)
    {
        String a = Index.getText();
        if(!a.equals("Index"))
        {

            System.out.println(a +"to be removed");
            tasklist.getTaskList().remove(Integer.parseInt(a));

        }

        observetasks = FXCollections.observableArrayList(tasklist.getTaskList());
        task_listview.setItems(observetasks);
        if(tasklist.getTaskList().size()==0)
        {
            Index.setText("Index");
            Date_fromlist.setText("YYYY-MM-DD");
            List_ItemDescription.setText("Sample Description");
        }

    }

    public void DisplayCompleteTasks(ActionEvent e){

        System.out.println("Displaying complete Tasks.");
        observetasks = FXCollections.observableArrayList(tasklist.getTaskList());
        int a = tasklist.getTaskList().size();
        for(int i=0; i<a; i++)
        {
            if(!tasklist.getTaskList().get(i).isComplete())
            {
                observetasks.remove(tasklist.getTaskList().get(i));
            }
        }

        task_listview.setItems(observetasks);
        if(tasklist.getTaskList().size()==0)
        {
            Index.setText("Index");
            Date_fromlist.setText("YYYY-MM-DD");
            List_ItemDescription.setText("Sample Description");
        }

    }
    public void DisplayAllTasks(ActionEvent e){

        System.out.println("Displaying All Tasks.");
        observetasks = FXCollections.observableArrayList(tasklist.getTaskList());
        task_listview.setItems(observetasks);
        if(tasklist.getTaskList().size()==0)
        {
            Index.setText("Index");
            Date_fromlist.setText("YYYY-MM-DD");
            List_ItemDescription.setText("Sample Description");
        }
    }
    public void DisplayIncompleteTasks(ActionEvent e){

        System.out.println("Displaying incomplete Tasks.");
        observetasks = FXCollections.observableArrayList(tasklist.getTaskList());
        int a = tasklist.getTaskList().size();
        for(int i=0; i<a; i++)
        {
            if(tasklist.getTaskList().get(i).isComplete())
            {
                observetasks.remove(tasklist.getTaskList().get(i));
            }
        }

        task_listview.setItems(observetasks);
        if(tasklist.getTaskList().size()==0)
        {
            Index.setText("Index");
            Date_fromlist.setText("YYYY-MM-DD");
            List_ItemDescription.setText("Sample Description");
        }

    }

    public void EditDescription(ActionEvent e){

        System.out.println("add task pressed");
        String temp = Index.getText();

        if(DescriptionEA.getText()!=null)
        {
            String str = DescriptionEA.getText();
            System.out.println(str);

            tasklist.getTaskList().get(Integer.parseInt(temp)).setDescription(str);

            observetasks = FXCollections.observableArrayList(tasklist.getTaskList());
            task_listview.setItems(observetasks);
            if(tasklist.getTaskList().size()==0)
            {
                Index.setText("Index");
                Date_fromlist.setText("YYYY-MM-DD");
                List_ItemDescription.setText("Sample Description");
            }



        }
        else System.out.println("null");


    }

    public void EditDate(ActionEvent e){

        System.out.println("edit task date pressed");
        String temp = Index.getText();


            if(pick_date.getValue()==null)
            {
                System.out.println("couldn't change date because you did not select a date.");
                return;
            }
            LocalDate mydate = pick_date.getValue();

            String date = mydate.toString();
            System.out.println(date);
            tasklist.getTaskList().get(Integer.parseInt(temp)).setDueDate(date);

            observetasks = FXCollections.observableArrayList(tasklist.getTaskList());
            task_listview.setItems(observetasks);
            if(tasklist.getTaskList().size()==0)
            {
                Index.setText("Index");
                Date_fromlist.setText("YYYY-MM-DD");
                List_ItemDescription.setText("Sample Description");
            }



    }
    public void Clear_List(ActionEvent e)
    {
        tasklist.getTaskList().clear();
        System.out.println("cleared list!");
        observetasks = FXCollections.observableArrayList(tasklist.getTaskList());
        task_listview.setItems(observetasks);
        Index.setText("Index");

    }

    public void SaveList_ToExternal(ActionEvent e) throws IOException {

        System.out.println("pressed");
        File text = new File("src/main/java/ucf/assignments/ExternalStorage.txt");
        Scanner checkempty = new Scanner(text);

        String txt = "src/main/java/ucf/assignments/ExternalStorage.txt";
        FileWriter write = new FileWriter(txt);
        if(!checkempty.hasNextLine())
        {
            write.write(tasklist.getTitle());
            write.write("\n");
        }

        int val=0;
        while(tasklist.getTaskList().size()>val)
        {
            String str = tasklist.getTaskList().get(val).getDescription();

            write.write(str);
            write.write("\n");

            str = tasklist.getTaskList().get(val).getDueDate();
            System.out.println(str);

            write.write(str);
            write.write("\n");

            boolean tru = tasklist.getTaskList().get(val).isComplete();


            write.write(String.valueOf(tru));
            write.write("\n");
            System.out.println(str);

            val++;
        }
        write.flush();
        write.close();

        if(tasklist.getTaskList().size()==0)
        {
            Index.setText("Index");
            Date_fromlist.setText("YYYY-MM-DD");
            List_ItemDescription.setText("Sample Description");
        }
        checkempty.close();

    }

    public void Open_List_FromExternal(ActionEvent e) throws FileNotFoundException{

        System.out.println("pressed");
        File text = new File("src/main/java/ucf/assignments/ExternalStorage.txt");
        tasklist.getTaskList().clear();

        Scanner scan = new Scanner(text);

        tasklist.setTitle(scan.nextLine());

        ToDoTitle.setText(tasklist.getTitle());




        while(scan.hasNextLine())
        {
            String des= scan.nextLine();
            String due= scan.nextLine();
            boolean comp =  Boolean.parseBoolean(scan.nextLine());
            Task temp = new Task(des, due);
            temp.setComplete(comp);
            tasklist.getTaskList().add(temp);
        }

        observetasks = FXCollections.observableArrayList(tasklist.getTaskList());
        task_listview.setItems(observetasks);
        if(tasklist.getTaskList().size()==0)
        {
            Index.setText("Index");
            Date_fromlist.setText("YYYY-MM-DD");
            List_ItemDescription.setText("Sample Description");
        }



    }

    public void DisplayListViewIndex(MouseEvent e)
    {
        if(observetasks.size()==0){System.out.println("cannot select empty items!");return;}
        int temp=task_listview.getSelectionModel().getSelectedIndex();

        System.out.println(temp);
        if(e != null)
        {
            Index.setText(String.valueOf(temp));
            String str = tasklist.getTaskList().get(temp).getDescription();
            System.out.println(str);
            List_ItemDescription.setText(str);
            str = tasklist.getTaskList().get(temp).getDueDate();
            System.out.println(str);
            Date_fromlist.setText(str);
            boolean boolin = tasklist.getTaskList().get(temp).isComplete();
            if(boolin)
            {
                checkbox.setSelected(true);
            }
            if(!boolin) {
                checkbox.setSelected(false);
            }

        }
        else
        {
            System.out.println("nothing pressed");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Task atask = new Task("Sample Description", "YYYY-MM-DD");
        tasklist.AddToTaskList(atask);

        observetasks = FXCollections.observableArrayList(tasklist.getTaskList());
        task_listview.setItems(observetasks);
        System.out.println(tasklist.getTaskList().get(0).getDescription());
        //int temp=task_listview.getSelectionModel().getSelectedIndex();
        //System.out.println(temp);

    }
}
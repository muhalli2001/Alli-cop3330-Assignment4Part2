/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Muhammad Alli
 */
package ucf.assignments;

import java.util.Date;

public class Task {
    protected String DueDate;
    protected String Description;
    protected boolean complete;



    public Task(String Description, String DueDate)
    {
        this.DueDate = DueDate;
        this.Description = Description;
        this.complete = false;
    }

    public boolean isComplete() {
        return complete;
    }

    public String getDueDate() {
        return DueDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

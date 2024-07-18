package com.todo;

import java.util.ArrayList;
import java.util.List;

public class TaskMemento {
    private List<Task> tasks;

    public TaskMemento(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public List<Task> getSavedTasks() {
        return tasks;
    }
}

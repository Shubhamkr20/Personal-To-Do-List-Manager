package com.todo;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private List<TaskMemento> taskMementos = new ArrayList<>();
    private int currentState = -1;

    public void addTask(Task task) {
        saveState();
        tasks.add(task);
    }

    public void deleteTask(String description) {
        saveState();
        tasks.removeIf(task -> task.getDescription().equals(description));
    }

    public void markTaskCompleted(String description) {
        saveState();
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                task.setCompleted(true);
                break;
            }
        }
    }

    public List<Task> viewTasks(String filter) {
        List<Task> filteredTasks = new ArrayList<>();
        switch (filter.toLowerCase()) {
            case "show completed":
                for (Task task : tasks) {
                    if (task.isCompleted()) {
                        filteredTasks.add(task);
                    }
                }
                break;
            case "show pending":
                for (Task task : tasks) {
                    if (!task.isCompleted()) {
                        filteredTasks.add(task);
                    }
                }
                break;
            case "show all":
            default:
                filteredTasks = tasks;
                break;
        }
        return filteredTasks;
    }

    public void undo() {
        if (currentState > 0) {
            currentState--;
            tasks = new ArrayList<>(taskMementos.get(currentState).getSavedTasks());
        }
    }

    public void redo() {
        if (currentState < taskMementos.size() - 1) {
            currentState++;
            tasks = new ArrayList<>(taskMementos.get(currentState).getSavedTasks());
        }
    }

    private void saveState() {
        while (taskMementos.size() > currentState + 1) {
            taskMementos.remove(taskMementos.size() - 1);
        }
        taskMementos.add(new TaskMemento(new ArrayList<>(tasks)));
        currentState++;
    }
}

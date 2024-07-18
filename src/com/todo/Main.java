package com.todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static TaskManager taskManager = new TaskManager();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Welcome to the To-Do List Manager!");

        while (true) {
            System.out.println("\nEnter a command (Add Task: \"<description>, Due: <yyyy-MM-dd>\", Mark Completed: \"<description>\", View Tasks: \"<filter>\", Undo, Redo, Exit): ");
            command = scanner.nextLine().trim();
            System.out.println("Command entered: '" + command + "'"); // Debugging statement

            if (command.startsWith("Add Task: \"") && command.endsWith("\"")) {
                addTask(command.substring(10, command.length() - 1));
            } else if (command.startsWith("Mark Completed: \"") && command.endsWith("\"")) {
                markTaskCompleted(command.substring(16, command.length() - 1));
            } else if (command.startsWith("View Tasks: \"") && command.endsWith("\"")) {
                viewTasks(command.substring(12, command.length() - 1));
            } else if (command.equalsIgnoreCase("Undo")) {
                taskManager.undo();
                System.out.println("Undo performed.");
            } else if (command.equalsIgnoreCase("Redo")) {
                taskManager.redo();
                System.out.println("Redo performed.");
            } else if (command.equalsIgnoreCase("Exit")) {
                System.out.println("Exiting the To-Do List Manager. Goodbye!");
                scanner.close();
                return;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private static void addTask(String input) {
        System.out.println("Adding task: '" + input + "'"); // Debugging statement
        String[] parts = input.split(", Due: ");
        String description = parts[0].trim();
        Date dueDate = null;

        if (parts.length > 1) {
            try {
                dueDate = dateFormat.parse(parts[1].trim());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Task not added.");
                return;
            }
        }

        Task task = new Task.TaskBuilder(description)
                .setDueDate(dueDate)
                .setCompleted(false)
                .build();
        taskManager.addTask(task);
        System.out.println("Task added successfully.");
    }

    private static void markTaskCompleted(String description) {
        System.out.println("Marking task as completed: '" + description + "'"); // Debugging statement
        taskManager.markTaskCompleted(description.trim());
        System.out.println("Task marked as completed.");
    }

    private static void viewTasks(String filter) {
        System.out.println("Viewing tasks with filter: '" + filter + "'"); // Debugging statement
        List<Task> tasks = taskManager.viewTasks(filter.trim().toLowerCase());
        System.out.println("Tasks:");
        for (Task task : tasks) {
            String status = task.isCompleted() ? "Completed" : "Pending";
            String dueDateStr = task.getDueDate() != null ? ", Due: " + dateFormat.format(task.getDueDate()) : "";
            System.out.println(task.getDescription() + " - " + status + dueDateStr);
        }
    }
}

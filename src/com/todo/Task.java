package com.todo;

import java.util.Date;

public class Task {
    private String description;
    private Date dueDate;
    private boolean completed;
    private String tag;

    private Task(TaskBuilder builder) {
        this.description = builder.description;
        this.dueDate = builder.dueDate;
        this.completed = builder.completed;
        this.tag = builder.tag;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getTag() {
        return tag;
    }

    public static class TaskBuilder {
        private String description;
        private Date dueDate;
        private boolean completed;
        private String tag;

        public TaskBuilder(String description) {
            this.description = description;
        }

        public TaskBuilder setDueDate(Date dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public TaskBuilder setCompleted(boolean completed) {
            this.completed = completed;
            return this;
        }

        public TaskBuilder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }

    @Override
    public String toString() {
        return "Task: " + description + ", Due: " + dueDate + ", Completed: " + completed + ", Tag: " + tag;
    }
}

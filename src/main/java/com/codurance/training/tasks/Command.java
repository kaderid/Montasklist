package com.codurance.training.tasks;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Command {
    private long lastId = 0;
    private long nextId() {
        return ++lastId;
    }
    private void addTask(String project, String description){
        List<Task> projectTasks = tasks.get(project);
    if (projectTasks == null) {
    output.printf("Could not find a project with the name \"%s\".", project);
        output.println();
            return;
        }

        Id i= new Id(nextId());
        Description e=new Description(description);
        Done d= new Done(false);
        projectTasks.add(new Task(i, e, d));
    }
    private void setDone(String idString, boolean done){
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                Id ids= task.getId();
                if (ids.getValue() == id) { // task.isequal(id)
                    task.setDone(new Done(done));
                    return;
                }
            }
        }
        output.printf("Could not find a task with an ID of %d.", id);
        output.println();
    }
    private void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }
    private final TaskMap tasks = new TaskMap(new LinkedHashMap<String, List<Task>>());
    private  BufferedReader input;
    private  PrintWriter output;
    
    void show() {
    for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            output.println(project.getKey());
        for (Task task : project.getValue()) {
                Id id= task.getId();
            Description description=task.getDescription();
            output.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), id, description);
            
        }
        output.println();
    }
}

    void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } 
        if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }
    void check(String idString) {
        setDone(idString, true);
    }

    void uncheck(String idString) {
        setDone(idString, false);
    }
    void help() {
        output.println("Commands:");
        output.println("  show");
        output.println("  add project <project name>");
        output.println("  add task <project name> <task description>");
        output.println("  check <task ID>");
        output.println("  uncheck <task ID>");
        output.println();
    }

    void error(String command) {
        output.printf("I don't know what the command \"%s\" is.", command);
        output.println();
    }
}

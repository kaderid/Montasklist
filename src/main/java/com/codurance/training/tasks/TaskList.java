package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final TaskMap tasks = new TaskMap(new LinkedHashMap<String, List<Task>>());
    private final BufferedReader input;
    private final PrintWriter output;

    

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter output = new PrintWriter(System.out);
        new TaskList(input, output).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.input = reader;
        this.output = writer;
    }

    public void run() {
        while (true) {
            output.print("> ");
            output.flush();
            String command;
            try {
                command = input.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        Command commande =new Command(); 
        switch (command) {
            case "show":
                commande.show();
                break;
            case "add":
                commande.add(commandRest[1]);
                break;
            case "check":
                commande.check(commandRest[1]);
                break;
            case "uncheck":
                commande.uncheck(commandRest[1]);
                break;
            case "help":
                commande.help();
                break;
            default:
                commande.error(command);
                break;
        }
    }


   // private void addProject(String name) {
   //     tasks.put(name, new ArrayList<Task>());
   // }

   // private void addTask(String project, String description) {
     //  List<Task> projectTasks = tasks.get(project);
      //  if (projectTasks == null) {
      //      output.printf("Could not find a project with the name \"%s\".", project);
      //      output.println();
      //      return;
     //   }

      //  Id i= new Id(nextId());
       // Description e=new Description(description);
       // Done d= new Done(false);
       // projectTasks.add(new Task(i, e, d));
   // }

   /*  private void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId().getValue() == id) { // task.isequal(id)

                    task.setDone(new Done(done));
                    return;
                }
            }
        }
        output.printf("Could not find a task with an ID of %d.", id);
        output.println();
    }
**/
   
}

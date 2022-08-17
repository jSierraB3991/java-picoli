package com.example.todo.commands.subs;

import com.example.todo.models.ListFormat;
import com.example.todo.models.Status;
import com.example.todo.models.Todo;
import com.example.todo.service.TodoService;
import com.example.todo.service.impl.TodoServiceImpl;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "list",
        aliases = { "ls", "show" },
        description = "This is a Sub Command and lists all the tasks as per the parameters",
        version = "1.0.0",
        mixinStandardHelpOptions = true,
        requiredOptionMarker = '*',
        footerHeading = "%nCopyright %n",
        footer = "\tDeveloped by Juan David Sierra",
        header = "List Todo SubCommand",
        optionListHeading = "%nOptions are%n")
public class ListTodoCommand implements Callable<Integer> {

    @CommandLine.Option(names = { "--format", "-f" },
            required = false,
            description = "Formatting the Todo and the default value is {DEFAULT} %nAll format are {SHORT}, {DETAIL}",
            defaultValue = "DEFAULT"
    )
    ListFormat format;

    private final TodoService service;

    public ListTodoCommand() {
        this.service = new TodoServiceImpl();
    }

    @Override
    public Integer call() throws Exception {
        this.service.findAll().forEach(todo -> printTodo(this.format, todo));
        return 0;
    }

    private void printTodo(ListFormat format, Todo todo) {
        if(format == ListFormat.SHORT){
            System.out.printf("%4d %s %s %s%n",
                    todo.getId(),
                    getStatus(todo),
                    todo.getCreateOn(),
                    todo.getMessage());
        } else {
            System.out.println("Id      = " + todo.getId());
            System.out.println("Message = " + todo.getMessage());
            System.out.println("Status  = " + todo.getStatus());
            System.out.println("Created = " + todo.getCreateOn());
            System.out.println("\n");
        }
    }

    private static String getStatus(Todo todo) {
        if (todo.getStatus().equals(Status.COMPLETED)) return "[x]";
        if (todo.getStatus().equals(Status.IN_PROGRESS)) return "[/]";
        return "[ ]";
    }
}
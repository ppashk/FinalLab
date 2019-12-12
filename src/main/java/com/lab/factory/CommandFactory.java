package com.lab.factory;

import com.lab.web.command.*;
import com.lab.web.command.common.*;
import com.lab.web.command.pages.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> getCommandMap = new HashMap<>();
    private static Map<String, Command> postCommandMap = new HashMap<>();
    private static Command defaultCommand = new NotFoundCommand();

    static {
        getCommandMap.put("/404", defaultCommand);
        getCommandMap.put("/403", new NoPermissionCommand());
        getCommandMap.put("/", new HomeCommand());
        getCommandMap.put("/language", new LanguageCommand());
        getCommandMap.put("/login", new LoginCommand());
        getCommandMap.put("/logout", new LogoutCommand());
        getCommandMap.put("/cashier", new CashierCommand());
        getCommandMap.put("/chief", new ChiefCommand());
        getCommandMap.put("/loader", new LoaderCommand());
        getCommandMap.put("/report", new ReportCommand());

        postCommandMap.put("/", new HomeCommand());
        postCommandMap.put("/login", new LoginCommand());
        postCommandMap.put("/cashier", new CashierCommand());
        postCommandMap.put("/chief", new ChiefCommand());
        postCommandMap.put("/loader", new LoaderCommand());
        postCommandMap.put("/report", new ReportCommand());
    }

    private CommandFactory() {
    }

    public static Command getCommand(String path, String type) {
        return "GET".equals(type)
                ? getCommand(path)
                : postCommand(path);
    }

    private static Command getCommand(String path) {
        return getCommandMap.getOrDefault(path, defaultCommand);
    }

    private static Command postCommand(String path) {
        return postCommandMap.getOrDefault(path, defaultCommand);
    }
}

package com.lab.web.command.common;

import com.lab.entity.User;
import com.lab.factory.ServiceFactory;
import com.lab.service.UserService;
import com.lab.web.command.MultipleMethodCommand;
import com.lab.web.data.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.lab.constant.PageConstants.HOME_REDIRECT;
import static com.lab.constant.PageConstants.LOGIN_PAGE;

public class LoginCommand extends MultipleMethodCommand {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    private UserService userService;

    public LoginCommand() {
        this.userService = ServiceFactory.getUserService();
    }

    @Override
    protected Page performGet(HttpServletRequest request) {
        return new Page(LOGIN_PAGE);
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LOG.debug(String.format("Login %s, password %s", username, password));

        Optional<User> optionalUser = userService.validateUser(username, password);

        if (optionalUser.isPresent()) {
            LOG.info("Login success");
            User user = optionalUser.get();
            session.setAttribute("user", user);
            return new Page(HOME_REDIRECT,true);
        }

        request.setAttribute("error", "error");
        return new Page(LOGIN_PAGE);
    }
}

package barbot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import barbot.database.model.User;
import barbot.database.service.UserService;
import barbot.utils.FieldValidator;

/**
 * Created by Alex on 2/19/2017.
 */
@Controller
public class AuthController {

    @Autowired
    FieldValidator validator;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(path = "/auth/register", method = RequestMethod.POST)
    public Map register(HttpServletRequest request) {
        HashMap result = new HashMap();
        result.put("result", "success");

        HashMap fieldsToValidate = new HashMap();
        fieldsToValidate.put("name", "required");
        fieldsToValidate.put("email", "required|unique:user");
        fieldsToValidate.put("password", "required");

        if(validator.validate(request.getParameterMap(), fieldsToValidate)) {
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setPassword(passwordEncoder.encode(request.getParameter("password")));
            user.setEmail(request.getParameter("email"));
            userService.create(user);
        } else {
            result.put("result", "error");
            result.put("errors", validator.getErrors());
        }

        return result;
    }
}

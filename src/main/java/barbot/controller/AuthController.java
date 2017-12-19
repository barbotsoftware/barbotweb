package barbot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import barbot.database.dao.MainDao;
import barbot.database.model.Barbot;
import barbot.database.model.EmailCapture;
import barbot.database.model.User;
import barbot.database.service.BarbotService;
import barbot.database.service.UserService;
import barbot.utils.FieldValidator;
import barbot.utils.Routes;

/**
 * Created by Alex on 2/19/2017.
 */
@Controller
public class AuthController extends BaseController {

    @Autowired
    FieldValidator validator;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    BarbotService barbotService;

    @Autowired
    UserService userService;

    @Autowired
    MainDao mainDao;

    @ResponseBody
    @RequestMapping(path = Routes.REGISTER, method = RequestMethod.POST)
    public Map register(HttpServletRequest request) {
        HashMap fieldsToValidate = new HashMap();
        fieldsToValidate.put("name", "required");
        fieldsToValidate.put("email", "required|unique:user|valid_email");
        fieldsToValidate.put("password", "required");

        if(validator.validate(request.getParameterMap(), fieldsToValidate)) {
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setPassword(passwordEncoder.encode(request.getParameter("password")));
            user.setEmail(request.getParameter("email"));
            userService.create(user);
        } else {
            return error(validator.getErrors());
        }

        return success(null);
    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    @RequestMapping(path = Routes.LOGIN, method = RequestMethod.POST)
    public Map login(HttpServletRequest request) {
        HashMap fieldsToValidate = new HashMap();
        fieldsToValidate.put("type", "required");
        fieldsToValidate.put("username", "required");
        fieldsToValidate.put("password", "required");

        if(validator.validate(request.getParameterMap(), fieldsToValidate)) {
            String type = request.getParameter("type");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (type.equals("user")) {
                User user = userService.findByUsernameAndPassword(username, password);

                if (user != null) {
                    return success(null);
                }
            } else if (type.equals("barbot")) {
                Barbot barbot = barbotService.findByNameAndPassword(username, password);

                if (barbot != null) {
                    return success(null);
                }
            }

        } else {
            return error(validator.getErrors());
        }

        return error(null);
    }

    @ResponseBody
    @RequestMapping(path = Routes.EMAIL_CAPTURE, method = RequestMethod.POST)
    public Map emailCapture(@RequestBody EmailCapture emailCapture) {
        HashMap fieldsToValidate = new HashMap();
        fieldsToValidate.put("email", "required|valid_email");

        Map parameterMap = new HashMap<>();
        parameterMap.put("email", emailCapture.getEmail());

        if(validator.validate(parameterMap, fieldsToValidate)) {
            mainDao.saveEmailCapture(emailCapture);
        } else {
            return error(validator.getErrors());
        }

        return success(null);
    }
}

package barbot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import barbot.database.dao.MainDao;
import barbot.database.model.EmailCapture;
import barbot.utils.Routes;
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
public class AuthController extends BaseController {

    @Autowired
    FieldValidator validator;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

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

    @ResponseBody
    @RequestMapping(path = Routes.EMAIL_CAPTURE, method = RequestMethod.POST)
    public Map emailCapture(HttpServletRequest request) {
        HashMap fieldsToValidate = new HashMap();
        fieldsToValidate.put("email", "required|valid_email");

        if(validator.validate(request.getParameterMap(), fieldsToValidate)) {
            EmailCapture emailCapture = new EmailCapture();
            emailCapture.setName(request.getParameter("name"));
            emailCapture.setEmail(request.getParameter("email"));
            emailCapture.setCity(request.getParameter("city"));
            mainDao.saveEmailCapture(emailCapture);
        } else {
            return error(validator.getErrors());
        }

        return success(null);
    }
}

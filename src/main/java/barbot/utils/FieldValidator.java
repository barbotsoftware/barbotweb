package barbot.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import barbot.database.dao.MainDao;

/**
 * Common validation logic for validating user input
 */
@Component
public class FieldValidator {

    @Autowired
    private HelperMethods hlpr;

    @Autowired
    private MainDao mainDao;

    private Map errors;

    public boolean validate(Map fields, Map<String, String> requirements) {
        errors = new HashMap();

        boolean error = false;
        for(Map.Entry<String, String> reqs : requirements.entrySet()) {
            for(String req : reqs.getValue().split("\\|")) {
                if(!validateField(fields, reqs.getKey(), req)) {
                    error = true;
                }
            }
        }

        return !error;
    }

    public Map getErrors() {
        return errors;
    }

    private boolean validateField(Map fields, String field, String validator) {
        boolean flag = true;
        String messageKey = "";

        if(fields.containsKey(field)) {
            String[] validatorParts = validator.split(":");

            String value = fields.get(field).getClass().equals(String.class) ? (String)fields.get(field) : ((String[]) fields.get(field))[0];
            switch (validatorParts[0])
            {
                case "required":
                    if (value == null || value.isEmpty())
                    {
                        messageKey = field + ".empty";
                        flag = false;
                    }
                    break;
                case "exists":
                    if(!mainDao.checkEntityExists(validatorParts[1], value)) {
                        messageKey = field + ".doesNotExist";
                        flag = false;
                    }
                    break;
                case "unique":
                    if (!mainDao.checkFieldIsUnique(validatorParts[1], field, value))
                    {
                        messageKey = field + ".notUnique";
                        flag = false;
                    }
                    break;
                case "requiredNotZero":
                    if (value == null || value.isEmpty() || value.equals("0"))
                    {
                        messageKey = field + ".empty";
                        flag = false;
                    }
                    break;
                case "valid_password":
                    // TODO
                    break;
                case "integer":
                    try {
                        Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        messageKey = field + ".invalidInt";
                        flag = false;
                    }
                    break;
                case "no_special_chars":
                    if(!Pattern.matches("^[a-zA-Z0-9_\\-\\s()]*$", value)) {
                        messageKey = field + ".noSpecialChars";
                        flag = false;
                    }
                    break;
                case "alpha_no_spaces":
                    if(!Pattern.matches("^[a-zA-Z0-9]*$", value)) {
                        messageKey = field + ".alphaNoSpaces";
                        flag = false;
                    }
                    break;
                case "alpha":
                    if(!Pattern.matches("^[a-zA-Z0-9\\s]*$", value)) {
                        messageKey = field + ".alpha";
                        flag = false;
                    }
                    break;
                case "valid_email":
                    if(!Pattern.matches("^([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}){0,1}$", value)) {
                        messageKey = field + ".invalidEmail";
                        flag = false;
                    }
                    break;
                case "validPhone":
                    if(!Pattern.matches("^[0-9()-]*$", value)) {
                        messageKey = field + ".invalidPhone";
                        flag = false;
                    }
                    break;
                case "matches":
                    if(fields.containsKey(validatorParts[1])) {
                        String fieldToMatch = ((String[]) fields.get(validatorParts[1]))[0];
                        if(!fieldToMatch.equals(value)) {
                            messageKey = field + ".mismatch";
                            flag = false;
                        }
                    }
                    break;
                case "max":
                    if(value.length() > Integer.parseInt(validatorParts[1])) {
                        messageKey = field + ".maxLength";
                        flag = false;
                    }
                    break;
            }
        } else {
            messageKey = field + ".notFound";
            flag = false;
        }

        if(!flag) {
            errors.put(messageKey, hlpr.getMessage(Constants.ERROR_MSG_PREFIX + messageKey));
        }

        return flag;
    }
}

package barbot.utils;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * Created by Alex on 4/26/2017.
 */
@Component
public class HelperMethods {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    /**
     * Retrieves the message from the message source for the current locale
     * @param message message key to look up
     * @return message, empty string if not found
     */
    public String getMessage(String message) {
        return messageSource.getMessage(message, null, "", LocaleContextHolder.getLocale());
    }

    public String getMessage(String message, Object... args) {
        return messageSource.getMessage(message, args, "", LocaleContextHolder.getLocale());
    }

    public String generateUid() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < Constants.UID_LENGTH){
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, Constants.UID_LENGTH);
    }
}

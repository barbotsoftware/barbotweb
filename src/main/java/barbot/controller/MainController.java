package barbot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Naveen on 4/15/17.
 */
@RestController
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "<h1>Dat Way</h1>";
    }
}

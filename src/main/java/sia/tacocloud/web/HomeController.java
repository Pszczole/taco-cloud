package sia.tacocloud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
HomeController class that handles requests from home page of tacoCloud
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

}

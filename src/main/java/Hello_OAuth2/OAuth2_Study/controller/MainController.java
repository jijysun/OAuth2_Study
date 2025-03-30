package Hello_OAuth2.OAuth2_Study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/my")
    public String myPage() {
        return "myPage";
    }
}

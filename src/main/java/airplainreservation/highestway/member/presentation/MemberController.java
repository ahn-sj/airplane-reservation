package airplainreservation.highestway.member.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @GetMapping("/login")
    public String loginForm(Model model) {

        model.addAttribute("member", new MemberLoginRequest());
        return "loginForm";
    }
}

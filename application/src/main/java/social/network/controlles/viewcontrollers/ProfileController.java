package social.network.controlles.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    public void viewProfile(int id){

    }
    @GetMapping
    public void viewProfile(){
        System.out.println(1);
    }
}

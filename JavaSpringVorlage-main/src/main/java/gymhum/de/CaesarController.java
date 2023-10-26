package gymhum.de;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class CaesarController {
    @GetMapping("caesar")
    public String caesar(@RequestParam(name="activePage", required = false, defaultValue = "caesar") String activePage, Model model){
        model.addAttribute("activePage", "caesar");
        return "index.html";
    }
     @GetMapping("caesarencryption")
    public String caesarencryption(@RequestParam(name="activePage", required = false, defaultValue = "caesarencryption") String activePage, Model model){
        model.addAttribute("activePage", "caesarencryption");
        return "index.html";
    }
     @GetMapping("caesardecryption")
    public String caesardecryption(@RequestParam(name="activePage", required = false, defaultValue = "caesardecryption") String activePage, Model model){
        model.addAttribute("activePage", "caesardecryption");
        return "index.html";
    }
}

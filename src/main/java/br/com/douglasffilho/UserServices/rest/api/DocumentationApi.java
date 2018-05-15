package br.com.douglasffilho.UserServices.rest.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/documentation")
public class DocumentationApi {

    @RequestMapping("/")
    public String documentationHome() {
        return "redirect:/swagger-ui.html";
    }

}

package br.com.douglasffilho.UserServices.documentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/docs")
public class DocumentationApi {

	@RequestMapping("")
	public String documentationHome() {
		return "redirect:/swagger-ui.html";
	}

}

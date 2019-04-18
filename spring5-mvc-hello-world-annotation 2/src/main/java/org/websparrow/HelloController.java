package org.websparrow;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String greet(Model model) throws Exception {
		model.addAttribute("name", "ceren");

		return "welcome";
	}
	@RequestMapping("/hello123")
	public String foo(Model model) throws Exception {
		model.addAttribute("name", "kaan");

		return "different";
	}

}

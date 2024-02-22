package dev.kilima.training.redis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("unchecked")
@Controller
public class SpringSessionController {

	@GetMapping("/home")
	public String home(Model model, HttpSession session) {

		List<String> messages = (List<String>) session.getAttribute("REDIS_SESSION_MESSAGES");
		if (messages == null) {
			messages = new ArrayList<String>();
		}
		model.addAttribute("sessionMessages", messages);
		model.addAttribute("sessionId", session.getId());
		return "Home";
	}

	@PostMapping("/persist")
	public String persistMessage(@RequestParam("msg") String msg, HttpServletRequest request) {
		List<String> messages = (List<String>) request.getSession().getAttribute("REDIS_SESSION_MESSAGE");
		if (messages == null) {
			messages = new ArrayList<String>();
			request.getSession().setAttribute("REDIS_SESSION_MESSAGE", messages);
		}
		messages.add(msg);
		request.getSession().setAttribute("REDIS_SESSION_MESSAGE", messages);
		return "redirect:/home";
	}

	@PostMapping("/destroy")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/home";
	}

}

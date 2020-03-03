package com.mak.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mak.model.Ticket;
import com.mak.service.TicketService;
import com.mak.service.UserService;

@Controller
public class TicketController {
	private static Logger logger = Logger.getLogger(TicketController.class);

	@Autowired
	private TicketService ticketService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/")
	public String welcome(Model model) {
		model.addAttribute("description", "unsecured page !");
		return "login";

	}

	@RequestMapping("/admin")
	public void admin(ModelAndView model, Principal principal, HttpServletResponse res, HttpServletRequest request) {
		try {
			String loggedInUserName = principal.getName();
			System.out.println(loggedInUserName);
			Object loggedUser = (Object) userService.findByRole(loggedInUserName);
			if ((boolean) loggedUser.equals("A")) {
				res.sendRedirect("/user");
			} else if ((boolean) loggedUser.equals("U")) {
				res.sendRedirect("/user");
			}
			System.out.println(loggedUser.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView home(ModelMap model, Principal principal) {
		String loggedInUserName = principal.getName();
		List<Ticket> listTickets = ticketService.getAllTickets(loggedInUserName);
		return new ModelAndView("createTicket", "listTickets", listTickets);

	}

	@PostMapping(value = "/tickets")
	public ModelAndView createTicket(@Validated @ModelAttribute("ticket") Ticket ticket, BindingResult result,
			ModelAndView model, Principal principal) {
		logger.info("Ticket Create Controller");
		String loggedInUserName = principal.getName();
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		if (result.hasErrors()) {
			logger.info("Ticket Error Controller");
			return new ModelAndView("createTicket");
		}
		ticket.setTicketStatus("open");
		ticket.setCreatedBy(loggedInUserName);
		ticket.setCreatedDate(date);
		ticketService.addTicket(ticket);
		Iterable<Ticket> listTickets = ticketService.getAllTickets(loggedInUserName);
		return new ModelAndView("createTicket", "listTickets", listTickets).addObject("message",
				"Ticket are successfully added");

	}

	@RequestMapping(value = "/ticket/{id}", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute("ticket") Ticket ticket, HttpServletRequest request, Model model,
			@PathVariable("id") int ticketId) {
		ticket = ticketService.getTicket(ticketId);
		return new ModelAndView("editTicket", "ticket", ticket);

	}

	@RequestMapping(value = "/ticket/success", method = RequestMethod.GET)
	public ModelAndView saveTicket(@ModelAttribute("ticket") Ticket ticket, Model model,
			@RequestParam("ticketId") int id, @RequestParam("ticketDesc") String desc,
			@RequestParam("priority") String priority) {
		logger.info("Success Controller");
		try {
			ticket = ticketService.getTicket(id);
			ticket.setTicketDesc(desc);
			ticket.setPriority(priority);
			ticketService.addTicket(ticket);
		} catch (Exception e) {
			model.addAttribute("message", "The record is not successfully updated. Kindly try again !");
		}
		return new ModelAndView("editsuccess");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		System.out.println("Entered");
		model.addAttribute("message", "You have successfully logged off from application !");
		return "login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";

	}
}

package com.mak.service;

import java.util.List;

import com.mak.model.Ticket;

public interface TicketService {

	public List<Ticket> getAllTickets(String loggedInUserNamename);

	public Ticket getTicket(int ticketId);

	public void addTicket(Ticket ticket);

}

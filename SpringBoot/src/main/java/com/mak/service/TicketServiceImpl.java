package com.mak.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mak.dao.TicketDao;
import com.mak.model.Ticket;

@Service
public class TicketServiceImpl implements TicketService {

	private static final Logger logger = LogManager.getLogger(TicketServiceImpl.class);

	@Autowired
	TicketDao ticketDao;

	public List<Ticket> getAllTickets(String name) {
		logger.info("Find all service");
		return ticketDao.findAllTicketsByUser(name);
	}

	@Override
	public Ticket getTicket(int ticketId) {
		logger.info("Find by id service");
		return ticketDao.findOne(ticketId);
	}

	@Override
	public void addTicket(Ticket ticket) {
		logger.info("save ticket service");
		ticketDao.save(ticket);
	}

}

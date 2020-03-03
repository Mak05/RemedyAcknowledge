
package com.mak.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mak.model.Ticket;

public interface TicketDao extends CrudRepository<Ticket, Integer> {
	@Query(value = "SELECT * FROM ticket where created_by=?1", nativeQuery = true)
	List<Ticket> findAllTicketsByUser(String name);
}

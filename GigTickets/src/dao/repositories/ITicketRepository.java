package dao.repositories;

import java.util.Date;
import java.util.List;

import domain.model.History;
import domain.model.Ticket;

public class ITicketRepository extends IRepository<Ticket> {

	public List<Ticket> withName(String name);

	public List<Ticket> withLocation(String location);

	public List<Ticket> withDate(String date);

	public List<Ticket> byPrice(Int price);

	public List<Ticket> byQuantity(Int quantity);

}
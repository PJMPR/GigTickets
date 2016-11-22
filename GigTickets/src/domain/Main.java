package domain;

//  system rezerwacji biletow na koncerty

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.TicketRepository;
import domain.model.Ticket;
import domain.model.Ticket.Category;

public class Main {

	public static void main(String[] args) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			TicketRepository ticketRepository = new TicketRepository(connection);
			Ticket ticket1 = new Ticket();
			ticket1.setCategory(Category.rap);
			ticket1.setName("tede");
			ticket1.setDate("11-11-2016");
			ticket1.setLocation("Warszawa");
			ticket1.setPrice(30);
			ticket1.setQuantity(500);
			
			// sticketRepository.add(ticket1);
			
			Ticket ticket1FromDb = ticketRepository.get(0);
			System.out.println("Kategoria: " + ticket1FromDb.getCategory() 
			+ ", nazwa: " + ticket1FromDb.getName() 
			+ ", data: " + ticket1FromDb.getDate()
			+ ", lokalizacja: " + ticket1FromDb.getLocation()
			+ ", cena: " + ticket1FromDb.getPrice()
			+ ", ilosc sztuk: " + ticket1FromDb.getQuantity());
			System.out.print("End");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
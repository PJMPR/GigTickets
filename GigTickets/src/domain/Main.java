package domain;

//  system rezerwacji biletow na koncerty

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import dao.TicketRepository;
import dao.repositories.IRepositoryCatalog;
import domain.model.User;
import domain.model.Ticket;

public class Main {

	public static void main(String[] args) {

		IRepositoryCatalog catalogOf;

		List<User> user = catalogOf.User().withName("Pawel");

		// catalogOf.Dictionaries().withDictionaryName("gender");

		try {
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			TicketRepository ticketRepository = new TicketRepository(connection, null, null);
			Ticket ticket1 = new Ticket();
			ticket1.setName("tede");
			ticket1.setDate("11-11-2016");
			ticket1.setLocation("Warszawa");
			ticket1.setPrice(30);
			ticket1.setQuantity(500);

			// sticketRepository.add(ticket1);

			Ticket ticket1FromDb = ticketRepository.get(0);
			System.out.println("Nazwa: " + ticket1FromDb.getName() + ", data: " + ticket1FromDb.getDate()
					+ ", lokalizacja: " + ticket1FromDb.getLocation() + ", cena: " + ticket1FromDb.getPrice()
					+ ", ilosc sztuk: " + ticket1FromDb.getQuantity());
			System.out.print("End");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
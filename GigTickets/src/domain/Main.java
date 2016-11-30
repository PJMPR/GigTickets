package domain;

//  system rezerwacji biletow na koncerty

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import dao.repositories.IRepositoryCatalog;
import dao.uow.IUnitOfWork;
import dao.uow.RepositoryCatalog;
import dao.uow.UnitOfWork;
import domain.model.Ticket;

public class Main {

	public static void main(String[] args) {

		//IRepositoryCatalog catalogOf;

		Ticket ticket1 = new Ticket();
		ticket1.setName("tede");
		ticket1.setDate("11-11-2016");
		ticket1.setLocation("Warszawa");
		ticket1.setPrice(30);
		ticket1.setQuantity(500);
		
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			IUnitOfWork uow = new UnitOfWork(connection);
			IRepositoryCatalog catalog = new RepositoryCatalog(connection, uow);
			catalog.Ticket().add(ticket1);

			/*System.out.println("Nazwa: " + ticket1FromDb.getName() + ", data: " + ticket1FromDb.getDate()
					+ ", lokalizacja: " + ticket1FromDb.getLocation() + ", cena: " + ticket1FromDb.getPrice()
					+ ", ilosc sztuk: " + ticket1FromDb.getQuantity());
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
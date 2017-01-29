package domain;

//  system rezerwacji biletow na koncerty

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.repositories.IRepositoryCatalog;
import dao.uow.IUnitOfWork;
import dao.uow.RepositoryCatalog;
import dao.uow.UnitOfWork;
import domain.model.Gig;
import domain.model.Ticket;

public class Main {

	public static void main(String[] args) {

		Connection connection;
		 		try {
		 			connection = DriverManager.getConnection("");
		 	    	IRepositoryCatalog catalogOf = new RepositoryCatalog(connection, new UnitOfWork(connection));
		 	    	
		 	    	//List<Person> people =  catalogOf.People().withName("janek");
		 	    	
		 	    	//catalogOf.Dictionaries().withDictionaryName("gender");
		 		} catch (SQLException e) {
		 			e.printStackTrace();
		 		}
		/*
		
		Gig gig1 = new Gig();
		gig1.setCategory("Rap");
		gig1.setTitle("KEPTN");
		gig1.setDescription("Zawiera sladowe ilosci przekazu");

		Ticket ticket1 = new Ticket();
		ticket1.setName("tede");
		ticket1.setDate("11-11-2016");
		ticket1.setLocation("Warszawa");
		ticket1.setPrice(30);
		ticket1.setQuantity(500);
		ticket1.setInformation(gig1);

		try {
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			IUnitOfWork uow = new UnitOfWork(connection);
			IRepositoryCatalog catalog = new RepositoryCatalog(connection, uow);
			catalog.Ticket().add(ticket1);

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
}
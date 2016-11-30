package dao.uow;

import dao.TicketRepository;
import dao.UserRepository;
import dao.mappers.*;
import dao.repositories.*;
import dao.uow.IUnitOfWork;

import java.sql.Connection;
import java.sql.SQLException;

public class RepositoryCatalog implements IRepositoryCatalog {

	private Connection connection;
	private IUnitOfWork uow;
	private TicketMapper ticketMapper;
	private UserMapper userMapper;

	public RepositoryCatalog(Connection connection, IUnitOfWork uow) {
		super();
		this.connection = connection;
		this.uow = uow;
	}

	public ITicketRepository Ticket() {
		return new TicketRepository(connection, ticketMapper, uow);
	}


	public IUserRepository User() {
		return new UserRepository(connection, userMapper, uow);
	}

	public void save() throws SQLException {
		uow.commit();
		connection.close();
	}

}
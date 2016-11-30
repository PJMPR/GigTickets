package dao.repositories;

public interface IRepositoryCatalog {

	public IUserRepository User();

	public ITicketRepository Ticket();
}
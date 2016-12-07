package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.mappers.IMapResultSetIntoEntity;
import dao.repositories.ITicketRepository;
import domain.model.Ticket;
import dao.uow.IUnitOfWork;

public class TicketRepository extends RepositoryBase<Ticket> implements ITicketRepository {

	public TicketRepository(Connection connection, IMapResultSetIntoEntity<Ticket> mapper, IUnitOfWork uow) {
		super(connection, mapper, uow);
	}

	private PreparedStatement getName;
	// private PreparedStatement getSurname;

	@Override
	protected String createTableSql() {
		return "" + "CREATE TABLE tickets(" + "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
				+ "name varchar(50)," + "date varchar(50)," + "location varchar(50)," + "price int(50),"
				+ "quantity int(50)," + "INFORMATION_ID," + ")";
	}

	@Override
	protected String tableName() {
		return "ticket";
	}

	protected String insertSql() {
		return "INSERT INTO tickets(name, date, location, price, quantity) VALUES (?,?,?,?,?)";
	}

	protected String updateSql() {
		return "UPDATE ticket SET (name, date, location, price, quantity)=(?,?,?,?,?) WHERE id=?";
	}

	protected String getNameSql() {
		return "SELECT * FROM ticket where name = ?";
	}

	protected String getPriceSql() {
		return "SELECT * FROM ticket where price=?";
	}

	protected String getQuantitySql() {
		return "SELECT * FROM ticket where quantity = ?";
	}

	protected String getLocationSql() {
		return "SELECT * FROM ticket where location=?";
	}

	protected String getDateSql() {
		return "SELECT * FROM ticket where date=?";
	}

	protected String getInformationSql() {
		return "SELECT * FROM ticket where INFORMATION_ID=?";
	}

	@Override
	protected void setUpdate(Ticket ticket) throws SQLException {
		update.setString(1, ticket.getName());
		update.setString(2, ticket.getDate());
		update.setString(3, ticket.getLocation());
		update.setInt(4, ticket.getPrice());
		update.setInt(5, ticket.getQuantity());
		update.setObject(6, ticket.getInformation());
	}

	@Override
	protected void setInsert(Ticket ticket) throws SQLException {
		insert.setString(1, ticket.getName());
		insert.setString(2, ticket.getDate());
		insert.setString(3, ticket.getLocation());
		insert.setInt(4, ticket.getPrice());
		insert.setInt(5, ticket.getQuantity());
		insert.setObject(6, ticket.getInformation());
	}

	private List<Ticket> searchBy(String value) {
		List<Ticket> ticket = new ArrayList<>();
		try {
			getName.setString(1, value);
			ResultSet resultSet = getName.executeQuery();
			while (resultSet.next()) {
				ticket.add(mapper.map(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticket;
	}

	public List<Ticket> withName(String name) {
		return searchBy(name);
	}

	public List<Ticket> byPrice(String price) {
		return searchBy(price);
	}

	public List<Ticket> byQuantity(String quantity) {
		return searchBy(quantity);
	}

	public List<Ticket> byLocation(String location) {
		return searchBy(location);
	}

	public List<Ticket> byDate(String date) {
		return searchBy(date);
	}

	@Override
	public List<Ticket> withLocation(String location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> withDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> byPrice(Integer price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> byQuantity(Integer quantity) {
		// TODO Auto-generated method stub
		return null;
	}

}
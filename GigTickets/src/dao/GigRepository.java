package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.mappers.IMapResultSetIntoEntity;
import dao.repositories.IGigRepository;
import dao.uow.IUnitOfWork;
import domain.model.Gig;

public class GigRepository extends RepositoryBase<Gig> implements IGigRepository {

	public GigRepository(Connection connection, IMapResultSetIntoEntity<Gig> mapper, IUnitOfWork uow) {
		super(connection, mapper, uow);
	}

	private PreparedStatement getTitle;
	// private PreparedStatement getSurname;

	@Override
	protected String createTableSql() {
		return "" + "CREATE TABLE gig(" + "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
				+ "category varchar(50)," + "title varchar(50)," + "description varchar(90)" + ")";
	}

	@Override
	protected String tableName() {
		return "gig";
	}

	protected String insertSql() {
		return "INSERT INTO gig(category, title, description) VALUES (?,?,?)";
	}

	protected String updateSql() {
		return "UPDATE gig SET (category, title, description)=(?,?,?) WHERE id=?";
	}

	protected String getCategorySql() {
		return "SELECT * FROM gig where category = ?";
	}

	protected String getTitleSql() {
		return "SELECT * FROM gig where title=?";
	}

	@Override
	protected void setUpdate(Gig entity) throws SQLException {
		update.setString(1, entity.getCategory());
		update.setString(2, entity.getTitle());
		update.setString(3, entity.getDescription());

	}

	@Override
	protected void setInsert(Gig entity) throws SQLException {
		insert.setString(1, entity.getCategory());
		insert.setString(2, entity.getTitle());
		insert.setString(3, entity.getDescription());
	}

	private List<Gig> searchBy(String value) {
		List<Gig> gig = new ArrayList<>();
		try {
			getTitle.setString(1, value);
			ResultSet resultSet = getTitle.executeQuery();
			while (resultSet.next()) {
				gig.add(mapper.map(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gig;
	}

	public List<Gig> withCategory(String category) {
		return searchBy(category);
	}

	public List<Gig> withTitle(String title) {
		return searchBy(title);
	}

	public List<Gig> withDescription(String description) {
		return searchBy(description);
	}

}
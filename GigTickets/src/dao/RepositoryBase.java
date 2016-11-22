package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.mappers.IMapResultSetIntoEntity;
import dao.repositories.IRepository;
import domain.model.IHaveId;

public abstract class RepositoryBase<TEntity extends IHaveId> implements IRepository<TEntity> {

	protected Connection connection;

	protected PreparedStatement insert;
	protected PreparedStatement selectById;
	protected PreparedStatement update;
	protected PreparedStatement delete;
	protected PreparedStatement selectAll;
	
	protected IMapResultSetIntoEntity<TEntity> mapper; 

	public Connection getConnection() {
		return connection;
	}
	
	protected RepositoryBase(Connection connection,
			IMapResultSetIntoEntity<TEntity> mapper){
		this.connection = connection;
		try{
			this.mapper=mapper;
			createTableIfnotExists();
			insert = connection.prepareStatement(insertSql());
			selectById = connection.prepareStatement(selectByIdSql());
			update=connection.prepareStatement(updateSql());
			delete=connection.prepareStatement(deleteSql());
			selectAll=connection.prepareStatement(selectAllSql());
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}


	/* (non-Javadoc)
	 * @see dao.IRepository#getAll()
	 */
	public List<TEntity> getAll() {
		try {
			ResultSet rs = selectAll.executeQuery();
			List<TEntity> result = new ArrayList<TEntity>();
			while (rs.next()) {
				result.add(mapper.map(rs));
			}
			return result;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see dao.IRepository#get(int)
	 */
	public TEntity get(int personId) {
		try {
			selectById.setInt(1, personId);
			ResultSet rs = selectById.executeQuery();
			while (rs.next()) {
				return mapper.map(rs);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;

	}

	
	/* (non-Javadoc)
	 * @see dao.IRepository#update(TEntity)
	 */
	public void update(TEntity entity) {
		try {
			setUpdate(entity);
			update.setInt(3, entity.getId());
			update.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see dao.IRepository#add(TEntity)
	 */
	public void add(TEntity entity) {
		try {
			setInsert(entity);
			insert.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see dao.IRepository#delete(TEntity)
	 */
	public void delete(TEntity entity) {
		try {
			delete.setInt(1, entity.getId());
			delete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected String selectByIdSql() {
		return "SELECT * FROM "
				+ tableName()
				+ " WHERE id=?";
	}


	protected String deleteSql() {
		return "DELETE FROM "
				+ tableName()
				+ " WHERE id=?";
	}

	protected String selectAllSql() {
		return "SELECT * FROM "+tableName();
	}

	private void createTableIfnotExists() throws SQLException {
			Statement createTable = this.connection.createStatement();

			boolean tableExists = false;

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);

			while (rs.next()) {
				if (rs.getString("Table_Name").equalsIgnoreCase(tableName())) {
					tableExists = true;
					break;
				}
			}
			if (!tableExists)
				createTable.executeUpdate(createTableSql());
	}

	protected abstract String insertSql();
	protected abstract String updateSql();
	protected abstract void setUpdate(TEntity entity) throws SQLException;
	protected abstract void setInsert(TEntity entity) throws SQLException;
	protected abstract String createTableSql();
	protected abstract String tableName();
}

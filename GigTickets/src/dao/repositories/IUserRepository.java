package dao.repositories;

import java.util.List;

import domain.model.User;

public interface IUserRepository extends IRepository<User> {

	public List<User> withName(String name);
	public List<User> withSurname(String surname);

}

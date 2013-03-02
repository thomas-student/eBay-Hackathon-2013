package services;

import resources.Event;
import resources.User;

public abstract class UserServiceFacade {

	public abstract User getUserById(long id);

	public abstract boolean createNewUser(String name, long phoneNumber);

	public abstract boolean changeName(long id, String name);

}
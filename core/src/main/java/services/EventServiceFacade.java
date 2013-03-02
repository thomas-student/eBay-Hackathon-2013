package services;

import resources.Event;

public abstract class EventServiceFacade {

	public abstract Event getEventById(long id);

	public abstract boolean createNewEvent(String name, long date, String location);

	public abstract boolean changeName(long id, String name, long userId);

	public abstract boolean changeDate(long id, long date, long userId);

	public abstract boolean changeLocation(long id, String location, long userId);

	public abstract boolean removeAdmin(long id, long userId);

	public abstract boolean addAdmin(long id, long userId);

	public abstract boolean addResponse(long id, String response, long userId, boolean attending);

	public abstract boolean addPost(long id, long userId, String content, long timestamp);

}

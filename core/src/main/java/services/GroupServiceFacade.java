package services;

import resources.Event;
import resources.Group;

public abstract class GroupServiceFacade {

	public abstract Group getGroupById(long id);

	public abstract boolean createNewGroup(String name, String description, long userId);

	public abstract boolean changeName(long id, String name, long userId);

	public abstract boolean changeDescription(long id, String description, long userId);

	public abstract boolean addMember(long id, long userId);

	public abstract boolean addPost(long id, long userId, String content, long timestamp);
}

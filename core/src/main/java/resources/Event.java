package resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Event {

	private long id;
	private String name;
	private long date;
	private String location;
	private List<Response> attending;
	private List<Response> notAttending;
	private Queue<Post> posts;
	private List<User> admins;

	public Event(){
		this.attending = new ArrayList<Response>();
		this.notAttending = new ArrayList<Response>();
		this.posts = new PriorityQueue<Post>();
		this.admins = new ArrayList<User>();
	}
	
	/**
	 * @param id
	 * @param name
	 * @param date
	 * @param location
	 * @param attending
	 * @param notAttending
	 * @param discussion
	 */
	public Event(long id, String name, long date, String location){
		this();
		this.id = id;
		this.name = name;
		this.date = date;
		this.location = location;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the date
	 */
	public long getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(long date) {
		this.date = date;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the attending
	 */
	public List<Response> getAttending() {
		return attending;
	}
	/**
	 * @param attending the attending to set
	 */
	public void addAttending(Response attending) {
		this.attending.add(attending);
	}
	/**
	 * @return the notAttending
	 */
	public List<Response> getNotAttending() {
		return notAttending;
	}
	/**
	 * @param notAttending the notAttending to set
	 */
	public void addNotAttending(Response notAttending) {
		this.notAttending.add(notAttending);
	}	
	
	public List<User> getAdmins() {
		return admins;
	}
	
	public void addAdmin(User admin) {
		this.admins.add(admin);
	}
	
	/**
	 * @return the posts
	 */
	public Collection<Post> getPosts() {
		return posts;
	}
	/**
	 * @param posts the posts to set
	 */
	public void addPost(Post post) {
		this.posts.add(post);
	}
	
			
}

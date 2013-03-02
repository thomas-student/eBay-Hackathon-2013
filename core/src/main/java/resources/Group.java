package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group {

	private String name;
	private String description;
	private List<User> users;
	private Queue<Post> posts;
	private long id;
	
	public Group(){
		this.users = new ArrayList<User>();
		this.posts = new PriorityQueue<Post>();
	}
	
	/**
	 * @param name
	 * @param description
	 * @param id
	 */
	public Group(String name, String description, long id) {
		this();
		this.name = name;
		this.description = description;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void addUser(User user) {
		this.users.add(user);
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

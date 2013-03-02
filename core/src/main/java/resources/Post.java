package resources;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("rawtypes")
@XmlRootElement
public class Post implements Comparable{

	private long id;
	private String content;
	private User author;
	private long timestamp;
	
	public Post(){
		
	}
	
	/**
	 * @param id
	 * @param content
	 * @param author
	 */
	public Post(long id, String content, User author, long timestamp) {
		this.id = id;
		this.content = content;
		this.author = author;
		this.timestamp = timestamp;
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public int compareTo(Object o) {
		if(o.getClass() != Post.class)
			return 0;
		if (this.timestamp > ((Post)o).getId())
			return 1;
		else 
			return -1;
	}
	
	
}

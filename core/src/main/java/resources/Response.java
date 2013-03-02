package resources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {

	public User user;
	public String response;
	public long id;
	
	public Response(){
		
	}
	
	/**
	 * @param user
	 * @param response
	 * @param id
	 */
	public Response(User user, String response, long id) {
		super();
		this.user = user;
		this.response = response;
		this.id = id;
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
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
	
	
}
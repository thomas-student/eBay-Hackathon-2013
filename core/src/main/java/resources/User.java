package resources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	private long phoneNumber;
	private long id;
	private String name;
	
	public User(){
		
	}
	
	/**
	 * @param phoneNumber
	 * @param id
	 * @param name
	 */
	public User(long phoneNumber, long id, String name) {
		super();
		this.phoneNumber = phoneNumber;
		this.id = id;
		this.name = name;
	}
	/**
	 * @return the phoneNumber
	 */
	public long getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	
	
}

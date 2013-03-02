package resources;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Attendance {

	private List<Response> attending;
	private List<Response> notAttending;
	
	
	public Attendance(){
		attending  = new ArrayList<Response>();
		notAttending  = new ArrayList<Response>();
	}
	
	public void addAttendingResponse(Response r){
		attending.add(r);
	}
	
	public void addNotAttendingResponse(Response r){
		notAttending.add(r);
	}
}

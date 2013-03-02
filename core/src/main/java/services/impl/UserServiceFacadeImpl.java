package services.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.math.BigInteger;

import resources.User;
import services.UserServiceFacade;

public class UserServiceFacadeImpl extends UserServiceFacade {

	@Override
	public User getUserById(long id) {
		Connection conn = AWSConnector.getConnection();
		try{
		ResultSet orig = conn.prepareStatement("SELECT * FROM users WHERE id="+id).executeQuery();
		if(orig.next())
			{
			return new User(((BigInteger)orig.getObject("number")).longValue(), ((BigInteger)(orig.getObject("id"))).longValue(), orig.getString("name"));
			}
		else
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	
	@Override
	public boolean createNewUser(String name, long phoneNumber) {
		Connection conn = AWSConnector.getConnection();
		try{
		ResultSet orig = conn.prepareStatement("SELECT * FROM users WHERE number="+phoneNumber).executeQuery();
		if(orig.next())
			return false;
		else
			return conn.prepareStatement("INSERT INTO meet_db.users	(name, number) VALUES  ('"+name+"', "+phoneNumber+" )").execute();
					}
		catch(Exception e){
			return false;
		}
	}
	
	@Override
	public boolean changeName(long id, String name) {
		Connection conn = AWSConnector.getConnection();
		try{
		return conn.prepareStatement("UPDATE users SET name = '"+name+"' WHERE id="+id).execute();
		}
		catch(Exception e){
			return false;
		}
	}
}

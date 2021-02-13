package model;

import java.util.ArrayList;

public class Classroom {
	
	ArrayList<UserAccount> userAccounts;
	
	public Classroom () {
		userAccounts = new ArrayList<UserAccount> ();
	}

	public ArrayList<UserAccount> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(ArrayList<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}
	
	public void addUserAccount (String username, String password, String photoDirectory, String gender, String career, String birthday, String browser) {
		
		UserAccount user = new UserAccount (username, password, photoDirectory, gender, career, birthday, browser);
		userAccounts.add(user);
		
		
	}
	
}

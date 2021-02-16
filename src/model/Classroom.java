package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Classroom {
	
	private static final String sep = ";";
	
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
	public void importData(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		while(line!=null){
			String[] parts = line.split(";");
			addUserAccount(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],parts[6]);
			line = br.readLine();
	    }
	    br.close();
	}
	public void exportData(String fileName) throws FileNotFoundException{
	    PrintWriter pw = new PrintWriter(fileName);

	    for(int i = 0; i < userAccounts.size(); i++){
	      UserAccount myUser = userAccounts.get(i);
	      pw.println(myUser.getUsername() + sep + myUser.getPassword() + sep + myUser.getPhotoDirectory() + sep + myUser.getGender() + sep + myUser.getCareer() + sep + myUser.getBirthday() + sep + myUser.getBrowser());
	    }

	    pw.close();
	  }
	
}

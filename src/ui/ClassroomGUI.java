package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;


import model.Classroom;
import model.UserAccount;

public class ClassroomGUI {
	

    @FXML
    private Label labUsername;

    @FXML
    private TableView<UserAccount> tvAccountsList;

    @FXML
    private TableColumn<UserAccount, String> tcUsername;

    @FXML
    private TableColumn<UserAccount, String> tcGender;

    @FXML
    private TableColumn<UserAccount, String> tcCareer;

    @FXML
    private TableColumn<UserAccount, String> tcBirthday;

    @FXML
    private TableColumn<UserAccount, String> tcBrowser;

    @FXML
    private ImageView imgProfilePhoto;
	
	@FXML
    private Pane mainPane1;
	
    @FXML
    private AnchorPane anchorPane2;
    
    @FXML
    private AnchorPane anchorPane3;
    
    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtUser;
    
    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhotoDirectory;

    @FXML
    private RadioButton maleBtn;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton femaleBtn;

    @FXML
    private RadioButton otherBtn;

    @FXML
    private CheckBox chkS;

    @FXML
    private CheckBox chkT;

    @FXML
    private CheckBox chkI;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> favoriteBrowser;
    
    private Classroom classroom;
    
    public ClassroomGUI (Classroom cr) {
    	classroom = cr;
    }
    
    private void initializeTableView() {
    	ObservableList<UserAccount> observableList;
    	observableList = FXCollections.observableArrayList(classroom.getUserAccounts());
    	
    	tvAccountsList.setItems(observableList);
    	tcUsername.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("username")); 
    	tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("gender"));
    	tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("career")); 
    	tcBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("birthday")); 
    	tcBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("browser")); 
    }

    @FXML
    void signUpButton(ActionEvent event) throws IOException {
    	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent addRegisterPane = fxmlLoader.load();
    	
		mainPane1.getChildren().setAll(addRegisterPane);
		
		favoriteBrowser.setValue("Opera");
		favoriteBrowser.getItems().addAll("Chrome", "Opera", "Firefox", "Brave", "Safari", "Edge");
		
		
    }

    @FXML
    void logInButton(ActionEvent event) throws IOException {
    	
    	if (classroom.getUserAccounts().size() == 0) {
    		
    		Alert alert = new Alert(Alert.AlertType.ERROR);
 		    alert.setHeaderText(null);
 		    alert.setTitle("Error");
 		    alert.setContentText("There are 0 users registered. Please create an account");
 		    alert.showAndWait();
 		    
    	}else {
		 
			 for(int i = 0; i < classroom.getUserAccounts().size();i++) {
				 
				 if(txtUser.getText().equals(classroom.getUserAccounts().get(i).getUsername())&& txtPass.getText().equals(classroom.getUserAccounts().get(i).getPassword())) {
					 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
						
						fxmlLoader.setController(this);    	
						Parent addRegisterPane = fxmlLoader.load();
				    	
						mainPane1.getChildren().setAll(addRegisterPane);
						initializeTableView();
						InputStream stream = new FileInputStream(txtPhotoDirectory.getText());
						Image img = new Image (stream);
						imgProfilePhoto.setImage(img);
						imgProfilePhoto.setFitWidth(75);
						imgProfilePhoto.setPreserveRatio(true);
						labUsername.setText(txtUser.getText());
				 }else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
		 		    alert.setHeaderText(null);
		 		    alert.setTitle("Log in incorrect");
		 		    alert.setContentText("The username and password given are incorrect");
		 		    alert.showAndWait();
				 }
			 
		    }
			 
    	}

    }
    
    @FXML
    void createAccountButton(ActionEvent event) {
    	if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty() || (maleBtn.isSelected() == false && femaleBtn.isSelected() == false && otherBtn.isSelected() == false) || (chkS.isSelected() == false && chkT.isSelected() == false && chkI.isSelected() == false) || datePicker.getValue().toString().equals(null) || favoriteBrowser.getSelectionModel().isEmpty()) {
    		
    		Alert alert = new Alert(Alert.AlertType.ERROR);
 		    alert.setHeaderText(null);
 		    alert.setTitle("Validation Error");
 		    alert.setContentText("You must fill each field in the form.");
 		    alert.showAndWait();
 		    
    	}else {
    		
    		String gender = "";
    		String career = "";
    		if (maleBtn.isSelected())
    			gender = "Male";
    		else if (femaleBtn.isSelected())
    			gender = "Female";
    		else
    			gender = "Other";
    		
    		if (chkS.isSelected())
    			career = "Software Engineering - ";
    		if (chkT.isSelected())
    			career += "Telematic Engineering - ";
    		if (chkI.isSelected())
    			career += "Industrial Engineering";
    		
    		classroom.addUserAccount(txtUsername.getText(), txtPassword.getText(), txtPhotoDirectory.getText(), gender, career, datePicker.getValue().toString(), favoriteBrowser.getValue().toString());
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Account created");
    		alert.setHeaderText(null);
    		alert.setContentText("The new account has been created. Now sign in.");

    		alert.showAndWait();
    	}
    }
    

    @FXML
    void showFileChooser(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        
        if(file != null) {
        	txtPhotoDirectory.setText(file.getPath());
        }
    }

    @FXML
    void signInButton(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent addRegisterPane = fxmlLoader.load();
    	
		anchorPane2.getChildren().setAll(addRegisterPane);
    }
    @FXML
    void logOutButton(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent addRegisterPane = fxmlLoader.load();
    	
		anchorPane3.getChildren().setAll(addRegisterPane);
    }

}

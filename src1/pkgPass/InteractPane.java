package pkgPass;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class InteractPane extends VBox{

	//Labels
	private Label aboveLbl;
	private Label belowLbl;
	private Label bottomLbl;
	
	//Text Field
	//private TextField passTF;
	private PasswordField passTF;
	
	//Password to be checked
	private String searchString;
	
	//Number of found passwords
	String fmtPass; //number formated for commas
	private int numExactPass;
	private int numContainsPass;
	
	//Button 
	private Button checkBtn; 
	
	/******************************************************************
	 * Constructor that builds the Pane
	 *****************************************************************/
	public InteractPane(){
		setPadding(new Insets(0, 25, 10, 10));
		setSpacing(25);
		setAlignment(Pos.CENTER);
		
		//initializing variables and fields
		
		// New back end for searching
        String basePath = "D:/full_sorted/output/";
        Backend b = new Backend(basePath);  
        
		searchString = "Password123";
		numExactPass = 0;
		numContainsPass = 0;
		
		aboveLbl = new Label("Enter a Password:");
		aboveLbl.setTextFill(Color.web("#ffffff"));
		aboveLbl.setStyle("-fx-font: 24 arial;");
		
		belowLbl = new Label();
		belowLbl.setTextFill(Color.web("#ffffff"));
		belowLbl.setStyle("-fx-font: 24 arial;");
		
		bottomLbl = new Label();
		bottomLbl.setTextFill(Color.web("#ffffff"));
		bottomLbl.setStyle("-fx-font: 24 arial;");
		
		checkBtn = new Button("Check Password");
		checkBtn.setDefaultButton(true);
		
		//passTF = new TextField();
		passTF = new PasswordField();
		passTF.setMaxWidth(200);
		passTF.setPromptText("password");
		
		Alert passError = new Alert(AlertType.ERROR);
		
		getChildren().addAll(aboveLbl,passTF, checkBtn, belowLbl,bottomLbl);
		
		//action listener
		checkBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        
		    	//get password
		    	searchString = passTF.getText();
		        
		    	//check validity of password
		    	if(searchString.length() < 3 ){
		    		passError.setContentText("Password must contain at least 3 characters.");
		    		passError.showAndWait();
		    	}
		    	else if(searchString.contains(" ")){
		    		passError.setContentText("Password cannot contain spaces.");
		    		passError.showAndWait();
		    	}	
		    	else{
		    		//search
		    		try{
		    			numExactPass = b.search(searchString);
		    			//numContainsPass = b.contains(searchString);
		    		} catch(Exception exc) {
		    			passError.setContentText("Error Opening File.");
			    		passError.showAndWait();
		            }
		    		
			        //Update Exact
		    		fmtPass = String.format("%,d", numExactPass);
		    		if (numExactPass <= 0)
		    			belowLbl.setText("There were no matches for this password.");
		    		else if (numExactPass == 1)
		    			belowLbl.setText("There was 1 other site that contained this password.");
		    		else
			        belowLbl.setText("We Found "+ fmtPass + 
								 " other sites with the same password.");
		    		
//		    		//Update Contains
//		    		if (numContainsPass <= 0)
//		    			bottomLbl.setText("There were no passwords containing that string");
//		    		else if (numContainsPass == 1)
//		    			bottomLbl.setText("There was 1 other site that contained this password");
//		    		else
//			        bottomLbl.setText("We Found "+ numContainsPass + 
//								 " other sites with a similar password");
		    	}
		    }
		});
	}
}
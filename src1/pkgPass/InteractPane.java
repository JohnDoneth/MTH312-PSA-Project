package pkgPass;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class InteractPane extends VBox{

	//Labels
	private Label aboveLbl;
	private Label belowLbl;
	
	//Text Field
	private TextField passTF;
	
	//Password to be checked
	private String passChk;
	
	//Number of found passwords
	private int numPass;
	
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
		passChk = "Password123";
		numPass = 0;
		
		aboveLbl = new Label("Enter a Password:");
		belowLbl = new Label("We Found "+getNumPass(passChk) + 
							 " other users with the same password");
		checkBtn = new Button("Check Password");
		
		passTF = new TextField();
		passTF.setPromptText("password");
		
		Alert passError = new Alert(AlertType.ERROR);
		
		
		getChildren().addAll(aboveLbl,passTF, checkBtn, belowLbl);
		
		//action listener
		checkBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        
		    	//get password
		    	passChk = passTF.getText();
		        
		    	//check validity of password
		    	if(passChk.length() < 3 ){
		    		passError.setContentText("Password must contain at least 3 characters");
		    		passError.showAndWait();
		    	}
		    	else if(passChk.contains(" ")){
		    		passError.setContentText("Password cannot contain spaces");
		    		passError.showAndWait();
		    	}	
		    	else{
		        //search
		    	numPass = getNumPass(passTF.getText());
		        checkBtn.setText(passTF.getText());
		        
		        
		        //Update Findings
		        belowLbl.setText("We Found "+ numPass + 
							 " other users with the same password");
		    	}
		    }
		});
		
	}
	


	//Returns the number of matched passwords
	private int getNumPass(String str){
		
		int numPass = 0;
		if (str.length() == 3)
			numPass = 1;
		
		
			//FIXME Connect to Corbin's search here.
		return numPass;
	}
	


}
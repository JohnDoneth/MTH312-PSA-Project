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
import javafx.scene.paint.Color;

public class InteractPane extends VBox{

	//Labels
	private Label aboveLbl;
	private Label belowLbl;
	
	//Text Field
	private TextField passTF;
	
	//Password to be checked
	private String searchString;
	
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
		
		// New backend for searching
        String basePath = "D:/full_counted/output/";
        Backend b = new Backend(basePath);  
		
        
		searchString = "Password123";
		numPass = 0;
		
		aboveLbl = new Label("Enter a Password:");
		aboveLbl.setTextFill(Color.web("#ffffff"));
		aboveLbl.setStyle("-fx-font: 24 arial;");
		
		belowLbl = new Label();
		belowLbl.setTextFill(Color.web("#ffffff"));
		belowLbl.setStyle("-fx-font: 24 arial;");
		
		
		checkBtn = new Button("Check Password");
		
		passTF = new TextField();
		passTF.setMaxWidth(200);
		passTF.setPromptText("password");
		
		Alert passError = new Alert(AlertType.ERROR);
		
		getChildren().addAll(aboveLbl,passTF, checkBtn, belowLbl);
		
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
		    			numPass = b.search(searchString);;
		    		} catch(Exception exc) {
		    			passError.setContentText("Error Opening File.");
			    		passError.showAndWait();
		            }
		    		
			        //Update Findings
		    		if (numPass <= 0)
		    			belowLbl.setText("There were no EXACT matches for this password");
		    		else if (numPass == 1)
		    			belowLbl.setText("There was 1 other site that contained this password");
		    		else
			        belowLbl.setText("We Found "+ numPass + 
								 " other sites with the same password");
		    	}
		    }
		});
		
	}
}
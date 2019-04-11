package pkgPass;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
		aboveLbl = new Label("Enter a Password:");
		belowLbl = new Label("We Found "+getNumPass(passChk) + 
							 " other users with the same password");
		checkBtn = new Button("Check Password");
		
		passTF = new TextField();
		passTF.setPromptText("password");
		
		getChildren().addAll(aboveLbl,passTF, checkBtn, belowLbl);
		
	}
	


	//Returns the number of matched passwords
	private int getNumPass(String str){
		
		int numPass = 0;
			//FIXME Connect to Corbin's search here.
		return numPass;
	}
	


}
package pkgPass;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class InteractPane extends VBox{

    //Labels
    private Label aboveLbl;
    private Label belowLbl;
    
    //Text Field
    private TextField passTF;
    
    /******************************************************************
     * Constructor that builds the Pane
     *****************************************************************/
    public InteractPane(){
        setPadding(new Insets(0, 25, 10, 10));
        setSpacing(25);
        setAlignment(Pos.CENTER);

        // New backend for searching
        String basePath = "../passwords/full_counted/output/";
        Backend b = new Backend(basePath);  

        // To search:
//        b.search(searchSting);
        try {
            System.out.println("Restult: " + b.search("Helena21"));
        } catch(Exception e) {
            System.out.println("error opening file");
        }
        
        aboveLbl = new Label("Enter a Password");
        belowLbl = new Label("We Found 32 other users with the same password");
    
        passTF = new TextField();
        
        
        getChildren().addAll(aboveLbl,passTF, belowLbl);
        
    }
    
}

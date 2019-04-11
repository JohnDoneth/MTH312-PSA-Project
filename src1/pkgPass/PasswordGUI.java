package pkgPass;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class PasswordGUI extends Application{

    
    /******************************************************************
     * The Main Method
     * @param args The command line
     *****************************************************************/
    public static void main (String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Setting title
        primaryStage.setTitle("Unique Password Identifier");
        
        //Creating the necessary panes
        BorderPane  mainPn = new BorderPane();
        InteractPane InteractPn = new InteractPane();
        
        //creating the MenuBar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem newItm = new MenuItem("New");
        
        
        //Setting up the menu bar
        fileMenu.getItems().addAll(newItm);
        menuBar.getMenus().add(fileMenu);
        
        //Setting up Main Pane
        mainPn.setTop(menuBar);
        mainPn.setCenter(InteractPn);
        mainPn.setId("pane");
        
        //Setting up stage
        Scene mainScene = new Scene(mainPn, 800, 600);
        BackgroundImage myBI= new BackgroundImage(new Image("background_image.jpg"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        //then you set to your node
        mainPn.setBackground(new Background(myBI));
        
        
        primaryStage.setScene(mainScene);
        primaryStage.setHeight(500);
        primaryStage.setWidth(900);
        primaryStage.show();
        


        //Ensure system close when the red X is hit
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        
    }

    
}


    

import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class JavaFXTemplate extends Application {
	
	/*Data Members*/
	HashMap<String, Scene> sceneMap;
	TextField portTxtFld;
	Button onBtn, offBtn;
	ImageView sceneOne;
	String portNum;
	Server serverConnection;
	ListView<String> clientsConctd, listItems2;
	Label resultsLabl;
	Label numClient;
	
	
	Label Player;
	Label player1stBox;
	Label Player2ndBox;
	Label Player3rdBox;
	
	
	Label Banker;
	Label Banker1stBox;
	Label Banker2ndBox;
	Label Banker3rdBox;
	
	
	

	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to Baccarat Server");
		
		/*
		 * Data members
		 */
		sceneMap = new HashMap<String, Scene>();
		portTxtFld = new TextField();
		onBtn = new Button("Start Server!");
		offBtn = new Button();
		sceneOne = new ImageView();
		resultsLabl = new Label(" ~*~Game Status~*~ ");
		offBtn = new Button("Shut Down");
		numClient = new Label("Number of clients connected: 0");
		
	
		numClient.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 20));
		numClient.setTextFill(Color.PURPLE);
		
		
		resultsLabl.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 25));
		resultsLabl.setTextFill(Color.SADDLEBROWN);
		
		offBtn.setMaxSize(200, 500);
		offBtn.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 20));
		offBtn.setTextFill(Color.DARKGREY);
		offBtn.setStyle("-fx-background-color: #522700; -fx-border-width: 2px;");
	
		

		
		/*
		 * Change to dashBoardScene
		 */
		 onBtn.setOnAction(e -> {primaryStage.setScene(sceneMap.get("dashBoard"));
		 	primaryStage.setTitle("This is the Baccarat Server");
		 	portNum = portTxtFld.getText();
		 	
		 	// data means passing the string from server to application thread
		 	// platform.runLater means from app thread grab the the list and 
		 	// add the string to the list.
		 	serverConnection = new Server(portNum, (data -> {
		 		Platform.runLater(()->{
		 			clientsConctd.getItems().add(data.toString());
		 			
		 			System.out.println("Port Number is: " + portNum);
		 			portTxtFld.setText("");
		 			
		 			
		 			
		 		});
		 	}), 
		 			
		 			data2-> {
		 		Platform.runLater(()->{
		 			numClient.setText(data2.toString());
		 		});
		 		
		 	});
		 			
		 			
//		 	data3-> {
//		 		Platform.runLater(()->{
//		 			
//		 			
//		 		});
		 		
//		 	});
		 	
		 	 
		 });
		 
		 
		 
		 // off button set on action
		 offBtn.setOnAction(e -> {
			//System.out.println("In server close button");
			System.exit(0);	
		 });
		
		
		/*
		 * fill in sceneMap
		 */
		 
		clientsConctd = new ListView<String>();
		listItems2 = new ListView<String>();
		
		
		
		
		sceneMap.put("welcmScn", createWelcomeScene());
		sceneMap.put("dashBoard", createDashBoardScene());
		sceneMap.put("endScene", createEndScene());
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
				
		
//		Scene scene = new Scene(new VBox(), 700,700);
		primaryStage.setScene(sceneMap.get("welcmScn"));
		primaryStage.show();
	}
	
	
	
	private Scene createWelcomeScene() {
		
		/* pane is the root node of the scene here */
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-font-family: serif;");
		pane.setPadding(new Insets(100));
		
		Image img1 = new Image("firstScene.png");
		

		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        pane.setBackground(new Background(new BackgroundImage(img1, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,BackgroundPosition.CENTER,bSize)));
		
       

		onBtn.setMaxSize(200, 500);
	    onBtn.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
	    onBtn.setTextFill(Color.GREEN);
	    onBtn.setStyle("-fx-background-color: #F4EF21; -fx-border-color: yellow; -fx-border-width: 2px;");
        
	    portTxtFld.setMaxWidth(70);
	    portTxtFld.setMaxHeight(40);
	    
	    VBox bottomBox = new VBox(30, portTxtFld, onBtn);
        bottomBox.setAlignment(Pos.CENTER);
        
        pane.setBottom(bottomBox);
		return new Scene(pane, 900, 800);
	}
	
	private Scene createDashBoardScene() {
		// update the label for number of clients connected 
		
//		BaccaratInfo info = new BaccaratInfo();
//		numClientsStr = info.getClientCount();
		
		
		// represent it on the label
		//numClient.setText("Number of clients connected " + numClientsStr);
		
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-font-family: serif;");
		pane.setPadding(new Insets(100));
		
		
		
		Image img1 = new Image("dashBoard.png");
		

		
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        pane.setBackground(new Background(new BackgroundImage(img1, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,BackgroundPosition.CENTER,bSize)));
		
        
        
        // increase size 
        VBox clientTotal = new VBox(25, numClient);
        clientTotal.setAlignment(Pos.CENTER);
      
       
        
        VBox resultBox = new VBox(10, resultsLabl);
        resultBox.setAlignment(Pos.CENTER);
        
        VBox ofBox = new VBox(40, offBtn);
        ofBox.setAlignment(Pos.CENTER);
        
	    VBox bottomBox = new VBox(20, clientsConctd, ofBox);
        bottomBox.setAlignment(Pos.CENTER);
        
        pane.setTop(clientTotal);
        
        pane.setCenter(resultsLabl);
        pane.setBottom(bottomBox);
		return new Scene(pane, 900, 800);
	}

	private Scene createEndScene() {
		// TODO Auto-generated method stub
		return null;
	}

	


}

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
//import projectThreeServer.Server;
import javafx.stage.WindowEvent;

public class JavaFXTemplate extends Application {
	
	/*
	 * Data members
	 */
	HashMap<String, Scene> sceneMap;
	TextField portTxtFld;
	TextField ipField;
	Button startBtn;
	ImageView sceneOne;
	String portNum;
	String ipAddress;
	TextField bidTxtFld;
	Button playerBtn;
	Button bankerBtn;
	Button tieBtn;
	Button letsGo;
	String checkSelect;
	String bidStr;
	ListView<String> listItems2;
	Client clientConnection;
	Button offBtn;
	Button newGame;
	boolean anotherGame;
	// card buttons
//	ImageView pCard1, pCard2, pCard3, bCard1, bCard2, bCard3;
	
	ArrayList<String> temp;
	
	// arraylist for storing cards;
	String pCardStr1;
	String pCardStr2;
	String bCardStr1;
	String bCardStr2;
	
	
	//constant values java style
	static final int picHeight = 275;
	static final int picWidth = 250;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Welcome to Baccarat Game");
		
		/*
		 * Data members
		 */
		sceneMap = new HashMap<String, Scene>();
		portTxtFld = new TextField();
		ipField = new TextField();
		startBtn = new Button("Start Game!");
		sceneOne = new ImageView();
		bidTxtFld = new TextField();
		playerBtn = new Button("Player");
		bankerBtn = new Button("Banker");
		tieBtn = new Button("Tie");
		letsGo = new Button("Lets Go!");
		portNum = "";
		ipAddress = "";
		checkSelect = "";
		bidStr = "";
		newGame = new Button("New Game");
		offBtn = new Button("Shut Down");
//		pCard1 = new ImageView();
//		pCard2 = new ImageView();
//		pCard3 = new ImageView();
//		bCard1 = new ImageView();
//		bCard2 = new ImageView();
//		bCard3 = new ImageView();
		pCardStr1 = "";
		pCardStr2 = "";
		bCardStr1 = "";
		bCardStr2 = "";
		temp = new ArrayList<>();
		
	
		//pCard1, pCard2, pCard3, bCard1, bCard2, bCard3;
		
		
		/*
		 * Change to select Scene
		 */
		 startBtn.setOnAction(e -> {
			portNum = portTxtFld.getText();
			ipAddress = ipField.getText();
		 	if (portNum != "" && ipAddress != "") {
				 clientConnection = new Client(portNum, ipAddress, (data->{
				 		Platform.runLater(()->{listItems2.getItems().add(data.toString());
				 		});
				 }), (data2-> {Platform.runLater(()->{temp.add(data2.toString());});})
				 
						 );// client conn ends here
				 
	            clientConnection.start();
		 		primaryStage.setScene(sceneMap.get("selectScn"));
			 	System.out.println("port Number is : " + portNum);
			 	//System.out.println("ip address is : " + temp.get(0));
			 	portTxtFld.setText("");
			 	ipField.setText("");
		 	}
	
		 });
		 
		 
		 
		 
		 // how to set action on new game
		 
		 
		 
		 
		 // what it means by "is the client playing another hand"
		 
		 // 
		 
		 
		 
		 
		 
		 newGame.setOnAction( e ->{
			 primaryStage.setScene(sceneMap.get("selectScn"));
			 
			 
		 });
		 
		 // style offBtn
		 offBtn.setMaxSize(200, 500);
		 offBtn.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 20));
		 offBtn.setTextFill(Color.DARKGREY);
		 offBtn.setStyle("-fx-background-color: #522700; -fx-border-width: 2px;");
		 
		 newGame.setMaxSize(200, 500);
		 newGame.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 20));
		 newGame.setTextFill(Color.DARKGREY);
		 newGame.setStyle("-fx-background-color: #522700; -fx-border-width: 2px;");
		 
		 /*
		  *  Change to Game Scene
		  */
		 playerBtn.setOnAction(e -> {checkSelect = "Player";});
		 bankerBtn.setOnAction(e -> {checkSelect = "Banker";});
		 tieBtn.setOnAction(e -> {checkSelect = "Tie";});
		 listItems2 = new ListView<String>();
		 
		 letsGo.setOnAction(e -> {
			bidStr = bidTxtFld.getText();
			if ((checkSelect == "Player" || checkSelect == "Banker" || checkSelect == "Tie") && (bidStr != "")) {
				primaryStage.setScene(sceneMap.get("gameScene"));
			}
			 // Send information to server about what the client selected.
			 if (checkSelect == "Player") {
				 clientConnection.sendChoice("Player", bidStr);
			 }
		     if (checkSelect == "Banker") {
		    	 System.out.println("Inside javafx selecting banker");
		    	 clientConnection.sendChoice("Banker", bidStr);
		     }
		     if (checkSelect == "Tie") {
	   	         clientConnection.sendChoice("Tie", bidStr);
	   	     }
		     
		 });
		 
		 System.out.println("Player selected: " + checkSelect);
		
         
	 
		
		/*
		 * fill in sceneMap
		 */
		
		 
		 //offBtn action
		 // off button set on action
		 // off button set on action
		 offBtn.setOnAction(e -> {
			System.out.println("In client close button");
			System.exit(0);	
		 });
	    
		sceneMap.put("welcmScen", createWelcomeScene());
		sceneMap.put("selectScn", createSelectScene());
		sceneMap.put("gameScene", createGameScene());
		
		
				
//		Scene scene = new Scene(new VBox(), 700,700);
		primaryStage.setScene(sceneMap.get("welcmScen"));
		primaryStage.show();
	}

	private Scene createWelcomeScene() {
		/* pane is the root node of the scene here */
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-font-family: serif;");
		pane.setPadding(new Insets(100));
		
		Image img1 = new Image("clientWelcom.png");
		

		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        pane.setBackground(new Background(new BackgroundImage(img1, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,BackgroundPosition.CENTER,bSize)));
		
       

        startBtn.setMaxSize(200, 500);
        startBtn.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
        startBtn.setTextFill(Color.GREEN);
        startBtn.setStyle("-fx-background-color: #F4EF21; -fx-border-color: yellow; -fx-border-width: 2px;");
        
	    portTxtFld.setMaxWidth(70);
	    portTxtFld.setMaxHeight(40);
	    
	    ipField.setMaxWidth(100);
	    ipField.setMaxHeight(40);
	    
	    VBox txtFieldBox = new VBox(80, portTxtFld, ipField);
	    txtFieldBox.setAlignment(Pos.CENTER);
	    
	    VBox bottomBox = new VBox(20, txtFieldBox, startBtn);
        bottomBox.setAlignment(Pos.CENTER);
        
        pane.setBottom(bottomBox);
		return new Scene(pane, 900, 800);
	}

	private Scene createSelectScene() {
		/* pane is the root node of the scene here */
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-font-family: serif;");
		pane.setPadding(new Insets(100));
		
		Image img1 = new Image("selectScreen.png");
		

		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        pane.setBackground(new Background(new BackgroundImage(img1, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,BackgroundPosition.CENTER,bSize)));
		
        
        playerBtn.setMinWidth(150);
        playerBtn.setMaxWidth(150);
        playerBtn.setMinHeight(150);
        playerBtn.setMaxHeight(150);
        playerBtn.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
        playerBtn.setStyle("-fx-text-fill: black;" +
							"-fx-background-color: #bdda57;");
       
        bankerBtn.setMinWidth(150);
        bankerBtn.setMaxWidth(150);
        bankerBtn.setMinHeight(150);
        bankerBtn.setMaxHeight(150);
        bankerBtn.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
        bankerBtn.setStyle("-fx-text-fill: black;" +
							"-fx-background-color: #DA5B6E;");

        
        tieBtn.setMinWidth(150);
        tieBtn.setMaxWidth(150);
        tieBtn.setMinHeight(150);
        tieBtn.setMaxHeight(150);
        tieBtn.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
        tieBtn.setStyle("-fx-text-fill: black;" +
							"-fx-background-color: #fddde6;");

       
		bidTxtFld.setMaxWidth(150);
		bidTxtFld.setMaxHeight(40);
		
		
		letsGo.setMaxSize(200, 500);
		letsGo.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		letsGo.setTextFill(Color.GREEN);
		letsGo.setStyle("-fx-background-color: #F4EF21; -fx-border-color: yellow; -fx-border-width: 2px;");
	        
		
	    VBox txtFieldBox = new VBox(bidTxtFld);
	    txtFieldBox.setAlignment(Pos.CENTER);
	    
	    HBox btnBox = new HBox(100, playerBtn, bankerBtn, tieBtn);
	    
	    VBox bottomBox = new VBox(320, txtFieldBox, btnBox);
        bottomBox.setAlignment(Pos.CENTER);
        

        pane.setCenter(bottomBox);
        
        HBox btnBox1 = new HBox(letsGo);
        btnBox1.setAlignment(Pos.BOTTOM_CENTER);
        
        pane.setBottom(btnBox1);
		return new Scene(pane, 900, 800);
	}
	
	private Scene createGameScene() {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-font-family: serif;");
		pane.setPadding(new Insets(100));
        Image img1 = new Image("clientBoard.png");
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        pane.setBackground(new Background(new BackgroundImage(img1, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,BackgroundPosition.CENTER,bSize)));
		
        /*Setting up cards images*/
//        Image pic = new Image("cozmo.jpg");
//		ImageView v = new ImageView(pic);
//		v.setFitHeight(picHeight);
//		v.setFitWidth(picWidth);
//		v.setPreserveRatio(true);
//        
        
        HBox ofBox = new HBox(40, newGame, offBtn);
        ofBox.setAlignment(Pos.CENTER);
        
	    
        
        
	    VBox bottomBox = new VBox(20, listItems2, ofBox);
        bottomBox.setAlignment(Pos.CENTER);
        pane.setCenter(bottomBox);
       
		return new Scene(pane, 900, 800);
	}
	
	
	

}

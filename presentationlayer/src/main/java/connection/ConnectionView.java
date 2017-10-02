package connection;

import base.View;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class ConnectionView extends View<ConnectionModel> {

    public ConnectionView(Stage stage, ConnectionModel model){
        super(stage, model);
    }
	
 /**
 *  @author Vanessa Cajochen
 *  */

    public Scene create_GUI(){
        	BorderPane root = new BorderPane();
		Scene scene = new Scene(root,450,400);
		GridPane gp = new GridPane();
		FlowPane fp = new FlowPane();
		
		// Creating and labeling 3 buttons
		Button startS = new Button("Start Server");
		Button joinS = new Button("Join Server");
		Button help = new Button("Help");
		
		// Set size for the buttons and FlowPane
		startS.setPrefSize(150, 30);
		joinS.setPrefSize(150, 30);
		help.setPrefSize(150, 30);		
		fp.setPrefSize(450, 100);
		
		// Asign FlowPane and GridPane to BorderPane
		root.setTop(fp);
		root.setCenter(gp);
		
		// Creating 3 columns
		for (int i = 0; i<3;i++){
			ColumnConstraints column = new ColumnConstraints(150);
			gp.getColumnConstraints().add(column);
					}
		
		// Creating 8 rows
		for (int i = 0; i<8;i++){
			RowConstraints row = new RowConstraints(30);
			gp.getRowConstraints().add(row);			
		}
		
		// Asign column and row to buttons
		gp.setConstraints(startS, 1, 1);
		gp.setConstraints(joinS, 1, 3);
		gp.setConstraints(help, 1, 5);
		
		gp.getChildren().addAll(joinS, startS, help);
		stage.setTitle("Connection");
		stage.setScene(scene);

        return scene;
    }
    
    public Scene create_Dialog(){
		Stage secondStage = new Stage();
		
		secondStage.initOwner(stage);
		secondStage.initModality(Modality.WINDOW_MODAL);					
		
		BorderPane root2 = new BorderPane();
		Scene scene2 = new Scene(root2,400,220);
		GridPane gp2 = new GridPane();
		
		// Creating and labeling button, label, textfield
		Button btnConnect = new Button("Connect");			
		Label lblPort = new Label("Port");
	    	TextField fldPort = new TextField();
		Label lblInfo = new Label("Insert port number");
	    	    			
		// Set size for the buttons and FlowPane
		btnConnect.setPrefSize(80, 30);
		lblPort.setPrefSize(50, 30);
		fldPort.setPrefSize(150, 30);
		
		// Asign GridPane to BorderPane
		root2.setCenter(gp2);
				
		// Creating columns with different sizes
		ColumnConstraints column = new ColumnConstraints(50);
		ColumnConstraints column1 = new ColumnConstraints(50);
		ColumnConstraints column2 = new ColumnConstraints(150);
		ColumnConstraints column3 = new ColumnConstraints(80);
		ColumnConstraints column4 = new ColumnConstraints(120);
		gp2.getColumnConstraints().addAll(column, column1, column2, column3, column4);
							
		// Creating 8 rows
		for (int i = 0; i<8;i++){
			RowConstraints row = new RowConstraints(30);
			gp2.getRowConstraints().add(row);			
		}
		
		// Asign buttons, label, textfield to column, row
	  	gp2.setConstraints(lblInfo, 2, 1);
	 	gp2.setConstraints(lblPort, 1, 3);
	 	gp2.setConstraints(fldPort, 2, 3);
	 	gp2.setConstraints(btnConnect, 3, 5);
		gp2.getChildren().addAll(lblPort, fldPort, lblInfo, btnConnect);
		
		secondStage.setTitle("Insert Port");
		secondStage.setScene(scene2);		
    	    	
        return scene2;
    }
       
    
    public Stage getStage(){
        return stage;
    }

}

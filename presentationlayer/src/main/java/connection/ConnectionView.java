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



}

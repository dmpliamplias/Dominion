package connection;

import base.View;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConnectionView extends View<ConnectionModel> {
	
    Button btnStartS;
    Button btnJoinS;
    Button btnHelp;

    Button btnConnect;
	Button btnOK;
	TextField fldPort;
	Label lblPort;
	Label lblInfo;
	Label lblIP;
	Stage secondStage;
	TextField fldIP;

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
		
		// Creating 3 buttons
		btnStartS = new Button();
		btnJoinS = new Button();
		btnHelp = new Button();
		
		// Set size for the buttons and FlowPane
		btnStartS.setPrefSize(150, 30);
		btnJoinS.setPrefSize(150, 30);
		btnHelp.setPrefSize(150, 30);
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
		gp.setConstraints(btnStartS, 1, 1);
		gp.setConstraints(btnJoinS, 1, 3);
		gp.setConstraints(btnHelp, 1, 5);
		
		gp.getChildren().addAll(btnJoinS, btnStartS, btnHelp);
		stage.setScene(scene);

		setTexts();
        return scene;
    }


	protected void setTexts() {
		this.stage.setTitle(getText("ConnectionView_stage_Title"));
		btnStartS.setText(getText("ConnectionView_btnStartS"));
		btnJoinS.setText(getText("ConnectionView_btnJoinS"));
		btnHelp.setText(getText("ConnectionView_btnHelp"));

	}

	//TODO Mige: Ich bekomme eine Nullpointer Exception wenn ich diese buttons in die Methode setText schreibe
	// da die Methode setTexts aufgerufen wird beim 1 Fenster und die Buttons noch gar nicht existieren
	// Eine andere Idee?
	protected void setTexts2() {
		btnConnect.setText(getText("ConnectionView_btnConnect"));
		btnOK.setText(getText("ConnectionView_btnOK"));
		lblPort.setText(getText("ConnectionView_lblPort"));
		lblInfo.setText(getText("ConnectionView_lblInfo"));
		secondStage.setTitle(getText("ConnectionView_secondStage_Title"));
	}

	private String getText(String key){
		return translator.getString(key);
	}

	public Stage create_Dialog(){
		secondStage = new Stage();

		secondStage.initOwner(stage);
		secondStage.initModality(Modality.WINDOW_MODAL);
		
		BorderPane root2 = new BorderPane();
		Scene scene2 = new Scene(root2,400,220);
		GridPane gp2 = new GridPane();
		
		// Creating and labeling button, label, textfield
		btnConnect = new Button();
		lblPort = new Label();
		fldPort = new TextField();
		lblInfo = new Label();
	    	    			
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
		
		secondStage.setScene(scene2);
		setTexts2();
        return secondStage;
    }

    public void refreshInfoDialog(){
    	fldPort.setText(Integer.toString(model.getPort()));
		fldIP.setText(model.getIP());
	}

	public void refreshModelFromInfoDialog(){
    	model.setPort(Integer.parseInt(fldPort.getText()));
    	model.setIP(fldIP.getText());
	}

	//TODO Vanessa setText ändern
	public Stage createConnectedInfoDialog(){
		Stage stage = createConnectionDialog();
		btnOK.setPrefSize(80, 30);
		lblInfo.setText(translator.getString("connectionview_notethisinfo"));
		btnOK.setText(translator.getString("ok"));
		stage.setTitle(translator.getString("connectionview_connectioninfo_titel"));

		fldIP.setDisable(true);
		fldPort.setDisable(true);

		return stage;
	}

	public Stage createJoinDialog(){
		Stage stage = createConnectionDialog();
		btnOK.setPrefSize(160, 30);
		lblInfo.setText("Enter the Data your hoster gave you...");
		stage.setTitle("Join Server");

		// TODO@Vanessa wieder ändern
		fldIP.setDisable(false);
		fldPort.setDisable(false);
		return stage;
	}

    private Stage createConnectionDialog(){
		Stage connectionDialogStage = new Stage();
		
		connectionDialogStage.initOwner(stage);
		connectionDialogStage.initModality(Modality.WINDOW_MODAL);
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,400,220);
		GridPane gp = new GridPane();

		
		// Creating and labeling button, label, textfield
		lblPort = new Label();
		lblInfo = new Label();
		fldPort = new TextField();
		lblIP = new Label();
		fldIP = new TextField();
		btnOK = new Button();

		// Set size for the buttons and FlowPane

		lblPort.setPrefSize(50, 30);
		fldPort.setPrefSize(150, 30);
		lblIP.setPrefSize(50, 30);
		fldIP.setPrefSize(150, 30);
		
		// Asign GridPane to BorderPane
		root.setCenter(gp);
		
		
		// Creating columns with different sizes
		ColumnConstraints column = new ColumnConstraints(50);
		ColumnConstraints column1 = new ColumnConstraints(50);
		ColumnConstraints column2 = new ColumnConstraints(150);
		ColumnConstraints column3 = new ColumnConstraints(80);
		ColumnConstraints column4 = new ColumnConstraints(120);
		gp.getColumnConstraints().addAll(column, column1, column2, column3, column4);
					
		
		// Creating 8 rows
		for (int i = 0; i<8;i++){
			RowConstraints row = new RowConstraints(30);
			gp.getRowConstraints().add(row);			
		}
		
		// Asign buttons, label, textfield to column, row
		gp.setConstraints(lblInfo, 2, 1);
	   	gp.setConstraints(lblPort, 1, 3);
		gp.setConstraints(fldPort, 2, 3);
	    gp.setConstraints(lblIP, 1, 4);
	    gp.setConstraints(fldIP, 2, 4);
	    gp.setConstraints(btnOK, 3, 5);
	    gp.getChildren().addAll(lblPort, fldPort, lblInfo, btnOK, lblIP, fldIP);
		

		connectionDialogStage.setScene(scene);

		if(secondStage != null) secondStage.close();
        return connectionDialogStage;
    }
	
       
}

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConnectionView extends View<ConnectionModel> {
	
    Button btnStartS;
    Button btnJoinS;
    Button btnHelp;
    Button btnConnect;
	Button btnOK;
	Button btnCopyPort;
	Button btnCopyIP;
	TextField fldPort;
	TextField fldIP;
	Label lblPort;
	Label lblInfo;
	Label lblIP;
	Stage stageCreateDialog;
	Stage stageConnectedDialog;
	Stage stageJoinDialog;
    ImageView imgViewDeFlag;
    ImageView imgViewEngFlag;
    ImageView imgViewChFlag;


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
		btnStartS.setPrefSize(150, 35);
		btnJoinS.setPrefSize(150, 35);
		btnHelp.setPrefSize(150, 35);
		fp.setPrefSize(450, 50);
        gp.setVgap(20);

		// Create Language Icons
		Image imgDeFlag = new Image(getClass().getResourceAsStream("GermanFlag.png"));
		imgViewDeFlag = new ImageView();
		imgViewDeFlag.setImage(imgDeFlag);
		imgViewDeFlag.setFitHeight(40);
		imgViewDeFlag.setFitWidth(40);

		Image imgChFlag = new Image(getClass().getResourceAsStream("SwissFlag.png"));
		imgViewChFlag = new ImageView();
		imgViewChFlag.setImage(imgChFlag);
		imgViewChFlag.setFitHeight(40);
		imgViewChFlag.setFitWidth(40);

		Image imgEngFlag = new Image(getClass().getResourceAsStream("EnglishFlag.png"));
		imgViewEngFlag = new ImageView();
		imgViewEngFlag.setImage(imgEngFlag);
		imgViewEngFlag.setFitHeight(40);
		imgViewEngFlag.setFitWidth(40);

		fp.getChildren().addAll(imgViewDeFlag, imgViewChFlag, imgViewEngFlag);


		// Asign FlowPane and GridPane to BorderPane
		root.setTop(fp);
		root.setCenter(gp);
		
		// Creating 3 columns
		for (int i = 0; i<3;i++){
			ColumnConstraints column = new ColumnConstraints(150);
			gp.getColumnConstraints().add(column);
					}
		
		// Creating 8 rows
		for (int i = 0; i<6;i++){
			RowConstraints row = new RowConstraints(40);
			gp.getRowConstraints().add(row);			
		}
		
		// Asign column and row to buttons
		gp.setConstraints(btnStartS, 1, 1);
		gp.setConstraints(btnJoinS, 1, 2);
		gp.setConstraints(btnHelp, 1, 3);
		
		gp.getChildren().addAll(btnJoinS, btnStartS, btnHelp);
		stage.setScene(scene);

		setTexts();
        return scene;
    }

	// Stage where you enter your port number
	public Stage create_Dialog(){
		stageCreateDialog = new Stage();

		stageCreateDialog.initOwner(stage);
		stageCreateDialog.initModality(Modality.WINDOW_MODAL);
		
		BorderPane root = new BorderPane();
		Scene scene2 = new Scene(root,400,220);
		GridPane gp = new GridPane();
		gp.setVgap(10);

		
		// Creating and labeling button, label, textfield
		btnConnect = new Button();
		lblPort = new Label();
		fldPort = new TextField();
		lblInfo = new Label();
	    	    			
		// Set size for the button, label, textfield
		btnConnect.setPrefSize(90, 30);
		lblPort.setPrefSize(50, 30);
		fldPort.setPrefSize(150, 30);

		// Asign GridPane to BorderPane
		root.setCenter(gp);

		// Creating columns with different sizes
		ColumnConstraints column = new ColumnConstraints(50);
		ColumnConstraints column1 = new ColumnConstraints(50);
		ColumnConstraints column2 = new ColumnConstraints(150);
		ColumnConstraints column3 = new ColumnConstraints(10);
		ColumnConstraints column4 = new ColumnConstraints(10);
		ColumnConstraints column5 = new ColumnConstraints(90);
		ColumnConstraints column6 = new ColumnConstraints(40);
		gp.getColumnConstraints().addAll(column, column1, column2, column3, column4, column5, column6);
							
		// Creating 8 rows
		for (int i = 0; i<8;i++){
			RowConstraints row = new RowConstraints(30);
			gp.getRowConstraints().add(row);
		}
		
		// Asign buttons, label, textfield to column, row
		gp.setConstraints(lblInfo, 2, 1);
		gp.setConstraints(lblPort, 1, 2);
		gp.setConstraints(fldPort, 2, 2);
	 	gp.setConstraints(btnConnect, 5, 4);
		gp.setColumnSpan(lblInfo, 5);
		gp.getChildren().addAll(lblPort, fldPort, lblInfo, btnConnect);

		stageCreateDialog.setScene(scene2);
		setTexts();
        return stageCreateDialog;
    }


	// stage where it shows port and IP
    public Stage createConnectedDialog(){
		stageConnectedDialog = new Stage();

		stageConnectedDialog.initOwner(stage);
		stageConnectedDialog.initModality(Modality.WINDOW_MODAL);
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,400,220);
		GridPane gp = new GridPane();
		gp.setVgap(10);

		// Asign GridPane to BorderPane
		root.setCenter(gp);
		
		// Creating and labeling button, label, textfield
		lblPort = new Label();
		lblInfo = new Label();
		fldPort = new TextField();
		lblIP = new Label();
		fldIP = new TextField();
		btnOK = new Button();
		btnCopyPort = new Button();
		btnCopyIP = new Button();

		// Set size for the buttons, labels, textfields
		lblPort.setPrefSize(50, 30);
		fldPort.setPrefSize(150, 30);
		lblIP.setPrefSize(50, 30);
		fldIP.setPrefSize(150, 30);
		btnCopyPort.setPrefSize(30, 30);
		btnCopyIP.setPrefSize(30, 30);
		btnOK.setPrefSize(60, 30);

		// Create Image 'Copy to ClipBoard'
		Image imgClipBoard = new Image(getClass().getResourceAsStream("CopyToClipboard.png"));
		ImageView imgViewPort = new ImageView();
		ImageView imgViewIP = new ImageView();
		imgViewPort.setImage(imgClipBoard);
		imgViewPort.setFitHeight(20);
		imgViewPort.setFitWidth(20);
		imgViewIP.setImage(imgClipBoard);
		imgViewIP.setFitHeight(20);
		imgViewIP.setFitWidth(20);
		btnCopyPort.setGraphic(imgViewPort);
		btnCopyIP.setGraphic(imgViewIP);


		// Creating columns with different sizes
		ColumnConstraints column = new ColumnConstraints(50);
		ColumnConstraints column1 = new ColumnConstraints(50);
		ColumnConstraints column2 = new ColumnConstraints(150);
		ColumnConstraints column3 = new ColumnConstraints(30);
		ColumnConstraints column4 = new ColumnConstraints(20);
		ColumnConstraints column5 = new ColumnConstraints(60);
		ColumnConstraints column6 = new ColumnConstraints(40);
		gp.getColumnConstraints().addAll(column, column1, column2, column3, column4, column5, column6);
					
		
		// Creating 8 rows
		for (int i = 0; i<8;i++){
			RowConstraints row = new RowConstraints(30);
			gp.getRowConstraints().add(row);			
		}
		
		// Asign buttons, label, textfield to column, row
		gp.setConstraints(lblInfo, 2, 1);
	   	gp.setConstraints(lblPort, 1, 2);
		gp.setConstraints(fldPort, 2, 2);
		gp.setConstraints(lblIP, 1, 3);
		gp.setConstraints(fldIP, 2, 3);
		gp.setConstraints(btnOK, 5, 4);
		gp.setConstraints(btnCopyPort, 3, 2);
		gp.setConstraints(btnCopyIP, 3, 3);
		gp.setColumnSpan(lblInfo, 5);
		gp.getChildren().addAll(lblPort, fldPort, lblInfo, btnOK, lblIP, fldIP, btnCopyPort, btnCopyIP);

		fldIP.setDisable(true);
		fldPort.setDisable(true);

		stageConnectedDialog.setScene(scene);

		if(stageCreateDialog != null) stageCreateDialog.close();
		setTexts();
        return stageConnectedDialog;
    }


	// stage where the user has to enter port and IP
	public Stage createJoinDialog(){
		stageJoinDialog = new Stage();

		stageJoinDialog.initOwner(stage);
		stageJoinDialog.initModality(Modality.WINDOW_MODAL);

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,400,220);
		GridPane gp = new GridPane();
		gp.setVgap(10);

		// Asign GridPane to BorderPane
		root.setCenter(gp);

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
		btnOK.setPrefSize(90, 30);


		// Creating columns with different sizes
		ColumnConstraints column = new ColumnConstraints(50);
		ColumnConstraints column1 = new ColumnConstraints(50);
		ColumnConstraints column2 = new ColumnConstraints(150);
		ColumnConstraints column3 = new ColumnConstraints(10);
		ColumnConstraints column4 = new ColumnConstraints(10);
		ColumnConstraints column5 = new ColumnConstraints(90);
		ColumnConstraints column6 = new ColumnConstraints(40);
		gp.getColumnConstraints().addAll(column, column1, column2, column3, column4, column5, column6);


		// Creating 8 rows
		for (int i = 0; i<8;i++){
			RowConstraints row = new RowConstraints(30);
			gp.getRowConstraints().add(row);
		}

		// Asign buttons, label, textfield to column, row
		gp.setConstraints(lblInfo, 2, 1);
		gp.setConstraints(lblPort, 1, 2);
		gp.setConstraints(fldPort, 2, 2);
		gp.setConstraints(lblIP, 1, 3);
		gp.setConstraints(fldIP, 2, 3);
		gp.setConstraints(btnOK, 5, 4);
		gp.setColumnSpan(lblInfo, 5);
		gp.getChildren().addAll(lblPort, fldPort, lblInfo, btnOK, lblIP, fldIP);

		fldIP.setDisable(false);
		fldPort.setDisable(false);

		stageJoinDialog.setScene(scene);

		if(stageCreateDialog != null) stageCreateDialog.close();
		setTexts();
		return stageJoinDialog;
	}


	public void refreshInfoDialog(){
		fldPort.setText(Integer.toString(model.getPort()));
		fldIP.setText(model.getIP());
	}

	public void refreshModelFromInfoDialog(){
		model.setPort(Integer.parseInt(fldPort.getText()));
		model.setIP(fldIP.getText());
	}


	private String getText(String key){
		return translator.getString(key);
	}


	protected void setTexts() {
		this.stage.setTitle(getText("ConnectionView_stage_Title"));
		btnStartS.setText(getText("ConnectionView_btnStartS"));
		btnJoinS.setText(getText("ConnectionView_btnJoinS"));
		btnHelp.setText(getText("ConnectionView_btnHelp"));

		if (stageCreateDialog!= null){
			lblPort.setText(getText("ConnectionView_lblPort"));
			lblInfo.setText(getText("ConnectionView_lblInfo"));
			stageCreateDialog.setTitle(getText("ConnectionView_secondStage_Title"));
			btnConnect.setText(getText("ConnectionView_btnConnect"));
		}

		if (stageConnectedDialog!= null){
			lblPort.setText(getText("ConnectionView_lblPort"));
			lblInfo.setText(getText("connectionview_notethisinfo"));
			lblIP.setText(getText("ConnectionView_lblIP"));
			btnOK.setText(getText("ok"));
			stageConnectedDialog.setTitle(getText("connectionview_connectioninfo_titel"));
		}

		if (stageJoinDialog!=null){
			lblPort.setText(getText("ConnectionView_lblPort"));
			lblInfo.setText(getText("ConnectionView_enterInfo"));
			lblIP.setText(getText("ConnectionView_lblIP"));
			btnOK.setText(getText("ConnectionView_btnConnect"));
			stageJoinDialog.setTitle(getText("ConnectionView_joinDialog_Title"));
		}

	}

       
}

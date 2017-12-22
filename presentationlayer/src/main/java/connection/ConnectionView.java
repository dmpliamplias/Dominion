package connection;

import base.View;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static javafx.stage.Modality.WINDOW_MODAL;
import static util.StyleSheetPath.CONNECTION;


public class ConnectionView extends View<ConnectionModel> {
	
    protected Button btnStartS;
	protected Button btnJoinS;
	protected Button btnHelp;
	protected Button btnConnect;
	protected Button btnOK;
	protected Button btnCopyPort;
	protected Button btnCopyIP;
	protected Button btnJoinOK;
	protected TextField fldPort;
	protected TextField fldIP;
	protected Label lblPort;
	protected Label lblInfo;
	protected Label lblInfo2;
	protected Label lblIP;
	protected Stage stageCreateDialog;
	protected Stage stageConnectedDialog;
	protected Stage stageJoinDialog;
	protected ImageView imgViewDeFlag;
	protected ImageView imgViewEngFlag;
	protected ImageView imgViewChFlag;
	protected Image imgIcon = new Image(getClass().getResourceAsStream("/base/castle.png"));


    public ConnectionView(Stage stage, ConnectionModel model){
        super(stage, model);
    }
	
 /**
 *  @author Vanessa Cajochen
 *  */

    public Scene create_GUI(){
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,600,400);
		GridPane gp = new GridPane();
		FlowPane fp = new FlowPane();
		
		// Creating 3 buttons
		btnStartS = new Button();
		btnJoinS = new Button();
		btnHelp = new Button();

		// Set size for the buttons and FlowPane
		btnStartS.setPrefSize(140, 50);
		btnJoinS.setPrefSize(140, 50);
		btnHelp.setPrefSize(140, 50);
		fp.setPrefSize(600, 50);


		// Create Language Icons
		Image imgDeFlag = new Image(getClass().getResourceAsStream("germanFlag.png"));
		imgViewDeFlag = new ImageView();
		imgViewDeFlag.setImage(imgDeFlag);
		imgViewDeFlag.setFitHeight(40);
		imgViewDeFlag.setFitWidth(40);

		Image imgChFlag = new Image(getClass().getResourceAsStream("swissFlag.png"));
		imgViewChFlag = new ImageView();
		imgViewChFlag.setImage(imgChFlag);
		imgViewChFlag.setFitHeight(40);
		imgViewChFlag.setFitWidth(40);

		Image imgEngFlag = new Image(getClass().getResourceAsStream("englishFlag.png"));
		imgViewEngFlag = new ImageView();
		imgViewEngFlag.setImage(imgEngFlag);
		imgViewEngFlag.setFitHeight(40);
		imgViewEngFlag.setFitWidth(40);

		fp.getChildren().addAll(imgViewDeFlag, imgViewChFlag, imgViewEngFlag);


		// Asign FlowPane and GridPane to BorderPane
		root.setTop(fp);
		root.setCenter(gp);

		// Creating 2 columns
		ColumnConstraints column1 = new ColumnConstraints(460);
		ColumnConstraints column2 = new ColumnConstraints(140);
		gp.getColumnConstraints().addAll(column1, column2);

		// Creating 7 rows
		RowConstraints row1 = new RowConstraints(110);
		RowConstraints row2 = new RowConstraints(50);
		RowConstraints row3 = new RowConstraints(25);
		RowConstraints row4 = new RowConstraints(50);
		RowConstraints row5 = new RowConstraints(25);
		RowConstraints row6 = new RowConstraints(50);
		RowConstraints row7 = new RowConstraints(90);
		gp.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7);

		
		// Asign column and row to buttons
		GridPane.setConstraints(btnStartS, 1, 1);
		GridPane.setConstraints(btnJoinS, 1, 3);
		GridPane.setConstraints(btnHelp, 1, 5);
		gp.getChildren().addAll(btnJoinS, btnStartS, btnHelp);


		stage.setScene(scene);
		stage.setResizable(false);
		setStylesheet(scene, CONNECTION);

        return scene;
    }

	// Stage where you enter your port number
	public Stage create_Dialog(){
		stageCreateDialog = new Stage();
		stageCreateDialog.initOwner(stage);
		stageCreateDialog.initModality(WINDOW_MODAL);
		
		BorderPane root = new BorderPane();
		Scene scene2 = new Scene(root,400,220);
		GridPane gp = new GridPane();
		gp.setVgap(10);


		// Creating and labeling button, label, textfield
		btnConnect = new Button();
		btnConnect.setDefaultButton( true );
		lblPort = new Label();
		fldPort = new TextField();
		lblInfo = new Label();
		btnConnect.getStyleClass().add("Transparent");
	    	    			
		// Set size for the button, label, textfield
		btnConnect.setPrefSize(90, 30);
		fldPort.setPrefSize(150, 30);

		// Asign GridPane to BorderPane
		root.setCenter(gp);

		// Creating columns with different sizes
		ColumnConstraints column = new ColumnConstraints(50);
		ColumnConstraints column1 = new ColumnConstraints(50);
		ColumnConstraints column2 = new ColumnConstraints(150);
		ColumnConstraints column3 = new ColumnConstraints(40);
		ColumnConstraints column4 = new ColumnConstraints(90);
		ColumnConstraints column5 = new ColumnConstraints(10);
		gp.getColumnConstraints().addAll(column, column1, column2, column3, column4, column5);
							
		// Creating 8 rows
		for (int i = 0; i<8;i++){
			RowConstraints row = new RowConstraints(30);
			gp.getRowConstraints().add(row);
		}
		
		// Asign buttons, label, textfield to column, row
		GridPane.setConstraints(lblInfo, 2, 1);
		GridPane.setConstraints(lblPort, 1, 2);
		GridPane.setConstraints(fldPort, 2, 2);
		GridPane.setConstraints(btnConnect, 4, 4);
		GridPane.setColumnSpan(lblInfo, 4);
		gp.getChildren().addAll(lblPort, fldPort, lblInfo, btnConnect);

		stageCreateDialog.setScene(scene2);
		setTexts();
        scene2.getStylesheets().add("connection/connectionport.css");
		stageCreateDialog.getIcons().add(imgIcon);
        return stageCreateDialog;
    }


	// stage where it shows port and IP
    public Stage createConnectedDialog(){
		stageConnectedDialog = new Stage();
		this.stageConnectedDialog.setOnCloseRequest(evt -> {
			// prevent window from closing
			evt.consume();

		});

		stageConnectedDialog.initOwner(stage);
		stageConnectedDialog.initModality(WINDOW_MODAL);
		
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
		btnOK.setDefaultButton( true );
		btnCopyPort = new Button();
		btnCopyIP = new Button();
		btnOK.getStyleClass().add("Transparent");

		// Set size for the buttons and textfields
		fldPort.setPrefSize(150, 30);
		fldIP.setPrefSize(150, 30);
		btnCopyPort.setPrefSize(30, 30);
		btnCopyIP.setPrefSize(30, 30);
		btnOK.setPrefSize(110, 30);

		// Create Image 'Copy to ClipBoard'
		Image imgClipBoard = new Image(getClass().getResourceAsStream("copyToClipboard.png"));
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
		ColumnConstraints column4 = new ColumnConstraints(110);
		ColumnConstraints column5 = new ColumnConstraints(10);
		gp.getColumnConstraints().addAll(column, column1, column2, column3, column4, column5);
					
		
		// Creating 8 rows
		for (int i = 0; i<8;i++){
			RowConstraints row = new RowConstraints(30);
			gp.getRowConstraints().add(row);			
		}
		
		// Asign buttons, label, textfield to column, row
		GridPane.setConstraints(lblInfo, 2, 1);
		GridPane.setConstraints(lblPort, 1, 3);
		GridPane.setConstraints(fldPort, 2, 3);
		GridPane.setConstraints(lblIP, 1, 2);
		GridPane.setConstraints(fldIP, 2, 2);
		GridPane.setConstraints(btnOK, 4, 4);
		GridPane.setConstraints(btnCopyPort, 3, 3);
		GridPane.setConstraints(btnCopyIP, 3, 2);
		GridPane.setColumnSpan(lblInfo, 5);
		gp.getChildren().addAll(lblPort, fldPort, lblInfo, btnOK, lblIP, fldIP, btnCopyPort, btnCopyIP);

		fldIP.setDisable(true);
		fldPort.setDisable(true);

		stageConnectedDialog.setScene(scene);

		if(stageCreateDialog != null) stageCreateDialog.close();
		setTexts();

        scene.getStylesheets().add("connection/connectionport.css");
		stageConnectedDialog.getIcons().add(imgIcon);
        return stageConnectedDialog;
    }


	// stage where the user has to enter port and IP
	public Stage createJoinDialog(){
		stageJoinDialog = new Stage();

		stageJoinDialog.initOwner(stage);
		stageJoinDialog.initModality(WINDOW_MODAL);

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,400,220);
		GridPane gp = new GridPane();
		gp.setVgap(10);


		gp.setGridLinesVisible(false);

		// Asign GridPane to BorderPane
		root.setCenter(gp);

		// Creating and labeling button, label, textfield
		lblPort = new Label();
		lblInfo2 = new Label();
		fldPort = new TextField();
		lblIP = new Label();
		fldIP = new TextField();
		btnJoinOK = new Button();
		btnJoinOK.setDefaultButton( true );
		btnJoinOK.getStyleClass().add("Transparent");

		// Set size for the buttons and FlowPane
		fldPort.setPrefSize(150, 30);
		fldIP.setPrefSize(150, 30);
		btnJoinOK.setPrefSize(90, 30);


		// Creating columns with different sizes
		ColumnConstraints column = new ColumnConstraints(50);
		ColumnConstraints column1 = new ColumnConstraints(50);
		ColumnConstraints column2 = new ColumnConstraints(150);
		ColumnConstraints column3 = new ColumnConstraints(10);
		ColumnConstraints column4 = new ColumnConstraints(30);
		ColumnConstraints column5 = new ColumnConstraints(90);
		ColumnConstraints column6 = new ColumnConstraints(20);
		gp.getColumnConstraints().addAll(column, column1, column2, column3, column4, column5, column6);


		// Creating 8 rows
		for (int i = 0; i<8;i++){
			RowConstraints row = new RowConstraints(30);
			gp.getRowConstraints().add(row);
		}

		// Asign buttons, label, textfield to column, row
		GridPane.setConstraints(lblInfo2, 2, 1);
		GridPane.setConstraints(lblPort, 1, 3);
		GridPane.setConstraints(fldPort, 2, 3);
		GridPane.setConstraints(lblIP, 1, 2);
		GridPane.setConstraints(fldIP, 2, 2);
		GridPane.setConstraints(btnJoinOK, 5, 4);
		GridPane.setColumnSpan(lblInfo2, 5);
		gp.getChildren().addAll(lblPort, fldPort, lblInfo2, btnJoinOK, lblIP, fldIP);

		fldIP.setDisable(false);
		fldIP.requestFocus();
		fldPort.setDisable(false);


		scene.getStylesheets().add("connection/connectionport.css");
		stageJoinDialog.setScene(scene);

		if(stageCreateDialog != null) stageCreateDialog.close();
		setTexts();
		stageJoinDialog.getIcons().add(imgIcon);
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


	protected void setTexts() {
		this.stage.setTitle(getText("connectionview.stage.first.title"));
		btnStartS.setText(getText("connectionview.btnStartServer"));
		btnJoinS.setText(getText("connectionview.btnJoinServer"));
		btnHelp.setText(getText("connectionview.btnHelp"));

		if (stageCreateDialog!= null){
			lblPort.setText(getText("connectionview.lblPort"));
			lblInfo.setText(getText("connectionview.lblInfo"));
			stageCreateDialog.setTitle(getText("connectionview.stage.second.title"));
			btnConnect.setText(getText("connectionview.btnConnect"));
		}

		if (stageConnectedDialog!= null){
			lblPort.setText(getText("connectionview.lblPort"));
			lblInfo.setText(getText("connectionview.noteThisInfo"));
			lblIP.setText(getText("connectionview.lblIp"));
			btnOK.setText(getText("btn.ok"));
			stageConnectedDialog.setTitle(getText("connectionview.connectionInfo.titel"));
		}

		if (stageJoinDialog!=null){
			lblPort.setText(getText("connectionview.lblPort"));
			lblInfo2.setText(getText("connectionview.enterInfo"));
			lblIP.setText(getText("connectionview.lblIp"));
			btnJoinOK.setText(getText("connectionview.btnConnect"));
			stageJoinDialog.setTitle(getText("connectionview.joinDialog.title"));
		}

	}

       
}

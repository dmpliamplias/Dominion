package ranking;

import base.View;
import com.weddingcrashers.model.Highscore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;



//** @author Murat Kelleci - Credits:http://docs.oracle.com/javafx/2/ui_controls/table-view.htm
//
public class RankingView extends View<RankingModel> {


    // TODO: 26.10.17 murat: Das ish keis javafx elemnt du muesch listview nä
    ListView<RankingViewModel> list = new ListView();

    ObservableList<RankingViewModel> dataModel;

    public RankingView(Stage stage, RankingModel model) {
        super(stage, model);
    }

    protected Label lblRanking;
    protected TableColumn userNameColumn;
    protected TableColumn userHighScore;
    protected TableColumn rankingPosition;
    protected Button btnLobby;


    @Override
    protected Scene create_GUI() {

        {


            this.stage.setWidth(300);
            this.stage.setHeight(500);


            list = new ListView();
            list.setEditable(false);

            lblRanking = new Label();
            lblRanking.setFont(new Font("Helvetica", 16));

            btnLobby=new Button("loginview.btnGoToLobbyView");



            userNameColumn = new TableColumn();
            userNameColumn.setCellValueFactory(
                    new PropertyValueFactory<RankingViewModel, String>("name"));

            userHighScore = new TableColumn();
            userHighScore.setCellValueFactory(
                    new PropertyValueFactory<RankingViewModel, Integer>("points"));

            rankingPosition = new TableColumn();

            bindModeltoView();

            this.list.setItems(dataModel);
            this.list.getItems().addAll();

            final VBox vbox = new VBox();
            vbox.setSpacing(4);
            vbox.setPadding(new Insets(8, 0, 0, 8));
            vbox.getChildren().addAll(lblRanking, list, btnLobby);

            Scene scene = new Scene(vbox);


            this.stage.setScene(scene);
            this.stage.show();

            setTexts();

            return scene;
        }

    }

    //TODO Murat anpassen
    protected void setTexts() {
        this.stage.setTitle(getText("rankingview.rankingPage"));
        this.lblRanking.setText(getText("rankingview.highscoreAlltime"));
        this.userNameColumn.setText(getText("registerview.username"));
        this.userHighScore.setText(getText("rangkingview.highscorePoints"));
        this.rankingPosition.setText(getText("rankingview.rankingPosition"));



    }

    public void bindModeltoView () {

            ArrayList<RankingViewModel> vmList = new ArrayList<RankingViewModel>();


            // For Loop from Script Kaspar Riesen Programming 1
            for (Highscore hs : model.getHighscores()) {
                RankingViewModel vm = new RankingViewModel();
                vm.setName(hs.getUser().getUserName());
                vm.setPoints(hs.getPoints());
                vmList.add(vm);

            }
            dataModel = FXCollections.observableList(vmList);
    }

}
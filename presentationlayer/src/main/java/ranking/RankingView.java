package ranking;

import base.View;
import com.weddingcrashers.model.Highscore;
import com.weddingcrashers.model.User;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.List;


//** @author Murat Kelleci - Credits:http://docs.oracle.com/javafx/2/ui_controls/table-view.htm
//
public class RankingView extends View<RankingModel> {

    TableView<RankingViewModel> table;

    protected Label lblRanking;
    protected TableColumn userNameColumn;
    protected TableColumn userHighScoreColumn;
    protected TableColumn rankingPositionColumn;
    protected Button btnLobby;

    public RankingView(Stage stage, RankingModel model) {
        super(stage, model);

    }

    @Override
    protected Scene create_GUI() {
            this.stage.setWidth(500);
            this.stage.setHeight(600);

            table = new TableView<RankingViewModel>();
            table.setEditable(false);

            lblRanking = new Label();
            lblRanking.setFont(new Font("Helvetica", 16));

            btnLobby = new Button();

            final VBox vbox = new VBox();
            vbox.setSpacing(4);
            vbox.setPadding(new Insets(8, 0, 0, 8));
            vbox.getChildren().addAll(lblRanking, table, btnLobby);

            Scene scene = new Scene(vbox);

            this.stage.setScene(scene);
            this.stage.show();

            setTexts();
            return scene;


    }

    protected void setTexts() {

        this.userNameColumn = new TableColumn("Name");
        this.rankingPositionColumn = new TableColumn(("Position"));
        this.userHighScoreColumn = new TableColumn("Points");

        this.table.getColumns().clear();
        this.table.getColumns().addAll(rankingPositionColumn, userNameColumn, userHighScoreColumn);

        this.stage.setTitle(getText("rankingview.rankingPage"));
        this.lblRanking.setText(getText("rankingview.highscoreAlltime"));
        this.userNameColumn.setText(getText("registerview.username"));
        this.btnLobby.setText(getText("loginview.btnGoToLobbyView"));


    }

    public void bindModelToView() {

        final ObservableList<RankingViewModel> vmList = FXCollections.observableArrayList();
        RankingViewModel vm2 = new RankingViewModel();
        vm2.setName("Murat Kelleci");
        vm2.setPoints(1);
        vm2.setPosition(2);
        vmList.add(vm2);

        //For Loop from Script Kaspar Riesen Programming 1
        for (Highscore hs : model.getHighscores()) {
            RankingViewModel vm = new RankingViewModel();
            vm.setName(hs.getUser().getUserName());
            vm.setPoints(hs.getPoints());
            vm.setPosition(model.getHighscores().indexOf(hs) + 1);
            vmList.add(vm);
        }

        table.setItems(vmList);

       this.userNameColumn.setCellValueFactory(new PropertyValueFactory<RankingViewModel, String>("name"));
        this.userHighScoreColumn.setCellValueFactory(new PropertyValueFactory<RankingViewModel, String>("points"));
        this.rankingPositionColumn.setCellValueFactory(new PropertyValueFactory<RankingViewModel, String>("position"));

    }





}
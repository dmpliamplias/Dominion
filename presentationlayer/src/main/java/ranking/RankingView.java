package ranking;

import base.View;
import com.weddingcrashers.model.Highscore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 *@author Murat Kelleci - Credits:http://docs.oracle.com/javafx/2/ui_controls/table-view.htm
*/
public class RankingView extends View<RankingModel> {


    private TableView<RankingViewModel> table;

    private Label lblRanking;
    private TableColumn<RankingViewModel, String> userNameColumn;
    private TableColumn<RankingViewModel, String> userHighScoreColumn;
    private TableColumn<RankingViewModel, String> rankingPositionColumn;
    protected Button btnLobby;

    public RankingView(Stage stage, RankingModel model) {
        super(stage, model);

    }

    @Override
    protected Scene create_GUI() {
            this.stage.setWidth(500);
            this.stage.setHeight(600);
            this.stage.setOnCloseRequest(evt -> {
            // prevent window from closing
            evt.consume();
            });


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
            return scene;
    }

    protected void setTexts() {

        this.userNameColumn = new TableColumn<>("Name");
        this.rankingPositionColumn = new TableColumn<RankingViewModel, String>(("Position"));
        this.userHighScoreColumn = new TableColumn<RankingViewModel, String>("Points");

        this.table.getColumns().clear();
        this.table.getColumns().addAll(rankingPositionColumn, userNameColumn, userHighScoreColumn);

        this.stage.setTitle(getText("rankingview.rankingPage"));
        this.lblRanking.setText(getText("rankingview.highscoreAlltime"));
        this.userNameColumn.setText(getText("registerview.username"));
        this.btnLobby.setText(getText("loginview.btnGoToLobbyView"));

    }

    protected void bindModelToView() {

        //For Loop from Script Kaspar Riesen Programming 1
        // here is the methode to bind model to the view

        final ObservableList<RankingViewModel> vmList = FXCollections.observableArrayList();
        for (Highscore hs : model.getHighscores()) {
            RankingViewModel vm = new RankingViewModel();
            final StringBuilder sb = new StringBuilder();
            sb.append(hs.getUser().getUsername());
            if (hs.getUser().isDeleted()) {
                sb.append(" ").append(getText("rankingview.userDeleted"));
            }
            vm.setName(sb.toString());
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
package ranking;

import base.View;
import com.weddingcrashers.model.Highscore;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import java.util.ArrayList;
import java.lang.*;



//** @author Murat Kelleci - Credits:http://docs.oracle.com/javafx/2/ui_controls/table-view.htm
//
public class RankingView extends View<RankingModel> {

    private TableView<RankingViewModel> table = new TableView();

    ObservableList<RankingViewModel> dataModel;

    public RankingView(Stage stage, RankingModel model) {
        super(stage, model);
    }

    protected Label lblRanking;
    protected TableColumn userNameColumn;
    protected TableColumn userHighScore;
    protected TableColumn rankingPosition;

    @Override
    protected Scene create_GUI() {

        {


            this.stage.setWidth(300);
            this.stage.setHeight(500);

            table.setEditable(false);

            lblRanking = new Label();
            lblRanking.setFont(new Font("Helvetica", 16));


            userNameColumn = new TableColumn();
            userNameColumn.setCellValueFactory(
                    new PropertyValueFactory<RankingViewModel, String>("name"));

            userHighScore = new TableColumn();
            userHighScore.setCellValueFactory(
                    new PropertyValueFactory<RankingViewModel, Integer>("points"));

            rankingPosition = new TableColumn();

            this.table.setItems(dataModel);
            this.table.getColumns().addAll(rankingPosition,userNameColumn, userHighScore);

            final VBox vbox = new VBox();
            vbox.setSpacing(4);
            vbox.setPadding(new Insets(8, 0, 0, 8));
            vbox.getChildren().addAll(lblRanking, table);

            Scene scene = new Scene(table);

            scene.getRoot().getChildrenUnmodifiable().addAll(vbox);

            this.stage.setScene(scene);
            this.stage.show();

            setTexts();
            return scene;
        }

    }

    //TODO Murat anpassen
    protected void setTexts() {
        this.stage.setTitle(getText("Ranking_Page"));
        this.lblRanking.setText(getText("Highscore_Alltime"));
        this.userNameColumn.setText(getText("Username"));
        this.userHighScore.setText(getText("Highscore_Points"));
        this.rankingPosition.setText(getText("RankingPosition"));



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
package ranking;

import base.View;
import com.weddingcrashers.model.Highscore;
import com.weddingcrashers.model.User;
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


            this.stage.setWidth(1000);
            this.stage.setHeight(600);


            list = new ListView();
            list.setEditable(false);

            lblRanking = new Label();
            lblRanking.setFont(new Font("Helvetica", 16));

            btnLobby = new Button();


            userNameColumn = new TableColumn();
            userNameColumn.setCellValueFactory(
                    new PropertyValueFactory<RankingViewModel, String>("name"));

            userHighScore = new TableColumn();
            userHighScore.setCellValueFactory(
                    new PropertyValueFactory<RankingViewModel, Integer>("points"));

            rankingPosition = new TableColumn();


            this.list.setItems(dataModel);
            //this.list.getItems().addAll();

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

    protected void setTexts() {
        this.stage.setTitle(getText("rankingview.rankingPage"));
        this.lblRanking.setText(getText("rankingview.highscoreAlltime"));
        this.userNameColumn.setText(getText("registerview.username"));
        this.userHighScore.setText(getText("rangkingview.highscorePoints"));
        this.rankingPosition.setText(getText("rankingview.rankingPosition"));
        this.btnLobby.setText(getText("loginview.btnGoToLobbyView"));


    }

    public void bindModelToView() {

        ArrayList<RankingViewModel> vmList = new ArrayList<RankingViewModel>();


        //For Loop from Script Kaspar Riesen Programming 1
        //for (Highscore hs : model.getHighscores()) {
          //  RankingViewModel vm = new RankingViewModel();
           // vm.setName(hs.getUser().getUserName());
           // vm.setPoints(hs.getPoints());
           // vmList.add(vm);
        for (Highscore hs: this.getMockPersons()) {
            RankingViewModel vm = new RankingViewModel();
            vm.setName(hs.getUser().getUserName());
            vm.setPoints(hs.getPoints());
            vmList.add(vm);

        }
       dataModel = FXCollections.observableList(vmList);
    }



    public List<Highscore> getMockPersons() {
        final ArrayList<Highscore> objects = new ArrayList<>();
        final User hans = new User().name("Hans").email("").password("");
        final User murat = new User().name("Murat").email("").password("");
        final User fritz = new User().name("Fritz").email("").password("");
        final User djoni = new User().name("Djoni").email("").password("");
        final User migi = new User().name("Migi").email("").password("");
        final User reto = new User().name("Reto").email("").password("");
        final User lola = new User().name("Lola").email("").password("");
        final Highscore highscoreHans = new Highscore();
        final Highscore highscoreFritz = new Highscore();
        final Highscore highscoreMigi = new Highscore();
        final Highscore highscoreDjoni = new Highscore();
        final Highscore highscoreReto = new Highscore();
        final Highscore highscoreLola = new Highscore();
        final Highscore highscoreMurat = new Highscore();
        highscoreHans.setPoints(1000);
        highscoreHans.setUser(hans);
        highscoreMurat.setPoints(2000);
        highscoreMurat.setUser(murat);
        highscoreFritz.setPoints(4000);
        highscoreFritz.setUser(fritz);
        highscoreDjoni.setPoints(6000);
        highscoreDjoni.setUser(djoni);
        highscoreMigi.setPoints(8000);
        highscoreMigi.setUser(migi);
        highscoreReto.setPoints(9000);
        highscoreReto.setUser(reto);
        highscoreLola.setPoints(9500);
        highscoreLola.setUser(lola);

        objects.add(highscoreHans);
        objects.add(highscoreMurat);
        objects.add(highscoreFritz);
        objects.add(highscoreDjoni);
        objects.add(highscoreMigi);
        objects.add(highscoreReto);
        objects.add(highscoreLola);

        return objects;
    }

}
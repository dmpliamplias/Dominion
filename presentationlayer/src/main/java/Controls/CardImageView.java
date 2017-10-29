package Controls;

import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.KingCard;
import com.weddingcrashers.businessmodels.MoneyCard;
import com.weddingcrashers.businessmodels.PointCard;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardImageView extends ImageView {

    private Card card;

    public CardImageView(Card card) {
        this.card = card;
        this.setOnMouseDragged(e -> {
            this.setLayoutX(e.getSceneX());
            this.setLayoutY(e.getSceneY());
                runAction();
            });
        this.setCardImageViewDesign();

    }

    private void runAction(){
        if(this.card instanceof KingCard){
            KingCard kc = (KingCard)card;

        }else if(this.card instanceof MoneyCard){
            MoneyCard mc= (MoneyCard)card;

        }else if(this.card instanceof PointCard){
            PointCard mc= (PointCard)card;

        }
    }

    private void setCardImageViewDesign(){

        try {
            Image playC=new Image(card.getFilePath());
            this.setFitHeight(130);
            this.setFitWidth(60);
            this.setPreserveRatio(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
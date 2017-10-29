package Controls;

import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.KingCard;
import com.weddingcrashers.businessmodels.MoneyCard;
import com.weddingcrashers.businessmodels.PointCard;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardButton extends Button {

    private Card card;

    public CardButton(Card card) {
        this.card = card;
        this.setOnMouseDragged(e -> {
            this.setLayoutX(e.getSceneX());
            this.setLayoutY(e.getSceneY());
                runAction();
            });
        this.setButtonDesign();

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

    private void setButtonDesign(){

        try {
            Image playC=new Image(card.getFilePath());
            ImageView iv= new ImageView(playC);
            iv.setFitHeight(130);
            iv.setFitWidth(60);
            //InputStream buttonIcon = new BufferedInputStream(new FileInputStream(card.getFilePath()));
            //this.setPrefSize(130,60);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
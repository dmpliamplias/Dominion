package Controls;

import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.KingCard;
import com.weddingcrashers.businessmodels.MoneyCard;
import com.weddingcrashers.businessmodels.PointCard;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;


public class CardImageView extends ImageView {

    private Card card;
    private final CardSize cardSize;
    public enum CardSize{
        miniSize,bigSize
    }

    public CardImageView(Card card, CardSize cardSize) {
        this.card = card;
        this.cardSize = cardSize;
        this.setOnMouseClicked(e -> {
            //this.setLayoutX(e.getSceneX());
            //this.setLayoutY(e.getSceneY());
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
            PointCard pc= (PointCard)card;

        }
    }

    private void setCardImageViewDesign(){

        try {
            String path = card.getFilePath();
            Translator tl = ServiceLocator.getServiceLocator().getTranslator();
            Translator.Language lang =  tl.getCurrentLanguage();
            String code = tl.getLanguageCode(lang);

            code = code.replace("de_", "");
            path = path.replace("{0}", code.toUpperCase());

            if (cardSize.equals(cardSize.miniSize)){
                Image playC = new Image(this.getClass().getResourceAsStream("/Game/mini/"+ path));
                this.setFitHeight(90);
                this.setFitWidth(45);
                this.setPreserveRatio(true);
                this.setImage(playC);
            } else if (cardSize.equals(cardSize.bigSize)) {
                Image playC = new Image(this.getClass().getResourceAsStream("/Game/big/"+ path));
                this.setFitHeight(130);
                this.setFitWidth(60);
                this.setPreserveRatio(true);
                this.setImage(playC);
            }


        } catch (Exception e){
                e.printStackTrace();

        }

    }

}
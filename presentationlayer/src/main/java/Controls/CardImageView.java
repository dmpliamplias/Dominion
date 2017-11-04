package Controls;

import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.KingCard;
import com.weddingcrashers.businessmodels.MoneyCard;
import com.weddingcrashers.businessmodels.PointCard;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class CardImageView extends ImageView {

    private Card card;
    private final CardSize cardSize;
    public enum CardSize{
        miniSize,normalSize
    }




    public CardImageView(Card card, CardSize cardSize) {
        this.card = card;
        this.cardSize=cardSize;
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
            Translator.Language lang = ServiceLocator.getServiceLocator().getTranslator().getCurrentLanguage();
            String code = ServiceLocator.getServiceLocator().getTranslator().getLanguageCode(lang);

            code = code.replace("de_","");
            path = path.replace("{0}", code.toUpperCase());
            Image playC=new Image(this.getClass().getResourceAsStream("/Game/mini"));
            this.setFitHeight(130);
            this.setFitWidth(60);
            this.setPreserveRatio(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
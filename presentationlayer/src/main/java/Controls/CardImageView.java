package Controls;

import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.KingCard;
import com.weddingcrashers.businessmodels.MoneyCard;
import com.weddingcrashers.businessmodels.PointCard;
import com.weddingcrashers.model.User;
import com.weddingcrashers.service.Language;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.PLServiceLocator;


//**
   //@author Murat Kelleci*/ 20.10.17-04.11.2017

public class CardImageView extends ImageView {


    private final int miniSizeHeight=90;
    private final int bigSizeHeight=130;
    private final int toolTipSizeHeight=300;
    private final int miniMiniHeight=60;
    private Card card;

    private final CardSize cardSize;
    public enum CardSize{
        miniSize,bigSize, tooltip, miniMini
    }

    public CardImageView(Card card, CardSize cardSize) {
        this.card = card;
        this.cardSize = cardSize;


        this.setCardImageViewDesign();
    }

    private void setCardImageViewDesign(){

        try {
            String path = card.getFilePath();
            Translator tr = ServiceLocator.getServiceLocator().getTranslator();
            final Language currentLanguage = tr.getCurrentLanguage();
            String code = currentLanguage.getCode();

            code = code.replace("de_", "");
            path = path.replace("{0}", code.toUpperCase());

            if (cardSize.equals(cardSize.miniSize)){
                Image playCM = new Image(this.getClass().getResourceAsStream("/Game/mini/"+ path));

                //ToDo Murat setPreserveRatio verwenden dann brauchts Zeile setFitWidth nicht mehr.
                //ToDo Murat umschreiben MagicNumbers
                this.setFitHeight(miniSizeHeight);
                //this.setFitWidth(100);
                this.setPreserveRatio(true);
                this.setImage(playCM);
            } else if (cardSize.equals(cardSize.bigSize)) {
                Image playCB = new Image(this.getClass().getResourceAsStream("/Game/big/"+ path));
                this.setFitHeight(bigSizeHeight);
                //this.setFitWidth(60);
                this.setPreserveRatio(true);
                this.setImage(playCB);
            } else if (cardSize.equals(cardSize.tooltip)) {
                Image playCT = new Image(this.getClass().getResourceAsStream("/Game/big/" + path));
                this.setFitHeight(toolTipSizeHeight);
                //this.setFitWidth(200);
                this.setPreserveRatio(true);
                this.setImage(playCT);
            } else if (cardSize.equals(cardSize.miniMini)){
                Image playCM2 = new Image(this.getClass().getResourceAsStream("/Game/mini/"+ path));
                this.setFitHeight(miniMiniHeight);
               // this.setFitWidth(70);
                this.setPreserveRatio(true);
                this.setImage(playCM2);

            }

        } catch (Exception e){
                e.printStackTrace();

        }

    }

    public Card getCard() {
        return card;
    }

    public CardSize getCardSize() {
        return cardSize;
    }


}
package Controls;

import base.View;
import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.KingCard;
import com.weddingcrashers.businessmodels.MoneyCard;
import com.weddingcrashers.businessmodels.PointCard;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.CardPlayedInfo;
import com.weddingcrashers.servermodels.GameContainer;
import com.weddingcrashers.servermodels.Methods;
import com.weddingcrashers.service.Language;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import com.weddingcrashers.util.businesslayer.ServerUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.PLServiceLocator;
import util.ServerConnectionService;

import java.io.IOException;


   //**
   //@author Murat Kelleci*/ 20.10.17-21.11.2017

public class CardImageView extends ImageView {


    private final int miniSizeHeight=90;
    private final int bigSizeHeight=130;
    private final int toolTipSizeHeight=300;
    private final int miniMiniHeight=60;
    private final CardSize cardSize;
    private Card card;

    public enum CardSize{
        miniSize,bigSize, tooltip, miniMini
    }

    public CardImageView(Card card, CardSize cardSize) {
        this.card = card;
        this.cardSize = cardSize;
        this.setCardImageViewDesign();
    }

    // ToDo je nach Resolution verschiedene Grösse für die einzelnen Karten
    // ToDo Zwei Methoden Karten kaufen und Karten spielen.


    private void setCardImageViewDesign() {

        try {
            String path = card.getFilePath();
            Translator tr = ServiceLocator.getServiceLocator().getTranslator();
            final Language currentLanguage = tr.getCurrentLanguage();
            String code = currentLanguage.getCode();

            code = code.replace("de_", "");
            if(currentLanguage == Language.ENGLISH){
                code = "ENG";
            }
            path = path.replace("{0}", code.toUpperCase());

            if (cardSize.equals(cardSize.miniSize)){
                Image playCM = new Image(this.getClass().getResourceAsStream("/Game/mini/"+ path));

                //ToDo setPreserveRatio verwenden dann brauchts Zeile setFitWidth nicht mehr.
                //ToDo umschreiben MagicNumbers
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

    public CardSize getCardSize() {
        return cardSize;
    }

    public Card getCard() {
        return card;
    }

}
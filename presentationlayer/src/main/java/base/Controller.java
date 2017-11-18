package base;

import com.weddingcrashers.service.Language;
import javafx.event.ActionEvent;
import util.PLServiceLocator;
import util.ServerConnectionService;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;

import static com.weddingcrashers.service.Language.ENGLISH;
import static com.weddingcrashers.service.Language.GERMAN;
import static com.weddingcrashers.service.Language.SWISS_GERMAN;

/**
 * Controller base class.
 *
 * @param <M> the type of the model.
 * @param <V> the type of the view.
 */
public abstract class Controller<M extends Model, V extends View<M>> {

    // ---- Members

    /** The service locator. */
    protected final ServiceLocator serviceLocator = ServiceLocator.getServiceLocator();
    /** The model. */
    protected M model;
    /** The view. */
    protected V view;
    /** The translator. */
    protected Translator translator;
    /** The server connection service. */
    protected ServerConnectionService serverConnectionService;
    /** The presentation layer service locator. */
    protected  PLServiceLocator plServiceLocator;


    // ---- Constructor

    /**
     * Constructor.
     *
     * @param model the model.
     * @param view the view.
     */
    protected Controller(M model, V view) {
        this.model = model;
        this.view = view;

        translator = ServiceLocator.getServiceLocator().getTranslator();
        plServiceLocator = PLServiceLocator.getPLServiceLocator();
        if( plServiceLocator != null) {
            serverConnectionService = plServiceLocator.getServerConnectionService();
        }

        /**
         *  author Manuel Wirz
         *  */

        if(view.menuBarUsed){

            setonActionEvent();
        }

    }

    /**
     *  author Manuel Wirz
     *  */

    private void setonActionEvent() {

        view.menuItemCH.setOnAction( event -> {

            view.switchTranslator(SWISS_GERMAN);

        } );

        view.menuItemDE.setOnAction( event -> {

            view.switchTranslator( GERMAN );

        } );

        view.menuItemENG.setOnAction( event -> {

            view.switchTranslator( ENGLISH );

        } );

    }


    /**
     * Returns the text for the given key.
     *
     * @param key the key to get the text for.
     * @return the text for the given key.
     */

    protected String getText(String key) {
        return translator.getString(key);
    }

}

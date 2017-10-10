package base;

import app.PLServiceLocator;
import app.ServerConnectionService;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;

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
    protected Translator translator;
    protected ServerConnectionService serverConnectionService;
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
    }

}

package splashscreen;

import javafx.animation.TranslateTransition;

/**
 * Translate transition progress holder.
 *
 * @author dmpliamplias
 */
public class TransitionProgressHolder {

    // ---- Members

    private TranslateTransition transition;
    private int progress;
    private boolean terminated;


    // ---- Constructor

    /**
     * Constructor.
     *
     * @param transition the transition.
     * @param progress the progress.
     */
    TransitionProgressHolder(TranslateTransition transition, int progress) {
        this.transition = transition;
        this.progress = progress;
    }


    // ---- Methods

    public TranslateTransition getTransition() {
        return transition;
    }

    public int getProgress() {
        return progress;
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

}
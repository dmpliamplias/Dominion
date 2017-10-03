package ranking;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;


//** @author Murat Kelleci


public class RankingViewModel {

    private SimpleStringProperty name;
    private SimpleIntegerProperty points;


    protected RankingViewModel(){
        this.name= new SimpleStringProperty();
        this.points=new SimpleIntegerProperty();


    }

    public String getName() {
       return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getPoints() {
        return points.get();
    }

    public SimpleIntegerProperty pointsProperty() {
        return points;
    }

    public void setPoints(int points) {
        this.points.set(points);
    }
}

package ranking;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;


//** @author Murat Kelleci


public class RankingViewModel {

    private RankingViewModel(String name,int points){
        this.name= new SimpleStringProperty(name);
        this.points=new SimpleIntegerProperty(points);

    }


    private final SimpleStringProperty name;
    private final SimpleIntegerProperty points;

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

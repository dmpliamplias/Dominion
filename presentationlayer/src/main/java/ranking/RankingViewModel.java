package ranking;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;


//** @author Murat Kelleci - Credits:http://docs.oracle.com/javafx/2/ui_controls/table-view.htm


public class RankingViewModel {

    private SimpleStringProperty name;
    private SimpleIntegerProperty points;

    public int getPosition() {
        return position.get();
    }

    private SimpleIntegerProperty position;


    protected RankingViewModel(){
        this.name= new SimpleStringProperty();
        this.points=new SimpleIntegerProperty();
        this.position = new SimpleIntegerProperty();
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

    public SimpleIntegerProperty positionProperty() {
        return position;
    }

    public void setPosition(int position) {
        this.position.set(position);
    }

}

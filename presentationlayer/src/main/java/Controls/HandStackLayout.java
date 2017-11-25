package Controls;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Region;

public class HandStackLayout extends Region {
    double cardInterval = 100;

    public void setCardInterval(double value){
        cardInterval = value;
    }

    public ObservableList<Node> getChildren(){
        return super.getChildren();
    }


    public void layoutChildren(){
    super.layoutChildren();
    ObservableList<Node> children = getChildren();
    int i = 0;
        for (Node child : children){
        child.relocate(i, 0);
        i+=cardInterval;
        }
    }
}

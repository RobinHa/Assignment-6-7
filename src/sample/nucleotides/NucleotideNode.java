package sample.nucleotides;

import javafx.scene.Group;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by Robin on 25.11.2015.
 */
public abstract class NucleotideNode extends Circle {

    protected Circle circle;
    protected Tooltip tooltip;

    private final double RADIUS = 6;

    public NucleotideNode() {
        super();

        circle = new Circle(RADIUS);
        tooltip = new Tooltip();
        tooltip.setAutoHide(false);
        Tooltip.install(circle, tooltip);
    }

    /**
     * Sets the circle color
     * @param color
     */
    protected void setColor(Color color){
        circle.setFill(color);
    }

    public void setTooltip(Tooltip tooltip){
        this.tooltip = tooltip;
    }

    public void setTooltip(String text){
        tooltip.setText(text);
    }

    public Circle getCircle() {
        return circle;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }
}

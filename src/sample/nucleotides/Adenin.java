package sample.nucleotides;

import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;

/**
 * Created by Robin on 25.11.2015.
 */
public class Adenin extends NucleotideNode {

    final private Color ADENINE_COLOR = Color.web("#FFAE6B");
    final private Tooltip ADENINE_TOOLTIP = new Tooltip("A");

    public Adenin(){
        super();
        this.setColor(ADENINE_COLOR);
        this.setTooltip(ADENINE_TOOLTIP);
    }

    @Override
    public void setTooltip(String text){
        tooltip = new Tooltip(text);
        tooltip.setAutoHide(false);
        Tooltip.install(circle, tooltip);
    }

}

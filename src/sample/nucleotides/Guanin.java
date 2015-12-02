package sample.nucleotides;

import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;

/**
 * Created by Robin on 26.12.2015.
 */
public class Guanin extends NucleotideNode {

    final private Color GUANIN_COLOR = Color.web("#63DE5D");
    final private Tooltip GUANIN_TOOLTIP = new Tooltip("G");

    public Guanin(){
        super();
        this.setColor(GUANIN_COLOR);
        this.setTooltip(GUANIN_TOOLTIP);
    }
}

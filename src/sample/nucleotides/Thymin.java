package sample.nucleotides;

import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;

/**
 * Created by Robin on 26.12.2015.
 */
public class Thymin extends NucleotideNode{

    final private Color THYMIN_COLOR = Color.web("#FD6A6E");
    final private Tooltip THYMIN_TOOLTIP = new Tooltip("T");

    public Thymin(){
        super();
        this.setColor(THYMIN_COLOR);
        this.setTooltip(THYMIN_TOOLTIP);
    }
}

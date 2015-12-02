package sample.nucleotides;

import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;

/**
 * Created by Robin on 01.12.2015.
 */
public class Uracil extends NucleotideNode {

    final private Color URACIL_COLOR = Color.web("#FD6A6E");
    final private Tooltip URACIL_TOOLTIP = new Tooltip("U");

    public Uracil (){
        setColor(URACIL_COLOR);
        setTooltip(URACIL_TOOLTIP);
    }
}

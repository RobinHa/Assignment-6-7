package sample.nucleotides;

import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;

/**
 * Created by Robin on 26.12.2015.
 */
public class Cytosin extends NucleotideNode{

    final private Color CYTOSIN_COLOR = Color.web("#4EBABA");
    final private Tooltip CYTOSIN_TOOLTIP = new Tooltip("C");

    public Cytosin(){
        super();
        this.setColor(CYTOSIN_COLOR);
        this.setTooltip(CYTOSIN_TOOLTIP);
    }
}

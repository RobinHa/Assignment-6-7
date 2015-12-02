package sample;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Robin on 30.11.2015.
 */
public class ViewImplementer extends BorderPane {

    VBox controllBox = new VBox();
    TextField seqeucneInput = new TextField();
    TextField dotBracketInput =  new TextField();

    HBox buttonBox = new HBox();
    Button drawButton = new Button();
    Button nussinovButton = new Button();
    CheckBox animate = new CheckBox();


    Pane rna2DArea = new Pane();

    final int MIN_WIDTH = 250;
    final Functionality functionality;

    /**
     *
     */
    public  ViewImplementer(Functionality func){
        super();

        functionality = func;

        createInputPanel();
        this.setTop(controllBox);
        this.setCenter(rna2DArea);
    }

    private void createInputPanel(){

        seqeucneInput.setMinWidth(MIN_WIDTH);
        seqeucneInput.setText("RNA input");
        dotBracketInput.setMinWidth(MIN_WIDTH);
        dotBracketInput.setText("dot bracket notation");

        drawButton.setText("Draw");
        nussinovButton.setText("Nussinov");
        animate.setText("Animate");

        buttonBox.getChildren().addAll(drawButton, nussinovButton, animate);
        controllBox.getChildren().addAll(seqeucneInput, dotBracketInput, buttonBox);

    }
}

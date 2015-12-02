package sample;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import sample.GivenCode.Graph;
import sample.GivenCode.Nussinov;
import sample.GivenCode.SpringEmbedder;
import sample.nucleotides.NucleotideNode;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.random;
import static sample.nucleotides.NucleotideBySymbol.createNucleotideBySymbol;

/**
 * Created by Robin on 30.12.2015.
 */
public class Functionality {

    private final char[] RNA_NUCLEOTIDES = ("AGCUagcu").toCharArray();

    private final char[] DNA_NUCLEOTIDES = ("AGTCagtc").toCharArray();

    private final SimpleStringProperty sequence = new SimpleStringProperty(this, null, null);
    private final SimpleStringProperty dotBracket = new SimpleStringProperty(this, null, null);
    private final SimpleBooleanProperty isRNA   = new SimpleBooleanProperty(this, null, false);
    private final SimpleBooleanProperty isMatchingNotation = new SimpleBooleanProperty(this, null, false);

    private Nussinov nussinov;

    public Functionality(){

        sequence.addListener((e) -> this.isRNA.setValue(isRnaSequence()));
        dotBracket.addListener((e) -> this.isMatchingNotation.setValue(isMatchingDotBracket()));
    }

    private boolean isRnaSequence(){
        char[] sequenceArray = sequence.getValue().toCharArray();
        boolean isRNA = sequenceArray.length>0; //true if length is longer than 0

        if(isRNA) {
            for (char c : sequenceArray) {
                if(!(isRNA = isRNABase(c)))break;
            }
        }

        return isRNA;
    }

    private boolean isRNABase(char c){
        for(char base: RNA_NUCLEOTIDES)
            if(base == c) return true;
        return false;
    }

    private boolean isMatchingDotBracket(){
//        if(sequence == null || dotBracket == null) { return false; }
//        if(sequence.get().length() != dotBracket.get().length()) {return false;}
        int openBrackets = 0;

        for(char c: dotBracket.getValue().toCharArray()){
            switch (c){
                case '(':
                    openBrackets++; break;
                case ')':
                    openBrackets--; break;
                default: break;
            }
        }

        return (openBrackets == 0);
    }

    public void runNussinov(){
        nussinov = new Nussinov(sequence.get());
        int nussinovScore = nussinov.apply(); // TODO maybe give the score out
        dotBracket.set(nussinov.getBracketNotation());
    }



    public String getSequence() {
        return sequence.get();
    }

    public String getDotBracket() {
        return dotBracket.get();
    }

    public boolean getIsRNA() {
        return isRNA.get();
    }

    public SimpleStringProperty sequenceProperty() {
        return sequence;
    }

    public SimpleStringProperty dotBracketProperty() {
        return dotBracket;
    }

    public SimpleBooleanProperty isRNAProperty() {
        return isRNA;
    }

    public SimpleBooleanProperty isMatchingNotationProperty(){ return isMatchingNotation;}
}

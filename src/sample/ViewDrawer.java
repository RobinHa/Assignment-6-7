package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import sample.GivenCode.Graph;
import sample.GivenCode.SpringEmbedder;
import sample.nucleotides.NucleotideNode;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.random;
import static sample.nucleotides.NucleotideBySymbol.createNucleotideBySymbol;

/**
 * Created by Robin on 01.12.2015.
 * This class is needed, because otherwise ViewImplementer and Functionality will need eachother.
 */
public class ViewDrawer {

    private ViewImplementer viewImplementer;
    private Functionality functionality;

    public ViewDrawer (Functionality functionality, ViewImplementer viewImplementer) {
        this.viewImplementer = viewImplementer;
        this.functionality = functionality;

        this.createBindings();
        viewImplementer.nussinovButton.setOnAction((e) -> functionality.runNussinov());
        viewImplementer.drawButton.setOnAction((e) -> viewImplementer.rna2DArea = drawRnaStructure());
    }


    /**
     * creates the Bindings for the buttons, was moved from ViewImplementer
     */
    private void createBindings(){
        viewImplementer.seqeucneInput.textProperty().bindBidirectional(functionality.sequenceProperty());
        viewImplementer.dotBracketInput.textProperty().bindBidirectional(functionality.dotBracketProperty());
        viewImplementer.drawButton.disableProperty().bind(functionality.isMatchingNotationProperty().not()
                .and(functionality.isRNAProperty().not()));
        viewImplementer.nussinovButton.disableProperty().bind((functionality.isRNAProperty()).not());

    }

    public Pane drawRnaStructure(){


        viewImplementer.rna2DArea.getChildren().clear();
        Graph graph=new Graph();

        try {

            // The Nucleotide nodes, and conecting lines
            ArrayList<NucleotideNode> nucleotides = new ArrayList<>();
            ArrayList<Line> backboneList = new ArrayList<>();
            ArrayList<Line> bondsList = new ArrayList<>();

            final int ITERATIONS = 75;

            double[][] coordinates;

            graph.parseNotation(functionality.getDotBracket());
            double[][] newCoordinates = SpringEmbedder.computeSpringEmbedding(ITERATIONS,
                    graph.getNumberOfNodes(), graph.getEdges(), null);
            double[][] animationStartingCoordinates = newCoordinates;

            // Only if animated is checked
            if(viewImplementer.animate.isSelected()){
                animationStartingCoordinates = initializeCoordinates(newCoordinates);
            }

            SpringEmbedder.centerCoordinates(newCoordinates, 15, (int) viewImplementer.rna2DArea.widthProperty().intValue() - 15,
                    15, (int) viewImplementer.rna2DArea.heightProperty().intValue() - 15);


            /**  Assigns the coordinates for each element in the graph**/
            nucleotides =  assignNucleotideCoordinates(newCoordinates);
            backboneList = bindBackboneCoordinates(newCoordinates, nucleotides);
            bondsList = bindBondsCoordinates(newCoordinates, nucleotides, graph);

//            for (int i=1; i < newCoordinates.length; i++){
//                Line backboneLine;
//
//                if (i>0 && i < newCoordinates.length){
//                    backboneLine = new Line(animationStartingCoordinates[i][0], animationStartingCoordinates[i][1],
//                            animationStartingCoordinates[i-1][0], animationStartingCoordinates[i-1][1]);
//                    backboneLine.setStroke(Color.BLACK);
//                    backboneList.add(backboneLine);
//
//                    backboneLine.endXProperty().bindBidirectional(nucleotides.get(i-1).layoutXProperty());
//                    backboneLine.startXProperty().bindBidirectional(nucleotides.get(i).layoutXProperty());
//
//                    backboneLine.endYProperty().bindBidirectional(nucleotides.get(i-1).layoutYProperty());
//                    backboneLine.startYProperty().bindBidirectional(nucleotides.get(i).layoutYProperty());
//                }
//
//
//                int pair = boundTo(graph.getEdges(), i);
//                if (pair != -1){
//                    Line l = new Line(animationStartingCoordinates[i][0], animationStartingCoordinates[i][1],
//                            animationStartingCoordinates[pair][0], animationStartingCoordinates[pair][1]);
//                    l.setStroke(Color.CORAL);
//                    bondsList.add(l);
//                    l.startXProperty().bind(nucleotides.get(i).layoutXProperty());
//                    l.startYProperty().bind(nucleotides.get(i).layoutYProperty());
//                    l.endXProperty().bind(nucleotides.get(pair).layoutXProperty());
//                    l.endYProperty().bind(nucleotides.get(pair).layoutYProperty());
//                }
//
//            }

            viewImplementer.rna2DArea.getChildren().addAll(backboneList);
            viewImplementer.rna2DArea.getChildren().addAll(bondsList);

            nucleotides.forEach(e -> viewImplementer.rna2DArea.getChildren().addAll(e.getCircle()));
        }catch (IOException e){
            System.err.print(e);
        }
        return null;
    }

    /**
     * assigns the coordinates of the nucleotideNodes
     * @param coordinates
     * @return ArrayList<NucleotideNode>
     *
     * </NucleotideNode>
     */
    private ArrayList<NucleotideNode> assignNucleotideCoordinates(double[][] coordinates){

        ArrayList<NucleotideNode> nucleotideList = new ArrayList<>();

        for (int i=0; i < coordinates.length; i++){

            char charRepresentation = functionality.sequenceProperty().getValue().charAt(i);
            NucleotideNode nucleotideNode = createNucleotideBySymbol(charRepresentation);

            nucleotideList.add(nucleotideNode);

            nucleotideNode.setLayoutX(coordinates[i][0]);
            nucleotideNode.setLayoutY(coordinates[i][1]);

            nucleotideNode.getCircle().setLayoutX(coordinates[i][0]);
            nucleotideNode.getCircle().setLayoutY(coordinates[i][1]);

        }

        return nucleotideList;
    }

    private ArrayList<Line> bindBackboneCoordinates(double[][] coordinates, ArrayList<NucleotideNode> nucleotideNodesList){

        ArrayList<Line> backboneList = new ArrayList<>();

        for (int i=1; i < coordinates.length; i++){
            Line backboneLine;

               if (i>0 && i < coordinates.length){
                   backboneLine = new Line(coordinates[i][0], coordinates[i][1],
                            coordinates[i-1][0], coordinates[i-1][1]);
                   backboneLine.setStroke(Color.BLACK);
                   backboneList.add(backboneLine);

                   backboneLine.endXProperty().bindBidirectional(nucleotideNodesList.get(i - 1).layoutXProperty());
                   backboneLine.startXProperty().bindBidirectional(nucleotideNodesList.get(i).layoutXProperty());

                   backboneLine.endYProperty().bindBidirectional(nucleotideNodesList.get(i - 1).layoutYProperty());
                   backboneLine.startYProperty().bindBidirectional(nucleotideNodesList.get(i).layoutYProperty());
               }

            }

        return backboneList;
    }

    private ArrayList<Line> bindBondsCoordinates(double[][] coordinates, ArrayList<NucleotideNode> nucleotideNodesList, Graph graph){

        ArrayList<Line> bondsList = new ArrayList<>();

        for(int i=0; i<coordinates.length; i++) {
            int pair = boundTo(graph.getEdges(), i);
            if (pair != -1) {
                Line line = new Line(coordinates[i][0], coordinates[i][1],
                        coordinates[pair][0], coordinates[pair][1]);
                line.setStroke(Color.BLACK);
                bondsList.add(line);
                line.startXProperty().bindBidirectional(nucleotideNodesList.get(i).layoutXProperty());
                line.startYProperty().bindBidirectional(nucleotideNodesList.get(i).layoutYProperty());
                line.endXProperty().bindBidirectional(nucleotideNodesList.get(pair).layoutXProperty());
                line.endYProperty().bindBidirectional(nucleotideNodesList.get(pair).layoutYProperty());
            }
        }

        return bondsList;
    }
    /**
     * Checks in the List if a base has a pair, i dont know wh it works that way, it was computed before.
     * @param list
     * @param check
     * @return
     */
    private int boundTo(int[][] list, int check){
        boolean foundFirst = false;


        for(int i=0; i< list.length; i++){
            if(list[i][0] == check){
                if(foundFirst){
                    return list[i][0]; }
                else{
                    foundFirst = true;
                }

            }
        }

        return -1;
    }


    private double[][] initializeCoordinates(double[][] coordinates){

        if(coordinates == null) return null; // catch if null

        double[][] startingCoordinates = new double[coordinates.length][coordinates[0].length];

        for(int i=0; i<coordinates.length; i++){
            coordinates[i][0] = (random() * viewImplementer.rna2DArea.widthProperty().get());
            coordinates[i][1] = (random() * viewImplementer.rna2DArea.heightProperty().get());
        }
        return startingCoordinates;
    }




}

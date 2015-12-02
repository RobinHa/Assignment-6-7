package sample.nucleotides;

/**
 * Created by Robin on 30.12.2015.
 */
public class NucleotideBySymbol{

    public static NucleotideNode createNucleotideBySymbol(char nucleotideRep){

        NucleotideNode newNucleotideNode;

        switch (nucleotideRep){
            case 'A':
            case 'a':
                newNucleotideNode = new Adenin();
                break;
            case 'G':
            case 'g':
                newNucleotideNode = new Guanin();
                break;
            case 'C':
            case 'c':
                newNucleotideNode = new Cytosin();
                break;
            case 'U':
            case'u':
                newNucleotideNode = new Uracil();
                break;
            default: return null;
        }
        return newNucleotideNode;
    }
}

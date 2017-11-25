package collaborativedoctp4;

import java.util.ArrayList;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MerkleTree mTree = MerkleTreeBuilder.buildMerkleTreeFromFile();
        System.out.println("------------------------------------------------------------------");
        System.out.println("Start traversing the tree (with a depth-firt style) to see which hashes were calculated inside, and in which type of node they are stored:");
        MerkleTreeBuilder.checkMerkleTree(mTree);
        System.exit(0);
    }
      
}

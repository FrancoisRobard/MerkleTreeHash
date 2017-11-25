package collaborativedoctp4;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * Class with public static functions containing the functions for building a Merkle Tree from a file.
 * @author francois
 */
public class MerkleTreeBuilder {
    /**
     * Function to get the path of a file using the windows file browser.
     * @return 
     */
    public static String loadFile(){
        JFrame fenetre = new JFrame();
            FileDialog fd = new FileDialog(fenetre, "Choisissez un fichier txt à charger :", FileDialog.LOAD);
            fd.setDirectory("C:\\");
            fd.setFile("*.txt");
            fd.setVisible(true);
            fd.setAlwaysOnTop(true);
            String filename = fd.getFile();
            if(filename == null){
                System.out.print("Vous avez annulé la sélection.");
                System.exit(1);
            }
            else{
                System.out.println("Vous avez choisi : "+fd.getDirectory()+filename);
            }
         return fd.getDirectory()+filename;
    }
    
    /**
     * Function to build a Merkle tree from a file (each line of the file is considered as an event).
     * @return 
     */
    public static MerkleTree buildMerkleTreeFromFile(){
        try{
            String filePath = loadFile();
            InputStream ips=new FileInputStream(filePath); // Open file
            InputStreamReader ipsr=new InputStreamReader(ips); // Open lecture mode
            BufferedReader br=new BufferedReader(ipsr); // Open buffered reader
            
            String event;
            ArrayList<String> eventsList = new ArrayList<>();
            while ((event = br.readLine()) != null) {
                eventsList.add(event);
            }
            return buildMerkleTreeFromList(eventsList);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }
    
    /**
     * Function to build a Merkle tree from a list (each element of the list is considered as an event).
     * @return 
     */
    public static MerkleTree buildMerkleTreeFromList(ArrayList<String> batchEvents) throws NoSuchAlgorithmException, IOException{
        int eventCounter=0;
        ArrayList<MerkleTree> mTree = new ArrayList<>(); // the list that will countain our Merkle tree
        for (String event : batchEvents) { // Reading the file line by line (= event after event)
            eventCounter+=1;
            // For each event, computing the associated tree
            System.out.println("Here is the content of event n°"+eventCounter+" : "+event);
            MerkleTree leaf = new Leaf(event,eventCounter);
            mTree.add(leaf);
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Start building the merkle tree for those "+mTree.size()+" events...");
        while(mTree.size()>1){ // Now calculating the interal nodes of our tree (hashing the leaves two by two) until we get only one principal node
            mTree=computeInternalNodes(mTree);
        }
        System.out.println("The Merkle Tree has been built successfully !  "+mTree.size());
        return mTree.get(0);
    }
    
    /**
     * Function that calculates the interal nodes of our tree by hashing the leaves two by two.
     * @param mTree
     * @return 
     */
    private static ArrayList<MerkleTree> computeInternalNodes(ArrayList<MerkleTree> mTree) throws IOException, NoSuchAlgorithmException{
        ArrayList<MerkleTree> internalNodesTree = new ArrayList<>();
        int length =mTree.size();
        for(int i=0;i<length/2;i++){ // Getting the nodes of the tree two by two, computing their hash in a new inIernal Node, and adding this node to the new Internal Nodes list.
            MerkleTree node1=mTree.get(2*i);
            MerkleTree node2=mTree.get(2*i+1);
            MerkleTree iNode = new InternalNode(node1,node2);
            internalNodesTree.add(iNode);
        }
        // If the given list of node was even, the computation is finished.
        // If the given list of node was odd, we add its last node to our new internal Nodes list (as there is no other node left to compute its hash with)
        if(length%2!=0){
            internalNodesTree.add(mTree.get(length-1));
        }
        return internalNodesTree;
    }
    
    /**
     * Prints the names of the hashes that were computed it the given Merkle Tree (travesing the tree in a depth-first method).
     * For testing puposes.
     * @param mTree 
     */
    public static void checkMerkleTree(MerkleTree mTree){
        if(mTree.getBegenningIndex()!=mTree.getEndingIndex()){
            System.out.println(" > hash "+mTree.getBegenningIndex()+".."+mTree.getEndingIndex()+" (Internal Node)");
            checkMerkleTree(mTree.getLeftSubTree());
            checkMerkleTree(mTree.getRightSubTree());
        }else{
            System.out.println(" > hash "+mTree.getBegenningIndex()+"    (leaf with event = '"+mTree.getEvent()+"')");
        }
    }
    
    public static MerkleTree genProof(MerkleTree mTree, MerkleTree subTree){
        return null;
    }
    
}



package collaborativedoctp4;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JFrame;

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
    public static MerkleTree buildMerkleTree(){
        try{
            String filePath = loadFile();
            InputStream ips=new FileInputStream(filePath); // Open file
            InputStreamReader ipsr=new InputStreamReader(ips); // Open lecture mode
            BufferedReader br=new BufferedReader(ipsr); // Open buffered reader
            String event; // Variable that will contain lines of the file (=events)
            int eventCounter=0;
            ArrayList<MerkleTree> mTree = new ArrayList<>(); // the list that will countain our Merkle tree
            while ((event = br.readLine()) != null) { // Reading the file line by line (= event after event)
                eventCounter+=1;
                // For each event, computing the associated tree
                System.out.println("Here is the content of the event n°"+eventCounter+" : "+event);
                MerkleTree leaf = new Leaf(event,eventCounter);
                mTree.add(leaf);
            }
            while(mTree.size()>=1){ // Now calculating the interal nodes of our tree (hashing the leaves two by two) until we get only one principal node 
                mTree=computeInternalNodes(mTree);
            }   
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }
    
    /**
     * Function that calculates the interal nodes of our tree by hashing the leaves two by two.
     * @param mTree
     * @return 
     */
    private static ArrayList<MerkleTree> computeInternalNodes(ArrayList<MerkleTree> mTree){
        
        return null;
    }
}

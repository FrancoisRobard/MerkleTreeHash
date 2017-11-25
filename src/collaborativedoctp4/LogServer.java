package collaborativedoctp4;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author francois
 */
public class LogServer implements LogServerInterface{
    
    protected MerkleTree currentHashTree;
    
    /**
     * Constructor that instantiate a Merkle Tree from a log file
     */
    public LogServer(){
        currentHashTree = MerkleTreeBuilder.buildMerkleTreeFromFile();
    }
    
    /**
     * Gets the hash of the root node of the current Merkle Tree
     * @return 
     */
    @Override
    public byte [] getCurrentRootHash(){
        return currentHashTree.getHash();
    }
    
    /**
     * Creates the Leaf (generating the hash of the new event) associated to the new event, and then creates a new hash for all the events + the new oneand sets the global tree as the new Merkle Tree.
     * @param event
     * @throws NoSuchAlgorithmException
     * @throws IOException 
     */
    @Override
    public void appendOneEvent(String event) throws NoSuchAlgorithmException, IOException{
        int newEventIndex = currentHashTree.getEndingIndex()+1;
        MerkleTree newLeaf = new Leaf(event,newEventIndex);
        currentHashTree = new InternalNode(currentHashTree,newLeaf);
    }
    
    /**
     * Generates the Merkle Tree for all the new batched event, and then computes the hash for all the events (old events + new events) and sets the global tree as the new Merkle Tree.
     * @param batchEvents
     * @throws NoSuchAlgorithmException
     * @throws IOException 
     */
    @Override
    public void appendBatch(ArrayList<String> batchEvents) throws NoSuchAlgorithmException, IOException{
        MerkleTree batchTree = MerkleTreeBuilder.buildMerkleTreeFromList(batchEvents);
        currentHashTree = new InternalNode(currentHashTree,batchTree);
    }
    
    /**
     * 
     * @param index
     * @return 
     */
    @Override
    public ArrayList<byte []> genPath(int index){
        if(index<currentHashTree.getBegenningIndex() || index>currentHashTree.getEndingIndex()){
            System.out.println("The asked number of event is not included in the hash");
            return null;
        }
        MerkleTree workingTree = currentHashTree;
        ArrayList<byte[]> bytesForProof = new ArrayList<>();
        while(workingTree.getBegenningIndex()!=index && workingTree.getEndingIndex()!=index){ // While we have not reach the leaf for the asked number
            bytesForProof.add(workingTree.getHash());                                         // We add the current working hash 
            MerkleTree subTree1 = workingTree.getLeftSubTree();
            if(subTree1.getBegenningIndex()<=index && index<=subTree1.getEndingIndex()){      // and we find the next subtree that includes the hash in the given index
                workingTree=subTree1;
            }else{
                workingTree=workingTree.getRightSubTree();
            }
        }
        return bytesForProof;
    }
    
    @Override
    public void genProof(MerkleTree mTree, MerkleTree subTree){
        
    }
    
}

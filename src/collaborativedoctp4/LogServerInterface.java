package collaborativedoctp4;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author francois
 */
public interface LogServerInterface {
    public byte [] getCurrentRootHash();
    public void appendOneEvent(String event)throws NoSuchAlgorithmException, IOException;
    public void appendBatch(ArrayList<String> batchEvents)  throws NoSuchAlgorithmException, IOException;
    public ArrayList<byte []> genPath(int index);
    public void genProof(MerkleTree mTree, MerkleTree subTree);
}

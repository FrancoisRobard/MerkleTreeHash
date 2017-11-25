package collaborativedoctp4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class (extending the Tree class) implementing the Internal Nodes of the Merkle Tree.
 * Here only writing th constructor is needed. It computes a hash with the two hashes of the given Merkle tree objects(with 0x01 prepended before the two hashes bytes).
 * @author francois
 */
public class InternalNode extends Tree{
    
    public InternalNode(MerkleTree leftTree, MerkleTree rightTree) throws IOException, NoSuchAlgorithmException{
        event=null;
        // The right tree is supposed to be the higher one, in term of indexes of events
        beginningIndex = leftTree.getBegenningIndex();
        endingIndex = rightTree.getEndingIndex();
        leftSubTree = leftTree;
        rightSubTree = rightTree;
        // Getting the bytes for 0x00 and for the event
        byte [] one = new byte[]{0x01};
        // Concatenating the two bytes arrays
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(one);
        outputStream.write(leftTree.getHash());
        outputStream.write(rightTree.getHash());
        byte [] toBeHashed = outputStream.toByteArray( );
        // Hashing the bytes
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        hash = digest.digest(toBeHashed);
    }
    
}

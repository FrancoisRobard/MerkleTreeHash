package collaborativedoctp4;

/**
 * Interface for the Merkle tree
 * @author francois
 */
public interface MerkleTree {
    public byte [] getHash();
    public MerkleTree getLeftSubTree();
    public MerkleTree getRightSubTree();
    public int getBegenningIndex();
    public int getEndingIndex();
}


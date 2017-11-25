package collaborativedoctp4;

public interface MerkleTree {
    public byte [] getHash();
    public MerkleTree getLeftSubTree();
    public MerkleTree getRightSubTree();
    public int getBegenningIndex();
    public int getEndingIndex();
}


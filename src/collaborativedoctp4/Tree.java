package collaborativedoctp4;

/**
 * Abstract implementation of the Meklt Tree, with the attributes and main getters functions.
 * @author francois
 */
public abstract class Tree implements MerkleTree{
    protected byte [] hash;
    protected MerkleTree leftSubTree;
    protected MerkleTree rightSubTree;
    protected int beginningIndex;
    protected int endingIndex;
    
    @Override
    public byte [] getHash(){return hash;};
    @Override
    public MerkleTree getLeftSubTree(){return leftSubTree;};
    @Override
    public MerkleTree getRightSubTree(){return rightSubTree;};
    @Override
    public int getBegenningIndex(){return beginningIndex;};
    @Override
    public int getEndingIndex(){return endingIndex;};
}

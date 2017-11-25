package collaborativedoctp4;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Class (extending the Tree class) implementing the Leaves.
 * Here only writing th constructor is needed. It computes the hash of its event (with 0x00 prepended before the event bytes).
 * @author francois
 */
public class Leaf extends Tree{
    
    public Leaf(String event, int nb) throws NoSuchAlgorithmException, IOException{
        this.event=event;
        beginningIndex = nb;
        endingIndex = nb;
        leftSubTree = null;
        rightSubTree = null;
        // Getting the bytes for 0x00 and for the event
        byte [] zeros = new byte[]{0x00};
        byte [] eventBytes = event.getBytes(StandardCharsets.UTF_8);
        // Concatenating the two bytes arrays
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(zeros);
        outputStream.write(eventBytes);
        byte [] toBeHashed = outputStream.toByteArray( );
        // Hashing the bytes
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        hash = digest.digest(toBeHashed);
    }
    
}

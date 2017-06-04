package hashTables;
import cs1c.SongEntry;
/**
 * Song Hashing based on ID requires this class as wrapper
 * @author Myron Pow 6/3/2017
 */
public class SongCompInt implements Comparable<Integer>{
    /**
     *SongEntry that is being wrapped
     */
    private SongEntry object;

    /**
     * Constructor, sets the internal SongEntry to the given SongEntry
     * @param song SongEntry to be wrapped
     */
    public SongCompInt(SongEntry song){
        object = song;
    }

    /**
     * Basic toString
     * @return a string containing the SongEntry
     */
    public String toString(){
        return object.toString();
    }

    /**
     * compareTo using subtraction to find a match
     * @param key value to compare
     * @return 0 if object is the same, all other results are not key
     */
    public int compareTo(Integer key){
        return object.getID() - key;
    }

    /**
     * Basic equals
     * @param target target to match
     * @return true if the object values are equal
     */
    public boolean equals(SongCompInt target){
        return object.equals(target.object);
    }

    /**
     * Basic hashCode
     * @return ID of SongEntry
     */
    public int hashCode(){
        return object.getID();
    }
}

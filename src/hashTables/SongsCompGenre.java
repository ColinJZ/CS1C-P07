package hashTables;
import java.util.*;
import cs1c.SongEntry;
/**
 * Wrapper class for SongEntry
 * @author Myron Pow 6/3/2017
 */
public class SongsCompGenre implements Comparable<String> {
    /**
     * Contains the name of the genre as a string
     */
    private String objectGenre;
    /**
     * Wrapped SongEntry object
     */
    private ArrayList<SongEntry> object;

    /**
     * Default Constructor
     */
    public SongsCompGenre(){
        object = new ArrayList<SongEntry>();
        objectGenre = "N/A";
    }

    /**
     * Constructor that takes SongEntry
     * @param current the SongEntry being wrapped
     */
    public SongsCompGenre(SongEntry current){
        this();
        this.objectGenre = current.getGenre();
        this.object.add(current);
    }

    /**
     * Basic toString
     * @return string of SongEntry
     */
    public String toString(){
        return object.toString();
    }

    /**
     * Calls SongEntry's compareTo
     * @param target target for comparison
     * @return int based on comparison
     */
    public int compareTo(String target){
        return objectGenre.compareTo(target);
    }

    /**
     * Calls SongEntry's equals
     * @param target target for equality check
     * @return bool based on comparison
     */
    public boolean equals(SongsCompGenre target){
        return object.equals(target.object);
    }

    /**
     * Calls SongEntry's hashCode
     * @return int based on the SongEntry
     */
    public int hashCode(){
        return objectGenre.hashCode();
    }

    /**
     * Accessor for name of genre
     * @return name of genre as a string
     */
    public String getName(){
        return objectGenre;
    }

    /**
     * Accessor for internal SongEntry arrayList
     * @return SongEntry Arraylist
     */
    public ArrayList<SongEntry> getData(){
        return object;
    }
}

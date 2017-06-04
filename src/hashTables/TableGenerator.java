package hashTables;
import cs1c.SongEntry;
import java.util.*;
/**
 * Class that generates the Hashing tables for MyTunes
 * @author Myron Pow 6/3/2017
 */
public class TableGenerator {
    /**
     * table of hash values based on ID
     */
    private FHhashQPwFind<Integer, SongCompInt> tableOfSongIDs = new FHhashQPwFind<Integer, SongCompInt>();
    /**
     * table of hash values based on genre
     */
    private FHhashQPwFind<String, SongsCompGenre> tableOfSongGenres = new FHhashQPwFind<String, SongsCompGenre>();
    /**
     * Necessary for the output of found genres
     */
    private ArrayList<String> genreNames = new ArrayList<String>();

    /**
     * Populates the ID based hash table
     * @param allSongs SongEntry's to hash
     * @return table of hash values
     */
    public FHhashQPwFind<Integer, SongCompInt> populateIDtable(SongEntry[] allSongs){
        for(int i = 0; i < allSongs.length; i++){
            SongCompInt song = new SongCompInt(allSongs[i]);
            tableOfSongIDs.insert(song);
        }
        return tableOfSongIDs;
    }

    /**
     * Populates the genre based hash table
     * @param allSongs SongEntry's to hash
     * @return table of hash values
     */
    public FHhashQPwFind<String, SongsCompGenre> populateGenreTable(SongEntry[] allSongs){
        for(int i = 0; i < allSongs.length; i++){
            try{
                SongsCompGenre currentGenre = tableOfSongGenres.find(allSongs[i].getGenre());
                currentGenre.getData().add(allSongs[i]);
            }
            catch(Exception e)
            {
                SongsCompGenre song = new SongsCompGenre(allSongs[i]);
                tableOfSongGenres.insert(song);
                genreNames.add(allSongs[i].getGenre());
            }
        }
        return tableOfSongGenres;
    }

    /**
     * Accessor method for printing
     * @return list of found genres
     */
    public ArrayList<String> getGenreNames(){
        return genreNames;
    }

}

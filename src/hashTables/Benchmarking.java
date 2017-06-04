package hashTables;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import cs1c.MillionSongDataSubset;
import cs1c.SongEntry;
import cs1c.TimeConverter;

/**
 * Tests the functionality of FHhashQPwFind.java.
 * Specifically checks for implementation of find() function to return an object associated with a given key input.
 *
 * @author Foothill College, Bita M [YOUR NAME HERE]
 */
public class Benchmarking {
    public static final boolean SHOW_DETAILS = false;

    // Two hash tables of type FHhashQPwFind which extends parent class FHhashQP --------

    // TODO: Define the wrapper class SongCompInt for SongEntry objects,
    //       which would compare SongEntry objects based on the song's int id field.
    private FHhashQPwFind<Integer, SongCompInt> tableOfSongIDs;

    // TODO: Define the wrapper class SongCompGenre for SongEntry objects,
    //       which would compare SongEntry objects based on the String genre field and
    //       determines the number of songs in each genre.
    private FHhashQPwFind<String, SongsCompGenre> tableOfGenres;

    // List of genres found while populating tableOfGenres field
    private ArrayList<String> genreNames;


    /**
     * Populates two hash tables:
     * - a hash table for comparing songs based on their int field ID.
     * - a hash table for comparing songs based on their String field genre.
     *
     * @param allSongs Array of SongEntry objects
     */
    public Benchmarking(SongEntry[] allSongs) {
        // TODO: Define the TableGenerator class to have two class fields of type
        //       FHhashQPwFind which extend the parent class FHhashQP.
        TableGenerator g = new TableGenerator();

        // TODO: Populates a hash table for comparing songs based on their int field ID.
        tableOfSongIDs = g.populateIDtable(allSongs);

        // TODO: Populates a hash table for comparing songs based on their String field genre.
        //       Uses this table to also populates list of genre names with unique keys.
        tableOfGenres = g.populateGenreTable(allSongs);

        // TODO: Return the unique genre names found when populating genre table
        genreNames = g.getGenreNames();
    }

    /**
     * Uses MillionSongDataSubset to read all songs from data file.
     *
     * @param jsonFile A JSON file to parse
     * @return The array of SongEntry objects
     */
    public static SongEntry[] readSongsFromDataFile(String jsonFile) {
        // parses the JSON input file
        MillionSongDataSubset msd = new MillionSongDataSubset(jsonFile);

        // retrieves the parsed objects
        SongEntry[] allSongs = msd.getArrayOfSongs();

        // displays the number of songs read from the input file
        System.out.printf("Total number of songs read %d \n", allSongs.length);

        return allSongs;
    }


    /**
     * Basic file reader which reads a file with format:
     * [value to find]
     * [value to find]
     * etc.
     * And stores each value into a list.
     *
     * @param filename The input file to read.
     * @return Read lines as a list.
     */
    public ArrayList<String> readFindRequests(String filename) {
        ArrayList<String> requests = new ArrayList<>();
        Scanner input = null;

        try {
            File infile = new File(filename);
            input = new Scanner(infile);

            String line = "";
            while (input.hasNextLine()) {
                line = input.nextLine();

                requests.add("" + line);
            } // while more lines in file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (input != null)
                input.close();
        }
        return requests;
    }

    /**
     * Modified testIDtable
     * @param filename The input file to read.
     */
    public void idHashBenchmark(String filename) {
        System.out.printf("Test file for id table: %s \n", filename);
        ArrayList<String> idsToFind = readFindRequests(filename);
        for (String idStr : idsToFind) {
            /**
             * Code block for benchmarking a find by ID for hash tables
             */
            long startTime, estimatedTime;
            startTime = System.nanoTime();
            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                System.out.printf("\nWarning: Input \"%s\" is not a valid number. Skipping.\n", idStr);
                continue;
            }

            System.out.printf("\nFinding song id: %d\n", id);

            try {
                // TODO: Define the wrapper class for a SongEntry object such that
                //       it compares objects based on a song's integer id field.
                SongCompInt compResult = tableOfSongIDs.find(id);
                if (compResult != null) {
                    estimatedTime = System.nanoTime() - startTime;
                    System.out.printf("Song id %d found in tableOfSongIDs in " + TimeConverter.convertTimeToString(estimatedTime) + ". \n", id);
                }
            } catch (NoSuchElementException e) {
                System.out.printf("Song id %d NOT found in tableOfSongIDs.\n", id);
            }
        } // for all requested IDs to find

        System.out.println("Done with testing table of ids.\n");
    }

    /**
     * Creates an object of type MyTunes class that, which reads the song information from a JSON input file
     * and stores all entries in an array of SongEntry objects.
     * Constructs an object of MyTunes, which populates two hash tables.
     * Each tables uses different attribute of SongEntry class as the key to find.
     * Tests finding keys in each hash table and reports whether requested key is found.
     */
    public static void main(String[] args) {
        final String DATAFILE = "resources/music_genre_subset.json";    // Directory path for JSON file
        final String TESTFILE01 = "resources/benchIDs.txt";    // Example test file for hashing based on IDs
        //final String TESTFILE02 = "resources/findGenres.txt"; // Example test file for hashing based on genres names

        // Note: This is similar to your previous projects.
        //		 Placed in a separate method for readability.
        // Parses the input file and stores all songs in an array of SongEntry object.
        SongEntry[] allSongs = readSongsFromDataFile(DATAFILE);

        // The constructor of class builds the hash tables
        Benchmarking theStore = new Benchmarking(allSongs);

        System.out.println("PAST THIS IS HASH");
        System.out.println("PAST THIS IS HASH");
        System.out.println("PAST THIS IS HASH");

        // Tests FHhashQPwFind and SongCompInt
        theStore.idHashBenchmark(TESTFILE01);

        System.out.println("PAST THIS IS SEQUENTIAL");
        System.out.println("PAST THIS IS SEQUENTIAL");
        System.out.println("PAST THIS IS SEQUENTIAL");
        // Tests sequential find
        theStore.idSeqBenchmark(TESTFILE01, allSongs);
        // flush the error stream
        System.err.flush();
        System.out.println("\nDone with Benchmarking.");
    }

    /**
     * For Testing Purposes
     * @param filename file to search from
     * @param linear array to be linearly searched
     */
    public void idSeqBenchmark(String filename, SongEntry[] linear) {
        System.out.printf("Test file for id table: %s \n", filename);
        ArrayList<String> idsToFind = readFindRequests(filename);
        for (String idStr : idsToFind) {
            /**
             * Code block for benchmarking a find by ID for hash tables
             */
            long startTime, estimatedTime;
            startTime = System.nanoTime();
            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                System.out.printf("\nWarning: Input \"%s\" is not a valid number. Skipping.\n", idStr);
                continue;
            }

            System.out.printf("\nFinding song id: %d\n", id);

            for (int i = 0; i < linear.length; i++){
                if (id == linear[i].getID()){
                    estimatedTime = System.nanoTime() - startTime;
                    System.out.printf("Song id %d found in tableOfSongIDs in " + TimeConverter.convertTimeToString(estimatedTime) + ". \n", id);
                }
            }

        } // for all requested IDs to find

        System.out.println("Done with testing table of ids.\n");
    }
}
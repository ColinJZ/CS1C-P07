package hashTables;
import java.util.*;

/**
 * Hashing implementation, as inherited from FHhashQP
 * @author Myron Pow, 6/2/2017
 */
public class FHhashQPwFind<KeyType, E extends  Comparable<KeyType>> extends FHhashQP<E> {

    /**
     * Finds object based on hash key
     * @param key value to find
     * @return the object or an exception
     */
    public E find(KeyType key){
        int target = findPosKey(key);
        if(mArray[target].state != ACTIVE)
            throw new NoSuchElementException();
        return mArray[target].data;
    }

    /**
     * Protected method for the implmentation of quadratic probing in hashing
     * @param key key to find
     * @return index of data
     */
    protected int findPosKey(KeyType key){
        int kthOddNum = 1;
        int index = myHashKey(key);

        while ((mArray[index].state != EMPTY) && (mArray[index].data.compareTo(key) != 0)){
            index += kthOddNum;
            kthOddNum += 2;
            if(index >= mTableSize)
                index -= mTableSize;
        }
        return index;
    }

    /**
     * hash based on key values
     * @param key to create a hash
     * @return a key for hash finding
     */
    protected int myHashKey(KeyType key){
        int hashValue;
        hashValue = key.hashCode() % mTableSize;
        if(hashValue < 0)
            hashValue += mTableSize;
        return hashValue;
    }
}

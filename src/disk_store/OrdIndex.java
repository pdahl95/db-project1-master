package disk_store;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * An ordered index.  Duplicate search key values are allowed,
 * but not duplicate index table entries.  In DB terminology, a
 * search key is not a superkey.
 * 
 * A limitation of this class is that only single integer search
 * keys are supported.
 *
 */

public class OrdIndex implements DBIndex {
	
	/**
	 * Create an new ordered index.
	 */

	List<Pair<Integer,Integer>> index;

	public OrdIndex() {
	    index = new ArrayList<>();
	}
	
	@Override
	public List<Integer> lookup(int key) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void insert(int key, int blockNum) {
	    // binary search for pair
        int getIndex = binarySearch( 0, index.size() - 1, key, blockNum );
        System.out.println(getIndex);
        // if pair is NOT in index:
	    Pair<Integer,Integer> newPair = new Pair(key, blockNum);
	    index.add(newPair);
	}

	@Override
	public void delete(int key, int blockNum) {
		// binary search for pair

        // if pair IS found:
        // shift all entries after found index
	}

	/**
	 * Return the number of entries in the index
	 * @return
	 */
	public int size() {
		return index.size();
	}

    public int binarySearch(int left, int right, int key, int blockNum){
        if (right >= left) {
            int mid = left + (right - left)/2;

            boolean foundKey = false;
            boolean foundValue = false;
            while (index.get(mid).getKey() == key) {
                foundKey = true;
                if (index.get(mid).getValue() == blockNum) {
                    foundValue = true;
                    break;
                }
                mid++;
            }
            if(foundKey && foundValue) {
                return mid;
            } else if(foundKey) {
                return mid * -1;
            }
            // check if last two numbers are less than and greater than the search key
//            if(left + 1 == right){
//                if(index.get( left ).getKey() < index.get( mid ).getKey() && index.get( right ).getKey() > index.get( mid ).getKey()){
//                    return left * -1;
//                }
//            }
            if (index.get(mid).getKey() > key)
                return binarySearch(left, mid-1, key, blockNum);
            return binarySearch(mid+1, right, key, blockNum);
        }
        return left * -1;
    }
	
	@Override
	public String toString() {
		throw new UnsupportedOperationException();
	}
}

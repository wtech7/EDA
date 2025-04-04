package aplicaciones.census;

import libraries.dataStructures.models.ListPOI;
import libraries.dataStructures.linear.LinkedListPOI;
import libraries.dataStructures.linear.SortedLinkedListPOI;

/**
 * VoterList: represents a list of residents registered in
 *            the census, and, thus, voters
 *
 * @author  EDA Professors
 * @version September 2023 (Translation Feb. 24)
 */

public class VoterList {

    private ListPOI<Resident> census;
    private int size;

    /**
     * Field getter methods
     */
    public ListPOI<Resident> getCensus() { return census; }
    public int getSize() { return size; }

    /**
     * Returns the String that represents a VoterList
     *
     * @return the String with the VoterList in the given textual format.
     */
    public String toString() {
        String res = "";
        if (size == 0) return res;
        census.begin();
        for (int pos = 0; pos <= census.size() - 2; pos++) {
            res += census.get() + ", \n";
            census.next();
        }
        res += census.get();
        return res;
    }

    /**
     * Creates a VoterList...
     *
     * @param sorted A boolean that shows whether the census must be
     *               sorted in ascending order (true) or not (false).
     * @param n     An int that shows the size (number of elements) of the list
     */
    public VoterList(boolean sorted, int n) {
        size = n;
        // TO BE COMPLETED
        boolean sort = true;
        if ( sorted ) {
            census = new SortedLinkedListPOI<Resident>();
        }
        else{
            census = new LinkedListPOI<Resident>();
        }
        
        int i = 0;
        while(n>i){
            Resident nuevo = new Resident();
            if ( index(nuevo) != -1) continue;
            else{
                census.add(nuevo);
                i++;
            }
                
        
        }
        
        
    }

    /**
     * Returns the index or position of Resident r in a VoterList,
     * or -1 if r doesn't belong to the list.
     *
     * @param r a Resident
     * @return r's index in a census, an int, 0 or positive
     *         if r is in the census, or -1 otherwise.
     */
    protected int index(Resident r) {
        // TO BE COMPLETED
        int index = -1;
        census.begin();
        for ( int i = 0; i< census.size() ; i++){
            if(census.get().equals(r)) {
                index = i;
                break;
            }
            census.next();
            
        }
        return index;
        
    }

}

package libraries.dataStructures.linear;



public class SortedLinkedListPOI<E extends Comparable> extends LinkedListPOI<E>
{
    public SortedLinkedListPOI(){
        super();
    }
    
   public void add(E e) {
       begin();
       while(!isEnd() && !(this.get().compareTo(e) > 0 )) {
        next();
        }
        super.add(e);
    }

    
}

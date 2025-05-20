package applications.hospital;


/**
 * Write a description of class PriorityQueueServerPlus here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PriorityQueueServerPlus extends PriorityQueueServer  implements SurgeryServerPlus
{
    // instance variables - replace the example below with your own
    private int size;

    
  

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int numPatients(){
        return size;
    
    }
    
    public void addWaiting (Patient j  ) {
    
        size++;
        super.addWaiting(j);
    
    
    }
    public Patient transferPatient(){
        if(this.hasPatients()){
            size--;
            return pQ.removeMin();
        }
        return null;
    
    }
    public void  distributePatients(SurgeryServerPlus s ){

        int half = this.numPatients()/2;
        
        for(int i = 0; i<half; i++){
            Patient p = this.transferPatient();
            s.addWaiting(p);
        }
    }
   
}

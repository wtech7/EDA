package applications.hospital;

import libraries.dataStructures.models.PriorityQueue;
import libraries.dataStructures.hierarchical.BinaryHeap;

/**
 * PriorityQueueServer class: implements a SurgeryServer
 * using a maximum priority model (PriorityQueue) to sort
 * incoming patients in its waitlist.
 *  
 *  @author  EDA Professors
 *  @version April 2024
 */

public class PriorityQueueServer implements SurgeryServer {

    /** A PriorityQueueServer HAS A PriorityQueue of awaiting Patients. */
    protected PriorityQueue<Patient> pQ;
    
    /** Creates an empty SurgeryServer. */
    public PriorityQueueServer() {
        /* TO BE COMPLETED */
         pQ = new BinaryHeap<Patient>();
    }

    /** Includes a new Patient p in a SurgeryServer. */
    @Override
    public void addWaiting(Patient j) {
        /* TO BE COMPLETED */
        pQ.add(j);
    }

    /** Checks whether there is any Patient waiting for surgery. */
    @Override
    public boolean hasPatients() {
        /* TO BE COMPLETED */
        return !pQ.isEmpty();
    }

    /** IFF hasPatients(): returns the Patient from a SurgeryServer to be operated. */
    @Override
    public Patient getPatient() {
        /* TO BE COMPLETED */
        return pQ.getMin();
    }

    /**
     * IFF hasPatients(): removes from a SurgeryServer the Patient that
     * will be operated on, and returns that Patient, updating its
     * entersSurgery attribute.
     * @param h Timestamp (in hours) when the patient is admitted to surgery.
     */
    @Override
    public Patient operatePatient(int h) {
        /* TO BE COMPLETED */
        if( this.hasPatients() ){
            Patient p = pQ.removeMin();
            p.setEntersSurgery(h+ SURGERY_TIME);
            return p;
        }
        return null;
    }
}

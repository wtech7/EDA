package libraries.dataStructures.graphs;

/** Edge Class: represents an edge in a graph.<br>
 *
 *  @version november 2021
 */

public class Edge {

    // AN Edge HAS A source vertex AND A target vertex:
    /* TO BE COMPLETED */
     int source;
     int target;
     double weight;
    // AN Edge HAS A weight:
    /* TO BE COMPLETED */

    /** Creates an edge (a, b) with weight w.
     *
     * @param a  Source vertex
     * @param b  Target vertex
     * @param w  Weight
     */
    
    public Edge(int a, int b, double w) {
        /* TO BE COMPLETED */
        source = a;
        target = b;
        weight = w;
        
    }

    /** Returns the source vertex of an edge.
     *
     * @return int source vertex
     */
    public int getSource() {
        // CHANGE ME
        return source;
    }

    /** Returns the target vertex of an edge.
     *
     * @return int target vertex
     */
    public int getTarget() {
        // CHANGE ME
        return target;
    }

    /** Reterns an edge's weight
     *
     * @return double Weight of the edge
     */
    public double getWeight() {
        // CHANGE ME
        return weight;
    }

    /** Returns a String that represents an edge
     *  in the format (source, target, weight)
     *
     * @return  String that represents the edge
     */
    public String toString() {
        // CHANGE ME
        return "(" + this.source + ", " + this.target
                    + ", " + this.weight + ")";
    }
}

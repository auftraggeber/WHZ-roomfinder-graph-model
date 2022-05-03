package de.fhzwickau.roomfinder.model.graph.edge;

import de.fhzwickau.roomfinder.model.graph.node.Node;
import de.fhzwickau.roomfinder.model.metadata.Metadata;

import java.io.Serializable;

/**
 * Die Klasse realisiert die Verbindungen (Kanten) zwischen zwei Knoten.
 * @version 0.1.0
 * @since 0.1.0
 * @author Jonas Langner
 */
public class Edge implements Serializable {

    private static final long serialVersionUID = 1L;

    private Node[] nodes;

    @Metadata(description = "Das Gewicht der Kante (> 0)")
    private int weight;

    /**
     * Initialisiert ein neues Objekt. Die Reihenfolge der beiden ersten Parameter ist dabei zufällig.
     * Über {@link #getOther(Node)} wird die Reihenfolge aufgelöst.
     * @param n1 Knoten 1 der Kante.
     * @param n2 Knoten 2 der Kante.
     * @param weight Das Gewicht der Kante, welches für die Berechnung des optimalen Weges benötigt wird.
     * @throws IllegalStateException Die beiden Knoten müssen schon Teil des gleichen Graphen sein.
     * Falls sie das nicht sind, wird eine Exception geworfen.
     */
    public Edge(Node n1, Node n2, int weight) throws IllegalStateException {

        if (n1.getGraph() == null || n2.getGraph() == null || !n1.getGraph().equals(n2.getGraph()))
            throw new IllegalStateException("The nodes are not part of the same graph.");

        nodes = new Node[]{
                n1,
                n2
        };
        this.weight = weight;
    }

    /**
     * Gibt den anderen Knoten aus. So erfährt ein Knoten ziemlich einfach, mit wem er überhaupt
     * in Verbindung steht.
     * @param that Der Knoten, der nicht zurückgegeben werden soll.
     * @return Der andere Knoten.
     * @throws IllegalArgumentException Wenn der als Parameter angegebene Knoten gar nicht Teil der Kante ist,
     * wird eine Exception geworfen.
     */
    public Node getOther(Node that) throws IllegalArgumentException {
        if (nodes[0].equals(that))
            return nodes[1];
        else if (nodes[1].equals(that))
            return nodes[0];

        throw new IllegalArgumentException("This node is not part of the edge. Therefore there is no other participant for this node.");
    }
}

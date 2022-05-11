package de.fhzwickau.roomfinder.model.graph.node;

import de.fhzwickau.roomfinder.model.graph.Graph;
import de.fhzwickau.roomfinder.model.graph.edge.Edge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LazyNodeTest {

    Graph g1, g2;
    Node n1, n2;
    LazyNode ln1, ln2;

    @BeforeEach
    void setUp() {
        g1 = new Graph();
        g2 = new Graph();

        n1 = new NodeFactory().createTestNode().setValueOf("id", "_1").obj();
        n2 = new NodeFactory().createTestNode().setValueOf("id", "_2").obj();

        ln1 = new LazyNode("_1");
        ln2 = new LazyNode("_2");

        g1.add(n1);
        g2.add(n2);
        g1.add(ln2);
        g2.add(ln1);

        Edge e = new Edge(n1, ln2,10);
        n1.addEdge(e);
        ln2.addEdge(e);

        e = new Edge(n2, ln1, 10);
        n2.addEdge(e);
        ln1.addEdge(e);
    }

    @Test
    @DisplayName("Test setup")
    void testSetup(){
        assertEquals(1, n1.getEdges().size());
        assertEquals(1, n2.getEdges().size());
        assertEquals(1, ln1.getEdges().size());
        assertEquals(1, ln2.getEdges().size());

        assertEquals(ln2, n1.getEdges().stream().toList().get(0).getOther(n1));
        assertEquals(ln1, n2.getEdges().stream().toList().get(0).getOther(n2));
    }

    @Test
    @DisplayName("Test merge")
    void testMerge() {
        g1.addAll(g2);

        assertEquals(1, n1.getEdges().size());
        assertEquals(1, n2.getEdges().size());
        assertEquals(n1.getEdges().stream().toList().get(0), n2.getEdges().stream().toList().get(0));
    }
}
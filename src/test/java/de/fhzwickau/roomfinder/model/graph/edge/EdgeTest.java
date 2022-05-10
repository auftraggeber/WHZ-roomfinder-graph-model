package de.fhzwickau.roomfinder.model.graph.edge;

import de.fhzwickau.roomfinder.model.graph.Graph;
import de.fhzwickau.roomfinder.model.graph.node.LazyNode;
import de.fhzwickau.roomfinder.model.graph.node.Node;
import de.fhzwickau.roomfinder.model.graph.node.NodeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    Node n1,n2;
    Edge edge;
    Graph graph;

    @BeforeEach
    void setUp() {
        graph = new Graph();
        n1 = new NodeFactory().createTestNode().obj();
        n2 = new LazyNode("node_2");

        graph.add(n1);
        graph.add(n2);

        edge = new Edge(n1, n2, 100);
        n1.addEdge(edge);
        n2.addEdge(edge);
    }

    @Test
    @DisplayName("Test if edge can be build and destroyed.")
    void testEdge() {
        assertTrue(n1.getEdges().contains(edge));
        assertTrue(n2.getEdges().contains(edge));

        assertEquals(n2, edge.getOther(n1));
        assertEquals(n1, edge.getOther(n2));

        edge.destroy();

        assertFalse(n1.getEdges().contains(edge));
        assertFalse(n2.getEdges().contains(edge));
    }

    @Test
    @DisplayName("Test if node will be replaced")
    void testLazyLoad() {
        Node newN2 = new NodeFactory().createTestNode().setValueOf(NodeFactory.MetadataParam.ID, "node_2").obj();

        assertTrue(n1.getEdges().contains(edge));
        assertTrue(n2.getEdges().contains(edge));
        assertFalse(newN2.getEdges().contains(edge));

        graph.add(newN2);

        assertTrue(n1.getEdges().contains(edge));
        assertFalse(n2.getEdges().contains(edge));
        assertTrue(newN2.getEdges().contains(edge));


        assertEquals(newN2, edge.getOther(n1));
        assertEquals(n1, edge.getOther(newN2));
    }
}
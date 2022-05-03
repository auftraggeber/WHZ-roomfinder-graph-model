package de.fhzwickau.roomfinder.model.graph;

import de.fhzwickau.roomfinder.model.graph.node.LazyNode;
import de.fhzwickau.roomfinder.model.graph.node.Node;
import de.fhzwickau.roomfinder.model.graph.node.NodeFactory;
import de.fhzwickau.roomfinder.model.graph.node.listener.LazyNodeListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GraphTest {


    Graph graph;
    Node n1, n2;
    LazyNode ln;

    @BeforeEach
    void setUp() {
        graph = new Graph();
        n1 = new NodeFactory().createTestNode().obj();
        n2 = new NodeFactory().createTestNode().obj();
        ln = new LazyNode("ln1");
    }

    @Test
    @DisplayName("Test if nodes can be added to graph")
    void testAdd(){
        graph.add(n1);

        assertTrue(graph.contains(n1));
        assertFalse(graph.contains(n2));

        graph.add(n2);

        assertTrue(graph.contains(n2));
    }

    @Test
    @DisplayName("Test if graph calls listener on node load.")
    void testNotifyListeners(){
        LazyNodeListener lazyNodeListener = mock(LazyNodeListener.class);
        graph.registerListener(lazyNodeListener);
        graph.add(ln);

        assertTrue(graph.contains(ln));

        Node node = new NodeFactory().createTestNode().setValueOf(NodeFactory.MetadataParam.ID, ln.getId()).obj();

        assertFalse(graph.contains(node));

        graph.add(node);
        verify(lazyNodeListener).onCompleteNodeLoaded(ln, node);

        assertTrue(graph.contains(node));
        assertFalse(graph.contains(ln));
    }



}
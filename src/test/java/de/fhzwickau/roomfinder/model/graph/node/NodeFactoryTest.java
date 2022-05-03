package de.fhzwickau.roomfinder.model.graph.node;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeFactoryTest {

    private static final String ID = "id", DISPLAYNAME = "dn", RESSOURCE = "r";
    private static final int POSX = 10, POSY = 6;
    private static final boolean TARGET = false;

    NodeFactory factory;

    @BeforeEach
    void setUp(){
        factory = new NodeFactory();
    }

    @Test
    @DisplayName("Test factory method.")
    void testCreateNode() {
        assertNotNull(factory.createSimpleNode().obj());
    }

    @Test
    @DisplayName("Test if all params can be set")
    void testSetParams() {
        Node node = factory.createSimpleNode()
                .setValueOf(NodeFactory.MetadataParam.ID, ID)
                .setValueOf(NodeFactory.MetadataParam.DISPLAYNAME, DISPLAYNAME)
                .setValueOf(NodeFactory.MetadataParam.RESSOURCE, RESSOURCE)
                .setValueOf(NodeFactory.MetadataParam.POSX, POSX)
                .setValueOf(NodeFactory.MetadataParam.POSY, POSY)
                .setValueOf(NodeFactory.MetadataParam.TARGET, TARGET)
                .obj();

        assertNotNull(node);

        assertEquals(ID, node.getId());
        assertEquals(DISPLAYNAME, node.getDisplayName());
        assertEquals(RESSOURCE, node.getRessource());
        assertEquals(POSX, node.getPositionX());
        assertEquals(POSY, node.getPositionY());
        assertEquals(TARGET, node.asTarget());
    }

}
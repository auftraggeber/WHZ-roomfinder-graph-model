package de.fhzwickau.roomfinder.model.graph.node;

import de.fhzwickau.roomfinder.model.metadata.Metadata;

import java.lang.reflect.Field;
import java.util.UUID;

/**
 * Diese Klasse dient zum Setzen der verschiedenen Parameter.
 * Das Setzen der Parameter wird später vom Controller des Erstellungsprogramms übernommen.
 * @version 0.1.0
 * @since 0.1.0
 * @author Jonas Langner
 */
public class NodeFactory {

    public static enum MetadataParam {

        ID("id"),
        DISPLAYNAME("displayName"),
        RESSOURCE("ressource"),
        POSX("positionX"),
        POSY("positionY"),
        TARGET("asTarget");

        private String exactParamName;

        MetadataParam(String exactParamName) {
            this.exactParamName = exactParamName;
        }

    }

    private Node node;

    public NodeFactory createSimpleNode() {
        node = new Node();

        return this;
    }

    public NodeFactory createTestNode() {
        createSimpleNode()
                .setValueOf(MetadataParam.ID,"TEST__" + UUID.randomUUID().toString());

        return this;
    }

    public NodeFactory setValueOf(MetadataParam param, Object val) {
        return setValueOf(param.exactParamName, val);
    }

    public NodeFactory setValueOf(String param, Object val) {
        try {
            Field field = node.getClass().getDeclaredField(param);
            field.setAccessible(true);
            field.set(node, val);
            field.setAccessible(false);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return this;
    }

    public Node obj() {
        return node;
    }




}

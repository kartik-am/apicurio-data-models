package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.Node;

import java.util.List;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiMessage extends AaiMessageBase {

    /**
     * The message may be "composite".
     * In this case, nested messages are stored here.
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#a-name-operationobject-a-operation-object">AsyncAPI 2.0.0 spec</a>
     */
    public List<AaiMessage> oneOf;
    public boolean _isOneOfMessage = false;

    /**
     * Objects of `any` type are deserialized as follows:
     * In TypeScript, this field contains the raw JS object, in case of Java
     * it is a {@link com.fasterxml.jackson.databind.node.ObjectNode}.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#messageObject">AsyncAPI 2.0.0 spec</a>
     */
    public Object payload;
    public AaiMessageTraitItems traits;


    public AaiMessage(Node parent) {
        super(parent);
    }

    public AaiMessage(Node parent, String _name) {
        super(parent, _name);
    }

    public abstract void addOneOfMessage(AaiMessage item);
}

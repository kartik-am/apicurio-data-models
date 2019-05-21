package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Node;

import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiComponents extends ExtensibleNode {

    /**
     * NOT Required.
     * <p>
     * Represents `Map[string, any]`.
     * Objects of `any` type are deserialized as follows:
     * In TypeScript, this field contains the raw JS object, in case of Java
     * it is a {@link com.fasterxml.jackson.databind.node.ObjectNode}.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#protocolInfoObject">AsyncAPI 2.0.0 spec</a>
     */
    public Map<String, Object> schemas;

    /**
     * NOT Required.
     */
    public Map<String, AaiMessage> messages;

    /**
     * NOT Required.
     */
    public Map<String, AaiSecurityScheme> securitySchemes;

    /**
     * NOT Required.
     */
    public Map<String, AaiParameter> parameters;

    /**
     * NOT Required.
     */
    public Map<String, AaiCorrelationId> correlationIds;

    /**
     * Represents `Map[string, Operation Trait Object | Message Trait Object]` type.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#componentsObject">AsyncAPI 2.0.0 spec</a>
     */
    public Map<String, AaiTraitItem> traits;

    public abstract List<AaiMessage> getMessagesList();

    public abstract List<AaiSecurityScheme> getSecuritySchemesList();

    public abstract List<AaiParameter> getParametersList();

    public abstract List<AaiCorrelationId> getCorrelationIdsList();

    public abstract List<AaiTraitItem> getTraitsList();

    public abstract void addSchema(String key, Object value);

    public abstract void addMessage(String key, AaiMessage value);

    public abstract void addSecurityScheme(String key, AaiSecurityScheme value);

    public abstract void addParameter(String key, AaiParameter value);

    public abstract void addCorrelationId(String key, AaiCorrelationId value);

    public abstract void addTraitItem(String key, AaiTraitItem value);

    public AaiComponents(Node parent) {
        requireNonNull(parent);
        this._parent = parent;
        this._ownerDocument = parent.ownerDocument();
    }
}

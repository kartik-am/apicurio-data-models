package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiHeaderItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiProtocolInfo;
import io.apicurio.datamodels.asyncapi.models.AaiTag;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import static io.apicurio.datamodels.asyncapi.v2.models.Aai20Document.mapToList;


public class Aai20MessageTrait extends AaiMessageTrait {


    public Aai20MessageTrait(Node parent) {
        super(parent);
    }

    public Aai20MessageTrait(Node parent, String _name) {
        super(parent, _name);
    }

    @Override
    public void accept(IVisitor visitor) {
        IAai20Visitor v = (IAai20Visitor) visitor;
        v.visitMessageTrait(this);
    }

    @Override
    public List<AaiHeaderItem> getHeadersList() {
        return mapToList(headers);
    }

    @Override
    public List<AaiProtocolInfo> getProtocolInfoList() {
        return mapToList(protocolInfo);
    }

    @Override
    public void addHeaderItem(AaiHeaderItem item) {
        if(headers == null)
            headers = new LinkedHashMap<>();
        headers.put(item.getName(), item);
    }

    @Override
    public void addTag(AaiTag tag) {
        if(tags == null)
            tags = new LinkedList<>();
        tags.add(tag);
    }

    @Override
    public void addProtocolInfo(AaiProtocolInfo item) {
        if(protocolInfo == null)
            protocolInfo = new LinkedHashMap<>();
        protocolInfo.put(item.getName(), item);
    }
}

/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.datamodels.openapi.v3.visitors;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30ParameterDefinition;
import io.apicurio.datamodels.openapi.visitors.OasTraverser;

/**
 * @author eric.wittmann@gmail.com
 */
public class Oas30Traverser extends OasTraverser implements IOas30Visitor {

    /**
     * Constructor.
     * @param visitor
     */
    public Oas30Traverser(IOas30Visitor visitor) {
        super(visitor);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.visitors.OasTraverser#doVisitDocument(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    protected void doVisitDocument(Document node) {
        Oas30Document doc = (Oas30Document) node;
        super.doVisitDocument(doc);
        this.traverseCollection(doc.servers);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitParameterDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30ParameterDefinition)
     */
    @Override
    public void visitParameterDefinition(Oas30ParameterDefinition node) {
        // TODO Auto-generated method stub
        
    }

}

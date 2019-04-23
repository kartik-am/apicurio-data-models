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

package io.apicurio.datamodels;

import java.util.List;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.io.DataModelReader;
import io.apicurio.datamodels.core.io.DataModelWriter;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.ValidationProblem;
import io.apicurio.datamodels.core.validation.DefaultSeverityRegistry;
import io.apicurio.datamodels.core.validation.IValidationSeverityRegistry;
import io.apicurio.datamodels.core.validation.ValidationProblemsResetVisitor;
import io.apicurio.datamodels.core.validation.ValidationVisitor;
import io.apicurio.datamodels.core.visitors.ITraverser;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * The most common entry points into using the data models library.  Provides convenience methods
 * for performing common actions such as i/o, visiting, and validation.
 * @author eric.wittmann@gmail.com
 */
public class Library {

    public static void visitNode(Node node, IVisitor visitor) {
        node.accept(visitor);
    }
    
    public static void visitTree(Node node, IVisitor visitor) {
        ITraverser traverser = TraverserFactory.create(node.ownerDocument(), visitor);
        traverser.traverse(node);
    }

    public static List<ValidationProblem> validate(Node node, IValidationSeverityRegistry severityRegistry) {
        if (severityRegistry == null) {
            severityRegistry = new DefaultSeverityRegistry();
        }
        
        // Clear/reset any problems that may have been found before.
        ValidationProblemsResetVisitor resetter = VisitorFactory.createValidationProblemsResetVisitor(node.ownerDocument());
        visitTree(node, resetter);
        
        // Validate the data model.
        ValidationVisitor validator = VisitorFactory.createValidationVisitor(node.ownerDocument());
        validator.setSeverityRegistry(severityRegistry);
        visitTree(node, validator);
        
        return validator.getValidationProblems();
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Document readDocument(Object json) {
        DocumentType type = discoverDocumentType(json);
        DataModelReader reader = VisitorFactory.createDataModelReader(type);
        Document document = DocumentFactory.create(type);
        reader.readDocument(json, document);
        return document;
    }
    
    public static Object writeNode(Node node) {
        DataModelWriter writer = VisitorFactory.createDataModelWriter(node.ownerDocument());
        visitTree(node, writer);
        return writer.getResult();
    }
    
    public static DocumentType discoverDocumentType(Object json) {
        String asyncapi = JsonCompat.propertyString(json, Constants.PROP_ASYNCAPI);
        String openapi = JsonCompat.propertyString(json, Constants.PROP_OPENAPI);
        if (asyncapi != null && asyncapi.startsWith("2.")) {
            return DocumentType.asyncapi2;
        }
        if (openapi != null) {
            if (openapi.startsWith("2.")) {
                return DocumentType.openapi2;
            }
            if (openapi.startsWith("3.0")) {
                return DocumentType.openapi3;
            }
        }
        
        throw new RuntimeException("Unknown data model type or version.");
    }

}

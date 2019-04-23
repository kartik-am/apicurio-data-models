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

package io.apicurio.datamodels.core.models;

import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * All visitable data model classes must implement this interface.
 * @author eric.wittmann@gmail.com
 */
public interface IVisitable {

    /**
     * Called to accept a visitor onto a visitable data model instance.
     * @param visitor
     */
    public void accept(IVisitor visitor);
    
}

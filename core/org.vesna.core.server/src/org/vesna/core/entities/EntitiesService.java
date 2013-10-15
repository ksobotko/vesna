/*
 * Copyright 2013 Krzysztof Marecki
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
package org.vesna.core.entities;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Krzysztof Marecki
 */
public class EntitiesService {
    
    Map<String, Repository> entities = new HashMap<>();
    
    public void addRepository(String name, Repository repository) {
        entities.put(name, repository);
    }
    
    public Repository  getRepository(String name) {
        Repository repository = entities.containsKey(name) ?
                                entities.get(name) :
                                null;
        return repository;
    }
    
}

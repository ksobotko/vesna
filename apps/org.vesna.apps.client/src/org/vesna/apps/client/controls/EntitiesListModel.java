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
package org.vesna.apps.client.controls;

import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.vesna.core.app.Core;
import org.vesna.core.entities.EntitiesService;
import org.vesna.core.entities.EntityException;
import org.vesna.core.entities.EntityHelper;
import org.vesna.core.entities.EntityType;
import org.vesna.core.entities.Repository;
import org.vesna.core.javafx.BaseModelImpl;
import org.vesna.core.lang.ReflectionHelper;
import org.vesna.core.logging.LoggerHelper;

/**
 *
 * @author Krzysztof Marecki
 */
public abstract class EntitiesListModel<TEntity> extends BaseModelImpl {
    protected static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EntitiesListModel.class);
    
    private final ListProperty<TEntity> entities = new SimpleListProperty<>(FXCollections.<TEntity>observableArrayList());

    public ObservableList<TEntity> getEntities() {
        return entities.get();
    }

    public void setEntities(ObservableList<TEntity> value) {
        entities.set(value);
    }

    public ListProperty entitiesProperty() {
        return entities;
    }
    
    private final ObjectProperty<TEntity> selectedEntity = new SimpleObjectProperty();

    public TEntity getSelectedEntity() {
        return selectedEntity.get();
    }

    public void setSelectedEntity(TEntity value) {
        selectedEntity.set(value);
    }

    public ObjectProperty selectedEntityProperty() {
        return selectedEntity;
    }
    
    protected Repository<TEntity> entitiesRepository;

    public Repository<TEntity> getEntitiesRepository() {
        return entitiesRepository;
    }
    
    protected EntityType entityType;

    @Override
    public void initialize() {
        loadEntityType();
    }

    @Override
    public void refresh() {
        try {
            loadEntities();
        } catch (EntityException ex) {
            LoggerHelper.logException(logger, ex);
        }
    }
    
    public EntitiesEditModel createNewEntityEditModel() {
        EntitiesEditModel model = createRowEditModel(EntitiesEditModel.Mode.Add);
        TEntity entity = createNewEntity();
        model.setEntity(entity);
        return model;
    }
     
    public EntitiesEditModel createSelectedEntityEditModel() 
            throws EntityException {
        EntitiesEditModel model = createRowEditModel(EntitiesEditModel.Mode.Edit);
        TEntity entity = getLoadedSelectedEntity();
        model.setEntity(entity);
        return model;
    }
    
    public void deleteSelectedEntity() 
            throws EntityException {
        int selectedIndex = getEntities().indexOf(getSelectedEntity());
        TEntity entity = getLoadedSelectedEntity();
        
        entitiesRepository.delete(entity);
        
        if(selectedIndex > 0) {
            TEntity selected = getEntities().get(selectedIndex - 1);
            setSelectedEntity(selected);
        }
    }
     
    
    protected abstract EntitiesEditModel createRowEditModel(EntitiesEditModel.Mode mode);
    
    protected abstract String getRepositoryName();
    
    protected List<TEntity> getAllEntities() {
         List<TEntity> dtos = entitiesRepository.getAll();
         return dtos;
    }
    
    protected TEntity getLoadedSelectedEntity() throws EntityException {
        Object id = EntityHelper.getId(entityType, getSelectedEntity());
        TEntity entity = entitiesRepository.getSingle(id);
        return entity;
    }
    
    protected TEntity createNewEntity() {
        TEntity entity = entitiesRepository.create();
        return entity;
    }
    
    private void loadEntities() throws EntityException {
        String repositoryName = getRepositoryName();
        EntitiesService entitiesService = Core.getService(EntitiesService.class);
        entitiesRepository = entitiesService.getRepository(repositoryName);
        List<TEntity> dtos = getAllEntities();
        
        Object oldSelectedId = EntityHelper.getId(entityType, getSelectedEntity());
        
        getEntities().clear();
        for(TEntity dto : dtos) {
            getEntities().add(dto);
        }
        
        TEntity selected = findEntity(oldSelectedId);
        if (selected == null && getEntities().size() > 0) {
            selected = getEntities().get(0);
        }
        setSelectedEntity(selected);
    }
    
    private void loadEntityType() {
        EntitiesService entitiesService = Core.getService(EntitiesService.class);
        String klassName = getTEntityClass().getName();
        entityType = entitiesService.getEntityType(klassName);
    }
    
    private TEntity findEntity(Object id) throws EntityException {
        TEntity ret = null;
        for (TEntity entity : getEntities()) {
            Object entityId = EntityHelper.getId(entityType, entity);
            if (entityId.equals(id)) {
                ret = entity;
                break;
            }
        }
        return ret;
    }
    
    protected Class getTEntityClass() {
        Class entityClass = ReflectionHelper.getTemplateTypeParameter(this.getClass());
        return entityClass;
    }
}

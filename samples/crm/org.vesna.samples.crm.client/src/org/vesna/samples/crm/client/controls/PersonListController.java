/*
 * Copyright 2013 Krzysztof Marecki
 *
 * Licensed under te Apache License, Version 2.0 (the "License");
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
package org.vesna.samples.crm.client.controls;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.vesna.apps.client.controls.EntitiesListController;
import org.vesna.core.javafx.controls.ControlEx;
import org.vesna.samples.crm.dto.Person;

/**
 *
 * @author Krzysztof Marecki
 */
public class PersonListController 
    extends EntitiesListController<PersonListModel> {
    
    @FXML
    TableColumn firstNameColumn;
    @FXML
    TableColumn lastNameColumn;
    @FXML
    TableColumn phoneColumn;
    @FXML
    TableColumn emailColumn;

    @Override
    protected void configureView(PersonListModel model) {
        super.configureView(model); 
        
        firstNameColumn.setCellValueFactory(
               new PropertyValueFactory<Person, String>("firstName"));
        lastNameColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("phone"));
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));
    }
    
    @Override
    protected ControlEx createRowEditControl() {
       PersonEdit control = new PersonEdit();
       return control;
    }
    
    
}

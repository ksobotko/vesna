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
package org.vesna.samples.crm.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.vesna.apps.client.MainFormController;
import org.vesna.samples.crm.client.controls.CompanyList;
import org.vesna.samples.crm.client.controls.CompanyListModel;
import org.vesna.samples.crm.client.controls.PersonList;
import org.vesna.samples.crm.client.controls.PersonListModel;

/**
 *
 * @author Krzysztof Marecki
 */
public class CrmMainFormController extends MainFormController {
        
    @FXML
    protected void handleMenuItemPersons(ActionEvent event) {
        PersonList list = new PersonList();
        PersonListModel model = new PersonListModel();
        showScreenInNewWindow(list, model, "Persons");
    }
    
    @FXML
    protected void handleMenuItemCompanies(ActionEvent event) {
        CompanyList list = new CompanyList();
        CompanyListModel model = new CompanyListModel();
        showScreenInNewWindow(list, model, "Companies");
    }
}

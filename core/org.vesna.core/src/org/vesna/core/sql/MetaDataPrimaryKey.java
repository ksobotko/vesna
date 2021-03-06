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
package org.vesna.core.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Krzysztof Marecki
 */
public class MetaDataPrimaryKey {
    
    private String columnName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public static MetaDataPrimaryKey fromResultSet(ResultSet resultSet) throws SQLException {
        MetaDataPrimaryKey pk = new MetaDataPrimaryKey();
        pk.setColumnName(resultSet.getString("COLUMN_NAME"));
        return pk;
    }
}

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
package org.vesna.core.data;

import java.util.HashMap;
import java.util.Map;
import org.vesna.core.lang.StringHelper;

/**
 *
 * @author Krzysztof Marecki
 */
public class HashDataRow implements DataRow {

    private static Map<String, String> values = new HashMap();
    
    @Override
    public String getString(int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String getString(String columnName) {
       return values.get(getColumnName(columnName));
    }

    @Override
    public void setString(int columnIndex, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setString(String columnName, String value) {
        values.put(getColumnName(columnName), value);
    }
    
    private String getColumnName(String columnName) {
        return StringHelper.toLowerCase(columnName);
    }
    
}

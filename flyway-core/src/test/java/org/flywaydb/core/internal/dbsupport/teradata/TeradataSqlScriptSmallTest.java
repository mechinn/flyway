/**
 * Copyright 2010-2016 Boxfuse GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flywaydb.core.internal.dbsupport.teradata;

import org.flywaydb.core.DbCategory;
import org.flywaydb.core.internal.dbsupport.SqlScript;
import org.flywaydb.core.internal.dbsupport.SqlStatement;
import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;
import org.flywaydb.core.internal.util.scanner.classpath.ClassPathResource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test for SqlScript.
 */
@Category(DbCategory.Teradata.class)
public class TeradataSqlScriptSmallTest {

    @Test
    public void parseSqlStatementsWithQuotes() throws Exception {
        String source = new ClassPathResource("migration/dbsupport/teradata/sql/doublequote/V1__DoubleQuote.sql",
                Thread.currentThread().getContextClassLoader()).loadAsString("UTF-8");

        SqlScript sqlScript = new SqlScript(source, new TeradataDbSupport(null));
        List<SqlStatement> sqlStatements = sqlScript.getSqlStatements();
        assertEquals(7, sqlStatements.size());
        assertEquals(17, sqlStatements.get(0).getLineNumber());
        assertEquals(18, sqlStatements.get(1).getLineNumber());
        assertEquals(19, sqlStatements.get(2).getLineNumber());
        assertEquals(21, sqlStatements.get(3).getLineNumber());
        assertEquals(26, sqlStatements.get(4).getLineNumber());
        assertEquals(31, sqlStatements.get(5).getLineNumber());
        assertEquals(36, sqlStatements.get(6).getLineNumber());
    }

    @Test
    public void parseProcedures() throws Exception {
        String source = new ClassPathResource("migration/dbsupport/teradata/sql/procedure/V1__Procedure.sql",
                Thread.currentThread().getContextClassLoader()).loadAsString("UTF-8");

        SqlScript sqlScript = new SqlScript(source, new TeradataDbSupport(null));
        List<SqlStatement> sqlStatements = sqlScript.getSqlStatements();
        assertEquals(8, sqlStatements.size());
        assertEquals(17, sqlStatements.get(0).getLineNumber());
        assertEquals(22, sqlStatements.get(1).getLineNumber());
        assertEquals(27, sqlStatements.get(2).getLineNumber());
        assertEquals(29, sqlStatements.get(3).getLineNumber());
        assertEquals(35, sqlStatements.get(4).getLineNumber());
        assertEquals(42, sqlStatements.get(5).getLineNumber());
        assertEquals(50, sqlStatements.get(6).getLineNumber());
        assertEquals(52, sqlStatements.get(7).getLineNumber());
    }

    @Test
    public void parseTriggers() throws Exception {
        String source = new ClassPathResource("migration/dbsupport/teradata/sql/trigger/V1__Trigger.sql",
                Thread.currentThread().getContextClassLoader()).loadAsString("UTF-8");

        SqlScript sqlScript = new SqlScript(source, new TeradataDbSupport(null));
        List<SqlStatement> sqlStatements = sqlScript.getSqlStatements();
        assertEquals(7, sqlStatements.size());
        assertEquals(17, sqlStatements.get(0).getLineNumber());
        assertEquals(18, sqlStatements.get(1).getLineNumber());
        assertEquals(19, sqlStatements.get(2).getLineNumber());
        assertEquals(20, sqlStatements.get(3).getLineNumber());
        assertEquals(25, sqlStatements.get(4).getLineNumber());
        assertEquals(32, sqlStatements.get(5).getLineNumber());
        assertEquals(36, sqlStatements.get(6).getLineNumber());
    }

    @Test
    public void parseUppercase() throws Exception {
        String source = new ClassPathResource("migration/dbsupport/teradata/sql/uppercase/V1__Uppercase.sql",
                Thread.currentThread().getContextClassLoader()).loadAsString("UTF-8");

        SqlScript sqlScript = new SqlScript(source, new TeradataDbSupport(null));
        List<SqlStatement> sqlStatements = sqlScript.getSqlStatements();
        assertEquals(1, sqlStatements.size());
        assertEquals(17, sqlStatements.get(0).getLineNumber());
    }

    @Test
    public void parseView() throws Exception {
        String source = new ClassPathResource("migration/dbsupport/teradata/sql/view/V1__View.sql",
                Thread.currentThread().getContextClassLoader()).loadAsString("UTF-8");

        SqlScript sqlScript = new SqlScript(source, new TeradataDbSupport(null));
        List<SqlStatement> sqlStatements = sqlScript.getSqlStatements();
        assertEquals(3, sqlStatements.size());
        assertEquals(17, sqlStatements.get(0).getLineNumber());
        assertEquals(18, sqlStatements.get(1).getLineNumber());
        assertEquals(19, sqlStatements.get(2).getLineNumber());
    }
}

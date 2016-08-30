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

import org.flywaydb.core.internal.dbsupport.Delimiter;
import org.flywaydb.core.internal.dbsupport.SqlStatementBuilder;
import org.flywaydb.core.internal.util.StringUtils;

import java.util.regex.Pattern;

/**
 * SqlStatementBuilder supporting Teradata specific delimiter changes.
 */
public class TeradataSqlStatementBuilder extends SqlStatementBuilder {
    private final String[] charSets = {
            "LATIN", "UNICODE", "KANJISJIS", "GRAPHIC"
    };

    @Override
    protected String cleanToken(String token) {
        if (token.endsWith("'XC") || token.endsWith("'XCV") || token.endsWith("'XCF")) {
            return token.substring(token.indexOf("'")+1);
        }

        if (token.startsWith("_")) {
            for (String charSet : charSets) {
                String cast = "_" + charSet;
                if (token.startsWith(cast)) {
                    return token.substring(cast.length()+1);
                }
            }
        }

        // If no matches are found for charset casting then return token
        return token;
    }

    @Override
    protected String extractAlternateOpenQuote(String token) {
//        if (token.startsWith("\"")) {
//            return "\"";
//        }
        return null;
    }

    /**
     * Are we currently inside a BEGIN END; block?
     */
    private int beginCount;

    /**
     * Holds the beginning of the statement.
     */
    private String statementStart = "";

    private boolean statementStarted() {
        return statementStart.contains("CREATE FUNCTION")
                || statementStart.contains("CREATE PROCEDURE")
                || statementStart.contains("CREATE TRIGGER")
                || statementStart.contains("REPLACE FUNCTION")
                || statementStart.contains("REPLACE PROCEDURE")
                || statementStart.contains("REPLACE TRIGGER");
    }

    /**
     * nasty workaround for stored procedures
     * @param line      The simplified line to analyse.
     * @param delimiter The current delimiter.
     * @return
     */
    @Override
    protected Delimiter changeDelimiterIfNecessary(String line, Delimiter delimiter) {
        if (StringUtils.countOccurrencesOf(statementStart, " ") < 4 || statementStarted()) {
            statementStart += line;
            statementStart += " ";
        }

        if (statementStarted()) {
            if (line.contains("BEGIN") || line.contains("CASE")) {
                beginCount++;
            }

            if (line.contains("END")) {
                beginCount--;
            }
        }

        if (beginCount > 0) {
            return null;
        }
        return getDefaultDelimiter();
    }
}

--
-- Copyright 2010-2016 Boxfuse GmbH
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--         http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

CREATE USER "FLYWAY" FROM "DBC"
AS PERM = 102400000
PASSWORD = "flyway"
DEFAULT DATABASE = "FLYWAY"
NO FALLBACK
NO BEFORE JOURNAL
NO AFTER JOURNAL;

GRANT ALL ON "FLYWAY" TO "FLYWAY" WITH GRANT OPTION;

GRANT
	MONRESOURCE
	,MONSESSION
	,ABORTSESSION
	,SETSESSRATE
	,SETRESRATE
	,REPLCONTROL
	,CREATE PROFILE
	,CREATE ROLE
	,DROP PROFILE
	,DROP ROLE
TO "FLYWAY" WITH GRANT OPTION;

GRANT ALL ON "DBC" TO "FLYWAY" WITH GRANT OPTION;

GRANT ALL ON SYSUDTLIB TO "FLYWAY" WITH GRANT OPTION;

GRANT ALL ON "Sys_Calendar" TO "FLYWAY" WITH GRANT OPTION;
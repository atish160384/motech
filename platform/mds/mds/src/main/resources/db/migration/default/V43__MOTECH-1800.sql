CREATE TABLE IF NOT EXISTS "UserPreferences" (
  "username" varchar(255) NOT NULL,
  "className" varchar(255) NOT NULL,
  "gridRowsNumber" int,
  PRIMARY KEY ("username", "className")
);

CREATE TABLE IF NOT EXISTS "UserPreferences_selectedFields" (
  "className_OID" varchar(255) NOT NULL,
  "username_OID" varchar(255) NOT NULL,
  "selectedField" bigint,
  "IDX" int,
  PRIMARY KEY ("className_OID", "username_OID", "IDX"),
  CONSTRAINT "UserPreferences_selectedFields_FK1" FOREIGN KEY ("username_OID", "className_OID") REFERENCES "UserPreferences" ("username", "className"),
  CONSTRAINT "UserPreferences_selectedFields_FK2" FOREIGN KEY ("selectedField") REFERENCES "Field" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "UserPreferences_unselectedFields" (
  "className_OID" varchar(255) NOT NULL,
  "username_OID" varchar(255) NOT NULL,
  "unselectedField" bigint,
  "IDX" int,
  PRIMARY KEY ("className_OID", "username_OID", "IDX"),
  CONSTRAINT "UserPreferences_unselectedFields_FK1" FOREIGN KEY ("username_OID", "className_OID") REFERENCES "UserPreferences" ("username", "className"),
  CONSTRAINT "UserPreferences_unselectedFields_FK2" FOREIGN KEY ("unselectedField") REFERENCES "Field" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "ConfigSettings" (
  "id" bigint,
  "afterTimeUnit" varchar(255) NOT NULL,
  "afterTimeValue" int,
  "deleteMode" varchar(255) NOT NULL,
  "emptyTrash" bit(1),
  PRIMARY KEY ("id")
);

ALTER TABLE "ConfigSettings" ADD "defaultGridSize" int DEFAULT 50;

CREATE TABLE brick (
  id       INTEGER,
  material VARCHAR(10)
);

CREATE TABLE house (
  uuid  CHAR(36) PRIMARY KEY NOT NULL,
  style VARCHAR(20) NOT NULL
);

CREATE TABLE room (
  uuid           CHAR(36) PRIMARY KEY NOT NULL,
  house_uuid     CHAR(36) NOT NULL,
  square_footage LONG NOT NULL,
  FOREIGN KEY (house_uuid) REFERENCES house (uuid)
    ON DELETE CASCADE
);

CREATE TABLE chair (
  uuid      CHAR(36) PRIMARY KEY NOT NULL,
  room_uuid CHAR(36) NOT NULL,
  reclines  BOOLEAN NOT NULL,
  FOREIGN KEY (room_uuid) REFERENCES room (uuid)
    ON DELETE CASCADE
);

CREATE TABLE leg (
  uuid       CHAR(36) PRIMARY KEY NOT NULL,
  chair_uuid CHAR(36) NOT NULL,
  material   VARCHAR(10) NOT NULL,
  FOREIGN KEY (chair_uuid) REFERENCES chair (uuid)
    ON DELETE CASCADE
);
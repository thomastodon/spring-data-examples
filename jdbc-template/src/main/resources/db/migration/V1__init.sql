CREATE TABLE brick (
    id INTEGER,
    material VARCHAR(10)
);

CREATE TABLE house (
  uuid CHAR(36) PRIMARY KEY NOT NULL
);

CREATE TABLE room (
  uuid       CHAR(36) PRIMARY KEY NOT NULL,
  house_uuid CHAR(36),
  FOREIGN KEY (house_uuid) REFERENCES house (uuid)
    ON DELETE CASCADE
);

CREATE TABLE chair (
  uuid      CHAR(36) PRIMARY KEY NOT NULL,
  room_uuid CHAR(36),
  FOREIGN KEY (room_uuid) REFERENCES room (uuid)
    ON DELETE CASCADE
);

CREATE TABLE leg (
  uuid      CHAR(36) PRIMARY KEY NOT NULL,
  chair_uuid CHAR(36),
  FOREIGN KEY (chair_uuid) REFERENCES chair (uuid)
    ON DELETE CASCADE
);
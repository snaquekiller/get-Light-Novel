CREATE TABLE manga
(
  id            BIGINT(20) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  name          VARCHAR(200),
  author        VARCHAR(100),
  notes         VARCHAR(100),
  url           VARCHAR(1000),
  type          ENUM ('LIGHT_NOVEL', 'MANGA'),
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted       TINYINT(1)                      DEFAULT 0
);

CREATE TABLE manga_out
(
  id            BIGINT(20) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  manga_id      BIGINT(20) UNSIGNED                 NOT NULL,
  hours         INT UNSIGNED                        NOT NULL,
  minutes       INT UNSIGNED                        NOT NULL,
  secondes      INT UNSIGNED                        NOT NULL,
  days          INT,
  nb_try        INT UNSIGNED,
  status        ENUM ('IN_PROGRESS', 'FAILED', 'RE_TRY', 'AVAILABLE'),
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted       TINYINT(1)                      DEFAULT 0
);

CREATE TABLE chapter
(
  id            BIGINT(20) UNSIGNED PRIMARY KEY                 NOT NULL AUTO_INCREMENT,
  text          VARCHAR(10000),
  num           INT(11),
  title         VARCHAR(1000),
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP             NOT NULL,
  update_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP             NOT NULL,
  tome          INT(11),
  file_id       INT(11),
  deleted       TINYINT(1)                                               DEFAULT 0
);


CREATE TABLE file
(
  id            BIGINT(20) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  name          VARCHAR(1000),
  type          VARCHAR(100),
  url           VARCHAR(1000),
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted       TINYINT(1)                      DEFAULT 0
);

CREATE TABLE user
(
  id            BIGINT(20) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  email         VARCHAR(1000),
  pseudo        VARCHAR(100),
  nom           VARCHAR(1000),
  prenom        VARCHAR(1000),
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted       TINYINT(1)                      DEFAULT 0
);

CREATE TABLE manga_subscription
(
  id            BIGINT(20) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  user_id       VARCHAR(1000),
  manga_id      VARCHAR(100),
  num_chapter   INT UNSIGNED,
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted       TINYINT(1)                      DEFAULT 0
);

INSERT INTO LN.user (email, pseudo, nom, prenom) VALUES ('nic.guitton@gmail.com', 'snaquekiller', 'guitton', 'nicolas
');
INSERT INTO LN.manga (name, author, notes, url, type, creation_date, update_date, deleted) VALUES ('
Douluo Dalu 3 - Dragon King''s Legend', 'Tang Jia San Shao', NULL, NULL, 'LIGHT_NOVEL', '2018-02-06 17:56:35',
                                                                                                   '2018-02-06 17:56:35', 0);


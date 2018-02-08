CREATE TABLE manga
(
  id            BIGINT(20) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  name          VARCHAR(200),
  author        VARCHAR(100),
  comment       VARCHAR(100),
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
  file_id       BIGINT(20) UNSIGNED,
  manga_id      BIGINT(20) UNSIGNED,
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

CREATE TABLE LN.user
(
  id            BIGINT(20) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  email         VARCHAR(200)                        NOT NULL UNIQUE,
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
  user_id       BIGINT(20) UNSIGNED,
  manga_id      BIGINT(20) UNSIGNED,
  num_chapter   INT UNSIGNED,
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted       TINYINT(1)                      DEFAULT 0
);

INSERT INTO LN.chapter (text, num, title, creation_date, update_date, tome, file_id, deleted)
VALUES ('""', 1583, 'test', '2018-02-08 11:27:29', '2018-02-08 11:27:29', 1, NULL, 0);

INSERT INTO LN.user (email, pseudo, nom, prenom) VALUES ('nic.guitton@gmail.com', 'snaquekiller', 'guitton', 'nicolas
');

INSERT INTO LN.manga_out (manga_id, hours, minutes, secondes, days, nb_try, status, creation_date, update_date, deleted)
VALUES (2, 12, 15, 0, NULL, 0, 'AVAILABLE', '2018-02-08 11:20:13', '2018-02-08 11:20:13', 0);
INSERT INTO LN.manga_out (manga_id, hours, minutes, secondes, days, nb_try, status, creation_date, update_date, deleted)
VALUES (2, 15, 35, 0, NULL, 0, 'AVAILABLE', '2018-02-08 11:20:13', '2018-02-08 11:20:13', 0);
INSERT INTO LN.manga_subscription (user_id, manga_id, num_chapter, creation_date, update_date, deleted)
VALUES (1, 2, 1583, '2018-02-07 17:39:22', '2018-02-07 17:39:22', 0);

INSERT INTO LN.manga (name, author, comment, url, type, creation_date, update_date, deleted) VALUES ('
Douluo Dalu 3 - Dragon King''s Legend', 'Tang Jia San Shao', NULL,
                                                                                                     'https://lnmtl.com/chapter/douluo-dalu-3-dragon-king-s-legend-chapter-',
                                                                                                     'LIGHT_NOVEL',
                                                                                                     '2018-02-06 17:56:35',
                                                                                                     '2018-02-06 17:56:35', 0);

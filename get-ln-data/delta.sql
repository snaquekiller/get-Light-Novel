CREATE TABLE IF NOT EXISTS LN.manga
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

CREATE TABLE IF NOT EXISTS LN.manga_out
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

CREATE TABLE IF NOT EXISTS LN.chapter
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


CREATE TABLE IF NOT EXISTS LN.file
(
  id            BIGINT(20) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  name          VARCHAR(1000),
  type          VARCHAR(100),
  url           VARCHAR(1000),
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted       TINYINT(1)                      DEFAULT 0
);

CREATE TABLE IF NOT EXISTS LN.user
(
  id            BIGINT(20) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  email         VARCHAR(200)                        NOT NULL UNIQUE,
  pseudo        VARCHAR(100),
  nom           VARCHAR(1000),
  prenom        VARCHAR(1000),
  password      VARCHAR(1000),
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted       TINYINT(1)                      DEFAULT 0
);

CREATE TABLE IF NOT EXISTS LN.chapter_files
(
  chapter_id BIGINT(20) UNSIGNED,
  #   type       ENUM ('EPUB', 'MOBI')           NOT NULL,
  file_id    BIGINT(20) UNSIGNED PRIMARY KEY NOT NULL
);
CREATE UNIQUE INDEX chapter_files_file_id_uindex
  ON LN.chapter_files (file_id);

CREATE TABLE IF NOT EXISTS LN.manga_subscription
(
  id            BIGINT(20) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  user_id       BIGINT(20) UNSIGNED,
  manga_id      BIGINT(20) UNSIGNED,
  num_chapter   INT UNSIGNED,
  format        ENUM ('EPUB', 'MOBI'),
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted       TINYINT(1)                      DEFAULT 0
);

INSERT INTO LN.chapter (text, num, title, creation_date, update_date, tome, file_id, deleted)
VALUES ('""', 1583, 'test', '2018-02-08 11:27:29', '2018-02-08 11:27:29', 1, NULL, 0);

INSERT INTO LN.user (email, pseudo, nom, prenom, creation_date, update_date, deleted)
VALUES ('nic.guitton@gmail.com', 'snaquekiller', 'guitton', 'nicolas
', '2018-02-07 17:14:44', '2018-02-07 17:14:44', 0);
INSERT INTO LN.user (email, pseudo, nom, prenom, creation_date, update_date, deleted)
VALUES ('nicolas.guitton@kindle.com', 'snaquekiller', 'guitton', 'nicolas
', '2018-02-07 17:14:44', '2018-02-07 17:14:44', 0);
make
INSERT INTO LN.manga_out (manga_id, hours, minutes, secondes, days, nb_try, status, creation_date, update_date, deleted)
VALUES (2, 12, 15, 0, NULL, 0, 'AVAILABLE', '2018-02-08 11:20:13', '2018-02-08 11:20:13', 0);
INSERT INTO LN.manga_out (manga_id, hours, minutes, secondes, days, nb_try, status, creation_date, update_date, deleted)
VALUES (2, 15, 35, 0, NULL, 0, 'AVAILABLE', '2018-02-08 11:20:13', '2018-02-08 11:20:13', 0);

INSERT INTO LN.manga_subscription (user_id, manga_id, num_chapter, creation_date, update_date, deleted, format)
VALUES (1, 2, 1583, '2018-02-07 17:39:22', '2018-02-07 17:39:22', 0, 'MOBI');
INSERT INTO LN.manga_subscription (user_id, manga_id, num_chapter, creation_date, update_date, deleted, format)
VALUES (2, 2, 1583, '2018-02-07 17:39:22', '2018-02-07 17:39:22', 0, 'MOBI');

INSERT INTO LN.chapter (text, num, title, creation_date, update_date, tome, file_id, manga_id, deleted)
VALUES ('""', 1589, 'test', '2018-02-08 11:27:29', '2018-02-08 11:27:29', 1, NULL, 2, 0);
INSERT INTO LN.chapter (text, num, title, creation_date, update_date, tome, file_id, manga_id, deleted)
VALUES (NULL, 1584, '#1584: golden-colored dragon pattern(s)', '2018-02-08 16:31:01', '2018-02-08 16:31:01', NULL, 1, NULL, 0);
INSERT INTO LN.chapter (text, num, title, creation_date, update_date, tome, file_id, manga_id, deleted)
VALUES (NULL, 1590, '#1590: The concern finished', '2018-02-10 14:25:00', '2018-02-10 14:25:00', NULL, 19, 2, 0);

INSERT INTO LN.manga (name, author, comment, url, type, creation_date, update_date, deleted) VALUES ('
Douluo Dalu 3 - Dragon King''s Legend', 'Tang Jia San Shao', NULL,
                                                                                                     'https://lnmtl.com/chapter/douluo-dalu-3-dragon-king-s-legend-chapter-',
                                                                                                     'LIGHT_NOVEL',
                                                                                                     '2018-02-06 17:56:35',
                                                                                                     '2018-02-06 17:56:35', 0);

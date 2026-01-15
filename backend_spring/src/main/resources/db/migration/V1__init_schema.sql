CREATE TABLE users (
  id SERIAL,
  name VARCHAR(255),
  username VARCHAR(255) UNIQUE NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  role VARCHAR(50) NOT NULL,
  is_account_non_locked BOOLEAN DEFAULT TRUE,
  is_enabled BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP ,
  deleted_at TIMESTAMP,
  CONSTRAINT users_primary_key PRIMARY KEY(id)
);

CREATE TABLE groups(
  id SERIAL,
  name VARCHAR(255),
  description VARCHAR(255),
  creator BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP ,
  deleted_at TIMESTAMP,
  CONSTRAINT groups_primary_key PRIMARY KEY(id),
  CONSTRAINT fk_user_creator FOREIGN KEY(creator) REFERENCES users(id)
);

CREATE TABLE normal_chat(
  id SERIAL,
  message VARCHAR(255) ,
  sent_by BIGINT,
  sent_to BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP ,
  deleted_at TIMESTAMP,
  CONSTRAINT normal_chat_primary_key PRIMARY KEY(id),
  CONSTRAINT fk_user_sent_by FOREIGN KEY(sent_by) REFERENCES users(id),
  CONSTRAINT fk_user_sent_to FOREIGN KEY(sent_to) REFERENCES users(id)
);

CREATE TABLE group_chat(
  id SERIAL,
  message VARCHAR(255) ,
  sent_by BIGINT,
  sent_to BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP ,
  deleted_at TIMESTAMP,
  CONSTRAINT group_chat_primary_key PRIMARY KEY(id),
  CONSTRAINT fk_user_sent_by FOREIGN KEY(sent_by) REFERENCES users(id),
  CONSTRAINT fk_user_sent_to FOREIGN KEY(sent_to) REFERENCES groups(id)
);

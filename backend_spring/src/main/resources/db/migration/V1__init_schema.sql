CREATE TABLE users (
  id SERIAL,
  name VARCHAR(255),
  username VARCHAR(255) UNIQUE NOT NULL,
  role VARCHAR(50) NOT NULL,
  is_account_non_locked BOOLEAN DEFAULT TRUE,
  is_enabled BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  created_by VARCHAR(255),
  updated_at TIMESTAMP ,
  updated_by VARCHAR(255),
  deleted_at TIMESTAMP,
  deleted_by VARCHAR(255),
  CONSTRAINT users_primary_key PRIMARY KEY(id)
);

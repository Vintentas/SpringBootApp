-- file: 10-create-user-and-db.sql
CREATE DATABASE spring_demo;
CREATE ROLE program WITH PASSWORD 'test';
GRANT ALL PRIVILEGES ON DATABASE spring_demo TO program;
ALTER ROLE program WITH LOGIN;
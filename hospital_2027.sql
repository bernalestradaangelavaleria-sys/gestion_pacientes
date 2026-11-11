CREATE DATABASE IF NOT EXISTS hospital_2027
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE hospital_2027;

CREATE TABLE IF NOT EXISTS pacientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni CHAR(8) NOT NULL UNIQUE,
    fecha_nacimiento DATE NOT NULL,
    telefono VARCHAR(15),
    correo VARCHAR(120),
    direccion VARCHAR(180),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO pacientes (nombres, apellidos, dni, fecha_nacimiento, telefono, correo, direccion)
VALUES
('María', 'López Torres', '74125896', '1998-04-12', '987654321', 'maria.lopez@email.com', 'Lima'),
('Carlos', 'Ramírez Soto', '70859631', '1987-09-23', '912345678', 'carlos.ramirez@email.com', 'Callao');

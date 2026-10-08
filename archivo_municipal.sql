CREATE DATABASE archivo_municipal;
USE archivo_municipal;

-- 1. Tabla de Áreas
CREATE TABLE areas (
    id_area INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion VARCHAR(255),
    estado BOOLEAN DEFAULT TRUE
);

INSERT INTO areas (nombre, descripcion) VALUES
('Presidencia', 'Presidencia Municipal'),
('Tesorería', 'Tesorería Municipal'),
('Obras Públicas', 'Dirección de Obras Públicas'),
('Desarrollo Social', 'Dirección de Desarrollo Social'),
('Contraloría', 'Órgano de Control Interno');

-- 2. Tabla de Usuarios
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(150) NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    estado BOOLEAN DEFAULT TRUE
);

INSERT INTO usuarios (nombre_completo, usuario, password, rol)
VALUES ('Administrador del Sistema', 'admin', 'admin123', 'Administrador');

-- 3. Tabla de Expedientes
CREATE TABLE expedientes (
    id_expediente INT AUTO_INCREMENT PRIMARY KEY,
    numero_expediente VARCHAR(50) NOT NULL UNIQUE,
    id_area INT NOT NULL,
    asunto VARCHAR(255) NOT NULL,
    responsable VARCHAR(150) NOT NULL,
    fecha_apertura DATE NOT NULL,
    estado VARCHAR(30) NOT NULL,
    ubicacion VARCHAR(150) NOT NULL,
    CONSTRAINT fk_expediente_area FOREIGN KEY (id_area) REFERENCES areas (id_area)
);

INSERT INTO expedientes (numero_expediente, id_area, asunto, responsable, fecha_apertura, estado, ubicacion)
VALUES 
('EXP-2026-001', 1, 'Solicitud de información', 'Administrador del Sistema', '2026-10-01', 'Abierto', 'Archivo de trámite'),
('EXP-2026-002', 2, 'Presupuesto municipal', 'Administrador del Sistema', '2026-10-02', 'Abierto', 'Archivo de trámite'),
('EXP-2026-003', 3, 'Proyecto de obra', 'Administrador del Sistema', '2026-10-03', 'En proceso', 'Archivo de trámite');
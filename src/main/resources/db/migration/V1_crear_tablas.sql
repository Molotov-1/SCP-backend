CREATE TABLE scps (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    precio INT NOT NULL DEFAULT 0
);

INSERT INTO scps (nombre, descripcion, categoria, stock, precio)
VALUES ('SCP-262', 'Un abrigo de muchos usos.', 'Clase Euclid', 12, 45000);
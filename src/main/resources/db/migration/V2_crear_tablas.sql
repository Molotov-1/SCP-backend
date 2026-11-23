-- 1. SITIOS DE CONTENCIÓN
CREATE TABLE sitios_contencion (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    ubicacion VARCHAR(150) NOT NULL,
    capacidad INT NOT NULL DEFAULT 0
);

-- 2. SCPS
CREATE TABLE scps (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    precio INT NOT NULL DEFAULT 0,
    sitio_id INT REFERENCES sitios_contencion(id)
);

-- 3. USUARIOS
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL DEFAULT 'usuario'
);

-- 4. DIRECCIONES
CREATE TABLE direcciones (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    calle VARCHAR(150) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    region VARCHAR(100) NOT NULL
);

-- 5. PAGOS
CREATE TABLE pagos (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL REFERENCES usuarios(id),
    total INT NOT NULL DEFAULT 0,
    estado VARCHAR(30) NOT NULL DEFAULT 'PAGADO'
);

-- 6. DETALLE DE PAGOS
CREATE TABLE pago_items (
    id SERIAL PRIMARY KEY,
    pago_id INT NOT NULL REFERENCES pagos(id) ON DELETE CASCADE,
    scp_id INT NOT NULL REFERENCES scps(id),
    cantidad INT NOT NULL DEFAULT 1,
    precio_unitario INT NOT NULL DEFAULT 0
);

-- 7. ENTREGAS
CREATE TABLE entregas (
    id SERIAL PRIMARY KEY,
    pago_id INT NOT NULL UNIQUE REFERENCES pagos(id) ON DELETE CASCADE,
    destino VARCHAR(150) NOT NULL,
    estado VARCHAR(30) NOT NULL DEFAULT 'PREPARANDO'
);

-- 8. CARRITOS
CREATE TABLE carritos (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL UNIQUE REFERENCES usuarios(id) ON DELETE CASCADE
);

-- 9. ITEMS DEL CARRITO
CREATE TABLE carrito_items (
    id SERIAL PRIMARY KEY,
    carrito_id INT NOT NULL REFERENCES carritos(id) ON DELETE CASCADE,
    scp_id INT NOT NULL REFERENCES scps(id) ON DELETE CASCADE,
    cantidad INT NOT NULL DEFAULT 1,
    UNIQUE (carrito_id, scp_id)
);
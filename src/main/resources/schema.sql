CREATE TABLE cliente(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(100) NOT NULL
);

CREATE TABLE email(
      id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      email VARCHAR(100) NOT NULL UNIQUE,
      id_cliente INTEGER NOT NULL,

      CONSTRAINT fk_id_cliente_email FOREIGN KEY (id_cliente) REFERENCES cliente(id)
          ON DELETE CASCADE
);

CREATE TABLE pedido(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_cliente INTEGER NOT NULL,
    fecha TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_id_cliente_pedido FOREIGN KEY (id_cliente) REFERENCES cliente(id)
    ON DELETE RESTRICT
);

CREATE TABLE producto(
     id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     nombre VARCHAR(50) NOT NULL,
     descripcion VARCHAR(150) NOT NULL,
     precio NUMERIC(10, 2) NOT NULL,
     stock INTEGER NOT NULL
);

CREATE TABLE detalle_pedido(
   id_pedido INT NOT NULL,
   id_producto INT NOT NULL,
   cantidad INTEGER NOT NULL,
   precio_unitario NUMERIC(10, 2) NOT NULL,

   CONSTRAINT pk_detalle_pedido PRIMARY KEY (id_pedido, id_producto),

   CONSTRAINT fk_id_pedido FOREIGN KEY (id_pedido) REFERENCES pedido(id)
       ON DELETE CASCADE,

   CONSTRAINT fk_id_producto FOREIGN KEY (id_producto) REFERENCES producto(id)
       ON DELETE RESTRICT
);
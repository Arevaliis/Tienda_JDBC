INSERT INTO cliente (nombre, apellido) VALUES
   ('Juan', 'Pérez'),
   ('Ana', 'García'),
   ('Luis', 'Martínez');

INSERT INTO email (email, id_cliente) VALUES
    ('juan@gmail.com', 1),
    ('ana@gmail.com', 2),
    ('luis@gmail.com', 3);

INSERT INTO telefono (telefono, id_cliente) VALUES
    ('600111222', 1),
    ('611222333', 2),
    ('622333444', 3);

INSERT INTO direccion (ciudad, provincia, calle, numero, cod_postal, piso, id_cliente) VALUES
    ('Madrid', 'Madrid', 'Gran Vía', '10', '28013', 3, 1),
    ('Barcelona', 'Barcelona', 'Diagonal', '200', '08018', NULL, 2),
    ('Valencia', 'Valencia', 'Colón', '15', '46004', 2, 3);

INSERT INTO producto (nombre, descripcion, precio, stock) VALUES
  ('Teclado', 'Teclado mecánico RGB', 50.00, 100),
  ('Ratón', 'Ratón gaming', 25.00, 200),
  ('Monitor', 'Monitor 24 pulgadas', 150.00, 50),
  ('Auriculares', 'Auriculares inalámbricos', 80.00, 75);

INSERT INTO pedido (id_cliente, fecha) VALUES
   (1, '2025-01-10 10:00:00'),
   (1, '2025-01-15 12:30:00'),
   (2, '2025-01-20 09:15:00'),
   (3, '2025-01-22 18:45:00');

INSERT INTO detalle_pedido VALUES
    (1, 1, 2, 50.00),
    (1, 2, 1, 25.00),
    (2, 3, 1, 150.00),
    (3, 4, 2, 80.00),
    (4, 1, 1, 50.00),
    (4, 2, 2, 25.00);
# 📦💻 Sistema de Gestión de Pedidos

## ⚙️ Descripción del proyecto

Este proyecto consiste en una aplicación de escritorio desarrollada en Java para la gestión de pedidos de una tienda. Permite administrar clientes, productos y pedidos, así como generar informes estadísticos para el análisis de ventas.

El sistema sigue una arquitectura en capas basada en el patrón DAO (Data Access Object), separando claramente la lógica de acceso a datos, la lógica de negocio y la interfaz de usuario.


---

## 🏗️ Arquitectura del sistema

### Capa DAO
- Acceso directo a la base de datos mediante SQL
- Implementa operaciones CRUD (Create, Read, Update, Delete)
- Gestiona la conexión con la base de datos
- Cada entidad tiene su propio DAO:
    - ClienteDAO
    - ProductoDAO
    - PedidoDAO
    - DetallePedidoDAO
- Manejo de errores con DAOException

---

### Capa Service
- Lógica de negocio del sistema
  - Validación de datos y reglas de negocio
  - Coordinación entre DAO
- Generación de informes
- Manejo de errores con ServiceException

---

### Capa de interfaz (Gestores)
- Gestión de interacción con el usuario
- Menús del sistema
- Entrada y validación de datos
- Visualización de información

Herramientas:
- JTable / TableViewer
- JOptionPane

---

## 🚨 Gestión de excepciones

- DAOException → errores en base de datos
- ServiceException → errores de lógica de negocio
- ValidationException → errores de validación de datos

---

## 📊 Informes

- Producto más vendido
- Cliente con más pedidos
- Total facturado
- Top 5 productos más vendidos

---

## 💻 Tecnologías utilizadas

- Java
- JDBC
- PostgreSQL
- Swing (JOptionPane)
- JTable / TableViewer
- JSON (exportación de datos)

---

## 🚦 Estado del Proyecto

| Versión | Estado |
|--------:|:-------|
| 1.0     | ✅     |

---

## 👤 Autoría

- [Jose Iglesias Arévalo](https://arevaliis.github.io/Portafolio)

---

## 📄 Licencia

Este proyecto no tiene licencia.

---

## 📫 Contacto

- ✉️ [joseiglesiasarevalo@gmail.com](mailto:joseiglesiasarevalo@gmail.com)
- 💼 [LinkedIn](https://www.linkedin.com/in/jose-iglesias-ar%C3%A9valo-812860206/)
- 🐙 [GitHub](https://github.com/Arevaliis)
# Product Comparison API

API REST construida con **Java 21** y **Spring Boot** que permite almacenar productos con múltiples atributos y recuperar información para facilitar la **comparación entre ítems**.

La solución sigue buenas prácticas de desarrollo backend, incluyendo:

* Arquitectura por capas (Controller, Service, Repository)
* Validaciones y manejo de errores
* Persistencia en memoria (H2)
* Endpoints claros y flexibles
* Comparación dinámica de campos

---

# Tecnologías utilizadas

* Java 21
* Spring Boot 3
* Spring Data JPA
* Base de datos en memoria H2
* Maven
* Jackson (JSON)

---

# Arquitectura

La aplicación está organizada en capas siguiendo principios de separación de responsabilidades:

```
controller  → API REST
application → lógica de negocio
domain      → entidades
infrastructure → repositorios
exception   → manejo de errores
```

Esto permite mantener un diseño limpio, escalable y fácil de mantener.

---

# Cómo ejecutar la aplicación

Desde la raíz del proyecto:

```bash
mvn clean install
mvn spring-boot:run
```

La aplicación se iniciará en:

```
http://localhost:8080
```

puede consultar los metodos en swagger


```
http://localhost:8080/swagger-ui/index.html#/
```
---

# Modelo de Producto

Ejemplo de estructura:

```json
{
  "id": 1,
  "name": "iPhone 16",
  "price": 1200,
  "rating": 0,
  "size": "string",
  "weight": 0,
  "color": "string",
  "specifications": {
    "year": "2024",
    "battery": "4000mAh",
    "storage": "256GB",
    "camera": "48MP",
    "brand": "Apple"
  }
}
```

Las especificaciones son dinámicas y pueden variar según el producto.

---

# Flujo de uso de la API

## 1️ Crear productos

### Producto 1

```bash
curl -X POST http://localhost:8080/products \
-H "Content-Type: application/json" \
-d '{
  "id": 1,
  "name": "iPhone 16",
  "price": 1200,
  "specifications": {
    "year": "2024",
    "battery": "4000mAh",
    "storage": "256GB",
    "camera": "48MP",
    "brand": "Apple"
  }
}'
```

### Producto 2

```bash
curl -X POST http://localhost:8080/products \
-H "Content-Type: application/json" \
-d '{
  "id": 2,
  "name": "iPhone 16 Pro",
  "price": 1400,
  "specifications": {
    "year": "2025",
    "battery": "4500mAh",
    "storage": "512GB",
    "camera": "64MP",
    "brand": "Apple"
  }
}'
```

---

## 2️ Obtener todos los productos

```bash
curl http://localhost:8080/products
```

---

## 3️ Obtener producto por ID

```bash
curl http://localhost:8080/products/1
```

---

# Comparar productos

Endpoint principal solicitado en la prueba.

Permite comparar múltiples productos seleccionando los campos deseados.

## Ejemplo

```bash
curl --location 'http://localhost:8080/products/compare?ids=1,2&fields=name,specifications,id,price'

```

También funciona:

```bash

curl --location 'http://localhost:8080/products/compare?ids=1&ids=2&fields=name,specifications,id,price'
```

---

## Respuesta esperada

```json
[
  {
    "name": "iPhone 16",
    "price": 1200,
    "specifications": {
      "year": "2024",
      "battery": "4000mAh",
      "storage": "256GB"
    }
  },
  {
    "name": "iPhone 16 Pro",
    "price": 1400,
    "specifications": {
      "year": "2025",
      "battery": "4500mAh",
      "storage": "512GB"
    }
  }
]
```

La API facilita la comparación devolviendo los datos relevantes de forma estructurada.

---

# ⚠️ Manejo de errores

La aplicación maneja errores comunes:

| Error                  | Código |
| ---------------------- | ------ |
| Producto duplicado     | 400    |
| Producto no encontrado | 404    |
| Datos inválidos        | 400    |

Ejemplo:

```json
{
  "timestamp": "...",
  "status": 404,
  "error": "Not Found",
  "message": "No product with given id found."
}
```

---

# Decisiones de diseño

* Se utilizó base de datos en memoria para simplificar la ejecución.
* Las especificaciones son dinámicas para soportar distintos tipos de productos.
* La comparación devuelve datos filtrados en lugar de calcular diferencias internas.
* Se priorizó claridad de API y extensibilidad.

---

#  Cumplimiento de la prueba

La solución cumple los objetivos:

✔ API para recibir detalles de productos
✔ Recuperación de múltiples ítems
✔ Comparación flexible mediante campos
✔ Buenas prácticas backend
✔ Respuestas claras y eficientes

---

# Posibles mejoras futuras

* Persistencia en base de datos real
* Autenticación

---

# Autor

Nestor Leonel Osorio Tovar.

---

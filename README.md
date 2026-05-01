# VotaBienPe - Backend 🗳️🇵🇪

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-brightgreen?style=flat-square&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)

Backend de alto rendimiento para **VotaBienPe**, diseñado para procesar información electoral mediante **IA** y **NLP**, permitiendo un voto informado a través de tecnología de punta.

---

## 🌐 Producción

| Servicio | URL |
| :--- | :--- |
| ⚙️ **Backend** | https://eleccionesperubackend.onrender.com |
| 🖥️ **Frontend** | https://elecciones-peru-eta.vercel.app |
| 🗄️ **Base de datos** | Railway (MySQL 8.0) |

---

## 🛠️ Stack Tecnológico

| Categoría | Tecnología |
| :--- | :--- |
| **Lenguaje** | Java 21 (LTS) |
| **Framework** | Spring Boot 3.3.5 |
| **Base de Datos** | MySQL 8.0 (Railway) |
| **IA & NLP** | LangChain4j, Apache OpenNLP, ONNX |
| **Despliegue** | Render (Docker) |

---

## 🧠 Lógica de Negocio Avanzada

### 1. ⚖️ Algoritmo de Match Ponderado
El sistema calcula la afinidad política aplicando pesos específicos por categoría:
- **Impacto Crítico (1.3):** Seguridad y Corrupción.
- **Impacto Alto (1.2):** Educación y Salud.
- **Impacto Base (1.0):** Infraestructura, Ambiente, Estado, Social.

### 2. ⚠️ Penalización por Riesgo Ético
Ajuste automático del puntaje según antecedentes del candidato:
- **Riesgo ALTO:** Condenas (Penalización x2).
- **Riesgo MEDIO:** Investigaciones en curso.
- **Riesgo BAJO:** Archivadas o sin historial.

### 3. 🤖 IA Semántica
Uso de **Embeddings** para analizar planes de gobierno conceptualmente, detectando coincidencias incluso si se usan palabras diferentes.

---

## 📍 API Endpoints Principales

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/candidatos` | Lista todos los candidatos con sus denuncias. |
| `GET` | `/api/preguntas` | Obtiene las preguntas del test de afinidad con opciones. |
| `GET` | `/api/resumen/{id}` | Resumen ejecutivo de un candidato por ID. |
| `GET` | `/api/comparar-full?c1={id}&c2={id}` | Comparación completa entre dos candidatos. |
| `POST` | `/api/match` | Calcula la afinidad política del usuario con los candidatos. |
| `POST` | `/api/chat` | Chatbot electoral con IA. |
| `POST` | `/api/contacto` | Envío de mensajes de contacto. |
| `POST` | `/api/reportes` | Reporte de errores en la información electoral. |
| `GET` | `/actuator/health` | Estado de salud del servicio. |

---

## 🏗️ Estructura del Proyecto

```text
src/main/java/com/elecciones/eleccionesperubackend/
 ├── 📂 config       # Configuración global de CORS.
 ├── 📂 controller   # Exposición de servicios REST y gestión de rutas.
 ├── 📂 dto          # Objetos de transferencia (ComparacionDTO, MatchResultDTO).
 ├── 📂 mapper       # Transformación de datos (Entidad ↔ DTO).
 ├── 📂 model        # Entidades JPA (Candidato, Denuncia, Opcion, Pregunta).
 ├── 📂 repository   # Interfaces de persistencia con Spring Data JPA.
 └── 📂 service      # Lógica central: IA, Match y Similitud Semántica.
```

---

## ⚙️ Configuración e Instalación

### ✅ Requisitos Previos

- ☕ **Java 21** (OpenJDK o Eclipse Temurin)
- 📦 **Maven** 3.8+
- 🗄️ **MySQL** 8.0
- 🐳 **Docker** (opcional, recomendado)

---

## 📥 Instalación del Proyecto

### 1️⃣ Clonar el Repositorio

```bash
git clone https://github.com/jcast2023/eleccionesperubackend.git
cd eleccionesperubackend
```

### 2️⃣ Configurar Base de Datos

Crea un archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/Elecciones2026
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
server.port=8080
```

### 3️⃣ Compilar el Proyecto

```bash
mvn clean install
```

### 4️⃣ Ejecutar el Servidor

```bash
mvn spring-boot:run
```

> [!TIP]
> El backend estará disponible en: http://localhost:8080

---

## 🐳 Ejecución con Docker (Recomendado)

El backend está preparado para ejecutarse en contenedores, lo que garantiza que todas las dependencias (Java 21, MySQL 8.0) se configuren automáticamente.

### Instrucciones:

Desde la raíz del proyecto backend, ejecuta:

```bash
docker-compose up -d --build
```

> [!NOTE]
> **CORS:** Configurado para aceptar peticiones desde `http://localhost:5173` en desarrollo y `https://elecciones-peru-eta.vercel.app` en producción mediante variable de entorno `FRONTEND_URL`.

---

## 🗄️ Diseño de Base de Datos (MySQL)

El sistema utiliza un esquema relacional optimizado para consultas de alta velocidad:

* **Candidatos:** Perfiles, fotos y metadatos biográficos.
* **Denuncias:** Motor de penalización ética, vinculada a cada candidato.
* **Propuestas:** Organizadas por categorías para el análisis semántico.
* **Preguntas/Opciones:** Estructura dinámica que permite modificar el test sin tocar el código.

---

## 👨‍💻 Autor

Desarrollado por **Julio Edson Castillo Ita**  
Proyecto enfocado en democracia digital e inteligencia artificial aplicada al voto informado en Perú.

---



# VotaBienPe - Backend 🗳️🇵🇪

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.5-brightgreen?style=flat-square&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)

Backend de alto rendimiento para **VotaBienPe**, diseñado para procesar información electoral mediante **IA** y **NLP**, permitiendo un voto informado a través de tecnología de punta.

---

## 🛠️ Stack Tecnológico

| Categoría | Tecnología |
| :--- | :--- |
| **Lenguaje** | Java 21 (LTS) |
| **Framework** | Spring Boot 4.0.5 |
| **Base de Datos** | MySQL 8.0 |
| **IA & NLP** | LangChain4j, Apache OpenNLP, ONNX |
| **Seguridad** | JWT (JSON Web Tokens) |

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

## ⚙️ Configuración del Entorno

Edita tu archivo `src/main/resources/application.properties`:

```properties
DB_HOST=localhost
DB_PORT=3306
DB_NAME=Elecciones2026
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña
SERVER_PORT=8080

# Clonar repositorio
git clone [https://github.com/jcast2023/elecciones-peru-backend.git](https://github.com/jcast2023/elecciones-peru-backend.git)

# Instalar y compilar
mvn clean install

# Ejecutar aplicación
mvn spring-boot:run

📍 API Endpoints (v1)MétodoEndpointDescripciónGET/api/candidatosListado de candidatosPOST/api/matchTest de afinidad políticaGET/api/comparar-fullComparativa IA profunda

src/main/java/com/elecciones/eleccionesperubackend/
├── controller      # Endpoints REST
├── dto             # Objetos de transferencia
├── mapper          # Entidad <-> DTO
├── model           # Entidades JPA
└── service         # Lógica de IA y Match

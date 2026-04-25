VotaBienPe - Backend 🗳️🇵🇪Backend de alto rendimiento para la plataforma VotaBienPe, diseñado para centralizar, procesar y analizar información electoral de manera inteligente. Utiliza algoritmos de NLP (Procesamiento de Lenguaje Natural) y Machine Learning para empoderar al ciudadano peruano con un voto informado.🛠️ Stack TecnológicoCategoríaTecnologíaLenguajeJava 21 (LTS)FrameworkSpring Boot 4.0.5Base de DatosMySQL 8.0 (Connector-J)IA & NLPLangChain4j, Apache OpenNLP, ONNX RuntimeSeguridadJWT (JSON Web Tokens)ProductividadLombok, MapStruct, Spring Data JPA🧠 Lógica de Negocio AvanzadaEste proyecto implementa un motor de evaluación con precisión académica:1. Algoritmo de Match (Afinidad Política)Se aplica una Ponderación Inteligente por Categorías para calcular la alineación real entre el usuario y los candidatos:Impacto Crítico (1.3): Seguridad y Corrupción.Impacto Alto (1.2): Educación y Salud.Impacto Moderado (1.1): Economía.Impacto Base (1.0): Infraestructura, Medio Ambiente, Estado, Social, Libertad.2. Penalización por Riesgo ÉticoEl backend analiza el objeto Denuncia y ajusta el puntaje de afinidad automáticamente:❌ Condenas: Penalización doble y alerta de riesgo ALTO.⚠️ En curso: Penalización basada en gravedad y alerta de riesgo MEDIO.✅ Archivadas: Penalización mínima por transparencia.3. Comparación Semántica con IAUtilizamos Embeddings (all-minilm-l6-v2-q) para analizar el "Resumen Largo" de los planes de gobierno. Esto permite identificar similitudes conceptuales aunque los candidatos utilicen términos diferentes.⚙️ Configuración del EntornoVariables de EntornoConfigura estas variables en tu sistema o edita el archivo src/main/resources/application.properties:PropertiesDB_HOST=localhost
DB_PORT=3306
DB_NAME=Elecciones2026
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña
SERVER_PORT=8080
JPA_SHOW_SQL=true
🚀 Instalación y EjecuciónClonar el repositorio:Bashgit clone https://github.com/jcast2023/elecciones-peru-backend.git
cd elecciones-peru-backend
Compilar e Instalar dependencias:Bashmvn clean install
Ejecutar la aplicación:Bashmvn spring-boot:run
📍 API Endpoints (v1)MétodoEndpointDescripciónGET/api/candidatosListado general de candidatos.POST/api/matchProcesa respuestas y devuelve el Top 3 de afinidad.GET/api/comparar-fullComparativa semántica profunda entre dos candidatos.GET/api/resumen/{id}Detalle de perfil, propuestas y antecedentes.🏗️ Estructura del ProyectoPlaintextsrc/main/java/com/elecciones/eleccionesperubackend/
├── controller      # Endpoints REST y gestión de CORS.
├── dto             # Objetos de transferencia de datos.
├── mapper          # Lógica de conversión Entidad <-> DTO.
├── model           # Entidades JPA (Candidato, Denuncia, Propuesta, etc).
├── repository      # Interfaces de persistencia (Spring Data JPA).
└── service         # Lógica de negocio e implementaciones NLP.
Nota sobre CORS: El servidor está pre-configurado para aceptar peticiones desde http://localhost:5173 (Entorno de desarrollo Frontend).
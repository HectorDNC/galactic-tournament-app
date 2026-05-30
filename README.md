# Galactic Tournament API

## Descripción

**Galactic Tournament** es una API REST desarrollada en Spring Boot que gestiona un sistema de torneos galácticos. Permite crear especies, registrar combates entre ellas y mantener un ranking de estadísticas de victorias y derrotas.

### Características principales
- Gestión de especies (crear, actualizar, listar, filtrar por nombre)
- Sistema de combates con lógica de ganador basada en nivel de poder
- Desempates alfabéticos en combates de igual poder
- Ranking de combates ordenado por victorias
- Estadísticas de victorias y derrotas por especie
- API REST con validación de datos

## Requisitos

### Software
- **Java**: versión 21 o superior
- **Maven**: versión 3.6+ (incluye wrapper `mvnw.cmd` y `./mvnw`)
- **Base de datos** (opcional para desarrollo):
  - H2 (en memoria, incluido por defecto en tests)
  - MySQL 8.0+ (para producción)

### Puertos
- Aplicación: `8080` (configurable)
- Base de datos MySQL: `3306` (si se usa)

## Instalación

### 1. Clonar el repositorio
```bash
git clone <url-repositorio>
cd galactic-tournament-back
```

### 2. Configurar variables de entorno (producción con MySQL)

Crear un archivo `.env` o configurar las variables del sistema:

```bash
# Base de datos
export MAIN_DB_URL=jdbc:mysql://localhost:3306/galactic_tournament
export MAIN_DB_USER=root
export MAIN_DB_PASSWORD=tu_password
```

### 3. Compilar el proyecto

**En Windows:**
```bash
mvnw.cmd clean package
```

**En Linux/Mac:**
```bash
./mvnw clean package
```

## Ejecución

### Opción 1: Ejecutar directamente con Maven

**En Windows:**
```bash
mvnw.cmd spring-boot:run
```

**En Linux/Mac:**
```bash
./mvnw spring-boot:run
```

### Opción 2: Ejecutar JAR compilado

```bash
java -jar target/galactictournament-0.0.1-SNAPSHOT.jar
```

### Opción 3: Ejecutar tests

```bash
# Todos los tests
mvnw.cmd test

# Solo tests unitarios de servicios
mvnw.cmd -Dtest=BattleServiceImplTest test

# Con reporte de cobertura
mvnw.cmd clean test
```

## Endpoints principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/species` | Listar especies paginadas (con filtro por nombre opcional) |
| `GET` | `/api/species/{id}` | Obtener especie por ID |
| `POST` | `/api/species` | Crear nueva especie |
| `PUT` | `/api/species/{id}` | Actualizar especie |
| `POST` | `/api/battles` | Iniciar nuevo combate |
| `GET` | `/api/battle-statistics/ranking` | Obtener ranking de combates (por victorias DESC, nombre ASC) |

### Ejemplos de uso

**Crear especie:**
```bash
curl -X POST http://localhost:8080/api/species \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Dragon",
    "powerLevel": 80,
    "specialAbility": "Volar"
  }'
```

**Filtrar especies por nombre:**
```bash
curl "http://localhost:8080/api/species?page=0&size=10&name=drag"
```

**Iniciar combate:**
```bash
curl -X POST http://localhost:8080/api/battles \
  -H "Content-Type: application/json" \
  -d '{
    "fighterLeftId": 1,
    "fighterRightId": 2
  }'
```

**Obtener ranking:**
```bash
curl http://localhost:8080/api/battle-statistics/ranking
```

## Estructura del proyecto

```
src/
├── main/
│   ├── java/tech/galactictournament/
│   │   ├── config/          # Configuración (CORS, etc.)
│   │   ├── domain/
│   │   │   ├── dtos/        # Data Transfer Objects
│   │   │   ├── entities/    # Entidades JPA
│   │   │   ├── mappers/     # Mapeadores Entity ↔ DTO
│   │   │   ├── repositories/# Repositorios Data JPA
│   │   │   └── services/    # Lógica de negocio
│   │   └── web/
│   │       └── controllers/ # Controladores REST
│   └── resources/
│       └── application.yaml # Configuración de la app
└── test/
    └── java/               # Tests unitarios e integración
```

## Tecnologías utilizadas

- **Spring Boot** 4.0.6
- **Spring Data JPA** - ORM con Hibernate
- **Spring MVC** - REST controllers
- **Lombok** - Reducir boilerplate
- **JUnit 5 + Mockito** - Testing
- **Maven** - Build & dependency management
- **H2 Database** - Base de datos en memoria (desarrollo/tests)
- **MySQL** - Base de datos (producción, opcional)

## Lógica de combates

### Ganador
1. Se comparan los `powerLevel` de ambas especies
2. La que tenga mayor poder gana
3. **Desempate**: Si ambas tienen igual poder, gana la que tenga el nombre alfabéticamente primero (case-insensitive)

### Estadísticas
- Al terminar un combate, se actualizan automáticamente las victorias del ganador y derrotas del perdedor
- Cada especie tiene un registro de `BattleStatistics` con contadores

## Solución de problemas

### Error: "MAIN_DB_URL not set"
- Para desarrollo, asegúrate de que la aplicación use perfil H2
- O configura las variables de entorno con los datos de tu MySQL

### Tests fallan
```bash
# Limpia y reconstruye
mvnw.cmd clean test

# O ejecuta tests de una clase específica
mvnw.cmd -Dtest=BattleServiceImplTest test
```

### Puerto 8080 ya en uso
Cambiar el puerto en `application.yaml`:
```yaml
server:
  port: 8081
```

## Contacto & Contribuciones

Para reportar bugs o sugerencias, crea un issue en el repositorio.

---

**Última actualización**: Junio 2026

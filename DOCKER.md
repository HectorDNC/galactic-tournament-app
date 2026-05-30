# Docker - Galactic Tournament Backend

La aplicación se ejecuta en Docker usando una base de datos MySQL **externa** (en otro servidor).

## Requisitos
- Docker
- Docker Compose
- Acceso a un servidor MySQL externo

## Configuración

### 1. Crear archivo `.env` (o variables de entorno)

Copia el archivo `.env.example` y configura los datos de tu servidor MySQL:

```bash
cp .env.example .env
```

Edita `.env` con tus valores:
```env
MAIN_DB_URL=jdbc:mysql://url_sql:3306/galactictournament
MAIN_DB_USER=usuario
MAIN_DB_PASSWORD=contraseña
```

### 2. Ejecutar la aplicación

Con archivo `.env`:
```bash
docker compose up --build
```

O pasar las variables directamente:
```bash
docker compose up --build \
  -e MAIN_DB_URL="jdbc:mysql://url_sql:3306/galactictournament" \
  -e MAIN_DB_USER="usuario" \
  -e MAIN_DB_PASSWORD="contraseña"
```

O con `docker run`:
```bash
docker build -t galactic-tournament-back .

docker run -d -p 8080:8080 \
  -e MAIN_DB_URL="jdbc:mysql://url_sql:3306/galactictournament" \
  -e MAIN_DB_USER="usuario" \
  -e MAIN_DB_PASSWORD="contraseña" \
  --name galactic-tournament-back galactic-tournament-back
```

## Variables de Entorno

| Variable | Descripción |
|----------|-------------|
| `MAIN_DB_URL` | URL JDBC de la base de datos MySQL |
| `MAIN_DB_USER` | Usuario de la base de datos |
| `MAIN_DB_PASSWORD` | Contraseña de la base de datos |

## Detener la aplicación

```bash
docker compose down
```

## Logs

```bash
docker compose logs -f app
```

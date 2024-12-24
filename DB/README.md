# Importar Backup y Restaurar Base de Datos Oracle XE

Este documento describe los pasos necesarios para crear un contenedor de Oracle XE, importar un backup (.DMP) y restaurar los datos.

## 1. Crear un contenedor para Oracle XE
Ejecuta el siguiente comando para crear y ejecutar un contenedor con Oracle XE:

```bash
podman run -d \
  --name oracle-xe \
  -p 1521:1521 \
  -p 5500:5500 \
  -e ORACLE_PASSWORD=123456 \
  -e ORACLE_PDB=XEPDB1 \
  gvenzl/oracle-xe:latest
```

## 2. Acceder al contenedor
Ingresa al contenedor usando el siguiente comando:

```bash
podman exec -it oracle-xe bash
```

Conéctate a la base de datos como el usuario `system` para crear un nuevo usuario:

```bash
sqlplus system/123456
```

## 3. Crear un usuario
Dentro de SQL*Plus, ejecuta los siguientes comandos para crear el usuario `JW07` y asignar los permisos necesarios:

```sql
CREATE USER JW07 IDENTIFIED BY 123456;
GRANT CONNECT, RESOURCE TO JW07;
ALTER USER JW07 QUOTA UNLIMITED ON USERS;
GRANT CREATE VIEW TO JW07;
```

## 4. Verificar la carpeta de importación
Dentro del contenedor, ejecuta este query para encontrar la carpeta asociada al directorio `DATA_PUMP_DIR`:

```sql
SELECT directory_name, directory_path FROM dba_directories;
```

Ejemplo de resultado:

```
DATA_PUMP_DIR
/opt/oracle/admin/XE/dpdump/
```

## 5. Copiar el archivo .DMP al contenedor
Desde el sistema host, copia el archivo `.DMP` a la carpeta correspondiente en el contenedor:

```bash
podman cp /ruta/a/tu/jw07_28102017_1222pm.DMP oracle-xe:/opt/oracle/admin/XE/dpdump/
```

## 6. Importar el backup
Sal de SQL*Plus y ejecuta el siguiente comando para realizar la importación del backup usando el usuario `system`:

```bash
imp SYSTEM/123456 fromuser=JW07 touser=JW07 file=/opt/oracle/admin/XE/dpdump/jw07_28102017_1222pm.DMP
```


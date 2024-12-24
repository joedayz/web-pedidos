IMPORTAR BACKUP Y RESTORE
=========================

1. Crear un contenedor para XE:

~ ❯ podman run -d \
  --name oracle-xe \
  -p 1521:1521 \
  -p 5500:5500 \
  -e ORACLE_PASSWORD=123456 \
  -e ORACLE_PDB=XEPDB1 \
  gvenzl/oracle-xe:latest

2. Ingresa al contenedor con: podman exec -it oracle-xe bash

y conectate como system para crear el usuario JWT07:

sqlplus system/123456


3. Crear el usuario JW07

CREATE USER JW07 IDENTIFIED BY 123456;
GRANT CONNECT, RESOURCE TO JW07;
ALTER USER JW07 QUOTA UNLIMITED ON USERS;


GRANT CREATE VIEW TO JW07;

4. Averiguar que carpeta en tu sistema es el DATA_PUMP_DIR ejecutando este query:


SELECT directory_name, directory_path FROM dba_directories;

Ejemplo:

DATA_PUMP_DIR
/opt/oracle/admin/XE/dpdump/

5. Fuera del contenedor copia el .DMP a esa carpeta dpdump:

podman cp /ruta/a/tu/jw07_28102017_1222pm.DMP oracle-xe:/opt/oracle/admin/XE/dpdump/


6. Sal de sqlplus y ejecuta el import con system:


imp SYSTEM/123456 fromuser=JW07 touser=JW07 file=/opt/oracle/admin/XE/dpdump/jw07_28102017_1222pm.DMP


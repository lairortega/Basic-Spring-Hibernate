# Basic-Spring-Hibernate
Configuración Básica para realizar conexión a base de datos desde Hibernet e inyectar el sessiónFactory como Singleton de Spring

## Clases de configuración

/src/main/java/lair/ortega/configuration/Hibernate.java

## Clase e Interfaz genérica para los CRUD de todas las entidades.

/src/main/java/lair/ortega/dao/IGenericDao.java

/src/main/java/lair/ortega/dao/GenericDao.java

## Implementación DAO
La Interfaz [IUserDao.java](https://github.com/lairortega/Basic-Spring-Hibernate/blob/master/src/main/java/lair/ortega/dao/IUserDao.java) define los métodos especificos de ésta entidad y herera los métodos CRUD de [GenericDao.java](https://github.com/lairortega/Basic-Spring-Hibernate/blob/master/src/main/java/lair/ortega/dao/GenericDao.java)

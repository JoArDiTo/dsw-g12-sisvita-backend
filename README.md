# GRUPO 12 SISVITA MICRO SERVICES
Este proyecto es una implementación de microservicios utilizando Spring Boot y Eureka para el descubrimiento de servicios. La arquitectura está diseñada para ser altamente escalable y fácil de mantener. A continuación, se detallan los componentes principales del sistema.

## Componentes

### Eureka Service
El `eureka-service` actúa como un servidor de descubrimiento de servicios. Todos los microservicios se registran en Eureka, lo que permite que se descubran entre sí sin necesidad de una configuración de red fija.

### Gateway Service
El `gateway-service` funciona como un gateway de API, proporcionando un único punto de entrada para todas las solicitudes externas. Este servicio enruta las solicitudes a los microservicios correspondientes basándose en la configuración de rutas.

### Config Server
El `config-server` gestiona la configuración externa de los microservicios. Permite centralizar y externalizar la configuración, facilitando la gestión de la configuración en entornos de desarrollo, prueba y producción.

### User Service
El `user-service` gestiona toda la lógica relacionada con los usuarios, incluyendo autenticación, registro y gestión de perfiles de usuario.

### Test Service
El `test-service` está diseñado para realizar pruebas internas de los microservicios, asegurando que todos los componentes funcionen correctamente juntos.

### Vigilance Service
El `vigilance-service` se encarga de monitorear y registrar las actividades sospechosas o inusuales dentro del sistema, proporcionando una capa adicional de seguridad.

## Tecnologías Utilizadas

- **Spring Boot**: Para la creación de microservicios de forma rápida y eficiente.
- **Eureka**: Para el descubrimiento de servicios y la balance de carga.
- **Spring Cloud Gateway**: Para el enrutamiento y filtrado de solicitudes.
- **Spring Cloud Config**: Para la gestión centralizada de la configuración.

## Arquitectura

La arquitectura de microservicios permite desarrollar, desplegar y escalar cada servicio de forma independiente, lo que resulta en sistemas más robustos y flexibles. La utilización de Eureka y Spring Cloud facilita la gestión de estos servicios, permitiendo una integración y descubrimiento de servicios eficiente.


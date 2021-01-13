# Challenge Code - Vending Machine

El diseño de la solución consiste en dos aplicaciones springboot que exponen interfaces REST. La primera aplicación "vendingmachine" tiene la funcionalidad asociada a la máquina expendedora, la segunda "salesadministration" tiene la funcionalidad de centralizar información de las máquinas y recibir alertas.

Dentro de las implementaciones se usaron distintas formas de implementar inyecciones, de trabajar con JPA (repositorios y HQL), abstraer servicios con interfaces etc. para poder maximizar las metodologías de trabajo sobre las cuales se tiene conocimiento. 

Dentro de cada aplicación existen requerimientos no alcanzados, supuestos y funcionalidades mock para situaciones fuera del alcance (ej. interacción con hardware, comunicación con sitios de pagos, envíos de alerta por algún medio.)


# vendingmachine 

### Supuestos:
	Como interfaz de entrada se usa un RestController con distintos métodos que se corresponden a eventos que ocurren en una máquina. Esta decisión se tomo teniendo en cuenta que los servicios rest no son stateless en este caso, sino que la maquina posee un estado interno.
	Los test de compra de ítems están pensados en el escenario de que siempre habrá monedas para el cambio. No se implemento el error por falta de monedas para el cambio.
	La información de stock y efectivo inicial de la maquina se carga por código y no tiene persistencia.
	Se utiliza una base de datos h2 con persistencia en archivo
	
### Requerimientos no alcanzados
    No se realiza caso de test para compra con tarjeta de crédito. La implementación corresponde al modelo 1 pero soporta los dos modelos.
    No se implementa la funcionalidad asociada a la apertura de la máquina
    No se implementa la funcionalidad de impresión de Tickets
    Si bien se persisten todas las transacciones, no existen consultas de transacciones por algún criterio implementadas.
    
## Decisiones arquitecturales:
    La aplicacion cuenta con tres capas definidas, la capa de REST como interface de entrada, lógica del negocio en la capa de servicios y persistencia de datos en la capa de dao
    Se aplica herencia en modelos para definir tipos de pago aceptados
    Se aplica Strategy para definir la implementación del pago según el tipo de pago.
    Se define un scheduler con Quarzt para periódicamente enviar transacciones a la aplicación "salesadministration" y también enviar alertas a la misma.


    
#salesadministration
    
### Supuestos:
	Se define un modo test para crear datos iniciales.
	
### Requerimientos no alcanzados
    Si bien se persisten todas las transacciones de distintas maquinas, no existen consultas de transacciones por algún criterio implementadas.
    
## Desiciones arquitecturales:
    La aplicación cuenta con tres capas definidas, la capa de REST como interface de entrada, lógica del negocio en la capa de servicios y persistencia de datos en la capa de dao.


# TEST de aplicaciones
## prerequisitos
modificar los archivos application.properties para definir los paths de los archivos de base de datos H2

## vendingmachine
Para testear las aplicaciones se requiere ejecutar los test de vendingmachine con la aplicación salesadministration levantada. Estos test generan transacciones en la maquina y alertas enviadas a salesadministration.
Luego iniciar vendingmachine y esperar que el scheduler procese las transacciones creadas por los test.

## salesadministration 
Se puede corre el test de manera independiente



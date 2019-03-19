# s1_SitiosWeb_201910
[![BugStatus](http://jenkins-1.sis.virtual.uniandes.edu.co:9000/api/project_badges/measure?project=co.edu.uniandes.csw%3As1_sitios&metric=bugs)](http://jenkins-1.sis.virtual.uniandes.edu.co:9000/dashboard?id=co.edu.uniandes.csw%3As1_sitios)
[![CodeSmells](http://jenkins-1.sis.virtual.uniandes.edu.co:9000/api/project_badges/measure?project=co.edu.uniandes.csw%3As1_sitios&metric=code_smells)](http://jenkins-1.sis.virtual.uniandes.edu.co:9000/dashboard?id=co.edu.uniandes.csw%3As1_sitios)
[![Coverage](http://jenkins-1.sis.virtual.uniandes.edu.co:9000/api/project_badges/measure?project=co.edu.uniandes.csw%3As1_sitios&metric=coverage)](http://jenkins-1.sis.virtual.uniandes.edu.co:9000/dashboard?id=co.edu.uniandes.csw%3As1_sitios)
[![lines](http://jenkins-1.sis.virtual.uniandes.edu.co:9000/api/project_badges/measure?project=co.edu.uniandes.csw%3As1_sitios&metric=ncloc)](http://jenkins-1.sis.virtual.uniandes.edu.co:9000/dashboard?id=co.edu.uniandes.csw%3As1_sitios)

Desarollo de software para CMSites:

  - Albert Molano
  - Allan Corinaldi
  - Daniel Preciado
  - Santiago Ballesteros
  - Julian Amezquita

# Problema a resolver:
La Unidad de Tecnologías de Información UTI de la Universidad de Los Alpes, es la unidad encargada del soporte y operación de todos los sistemas que apoyan los procesos de negocio de la Universidad. Entre los sistemas que operan se encuentran los sitios web, que en los últimos años han ido creciendo exponencialmente en la cantidad de sitios desplegados activos; llegando a más de 600 sitios web que deben ser soportados cada día.

Nuestro cliente (UTI) nos ha solicitado la construcción de una plataforma que les permita, inicialmente, gestionar la información de los sitios web y así poder llevar un mejor control, y brindar mejor servicio.

La plataforma debe permitir el registro de sitios web con la información del sitio; tecnologías usadas (versiones), hardware o servicio donde se encuentra desplegado, personas responsables, unidad que lo solicita, descripción, audiencias, categoría, integraciones con otros sistemas, e imagen del sitio.

Las tecnologías en las que se construyen un sitio web van desde el lenguaje de programación, los frameworks utilizados, servidor de aplicación y librerías. Es importante que se tenga la información necesaria de la tecnología, así como, los enlaces a sitios oficiales.

Con respecto al hardware utilizado, es necesario tener claridad de quién es el proveedor de la infraestructura o plataforma de despliegue; puede ser un proveedor externo o en infraestructura on-premise. Es importante tener la caracterización del hardware donde está corriendo la aplicación; dirección IP, cores, ram, CPU, así como la información de que tipo de plataforma se usa; servicio de hosting, ambiente virtualizado o no, o si es un PaaS de un proveedor de servicios cloud.

Es necesario poder contar con la información las personas responsables del sitio. Tener la información de quien lo solicitó, la persona que se hace responsable del sitio y modifica su contenido, y el proveedor o desarrollador interno que se encarga del soporte del sitio web. De estos usuarios es necesario contar con los datos de contacto, así como la información de la dependencia de la universidad a la que están vinculados.

Con respecto a la información del sitio es necesario contar con descripción, nombre, propósito, audiencias esperadas, su categorización, las integraciones con otros sistemas, los sitios que se enlazan a través del sitio web y la imagen. También es necesario que se presente el estado del sitio web: activo, inactivo, en falla y demás, así como las fechas de lanzamiento, solicitud y última modificación.

El sistema debe tener dos usuarios, el administrador que puede acceder y gestionar todos los sitios, y el equipo responsable que puede acceder y gestionar los datos y ver el estado de los sitios que administran. Los responsables del sitio se les emitirá una notificación cuando el estado de sitio cambie; los mecanismos de notificación podría ser un mensaje en whatsapp, SMS, correo electrónico u otro que se defina en mutuo acuerdo.

El cliente ha solicitado que los usuarios del sistema puedan llevar una bitácora de los cambios que se realizan en cada sitio; en la bitácora se registrarán cambios que pueden ir desde tecnología, cambios de estado, o cambios en la información del sitio.  

El cliente nos ha solicitado que tengamos presentes algunos requerimientos deseables y evaluemos si son posibles de implementar en esta primera fase. 

El cliente desea que el proceso de verificación del estado del sitio se realice por demanda de forma automática, al igual que la captura de la imagen del sitio y la verificación de las tecnologías que lo soportan. También que el sistema puede verificar si alguna tecnología de la plataforma es la última versión o presenta bugs críticos, notificando al usuario si su sitio web está en riesgo. Y por último que se pueda realizar de forma automática algún proceso que le permita conocer los hipervínculos a otros sitios web y si estos hipervínculos están bien o generan error.

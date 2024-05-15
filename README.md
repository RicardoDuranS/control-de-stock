# Aplicación Java con JDBC y DAO
Este repositorio contiene una aplicación Java que utiliza la interfaz JDBC y sigue una arquitectura DAO para separar la lógica de negocios de las operaciones de acceso a la base de datos MySQL local. El objetivo principal de este proyecto es demostrar el uso de JDBC y la implementación del patrón DAO.

## Temas Aprendidos y Aplicados
### Gestión de Dependencias con Maven
Se utiliza Maven para gestionar las dependencias del proyecto, lo que facilita la incorporación de librerías como el driver de MySQL y otras utilidades.

### Patrones de Diseño
Se implementan patrones como el Factory y el DAO, permitiendo optimizar la reutilización de código y centralizar las operaciones de acceso a recursos.

### Arquitectura MVC
El proyecto sigue el modelo de arquitectura MVC (Modelo-Vista-Controlador), separando las responsabilidades en capas de vista, modelo y reglas de negocio para una mejor organización y mantenimiento del código.

### Optimización de Recursos y Rendimiento
Se configura un pool de conexiones con C3P0 para mejorar el rendimiento de la aplicación, además de utilizar "try with resources" para gestionar automáticamente el cierre de recursos, optimizando así el uso de memoria.

### Seguridad
Se aborda la seguridad contra ataques de SQL injection mediante el uso de PreparedStatements. También se evita el problema de las consultas N + 1 al relacionar tablas, garantizando la integridad de los datos y la seguridad de la aplicación.

### Aplicabilidad
Los conocimientos y técnicas aprendidos en esta clase pueden ser aplicados en proyectos reales para mejorar la calidad y el rendimiento de las aplicaciones Java que utilicen JDBC y el driver de MySQL. Las buenas prácticas, los patrones de diseño, la optimización de recursos y la seguridad son aspectos fundamentales en el desarrollo de software robusto y confiable.

### Contribución
Siéntete libre de contribuir a este proyecto abriendo nuevas issues, proponiendo mejoras o enviando pull requests. ¡Tu colaboración es bienvenida!


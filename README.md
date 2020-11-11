# babel-level-test
Esta aplicación sigue le patrón de diseño MVVM. He utilizado Koin para la inyección de dependencias, Retrofit para consumir servicios web, Picasso para la gestión de las imagenes y RxKotlin para implementar programación reactiva. La aplicación sigue el concepto de single activity y utilizo NavigationUI de Jetpack para la navegación entre los fragmentos. Para comunicar cada vista con su view model utilizo observers que se activan cuando los datos que estan observando cambian y actualizan la vista.

En el paquete ui están las vistas y sus view models correspondientes.

En el paquete data están los repositorios y la capa de networking. Dentro podemos encontrar también el paquete util, en él he implementado un wrapper para la clase LiveData que me permite gestionar los estados LOADING, ERROR y SUCCESS de la peticiones a los servicios web.

En el paquete di esta la configuración de la inyección de dependencias de los view models, los repositorios y la capa de networking.


Debido a que no tengo más tiempo para desarrollar la aplicación no he podido realizar unit test y el control de errores está planteado pero no desarrollado

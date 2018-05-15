# track-bus
Nos permite visuzalizar las rutas de buses de los colegios donde viajan sus hijos y poder hacer seguimiento a través de un mapa.

# Estructura del proyecto.
# - Adapters
# - DataAdapter.class:

Esta clase proporciona acceso a los elemntos de datos, es responsable de hacer una vista para cada elemento en el conjunto de datos. Para este caso es poder poblar una lista de datos(Rutas) en un RecyclerView.

# - Fragment
# - MainActivityFragment.class

Es una clase que extienede de Fragmente que nos permite visualizar la lista de las rutas.
# - Interfaces
# - RequestIF.class

Implementa una serie de métodos que nos permite realizar peticiones HTTP a una API por medio de la librería Retrofit
# - Models
# - Bus.class

Nos permite terner acceso a los datos de las rutas.
# - CoordinatesBus.class

Nos permite terner acceso a los datos de las coordenadas de cada bus.
# - Activitys
# - DetailMapsActivity.class
Clase donde nos permite visalizar el moviento de la ruta(seguimiento).
# - Utils
# - Alert.class

Implementa una serie de métodos los cuales nos permite mostrar una serie de Alerts.
# - CheckConexion.class

Nos permite verificar si hay o no conexión a Internet. Solo se instancia la clase donde es necesario compranoar la conexión.
# - JSONResponse.class


Nos permite manejar la respuesta y manipulas los datos de las rutas.
# - MainActivity

Es la actividad principal donde contiene el fragment  MainActivityFragment donde nos permite visualizar las rutas.

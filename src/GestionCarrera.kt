import java.util.*
import kotlin.random.Random

class GestionCarrera private constructor() {
    companion object {
        var nombresVehiculos = mutableListOf<String>()
        /**
         * Solicita al usuario que ingrese el número de participantes para la carrera y lo valida para asegurarse de que esté dentro del rango permitido (entre 1 y 50).
         *
         * @return El número de participantes ingresado por el usuario.
         */
        fun pedirNumeroParticipantes(): Int {
            var todoOk = false
            var nparticipantes = 0
            while (!todoOk){
                println("Introduce el número de participantes: ")
                try {
                    nparticipantes = readln().toInt()
                    if (nparticipantes !in 1..50) {
                        throw NumberFormatException("Introduce un número entre 1 y 50")
                    }
                    todoOk = true
                }catch (e:NumberFormatException){
                    println("Introduce un número entre 1 y 50")
                }
            }
            return nparticipantes
        }
        /**
         * Solicita al usuario que ingrese el nombre de un vehículo y lo valida para asegurarse de que no esté vacío y sea único entre los nombres previamente ingresados.
         *
         * @param numeroDeVehiculo El número de vehículo para el que se solicita el nombre.
         * @return El nombre único del vehículo ingresado por el usuario.
         */
        fun generarNombre(numeroDeVehiculo:Int): String {
            var nombreV:String? = null
            while (nombreV == null){
                print("* Nombre del vehículo ${numeroDeVehiculo} -> ")
                var nombreAMeter:String = ""
                try {
                    nombreAMeter = readln().lowercase(Locale.getDefault()).trim()
                    if (nombreAMeter in nombresVehiculos || nombreAMeter.trim().isBlank()) throw IllegalArgumentException("El nombre no tiene que estar vacio y ser único")
                    else {
                        nombresVehiculos.add(nombreAMeter)
                        nombreV = nombreAMeter
                    }
                }catch (e:IllegalArgumentException){
                    println(e)
                }
            }
            return nombreV
        }

        /**
         * Genera una lista de vehículos aleatorios basados en el número especificado, con nombres únicos y atributos aleatorios.
         *
         * @param numeroAGenerar El número de vehículos que se generarán aleatoriamente
         * @return Una lista de vehículos aleatorios
         */
        fun generarVehiculosAleatorios(numeroAGenerar:Int):MutableList<Vehiculo>{
            var vgenerados = 0
            var listaVehiculo = mutableListOf<Vehiculo>()
            while (numeroAGenerar != vgenerados){
                val tipoVehiculo = Random.nextInt(1,5)
                val nombreV = generarNombre(++vgenerados)
                val vehiculoGenerado:Vehiculo = when(tipoVehiculo){
                    1 -> {
                        val marcaModelo = MarcaModelo.Automovil.random()
                        val capacidad = generarCapacidad(1)
                        Automovil(
                            nombreV,
                            marcaModelo.first,
                            marcaModelo.second,
                            capacidad,
                            generarCombustible(capacidad),
                            0f,
                            Random.nextBoolean()
                        )
                    }
                    2 -> {
                        val marcaModelo = MarcaModelo.Motos.random()
                        val capacidad = generarCapacidad(2)
                        Motocicleta(
                            nombreV,
                            marcaModelo.first,
                            marcaModelo.second,
                            capacidad,
                            generarCombustible(capacidad),
                            0f,
                            generarCilindrada()
                        )
                    }
                    3 -> {
                        val capacidad = generarCapacidad(3)
                        Camion(
                            nombreV,
                            "",
                            "",
                            capacidad,
                            generarCombustible(capacidad),
                            0f,
                            Random.nextBoolean(),
                            Random.nextInt(1000, 10000)
                        )
                    }
                    else -> {
                        val capacidad = generarCapacidad(4)
                        Quad(
                            nombreV,
                            "",
                            "",
                            capacidad,
                            generarCombustible(capacidad),
                            0f,
                            generarCilindrada()
                        )
                    }
                }
                println("Te ha tocado un $vehiculoGenerado")

                listaVehiculo.add(vehiculoGenerado)
            }
            return listaVehiculo
        }
        /**
         * Genera y devuelve una capacidad aleatoria de combustible para un tipo de vehículo específico.
         *
         * @param tipoVehiculo El tipo de vehículo
         * @return La capacidad de combustible generada
         */
        private fun generarCapacidad(tipoVehiculo: Int): Float {
            return when (tipoVehiculo) {
                1 -> Random.nextInt(30, 61).toFloat() // Automóvil
                2 -> Random.nextInt(15, 31).toFloat() // Motocicleta
                3 -> Random.nextInt(90, 151).toFloat() // Camión
                else -> Random.nextInt(20, 41).toFloat() // Quad
            }
        }
        /**
         * Genera y devuelve una cantidad aleatoria de combustible dentro de un rango específico basado en la capacidad del tanque
         *
         * @param capacidad La capacidad total del tanque
         * @return La cantidad aleatoria de combustible
         */
        fun generarCombustible(capacidad: Float): Float {
            return ((Random.nextInt(20, 100) / 100f) * capacidad).redondear(2)
        }
        fun generarCilindrada():Int{
            return arrayOf(125, 250, 400, 500, 750, 900, 1000).random()
        }
    }
}

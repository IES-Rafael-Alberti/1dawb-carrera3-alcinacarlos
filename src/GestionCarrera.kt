import java.util.*
import kotlin.random.Random

class GestionCarrera private constructor() {
    companion object {
        var nombresVehiculos = mutableListOf<String>()
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
        fun generarNombre(numeroDeVehiculo:Int): String {
            var nombreV:String? = null
            while (nombreV == null){
                print("* Nombre del vehículo ${numeroDeVehiculo} -> ")
                var nombreAMeter:String = ""
                try {
                    nombreAMeter = readln().lowercase(Locale.getDefault()).trim()
                    if (nombreAMeter in nombresVehiculos) throw IllegalArgumentException("Nombre repetido, todos los vehiculso tienen que tener un nombre único")
                }catch (e:IllegalArgumentException){
                    println(e)
                }
                nombreV = nombreAMeter
            }
            return nombreV
        }

        fun generarVehiculosAleatorios(numeroAGenerar:Int):MutableList<Vehiculo>{
            var vgenerados = 0
            var listaVehiculo = mutableListOf<Vehiculo>()
            val tipoVehiculo = Random.nextInt(1,4)
            while (numeroAGenerar != vgenerados){
                var nombreV:String = generarNombre(++vgenerados)
                val vehiculoAGenerado:Vehiculo = when(tipoVehiculo){
                    1 -> {
                        val marcaModelo = MarcaModelo.Automovil.random()
                        Automovil(
                            nombreV,
                            marcaModelo.first,
                            marcaModelo.second,
                            generarCapacidad(1),
                            generarCapacidad(1),
                            0f,
                            Random.nextBoolean()
                        )
                    }
                    2 -> {
                        val marcaModelo = MarcaModelo.Motos.random()
                        Motocicleta(
                            nombreV,
                            marcaModelo.first,
                            marcaModelo.second,
                            generarCapacidad(2),
                            generarCapacidad(2),
                            0f,
                            generarCilindrada()
                        )
                    }
                    3 -> {
                        Camion(
                            nombreV,
                            "",
                            "",
                            generarCapacidad(3),
                            generarCapacidad(3),
                            0f,
                            Random.nextBoolean(),
                            Random.nextInt(1000, 10000)
                        )
                    }
                    else -> {
                        Quad(
                            nombreV,
                            "",
                            "",
                            generarCapacidad(3),
                            generarCapacidad(3),
                            0f,
                            generarCilindrada()
                        )
                    }
                }


                listaVehiculo.add()
            }
            return listaVehiculo
        }
        fun generarCapacidad(tipoVehiculo: Int): Float {
            return when (tipoVehiculo) {
                1 -> Random.nextInt(30, 61).toFloat() // Automóvil
                2 -> Random.nextInt(15, 31).toFloat() // Motocicleta
                3 -> Random.nextInt(90, 151).toFloat() // Camión
                else -> Random.nextInt(20, 41).toFloat() // Quad
            }
        }
        fun generarCombustible(capacidad: Float): Float {
            return ((Random.nextInt(20, 100) / 100f) * capacidad).redondear(2)
        }
        fun generarCilindrada():Int{
            return arrayOf(125, 250, 400, 500, 750, 900, 1000).random()
        }
    }
}

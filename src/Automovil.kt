/**
 * Representa un automóvil, que es una especialización de [Vehiculo], añadiendo la característica de si es eléctrico o no.
 *
 * @property esHibrido Indica si el automóvil es eléctrico (true) o no (false).
 * @constructor Crea un automóvil con los parámetros especificados, heredando propiedades y funcionalidades de [Vehiculo].
 */
open class Automovil(
    nombre: String,
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Float,
    val esHibrido: Boolean
) : Vehiculo(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    companion object {
        const val AHORRO_ELECTRICO = 5.0f // Si es eléctrico, asume un rendimiento de 5 km más por litro.
        const val KM_POR_DERRAPE = 7.5f // Gasto equivalente a 7,5 km.
        const val KM_POR_DERRAPE_ELECTRICO = 6.25f // Gasto equivalente a 6,25 km si es eléctrico.

        var condicionBritanica: Boolean = false
            private set

        /**
         * Actualiza el estado de la condición de Britania (conducción Británica).
         */
        fun cambiarCondicionBritanica(nuevaCondicion: Boolean) {
            condicionBritanica = nuevaCondicion
        }
    }

    /**
     * Calcula y devuelve la autonomía del automóvil en kilómetros. Si el automóvil es eléctrico,
     * se ajusta la eficiencia de combustible restando el ahorro eléctrico.
     *
     * @return La autonomía del automóvil en kilómetros como [Int].
     */
    override fun calcularAutonomia() =
        if (esHibrido)
            (combustibleActual * (KM_POR_LITRO + AHORRO_ELECTRICO)).redondear(2)
        else
            super.calcularAutonomia()

    /**
     * Ejecuta una maniobra de derrape con el automóvil, consumiendo combustible adicionalmente.
     * Este método simula el derrape sin importar el nivel de combustible actual.
     *
     * @return El nivel de combustible restante después de realizar el derrape, como [Float].
     */
    fun realizaDerrape(): Float {
        if (esHibrido) {
            actualizaCombustible(KM_POR_DERRAPE_ELECTRICO)
        }
        else {
            actualizaCombustible(KM_POR_DERRAPE)
        }
        return combustibleActual
    }

    /**
     * Actualiza la cantidad de combustible actual basado en la distancia recorrida, considerando la eficiencia de
     * combustible del vehículo.
     *
     * @param distanciaReal La distancia real recorrida por el vehículo.
     */
    override fun actualizaCombustible(distanciaReal: Float) {
        if (esHibrido) {
            val combustibleGastado = (distanciaReal / (KM_POR_LITRO + AHORRO_ELECTRICO))
            combustibleActual -= combustibleGastado.redondear(2)
        }
        else {
            super.actualizaCombustible(distanciaReal)
        }
    }

    /**
     * Sobrescribe el método toString de la clase [Vehiculo] para proporcionar una representación en cadena de texto
     * específica del automóvil, incluyendo su estado de ser eléctrico además de los detalles heredados de Vehiculo.
     *
     * @return Una cadena de texto que representa al automóvil.
     */
    override fun toString(): String {
        return "Automovil(nombre=${nombre.capitalizarCadaPalabra()}, marca=$marca, modelo=$modelo, capacidadCombustible=$capacidadCombustible, combustibleActual=$combustibleActual, kilometrosActuales=$kilometrosActuales, esElectrico=$esHibrido)"
    }
}

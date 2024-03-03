/**
 * Representa una motocicleta, que es una especialización de [Vehiculo], con la adición de la cilindrada como propiedad única.
 *
 * @property cilindrada La cilindrada de la motocicleta.
 * @constructor Crea una motocicleta con los parámetros especificados, heredando propiedades y funcionalidades de [Vehiculo].
 */
open class Motocicleta(
    nombre: String,
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Float,
    val cilindrada: Int
) : Vehiculo(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    init {
        require(cilindrada in 125..1000) { "Una motocicleta debe tener entre 125 y 100 cc." }
    }

    companion object {
        const val KM_POR_LITRO = 20.0f // 20 KM por litro.
        const val KM_POR_CABALLITO = 6.5f // Gasto equivalente a 6,5 km.
    }

    /**
     * Calcula y devuelve la autonomía de la motocicleta en kilómetros, considerando una eficiencia de
     * combustible específica para motocicletas.
     *
     * @return La autonomía de la motocicleta en kilómetros como [Int].
     */
    override fun calcularAutonomia() = (combustibleActual * (KM_POR_LITRO - (1 - (cilindrada/1000)))).redondear(2)

    /**
     * Ejecuta una maniobra de caballito con la motocicleta, consumiendo una cantidad fija de combustible.
     * Este método simula el caballito sin importar el nivel de combustible actual.
     *
     * @return El nivel de combustible restante después de realizar el caballito, como [Float].
     */
    fun realizaCaballito(): Float {
        actualizaCombustible(KM_POR_CABALLITO)
        return combustibleActual
    }

    /**
     * Actualiza la cantidad de combustible actual basado en la distancia recorrida, considerando la eficiencia de
     * combustible del vehículo.
     *
     * @param distanciaReal La distancia real recorrida por el vehículo.
     */
    override fun actualizaCombustible(distanciaReal: Float) {
        val combustibleGastado = (distanciaReal / (KM_POR_LITRO - (1 - (cilindrada/1000))))
        combustibleActual -= combustibleGastado.redondear(2)
    }

    /**
     * Sobrescribe el método toString de la clase [Vehiculo] para ofrecer una representación textual de la
     * motocicleta que incluye su cilindrada, además de los atributos heredados de la clase base.
     *
     * @return Una representación en cadena de texto de la motocicleta, detallando su identificación, características, y estado actual.
     */
    override fun toString(): String {
        return "Motocicleta(nombre=${nombre.capitalizarCadaPalabra()}, marca=$marca, modelo=$modelo, capacidadCombustible=$capacidadCombustible, combustibleActual=$combustibleActual, kilometrosActuales=$kilometrosActuales, cilindrada=$cilindrada)"
    }
}
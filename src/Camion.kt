class Camion(
    nombre: String,
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Float,
    esHibrido: Boolean,
    private val peso:Int
):Automovil(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales, esHibrido) {
    companion object {
        const val KM_POR_LITRO = 6.25f // 16l/100km
    }
    init {
        require(peso in 1000..10000){"Peso min 1000 max 10000"}
    }

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
     * Calcula la reducción en los kilómetros por litro basándose en el peso del vehículo.
     *
     * @param peso El peso del vehículo en kilogramos.
     * @return La cantidad de reducción en los kilómetros por litro.
     */
    private fun calcularReduccionKmPorLitro(peso: Int): Float {
        val reduccionPorKg = 0.2
        val factorDeReduccion = peso / 1000 * reduccionPorKg
        return factorDeReduccion.toFloat()
    }
    override fun calcularAutonomia() =
        if (esHibrido)
            (combustibleActual * (KM_POR_LITRO + AHORRO_ELECTRICO - calcularReduccionKmPorLitro(peso))).redondear(2)
        else
            (combustibleActual * (KM_POR_LITRO - calcularReduccionKmPorLitro(peso))).redondear(2)
    override fun toString(): String {
        return "Camion(nombre=${nombre.capitalizarCadaPalabra()}, marca=$marca, modelo=$modelo, capacidadCombustible=$capacidadCombustible, combustibleActual=$combustibleActual, kilometrosActuales=$kilometrosActuales, esElectrico=$esHibrido, peso=$peso)"
    }
}
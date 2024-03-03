class Quad(
    nombre: String,
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Float,
    cilindrada: Int,
    val tipoCuadricilo:String = arrayOf("Cuadriciclos ligeros", "Cuadriciclos no ligeros", "Vehículos especiales").random()
):Motocicleta(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales, cilindrada) {
    override fun actualizaCombustible(distanciaReal: Float) {
        val combustibleGastado = (distanciaReal / ((KM_POR_LITRO - (1 - (cilindrada/1000)))/2))
        combustibleActual -= combustibleGastado.redondear(2)
    }

    override fun calcularAutonomia() = (combustibleActual * ((KM_POR_LITRO - (1 - (cilindrada/1000)))/2)).redondear(2)
    override fun toString(): String {
        return "Quad(nombre=${nombre.capitalizarCadaPalabra()}, marca=$marca, modelo=$modelo, capacidadCombustible=$capacidadCombustible, combustibleActual=$combustibleActual, kilometrosActuales=$kilometrosActuales, cilindrada=$cilindrada, tipoCuadricilo=$tipoCuadricilo)"
    }
}
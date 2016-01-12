package redutorPontos;

import dominio.PontoMarcado;

public class CalculadoraDistanciaGeografica {
	private final static Double RAIO_DA_TERRA = 6371.0;

	/**
	 * CALCULA A DISTANCIA EM Km DE "pontoAtual" ATE A O ARCO FORMADO PELO
	 * "pontoAnterior" E O "pontoSeguinte". PARA O CALCULO, UTILIZA-SE A FORMULA
	 * DE HAVERSINE.
	 * 
	 * @param pontoAtual
	 * @param pontoAnterior
	 * @param pontoSeguinte
	 * @return distancia
	 */
	public Double calculaDistanciaDeUmPontoAoArcoAdjacente(PontoMarcado pontoAtual, PontoMarcado pontoAnterior,
			PontoMarcado pontoSeguinte) {

		Double distanciaRequerida;
		// y0
		Double latitudeAnterior = pontoAnterior.getLatitude();
		// x0
		Double longitudeAnterior = pontoAnterior.getLongitude();

		// y1
		Double latitudeAtual = pontoAtual.getLatitude();
		// x1
		Double longitudeAtual = pontoAtual.getLongitude();

		// y2
		Double latitudeSeguinte = pontoSeguinte.getLatitude();
		// x2
		Double longitudeSeguinte = pontoSeguinte.getLongitude();

		Double distancia1 = formulaDeHaversine(latitudeAnterior, longitudeAnterior, latitudeSeguinte,
				longitudeSeguinte);
		Double distancia2 = formulaDeHaversine(latitudeAnterior, longitudeAnterior, latitudeAtual, longitudeAtual);
		Double distancia3 = formulaDeHaversine(latitudeSeguinte, longitudeSeguinte, latitudeAtual, longitudeAtual);

		// Calculo da area do triangulo pelo Teorema de Heron
		Double semiPerimetro = (distancia1 + distancia2 + distancia3) / 2;
		Double area = Math.sqrt(semiPerimetro * (semiPerimetro - distancia1) * (semiPerimetro - distancia2)
				* (semiPerimetro - distancia3));
		// distanciaRequerida é igual a altura do triangulo formado pelos
		// vertices pontoAnterior, pontoAtual e pontoSeguinte
		distanciaRequerida = (2 * area) / distancia1;

		return distanciaRequerida;
	}

	private static Double converteEmRadiano(Double valor) {
		return valor * Math.PI / 180;
	}

	/**
	 * 
	 * @param latitude1
	 * @param longitude1
	 * @param latitude2
	 * @param longitude2
	 * @return distancia entre duas coordenadas geográficas pela fórmula de
	 *         Haversine
	 */
	public static Double formulaDeHaversine(Double latitude1, Double longitude1, Double latitude2, Double longitude2) {

		Double diferencaLatitude = converteEmRadiano(latitude2 - latitude1);
		Double diferencaLongitude = converteEmRadiano(longitude2 - longitude1);

		Double h = Math.pow(Math.sin(diferencaLatitude / 2), 2) + Math.cos(converteEmRadiano(latitude1))
				* Math.cos(converteEmRadiano(latitude2)) * Math.pow(Math.sin(diferencaLongitude / 2), 2);
		Double distancia = 2 * RAIO_DA_TERRA * Math.asin(Math.sqrt(h));

		return distancia;
	}

}
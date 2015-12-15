package dominio;

public class CalculadoraDistanciaPontoReta {

	/**
	 * CALCULA A DISTANCIA DE um "ponto" ATE A RETA FORMADA PELO "pontoAnterior"
	 * E O "pontoSeguinte"
	 * 
	 * @param pontoAtual
	 * @param pontoAnterior
	 * @param pontoSeguinte
	 * @return distancia
	 */
	public Double calculaDistanciaDeUmPontoAReta(PontoMarcado pontoAtual, PontoMarcado pontoAnterior,
			PontoMarcado pontoSeguinte) {
		Double a, b, c, x3, y3;
		Double distancia;

		Double longitudePositivaPontoAnterior = calculaModuloLongitude(pontoAnterior);
		Double latitudePositivaPontoAnterior = calculaModuloLatitude(pontoAnterior);

		Double longitudePositivaPontoAtual = calculaModuloLongitude(pontoAtual);
		Double latitudePositivaPontoAtual = calculaModuloLatitude(pontoAtual);

		Double longitudePositivaPontoSeguinte = calculaModuloLongitude(pontoSeguinte);
		Double latitudePositivaPontoSeguinte = calculaModuloLatitude(pontoSeguinte);

		a = (latitudePositivaPontoAnterior - latitudePositivaPontoSeguinte)
				/ (longitudePositivaPontoAnterior - longitudePositivaPontoSeguinte);

		b = latitudePositivaPontoSeguinte - (a * longitudePositivaPontoSeguinte);
		c = (a * longitudePositivaPontoAtual) + (latitudePositivaPontoAtual);
		x3 = (c - b) / (2 * a);
		y3 = (a * x3) + b;
		distancia = Math
				.sqrt((Math.pow(longitudePositivaPontoAtual - x3, 2)) + Math.pow((latitudePositivaPontoAtual - y3), 2));
		return distancia;
	}

	//TODO Verificar!
	private Double calculaModuloLatitude(PontoMarcado pontoAnterior) {
		return (pontoAnterior.getLatitude() < 0 ? pontoAnterior.getLatitude() * (1) : pontoAnterior.getLatitude());
	}

	private Double calculaModuloLongitude(PontoMarcado pontoAnterior) {
		return (pontoAnterior.getLongitude() < 0 ? pontoAnterior.getLongitude() * (1) : pontoAnterior.getLongitude());
	}
}

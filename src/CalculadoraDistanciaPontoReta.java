
public class CalculadoraDistanciaPontoReta {

	/**
	 * CALCULA A DISTANCIA DE um "ponto" ATE A RETA FORMADA PELO "pontoAnterior"
	 * E O "pontoSeguinte"
	 * 
	 * @param ponto
	 * @param pontoAnterior
	 * @param pontoSeguinte
	 * @return distancia
	 */
	public Double calculaDistanciaDeUmPontoAReta(PontoMarcado ponto, PontoMarcado pontoAnterior,
			PontoMarcado pontoSeguinte) {
		Double a, b, c, distancia, x3, y3;
		a = (pontoAnterior.getLatitude() - pontoSeguinte.getLatitude())
				/ (pontoAnterior.getLongitude() - pontoSeguinte.getLongitude());
		b = pontoSeguinte.getLatitude() - (a * pontoSeguinte.getLongitude());
		c = (a * ponto.getLongitude()) + ponto.getLatitude();
		x3 = (c - b) / (2 * a);
		y3 = (a * x3) + b;
		distancia = Math.sqrt(Math.pow((ponto.getLongitude() - x3), 2) + Math.pow((ponto.getLatitude() - y3), 2));
		return distancia;
	}
}

import java.math.BigDecimal;

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
		BigDecimal a, b, c, x3, y3;
		Double distancia;

		a = (pontoAnterior.getLatitude().subtract(pontoSeguinte.getLatitude()))
				.divide((pontoAnterior.getLongitude().subtract(pontoSeguinte.getLongitude())));

		b = pontoSeguinte.getLatitude().subtract(a.multiply(pontoSeguinte.getLongitude()));
		c = (a.multiply(ponto.getLongitude())).add(ponto.getLatitude());
		x3 = (c.subtract(b)).divide((a.multiply(new BigDecimal(2))));
		y3 = (a.multiply(x3).add(b));
		distancia = Math.sqrt((ponto.getLongitude().subtract(x3).pow(2).add(ponto.getLatitude().subtract(y3).pow(2))).doubleValue());
		
		ponto.getLatitude().doubleValue();
		return distancia;
	}
}

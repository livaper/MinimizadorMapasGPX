import java.util.ArrayList;
import java.util.List;

/**
 * Classe de domínio para a trajetória realizada no arquivo GPX
 * 
 * @author liviapereira
 *
 */
public class Trajetoria {
	List<PontoMarcado> pontosMarcados;

	public Trajetoria() {
		super();
		pontosMarcados = new ArrayList<PontoMarcado>();
	}

	/**
	 * ADICIONA A DISTANCIA DE UM PONTO ATE A RETA FORMADA PELOS SEUS ADJACENTES
	 */
	public void adicionaDistanciaARetaAdjacenteDosPontos() {
		CalculadoraDistanciaPontoReta calculadora = new CalculadoraDistanciaPontoReta();

		for (PontoMarcado ponto : pontosMarcados) {
			boolean primeiroPontoTrajetoria = pontosMarcados.indexOf(ponto) == 0;
			boolean ultimoPontoTrajetoria = pontosMarcados.indexOf(ponto) == (pontosMarcados.size() - 1);

			if (!primeiroPontoTrajetoria && !ultimoPontoTrajetoria) {
				PontoMarcado pontoSeguinte = pontosMarcados.get(pontosMarcados.indexOf(ponto) + 1);
				PontoMarcado pontoAnterior = pontosMarcados.get(pontosMarcados.indexOf(ponto) - 1);
				Double distancia = calculadora.calculaDistanciaDeUmPontoAReta(ponto, pontoAnterior, pontoSeguinte);
				ponto.setDistanciaARetaAdjacente(distancia);
			}
		}
	}

	public List<PontoMarcado> getPontosMarcados() {
		return pontosMarcados;
	}

	public void setPontosMarcados(List<PontoMarcado> trajetoria) {
		this.pontosMarcados = trajetoria;
	}

}

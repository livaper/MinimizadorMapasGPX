import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe que aplica a lógica de redução de pontos numa trajetória
 * 
 * @author liviapereira
 *
 */
public class RedutorDePontos {

	/**
	 * APLICA A REDUCAO DE PONTOS EM UMA TRAJETORIA CONSIDERANDO RETIRANDO X%
	 * DOS PONTOS MAIS PROXIMOS DE UMA RETA FORMADA PELOS PONTOS ADJACENTES
	 * 
	 * @param trajetoriaOriginal
	 *            - trajetoria lida pelo Leitor GPX com a
	 *            "distanciaARetaAdjacente" de todos os pontos da trajetória
	 *            (excetuando o primeiro e último) preenchidos.
	 * @param porcentagem
	 *            - valor entre 0 e 100, inclusive.
	 */
	public Trajetoria reduzTrajetoriaPorPorcentagem(Trajetoria trajetoriaOriginal, Double porcentagem) {
		int quantidadeDePontosARetirar = (int) (trajetoriaOriginal.getPontosMarcados().size() * (porcentagem / 100));

		List<PontoMarcado> trajetoriaMinimizada = new ArrayList<PontoMarcado>(trajetoriaOriginal.getPontosMarcados());
		Collections.sort(trajetoriaMinimizada, PontoMarcado.getComparatorDistanciaARetaCrescente());
		for (int i = 0; i < quantidadeDePontosARetirar; i++) {
			trajetoriaMinimizada.remove(i);
		}
		Collections.sort(trajetoriaMinimizada, PontoMarcado.getComparatorDataCrescente());
		return new Trajetoria(trajetoriaMinimizada);
	}

	/**
	 * APLICA A REDUCAO DE PONTOS EM UMA TRAJETORIA RETIRANDO TODOS OS PONTOS
	 * CUJA DISTANCIA A RETA FORMADA PELOS PONTOS ADJACENTES SEJA MENOR OU IGUAL
	 * A "distancia" PARAMETRO
	 * 
	 * @param trajetoriaOriginal
	 *            - trajetoria lida pelo Leitor GPX com a
	 *            "distanciaARetaAdjacente" de todos os pontos da trajetória
	 *            (excetuando o primeiro e último) preenchidos.
	 * @param distanciaLimite
	 *            - valor em metros.
	 * @return
	 */
	public Trajetoria reduzTrajetoriaPorDistancia(Trajetoria trajetoriaOriginal, Double distanciaLimite) {
		Double distanciLimiteEmKM = distanciaLimite / 1000;
		List<PontoMarcado> trajetoriaMinimizada = new ArrayList<PontoMarcado>(trajetoriaOriginal.getPontosMarcados());
		for (PontoMarcado ponto : trajetoriaMinimizada) {
			if (ponto.getDistanciaARetaAdjacente() <= distanciLimiteEmKM) {
				trajetoriaMinimizada.remove(ponto);
			}
		}
		Collections.sort(trajetoriaMinimizada, PontoMarcado.getComparatorDataCrescente());
		return new Trajetoria(trajetoriaMinimizada);

	}
}

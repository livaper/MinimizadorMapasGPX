package dominio;

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
	 * /** APLICA A REDUCAO DE PONTOS EM UMA TRAJETORIA CONSIDERANDO RETIRANDO
	 * X% DOS PONTOS MAIS PROXIMOS DE UMA RETA FORMADA PELOS PONTOS ADJACENTES
	 * 
	 * @param trajetoriaOriginal
	 *            - trajetoria lida pelo Leitor GPX com a
	 *            "distanciaARetaAdjacente" de todos os pontos da trajetória
	 *            (excetuando o primeiro e último) preenchidos.
	 * @param porcentagem
	 *            - valor entre 0 e 100, inclusive.
	 *
	 * @return Trajetoria reduzida
	 */
	public Trajetoria reduzTrajetoriaPorPorcentagem(Trajetoria trajetoriaOriginal, Double porcentagem) {
		int quantidadeDePontosARetirar = (int) (trajetoriaOriginal.getPontosMarcados().size() * (porcentagem / 100));

		List<PontoMarcado> pontosReduzidos = new ArrayList<PontoMarcado>(trajetoriaOriginal.getPontosMarcados());

		// RETIRA OS PONTOS INICIAL E FINAL (e salva em pontoOrigem e
		// pontoFinal) PARA ORDENACAO PELA DISTANCIA A RETA ADJACENTE
		PontoMarcado pontoOrigem = pontosReduzidos.get(0);
		PontoMarcado pontoFinal = pontosReduzidos.get(pontosReduzidos.size() - 1);
		pontosReduzidos.remove(pontosReduzidos.size() - 1);
		pontosReduzidos.remove(0);

		// ORDENA CRESCENTEMENTE PELA DISTANCIA A RETA DOS PONTOS ADJACENTE PARA
		// RETIRAR OS X% PRIMEIROS
		Collections.sort(pontosReduzidos, PontoMarcado.getComparatorDistanciaARetaCrescente());

		for (int i = 0; i < quantidadeDePontosARetirar; i++) {
			pontosReduzidos.remove(i);
		}

		// COLOCA OS PONTOS ORIGEM E DESTINO RETIRADOS DE VOLTA
		// ORDENA PELA DATA PARA COLOCAR A TRAJETORIA EM ORDEM DE VOLTA
		pontosReduzidos.add(0, pontoOrigem);
		pontosReduzidos.add(pontoFinal);
		Collections.sort(pontosReduzidos, PontoMarcado.getComparatorDataCrescente());
		return new Trajetoria(pontosReduzidos);
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
		List<PontoMarcado> pontosReduzidos = new ArrayList<PontoMarcado>(trajetoriaOriginal.getPontosMarcados());

		// RETIRA OS PONTOS INICIAL E FINAL (e salva em pontoOrigem e
		// pontoFinal) PARA REMOCAO DE PONTOS
		PontoMarcado pontoOrigem = trajetoriaOriginal.getPontosMarcados().get(0);
		PontoMarcado pontoFinal = trajetoriaOriginal.getPontosMarcados()
				.get(trajetoriaOriginal.getPontosMarcados().size() - 1);
		trajetoriaOriginal.getPontosMarcados().remove(0);
		trajetoriaOriginal.getPontosMarcados().remove(trajetoriaOriginal.getPontosMarcados().size() - 1);
		pontosReduzidos.remove(0);
		pontosReduzidos.remove(pontosReduzidos.size() - 1);

		for (PontoMarcado ponto : trajetoriaOriginal.getPontosMarcados()) {

			if (ponto.getDistanciaARetaAdjacente() <= distanciaLimite) {
				pontosReduzidos.remove(ponto);
			}
		}
		// COLOCA OS PONTOS ORIGEM E DESTINO RETIRADOS DE VOLTA
		pontosReduzidos.add(0, pontoOrigem);
		pontosReduzidos.add(pontoFinal);
		trajetoriaOriginal.getPontosMarcados().add(0, pontoOrigem);
		trajetoriaOriginal.getPontosMarcados().add(pontoFinal);
		
		//RETOMA A ORDEM ORIGINAL DOS PONTOS
		Collections.sort(pontosReduzidos, PontoMarcado.getComparatorDataCrescente());

		return new Trajetoria(pontosReduzidos);

	}
}

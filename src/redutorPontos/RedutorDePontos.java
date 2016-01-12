package redutorPontos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dominio.PontoMarcado;
import dominio.Trajetoria;

/**
 * Classe que aplica a lógica de redução de pontos numa trajetória
 * 
 * @author liviapereira
 *
 */
public class RedutorDePontos {

	/**
	 * /** APLICA A REDUCAO DE PONTOS EM UMA TRAJETORIA RETIRANDO 100% MENOS (-)
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
		int quantidadeDePontosARetirar = (int) (trajetoriaOriginal.getPontosMarcados().size()
				* ((Double.parseDouble("100") - porcentagem) / 100));

		List<PontoMarcado> pontosTrajetoriaOriginal = new ArrayList<PontoMarcado>(
				trajetoriaOriginal.getPontosMarcados());

		// LISTA DE PONTOS AUXILIAR QUE SERA ORDENADA CONTINUAMENTE APOS A
		// REMOCAO DE UM PONTO. NAO CONTERA OS PONTOS INICIAIS E FINAIS
		List<PontoMarcado> pontosTrajetoriaOrdenadosDistancia = new ArrayList<PontoMarcado>(
				trajetoriaOriginal.getPontosMarcados());
		pontosTrajetoriaOrdenadosDistancia.remove(0);
		pontosTrajetoriaOrdenadosDistancia.remove(pontosTrajetoriaOrdenadosDistancia.size() - 1);
		Collections.sort(pontosTrajetoriaOrdenadosDistancia, PontoMarcado.getComparatorDistanciaARetaCrescente());

		PontoMarcado pontoOrigem = pontosTrajetoriaOriginal.get(0);
		PontoMarcado pontoFinal = pontosTrajetoriaOriginal.get(pontosTrajetoriaOriginal.size() - 1);

		for (int i = 0; i < quantidadeDePontosARetirar; i++) {
			int posicaoElementoRemocao = pontosTrajetoriaOriginal.indexOf(pontosTrajetoriaOrdenadosDistancia.get(0));
			int posicaoElementoSeguinteRemocao = posicaoElementoRemocao;
			int posicaoElementoAnterior = posicaoElementoRemocao - 1;

			// REMOVE O PONTO DA TRAJETORIA ORIGINAL E DA TRAJETORIA AUXILIAR
			pontosTrajetoriaOriginal.remove(posicaoElementoRemocao);
			pontosTrajetoriaOrdenadosDistancia.remove(0);

			// RECALCULA A DISTANCIA AO ARCO FORMADO PELOS PONTOS ADJACENTES
			CalculadoraDistanciaGeografica calculadora = new CalculadoraDistanciaGeografica();
			atualizaDistanciaGeograficaDoPontoSeguinteAoRemovido(pontosTrajetoriaOriginal, pontoFinal,
					posicaoElementoSeguinteRemocao, posicaoElementoAnterior, calculadora);
			atualizaDistanciaGeograficaDoPontoAnteriorAoRemovido(pontosTrajetoriaOriginal, pontoOrigem,
					posicaoElementoSeguinteRemocao, posicaoElementoAnterior, calculadora);

			Collections.sort(pontosTrajetoriaOrdenadosDistancia, PontoMarcado.getComparatorDistanciaARetaCrescente());
		}

		return new Trajetoria(pontosTrajetoriaOriginal);

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
		List<PontoMarcado> pontosTrajetoriaOriginal = new ArrayList<PontoMarcado>(
				trajetoriaOriginal.getPontosMarcados());

		// LISTA DE PONTOS AUXILIAR QUE SERA ORDENADA CONTINUAMENTE APOS A
		// REMOCAO DE UM PONTO. NAO CONTERA OS PONTOS INICIAIS E FINAIS
		List<PontoMarcado> pontosTrajetoriaOrdenadosDistancia = new ArrayList<PontoMarcado>(
				trajetoriaOriginal.getPontosMarcados());

		PontoMarcado pontoOrigem = pontosTrajetoriaOriginal.get(0);
		PontoMarcado pontoFinal = pontosTrajetoriaOriginal.get(pontosTrajetoriaOriginal.size() - 1);

		// ORDENA pontosOrdenadosPorDistancia em ordem crescente pela distancia
		// ao arco formado pelos pontos adjacentes
		pontosTrajetoriaOrdenadosDistancia.remove(0);
		pontosTrajetoriaOrdenadosDistancia.remove(pontosTrajetoriaOrdenadosDistancia.size() - 1);
		Collections.sort(pontosTrajetoriaOrdenadosDistancia, PontoMarcado.getComparatorDistanciaARetaCrescente());

		// PERCORRE TODOS OS PONTOS (EXCETO PRIMEIRO E ULTIMO), ORDENADOS
		// CRESCENTEMENTE, E VERIFICA SE DISTANCIA AO ARCO ADJACENTE É MENOR QUE
		// A "distanciaLimite"
		while (pontosTrajetoriaOrdenadosDistancia.get(0).getDistanciaGeograficaAoArcoFormadoPelosPontosAdjacentes()
				.compareTo(distanciaLimite) < 0) {

			int posicaoElementoRemocao = pontosTrajetoriaOriginal.indexOf(pontosTrajetoriaOrdenadosDistancia.get(0));
			int posicaoElementoSeguinteRemocao = posicaoElementoRemocao;
			int posicaoElementoAnterior = posicaoElementoRemocao - 1;

			// REMOVE O PONTO DA TRAJETORIA ORIGINAL E DA TRAJETORIA AUXILIAR
			pontosTrajetoriaOriginal.remove(posicaoElementoRemocao);
			pontosTrajetoriaOrdenadosDistancia.remove(0);

			// RECALCULA A DISTANCIA AO ARCO FORMADO PELOS PONTOS ADJACENTES
			CalculadoraDistanciaGeografica calculadora = new CalculadoraDistanciaGeografica();
			atualizaDistanciaGeograficaDoPontoSeguinteAoRemovido(pontosTrajetoriaOriginal, pontoFinal,
					posicaoElementoSeguinteRemocao, posicaoElementoAnterior, calculadora);
			atualizaDistanciaGeograficaDoPontoAnteriorAoRemovido(pontosTrajetoriaOriginal, pontoOrigem,
					posicaoElementoSeguinteRemocao, posicaoElementoAnterior, calculadora);

			Collections.sort(pontosTrajetoriaOrdenadosDistancia, PontoMarcado.getComparatorDistanciaARetaCrescente());
		}

		return new Trajetoria(pontosTrajetoriaOriginal);

	}

	/**
	 * ATUALIZA A NOVA DISTANCIA A RETA ADJACENTE DO PONTO SEGUINTE AO REMOVIDO
	 * SE ESTE PONTO SEGUINTE NAO FOR O ULTIMO PONTO DA TRAJETORIA
	 * 
	 * @param pontosTrajetoriaOriginal
	 * @param pontoFinal
	 * @param posicaoElementoSeguinteRemocao
	 * @param posicaoElementoAnterior
	 * @param calculadora
	 */
	private void atualizaDistanciaGeograficaDoPontoSeguinteAoRemovido(List<PontoMarcado> pontosTrajetoriaOriginal,
			PontoMarcado pontoFinal, int posicaoElementoSeguinteRemocao, int posicaoElementoAnterior,
			CalculadoraDistanciaGeografica calculadora) {

		boolean pontoNaoUltimo = !(pontoFinal.getIdentificador()
				.equals(pontosTrajetoriaOriginal.get(posicaoElementoSeguinteRemocao).getIdentificador()));
		if (pontoNaoUltimo) {

			Double novaDistanciaPontoSucessorRemocao = calculadora.calculaDistanciaDeUmPontoAoArcoAdjacente(
					pontosTrajetoriaOriginal.get(posicaoElementoSeguinteRemocao),
					pontosTrajetoriaOriginal.get(posicaoElementoAnterior),
					pontosTrajetoriaOriginal.get(posicaoElementoSeguinteRemocao + 1));
			pontosTrajetoriaOriginal.get(posicaoElementoSeguinteRemocao)
					.setDistanciaGeograficaAoArcoFormadoPelosPontosAdjacentes(novaDistanciaPontoSucessorRemocao);
		}
	}

	/**
	 * ATUALIZA A NOVA DISTANCIA A RETA ADJACENTE DO PONTO ANTERIOR AO REMOVIDO
	 * SE ESTE PONTO ANTERIOR NAO FOR O PRIMEIRO PONTO DA TRAJETORIA
	 * 
	 * @param pontosTrajetoriaOriginal
	 * @param pontoOrigem
	 * @param posicaoElementoSeguinteRemocao
	 * @param posicaoElementoAnterior
	 * @param calculadora
	 */
	private void atualizaDistanciaGeograficaDoPontoAnteriorAoRemovido(List<PontoMarcado> pontosTrajetoriaOriginal,
			PontoMarcado pontoOrigem, int posicaoElementoSeguinteRemocao, int posicaoElementoAnterior,
			CalculadoraDistanciaGeografica calculadora) {

		boolean pontoNaoOrigem = !(pontoOrigem.getIdentificador()
				.equals(pontosTrajetoriaOriginal.get(posicaoElementoAnterior).getIdentificador()));
		if (pontoNaoOrigem) {
			Double novaDistanciaPontoAnteriorRemocao = calculadora.calculaDistanciaDeUmPontoAoArcoAdjacente(
					pontosTrajetoriaOriginal.get(posicaoElementoAnterior),
					pontosTrajetoriaOriginal.get(posicaoElementoAnterior - 1),
					pontosTrajetoriaOriginal.get(posicaoElementoSeguinteRemocao));
			pontosTrajetoriaOriginal.get(posicaoElementoAnterior)
					.setDistanciaGeograficaAoArcoFormadoPelosPontosAdjacentes(novaDistanciaPontoAnteriorRemocao);

		}
	}
}

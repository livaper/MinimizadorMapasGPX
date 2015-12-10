import java.util.ArrayList;
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
	public void reduzTrajetoriaPorPorcentagem(Trajetoria trajetoriaOriginal, Double porcentagem) {
		int quantidadeDePontosARetirar = (int) (trajetoriaOriginal.getPontosMarcados().size() * (porcentagem / 100));
		
		List <PontoMarcado>pontosTrajetoria = new ArrayList<PontoMarcado>(trajetoriaOriginal.getPontosMarcados());
		

	}

	/**
	 * APLICA A REDUCAO DE PONTOS EM UMA TRAJETORIA RETIRANDO TODOS
	 * OS PONTOS CUJA DISTANCIA A RETA FORMADA PELOS PONTOS ADJACENTES SEJA MENOR OU IGUAL A "distancia" PARAMETRO
	 * 
	 * @param trajetoriaOriginal
	 *            - trajetoria lida pelo Leitor GPX com a
	 *            "distanciaARetaAdjacente" de todos os pontos da trajetória
	 *            (excetuando o primeiro e último) preenchidos.
	 * @param porcentagem
	 *            - valor em metros.
	 */
	public void reduzTrajetoriaPorDistancia(Trajetoria trajetoriaOriginal, Double distancia) {

	}
}

package leituraXML;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import dominio.PontoMarcado;
import dominio.Trajetoria;
import mapeamentoXMLObjeto.*;

/**
 * Classe para a leitura dos dados no arquivo GPX contendo os dados da
 * trajetória
 * 
 * @author liviapereira
 *
 */
public class LeitorGPX {

	public Trajetoria lerXML(String caminhoRelativo) {

		adequaTagGPXParaLeitura(caminhoRelativo);

		GpxType gpx = null;
		Trajetoria trajetoriaPreenchida = new Trajetoria();
		try {

			JAXBContext jc = JAXBContext.newInstance("mapeamentoXMLObjeto");
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<GpxType> root = (JAXBElement<GpxType>) unmarshaller.unmarshal(new File(caminhoRelativo));
			gpx = root.getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		List<TrkType> conjuntoTrajetoriasGPX = gpx.getTrk();

		trajetoriaPreenchida = extrairDadosPontoXML(conjuntoTrajetoriasGPX);
		return trajetoriaPreenchida;
	}

	/**
	 * TAG DO XML(TRK, TRKSEG, TRKPT)PERCORRE A LISTA DE TRK, PARA CADA TRK,
	 * PERCORRE CADA TRKSEG, PARA CADA TRKSEG, PERCORRE CADA TRKPT PARA CADA
	 * TRKPT, EXTRAI OS DADOS DOS PONTOS E ADICIONA CADA PONTO EM UMA TRAJETORIA
	 * 
	 * @param conjuntoTrajetoriasGPX
	 * @return trajetoriaPreenchida
	 */
	private static Trajetoria extrairDadosPontoXML(List<TrkType> conjuntoTrajetoriasGPX) {
		Trajetoria trajetoriaPreenchida = new Trajetoria();
		List<PontoMarcado> pontosMarcados = new ArrayList<PontoMarcado>();

		for (TrkType trajetoriasGPX : conjuntoTrajetoriasGPX) {
			trajetoriaPreenchida.setNome(trajetoriasGPX.getName());
			System.out.println(trajetoriasGPX.getName());
			for (TrksegType trajetoriaGPX : trajetoriasGPX.getTrkseg()) {
				for (WptType pontoGPX : trajetoriaGPX.getTrkpt()) {
					Date dataPonto = conversorData(pontoGPX);
					PontoMarcado ponto = new PontoMarcado(pontoGPX.getEle().doubleValue(),
							pontoGPX.getLon().doubleValue(), pontoGPX.getLat().doubleValue(), dataPonto);
					pontosMarcados.add(ponto);
				}
			}
		}

		trajetoriaPreenchida.setPontosMarcados(pontosMarcados);
		return trajetoriaPreenchida;
	}

	/**
	 * 
	 * @param pontoGPX
	 * @return Data XMLGregorianCalendar convertida em Date
	 */
	private static Date conversorData(WptType pontoGPX) {
		Date dataPonto = null;
		try {
			dataPonto = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'").parse(pontoGPX.getTime().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataPonto;
	}

	/**
	 * ADEQUA TAG <gpx> PARA REALIZAR A LEITURA CORRETAMENTE ATRAVES DO
	 * MAPEAMENTO GPX-OBJETO
	 * 
	 * @param caminhoRelativo
	 */
	private void adequaTagGPXParaLeitura(String caminhoRelativo) {
		String arquivoTemporario = "ARQUIVO-TMP";
		try {
			BufferedReader leitor = new BufferedReader(new FileReader(caminhoRelativo));
			BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoTemporario));

			String linha = " ";

			while ((linha = leitor.readLine()) != null) {
				if (linha.contains("<gpx>")) {
					linha = linha.replace("<gpx>", "<gpx xmlns=\"http://www.topografix.com/GPX/1/1\">");
				}
				escritor.write(linha + "\n");
			}
			escritor.close();
			leitor.close();
			new File(caminhoRelativo).delete();
			new File(arquivoTemporario).renameTo(new File(caminhoRelativo));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}

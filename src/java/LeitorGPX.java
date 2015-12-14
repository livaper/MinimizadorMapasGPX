package java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import com.topografix.gpx.*;

/**
 * Classe para a leitura dos dados no arquivo GPX contendo os dados da
 * trajetória
 * 
 * @author liviapereira
 *
 */
public class LeitorGPX {

	public  Trajetoria lerXML(String caminhoRelativo) {

		GpxType gpx = null;
		Trajetoria trajetoriaPreenchida = new Trajetoria();
		try {

			JAXBContext jc = JAXBContext.newInstance("com.topografix.gpx");
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<GpxType> root = (JAXBElement<GpxType>) unmarshaller
					.unmarshal(new File("Century-2007-02-18.gpx"));
			gpx = root.getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		List<TrkType> conjuntoTrajetoriasGPX = gpx.getTrk();

		trajetoriaPreenchida = extrairDadosPontoXML(conjuntoTrajetoriasGPX);
		return trajetoriaPreenchida;
	}

	/**
	 * TAGG DO XML(TRK, TRKSEG, TRKPT)PERCORRE A LISTA DE TRK, PARA CADA TRK,
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
					System.out.println("pt  lat = " + pontoGPX.getLat());
					System.out.println("lon = " + pontoGPX.getLon());
					System.out.println("ele =" + pontoGPX.getEle());
					System.out.println("time = " + pontoGPX.getTime());

					Date dataPonto = conversorData(pontoGPX);
					PontoMarcado ponto = new PontoMarcado(pontoGPX.getEle().doubleValue(), pontoGPX.getLon().doubleValue(), pontoGPX.getLat().doubleValue(),
							dataPonto);
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

}


import java.io.File;
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

	public static Trajetoria lerXML(String caminhoRelativo) {

		GpxType gpx = null;
		try {

			JAXBContext jc = JAXBContext.newInstance("com.topografix.gpx");
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			JAXBElement<GpxType> root = (JAXBElement<GpxType>) unmarshaller
					.unmarshal(new File("Century-2007-02-18.gpx"));
			gpx = root.getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		List<TrkType> conjuntoTrajetorias = gpx.getTrk();

		for (TrkType trajetorias : conjuntoTrajetorias) {
			System.out.println(trajetorias.getName());
			for (TrksegType trajetoria : trajetorias.getTrkseg()){
				for( WptType ponto : trajetoria.getTrkpt()){
					System.out.println("ponto  lat = " + ponto.getLat());
					System.out.println("lon = " + ponto.getLon());
					System.out.println("ele =" + ponto.getEle());
					System.out.println("time = " + ponto.getTime());
				}
				
			}

		}
		return null;
	}

}

package dominio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EscritorGPX {

	public static void escreverGPX(Trajetoria trajetoriaMinimizada, String nomeArquivo) {

		String header = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n"
				+ "   <gpx creator=\"LoadMyTracks/045 \"http://www.cluetrust.com/LoadMyTracks.html\" \n"
				+ "   version=\"1.1\" xmlns=\"http://www.topografix.com/GPX/1/1\" \n"
				+ "   xmlns:geocache=\"http://www.groundspeak.com/cache/1/0\" \n"
				+ "   xmlns:gpxdata=\"http://www.cluetrust.com/XML/GPXDATA/1/0\" \n"
				+ "   xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ "   xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd"
				+ "	 http://www.cluetrust.com/XML/GPXDATA/1/0 http://www.cluetrust.com/Schemas/gpxdata10.xsd\">\n"
				+ "      <trk>\n";
		String name = "          <name>" + trajetoriaMinimizada.getNome() + "</name>\n                <trkseg>\n";

		String segments = "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

		for (PontoMarcado ponto : trajetoriaMinimizada.getPontosMarcados()) {
			segments += "                     <trkpt lat=\"" + ponto.getLatitude() + "\" lon=\"" + ponto.getLongitude()
			+ "\"><time>" + "<ele>" + ponto.getIdentificador() + "</ele>" + df.format(ponto.getData())
			+ "</time></trkpt>\n";
		}

		String footer = "</trkseg></trk></gpx>";

		File arquivo = new File(nomeArquivo);
		FileWriter escritor;
		try {
			escritor = new FileWriter(arquivo, false);
			escritor.append(header);
			escritor.append(name);
			escritor.append(segments);
			escritor.append(footer);
			escritor.flush();
			escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

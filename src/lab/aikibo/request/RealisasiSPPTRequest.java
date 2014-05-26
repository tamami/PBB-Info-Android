package lab.aikibo.request;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import lab.aikibo.util.ConnectionInfo;
import lab.aikibo.util.Formator;

public class RealisasiSPPTRequest {
	
	public String getRealisasiToday() throws JSONException, ResourceException, IOException, ParseException {
		String valueFromServer =
				new ClientResource(ConnectionInfo.URL_KANTOR_PBBINFO + 
						"/realisasiSPPT?mode=today").get().getText();
		JsonRepresentation represent = new JsonRepresentation(valueFromServer);
		JSONObject jsonObject = represent.getJsonObject();
		JSONParser parser = new JSONParser();
		Map jsonMap = (Map) parser.parse(jsonObject.toString());
		return ((String) jsonMap.get("result"));
	}
	
	public String getRealisasiPeriodic(String thnPajak, String tglAkhir) throws Exception {
		String tglAwal = Formator.getTglAwal(tglAkhir);
		String valueFromServer = 
				new ClientResource(ConnectionInfo.URL_KANTOR_PBBINFO +
						"/realisasiSPPT?mode=periodic&tahun=" + thnPajak +
						"&tglAwal=" + tglAwal + 
						"&tglAkhir=" + tglAkhir).get().getText();
		
		JsonRepresentation represent = new JsonRepresentation(valueFromServer);
		JSONObject jsonObject = represent.getJsonObject();
		JSONParser parser = new JSONParser();
		Map jsonMap = (Map) parser.parse(jsonObject.toString());
		return ((String) jsonMap.get("result"));
	}
	
	public String getRealisasiTotalPerTanggal(String tgl) throws ResourceException, IOException, JSONException, ParseException {
		String valueFromServer = 
				new ClientResource(ConnectionInfo.URL_KANTOR_PBBINFO + 
						"/realisasiSPPT?mode=date" +
						"&tanggal=" + tgl).get().getText();
		JsonRepresentation represent = new JsonRepresentation(valueFromServer);
		JSONObject jsonObject = represent.getJsonObject();
		JSONParser parser = new JSONParser();
		Map jsonMap = (Map) parser.parse(jsonObject.toString());
		return ((String) jsonMap.get("result"));
	}

}

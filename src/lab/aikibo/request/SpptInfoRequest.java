package lab.aikibo.request;

import java.io.IOException;
import java.util.Map;

import lab.aikibo.util.ConnectionInfo;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class SpptInfoRequest {
	
	public String getNamaWp(String nop, String thnPajak) throws ResourceException, IOException, JSONException, ParseException {
		String valueFromServer = 
				new ClientResource(ConnectionInfo.URL_GET_SPPT +
						"/spptInfo?mode=namaWP&nop=" + nop + 
						"&thn=" + thnPajak).get().getText();
		JsonRepresentation represent = new JsonRepresentation(valueFromServer);
		JSONObject jsonObject = represent.getJsonObject();
		JSONParser parser = new JSONParser();
		Map jsonMap = (Map) parser.parse(jsonObject.toString());
		return (String) jsonMap.get("result");
	}
	
	public String getStatusLunas(String nop, String thnPajak) throws ResourceException, IOException, JSONException, ParseException {
		String valueFromServer = 
				new ClientResource(ConnectionInfo.URL_GET_SPPT +
						"/spptInfo?mode=statusPembayaran" + 
						"&nop=" + nop +
						"&thn=" + thnPajak).get().getText();
		JsonRepresentation represent = new JsonRepresentation(valueFromServer);
		JSONObject jsonObject = represent.getJsonObject();
		JSONParser parser = new JSONParser();
		Map jsonMap = (Map) parser.parse(jsonObject.toString());
		return (String) jsonMap.get("result");
	}

}

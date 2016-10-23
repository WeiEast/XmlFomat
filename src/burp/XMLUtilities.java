package burp;

import com.burp.XMLConverter;

public class XMLUtilities {

	


	public static byte[] deserializeProxyItem(byte[] message) {
		return message;
		
	}

	public static byte[] serializeProxyItem(byte[] message) {
		
		try {
			return XMLConverter.convertRequestToXml(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;


	}

}

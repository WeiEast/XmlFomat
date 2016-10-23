package com.burp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import flex.messaging.util.URLDecoder;

public class XMLConverter {

	private static XStream xstream;

	private static String DOUBLELINEBREAK = "\\r\\n\\r\\n";
	private static String LINESEPARATOR = System.getProperty("line.separator");

	public static byte[] getBody(byte[] message) throws IOException {

		String testStr = new String(message);
		String[] strHeadersAndContent = testStr.split(DOUBLELINEBREAK);
		byte[] reqBody = Arrays.copyOfRange(message,
				strHeadersAndContent[0].getBytes().length + 4, message.length);
		return reqBody;
	}

	public static String getHeader(byte[] message) throws IOException {

		String testStr = new String(message);
		String[] strHeadersAndContent = testStr.split(DOUBLELINEBREAK);
		return strHeadersAndContent[0];
	}

	/**
	 * Converts XML to an object then serializes it
	 */
	public static byte[] convertXmlToRequst(byte[] message) {
		return null;

	}

	/**
	 * Converts XML to a complete request message
	 * @throws Exception 
	 */
	public static byte[] convertRequestToXml(byte[] message) throws Exception {
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		RequestBean rb = new RequestBean();
		List<Field> header_list = new ArrayList<Field>();
		List<Field> body_list = new ArrayList<Field>();
		Field field = null;

		String header = getHeader(message);
		
		String body = new String(getBody(message));
		String[] headers = header.split(LINESEPARATOR);
		String[] bodys = body.split("\\&");

		String[] path_method = headers[0].split(" ");
		String path = "http://";

		String[] tmp_header = null;
		for (int i = 1; i < headers.length; i++) {
			tmp_header = headers[i].split("\\:", 2);
			if (tmp_header.length > 1) {
				if (tmp_header[0].equals("Host")) {
					path = path + tmp_header[1].replaceAll("\\r", "") + path_method[1];
				}
				if (tmp_header[0].equals("Cookie")) {
					continue;
				}
				field = new Field(tmp_header[0], tmp_header[1].replaceAll("\\r", ""));
				header_list.add(field);
			}

		}
		String[] body_header = null;
		for (int i = 0; i < bodys.length; i++) {
			body_header = bodys[i].split("\\=", 2);
			if (body_header.length > 1) {

				field = new Field(body_header[0], body_header[1].replaceAll("\\r", ""));

			} else {
				field = new Field(body_header[0], "");
			}
			body_list.add(field);

		}

		rb.setId("");
		rb.setUrl(path);
		rb.setMethod(path_method[0]);
		rb.setDescription("");
		rb.setHeader(header_list);
		rb.setBody(body_list);

		String xml = xstream.toXML(rb);

		return xml.getBytes();

	}
}

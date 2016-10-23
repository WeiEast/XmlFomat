package cn.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.burp.Field;
import com.burp.RequestBean;
import com.thoughtworks.xstream.XStream;

public class XML {
	
	private static String DOUBLELINEBREAK = "\\n\\n";
	private static String LINESEPARATOR = System.getProperty("line.separator");


	public static byte[] getBody(byte[] message) {

		String testStr = new String(message);
		String[] strHeadersAndContent = testStr.split(DOUBLELINEBREAK);
		byte[] reqBody = Arrays.copyOfRange(message, strHeadersAndContent[0].getBytes().length + 4, message.length);
		return reqBody;
	}
	public static String getHeader(byte[] message) {

		String testStr = new String(message);
		String[] strHeadersAndContent = testStr.split("\n");
		return strHeadersAndContent[0];
	}
	
	public static byte[] readToString(String fileName) {  
        File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
            return filecontent;  
    }  
	
	public static void main(String[] args) {
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true); 
		RequestBean rb = new RequestBean();
		List<Field> header_list = new ArrayList<Field>();
		List<Field> body_list = new ArrayList<Field>();
		Field field = null;
		
		
		 byte[] message = readToString("/Users/wdf/Documents/okhttp/AMFDSER/src/cn/test/aa");
		 String header = getHeader(message);
		 String body = new String(getBody(message));
		 String[] headers = header.split(LINESEPARATOR);
		 String[] bodys = body.split("\\&");
		 
		 
		 String[] path_method = headers[0].split(" ");
		 String path = "http://";
		 
		 String[] tmp_header = null;
		 for(int i = 1; i < headers.length; i++){
			 tmp_header = headers[i].split("\\:", 2);
			 if(tmp_header.length > 1){
				 if (tmp_header[0].equals("Host")){
					 path = path + tmp_header[1] + path_method[1];
				 }
				 if(tmp_header[0].equals("Cookie")){
					 continue;
				 }
				 field = new Field(tmp_header[0], tmp_header[1]);
				 header_list.add(field);
			 }
			 
		 }
		 String[] body_header = null;
		 for(int i = 0; i < bodys.length; i++){
			 body_header = bodys[i].split("\\=", 2);
			 if(body_header.length > 1){
				 
				 field = new Field(body_header[0], body_header[1]);
				
			 }else{
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
		System.out.println(xml);
		
	}

}

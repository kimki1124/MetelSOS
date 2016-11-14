package com.metelsos.common.sms;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import java.util.Random;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/*
 * Https.class
 * Https request, response瑜� 愿�由ы븯�뒗 class �엯�땲�떎.
 */
public class Https
{
	/*
	 * postRequest (POST)
	 * @param StringBuffer : data 
	 * @param String : image
	 */
	public JSONObject postRequest(String url_string, HashMap<String, String> params) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("status", false);

			String salt = salt();
			String timestamp = getTimestamp();
			String signature = getSignature(params.get("api_secret"), salt, timestamp); 
			String boundary = salt + timestamp;
			String delimiter = "\r\n--" + boundary + "\r\n";

			params.put("salt", salt);
			params.put("signature", signature);
			params.put("timestamp", timestamp);

			// data �깮�꽦 諛� �뜲�씠�꽣 援щ텇�쓣 �쐞�븳 delimiter �꽕�젙
			StringBuffer postDataBuilder = new StringBuffer();
			postDataBuilder.append(delimiter);

			// params�뿉 image媛� �엳�쑝硫� 蹂��닔�뿉 �떞�븘 request瑜� �떎瑜닿쾶 蹂대궦�떎
			String image = null;
			String image_path = null;
			for (Entry<String, String> entry : params.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				
				if (key == "image") {
					image = value;
					continue;
				}				
				if (key == "image_path") {
					image_path = value;
					continue;
				}				
				postDataBuilder = setPostData(postDataBuilder, key, value, delimiter);
				if(postDataBuilder == null) {
					obj.put("message", "postRequest data build fail");
				   	return obj;
				}
			}
			
			URL url = new URL(url_string);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection(); // connect
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			connection.setUseCaches(false);
			DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()));

			// image data set
			if(image != null) {
				// MMS Setting
				if(image_path == null) image_path = "./";
				
				// image file set
				postDataBuilder.append(setFile("image", image));				
				postDataBuilder.append("\r\n");
				FileInputStream fileStream = new FileInputStream(image_path + image);
				outputStream.writeUTF(postDataBuilder.toString());
				
				// �뙆�씪�쟾�넚 �옉�뾽 �떆�옉
				int maxBufferSize = 1024;
				int bufferSize = Math.min(fileStream.available(), maxBufferSize);
				byte[] buffer = new byte[bufferSize];
				// 踰꾪띁 �겕湲곕쭔�겮 �뙆�씪濡쒕��꽣 諛붿씠�듃 �뜲�씠�꽣瑜� �씫�뒗�떎
				int byteRead = fileStream.read(buffer, 0, bufferSize);
				// �쟾�넚
				while (byteRead > 0) {
					outputStream.write(buffer);
					bufferSize = Math.min(fileStream.available(), maxBufferSize);
					byteRead = fileStream.read(buffer, 0, bufferSize);
				}
				fileStream.close();
			} else {
				outputStream.writeUTF(postDataBuilder.toString());
			}
			outputStream.writeBytes(delimiter); 
			outputStream.flush();
			outputStream.close();

			String response = null;
			String inputLine; 
			int response_code = connection.getResponseCode();
			BufferedReader in = null;
			// response �떞湲� 
			if (response_code != 200) {
				in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			}

			while ((inputLine = in.readLine()) != null) {					
				response = inputLine;
			}

			if (response != null) {
				obj = (JSONObject) JSONValue.parse(response);
				obj.put("status", true);
				if (obj.get("code") != null) {
					obj.put("status", false);
				}
			} else {
				obj.put("status", false);
				obj.put("message", "response is empty");
			}
		} catch (Exception e) {
			obj.put("status", false);
			obj.put("message", e.toString());
		}
		return obj;
	}

	/*
	 * https request (GET)
	 */
	public JSONObject request(String url_string, HashMap<String, String> params) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("status", true);
			String charset = "UTF8";
			String salt = salt();
			String timestamp = getTimestamp();
			String signature = getSignature(params.get("api_secret"), salt, timestamp); // getSignature

			String data = url_string + "?";
			data = data + URLEncoder.encode("api_key", charset) + "=" + URLEncoder.encode(params.get("api_key"), charset);
			data = setGetData(data, "signature", signature, charset);
			data = setGetData(data, "salt", salt, charset);
			data = setGetData(data, "timestamp", timestamp, charset);

			params.remove("api_secret");

			for (Entry<String, String> entry : params.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				data = setGetData(data, key, value, charset);
				if(data == null) {
					obj.put("status", false);
					obj.put("message", "request data build fail");
				   	return obj;
				}
			}

			URL url = new URL(data);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection(); // connect
			connection.setRequestMethod("GET");
			
			BufferedReader in = null;
			int response_code = connection.getResponseCode();
			if (response_code != 200) {
				// �삤瑜섎컻�깮�떆
				in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			}

			String response = null;
			String inputLine; // �꽌踰꾨줈 遺��꽣 諛쏆� response瑜� 諛쏆쓣 蹂��닔
			while ((inputLine = in.readLine()) != null) {
				response = inputLine;
			}

			if (response != null) {
				// response 媛� object �깘 array�뿉 �뵲�씪 parse瑜� �떎瑜닿쾶�븳�떎.
				try {
					obj = (JSONObject) JSONValue.parse(response);
				} catch (Exception e) {
					try {
						JSONArray reponse_array = (JSONArray) JSONValue.parse(response);
						obj.put("data", reponse_array);
					} catch (Exception ex) {
						obj.put("status", false);
					}
				}
				obj.put("status", true);
				if (obj.get("code") != null) {
					obj.put("status", false);
				}
			} else {
				obj.put("status", false);
				obj.put("message", "response is empty");
			}
		} catch (Exception e) {
			obj.put("status", false);
			obj.put("message", e.toString());
		}
		return obj;
	}

	/*
     * �뾽濡쒕뱶�븷 �뙆�씪�뿉 ���븳 硫뷀� �뜲�씠�꽣瑜� �꽕�젙�븳�떎.
     * @param key : �꽌踰꾩뿉�꽌 �궗�슜�븷 �뙆�씪 蹂��닔紐�
     * @param fileName : �꽌踰꾩뿉�꽌 ���옣�맆 �뙆�씪紐�
     */
	public String setFile(String key, String fileName) {
		return "Content-Disposition: form-data; name=\"" + key
				+ "\";filename=\"" + fileName
				+ "\"\r\nContent-type: image/jpeg;\r\n";
	}

	/*
	 * String�쓣 POST �삎�떇�뿉 留욊쾶 Input 
	 */
	public StringBuffer setPostData(StringBuffer builder, String key, String value, String delimiter) {
		try {
			builder.append(setValue(key, value));
			builder.append(delimiter);
		} catch(Exception e) {
			return null;
		}

		return builder;
	}

	/*
	 * String�쓣 GET 諛⑹떇�쑝濡� 蹂�寃�
	 */
	public String setGetData(String data, String key, String value, String charSet) {
		try {
			data += "&"
				+ URLEncoder.encode(key, charSet)
				+ "="
				+ URLEncoder.encode(value, charSet);
		} catch(Exception e) {
			return null;
		}

		return data;
	}

	/*
	 * Get salt
	 */
	public String salt() {
		String uniqId = "";
		Random randomGenerator = new Random();

		// length - set the unique Id length
		for (int length = 1; length <= 10; ++length) {
			int randomInt = randomGenerator.nextInt(10); // digit range from 0 - 9
			uniqId += randomInt + "";
		}

		return uniqId;
	}

	/*
	 * Get signature
	 */
	public String getSignature(String api_secret, String salt, String timestamp) {
		String signature = "";
		
		try {
			String temp = timestamp + salt;
			SecretKeySpec keySpec = new SecretKeySpec(api_secret.getBytes(), "HmacMD5");
			Mac mac = Mac.getInstance("HmacMD5");
			mac.init(keySpec);

			byte[] result = mac.doFinal(temp.getBytes());
			char[] hexArray = "0123456789ABCDEF".toCharArray();
			char[] hexChars = new char[result.length * 2];

			for (int i = 0; i < result.length; i++) {
				int positive = result[i] & 0xff;
				hexChars[i * 2] = hexArray[positive >>> 4];
				hexChars[i * 2 + 1] = hexArray[positive & 0x0F];
			}
			signature = new String(hexChars);
		} catch (Exception e) {
			signature = e.getMessage();
		}

		return signature;
	}

	/*
	 * Get timestamp
	 */
	public String getTimestamp() {
		long timestamp_long = System.currentTimeMillis() / 1000;
		String timestamp = Long.toString(timestamp_long);
		return timestamp;
	}

	/*
     * Map �삎�떇�쑝濡� Key�� Value瑜� �뀑�똿�븳�떎.
     * @param key : �꽌踰꾩뿉�꽌 �궗�슜�븷 蹂��닔紐�
     * @param value : 蹂��닔紐낆뿉 �빐�떦�븯�뒗 �떎�젣 媛�
     */
	public String setValue(String key, String value) {
		return "Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n"+ value;
	}
}

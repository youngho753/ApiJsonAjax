package com.movie.ctrl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Handles requests for the application home page.
 */
@Controller
public class Search {
	@RequestMapping(value = "/search.do", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String home(Locale locale, Model model,String word) {
		
		String result = null;
		
		String json = null;//검색 결과값
		List<List<String>> itemArray = new ArrayList<>();
		//네이버 api
		 String clientId = "rVVvyf_LtTKAC7kn4yzc";//애플리케이션 클라이언트 아이디값";
	        String clientSecret = "pCiow5NKVA";//애플리케이션 클라이언트 시크릿값";
	        try {
	            String text = URLEncoder.encode(word, "UTF-8");
	            int display = 10;//결과값 갯수
	            String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text; // json 결과
	            	   apiURL += "&display"+display;
	            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("GET");
	            con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));//결과를 한글로 변경
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();

	            
	            
	            json= response.toString();
	            //{jsonObject[{dataObject}{dataObject}]}
	            JSONParser jsonParser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) jsonParser.parse(json);//JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환
	            JSONArray dataArray = (JSONArray) jsonObject.get("items");////JSONObject에서 items배열을 get하여 JSONArray에 저장.
	            
	            result = dataArray.toString();
	            
	            //{dataObject},{dataObject} 이렇게 풀림
	            for(int i=0;i<dataArray.size();i++) {//dataObject크기만큼
	            	JSONObject itemsObject = (JSONObject) dataArray.get(i);//i번째 요소를 itemsObject에 저장
	            	//이제 원하는 데이터 추출 title과 link를 추출
	            	String title = itemsObject.get("title").toString();
	            	String link = itemsObject.get("link").toString();
	            	
	            	//추출된값 List에 담기
	            	List<String> data = new ArrayList<>();
	            	data.add(title);
	            	data.add(link);
	            	
	            	//이렇게 하면 끝인데 view에서 처리할 예정 그래서 배열까지만 하고 보냄
	            }	            
	            
	        } catch (Exception e) {
	            System.out.println(e);
	        }
		
	    return result;
	}
}

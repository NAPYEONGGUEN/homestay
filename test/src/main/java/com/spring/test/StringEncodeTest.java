package com.spring.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StringEncodeTest {

	public static void main(String[] args) throws UnsupportedEncodingException {



		String locs[] ={"��ü","����","���","��õ","����","��û","�뱸","�λ�","���","����","����"};
		String curLoc;

		curLoc = "��ü";
		String result = "";
		String decoded_result;
		
		for(String loc : locs){
			result = "<a href='/main?location=";
			if(loc.equals(curLoc)){
				System.out.println("<div class='col-sm regionItem selected'>");
			}else{
				System.out.println("<div class='col-sm regionItem'>");
			}
			

			

			result += URLEncoder.encode(loc, "UTF-8");
			result +="><a>"+loc;
			result +="</a></div>";
			System.out.println(result);
		}
		
		



	}




}

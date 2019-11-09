package com.homestay.korea.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.homestay.korea.DTO.PlaceDetailDataDTO;
import com.homestay.korea.DTO.TourImageDTO;
import com.homestay.korea.service.IPlaceDetailDataReadService;
import com.homestay.korea.service.IThemePreferReadService;
import com.homestay.korea.service.ITourImageReadService;

@Controller
public class InfoController {
	
	@Autowired
	IThemePreferReadService themePreferReadService;
	
	@Autowired
	private IPlaceDetailDataReadService placeDetailDataReadService;
	
	@Autowired
	private ITourImageReadService tourImageReadService;
	
	//상세 페이지로 이동
	@RequestMapping(value="/detailContent", method = RequestMethod.GET)
	public String detailContent(PlaceDetailDataDTO placeDetailDataDTO, TourImageDTO tourImageDTO, Model model, HttpServletRequest request, HttpSession session) {
		
		//메인 페이지에서 관광지의 contentId받아오기
		String contentid = request.getParameter("contentid");
		
		if(session.getAttribute("memberInfo") == null) 
		{
			model.addAttribute("memberinfo",session.getAttribute("memberInfo"));
		}
		
		try {
			
			//관광지 공통정보 + 관광지 이미지
			placeDetailDataDTO.setContentid(contentid);
			tourImageDTO.setContentid(contentid);
			
			//메인에서 값 넘겨주기 전까지는 해당 소스 사용
			//placeDetailDataDTO.setContentid("1");
			//tourImageDTO.setContentid("1");
			
			//User-Based Collaborative Filtering
			//HttpSession httpSession = httpServletRequest.getSession();
			//MemberDTO member = (MemberDTO)httpSession.getAttribute("memberInfo");
			
			/* 에러 발생하길래 주석걸어 둠
			ThemePreferDTO themePreferDTO =  themePreferReadService.getThemePrefer("billp");
			RelationAnalyze relationAnalyze = new RelationAnalyze(themePreferDTO);
			
			List<ThemePreferDTO> themePreferDTOList = themePreferReadService.getThemePreferList();
			String idArr[] =  relationAnalyze.getMatchIds(themePreferDTOList);
			for(int i=0; i<idArr.length; i++) 
			{
				System.out.println(idArr[i]);
			}
			*/
			
			//관광지 공통정보 불러오기
			model.addAttribute("readWithPlaceDetailData", placeDetailDataReadService.readWithPlaceDetailData(placeDetailDataDTO));
			//관광지 이미지정보 불러오기
			model.addAttribute("readWithPlaceDetailDataImage", tourImageReadService.readWithPlaceDetailDataImage(tourImageDTO));
			//contentId
			model.addAttribute("contentid", contentid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "homestay/detailContent";
	}
}

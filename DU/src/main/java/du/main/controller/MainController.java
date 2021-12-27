package du.main.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

	private Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value="/main.do", method = RequestMethod.GET)
	public String mainPageByGet(HttpServletRequest request) {

		logger.info(request.getQueryString());
		
		return "main.html";
	}
	
	@RequestMapping(value="/main.do", method = RequestMethod.POST)
	public String mainPageByPost(HttpServletRequest request) {

		Map<String, String[]> paramMap = request.getParameterMap();
		for(String name: paramMap.keySet()) {
			logger.info("{} : {}", name, Arrays.toString(paramMap.get(name)));
		}
		
		logger.info(request.getQueryString());
		
		return "main.html";
	}

	@RequestMapping(value="/login.do", method = RequestMethod.GET)
	public String loginPage() {
		return "login.html";
	}
}

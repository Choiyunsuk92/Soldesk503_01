package testSpringBoot.service.survey;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import testSpringBoot.command.AuthInfo;
import testSpringBoot.command.SurveyCommend;
import testSpringBoot.domain.OptionsDTO;
import testSpringBoot.domain.QuestionDTO;
import testSpringBoot.mapper.SurveyMapper;
@Component
@Service
public class SurveyInsertService {
	@Autowired
	SurveyMapper surveyMapper;
	public void surveyInsert(SurveyCommend surveyCommend, HttpSession session) 
			throws Exception{
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getId();
		// 문제가 하나일 
		QuestionDTO qdto =  new QuestionDTO();
		qdto.setQuestionTitle(surveyCommend.getQuestion());
		qdto.setUserId(userId);
		surveyMapper.questionInsert(qdto);
		//question 저장되고 나서 questionNum를 받아오기 위해 쿼리문 실행 
		int questionNum = surveyMapper.questionNum(userId);
		System.out.println(surveyCommend.getOptions()
				);
		String options [] = null;
		if(surveyCommend.getOptions() != null) {
			options = surveyCommend.getOptions().split("`");
		}
		int optionsNum = 1; //option 번호생성 
		// 문항은 1개 이상 
		if(options != null && !options.equals("")) {
			// n개만큼 문항이 저장이 되도록 반복구문 사용 
			for(String optionName : options) {
				OptionsDTO odto = new OptionsDTO();
				odto.setQuestionNum(questionNum);
				odto.setOptionName(optionName);
				odto.setOptionNum(optionsNum++); // option번호를 option 만큼 1씩증
				odto.setUserId(userId);
				System.out.print(optionName);
				surveyMapper.optionInsert(odto);
			}
		}
	}
}

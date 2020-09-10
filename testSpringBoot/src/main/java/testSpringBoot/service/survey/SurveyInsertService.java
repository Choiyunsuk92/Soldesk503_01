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
		QuestionDTO qdto =  new QuestionDTO();
		qdto.setQuestionTitle(surveyCommend.getQuestion());
		qdto.setUserId(userId);
		surveyMapper.questionInsert(qdto);
		//question 저장되고 나서 questionNum를 받아오기 위해 쿼리문 실행 
		int questionNum = surveyMapper.questionNum(userId);
		String options [] = surveyCommend.getOptions().split("`");
		int optionsNum = 1;
		if(options != null) {
			for(String optionName : options) {
				OptionsDTO odto = new OptionsDTO();
				odto.setQuestionNum(questionNum);
				odto.setOptionName(optionName);
				odto.setOptionNum(optionsNum++);
				odto.setUserId(userId);
				surveyMapper.optionInsert(odto);
			}
		}
	}
}

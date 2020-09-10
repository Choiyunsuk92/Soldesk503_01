package testSpringBoot.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import testSpringBoot.domain.OptionsDTO;
import testSpringBoot.domain.QuestionDTO;

@Component
@Repository
public interface SurveyMapper {
	public void questionInsert(QuestionDTO qdto) throws Exception;
	public Integer questionNum(String userId)throws Exception;
	public void optionInsert(OptionsDTO odto)throws Exception;
}

package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GoodsCommandValidate 
			implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors,"goodsNum","required");
		ValidationUtils.rejectIfEmpty(errors,"goodsName","required");
		ValidationUtils.rejectIfEmpty(errors,"goodsPrice","required");
		ValidationUtils.rejectIfEmpty(errors,"goodsContent","required");
		ValidationUtils.rejectIfEmpty(errors,"goodsImage","required");
	}
}

package cz.vasekric.beetletrack.webgui.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.time.Duration;
import java.time.format.DateTimeParseException;

/**
 * Created by vasek on 09.12.2015.
 */
@FacesValidator
@ManagedBean
public class LogWorkValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try {
            Duration.parse("PT" + value);
        } catch (DateTimeParseException e) {
            throw new ValidatorException(
                    new FacesMessage("Invalid format for log work. Example 20h")
            );
        }
    }
}

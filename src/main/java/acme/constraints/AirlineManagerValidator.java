
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import acme.client.components.validation.AbstractValidator;
import acme.client.helpers.SpringHelper;
import acme.entities.airline.AirlineManager;
import acme.entities.airline.AirlineManagerRepository;

public class AirlineManagerValidator extends AbstractValidator<ValidAirlineManager, AirlineManager> {

	@Override
	public boolean isValid(final AirlineManager airlineManager, final ConstraintValidatorContext context) {
		AirlineManagerRepository airlineManagerRepository = SpringHelper.getBean(AirlineManagerRepository.class);
		String initials = AirlineManagerValidator.getInitials(airlineManager.getUserAccount().getIdentity().getName(), airlineManager.getUserAccount().getIdentity().getSurname());
		String identifierNumberInitialsToValidate = airlineManager.getIdentifierNumber().substring(0, initials.length());
		String identifierNumberToValidate = airlineManager.getIdentifierNumber();
		boolean result = initials.equals(identifierNumberInitialsToValidate);
		if (!result)
			super.state(context, false, "identifierNumber", "acme.validation.airlineManager.identifierNumber.initials.message");
		if (!airlineManagerRepository.identifierNumberAlreadyExists(identifierNumberToValidate)) {
			super.state(context, false, "identifierNumber", "acme.validation.airlineManager.identifierNumber.unique.message");
			result = false;
		}

		return result;
	}

	private static String getInitials(final String name, final String surname) {
		StringBuilder result = new StringBuilder();
		result.append(name.charAt(0));
		result.append(surname.charAt(0));
		return result.toString();
	}
}

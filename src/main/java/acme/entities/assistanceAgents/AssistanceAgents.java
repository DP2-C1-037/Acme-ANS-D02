
package acme.entities.assistanceAgents;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.datatypes.Money;
import acme.client.components.mappings.Automapped;
import acme.client.components.principals.UserAccount;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidMoney;
import acme.client.components.validation.ValidString;
import acme.client.components.validation.ValidUrl;
import acme.constraints.ValidEmployeeCode;
import acme.entities.airline.Airline;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ValidEmployeeCode

public class AssistanceAgents extends AbstractEntity {

	// Serialisation version -----------------------------------------------------------------------------------------
	private static final long	serialVersionUID	= 1L;
	// Attributes ----------------------------------------------------------------------------------------------------

	@Mandatory
	@Column(unique = true)
	@ValidString(min = 8, max = 9, pattern = "^[A-Z]{2,3}\\d{6}$")
	private String				employeeCode;

	@Mandatory
	@Automapped
	@ValidString(min = 1, max = 255)
	private String				spokenLanguages;

	@Mandatory
	@ValidMoment(past = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	@Optional
	@Automapped
	@ValidMoney
	private Money				salary;

	@Optional
	@Automapped
	@ValidString(max = 255)
	private String				briefBio;

	@Optional
	@Automapped
	@ValidUrl
	private String				picture;

	// Relationships -------------------------------------------------------------------------------------------------

	@Mandatory
	@Valid
	@OneToOne(optional = false)
	private UserAccount			userAccount;

	@Mandatory
	@ManyToOne(optional = false)
	@Valid
	private Airline				airline;

}

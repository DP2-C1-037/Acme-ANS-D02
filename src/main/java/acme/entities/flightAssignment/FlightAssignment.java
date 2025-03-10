
package acme.entities.flightAssignment;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidString;
import acme.datatypes.AssignmentStatus;
import acme.datatypes.FlightCrewDuty;
import acme.entities.airline.Leg;
import acme.realms.FlightCrewMember;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FlightAssignment extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	@ManyToOne(optional = false)
	@Valid
	private Leg					leg;

	@Mandatory
	@ManyToOne(optional = false)
	@Valid
	private FlightCrewMember	flightCrewMember;

	@Mandatory
	@Automapped
	@Valid
	private FlightCrewDuty		flightCrewDuty;

	@Mandatory
	@Temporal(TemporalType.TIMESTAMP)
	@ValidMoment(past = true)
	private Date				lastUpdateMoment;

	@Mandatory
	@Automapped
	@Valid
	private AssignmentStatus	status;

	@Optional
	@Automapped
	@ValidString(min = 0, max = 255)
	private String				remarks;

}

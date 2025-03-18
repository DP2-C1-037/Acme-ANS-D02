
package acme.entities.airline;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.datatypes.Money;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoney;
import acme.client.components.validation.ValidString;
import acme.client.helpers.SpringHelper;
import acme.datatypes.FlightSelfTransfer;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Flight extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	@ValidString(min = 50, max = 50)
	@Automapped
	private String				tag;

	@Mandatory
	@Valid
	@Automapped
	private FlightSelfTransfer	requiresSelfTransfer;

	@Mandatory
	@ValidMoney(min = 0, max = 1000000)
	@Automapped
	private Money				cost;

	@Optional
	@ValidString
	@Automapped
	private String				description;

	// Derived attributes


	@Transient
	private Date getScheduledDeparture() {
		LegRepository legRepository = SpringHelper.getBean(LegRepository.class);
		return legRepository.findScheduledDeparture(this.getId());
	}

	@Transient
	private Date getScheduledArrival() {
		LegRepository legRepository = SpringHelper.getBean(LegRepository.class);
		return legRepository.findScheduledArrival(this.getId());
	}

	@Transient
	private String getOriginCity() {
		LegRepository legRepository = SpringHelper.getBean(LegRepository.class);
		return legRepository.findOriginCity(this.getId());
	}

	@Transient
	private String getDestinationCity() {
		LegRepository legRepository = SpringHelper.getBean(LegRepository.class);
		return legRepository.findDestinationCity(this.getId());
	}

	@Transient
	private int getLayoversNumber() {
		LegRepository legRepository = SpringHelper.getBean(LegRepository.class);
		return legRepository.legsNumberFromFlightId(this.getId());
	}

	// Relationships


	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private AirlineManager airlineManager;
}

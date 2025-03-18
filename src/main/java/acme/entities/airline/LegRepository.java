
package acme.entities.airline;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface LegRepository extends AbstractRepository {

	@Query("SELECT min(l.scheduledDeparture) FROM Leg l where l.id = :legId")
	public Date findScheduledDeparture(int legId);

	@Query("SELECT max(l.scheduledArrival) FROM Leg l where l.id = :legId")
	public Date findScheduledArrival(int legId);

	@Query("SELECT l.departureAirport.city FROM Leg l WHERE l.id = :legId AND l.scheduledDeparture = (SELECT MIN(l2.scheduledDeparture) FROM Leg l2 WHERE l2.id = :legId)")
	public String findOriginCity(int legId);

	@Query("SELECT l.arrivalAirport.city FROM Leg l WHERE l.id = :legId AND l.scheduledArrival = (SELECT MAX(l2.scheduledArrival) FROM Leg l2 WHERE l2.id = :legId)")
	public String findDestinationCity(int legId);

	@Query("SELECT COUNT(l) FROM Leg l WHERE leg.flight.id = :flightId")
	public Integer legsNumberFromFlightId(int flightId);

	@Query("SELECT COUNT(l) > 0 FROM Leg l WHERE l.flightNumber = :flightNumber")
	public Boolean flightNumberAlreadyExists(String flightNumber);

}

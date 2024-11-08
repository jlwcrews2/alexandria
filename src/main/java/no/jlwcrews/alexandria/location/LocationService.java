package no.jlwcrews.alexandria.location;

import no.jlwcrews.alexandria.application.error.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LocationService {

    private final LocationRepo locationRepo;

    @Autowired
    public LocationService(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    public List<Location> getLocations() {
        return locationRepo.findAll();
    }

    public Location getLocationById(long id) {
        Location location;
        try {
            location = locationRepo.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new LocationNotFoundException("Location " + id + " not found");
        }
        return location;
    }

    public Location saveLocation(Location location) {
        return locationRepo.save(location);
    }

    public void deleteLocationById(long id) {
        locationRepo.deleteById(id);
    }

    public void deleteAllLocations() {
        locationRepo.deleteAll();
    }
}

package rent.easily.property.application.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.property.application.dto.PropertyDTO;
import rent.easily.property.domain.entity.Address;
import rent.easily.property.domain.entity.Property;
import rent.easily.property.infrastructure.database.AddressModel;
import rent.easily.property.infrastructure.database.AddressRepository;
import rent.easily.property.infrastructure.database.PropertyModel;
import rent.easily.property.infrastructure.database.PropertyRepository;
import rent.easily.shared.application.service.EntityNormalizer;
import rent.easily.shared.domain.exception.BusinessException;
import rent.easily.shared.domain.exception.ValidationError;

@ApplicationScoped
public class PropertyNormalizer extends EntityNormalizer<PropertyDTO, PropertyModel> {

    @Inject
    PropertyRepository repository;
    @Inject
    AddressRepository addressRepository;

    @Override
    public PropertyModel normalize(PropertyDTO entry, Long id) throws ValidationError {
        PropertyModel model = repository.findById(id);
        if (isNull(model))
            throw new ValidationError(List.of(
                    new BusinessException("There's no Property to given id", "Property.service.PropertyNormalize")));

        AddressModel addressModel = addressRepository.findById(entry.getAddress().getZipCode());

        Address address = new Address(
                setNonNull(entry.getAddress().getCountry(), addressModel.getCountry()),
                setNonNull(entry.getAddress().getState(), addressModel.getState()),
                setNonNull(entry.getAddress().getCity(), addressModel.getCity()),
                setNonNull(entry.getAddress().getNeighborhood(), addressModel.getNeighborhood()),
                setNonNull(entry.getAddress().getStreet(), addressModel.getStreet()),
                setStreetNumber(setNonNull(entry.getAddress().getStreetNumber(), addressModel.getStreetNumber())),
                setNonNull(entry.getAddress().getZipCode(), addressModel.getZipCode()));

        Property property = new Property(id, setNonNull(entry.getDescription(), model.getDescription()),
                setNonNull(entry.getUserId(), model.getUserId()),
                setNonNull(entry.getRegistryId(), model.getRegistryId()),
                setNonNull(setActive(entry.isActive()), model.getActive()),
                address);

        model.setDescription(property.getDescription());
        model.setActive(setActive(property.isActive()));

        return model;
    }

    private <T> String setStreetNumber(T streetNumber) {

        if (streetNumber instanceof Integer) {
            int number = (int) streetNumber;
            return number <= 0 ? "SN" : String.valueOf(streetNumber);
        }

        String numberStr = (String) streetNumber;
        return String.valueOf(numberStr);

    }

    private String setActive(boolean value) {
        return value ? "1" : "0";
    }

}

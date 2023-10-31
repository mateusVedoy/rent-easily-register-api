package rent.easily.property.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.property.domain.entity.Address;
import rent.easily.shared.infrastructure.Repository;

@ApplicationScoped
public class AddressRepository extends Repository<Address, AddressModel> {


    public AddressModel findById(String id) {
        PanacheQuery<AddressModel> result = find("zipCode = ?1", id);
        return result.firstResult();
    }

    public Address findPerId(String id) {
        PanacheQuery<AddressModel> result = find("zipCode = ?1", id);
        AddressModel model = result.firstResult();
        if (model != null)
            return convertToDomainList(List.of(model)).stream().findFirst().get();
        return null;
    }

    @Override
    protected AddressModel convertToModel(Address entity) {
        return new AddressModel(entity.getCountry(),
                entity.getState(),
                entity.getCity(),
                entity.getNeighborhood(),
                entity.getStreet(),
                entity.getStreetNumber(),
                entity.getZipCode());
    }

    @Override
    protected List<Address> convertToDomainList(List<AddressModel> models) {
        List<Address> addresses = new ArrayList<>();
        models.forEach(address -> {
            addresses.add(new Address(
                    address.getCountry(),
                    address.getState(),
                    address.getCity(),
                    address.getNeighborhood(),
                    address.getStreet(),
                    address.getStreetNumber(),
                    address.getZipCode()));
        });

        return addresses;
    }

}

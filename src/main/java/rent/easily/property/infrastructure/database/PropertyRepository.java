package rent.easily.property.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.property.domain.entity.Address;
import rent.easily.property.domain.entity.Property;
import rent.easily.shared.infrastructure.Repository;

@ApplicationScoped
public class PropertyRepository extends Repository<Property, PropertyModel> {

    @Inject
    AddressRepository repository;

    public boolean existsById(Long id) {
        Long result = count("id = ?1", id);
        return result > 0;
    }

    //verificar se passará por aqui
    public List<Property> save(Property entity) {
        PropertyModel model = convertToModel(entity);
        persist(model);
        repository.save(entity.getAddress());
        return convertToDomainList(List.of(model));
    }

    @Override
    protected PropertyModel convertToModel(Property entity) {
        PropertyModel model = new PropertyModel(
                entity.getDescription(),
                entity.getUserId(),
                entity.getRegistryId(),
                entity.getAddress().getZipCode());
        return model;
    }

    @Override
    protected List<Property> convertToDomainList(List<PropertyModel> models) {
        List<Property> properties = new ArrayList<>();
        for (PropertyModel model : models) {
            Address address = repository.findPerId(model.getAddress());
            properties.add(new Property(model.getId(), model.getDescription(), model.getUserId(), model.getRegistryId(),
                    model.getActive(),
                    address));
        }
        return properties;
    }
}

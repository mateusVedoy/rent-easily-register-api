package rent.easily.property.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.property.domain.entity.Property;
import rent.easily.shared.infrastructure.Repository;

@ApplicationScoped
public class PropertyRepository extends Repository<Property, PropertyModel> {

    public boolean existsById(Long id) {
        Long result = count("id = ?1", id);
        return result > 0;
    }

    @Override
    protected PropertyModel convertToModel(Property entity) {
        PropertyModel model = new PropertyModel(
                entity.getDescription(),
                entity.getUserId(),
                entity.getRegistryId());
        return model;
    }

    @Override
    protected List<Property> convertToDomainList(List<PropertyModel> models) {
        List<Property> properties = new ArrayList<>();
        for (PropertyModel model : models) {
            properties.add(new Property(model.getId(), model.getDescription(), model.getUserId(), model.getRegistryId(), model.getActive()));
        }
        return properties;
    }
}

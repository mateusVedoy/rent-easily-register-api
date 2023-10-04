package rent.easily.property.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.shared.infrastructure.Repository;
import rent.easily.property.domain.Property;

@ApplicationScoped
public class PropertyRepository extends Repository<Property, PropertyModel> {

    @Override
    protected PropertyModel convertToModel(Property entity) {
        PropertyModel model = new PropertyModel(
                entity.getDescription(),
                entity.getUserId());
        return model;
    }

    @Override
    protected List<Property> convertToDomainList(List<PropertyModel> models) {
        List<Property> properties = new ArrayList<>();
        for (PropertyModel model : models) {
            properties.add(new Property(model.getId(), model.getDescription(), model.getUserId()));
        }
        return properties;
    }
}

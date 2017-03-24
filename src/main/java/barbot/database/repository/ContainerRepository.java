package barbot.database.repository;

import barbot.database.model.BarbotContainers;
import org.springframework.data.repository.CrudRepository;

public interface ContainerRepository extends BaseRepository<BarbotContainers, Long> {

    BarbotContainers findByBarbotIdAndIngredient(String barbotId, String ingredientId);
}

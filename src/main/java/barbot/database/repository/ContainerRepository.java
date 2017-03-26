package barbot.database.repository;

import barbot.database.model.BarbotContainers;

public interface ContainerRepository extends BaseRepository<BarbotContainers, Long> {

    BarbotContainers findByBarbotIdAndIngredient(String barbotId, String ingredientId);
}

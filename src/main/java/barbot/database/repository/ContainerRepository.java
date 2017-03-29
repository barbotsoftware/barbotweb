package barbot.database.repository;

import barbot.database.model.BarbotContainer;

public interface ContainerRepository extends BaseRepository<BarbotContainer, Long> {

    BarbotContainer findByBarbotIdAndIngredient(String barbotId, String ingredientId);
}

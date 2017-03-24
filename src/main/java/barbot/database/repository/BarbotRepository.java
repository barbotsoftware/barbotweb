package barbot.database.repository;

import barbot.database.model.Barbots;

public interface BarbotRepository extends BaseRepository<Barbots, Long> {

    Barbots findByUid(String uid);
}

package barbot.database.repository;

import barbot.database.model.Barbot;

public interface BarbotRepository extends BaseRepository<Barbot, Long> {

    Barbot findByUid(String uid);
}

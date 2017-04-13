package barbot.database.service;

import barbot.database.model.Barbot;
import barbot.database.repository.BarbotRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Created by Naveen on 4/12/17.
 */
public class BarbotServiceImpl implements BarbotService {

    private final BarbotRepository barbotRepository;

    public BarbotServiceImpl(BarbotRepository barbotRepository) {
        this.barbotRepository = barbotRepository;
    }

    @Override
    @Transactional
    public Barbot findById(String barbotId) {
        Assert.hasLength(barbotId, "BarbotId must not be empty");
        return this.barbotRepository.findByUid(barbotId);
    }
}

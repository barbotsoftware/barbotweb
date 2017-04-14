package barbot.database.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import barbot.database.model.Barbot;
import barbot.database.repository.BarbotRepository;

/**
 * Created by Naveen on 4/12/17.
 */
@Service
public class BarbotServiceImpl implements BarbotService {

    @Resource
    private BarbotRepository barbotRepository;

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

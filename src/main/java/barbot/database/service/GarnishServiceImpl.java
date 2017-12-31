package barbot.database.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import barbot.database.dao.BarbotGarnishDao;
import barbot.database.dao.GarnishDao;
import barbot.database.model.Barbot;
import barbot.database.model.BarbotGarnish;
import barbot.database.model.Garnish;

/**
 * Created by Naveen on 12/30/17.
 */
@Service
@Transactional
public class GarnishServiceImpl implements GarnishService {

    @Autowired
    GarnishDao garnishDao;

    @Autowired
    BarbotGarnishDao barbotGarnishDao;

    @Override
    @Transactional
    public Garnish findByUid(String garnishId) {
        Assert.hasLength(garnishId, "GarnishId must not be empty");
        return garnishDao.findByUid(garnishId);
    }

    @Override
    public BarbotGarnish findByBarbotAndOptionNumber(Barbot barbot, int optionNumber) {
        if (barbot == null) {
            throw new NullPointerException(barbot + " not found.");
        }
        return barbotGarnishDao.findByBarbotAndOptionNumber(barbot, optionNumber);
    }

    @Override
    public void update(BarbotGarnish garnish) {
        if (garnish == null) {
            throw new NullPointerException(garnish + " not found.");
        }

        barbotGarnishDao.update(garnish);
    }
}

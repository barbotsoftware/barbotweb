package barbot.database.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barbot.database.dao.BarbotContainerDao;
import barbot.database.model.Barbot;
import barbot.database.model.BarbotContainer;

/**
 * Created by Naveen on 6/27/17.
 */
@Service
@Transactional
public class BarbotContainerServiceImpl implements BarbotContainerService {

    @Autowired
    BarbotContainerDao barbotContainerDao;

    @Override
    public BarbotContainer findById(int id) {
        return barbotContainerDao.findById(id);
    }

    @Override
    public BarbotContainer findByBarbotAndNumber(Barbot barbot, int number) {
        if (barbot == null) {
            throw new NullPointerException(barbot + " not found.");
        }
        return barbotContainerDao.findByBarbotAndNumber(barbot, number);
    }

    @Override
    public void update(BarbotContainer barbotContainer) {
        if (barbotContainer == null) {
            throw new NullPointerException(barbotContainer + " not found.");
        }

        barbotContainerDao.update(barbotContainer);
    }
}

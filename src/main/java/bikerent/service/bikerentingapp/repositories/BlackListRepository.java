package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.BlackList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BlackListRepository extends CrudRepository<BlackList, Long> {

    BlackList findBlackListByPhoneNumber(int phoneNumber);

    void deleteById(Long id);
}

package po.lab.example.predix.timeseries.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import po.lab.example.predix.timeseries.Model.Unit1;

public interface Unit1Repository extends CrudRepository<Unit1,Integer> {
    @Query(value = "SELECT ?1 FROM Unit1", nativeQuery = true)
    Iterable<Unit1> findByTag(String tag);
}

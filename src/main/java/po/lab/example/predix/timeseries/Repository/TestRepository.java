package po.lab.example.predix.timeseries.Repository;

import org.springframework.data.repository.CrudRepository;
import po.lab.example.predix.timeseries.Model.Test;

public interface TestRepository extends CrudRepository<Test,Integer> {
}

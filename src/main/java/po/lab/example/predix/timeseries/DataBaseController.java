package po.lab.example.predix.timeseries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import po.lab.example.predix.timeseries.Model.Test;
import po.lab.example.predix.timeseries.Model.Unit1;
import po.lab.example.predix.timeseries.Repository.TestRepository;
import po.lab.example.predix.timeseries.Repository.Unit1Repository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/db")
public class DataBaseController {
    @Autowired
    private Unit1Repository unit1Repository;
    @Autowired
    private TestRepository testRepository;

    @RequestMapping("/getdb")
    public String getDB() throws ParseException {
        Iterable<Unit1> unitIt = unit1Repository.findAll();
        List<Unit1> target = new ArrayList<>();
        unitIt.forEach(target::add);
        String result = "[";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

        for(int i = 0; i<target.size(); i++){
            Unit1 un = target.get(i);
            Date parsedDate = dateFormat.parse(un.date);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            result+=(i==target.size()-1)? "["+timestamp.getTime()+","+un.Brg1_Drn_Temp+","+un.id+" ]]":"["+timestamp.getTime()+","+un.Brg1_Drn_Temp+","+un.id+" ],";
        }
        return result;
    }

    @RequestMapping("/test")
    public Iterable<Test> gettest(){
        Test tst = new Test();
        tst.id = 2;
        tst.descripton = "tst";
        tst.name = "nametst";
        testRepository.save(tst);
        return testRepository.findAll();
    }
}

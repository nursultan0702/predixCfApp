package po.lab.example.predix.timeseries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonResult {

    private String values;

    public String getName() {
        return values;
    }

    }


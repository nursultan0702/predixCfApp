package po.lab.example.predix.timeseries;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class QueryController {

    @Value("${demo.timeseries.queryUrlPrefix}")
    private String queryUrlPrefix;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @RequestMapping("/ping")
    public String ping(){
        return "pong - " + System.currentTimeMillis();
    }

    @ExceptionHandler
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public Object exceptionHandler(HttpStatusCodeException e) {
        return e.getResponseBodyAsString();
    }

    @RequestMapping("/tags")
    public String queryTags() throws Exception {
        String resultTags = restTemplate.getForEntity(queryUrlPrefix + "/tags", String.class, emptyMap()).getBody();
        JSONObject tags = new JSONObject(resultTags);
        JSONArray tagsarray = tags.getJSONArray("results");
        ArrayList<String> listdata = new ArrayList<String>();
        for (int i=0;i<tagsarray.length();i++){
            listdata.add(tagsarray.getString(i));
        }
        return tagsarray.toString();
    }

    @RequestMapping("/latest")
    public String queryLatestValues(String tag) throws Exception{
        String result = restTemplate.postForEntity(queryUrlPrefix + "/datapoints/latest", "{\"tags\":[{\"name\":\""+tag+"\"}]}", String.class, emptyMap()).getBody();
        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("tags");
        JSONArray jsArray = jsonArray.getJSONObject(0).getJSONArray("results").getJSONObject(0).getJSONArray("values");
        String resjs = jsArray.toString();
        resjs = resjs.replace("[","");
        resjs = resjs.replace("]","");
        return resjs;
    }
    @RequestMapping("/getTemp")
    public ModelAndView getTemp(@RequestParam(defaultValue = "{\"start\": \"1y-ago\",\"tags\":[{\"name\":\"Compressor-2017:Temperature\",\"order\": \"desc\"}]}") String requestBody) throws Exception {
        String result = restTemplate.postForEntity(queryUrlPrefix + "/datapoints", requestBody, String.class, emptyMap()).getBody();
        JSONObject jsonObject = new JSONObject(result);
        Map<String, String> model = new HashMap<>();
        model.put("result", result);
        return new ModelAndView("index");
    }
    @RequestMapping("/index")
    public ModelAndView Index(@RequestParam(defaultValue = "{\"start\": \"1y-ago\",\"tags\":[{\"name\":\"NGP_U2\",\"order\": \"desc\"}]}") String requestBody) throws Exception {
        try {
            String result = restTemplate.postForEntity(queryUrlPrefix + "/datapoints", requestBody, String.class, emptyMap()).getBody();
            String velocityResult = restTemplate.postForEntity(queryUrlPrefix + "/datapoints", "{\"start\": \"1y-ago\",\"tags\":[{\"name\":\"[PredixU2]PGM_Fuel.Npt" +
                    "\",\"order\": \"desc\"}]}", String.class, emptyMap()).getBody();
            JSONObject jsonObject = new JSONObject(result);
            Double res = 0.0;
            JSONArray jsonArray = jsonObject.getJSONArray("tags");
            JSONArray jsArray = jsonArray.getJSONObject(0).getJSONArray("results").getJSONObject(0).getJSONArray("values");
            for (int i = 0; i < jsArray.length(); i++) {
                res = res + getValue(jsArray.getJSONArray(i).toString());
            }
            res = res / jsArray.length();

            JSONObject jsonObjectV = new JSONObject(velocityResult);
            Double resV = 0.0;
            JSONArray jsonArrayV = jsonObjectV.getJSONArray("tags");
            JSONArray jsArrayV = jsonArrayV.getJSONObject(0).getJSONArray("results").getJSONObject(0).getJSONArray("values");
            for (int i = 0; i < jsArrayV.length(); i++) {
                resV = resV + getValue(jsArrayV.getJSONArray(i).toString());
            }
            resV = resV / jsArrayV.length();

            Map<String, String> model = new HashMap<>();
            model.put("gauge", Integer.toString(res.intValue()));
            model.put("DischargePressure", Integer.toString(resV.intValue()));
            return new ModelAndView("index", model);
        }catch (Exception wx){
            Map<String, String> model = new HashMap<>();
            model.put("gauge", Integer.toString(0));
            model.put("DischargePressure", Integer.toString(0));
            return  new ModelAndView("index",model);
        }
    }
    public static double getValue(String inputString) throws Exception{
        String[] result = inputString.split(",");
        return Double.parseDouble(result[1]);
    }
        @RequestMapping("/timeseries")
        public String timeSeries(String start, String end , String tag) throws Exception{
            if(start==null && end==null || start.equals("NaN")){
            String result = restTemplate.postForEntity(queryUrlPrefix + "/datapoints", "{\"cache_time\": 0,\"tags\":[{\"name\":\"ALTUS TEMP SUM\",\"order\": \"desc\"}],\"start\": 1452112200000,\n" +
            "  \"end\": 1513049857000}", String.class, emptyMap()).getBody();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("tags");
            JSONArray jsArray = jsonArray.getJSONObject(0).getJSONArray("results").getJSONObject(0).getJSONArray("values");
            return jsArray.toString();
            }else{
              String result = restTemplate.postForEntity(queryUrlPrefix + "/datapoints", "{\"cache_time\": 0,\"tags\":[{\"name\":\""+tag+"\",\"order\": \"desc\"}],\"start\": "+start+",\n" +
            "  \"end\": "+end+"}", String.class, emptyMap()).getBody();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("tags");
            JSONArray jsArray = jsonArray.getJSONObject(0).getJSONArray("results").getJSONObject(0).getJSONArray("values");
            return jsArray.toString();
            }
        }
        @RequestMapping("/login")
        public String loginValidate(String username, String password) throws Exception{
            if(username.equals("zsse") && password.equals("zsse123")){
            return "ok";
            }
            return "missmatch";
        }
        @RequestMapping("/")
        public ModelAndView firstload(){
        return new ModelAndView("login");
        }
        @RequestMapping("/timeseries/table")
    public String getTable(){
            String resultJson = "{\"values\":[";
            //get All tags
            String resultTags = restTemplate.getForEntity(queryUrlPrefix + "/tags", String.class, emptyMap()).getBody();
            JSONObject tags = new JSONObject(resultTags);
            JSONArray tagsarray = tags.getJSONArray("results");
            ArrayList<String> listdata = new ArrayList<String>();
            for (int i=0;i<tagsarray.length();i++){
                listdata.add(tagsarray.getString(i));
            }
            int i = 0;
            // get last values of tags
            for (String str:listdata) {
                i++;
                String result = restTemplate.postForEntity(queryUrlPrefix + "/datapoints/latest", "{\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"name\": \"" + str + "\"\n" +
                        "    }\n" +
                        "  ]\n" +
                            "}", String.class, emptyMap()).getBody();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("tags");
                JSONArray jsArray = jsonArray.getJSONObject(0).getJSONArray("results").getJSONObject(0).getJSONArray("values");
                    String resjs = jsArray.toString();
                    resjs = resjs.replace("[","");
                    resjs = resjs.replace("]","");
                   // r1esultJson += "{\"" + str + "\" = \"" + resjs + "\"}";
                    if(i==listdata.size()) {
                        resultJson += "{\"name\":\"" + str + "\", \"value\":\"" + resjs + "\"}]";
                    }else{
                        resultJson += "{\"name\":\"" + str + "\", \"value\":\"" + resjs + "\"},";
                    }

            }
            return resultJson+="}";
        }
}

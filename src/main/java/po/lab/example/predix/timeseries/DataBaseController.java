package po.lab.example.predix.timeseries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import po.lab.example.predix.timeseries.Model.TagData;
import po.lab.example.predix.timeseries.Model.Test;
import po.lab.example.predix.timeseries.Model.Unit1;
import po.lab.example.predix.timeseries.Repository.TestRepository;
import po.lab.example.predix.timeseries.Repository.Unit1Repository;

import javax.swing.text.html.HTML;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/db")
public class DataBaseController {
    @Autowired
    private Unit1Repository unit1Repository;
    @Autowired
    private TestRepository testRepository;

    @RequestMapping("/getdb")
    public String getDB(String tag) throws ParseException {
        Iterable<Unit1> unitIt = unit1Repository.findAll();
        List<Unit1> target = new ArrayList<>();
        unitIt.forEach(target::add);
        String result = "[";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

        for(int i = 0; i<target.size(); i++) {
            Unit1 un = target.get(i);
            Date parsedDate = dateFormat.parse(un.dateandtime);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            //result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Brg1_Drn_Temp + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Brg1_Drn_Temp + "," + un.id + " ],";
            switch (tag) {
                case "NGP":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.NGP + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.NGP + "," + un.id + " ],";
                    break;
                case "NPT":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.NPT + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.NPT + "," + un.id + " ],";
                    break;
                case "PCD":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.PCD + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.PCD + "," + un.id + " ],";
                    break;
                case "T5_Average":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T5_Average + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T5_Average + "," + un.id + " ],";
                    break;
                case "T5_Spread":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T5_Spread + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T5_Spread + "," + un.id + " ],";
                    break;
                case "Header_P":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Header_P + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Header_P + "," + un.id + " ],";
                    break;
                case "Header_T":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Header_T + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Header_T + "," + un.id + " ],";
                    break;
                case "T1":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T1 + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T1 + "," + un.id + " ],";
                    break;
                case "HPC_Suction_P":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.HPC_Suction_P + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.HPC_Suction_P + "," + un.id + " ],";
                    break;
                case "HPC_Suction_T":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.HPC_Suction_T + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.HPC_Suction_T + "," + un.id + " ],";
                    break;
                case "HPC_Disch_P":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.HPC_Disch_P + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.HPC_Disch_P + "," + un.id + " ],";
                    break;
                case "HPC_Disch_T":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.HPC_Disch_T + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.HPC_Disch_T + "," + un.id + " ],";
                    break;
                case "HPC_Eta_Nom":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.HPC_Eta_Nom + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.HPC_Eta_Nom + "," + un.id + " ],";
                    break;
                case "HPC_Eta_Act":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.HPC_Eta_Act + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.HPC_Eta_Act + "," + un.id + " ],";
                    break;
                case "HPC_Flow_Act":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.HPC_Flow_Act + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.HPC_Flow_Act + "," + un.id + " ],";
                    break;
                case "HPC_Flow_Std":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.HPC_Flow_Std + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.HPC_Flow_Std + "," + un.id + " ],";
                    break;
                case "T5_TC1":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T5_TC1 + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T5_TC1 + "," + un.id + " ],";
                    break;
                case "T5_TC2":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T5_TC2 + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T5_TC2 + "," + un.id + " ],";
                    break;
                case "T5_TC3":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T5_TC3 + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T5_TC3 + "," + un.id + " ],";
                    break;
                case "T5_TC4":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T5_TC4 + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T5_TC4 + "," + un.id + " ],";
                    break;
                case "T5_TC5":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T5_TC5 + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T5_TC5 + "," + un.id + " ],";
                    break;
                case "T5_TC6":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T5_TC6 + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T5_TC6 + "," + un.id + " ],";
                    break;
                case "T5_TC7":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T5_TC7 + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T5_TC7 + "," + un.id + " ],";
                    break;
                case "T5_TC8":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T5_TC8 + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T5_TC8 + "," + un.id + " ],";
                    break;
                case "T5_TC9":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T5_TC9 + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T5_TC9 + "," + un.id + " ],";
                    break;
                case "T5_TC10":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T5_TC10 + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T5_TC10 + "," + un.id + " ],";
                    break;
                case "T5_TC11":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T5_TC11 + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T5_TC11 + "," + un.id + " ],";
                    break;
                case "T5_TC12":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.T5_TC12 + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.T5_TC12 + "," + un.id + " ],";
                    break;
                case "HPC_Ax_Disp":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.HPC_Ax_Disp + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.HPC_Ax_Disp + "," + un.id + " ],";
                    break;
                case "GP_Ax_Disp":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.GP_Ax_Disp + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.GP_Ax_Disp + "," + un.id + " ],";
                    break;
                case "PT_Ax_Disp":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.PT_Ax_Disp + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.PT_Ax_Disp + "," + un.id + " ],";
                    break;
                case "Brg1_Drn_Temp":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Brg1_Drn_Temp + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Brg1_Drn_Temp + "," + un.id + " ],";
                    break;
                case "Brg1x_Vib":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Brg1x_Vib + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Brg1x_Vib + "," + un.id + " ],";
                    break;
                case "Brg1y_Vib":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Brg1y_Vib + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Brg1y_Vib + "," + un.id + " ],";
                    break;
                case "Brg2_3_Drn_Temp":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Brg2_3_Drn_Temp + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Brg2_3_Drn_Temp + "," + un.id + " ],";
                    break;
                case "Brg2x_Vib":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Brg2x_Vib + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Brg2x_Vib + "," + un.id + " ],";
                    break;
                case "Brg2y_Vib":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Brg2y_Vib + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Brg2y_Vib + "," + un.id + " ],";
                    break;
                case "Brg3x_Vib":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Brg3x_Vib + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Brg3x_Vib + "," + un.id + " ],";
                    break;
                case "Brg3y_Vib":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Brg3y_Vib + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Brg3y_Vib + "," + un.id + " ],";
                    break;
                case "Brg4_5_Drn_Temp":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Brg4_5_Drn_Temp + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Brg4_5_Drn_Temp + "," + un.id + " ],";
                    break;
                case "Brg4x_Vib":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Brg4x_Vib + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Brg4x_Vib + "," + un.id + " ],";
                    break;
                case "Brg4y_Vib":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Brg4y_Vib + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Brg4y_Vib + "," + un.id + " ],";
                    break;
                case "Brg5x_Vib":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Brg5x_Vib + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Brg5x_Vib + "," + un.id + " ],";
                    break;
                case "Brg5y_Vib":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Brg5y_Vib + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Brg5y_Vib + "," + un.id + " ],";
                    break;
                case "Gasfuel_Flow":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Gasfuel_Flow + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Gasfuel_Flow + "," + un.id + " ],";
                    break;
                case "Gasfuel_Press":
                    result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.Gasfuel_Press + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.Gasfuel_Press + "," + un.id + " ],";
                    break;


            }
        }
        return result;
    }
    @RequestMapping("/gettag")
    public List testdb() throws ClassNotFoundException, SQLException {
        List ll = new LinkedList();
        Class.forName("com.mysql.jdbc.Driver") ;
        Connection conn = DriverManager.getConnection("jdbc:mysql://80.241.40.230:3306/zsse", "userzsse", "Zeinet8sse") ;
        Statement stmt = conn.createStatement() ;
        String query = "SELECT `COLUMN_NAME` \n" +
                "FROM `INFORMATION_SCHEMA`.`COLUMNS` \n" +
                "WHERE `TABLE_SCHEMA`='zsse' \n" +
                "    AND `TABLE_NAME`='Unit1';" ;
        ResultSet rs = stmt.executeQuery(query) ;
        while (rs.next()) {
            String str = rs.getString("COLUMN_NAME");
            ll.add(str);
        }
        rs.close();
        return ll;
    }
    //result += (i == target.size() - 1) ? "[" + timestamp.getTime() + "," + un.NGP + "," + un.id + " ]]" : "[" + timestamp.getTime() + "," + un.NGP + "," + un.id + " ],";

    @RequestMapping("/newtagvalue")
    public String geTagsValue(String tag) throws ClassNotFoundException, SQLException {
        TagData tagData = new TagData();
        String result = "[";
        List<TagData> ll = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver") ;
        Connection conn = DriverManager.getConnection("jdbc:mysql://80.241.40.230:3306/zsse", "userzsse", "Zeinet8sse") ;
        Statement stmt = conn.createStatement() ;
        String query = "SELECT "+tag+",dateandtime FROM Unit1" ;
        ResultSet rs = stmt.executeQuery(query) ;
        int status = 0;
        int numColumns = rs.getRow();
        while (rs.next()) {
            String value = rs.getString(tag);
            String date = rs.getString("dateandtime");
            tagData.setDate(date);
            tagData.setValue(value);
            ll.add(tagData);
        }
        for(TagData td : ll) {
            status++;
            result += (status<ll.size()) ? "[" + td.getDate() + "," + td.getValue() + "," + status + " ]]" : "[" + td.getDate() + "," + td.getValue() + "," + status + " ],";
        }
        rs.close();
        return result;
    }
}

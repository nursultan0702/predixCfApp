package po.lab.example.predix.timeseries.Model;


import javax.persistence.*;

@Entity
@Table(name="Unit1")
public class Unit1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String dateandtime;
    public Integer NGP;
    public Integer NPT;
    public Integer PCD;
    public Integer T5_Average;
    public Integer T5_Spread;
    public Integer Header_P;
    public Integer Header_T;
    public Integer T1;
    public Integer HPC_Suction_P;
    public Integer HPC_Suction_T;
    public Integer HPC_Disch_P;
    public Integer HPC_Disch_T;
    public Integer HPC_Eta_Nom;
    public Integer HPC_Eta_Act;
    public Integer HPC_Flow_Act;
    public Integer HPC_Flow_Std;
    public Integer T5_TC1;
    public Integer T5_TC2;
    public Integer T5_TC3;
    public Integer T5_TC4;
    public Integer T5_TC5;
    public Integer T5_TC6;
    public Integer T5_TC7;
    public Integer T5_TC8;
    public Integer T5_TC9;
    public Integer T5_TC10;
    public Integer T5_TC11;
    public Integer T5_TC12;
    public Integer HPC_Ax_Disp;
    public Integer GP_Ax_Disp;
    public Integer PT_Ax_Disp;
    public Integer Brg1_Drn_Temp;
    public Integer Brg1x_Vib;
    public Integer Brg1y_Vib;
    public Integer Brg2_3_Drn_Temp;
    public Integer Brg2x_Vib;
    public Integer Brg2y_Vib;
    public Integer Brg3x_Vib;
    public Integer Brg3y_Vib;
    public Integer Brg4_5_Drn_Temp;
    public Integer Brg4x_Vib;
    public Integer Brg4y_Vib;
    public Integer Brg5x_Vib;
    public Integer Brg5y_Vib;
    public Integer Gasfuel_Flow;
    public Integer Gasfuel_Press;

    public String getTag(String tag){
        return tag;
    }
}

import swisseph.*;

public class Sw1 {

    int pl[] = {
        0, 1, 2, 3, 4, 5, 6, 11,12
    };
    static String plNams[] = {"Sun","Moon","Budha","Sukra","Mars","Guru","Sani","Rahu","Kethu","Asc"};
    public static void main(String[] p) {
        SwissEph sw=new SwissEph();
        Sw1 sw1 = new Sw1();
        double time = (1 + (44.00/60.00)) - 5.5 ;
        SweDate sd=new SweDate(1980,12,11,time);
        /*double time = (14 + (6.00/60.00)) - 5.5 ;
        SweDate sd=new SweDate(1900,1,1,0);*/


        // In this array, the values will be returned:
        double[] res=new double[6];
        StringBuffer sbErr=new StringBuffer();
        int rc;
        //sw.swe_set_topo((77+(44.00/60.00)),(11+(22.00/60.00)),0.0D);
        sw.swe_set_sid_mode(1,0.0,0.0);
        double ayanamsa = sw.swe_get_ayanamsa(sd.getJulDay());
        double longi = res[0]- ayanamsa;

        //sw.swe_set_ephe_path("d\\:\\\\AstroSoft");
        System.out.print("Ayanamsa " + sw1.dms(ayanamsa));
        //int iflag = 0x10102 ;
        int iflag = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_SPEED  | SweConst.SEFLG_SIDEREAL;
        

        System.out.println("IFlag: "+iflag);
        int planet;
       /* double xnasc[] = new double[12];
        double xndsc[] = new double[12];
        double xperi[] = new double[12];
        double xaphe[] = new double[12];*/
        for (planet=0;planet<9;planet++)
        {
            rc=sw.swe_calc_ut(sd.getJulDay() ,
            sw1.pl[planet],
            iflag,
            res,
            sbErr);

          /*sw.swe_nod_aps_ut(sd.getJulDay(),
                          sw1.pl[planet],
                          iflag,
                          SweConst.SE_NODBIT_MEAN,
                          xnasc,
                          xndsc,
                          xperi,
                          xaphe,
                          sbErr) ;*/
        System.out.println(plNams[planet]+" --> " + sw1.dms(res[0])+" Speed  " + sw1.dms(res[3])+ " -- " );
        //  System.out.println(sbErr);
        /*for(int k=0; k < 6 ; k++){

            System.out.println(" Asc " + xnasc[k]);
            System.out.println(" Dsc " + xndsc[k]);
            System.out.println(" Peri " + xperi[k]);
            System.out.println(" Aphe " + xaphe[k]);
        }*/

        }

        double cusp[]=new double[20];
        double ac[]=new double[20];

        sw.swe_houses(sd.getJulDay(), 0x10000, (11+(22.00/60.00)),(77+(44.00/60)), 'O', cusp, ac);
        System.out.print("Sidereal Time : ");
        //for(int i=0;i<8;i++)
        System.out.print(sw1.dms(ac[2]/15));
        /*for(int i=0;i<13;i++)
            System.out.println("cusp: "+ i + "--> " + sw1.dms(cusp[i]));*/

        DblObj E = new DblObj();
        int diff = sw.swe_time_equ(sd.getJulDay() + sd.getDeltaT(),E,sbErr);
        System.out.println("\nDiff : " + diff + " E: " + sw1.dms(E.val * 24 * -1) + sbErr);
        
        //////////////// Transit routines ////////////////////
        
        iflag = SweConst.SEFLG_TRANSIT_LONGITUDE | SweConst.SEFLG_SIDEREAL;
        TransitCalculator tc = new TCPlanet(
                sw,
                SweConst.SE_MOON,
                iflag,
                300);
        
        SweDate d1 = new SweDate(2005,10,1,0.0);
        SweDate d2 = new SweDate(2006,11,1,0.0);
        
        double nextTransit = sw.getTransitUT(tc, d1.getJulDay(), false);
        
        System.out.println("Next Transit of moon ->" + SweDate.getDate(nextTransit));
        
        nextTransit = sw.getTransitUT(tc, nextTransit, false);
        
        System.out.println("Next Transit of moon ->" + SweDate.getDate(nextTransit));

    }
    private String dms(double val){
        String res = new String();
        if (val < 0){
            val = Math.abs(val);
            res = res + "-";
        }
        int deg = (int)val;
        //System.out.print(deg+" : ");
        double mindob = (val-deg)* 60;
        int min = (int)(mindob);
        int sec = (int)((mindob - min)*60);
        //System.out.println(min+" : "+sec);
        res = res + deg+" : "+min+" : "+sec;
        return res;
    }
    
    
}

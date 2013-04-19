/**
 * Vargas.java
 *
 * Created on December 14, 2002, 3:13 PM
 * @author  E. Rajasekar
 */
package app.astrosoft.core;

import java.util.EnumMap;

import app.astrosoft.beans.HousePosition;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Varga;

public class VargaCharts {

    private EnumMap<Planet, Double> planetaryPosition;
    private HousePosition housePosition;
    
    private EnumMap<Varga,EnumMap<Planet,Integer>> divChart;
 
    /** Creates a new instance of Vargas */
    public VargaCharts( EnumMap<Planet, Double> pp, HousePosition hs ) {

    	planetaryPosition = pp;
        housePosition = hs;
    }

    public EnumMap<Planet,Integer> genCharts( Varga varga ) {

        EnumMap<Planet,Integer> pos = new EnumMap<Planet,Integer>(Planet.class);

        for ( Planet p : Planet.planetsAsc() ) {

            pos.put(p, findPosition( planetaryPosition.get(p), varga.division()));
        }

        return pos;

    }

    public int findPosition( double deg, int div ) {

        int house = 0;
        double rem = 0.0;
        double pos = 0.0;
        double part = ( double ) ( 30.00 / div );
        int sign = 0;

        switch ( div ) {

        /*case 1: house = (int)(deg / 30)+1;
                break;*/
        case 0:
            house = calcBhavaPosition( deg );

            break;

        case 1:
        case 9:
            pos = deg / part;
            house = ( int ) ( pos % 12 ) + 1;

            break;

        case 2:
            rem = ( int ) deg % 30;
            house = ( int ) ( deg / 30 ) + 1;
            sign = oddEven( house );

            if ( ( rem < 15 ) && ( sign == 0 ) ) {
                house = 4;
            }

            if ( ( rem < 15 ) && ( sign > 0 ) ) {
                house = 5;
            }

            if ( ( rem >= 15 ) && ( sign == 0 ) ) {
                house = 5;
            }

            if ( ( rem >= 15 ) && ( sign > 0 ) ) {
                house = 4;
            }

            break;

        case 3:
            house = ( int ) ( deg / 30 );
            rem = deg % 30;

            if ( ( 0.0 <= rem ) && ( rem < 10.0 ) ) {
                house = house + 1;
            }

            if ( ( 10.0 <= rem ) && ( rem < 20.0 ) ) {
                house = house + 5;
            }

            if ( ( 20.0 <= rem ) && ( rem <= 30.0 ) ) {
                house = house + 9;
            }

            house = house % 12;

            break;

        case 4:
            house = ( int ) ( deg / 30 );
            rem = deg % 30;
            house = house + ( ( int ) ( rem / part ) * 3 );
            house = ( house % 12 ) + 1;

            break;

        case 7:
        case 10:
            house = ( int ) ( deg / 30 ) + 1;
            rem = deg % 30;
            pos = ( int ) ( rem / part );

            if ( oddEven( house ) == 0 ) {
                house = ( int ) ( house + pos ) + getInc( div );
            } else {
                house = ( int ) ( house + pos );
            }

            house = ( int ) ( house % 12 );

            break;

        case 20:
            house = ( int ) ( deg / 30 ) + 1;
            rem = deg % 30;
            pos = ( int ) ( rem / part );

            int i = FMD( house );

            if ( i == 9 ) {
                i = 5;
            } else if ( i == 5 ) {
                i = 9;
            }

            house = ( int ) ( i + pos ) % 12;

            break;

        case 8:
        case 16:
        case 45:
            house = ( int ) ( deg / 30 ) + 1;
            rem = deg % 30;
            pos = ( int ) ( rem / part );

            /*if(div ==8)
                System.out.println("deg "+deg+" house "+house); */
            house = ( int ) ( FMD( house ) + pos ) % 12;

            /*if(div ==8)
                System.out.println("pos "+pos+"house "+house); */
            break;

        case 24:
            house = ( int ) ( deg / 30 ) + 1;
            rem = deg % 30;
            pos = ( int ) ( rem / part );

            if ( oddEven( house ) == 0 ) {
                house = ( int ) pos + 4;
            } else {
                house = ( int ) pos + 5;
            }

            house = house % 12;

            break;

        case 27:
            house = ( int ) ( deg / 30 ) + 1;
            rem = deg % 30;
            pos = ( int ) ( rem / part );
            house = ( int ) ( FEAW( house ) + pos ) % 12;

            break;

        case 30:
            house = ( int ) ( deg / 30 ) + 1;
            rem = deg % 30;
            pos = ( int ) ( rem / part );

            if ( oddEven( house ) == 0 ) {

                if ( pos < 5 ) {
                    house = 2;
                } else if ( ( 5 <= pos ) && ( pos < 12 ) ) {
                    house = 6;
                } else if ( ( 12 <= pos ) && ( pos < 20 ) ) {
                    house = 12;
                } else if ( ( 20 <= pos ) && ( pos < 25 ) ) {
                    house = 10;
                } else if ( ( 25 <= pos ) && ( pos < 30 ) ) {
                    house = 8;
                }

            } else {

                if ( pos < 5 ) {
                    house = 1;
                } else if ( ( 5 <= pos ) && ( pos < 10 ) ) {
                    house = 11;
                } else if ( ( 10 <= pos ) && ( pos < 18 ) ) {
                    house = 9;
                } else if ( ( 18 <= pos ) && ( pos < 25 ) ) {
                    house = 3;
                } else if ( ( 25 <= pos ) && ( pos < 30 ) ) {
                    house = 7;
                }

            }

            break;

        case 40:
            house = ( int ) ( deg / 30 ) + 1;
            rem = deg % 30;
            pos = ( int ) ( rem / part );

            if ( oddEven( house ) == 0 ) {
                house = ( int ) pos + 7;
            } else {
                house = ( int ) pos + 1;
            }

            house = house % 12;

            break;

        case 60:
        case 12:
            house = ( int ) ( deg / 30 );
            rem = deg % 30;
            house = house + ( int ) ( rem / part );
            house = ( house % 12 ) + 1;

            break;

        case 5:
            house = ( int ) ( deg / 30 ) + 1;
            rem = deg % 30;
            pos = ( int ) ( rem / part ) + 1;

            if ( oddEven( house ) != 0 ) {

                switch ( ( int ) pos ) {

                case 1:
                    house = 1;

                    break;

                case 2:
                    house = 11;

                    break;

                case 3:
                    house = 9;

                    break;

                case 4:
                    house = 3;

                    break;

                case 5:
                    house = 7;

                    break;

                }

            } else {

                switch ( ( int ) pos ) {

                case 1:
                    house = 2;

                    break;

                case 2:
                    house = 6;

                    break;

                case 3:
                    house = 12;

                    break;

                case 4:
                    house = 10;

                    break;

                case 5:
                    house = 8;

                    break;

                }

            }

            break;

        case 6:
            house = ( int ) ( deg / 30 ) + 1;
            rem = deg % 30;
            pos = ( int ) ( rem / part );

            if ( oddEven( house ) == 0 ) {
                house = ( int ) pos + 7;
            } else {
                house = ( int ) pos + 1;
            }

            house = house % 12;

            break;

        case 11:
            rem = deg % 30;
            pos = ( int ) ( rem / part );
            house = ( int ) ( 12 - pos );

            break;

        }

        if ( house == 0 ) {
            house = 12;
        }

        return house;

    }

    public EnumMap<Varga,EnumMap<Planet,Integer>> getAllCharts(  ) {

    	
    	divChart = new EnumMap<Varga,EnumMap<Planet,Integer>>(Varga.class);
      
    	for ( Varga v : Varga.values() ) {

            divChart.put(v, genCharts( v ));

        }
        return divChart;
    }

    public static int oddEven( int house ) {

        return ( house % 2 );

    }

    //check for Fixed, Movable, Dual Houses
    public static int FMD( int house ) {

        int res = 0;

        switch ( house ) {

        case 1:
        case 4:
        case 7:
        case 10:
            res = 1;

            break;

        case 2:
        case 5:
        case 8:
        case 11:
            res = 5;

            break;

        case 3:
        case 6:
        case 9:
        case 12:
            res = 9;

            break;

        }

        return res;

    }

    //check for Firery, Earthy, Airy, Watery Houses
    private int FEAW( int house ) {

        int res = 0;

        switch ( house ) {

        case 1:
        case 5:
        case 9:
            res = 1;

            break;

        case 2:
        case 6:
        case 10:
            res = 4;

            break;

        case 3:
        case 7:
        case 11:
            res = 7;

            break;

        case 4:
        case 8:
        case 12:
            res = 10;

            break;

        }

        return res;

    }

    private int getInc( int div ) {

        int inc = 0;

        switch ( div ) {

        case 10:
            inc = 8;

            break;

        case 7:
            inc = 6;

            break;

        }

        return inc;

    }

    public int calcBhavaPosition( double deg ) {

        return housePosition.locateHouse(deg).ordinal()+1;
    }
    
    @Override
    public String toString() {
    	
    	return toString(divChart);
    }
    
    public static String toString(EnumMap<Varga,EnumMap<Planet,Integer>> divChart) {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for(Varga v : Varga.values()){
    		sb.append(v + " -> ");
    		for (Planet p : Planet.planetsAsc()) {
				sb.append(divChart.get(v).get(p) + " , ");
			}
    		sb.append("\n");
    	}
    	return sb.toString();
    }
    
    public static void main(String[] args) {
    	Horoscope h = new Horoscope("Raja", 11, 12, 1980, 1, 44,
				77 + (44.00 / 60.00), 11 + (22.00 / 60.00), 5.5, "Erode");
    	
    	System.out.println(VargaCharts.toString(h.getDivChart()));
	}

}

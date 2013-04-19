/**
 * AshtaVarga.java
 *
 * Created on January 17, 2003, 10:19 AM
 *
 * @author  E. Rajasekar
 * @version
 */
package app.astrosoft.core;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Vector;

import app.astrosoft.consts.AshtavargaName;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.export.Exportable;
import app.astrosoft.export.Exporter;
import app.astrosoft.ui.table.ColumnMetaData;
import app.astrosoft.ui.table.DefaultColumnMetaData;
import app.astrosoft.ui.table.MapTableRow;
import app.astrosoft.ui.table.MapTableRowHelper;
import app.astrosoft.ui.table.Table;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableDataFactory;
import app.astrosoft.ui.table.TableRowData;


public class Ashtavarga implements Exportable {

    private static int[] one =
        {
            0, 2, 6, 9, 11, 12, 18, 22, 23, 26, 27, 29, 30, 31, 32, 34, 35, 36,
            39, 41, 45, 47, 48, 55
        
        };
    private static int[] two =
        {
            0, 2, 6, 10, 18, 25, 26, 29, 30, 31, 32, 33, 34, 35, 36, 37, 39, 41,
            45, 47, 48
        
        };
    private static int[] three =
        {
            1, 3, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 19, 23, 27, 29, 32, 36,
            38, 41, 42, 43, 45, 46, 47, 49, 50, 54, 55
        
        };
    private static int[] four =
        {
            0, 2, 6, 7, 11, 12, 13, 18, 22, 25, 26, 29, 30, 31, 32, 34, 35, 36,
            39, 41, 45, 46, 47, 48, 55
        
        };
    private static int[] five =
        {
            3, 4, 10, 11, 13, 14, 16, 19, 24, 27, 29, 33, 35, 37, 38, 39, 41, 42,
            44, 43, 45, 46, 47, 52, 54, 50
        
        };
    private static int[] six =
        {
            1, 3, 4, 5, 7, 8, 9, 10, 14, 15, 16, 17, 19, 20, 21, 23, 24, 25, 27,
            28, 31, 35, 37, 38, 39, 42, 43, 49, 50, 51, 52, 53, 54, 55
        
        };
    private static int[] seven =
        { 0, 2, 5, 6, 8, 9, 11, 12, 13, 18, 22, 26, 30, 32, 33, 34, 36, 39, 48
         };
    private static int[] eight =
        {
            0, 2, 6, 8, 11, 12, 18, 21, 22, 25, 26, 28, 29, 30, 31, 32, 34, 36,
            40, 41, 44, 45, 46, 47, 48, 51
        
        };
    private static int[] nine =
        {
            0, 2, 3, 4, 6, 10, 13, 22, 24, 26, 27, 29, 30, 32, 33, 35, 37, 39,
            41, 42, 43, 44, 45, 46, 47, 51
        
        };
    private static int[] ten =
        {
            0, 1, 2, 3, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 18, 20, 22, 23, 25,
            26, 27, 30, 31, 32, 34, 35, 36, 37, 39, 44, 45, 46, 48, 50, 51, 55
        
        };
    private static int[] eleven =
        {
            0, 1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 34, 35, 36, 37,
            39, 33, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54,
            55
        
        };
    private static int[] twelve =
        { 3, 5, 7, 12, 20, 21, 24, 27, 28, 38, 40, 41, 42, 51, 52, 53, 50
         };
    private static EnumMap<AshtavargaName, Integer> count;
    
    private static int[] rasiGunaVals =
        { 7, 10, 8, 4, 10, 5, 7, 8, 9, 5, 11, 12
         };
    private static int[] grahaGunaVals = { 5, 5, 8, 5, 10, 7, 5
         };
   
    Vector<int[]> v;
    
    private EnumMap<AshtavargaName, EnumMap<Rasi, Integer>> ashtavarga;
    private EnumMap<AshtavargaName, EnumMap<Rasi, Integer>> trikonaReduced;
    private EnumMap<AshtavargaName, EnumMap<Rasi, Integer>> ekathipathiyaReduced;
    
    private EnumMap<AshtavargaName, Integer> rasiGuna;
    private EnumMap<AshtavargaName, Integer> grahaGuna;
    private EnumMap<Planet, Integer> planetPos;

    /** Creates new AshtaVarga */
    public Ashtavarga( EnumMap<Planet, Integer> pos ) {

    	planetPos = pos;
        v = new Vector<int []>(  );
        v.add( one );
        v.add( two );
        v.add( three );
        v.add( four );
        v.add( five );
        v.add( six );
        v.add( seven );
        v.add( eight );
        v.add( nine );
        v.add( ten );
        v.add( eleven );
        v.add( twelve );
        computeAstavarga(  );
        calcSarvastaVarga(  );
        doTrikonaReduction(  );
        doEkathipathiyaReduction(  );
        calcGunakaras(  );
        initCount();
    }

    private void initCount() {
    	
    	count = new EnumMap<AshtavargaName, Integer>(AshtavargaName.class);
    	count.put(AshtavargaName.Sun, 48);
    	count.put(AshtavargaName.Moon, 49);
    	count.put(AshtavargaName.Mars, 39);
    	count.put(AshtavargaName.Mercury, 54);
    	count.put(AshtavargaName.Jupiter, 56);
    	count.put(AshtavargaName.Venus, 52);
    	count.put(AshtavargaName.Saturn, 39);
	}

	private int getParal( int houseNo, int vargaNo ) {

        int paral = 0;
        int[] varga = ( int[] ) ( v.elementAt( houseNo ) );

        for ( int i = 0; i < varga.length; i++ ) {

            if ( varga[i] == vargaNo ) {

                paral = 1;

                break;

            }

        }
        return paral;
    }

    private void computeAstavarga(  ) {

    	ashtavarga = new EnumMap<AshtavargaName, EnumMap<Rasi, Integer>> (AshtavargaName.class);
    	
        int diff = 0;
        int paral = 0;
        
        for ( Planet planet : Planet.majorPlanets() ) {

        	EnumMap<Rasi, Integer> paralsInHouse = initParals();
            for ( Rasi house : Rasi.values() ) {

            	int total = 0;
                for ( Planet subpla : Planet.majorPlanetsAsc() ) {

                    diff = ( (house.ordinal() + 1) - planetPos.get(subpla) + 12 ) % 12;
                    paral = getParal( diff, ( 8 * planet.ordinal() ) + subpla.ashtavargaNo() );

                    total = total + paral;

                }
                paralsInHouse.put(house, total);
             }
            ashtavarga.put(AshtavargaName.ofPlanet(planet), paralsInHouse);
        }

    }

    private void calcSarvastaVarga(  ) {

        int sum = 0;
        EnumMap<Rasi, Integer> paralsInHouse = new EnumMap<Rasi, Integer>(Rasi.class);
        
        for ( Rasi h : Rasi.values() ) {

        	sum = 0;
            for ( Planet p : Planet.majorPlanets() ) {

                sum = sum + ashtavarga.get(AshtavargaName.ofPlanet(p)).get(h);

            }

            paralsInHouse.put(h, sum);
        }
        
        ashtavarga.put(AshtavargaName.SarvaAshtavarga, paralsInHouse);

    }

    private void doTrikonaReduction(  ) {

    	int i, j, k, min;
    	
    	trikonaReduced = new EnumMap<AshtavargaName, EnumMap<Rasi, Integer>> (AshtavargaName.class);
    	
        for ( AshtavargaName ashtavargaName : ashtavarga.keySet() ) {
        	
        	EnumMap<Rasi, Integer> paralsInHouse = initParals();
        	
            EnumMap<Rasi, Integer> ashtavargaParals = ashtavarga.get(ashtavargaName);

            for ( Rasi h = Rasi.Mesha; (h != Rasi.Simha); h = h.next()  ) {
            	
	            Rasi []trines = h.trines();
	            
	            if (ashtavargaName == AshtavargaName.SarvaAshtavarga) {
	            	i = ashtavargaParals.get(trines[0]) % 12;
	            	j = ashtavargaParals.get(trines[1]) % 12;
	            	k = ashtavargaParals.get(trines[2]) % 12;
	            }else{
	            	i = ashtavargaParals.get(trines[0]);
	            	j = ashtavargaParals.get(trines[1]);
	            	k = ashtavargaParals.get(trines[2]);
	            }
	            
	            if ( ( i == j ) && ( j == k ) ) {
	
	                for(Rasi t : trines){
	                	paralsInHouse.put(t, 0);
	                }
	
	            } else if ( 
	                ( ( i == 0 ) && ( j == 0 ) ) || ( ( i == 0 ) && ( k == 0 ) )
	                    || ( ( k == 0 ) && ( j == 0 ) ) ) {
	
	            	for(Rasi t : trines){
	                	paralsInHouse.put(t, 0);
	                }
	
	            } else {
	
	            	
	                min = Math.min( Math.min( i, j ), k );
	
	                if ( i != min ) {
	
	                	paralsInHouse.put(trines[0], i - min);
	
	                }
	
	                if ( j != min ) {
	
	                	paralsInHouse.put(trines[1], j - min);
	
	                }
	
	                if ( k != min ) {
	
	                	paralsInHouse.put(trines[2],  k - min);
	
	                }
	                
	            }
            }

            trikonaReduced.put(ashtavargaName, paralsInHouse);
        }

    }

    private void doEkathipathiyaReduction(  ) {

        Rasi i;
        Rasi j;

        ekathipathiyaReduced = new EnumMap<AshtavargaName, EnumMap<Rasi, Integer>> (AshtavargaName.class);
        
        for ( AshtavargaName ashtavargaName : ashtavarga.keySet()) {

        	i = Rasi.Makara;
            j = Rasi.Kumbha;
            
            EnumMap<Rasi, Integer> paralsInHouse = initParals();
            EnumMap<Rasi, Integer> trikonaParals = trikonaReduced.get(ashtavargaName);

            for ( ; (i != Rasi.Kataka); i = i.previous(), j = j.next() ) {

            	int val1 = trikonaParals.get(i);
            	int val2 = trikonaParals.get(j);
            	
            	if ( ( i == Rasi.Simha ) && ( j == Rasi.Kataka ) ) {

                	paralsInHouse.put(i,  val1);
                	paralsInHouse.put(j,  val2);
                }

            	else if ( 
                    ( ( val1 == 0 ) || ( val2 == 0 ) )
                        || ( hasPlanet( i ) && hasPlanet( j ) ) ) {

                	paralsInHouse.put(i,  val1);
                	paralsInHouse.put(j,  val2);
                }

            	else if ( hasPlanet( i ) && !hasPlanet( j ) ) {

                    if ( val1 >= val2 ) {

                    	paralsInHouse.put(i,  val1);
                    	paralsInHouse.put(j,  0);

                    } else if ( val1 < val2 ) {

                    	paralsInHouse.put(i,  val1);
                    	paralsInHouse.put(j,  val1);

                    }

                } else if ( !hasPlanet( i ) && hasPlanet( j ) ) {

                    if ( val2 >= val1 ) {

                    	paralsInHouse.put(i,  0);
                    	paralsInHouse.put(j,  val2);

                    } else if ( val2 < val1 ) {

                    	paralsInHouse.put(i,  val2);
                    	paralsInHouse.put(j,  val2);

                    }

                } else { // No planet in both

                    if ( val1 == val2 ) {

                    	paralsInHouse.put(i,  0);
                    	paralsInHouse.put(j,  0);

                    } else {

                        if ( val1 < val2 ) {

                        	paralsInHouse.put(i,  val1);
                        	paralsInHouse.put(j,  val1);

                        } else {

                        	paralsInHouse.put(i,  val2);
                        	paralsInHouse.put(j,  val2);

                        }

                    }

                }
                
            }
            ekathipathiyaReduced.put(ashtavargaName, paralsInHouse);
        }
    }

    private boolean hasPlanet( Rasi h ) {

        boolean res = false;

        int house = h.ordinal() + 1;

        for ( Planet p : Planet.majorPlanetsAsc() ){

            if ( planetPos.get(p) == house ) {

                res = true;

                break;

            }
        }
        return res;

    }

    private void calcGunakaras(  ) {

    	rasiGuna = new EnumMap<AshtavargaName, Integer>(AshtavargaName.class);
        grahaGuna = new EnumMap<AshtavargaName, Integer>(AshtavargaName.class);
        
        int result = 0;

        for ( AshtavargaName ashtavargaName : ashtavarga.keySet() ) {

        	EnumMap<Rasi, Integer> ekaParals = ekathipathiyaReduced.get(ashtavargaName);
        	
            result = 0;

            for ( Rasi i : Rasi.values() ) {

                result = result + ( rasiGunaVals[i.ordinal()] * ekaParals.get(i) );
                
            }
            rasiGuna.put(ashtavargaName ,result);
            result = 0;

            for ( Planet j : Planet.majorPlanets() ) {

                result =
                    result
                    + ( grahaGunaVals[j.ordinal()] * ekaParals.get(Rasi.ofIndex(planetPos.get(j) - 1)) );

            }

            grahaGuna.put(ashtavargaName ,result);

        }

    }

    private EnumMap<Rasi, Integer> initParals(){
    	
    	EnumMap<Rasi, Integer> vargas = new EnumMap<Rasi, Integer> (Rasi.class);
    	for(Rasi h : Rasi.values()){
    		vargas.put(h, 0);
    	}
    	
    	return vargas;
    	
    }
    public static int getCount(AshtavargaName ashtavarga) {
    	
    	if (count.containsKey(ashtavarga)){
    		return count.get(ashtavarga);
    	}else{
    		return -1;
    	}
    
	}
    
    public Table getGunaTable(final AshtavargaName ashtavargaName){
    
    	final DefaultColumnMetaData colMetaData = new DefaultColumnMetaData(AstrosoftTableColumn.keyvalCols());
    	colMetaData.localizeColumns();
    	
    	MapTableRowHelper helper = new MapTableRowHelper(colMetaData);
    	
    	List<MapTableRow> rows = new ArrayList<MapTableRow>();
    	
    	int rasiGunaVal = rasiGuna.get(ashtavargaName);
		int grahaGunaVal = grahaGuna.get(ashtavargaName);
		
    	rows.add(helper.createRow(DisplayStrings.RASI_GUNA_STR, rasiGunaVal));
    	rows.add(helper.createRow(DisplayStrings.GRAHA_GUNA_STR, grahaGunaVal));
    	rows.add(helper.createRow(DisplayStrings.SUTHDHA_BINDU_STR, (rasiGunaVal + grahaGunaVal)));
    	
    	final TableData<MapTableRow> data = TableDataFactory.getTableData(rows);
    	
    	Table gunaTable = new Table(){

			public TableData<? extends TableRowData> getTableData() {
				return data;
			}

			public ColumnMetaData getColumnMetaData() {
				return colMetaData;
			}
    		
    	};
    	
    	return gunaTable;
    }
    
    public EnumMap<Rasi, Integer> getAshtavarga(AshtavargaName name) {
		return ashtavarga.get(name);
	}
    
    public EnumMap<Rasi, Integer> getTrikona(AshtavargaName name) {
		return trikonaReduced.get(name);
	}
    
    public EnumMap<Rasi, Integer> getEkathipathiya(AshtavargaName name) {
		return ekathipathiyaReduced.get(name);
	}
    
    /**
	 * @return Returns the grahaGuna.
	 */
	public int getGrahaGuna(AshtavargaName ast) {
		return grahaGuna.get(ast);
	}
	
	/**
	 * @return Returns the rasiGuna.
	 */
	public int getRasiGuna(AshtavargaName ast) {
		return rasiGuna.get(ast);
	}
    
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("Ashtavarga\n");
    	
        int sum = 0;
        int total = 0;

        for ( AshtavargaName ashtavargaName : ashtavarga.keySet() ) {

        	sb.append( ashtavargaName + " Astavarga \n\n" );
            sum = 0;

            for ( Rasi j : Rasi.values() ) {

                sum = sum + ashtavarga.get(ashtavargaName).get(j);
                
                sb.append(j + " -->  " + ashtavarga.get(ashtavargaName).get(j) + "\n");
            }

            sb.append("Sum " + sum + "\n\n");
            total = total + sum;
            sb.append( "RasiGuna " + rasiGuna.get(ashtavargaName) +  "\n");
            sb.append( "GrahaGuna " + grahaGuna.get(ashtavargaName) + "\n");

        }

        sb.append("\n");
        sb.append( "Total " + total + "\n" );

    	return sb.toString();
    }
    
    public static void main( String[] args ) {

    	EnumMap<Planet, Integer> pos = new EnumMap<Planet, Integer>(Planet.class);
    	
    	pos.put(Planet.Sun, 8);
    	pos.put(Planet.Moon, 10);
    	pos.put(Planet.Mars, 9);
    	pos.put(Planet.Mercury, 8);
    	pos.put(Planet.Jupiter, 6);
    	pos.put(Planet.Venus, 7);
    	pos.put(Planet.Saturn, 6);
    	pos.put(Planet.Rahu, 4);
    	pos.put(Planet.Ketu, 10);
    	pos.put(Planet.Ascendant, 6);
    	
        Ashtavarga av = new Ashtavarga( pos );
     
        System.out.println(av);

    }

	public void doExport(Exporter e) {
		e.export(this);
	}

}

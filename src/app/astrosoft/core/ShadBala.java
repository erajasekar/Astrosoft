/**
 * ShadBala.java
 *
 * Created on May 3, 2003, 5:29 PM
 * @author  E. Rajasekar.
 */
package app.astrosoft.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import app.astrosoft.beans.BirthData;
import app.astrosoft.beans.HousePosition;
import app.astrosoft.beans.PlanetaryInfo;
import app.astrosoft.beans.HousePosition.Bhava;
import app.astrosoft.consts.AstroConsts;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.Ayanamsa;
import app.astrosoft.consts.Paksha;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.Roman;
import app.astrosoft.consts.Varga;
import app.astrosoft.consts.Planet.Character;
import app.astrosoft.ui.table.DefaultColumnMetaData;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableRowData;
import app.astrosoft.util.AstroUtil;
import app.astrosoft.util.ComparableEntry;
import app.astrosoft.util.Mod;
import app.astrosoft.util.SwissHelper;
import app.astrosoft.util.Utils;
import app.astrosoft.export.Exportable;
import app.astrosoft.export.Exporter;

public class ShadBala implements Exportable {

	private static final Logger log = Logger.getLogger(ShadBala.class.getName());

	private static enum BhavaType {
		Poorva, Uttra
	};

	public static enum Bala {
		ResidentialStrength,
		SthanaBala,
		OchchaBala,
		SaptavargajaBala,
		OjaYugmarasyamsaBala,
		KendraBala,
		DrekkanaBala,
		DigBala,
		PakshaBala,
		TribhagaBala,
		AbdaBala,
		MasaBala,
		VaraBala,
		HoraBala,
		NatonnataBala,
		AyanaBala,
		YuddhaBala,
		KalaBala,
		DrikBala,
		ChestaBala,
		NaisargikaBala,
		IshtaBala,
		KashtaBala,
		ShadBala,
		BhavaBala,
		BhavaAdhipathiBala,
		BhavaDigBala,
		BhavaDrishtiBala;

		public static EnumSet<Bala> planetBalas() {
			return EnumSet.of(ResidentialStrength, SthanaBala, KalaBala,
					DigBala, DrikBala, ChestaBala, NaisargikaBala);
		}

		public static EnumSet<Bala> bhavaBalas() {
			return EnumSet.of(BhavaAdhipathiBala, BhavaDigBala,
					BhavaDrishtiBala);
		}

		public static EnumSet<Bala> sthanaBalas() {
			return EnumSet.of(OchchaBala, SaptavargajaBala,
					OjaYugmarasyamsaBala, KendraBala, DrekkanaBala);
		}

		public static EnumSet<Bala> kalaBalas() {
			return EnumSet.of(AbdaBala, MasaBala, VaraBala, HoraBala,
					PakshaBala, TribhagaBala, NatonnataBala, AyanaBala,
					YuddhaBala);
		}

		public static List<AstrosoftTableColumn> toTableColumn(
				EnumSet<Bala> balas) {
			List<AstrosoftTableColumn> cols = new ArrayList<AstrosoftTableColumn>();
			for (Bala b : balas) {
				cols.add(AstrosoftTableColumn.valueOf(b.name()));
			}
			return cols;
		}
	}


	private BirthData birthData;
	private double ayanamsa;

	private double sunrise;

	private double sunset;

	private Paksha pak;

	private int[][] Rel;
	private int birthDate;
	private int birthMonth;
	private int birthYear;
	private Planet VaraAdipathi;
	private Calendar birthDay;
	private long epochDays;
	private long Ahargana;
	private boolean birthDayNight;

	private HousePosition housePosition;

	private EnumMap<Planet, Double> planetPosition;
	private EnumMap<Planet, Integer> planetLocation;
	private EnumMap<Planet, Bhava> planetBhava;
	private EnumMap<Varga,EnumMap<Planet,Integer>> divChart;
	private Map<Planet, Boolean> planetCharacter;

	private EnumMap<Bala, EnumMap<Planet, Double>> PlanetBala;
	private EnumMap<Bala, ArrayList<Double>> BhavaBala;
	private EnumMap<Planet, Double> StrengthPer = new EnumMap<Planet, Double>(
			Planet.class);

	private EnumMap<Planet, Integer> ShadBalaRank;
	private ArrayList<Integer> BhavaBalaRank;

	private TableData<PlanetBalaRow> planetBalaTableData;

	private DefaultColumnMetaData planetBalaColumnMetaData;

	private DefaultColumnMetaData sthanaBalaColumnMetaData;

	private DefaultColumnMetaData kalaBalaColumnMetaData;

	private TableData<BhavaBalaRow> bhavaBalaTableData;

	private DefaultColumnMetaData bhavaBalaColumnMetaData;

	/**
	 * Creates a new instance of ShadBala
	 *
	 * @param paksha
	 */
	public ShadBala(PlanetaryInfo planetaryInfo, HousePosition housePosition,
			BirthData birthData, double ayanamsa, double sunrise,
			double sunset, Paksha paksha) {

		if (birthData.year() < 1900){
			throw new IllegalArgumentException("WARNING: Year should be less than 1900");
		}
		this.birthData = birthData;
		this.housePosition = housePosition;
		this.planetPosition = planetaryInfo.getPlanetPosition();
		this.planetLocation = planetaryInfo.getPlanetLocation();
		this.planetBhava = planetaryInfo.getPlanetBhava();
		this.divChart = planetaryInfo.getDivChart();
		this.planetCharacter = planetaryInfo.getPlanetCharacter();
		this.ayanamsa = ayanamsa;
		this.sunrise = sunrise;
		this.sunset = sunset;
		this.pak = paksha;
		

		birthDay = new GregorianCalendar();
		birthDay.setTime(birthData.birthDay().getTime());

		if (birthData.birthTime() < sunrise) {
			birthDay.add(Calendar.DATE, -1);
		}

		birthDate = birthDay.get(Calendar.DATE);
		birthMonth = birthDay.get(Calendar.MONTH) + 1;
		birthYear = birthDay.get(Calendar.YEAR);

		if ((birthData.birthTime() > sunrise)
				&& (birthData.birthTime() < sunset)) {
			birthDayNight = true;
		} else {
			birthDayNight = false;
		}

		Rel = new int[7][7];

		calcBalas();

	}

	private void calcBalas() {

		PlanetBala = new EnumMap<Bala, EnumMap<Planet, Double>>(Bala.class);
		double[] MinStrength = {300.0, 360.0, 300.0, 420.0, 390.0, 330.0, 300.0};

		PlanetBala.put(Bala.ResidentialStrength, calcResidentialStrength());
		PlanetBala.put(Bala.SthanaBala, calcSthanaBala());

		calcKalaBala();
		PlanetBala.put(Bala.DigBala, calcDigBala());
		PlanetBala.put(Bala.DrikBala, calcDrikBala());
		PlanetBala.put(Bala.ChestaBala, calcChestabala());
		PlanetBala.put(Bala.NaisargikaBala, calcNaisargikaBala());
		calcIshtaKashtaBala();

		EnumMap<Planet, Double> ShadBala = new EnumMap<Planet, Double>(
				Planet.class);

		for (Planet p : Planet.majorPlanets()) {

			double totalShadBala = PlanetBala.get(Bala.SthanaBala).get(p)
					+ PlanetBala.get(Bala.DigBala).get(p)
					+ PlanetBala.get(Bala.KalaBala).get(p)
					+ PlanetBala.get(Bala.NaisargikaBala).get(p)
					+ PlanetBala.get(Bala.DrikBala).get(p);
			if (!(p == Planet.Sun || p == Planet.Moon)) {
				totalShadBala = totalShadBala
						+ PlanetBala.get(Bala.ChestaBala).get(p);
			}

			ShadBala.put(p, totalShadBala);
		}

		PlanetBala.put(Bala.ShadBala, ShadBala);

		for (Planet p : Planet.majorPlanets()) {

			double percent = (ShadBala.get(p) / MinStrength[p.ordinal()]) * 100;
			StrengthPer.put(p, percent);

		}
		calcBhavaBalas();
		calcRanks();

	}

	private EnumMap<Planet, Double> calcNaisargikaBala() {

		EnumMap<Planet, Double> NaisargikaBala = new EnumMap<Planet, Double>(
				Planet.class);

		NaisargikaBala.put(Planet.Sun, 60.00);
		NaisargikaBala.put(Planet.Moon, 51.43);
		NaisargikaBala.put(Planet.Mars, 17.14);
		NaisargikaBala.put(Planet.Mercury, 25.70);
		NaisargikaBala.put(Planet.Jupiter, 34.28);
		NaisargikaBala.put(Planet.Venus, 42.85);
		NaisargikaBala.put(Planet.Saturn, 8.57);

		return NaisargikaBala;
	}

	private boolean calcSubaPapa(Planet pl) {

		boolean res = false;

		if (pl == Planet.Mercury) {

				res = true; // Checked for house & not degrees 

				int mercHouse = divChart.get(Varga.Rasi).get(Planet.Mercury);

				for (Planet p : Planet.allPlanets()) {

					if (divChart.get(Varga.Rasi).get(p) == mercHouse) {

						if ((p == Planet.Sun) || (p == Planet.Mars)
								|| (p == Planet.Saturn) || (p == Planet.Rahu)
								|| (p == Planet.Ketu)) {
							res = false;
						}

					}
				}
		}else {
			res = planetCharacter.get(pl);
		}
		//System.out.println(pl + " : " + res);
		return res;

	}
	
	/*private boolean calcSubaPapa(Planet pl) {

		boolean res = false;

		Character planetCharacter = pl.character();

		if (planetCharacter == Character.PAPA) {
			res = false;
		} else if (planetCharacter == Character.SUBA) {
			res = true;
		} else {
			if (pl == Planet.Moon) {
				res = pak.isShukla();
			} else if (pl == Planet.Mercury) {

				res = true; // Checked for house & not degrees 

				int mercHouse = divChart.get(Varga.Rasi).get(Planet.Mercury);

				for (Planet p : Planet.allPlanets()) {

					if (divChart.get(Varga.Rasi).get(p) == mercHouse) {

						if ((p == Planet.Sun) || (p == Planet.Mars)
								|| (p == Planet.Saturn) || (p == Planet.Rahu)
								|| (p == Planet.Ketu)) {
							res = false;
						}

						// System.out.println("Merc House :" + mercHouse + " - "
						// + i
						// + " - " + res) ;
					}

				}

			}
		}
		
		//System.out.println(pl + " : " + res);
		return res;

	}*/

	private EnumMap<Planet, Double> calcResidentialStrength() {

		double arc = 0;
		double length = 0;
		BhavaType bhavaType;

		EnumMap<Planet, Double> ResidentialStrength = new EnumMap<Planet, Double>(
				Planet.class);

		for (Planet p : Planet.allPlanets()) {

			bhavaType = poorvaUttraBhava(planetPosition.get(p), planetBhava
					.get(p));

			if (bhavaType == BhavaType.Poorva) { // Poorva Bhava
				arc = planetPosition.get(p) - planetBhava.get(p).start();
				length = planetBhava.get(p).mid() - planetBhava.get(p).start();

			} else if (bhavaType == BhavaType.Uttra) {

				arc = planetBhava.get(p).end() - planetPosition.get(p);
				length = planetBhava.get(p).end() - planetBhava.get(p).mid();

			} else {
				log.severe("Residential Strength Error, invalid bhava type " + bhavaType);
			}

			Mod mod = new Mod(360);
			arc = mod.correct(arc);
			length = mod.correct(length);

			ResidentialStrength.put(p, (arc * 100) / length);

		}
		return ResidentialStrength;

	}

	private BhavaType poorvaUttraBhava(double pos, Bhava bhava) {

		BhavaType type = null;

		if ((pos >= bhava.start()) && (pos <= bhava.mid())) {
			type = BhavaType.Poorva;
		} else if ((pos >= bhava.mid()) && (pos <= bhava.end())) {
			type = BhavaType.Uttra;
		}// TODO: CONJUCTION YET TO TEST
		else {
			log.info("Residential Strengh! Need to verify");
			double length = bhava.length();
			double start = bhava.start();
			double mid = start + (length / 2);
			double end = mid + (length / 2);
			
			log.info("length " + length);
			log.info("start " + start);
			log.info("mid " + mid);
			log.info("end " + end);
			log.info("pos " + pos);

			if (mid <= 360.0) {
				if (pos >= start && pos <= mid) {
					type = BhavaType.Poorva;
				} else {
					type = BhavaType.Uttra;
				}
			} //TODO: Else part verified for suba hor..still if part needs to be verified
			else {
				pos = pos + 360;
				if (pos >= mid && pos <= end) {
					type = BhavaType.Uttra;
				} else {
					type = BhavaType.Poorva;
				}
			}
			
			log.info("type " + type);
		}

		return type;

	}

	private EnumMap<Planet, Double> calcSthanaBala() {

		PlanetBala.put(Bala.OchchaBala, calcOchchaBala());
		PlanetBala.put(Bala.SaptavargajaBala, calcSaptavargajaBala());
		PlanetBala.put(Bala.OjaYugmarasyamsaBala, calcOjaYugmarasyamsaBala());
		PlanetBala.put(Bala.KendraBala, calcKendraBala());
		PlanetBala.put(Bala.DrekkanaBala, calcDrekkanaBala());

		EnumMap<Planet, Double> SthanaBala = new EnumMap<Planet, Double>(
				Planet.class);

		for (Planet p : Planet.majorPlanets()) {

			double total = 0;
			for (Bala b : Bala.sthanaBalas()) {
				total = total + (PlanetBala.get(b).get(p));
			}
			SthanaBala.put(p, total);
		}

		PlanetBala.put(Bala.SthanaBala, SthanaBala);

		return SthanaBala;
	}

	private EnumMap<Planet, Double> calcOchchaBala() {

		double[] debiliPos = {190.0, 213.0, 118.0, 345.0, 275.0, 177.0, 20.0};

		EnumMap<Planet, Double> OchchaBala = new EnumMap<Planet, Double>(
				Planet.class);
		for (Planet p : Planet.majorPlanets()) {

			double balaVal = Math.abs(planetPosition.get(p)
					- debiliPos[p.ordinal()]) / 3;

			if (balaVal > 60.00) {
				balaVal = 120.00 - balaVal;
			}

			OchchaBala.put(p, balaVal);
		}

		return OchchaBala;
	}

	private EnumMap<Planet, Double> calcOjaYugmarasyamsaBala() {

		int rasiSign;
		int navSign;

		EnumMap<Planet, Double> OjaYugmarasyamsaBala = new EnumMap<Planet, Double>(
				Planet.class);

		for (Planet p : Planet.majorPlanets()) {

			rasiSign = VargaCharts.oddEven(divChart.get(Varga.Rasi).get(p));
			navSign = VargaCharts.oddEven(divChart.get(Varga.Navamsa).get(p));

			if ((p == Planet.Sun) || (p == Planet.Mars)
					|| (p == Planet.Mercury) || (p == Planet.Jupiter)
					|| (p == Planet.Saturn)) {
				OjaYugmarasyamsaBala.put(p, (double) (rasiSign * 15)
						+ (navSign * 15));
			}

			if ((p == Planet.Moon) || (p == Planet.Venus)) {
				OjaYugmarasyamsaBala.put(p, (double) ((15 * ((rasiSign == 0)
						? 1
						: 0)) + (15 * ((navSign == 0) ? 1 : 0))));
			}

		}

		return OjaYugmarasyamsaBala;

	}

	private EnumMap<Planet, Double> calcKendraBala() {

		EnumMap<Planet, Double> KendraBala = initBala();
		for (Planet p : Planet.majorPlanets()) {

			switch (planetLocation.get(p)) {

				case 1 :
				case 4 :
				case 7 :
				case 10 :
					KendraBala.put(p, 60.0);

					break;

				case 2 :
				case 5 :
				case 8 :
				case 11 :
					KendraBala.put(p, 30.0);

					break;

				case 3 :
				case 6 :
				case 9 :
				case 12 :
					KendraBala.put(p, 15.0);

					break;

			}

		}

		return KendraBala;

	}

	private EnumMap<Planet, Double> calcDrekkanaBala() {

		int drek = 0;

		EnumMap<Planet, Double> DrekkanaBala = initBala();

		for (Planet p : Planet.majorPlanets()) {

			drek = (int) ((planetPosition.get(p) % 30) / 10) + 1;

			if (((p == Planet.Sun) || (p == Planet.Mars) || (p == Planet.Jupiter))
					&& (drek == 1)) {
				DrekkanaBala.put(p, 15.0);
			}

			if (((p == Planet.Mercury) || (p == Planet.Saturn)) && (drek == 2)) {
				DrekkanaBala.put(p, 15.0);
			}

			if (((p == Planet.Moon) || (p == Planet.Venus)) && (drek == 3)) {
				DrekkanaBala.put(p, 15.0);
			}

		}

		return DrekkanaBala;

	}

	private EnumMap<Planet, Double> calcSaptavargajaBala() {

		calcRelationShips();

		EnumMap<Planet, Double> SaptavargajaBala = new EnumMap<Planet, Double>(
				Planet.class);

		for (Planet p : Planet.majorPlanets()) {

			SaptavargajaBala.put(p, (SaptavargajaBala(Varga.Rasi, p)
					+ SaptavargajaBala(Varga.Hora, p) + SaptavargajaBala(Varga.Drekkana, p)
					+ SaptavargajaBala(Varga.Saptamsa, p) + SaptavargajaBala(Varga.Navamsa, p)
					+ SaptavargajaBala(Varga.Dwadasamsa, p) + SaptavargajaBala(Varga.Trimshamsa, p)));
		}

		return SaptavargajaBala;

	}

	private double SaptavargajaBala(Varga varga, Planet pl) {

		Rasi house;
		Planet owner;
		double bala = 0.0;

		house = Rasi.ofIndex(divChart.get(varga).get(pl) - 1);
		owner = house.owner();

		if (pl == owner) {

			if ((varga == Varga.Rasi) && owner.isMoolaTrikona(house)) {
				bala = 45.0;
			} else {
				bala = 30.0;
			}

		} else {

			switch (Rel[pl.ordinal()][owner.ordinal()]) {

				case 2 :
					bala = 22.5;

					break;

				case 1 :
					bala = 15.0;

					break;

				case 0 :
					bala = 07.5;

					break;

				case -1 :
					bala = 3.75;

					break;

				case -2 :
					bala = 1.875;

					break;

			}

		}
		return bala;
	}

	private void calcRelationShips() {

		int dist = 0;

		for (Planet i : Planet.majorPlanets()) {

			for (Planet j : Planet.majorPlanets()) {

				if (i == j) {
					Rel[i.ordinal()][j.ordinal()] = 2;
				} else {

					dist = planetLocation.get(j) - planetLocation.get(i) + 1;

					if (dist <= 0) {
						dist += 12;
					}

					if ((dist == 2) || (dist == 3) || (dist == 4)
							|| (dist == 10) || (dist == 11) || (dist == 12)) {
						Rel[i.ordinal()][j.ordinal()] = 1;
					} else {
						Rel[i.ordinal()][j.ordinal()] = -1;
					}

				}

				Rel[i.ordinal()][j.ordinal()] = Rel[i.ordinal()][j.ordinal()]
						+ AstroConsts.permanentRel(i, j);

			}

		}

	}

	private EnumMap<Planet, Double> calcDigBala() {

		double powerLessPos = 0.0;
		double arc = 0.0;

		EnumMap<Planet, Double> DigBala = new EnumMap<Planet, Double>(
				Planet.class);

		for (Planet p : Planet.majorPlanets()) {

			if ((p == Planet.Sun) || (p == Planet.Mars)) {
				powerLessPos = housePosition.getBhava(4).mid();
			}

			if ((p == Planet.Jupiter) || (p == Planet.Mercury)) {
				powerLessPos = housePosition.getBhava(7).mid();
			}

			if ((p == Planet.Moon) || (p == Planet.Venus)) {
				powerLessPos = housePosition.getBhava(10).mid();
			}

			if (p == Planet.Saturn) {
				powerLessPos = housePosition.getBhava(1).mid();
			}

			arc = Math.abs(planetPosition.get(p) - powerLessPos);

			if (arc > 180.00) {
				arc = 360.0 - arc;
			}

			DigBala.put(p, arc / 3);

		}

		return DigBala;

	}

	private void calcKalaBala() {

		calcAhargana();
		PlanetBala.put(Bala.AbdaBala, calcAbdaBala());
		PlanetBala.put(Bala.MasaBala, calcMasaBala());
		PlanetBala.put(Bala.VaraBala, calcVaraBala());
		PlanetBala.put(Bala.PakshaBala, calcPakshaBala());
		PlanetBala.put(Bala.TribhagaBala, calcTribhagaBala());
		PlanetBala.put(Bala.HoraBala, calcHoraBala());
		PlanetBala.put(Bala.NatonnataBala, calcNatonnataBala());
		PlanetBala.put(Bala.AyanaBala, calcAyanaBala());
		PlanetBala.put(Bala.YuddhaBala, calcYuddhaBala());

	}

	private EnumMap<Planet, Double> calcVaraBala() {

		VaraAdipathi = Planet.ofIndex((int) ((Ahargana + 2) % 7));
		EnumMap<Planet, Double> VaraBala = initBala();
		VaraBala.put(VaraAdipathi, 45.0);

		return VaraBala;
	}

	private EnumMap<Planet, Double> calcAbdaBala() {

		Planet AbdaAdipathi = Planet
				.ofIndex((int) ((((Ahargana / 360) * 3) + 3) % 7));

		EnumMap<Planet, Double> AbdaBala = initBala();
		AbdaBala.put(AbdaAdipathi, 15.0);

		return AbdaBala;
		// System.out.println("AbdaAdipathi: " + AbdaAdipathi);
	}

	private EnumMap<Planet, Double> calcMasaBala() {

		Planet MasaAdipathi = Planet
				.ofIndex((int) ((((Ahargana / 30) * 2) + 3) % 7));

		EnumMap<Planet, Double> MasaBala = initBala();
		MasaBala.put(MasaAdipathi, 30.0);

		return MasaBala;
	}

	private void calcAhargana() {

		int[] monthEnds = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304,
				334, 365};
		int yrdiff = birthYear - 1900;

		epochDays = ((yrdiff * 365) + (yrdiff / 4) + monthEnds[birthMonth - 1])
				- 1 + birthDate;
		Ahargana = epochDays + 26543;

		// System.out.println("Epoch Days: " + epochDays + " Ahargana: " +
		// Ahargana);
	}

	private EnumMap<Planet, Double> calcPakshaBala() {

		EnumMap<Planet, Double> PakshaBala = new EnumMap<Planet, Double>(
				Planet.class);

		double arc = Math.abs(planetPosition.get(Planet.Moon)
				- planetPosition.get(Planet.Sun));

		if (arc > 180.0) {
			arc = 360 - arc;
		}

		double subaBala = arc / 3;

		if (calcSubaPapa(Planet.Moon)) {
			PakshaBala.put(Planet.Moon, subaBala * 2);
		} else {
			PakshaBala.put(Planet.Moon, (60.0 - subaBala) * 2);
		}

		PakshaBala.put(Planet.Sun, (60.0 - subaBala));
		PakshaBala.put(Planet.Mars, (60.0 - subaBala));
		PakshaBala.put(Planet.Saturn, (60.0 - subaBala));

		PakshaBala.put(Planet.Jupiter, (subaBala));
		PakshaBala.put(Planet.Venus, (subaBala));

		if (calcSubaPapa(Planet.Mercury)) {
			PakshaBala.put(Planet.Mercury, (subaBala));
		} else {
			PakshaBala.put(Planet.Mercury, (60.0 - subaBala));
		}

		return PakshaBala;

	}

	private EnumMap<Planet, Double> calcTribhagaBala() {

		double part = 0;
		int div = 0;
		double lsunrise = 0.0;
		double lsunset = 0.0;

		if (birthDayNight) {

			lsunrise = sunrise;
			lsunset = sunset;

		} else if (birthData.birthTime() < sunrise) {

			lsunrise = sunrise;
			lsunset = AstroUtil.getSunSet(birthYear, birthMonth, birthDate,
					birthData.longitude(), birthData.latitude(), birthData
							.timeZone());

		} else if (birthData.birthTime() > sunset) {

			birthDay.add(Calendar.DATE, 1);
			lsunrise = AstroUtil.getSunRise(birthDay.get(Calendar.YEAR),
					birthDay.get(Calendar.MONTH) + 1, birthDay
							.get(Calendar.DATE), birthData.longitude(),
					birthData.latitude(), birthData.timeZone());
			lsunset = sunset;

		} else {
			log.severe("TribhagaBala Error:");
		}

		// System.out.println(birthYear+" / "+birthMonth+" / "+ birthDate);
		EnumMap<Planet, Double> TribhagaBala = initBala();

		if (birthDayNight) {

			part = (lsunset - lsunrise) / 3;
			div = (int) ((birthData.birthTime() - lsunrise) / part) + 1;

			switch (div) {

				case 1 :
					TribhagaBala.put(Planet.Mercury, 60.0);

					break;

				case 2 :
					TribhagaBala.put(Planet.Sun, 60.0);

					break;

				case 3 :
					TribhagaBala.put(Planet.Saturn, 60.0);

					break;

			}

		} else {

			part = ((24 + lsunrise) - lsunset) / 3;

			double diff = birthData.birthTime() - lsunset;

			if (diff < 0) {
				diff += 24;
			}

			div = (int) (diff / part) + 1;

			switch (div) {

				case 1 :
					TribhagaBala.put(Planet.Moon, 60.0);

					break;

				case 2 :
					TribhagaBala.put(Planet.Venus, 60.0);

					break;

				case 3 :
					TribhagaBala.put(Planet.Mars, 60.0);

					break;

			}

		}

		TribhagaBala.put(Planet.Jupiter, 60.0);

		return TribhagaBala;
		// System.out.println("SR: " + AstroUtil.dms(sunrise) + " SS: " +
		// AstroUtil.dms(sunset));
	}

	private EnumMap<Planet, Double> calcHoraBala() {

		EnumMap<Planet, Double> HoraBala = initBala();

		Planet[] horaLords = {Planet.Sun, Planet.Venus, Planet.Mercury,
				Planet.Moon, Planet.Saturn, Planet.Jupiter, Planet.Mars};
		int[] wdaydiff = {0, 3, 6, 2, 5, 1, 4};
		double sunrise;

		sunrise = AstroUtil.getSunRise(birthYear, birthMonth, birthDate,
				birthData.longitude(), birthData.latitude(), birthData
						.timeZone());

		double dur = birthData.birthTime() - sunrise;

		if (dur < 0) {
			dur = dur + 24;
		}

		int hora = (int) (dur) + 1;
		int HoraAdipathi = ((wdaydiff[VaraAdipathi.ordinal()] + hora) - 1) % 7;

		// System.out.println("Dur: " + dur + " Hora: " + hora + " HL : " +
		// HoraAdipathi + " VL: " + VaraAdipathi);
		HoraBala.put(horaLords[HoraAdipathi], 60.0);

		return HoraBala;

	}

	private EnumMap<Planet, Double> calcNatonnataBala() {

		double btimeDeg = SwissHelper.calcNatonnataBalaDeg(birthData.birthSD(),
				birthData.birthTime());

		EnumMap<Planet, Double> NatonnataBala = new EnumMap<Planet, Double>(
				Planet.class);

		NatonnataBala.put(Planet.Sun, btimeDeg / 3);
		NatonnataBala.put(Planet.Jupiter, btimeDeg / 3);
		NatonnataBala.put(Planet.Venus, btimeDeg / 3);

		NatonnataBala.put(Planet.Moon, ((180 - btimeDeg) / 3));
		NatonnataBala.put(Planet.Mars, ((180 - btimeDeg) / 3));
		NatonnataBala.put(Planet.Saturn, ((180 - btimeDeg) / 3));

		NatonnataBala.put(Planet.Mercury, 60.0);

		return NatonnataBala;
		// System.out.println("\nbt : " + birthData.birthTime() + " E: " +
		// AstroUtil.dms(EqnOfTime) + sbErr);
	}

	private EnumMap<Planet, Double> calcAyanaBala() {

		double[] kranti;

		kranti = calcKranti();

		EnumMap<Planet, Double> AyanaBala = initBala();

		for (Planet p : Planet.majorPlanets()) {

			if ((p == Planet.Sun) || (p == Planet.Venus) || (p == Planet.Mars)
					|| (p == Planet.Jupiter)) {
				AyanaBala.put(p, (((24 + kranti[p.ordinal()]) * 60) / 48));
			} else if ((p == Planet.Moon) || (p == Planet.Saturn)) {
				AyanaBala.put(p,
						(((24 + (kranti[p.ordinal()] * -1)) * 60) / 48));
			} else {
				AyanaBala.put(p,
						(((24 + Math.abs(kranti[p.ordinal()])) * 60) / 48));
			}

		}

		AyanaBala.put(Planet.Sun, AyanaBala.get(Planet.Sun) * 2);

		return AyanaBala;

	}

	private double[] calcKranti() {

		double[] decln = {0, 362 / 60.00, 703 / 60.00, 1002 / 60.00,
				1238 / 60.00, 1388 / 60.00, 1440 / 60.00

		};

		double sayanaPos;
		int sign;
		int div;
		double bhuja = 0;
		double remdecl;
		double rem;
		double[] kranti = new double[7];

		for (Planet p : Planet.majorPlanets()) {

			sayanaPos = planetPosition.get(p) + ayanamsa;

			if ((sayanaPos > 180) && (sayanaPos <= 360)) {
				sign = -1;
			} else {
				sign = 1;
			}

			if ((sayanaPos > 0) && (sayanaPos <= 90)) {
				bhuja = sayanaPos;
			} else if ((sayanaPos > 90) && (sayanaPos <= 180)) {
				bhuja = 180.0 - sayanaPos;
			} else if ((sayanaPos > 180) && (sayanaPos <= 270)) {
				bhuja = sayanaPos - 180;
			} else if ((sayanaPos > 270) && (sayanaPos <= 360)) {
				bhuja = 360.0 - sayanaPos;
			}

			div = (int) bhuja / 15;
			rem = bhuja % 15;
			remdecl = ((decln[div + 1] - decln[div]) * rem) / 15.00;
			kranti[p.ordinal()] = (decln[div] + remdecl) * sign;

			/*
			 * System.out.println("SayanaPos: " + sayanaPos + " Bhuja: " + bhuja + "
			 * Div " + div + " Rem " + rem + " RemDecl " + remdecl + " Kranti " +
			 * kranti[pl]);
			 */
		}

		return kranti;

	}

	private EnumMap<Planet, Double> calcYuddhaBala() {

		double[] discDia = {0, 0, 9.4, 6.6, 190.4, 16.6, 158.0};
		double ybala = 0.0;
		Planet winner;

		EnumMap<Planet, Double> YuddhaBala = initBala();
		EnumMap<Planet, Double> KalaBala = new EnumMap<Planet, Double>(
				Planet.class);

		YuddhaBala.put(Planet.Sun, 0.0);
		YuddhaBala.put(Planet.Moon, 0.0);
		PlanetBala.put(Bala.YuddhaBala, YuddhaBala);

		for (Planet p : Planet.majorPlanets()) {

			double totalKala = 0.0;

			for (Bala b : Bala.kalaBalas()) {
				totalKala = totalKala + PlanetBala.get(b).get(p);
			}
			KalaBala.put(p, totalKala);

		}

		for (Planet i : EnumSet.range(Planet.Mars, Planet.Venus)) {

			for (Planet j : EnumSet.range(i.nextPlanet(), Planet.Saturn)) {

				winner = findWinner(i, j);

				if (winner != null) {

					ybala = Math.abs(KalaBala.get(i) - KalaBala.get(j))
							/ Math.abs(discDia[i.ordinal()]
									- discDia[j.ordinal()]);

					// System.out.println("(" + i + " , " + j + ")" + " Ybala: "
					// + ybala);
					YuddhaBala.put(i, ybala);

					if (winner == i) {

						YuddhaBala.put(i, ybala);
						YuddhaBala.put(j, ybala * -1);
						KalaBala.put(i, (KalaBala.get(i) + ybala));
						KalaBala.put(j, (KalaBala.get(j) - ybala));

					} else {

						YuddhaBala.put(j, ybala);
						YuddhaBala.put(i, ybala * -1);
						KalaBala.put(i, (KalaBala.get(i) - ybala));
						KalaBala.put(j, (KalaBala.get(j) + ybala));

					}

				}

			}

		}

		PlanetBala.put(Bala.KalaBala, KalaBala);
		return YuddhaBala;

	}

	private Planet findWinner(Planet i, Planet j) {

		double diff = planetPosition.get(i) - planetPosition.get(j);
		Planet winner = null;

		if ((diff >= -1.00) && (diff <= 00)) {
			winner = i;
		} else if ((diff > 0) && (diff <= 1)) {
			winner = j;
		}
		// System.out.println("(" + h.planetPositions[i] + "," +
		// h.planetPositions[j] + ")" + " Diff: " + diff + " Winner " + winner);
		return winner;

	}

	private EnumMap<Planet, Double> calcDrikBala() {

		double dk;
		double[][] drishti = new double[7][7];
		double vdrishti;
		int[] sp = new int[7];

		for (Planet p : Planet.majorPlanets()) {

			if (calcSubaPapa(p)) {
				sp[p.ordinal()] = 1;
			} else {
				sp[p.ordinal()] = -1;
			}

		}

		for (Planet i : Planet.majorPlanets()) {

			// System.out.print(DisplayConsts.planetSyms[0][i]);
			for (Planet j : Planet.majorPlanets()) {

				dk = planetPosition.get(j) - planetPosition.get(i);

				if (dk < 0) {
					dk += 360;
				}

				vdrishti = findViseshaDrishti(dk, i);
				drishti[i.ordinal()][j.ordinal()] = findDrishtiValue(dk)
						+ vdrishti;

				// System.out.print("\t" + AstroUtil.dms(dk));
				// System.out.print("\t" + drishti[i][j]*sp[i] + "("+
				// AstroUtil.dms(dk) + ")");

				/*
				 * if (vdrishti > 0.0) System.out.print(" + " + vdrishti);
				 */
			}

		}

		double bala = 0;

		EnumMap<Planet, Double> DrikBala = new EnumMap<Planet, Double>(
				Planet.class);

		for (Planet i : Planet.majorPlanets()) {

			bala = 0;

			for (Planet j : Planet.majorPlanets()) {

				bala = bala
						+ (sp[j.ordinal()] * drishti[j.ordinal()][i.ordinal()]);

			}

			DrikBala.put(i, bala / 4);

			// DrikBala[i] = PlanetBala ;
		}

		return DrikBala;

	}

	private double findDrishtiValue(double dk) {

		double drishti = 0;

		if ((dk >= 30.0) && (dk <= 60)) {
			drishti = (dk - 30) / 2;
		} else if ((dk > 60.0) && (dk <= 90)) {
			drishti = (dk - 60) + 15;
		} else if ((dk > 90.0) && (dk <= 120)) {
			drishti = ((120 - dk) / 2) + 30;
		} else if ((dk > 120.0) && (dk <= 150)) {
			drishti = (150 - dk);
		} else if ((dk > 150.0) && (dk <= 180)) {
			drishti = (dk - 150) * 2;
		} else if ((dk > 180.0) && (dk <= 300)) {
			drishti = (300 - dk) / 2;
		}

		return drishti;

	}

	private double findViseshaDrishti(double dk, Planet p) {

		double vdrishti = 0;

		switch (p) {

			case Saturn :

				if (((dk >= 60) && (dk <= 90)) || ((dk >= 270) && (dk <= 300))) {
					vdrishti = 45;
				}

				break;

			case Jupiter :

				if (((dk >= 120) && (dk <= 150))
						|| ((dk >= 240) && (dk <= 270))) {
					vdrishti = 30;
				}

				break;

			case Mars :

				if (((dk >= 90) && (dk <= 120)) || ((dk >= 210) && (dk <= 240))) {
					vdrishti = 15;
				}

				break;

			default :
				vdrishti = 0;

		}

		return vdrishti;

	}

	private EnumMap<Planet, Double> calcChestabala() {

		double utime = birthData.birthTime()
				+ ((5 + (double) (4.00 / 60.00)) - birthData.timeZone());
		double interval = epochDays + (double) (utime / 24.00);
		double[] madhya = new double[7];
		double[] seegh = new double[7];
		double correction;
		double ck;

		// System.out.println("Interval : " + interval + " BT " +
		// AstroUtil.dms(birthData.birthTime()) + " UT: " + AstroUtil.dms(utime)
		// );
		madhya[0] = madhya[3] = madhya[5] = ((interval * 0.9855931) + 257.4568) % 360;
		madhya[2] = ((interval * 0.5240218) + 270.22) % 360;
		correction = 3.33 + (0.0067 * (birthYear - 1900));
		madhya[4] = (((interval * 0.08310024) + 220.04) - correction) % 360;
		correction = 5 + (0.001 * (birthYear - 1900));
		madhya[6] = ((interval * 0.03333857) + 236.74 + correction) % 360;
		seegh[2] = seegh[4] = seegh[6] = madhya[0];
		correction = 6.670 + (0.00133 * (birthYear - 1900));
		seegh[3] = ((interval * 4.092385) + 164.00 + correction) % 360;
		correction = 5 + (0.0001 * (birthYear - 1900));
		seegh[5] = (((interval * 1.602159) + 328.51) - correction) % 360;
		ck = (planetPosition.get(Planet.Sun) + ayanamsa + 90) % 360;

		if (ck > 180.00) {
			ck = 360 - ck;
		}

		EnumMap<Planet, Double> ChestaBala = new EnumMap<Planet, Double>(
				Planet.class);

		ChestaBala.put(Planet.Sun, ck / 3.00);
		ck = (planetPosition.get(Planet.Moon) - planetPosition.get(Planet.Sun));

		if (ck < 0) {
			ck = ck + 360;
		}

		if (ck > 180.00) {
			ck = 360 - ck;
		}

		ChestaBala.put(Planet.Moon, ck / 3.00);

		for (Planet p : EnumSet.range(Planet.Mars, Planet.Saturn)) {

			ck = (seegh[p.ordinal()] - ((madhya[p.ordinal()] + planetPosition
					.get(p)) / 2.0));

			if (ck < 360.00) {
				ck = ck + 360;
			}

			ck = ck % 360;

			if (ck > 180.00) {
				ck = 360 - ck;
			}

			ChestaBala.put(p, ck / 3.00);

			// System.out.println(DisplayConsts.planetNames[0][pl] + " Madhya: "
			// + madhya[pl] + " Seeghrochcha: " + seegh[pl]);
			// System.out.println(DisplayConsts.planetNames[0][pl] + "
			// ChestaKendra: " + ck + " PlanetPos: " + h.planetPositions[pl]);
		}

		return ChestaBala;

	}

	public void calcIshtaKashtaBala() {

		double prod;

		EnumMap<Planet, Double> IshtaBala = new EnumMap<Planet, Double>(
				Planet.class);
		EnumMap<Planet, Double> KashtaBala = new EnumMap<Planet, Double>(
				Planet.class);

		for (Planet p : Planet.majorPlanets()) {

			prod = PlanetBala.get(Bala.OchchaBala).get(p)
					* PlanetBala.get(Bala.ChestaBala).get(p);

			// System.out.println(OchchaBala[pl] + " + " + ChestaBala[pl]);
			IshtaBala.put(p, Math.sqrt(prod));
			prod = (60.00 - PlanetBala.get(Bala.OchchaBala).get(p))
					* (60.00 - PlanetBala.get(Bala.ChestaBala).get(p));
			KashtaBala.put(p, Math.sqrt(prod));
		}

		PlanetBala.put(Bala.IshtaBala, IshtaBala);
		PlanetBala.put(Bala.KashtaBala, KashtaBala);

	}

	public void calcBhavaBalas() {

		BhavaBala = new EnumMap<Bala, ArrayList<Double>>(Bala.class);
		BhavaBala.put(Bala.BhavaAdhipathiBala, calcBhavaAdhipathiBala());
		BhavaBala.put(Bala.BhavaDigBala, calcBhavaDigBala());
		BhavaBala.put(Bala.BhavaDrishtiBala, calcBhavaDrishtiBala());

		ArrayList<Double> totalBhavaBala = new ArrayList<Double>();
		for (int i = 0; i < 12; i++) {

			double total = 0;
			for (Bala b : Bala.bhavaBalas()) {
				total = total + BhavaBala.get(b).get(i);
			}
			totalBhavaBala.add(i, total);
		}

		BhavaBala.put(Bala.BhavaBala, totalBhavaBala);
	}

	public ArrayList<Double> calcBhavaAdhipathiBala() {

		Planet owner;

		ArrayList<Double> BhavaAdhipathiBala = new ArrayList<Double>();
		for (int i = 1; i <= 12; i++) {

			owner = housePosition.getBhava(i).house().owner();
			BhavaAdhipathiBala.add(i - 1, PlanetBala.get(Bala.ShadBala).get(
					owner));

		}

		return BhavaAdhipathiBala;
	}

	public ArrayList<Double> calcBhavaDigBala() {

		ArrayList<Double> BhavaDigBala = new ArrayList<Double>();

		int dig = 0;

		for (int i = 1; i <= 12; i++) {

			if ((housePosition.getBhava(i).mid() >= 210.00)
					&& (housePosition.getBhava(i).mid() <= 240.00)) {
				dig = 1 - i;
			} else if (((housePosition.getBhava(i).mid() >= 0.00) && (housePosition
					.getBhava(i).mid() <= 60.00))
					|| ((housePosition.getBhava(i).mid() >= 120.00) && (housePosition
							.getBhava(i).mid() <= 150.00))
					|| ((housePosition.getBhava(i).mid() >= 255.00) && (housePosition
							.getBhava(i).mid() <= 285.00))) {
				dig = 4 - i;
			} else if (((housePosition.getBhava(i).mid() >= 60.00) && (housePosition
					.getBhava(i).mid() <= 90.00))
					|| ((housePosition.getBhava(i).mid() >= 150.00) && (housePosition
							.getBhava(i).mid() <= 210.00))
					|| ((housePosition.getBhava(i).mid() >= 300.00) && (housePosition
							.getBhava(i).mid() <= 330.00))
					|| ((housePosition.getBhava(i).mid() >= 240.00) && (housePosition
							.getBhava(i).mid() <= 255.00))) {
				dig = 7 - i;
			} else if (((housePosition.getBhava(i).mid() >= 90.00) && (housePosition
					.getBhava(i).mid() <= 120.00))
					|| ((housePosition.getBhava(i).mid() >= 330.00) && (housePosition
							.getBhava(i).mid() <= 360.00))
					|| ((housePosition.getBhava(i).mid() >= 285.00) && (housePosition
							.getBhava(i).mid() <= 300.00))) {
				dig = 10 - i;
			}

			if (dig < 0) {
				dig = dig + 12;
			}

			if (dig > 6) {
				dig = 12 - dig;
			}

			BhavaDigBala.add(i - 1, (double) dig * 10);

		}

		return BhavaDigBala;
	}

	private ArrayList<Double> calcBhavaDrishtiBala() {

		double dk;
		double[][] drishti = new double[7][12];
		double vdrishti;
		int[] sp = new int[7];

		for (Planet p : Planet.majorPlanets()) {

			if (calcSubaPapa(p)) {
				sp[p.ordinal()] = 1;
			} else {
				sp[p.ordinal()] = -1;
			}

		}

		sp[3] = 1;

		for (Planet i : Planet.majorPlanets()) {

			for (int j = 1; j <= 12; j++) {

				dk = housePosition.getBhava(j).mid() - planetPosition.get(i);

				if (dk < 0) {
					dk += 360;
				}

				vdrishti = findViseshaDrishti(dk, i);

				if ((i == Planet.Mercury) || (i == Planet.Jupiter)) {
					drishti[i.ordinal()][j - 1] = findDrishtiValue(dk)
							+ vdrishti;
				} else {
					drishti[i.ordinal()][j - 1] = (findDrishtiValue(dk) + vdrishti) / 4.00;
				}

			}

		}

		double bala = 0;

		ArrayList<Double> BhavaDrishtiBala = new ArrayList<Double>();

		for (int i = 0; i < 12; i++) {

			bala = 0;

			for (int j = 0; j < 7; j++) {

				bala = bala + (sp[j] * drishti[j][i]);

			}

			BhavaDrishtiBala.add(i, bala);
		}

		return BhavaDrishtiBala;
	}

	private void calcRanks() {

		List<ComparableEntry<Planet, Double>> StrengthPerOrdered = Utils
				.sortMap(StrengthPer.entrySet(), true);

		ShadBalaRank = new EnumMap<Planet, Integer>(Planet.class);

		for (int rank = 1; rank <= 7; rank++) {
			ComparableEntry<Planet, Double> entry = StrengthPerOrdered
					.get(rank - 1);
			ShadBalaRank.put(entry.getKey(), rank);
		}

		BhavaBalaRank = new ArrayList<Integer>();

		ArrayList<Double> BhavaBalaOrig = BhavaBala.get(Bala.BhavaBala);

		ArrayList<Double> BhavaBalaOrdered = new ArrayList<Double>();

		BhavaBalaOrdered.addAll(BhavaBalaOrig);

		Collections.sort(BhavaBalaOrdered, Collections.reverseOrder());

		for (int house = 0; house < 12; house++) {

			int rank = BhavaBalaOrdered.indexOf(BhavaBalaOrig.get(house)) + 1;

			BhavaBalaRank.add(house, rank);
		}
	}

	private static EnumMap<Planet, Double> initBala() {

		EnumMap<Planet, Double> bala = new EnumMap<Planet, Double>(Planet.class);
		for (Planet p : Planet.majorPlanets()) {
			bala.put(p, 0.0);
		}

		return bala;
	}

	public Map<Planet, Double> getStrengthPer() {
		return StrengthPer;
	}
	
	public TableData<PlanetBalaRow> getPlanetBalaTableData() {

		if (planetBalaTableData == null) {
			planetBalaTableData = new PlanetBalaTableData();
		}
		return planetBalaTableData;
	}

	public DefaultColumnMetaData getPlanetBalaColumnMetaData() {

		if (planetBalaColumnMetaData == null) {
			planetBalaColumnMetaData = new PlanetBalaColumnMetaData(Bala
					.planetBalas(), AstrosoftTableColumn.ShadBala,
					AstrosoftTableColumn.Rupa,
					AstrosoftTableColumn.BalaPercentage,
					AstrosoftTableColumn.Rank, AstrosoftTableColumn.IshtaBala,
					AstrosoftTableColumn.KashtaBala){


				@Override
				public Comparator getColumnComparator(final AstrosoftTableColumn col) {


					return new Comparator(){

						public int compare(Object o1, Object o2) {

							if(sortableColumns.contains(col)) {
								Comparable r1 = (Comparable)((PlanetBalaRow)o1).getColumnData(col);
								Comparable r2 = (Comparable)((PlanetBalaRow)o2).getColumnData(col);

								if (r1 == null && r2 ==null){
									return 0;
								}else if (r1 == null){
									return -1;
								}else if (r2 == null){
									return 1;
								}else {
									return r1.compareTo(r2);
								}
							}else{
								return 0;
							}
						}

					};
				}

			};
			planetBalaColumnMetaData.setSortableColumns(AstrosoftTableColumn.Planet, AstrosoftTableColumn.ResidentialStrength, AstrosoftTableColumn.Rank, AstrosoftTableColumn.KashtaBala, AstrosoftTableColumn.IshtaBala);
		}
		return planetBalaColumnMetaData;
	}

	public TableData<BhavaBalaRow> getBhavaBalaTableData() {

		if (bhavaBalaTableData == null) {
			bhavaBalaTableData = new BhavaBalaTableData();
		}
		return bhavaBalaTableData;
	}

	public DefaultColumnMetaData getBhavaBalaColumnMetaData() {

		if (bhavaBalaColumnMetaData == null) {
			List<AstrosoftTableColumn> cols = Bala.toTableColumn(Bala
					.bhavaBalas());
			cols.add(0, AstrosoftTableColumn.House);
			cols.add(1,AstrosoftTableColumn.Bhava);
			cols.add(AstrosoftTableColumn.BhavaBala);
			cols.add(AstrosoftTableColumn.Rupa);
			cols.add(AstrosoftTableColumn.Rank);
			bhavaBalaColumnMetaData = new DefaultColumnMetaData(cols) {
				@Override
				public Class getColumnClass(AstrosoftTableColumn col) {

					switch (col) {
						case House :
						case Rank:
							return Roman.class;
						case Bhava:
							return Bhava.class;
					}
					return Number.class;
				}

				@Override
				public Comparator getColumnComparator(final AstrosoftTableColumn col) {


					return new Comparator(){

						public int compare(Object o1, Object o2) {

							if (sortableColumns.contains(col)){

								Comparable r1 = (Comparable)((BhavaBalaRow)o1).getColumnData(col);
								Comparable r2 = (Comparable)((BhavaBalaRow)o2).getColumnData(col);
								return r1.compareTo(r2);
							}else{
								return 0;
							}
						}

					};
				}
			};
			bhavaBalaColumnMetaData.localizeColumns();
			bhavaBalaColumnMetaData.setSortableColumns(AstrosoftTableColumn.Bhava, AstrosoftTableColumn.Rank);
		}
		return bhavaBalaColumnMetaData;
	}

	public DefaultColumnMetaData getSthanaBalaColumnMetaData() {

		if (sthanaBalaColumnMetaData == null) {
			sthanaBalaColumnMetaData = new PlanetBalaColumnMetaData(Bala
					.sthanaBalas(), AstrosoftTableColumn.SthanaBala);
		}
		return sthanaBalaColumnMetaData;
	}

	public DefaultColumnMetaData getKalaBalaColumnMetaData() {

		if (kalaBalaColumnMetaData == null) {
			kalaBalaColumnMetaData = new PlanetBalaColumnMetaData(Bala
					.kalaBalas(), AstrosoftTableColumn.KalaBala);
		}
		return kalaBalaColumnMetaData;
	}

	private class PlanetBalaColumnMetaData extends DefaultColumnMetaData {

		public PlanetBalaColumnMetaData(EnumSet<Bala> balas,
				AstrosoftTableColumn... otherCols) {
			List<AstrosoftTableColumn> cols = Bala.toTableColumn(balas);
			cols.add(0, AstrosoftTableColumn.Planet);
			for (AstrosoftTableColumn col : otherCols) {
				cols.add(col);
			}
			super.addColumns(cols);
			localizeColumns();
		}

		@Override
		public Class getColumnClass(AstrosoftTableColumn col) {

			switch (col) {
				case Planet :
					return Planet.class;
				case Rank:
					return Roman.class;

			}
			return Number.class;
		}
	}

	private class PlanetBalaTableData implements TableData<PlanetBalaRow> {

		public PlanetBalaRow getRow(int index) {

			return new PlanetBalaRow(Planet.values()[index]);
		}

		public int getRowCount() {
			return 9;
		}

	}

	private class PlanetBalaRow implements TableRowData {

		Planet row;

		public PlanetBalaRow(Planet row) {
			this.row = row;
		}

		public Object getColumnData(AstrosoftTableColumn col) {

			if (col == AstrosoftTableColumn.Planet) {
				return row;
			} else if (col == AstrosoftTableColumn.Rupa) {
				Double val = PlanetBala.get(Bala.ShadBala).get(row);
				if (val != null){
					return val / 60.00;
				}else{
					return null;
				}
			} else if (col == AstrosoftTableColumn.BalaPercentage) {
				return StrengthPer.get(row);
			} else if (col == AstrosoftTableColumn.Rank) {

				Integer rank = ShadBalaRank.get(row);

				if (rank != null)
					return Roman.of(ShadBalaRank.get(row));
				else
					return null;
			} else {
				Bala b = col.toEnum(Bala.class);
				return PlanetBala.get(b).get(row);
			}
		}

	}

	private class BhavaBalaTableData implements TableData<BhavaBalaRow> {

		public BhavaBalaRow getRow(int index) {

			return new BhavaBalaRow(index);
		}

		public int getRowCount() {
			return 12;
		}

	}

	private class BhavaBalaRow implements TableRowData {

		int bhava;

		public BhavaBalaRow(int row) {
			this.bhava = row;
		}

		public Object getColumnData(AstrosoftTableColumn col) {

			if (col == AstrosoftTableColumn.House ){
				return Roman.of(bhava + 1);
			} else if (col == AstrosoftTableColumn.Bhava) {
				return housePosition.getBhava(bhava + 1).house();
			} else if (col == AstrosoftTableColumn.Rupa) {
				return BhavaBala.get(Bala.BhavaBala).get(bhava) / 60.00;
			} else if (col == AstrosoftTableColumn.Rank) {
				return Roman.of(BhavaBalaRank.get(bhava));
			} else {
				Bala b = col.toEnum(Bala.class);
				return BhavaBala.get(b).get(bhava);
			}
		}

	}

	public void doExport(Exporter e) {
		e.export(this);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("ShadBala \n");

		for (Planet p : Planet.majorPlanets()) {

			// sb.append("Planet Position: " + h.planetPositions[i]);
			sb.append(p.toString() + " --> " + planetLocation.get(p) + "\n");
			sb.append(p.toString() + " --> "
					+ PlanetBala.get(Bala.ResidentialStrength).get(p) + "\n");
			sb.append("OchachaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.OchchaBala).get(p) + "\n");
			sb.append("OjaYugmarasyamsaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.OjaYugmarasyamsaBala).get(p) + "\n");
			sb.append("KendraBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.KendraBala).get(p) + "\n");
			sb.append("DrekkanaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.DrekkanaBala).get(p) + "\n");
			sb.append("SapthavargaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.SaptavargajaBala).get(p) + "\n");
			sb.append("SthanaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.SthanaBala).get(p) + "\n");
			sb.append("DigBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.DigBala).get(p) + "\n");
			sb.append("NaisargikaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.NaisargikaBala).get(p) + "\n");
			sb.append("AbdaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.AbdaBala).get(p) + "\n");
			sb.append("MasaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.MasaBala).get(p) + "\n");
			sb.append("VaraBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.VaraBala).get(p) + "\n");
			sb.append("PakshaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.PakshaBala).get(p) + "\n");
			sb.append("TribhagaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.TribhagaBala).get(p) + "\n");
			sb.append("HoraBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.HoraBala).get(p) + "\n");
			sb.append("NatonnataBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.NatonnataBala).get(p) + "\n");
			sb.append("AyanaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.AyanaBala).get(p) + "\n");
			sb.append("YuddhaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.YuddhaBala).get(p) + "\n");
			sb.append("KalaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.KalaBala).get(p) + "\n");
			sb.append("DrikBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.DrikBala).get(p) + "\n");
			sb.append("ChestaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.ChestaBala).get(p) + "\n");
			sb.append("IshtaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.IshtaBala).get(p) + "\n");
			sb.append("KashtaBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.KashtaBala).get(p) + "\n");
			sb.append("ShadBala:-->" + p.toString() + " :-> "
					+ PlanetBala.get(Bala.ShadBala).get(p) / 60.00 + "\n");
			sb.append("ShadBala Per:-->" + p.toString() + " :-> "
					+ this.StrengthPer.get(p) + "\n");
			sb.append("ShadBala Rank:-->" + p.toString() + " :-> "
					+ ShadBalaRank.get(p) + "\n");

		}

		for (int i = 0; i < 12; i++) {

			sb.append("BhavaAdhipathiBala: " + (i + 1) + " : -->"
					+ BhavaBala.get(Bala.BhavaAdhipathiBala).get(i) / 60.00
					+ "\n");
			sb.append("BhavaDigBala: " + (i + 1) + " : -->"
					+ BhavaBala.get(Bala.BhavaDigBala).get(i) + "\n");
			sb.append("BhavaDrishtiBala: " + (i + 1) + " : -->"
					+ BhavaBala.get(Bala.BhavaDrishtiBala).get(i) + "\n");
			sb.append("BhavaBala: " + (i + 1) + " : -->"
					+ BhavaBala.get(Bala.BhavaBala).get(i) + "\n");
			sb.append("BhavaBala Rank: " + (i + 1) + " : -->"
					+ BhavaBalaRank.get(i) + "\n");

		}
		sb.append("\n");
		return sb.toString();
	}

	public static void main(String[] args) {

		
		 Horoscope h = new Horoscope( "Raja", 11, 12, 1980, 1, 44, 77 + (
		  44.00 / 60.00 ), 11 + ( 22.00 / 60.00 ), 5.5, "Erode" );
		 

		/*
		 * Horoscope h = new Horoscope("Elango", 17, 4, 1957, 7, 10, 77 + (44.00 /
		 * 60.00), 11 + (22.00 / 60.00), 5.5, "Erode");
		 */

		/*Horoscope h = new Horoscope("Mani", 10, 8, 1960, 5, 30,
				77 + (44.00 / 60.00), 11 + (22.00 / 60.00), 5.5, "Erode");*/

		// Horoscope h = new
		// Horoscope("Jayaram",13,5,1979,10,12,80+(15.00/60.00),11+(22.00/60.00),5.5,"Chennai");
		// Horoscope h = new
		// Horoscope("Suba",31,3,1988,17,56,77+(44.00/60.00),11+(22.00/60.00),5.5,"Erode");
		// Horoscope h = new
		// Horoscope("Mani",10,8,1960,5,30,77+(44.00/60.00),11+(22.00/60.00),5.5,"Erode");
		// Horoscope h = new
		// Horoscope("BV",16,10,1918,14,26,77+(34.00/60.00),13+(0.00/60.00),5.5,"Banglore");
		h.setAyanamsa(Ayanamsa.KRISHNAMURTHI);
		ShadBala sb = new ShadBala(h.getPlanetaryInfo(), h.getHousePosition(),
				h.getBirthData(), h.getAyanamsa(), h.getSunrise(), h
						.getSunset(), h.getPaksha());

		System.out.println(sb);
	}



}

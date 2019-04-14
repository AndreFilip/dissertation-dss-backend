package gr.athenstech.dissertation.decisionsupportsystem.services.servicesImpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class FireIndexServiceImpl {
	
	//temp  Noon temperature (°C)
	//rhum Noon relative humidity (%)
	//wind Noon wind speed (km/h)
	//prcp Rain fall in open over previous 24 hours, at noon (mm)
	@Transactional
	public double FWIcalculator (double temp, double rhum, double wind, double prcp, int mth){		
		 double ffmc0 = 85.0;
		 double dmc0 = 6.0;
		 double dc0 = 15.0;
		 
		 if (rhum > 100.0) rhum = 100.0; 
		 
		 // convert m/s to km/h
		 wind =(int) (3.6 * wind); 
		 
		 double ffmc = FFMCcalc(temp,rhum,wind,prcp,ffmc0);
		 double dmc = DMCcalc(temp,rhum,prcp,dmc0, mth);
		 double dc = DCcalc(temp,prcp,dc0, mth);
		 double isi = ISIcalc(ffmc,wind);
		 double bui = BUIcalc(dmc,dc);
		 double fwi = FWIcalc(isi,bui);

		 return fwi;
	}
	
	// Function to calculate fwi 
	private static double FWIcalc(double R, double U) {
			double fD, B, S;
			if (U <= 80.0)
				fD = .626 * Math.pow(U, .809) + 2.0;
			else
				fD = 1000.0 / (25.0 + 108.64 * Math.exp(-.023 * U));
			B = .1 * R * fD;
			if (B > 1.0)
				S = Math.exp(2.72 * Math.pow(.434 * Math.log(B), .647));
			else
				S = B;
			return S;
		}

	// Function for calculating FFMC
	private static double FFMCcalc(double T, double H, double W, double ro, double Fo) {
		double mo, rf, mr, Ed, Ew, ko, kd, kl, kw, m, F;
		mo = 147.2 * (101.0 - Fo) / (59.5 + Fo); /* Eq. 1 */
		if (ro > 0.5) {
			rf = ro - 0.5; /* Eq. 2 */
			if (mo <= 150.0) {
				mr = mo + 42.5 * rf * (Math.exp(-100.0 / (251.0 - mo))) * (1.0 - Math.exp(-6.93 / rf)); /* Eq. 3a */
			} else {
				mr = mo + 42.5 * rf * (Math.exp(-100.0 / (251.0 - mo))) * (1.0 - Math.exp(-6.93 / rf))
						+ .0015 * Math.pow(mo - 150.0, 2.0) * Math.pow(rf, .5); /* Eq. 3b */
			}
			if (mr > 250.0) {
				mr = 250.0;
			}
			mo = mr;
		}
		Ed = 0.942 * Math.pow(H, .679) + 11.0 * Math.exp((H - 100.0) / 10.0)
				+ .18 * (21.1 - T) * (1.0 - Math.exp(-.115 * H)); /* Eq. 4 */
		if (mo > Ed) {
			ko = 0.424 * (1.0 - Math.pow(H / 100.0, 1.7))
					+ 0.0694 * Math.pow(W, .5) * (1.0 - Math.pow(H / 100.0, 8)); /* Eq. 6a */
			kd = ko * .581 * Math.exp(0.0365 * T); /* Eq. 6b */
			m = Ed + (mo - Ed) * Math.pow(10.0, -kd); /* Eq. 8 */
		} else {
			Ew = 0.618 * Math.pow(H, .753) + 10.0 * Math.exp((H - 100.0) / 10.0)
					+ .18 * (21.1 - T) * (1.0 - Math.exp(-.115 * H)); /* Eq. 5 */
			if (mo < Ew) {
				kl = 0.424 * (1.0 - Math.pow((100.0 - H) / 100.0, 1.7))
						+ 0.0694 * Math.pow(W, .5) * (1.0 - Math.pow((100.0 - H) / 100.0, 8)); /* Eq. 7a */
				kw = kl * .581 * Math.exp(0.0365 * T); /* Eq. 7b */
				m = Ew - (Ew - mo) * Math.pow(10.0, -kw); /* Eq. 9 */
			} else
				m = mo;
		}
		F = 59.5 * (250.0 - m) / (147.2 + m); /* Eq. 10 */
		if (F > 101.0)
			F = 101.0;
		return F;
	}

	// Function to calculate DMC 
	private static double DMCcalc(double T, double H, double ro, double Po, int I) {
		double re, Mo, Mr, K, b, P, Pr;
		double Le[] = { 6.5, 7.5, 9., 12.8, 13.9, 13.9, 12.4, 10.9, 9.4, 8., 7., 6. };
		if (T >= -1.1) {
			K = 1.894 * (T + 1.1) * (100.0 - H) * Le[I - 1] * 0.0001; /* Eq. 16 */
		} else {
			K = 0.0; /* Eq. 17 */
		}
		if (ro <= 1.5) {
			Pr = Po;
		} else {
			re = 0.92 * ro - 1.27; /* Eq. 11 */
			Mo = 20.0 + 280.0 / Math.exp(0.023 * Po); /* Eq. 12 */
			if (Po <= 33.0) {
				b = 100.0 / (.50 + .30 * Po); /* Eq. 13a */
			} else {
				if (Po <= 65.0) {
					b = 14.0 - 1.3 * Math.log(Po); /* Eq. 13b */
				} else {
					b = 6.2 * Math.log(Po) - 17.2;
				}
			} /* Eq. 13c */
			Mr = Mo + 1000.0 * re / (48.77 + b * re); /* Eq. 14 */
			Pr = 43.43 * (5.6348 - Math.log(Mr - 20.0)); /* Eq. 15 */
		}
		if (Pr < 0.0) {
			Pr = 0.0;
		}
		P = Pr + K;
		if (P <= 0.0) {
			P = 0.0;
		}
		return P;
	}

	// Function to calculateDC 
	private static double DCcalc(double T, double ro, double Do, int I) {
		double rd, Qo, Qr, V, D, Dr;
		double Lf[] = { -1.6, -1.6, -1.6, .9, 3.8, 5.8, 6.4, 5., 2.4, .4, -1.6, -1.6 };
		if (ro > 2.8) {
			rd = 0.83 * (ro) - 1.27; /* Eq. 18 */
			Qo = 800.0 * Math.exp(-Do / 400.0); /* Eq. 19 */
			Qr = Qo + 3.937 * rd; /* Eq. 20 */
			Dr = 400.0 * Math.log(800.0 / Qr); /* Eq. 21 */
			if (Dr > 0.0)
				Do = Dr;
			else
				Do = 0.0;
		}
		if (T > -2.8)
			V = 0.36 * (T + 2.8) + Lf[I - 1]; /* Eq. 22 */
		else
			V = Lf[I - 1];
		if (V < 0.)
			V = .0;
		D = Do + 0.5 * V; /* Eq. 23 */
		return D;
	}

	// Function to calculate isi 
	private static double ISIcalc(double F, double W) {
		double fW, m, fF, R;
		fW = Math.exp(0.05039 * W);
		m = 147.2 * (101.0 - F) / (59.5 + F);
		fF = 91.9 * Math.exp(-.1386 * m) * (1. + Math.pow(m, 5.31) / 4.93E7);
		R = 0.208 * fW * fF;
		return R;
	}

	
	// Function to calculate bui 
	private static double BUIcalc(double P, double D) {
		double U;
		if (P <= .4 * D)
			U = 0.8 * P * D / (P + .4 * D);
		else
			U = P - (1.0 - .8 * D / (P + .4 * D)) * (.92 + Math.pow(.0114 * P, 1.7));
		if (U < 0.0)
			U = 0.0;
		return U;
	}	

}

//The following variables were discussed and
//defined in Van Wagner and Pickett (1985)

//T = Noon temperature (°C)
//H = Noon relative humidity (%)
//W = Noon wind speed (km/h)
//Ro = Rain fall in open over previous 24 hours, at noon (mm)
//Rf = Effective rain fall for calculating FFMC
//Re = Effective rainfall for calculating DMC
//Rd = Effective rainfall for calculating DC
//Mo = Fine Fuel Moisture Content from previous day
//Mr = Fine Fuel Moisture Content after rain
//M = Fine Fuel Moisture Content after drying
//Ed = Fine Fuel equilibrium moisture content (EMC) for drying
//Ew = Fine Fuel EMC for wetting
//Ko = Intermediate step in calculation of Kd
//Kd = Log drying rate, FFMC drying rate, FFMC ln (M)/day
//K1 = Intermediate step in calculation of Kw
//Kw = Natural log wetting rate, ln (M)/day
//Fo = Previous day’s FFMC
//F = Fine Fuel Moisture Code
//DMo = Duff Moisture Content from previous day
//DMr = Duff moisture content after rain
//DM = Duff moisture content after drying
//K = Log drying rate in DMC, ln (M)/day
//Le = Effective day length in DMC, hours
//B = Slope variable in DMC rain effect
//Po = Previous day’s DMC
//Pr = DMC after rain
//P = DMC
//Q = Moisture equivalent of DC, units of 0.254 mm
//Qo = Moisture equivalent of previous day’s DC
//Qr = Moisture equivalent after rain
//V = Potential evapotranspiration, units of 0.254 mm water/day
//Lf = Day-length adjustment in DC
//Do = Previous day’s DC
//Dr = DC after rain
//D = DC
//Fw = Wind function
//Ff = Fine fuel moisture function
//Fd = Duff moisture function
//R = Initial spread index
//U = Buildup index
//B = Intermediate FWI
//S = Final FWI 



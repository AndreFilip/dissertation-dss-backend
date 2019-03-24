package gr.athenstech.dissertation.decisionsupportsystem.utils;

public class MyConstants {
	public static final String Soil_Sand = "Sand";
	public static final String Soil_Loamy_sand = "Loamy sand";
	public static final String Soil_Sandy_loam = "Sandy loam";
	public static final String Soil_Loam = "Loam";
	public static final String Soil_Silty_loam = "Silty loam";
	public static final String Soil_Silt = "Silt";
	public static final String Soil_Clay_loam = "Clay loam";
	public static final String Soil_Sand_clay_loam = "Sandy clay loam";
	public static final String Soil_Silty_clay_loam = "Silty clay loam";
	public static final String Soil_Sandy_clay = "Sandy clay";
	public static final String Soil_Silty_clay = "Silty clay";
	public static final String Soil_Clay = "Clay";
	
	public static final String Bulk_Result_above_1_6 = "Soils with a bulk density higher than 1.6 g/cm³ tend to restrict root growth.";
	public static final String Bulk_Result_between_1_1_6 = "Soils with bulk density between 1.0 and 1.6 g/cm³ is a good indicator for plant growth and is likely to have plenty of minerals.";
	public static final String Bulk_Result_below_1 = "Soils high in organics and some friable clay may have a bulk density well below 1 g/cm³.";
	public static final String Bulk_Result_below_0_02 = "Peat soils have bulk densities of around 0.02 g/cm³. These soils are highly saline and deficient in phosphate and potash. so, this soil is useless for agriculture.";
	
	public static final String Cation_Result_above_30 = "Soils with high levels of swelling clay and organic matter can have a CEC of 30 cmol(+)/kg or more.";
	public static final String Cation_Result_above_10 = "A figure above 10 cmol(+)/kg is preferred for plant production.";
	public static final String Cation_Result_below_10 = "A figure below 10 cmol(+)/kg is not preferred for agriculture.";

	public static final String PH_Result_bad = "PH in the range of 7.6 and above or 5.4 and below is not so good for growing crops.";
	public static final String PH_Result_optimal = "PH in the range of 6.2 and 6.8 is optimal for growing crops.";	
	public static final String BPH_Result_good = "PH in the range of 5.5 to 7.5 is very good for growing crops";
	
	public static final String SOC_Result_above_threshold = "Above the threshold for your kind of soil. Soil organic C (SOC) accumulation contributes to the long-term resilience and productivity of agricultural soils.";
	public static final String SOC_Result_below_threshold = "Below the threshold for your kind of soil. Soil organic C (SOC) accumulation contributes to the long-term resilience and productivity of agricultural soils. You need more clay and silt particles in your soil.";

	
}

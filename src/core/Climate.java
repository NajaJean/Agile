package core;

public class Climate{	

	static double[] NAfrom = {0.0, 0.0};
	static double[] NAto = {490.0, 400.0};
	
	static double[] SAfrom = {0.0, 450.0};
	static double[] SAto = {490.0, 900.0};
	
	static double[] EUfrom = {490.0, 0.0};
	static double[] EUto = {870.0, 250.0};
	
	static double[] Africafrom = {490.0, 250.0};
	static double[] Africato = {1000.0, 900.0};
	
	static double[] NAsiafrom = {870.0, 0.0};
	static double[] NAsiato = {1800.0, 250.0};
	
	static double[] SAsiafrom = {870.0, 250.0};
	static double[] SAsiato = {1800.0, 550.0};
	
	static double[] Australiafrom = {870.0, 550.0};
	static double[] Australiato = {1800.0, 900.0};
	
	static Weather[] climate = { new Weather(NAfrom, NAto),
						  new Weather(SAfrom, SAto),
						  new Weather(EUfrom, EUto),
						  new Weather(Africafrom, Africato),
						  new Weather(NAsiafrom, NAsiato),
						  new Weather(SAsiafrom, SAsiato),
						  new Weather(Australiafrom, Australiato)};
	
	public static Weather[] getClimate() {
		return climate;
	}
}

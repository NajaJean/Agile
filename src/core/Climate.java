package core;


public class Climate{
	

	static double[] NAfrom = {0.0, 0.0};
	static double[] NAto = {490.0, 400.0};
	static Environment NAEnv = new Environment(15.0, 1.0, 0.0);
	
	static double[] SAfrom = {0.0, 450.0};
	static double[] SAto = {490.0, 900.0};
	static Environment SAEnv = new Environment(30.0, 1.0, 0.0);
	
	static double[] EUfrom = {490.0, 0.0};
	static double[] EUto = {870.0, 250.0};
	static Environment EUEnv = new Environment(20.0, 1.0, 0.0);
	
	static double[] Africafrom = {490.0, 250.0};
	static double[] Africato = {1000.0, 900.0};
	static Environment AfricaEnv = new Environment(35.0, 1.0, 0.0);
	
	static double[] NAsiafrom = {870.0, 0.0};
	static double[] NAsiato = {1800.0, 250.0};
	static Environment NAsiaEnv = new Environment(15.0, 1.0, 0.0);
	
	static double[] SAsiafrom = {870.0, 250.0};
	static double[] SAsiato = {1800.0, 550.0};
	static Environment SAsiaEnv = new Environment(25.0, 1.0, 0.0);
	
	static double[] Australiafrom = {870.0, 550.0};
	static double[] Australiato = {1800.0, 900.0};
	static Environment AustraliaEnv = new Environment(100.0, 1.0, 0.0);
	
	static Weather[] climate = { new Weather(NAfrom, NAto, NAEnv),
						  new Weather(SAfrom, SAto, SAEnv),
						  new Weather(EUfrom, EUto, EUEnv),
						  new Weather(Africafrom, Africato, AfricaEnv),
						  new Weather(NAsiafrom, NAsiato, NAsiaEnv),
						  new Weather(SAsiafrom, SAsiato, SAsiaEnv),
						  new Weather(Australiafrom, Australiato, AustraliaEnv)};
	
	public void updateWeatherAllContainerJs(ContainerJourney cJs[]) {
			for (int j = 0; j < cJs.length; j++) {
				updateWeatherForOneContainerJ(cJs[j]);		
		}
	}
	
	public static Weather[] getClimate() {
		return climate;
	}
	
	public void updateWeatherForOneContainerJ(ContainerJourney cJ) {
		for (int i = 0; i < climate.length; i++) {
				if (((climate[i].from[0] <= cJ.getCurrentX()) && 
						(cJ.getCurrentX() < climate[i].to[0])) &&
					((climate[i].from[1] <= cJ.getCurrentY()) && 
							(cJ.getCurrentY() < climate[i].to[1]))){
					cJ.getContaineronJourney().setContainerEnvironment(climate[i].localWeather);
					
				}
		}	
	}
}

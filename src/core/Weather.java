package core;

public class Weather {

	double[] from;
	double[] to;
	Environment localWeather;
	
	public Weather (double[] from, double[] to, Environment weather) {
		this.from = from;
		this.to = to;
		this.localWeather = weather;
	}	
}


class Climate{
	

	double[] NAfrom = {0.0, 0.0};
	double[] NAto = {490.0, 400.0};
	Environment NAEnv = new Environment(15.0, 1.0, 0.0);
	
	double[] SAfrom = {0.0, 450.0};
	double[] SAto = {490.0, 900.0};
	Environment SAEnv = new Environment(30.0, 1.0, 0.0);
	
	double[] EUfrom = {490.0, 0.0};
	double[] EUto = {870.0, 250.0};
	Environment EUEnv = new Environment(20.0, 1.0, 0.0);
	
	double[] Africafrom = {490.0, 250.0};
	double[] Africato = {1000.0, 900.0};
	Environment AfricaEnv = new Environment(3.0, 1.0, 0.0);
	
	double[] NAsiafrom = {870.0, 0.0};
	double[] NAsiato = {1800.0, 250.0};
	Environment NAsiaEnv = new Environment(15.0, 1.0, 0.0);
	
	double[] SAsiafrom = {870.0, 250.0};
	double[] SAsiato = {1800.0, 550.0};
	Environment SAsiaEnv = new Environment(25.0, 1.0, 0.0);
	
	double[] Australiafrom = {870.0, 550.0};
	double[] Australiato = {1800.0, 900.0};
	Environment AustraliaEnv = new Environment(35.0, 1.0, 0.0);
	
	Weather[] climate = { new Weather(NAfrom, NAto, NAEnv),
						  new Weather(SAfrom, SAto, SAEnv),
						  new Weather(EUfrom, EUto, EUEnv),
						  new Weather(Africafrom, Africato, AfricaEnv),
						  new Weather(NAsiafrom, NAsiato, NAsiaEnv),
						  new Weather(SAsiafrom, SAsiato, SAsiaEnv),
						  new Weather(Australiafrom, Australiato, AustraliaEnv)};
	
	public void updateWeatherAllContainerJs(ContainerJourney cJs[]) {
		for (int i = 0; i < climate.length; i++) {
			for (int j = 0; j < cJs.length; i++) {
		
				if (((climate[i].from[0] <= cJs[j].getCurrentLocX()) && 
						(cJs[j].getCurrentLocX() < climate[i].to[0])) &&
					((climate[i].from[1] <= cJs[j].getCurrentLocY()) && 
							(cJs[j].getCurrentLocY() < climate[i].to[1]))){
					cJs[j].getContaineronJourney().setContainerEnvironment(climate[i].localWeather);
				}
			}	
		}
	}
	
	public void updateWeatherForOneContainerJ(ContainerJourney cJ) {
		for (int i = 0; i < climate.length; i++) {
				if (((climate[i].from[0] <= cJ.getCurrentLocX()) && 
						(cJ.getCurrentLocX() < climate[i].to[0])) &&
					((climate[i].from[1] <= cJ.getCurrentLocY()) && 
							(cJ.getCurrentLocY() < climate[i].to[1]))){
					cJ.getContaineronJourney().setContainerEnvironment(climate[i].localWeather);
					
				}
		}	
	}
}

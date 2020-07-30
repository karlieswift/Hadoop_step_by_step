package enum_test;

public class Enum_Test {

	public static void main(String[] args) {
		
		SeasonEnum []seasonarr=SeasonEnum.values();
		
		System.out.println(seasonarr[0]);
	}
}
enum SeasonEnum{
	SPRING,SUMMER,AUTUMN,WINTER;
	
}
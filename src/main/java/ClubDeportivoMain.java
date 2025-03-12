import clubdeportivo.ClubDeportivo;
import clubdeportivo.ClubException;
import clubdeportivo.Grupo;

public class ClubDeportivoMain {
	public static void main(String[] args) {
		String [] grupo1 = {"123A","padel","10","0","25.0"};
		
		try {
			ClubDeportivo club = new ClubDeportivo("UMA");
			Grupo pilates = new Grupo("123B","padel",8,0,50.0);
			club.anyadirActividad(grupo1);
			club.anyadirActividad(pilates);
			System.out.println(club);			
			System.out.println("Ingresos: " + club.ingresos());
			club.matricular("padel", 15);
			System.out.println(club);
			System.out.println(club.plazasLibres("padel"));
			
		} catch (ClubException e) {
			System.out.println(e.getMessage());
		}
		
	}
}

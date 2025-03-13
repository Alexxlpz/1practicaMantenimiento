/*
 * Alejandro López Ortega
 * Pablo Galvez Castillo
*/
package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    
    static ClubDeportivo clubBienCreado;

    @BeforeAll
    static public void ClubDeportivoBienCreado() throws ClubException{
        clubBienCreado = new ClubDeportivo("Facultad", 3);
        String[] datos = {"1", "tenis", "150", "2", "100"};
        String[] datos2 = {"2", "futbol", "100", "3", "33"};
        clubBienCreado.anyadirActividad(datos);
        clubBienCreado.anyadirActividad(datos2);
    }

    @Test
    public void ClubDeportivoConNGruposMenorQue1(){
        assertThrows(ClubException.class, () -> {
            new ClubDeportivo("Madrid", 0);
        });
    }

    @Test
    public void ClubDeportivoAnyadirDatosMenorQue5(){
        //Arrange
        String[] datos = {"1", "tenis", "150", "1"};
        assertThrows(ClubException.class, () -> clubBienCreado.anyadirActividad(datos));
    }

    @Test 
    public void ClubDeportivoAnyadirActividadDatosIncorrectos() throws ClubException{
        //Arrange
        String[] datos = {"1", "tenis", "99", "1", "a"};
        ClubDeportivo clubBienCreado2TamAuto = new ClubDeportivo("Facultad");
        //Act
        //Assert
        assertThrows(ClubException.class, () -> clubBienCreado2TamAuto.anyadirActividad(datos));
    }

    @Test
    public void ClubDeportivoAnyadirActividadGrupoNulo(){
        //Arrange
        Grupo g = null;
        assertThrows(ClubException.class, () -> clubBienCreado.anyadirActividad(g));
    }

    @Test
    public void ClubDeportivoAnyadirActividadGrupoNuevo(){
        try{
            //Arrange
            Grupo g = new Grupo("3", "baloncesto", 30, 8, 60.0);
            //Act
            clubBienCreado.anyadirActividad(g);
            String expected = "(3 - baloncesto - 60.0 euros - P:30 - M:8) ]";
            int index = clubBienCreado.toString().indexOf("(3 - baloncesto");
            //Assert
            assertEquals(expected, clubBienCreado.toString().substring(index));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void ClubDeportivoAnyadirActividadGrupoExistente(){
        try{
            //Arrange
            Grupo g = new Grupo("1", "tenis", 20, 12, 80.0);
            //Act
            clubBienCreado.anyadirActividad(g);
            String expected = "P:20";
            int indexInicio = clubBienCreado.toString().indexOf("100.0 euros") + 14;
            int indexFin = indexInicio + 4;
            //Assert
            assertEquals(expected, clubBienCreado.toString().substring(indexInicio, indexFin));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void ClubDeportivoAnyadirActividadGrupoExistenteConMasMatriculados(){
        try{
            //Arrange
            Grupo g = new Grupo("1", "tenis", 1, 0, 80.0);
            //Act
            //Assert
            assertThrows(ClubException.class, () -> clubBienCreado.anyadirActividad(g));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test 
    public void ClubDeportivoIngresos(){

        //Arrange
        //Act
        double res = clubBienCreado.ingresos();
        double cantidadNormal = 100*2 + 3*33; // hemos metido dos grupos, uno con 2 personas que pagan cada una 100 y otro grupo con 3 personas que pagan cada una 33
        //Assert
        assertEquals(res,cantidadNormal);
    }

    @Test 
    public void ClubDeportivoPlazasLibresSinGrupo(){
        try {
            //Arrange
            ClubDeportivo clubSinGrupo = new ClubDeportivo("Facultad2", 3);
            //Act
            double res = clubSinGrupo.plazasLibres("tenis");
            double cantidadNormal = 0;
            //Assert
            assertEquals(res,cantidadNormal);
        } catch (ClubException e) {
            e.printStackTrace();
        }
    }

    @Test 
    public void ClubDeportivoPlazasLibresGrupoNoEncontrado(){

        //Arrange
        //Act
        double res = clubBienCreado.plazasLibres("tens");
        double cantidadNormal = 0;
        //Assert
        assertEquals(res,cantidadNormal);
    }

    @Test 
    public void ClubDeportivoPlazasLibresConGrupo(){

        //Arrange
        //Act
        double res = clubBienCreado.plazasLibres("tenis");
        double cantidadNormal = 20 - 2;
        //Assert
        assertEquals(res, cantidadNormal);
    }

    @Test
    public void ClubDeportivoMatricularMenosPlazasQuePersonas(){

        //Arrange
        //Act
        int plazas = clubBienCreado.plazasLibres("tenis");
        //Assert
        assertThrows(ClubException.class, () -> clubBienCreado.matricular("tenis", plazas + 1));
    }

    @Test
    public void ClubDeportivoMatricularPlazasSuficientes(){
        try {
            //Arrange
            int plazasIniciales = clubBienCreado.plazasLibres("tenis");
            //Act
            int diferencia = 2;
            clubBienCreado.matricular("tenis", plazasIniciales - diferencia);
            int res = clubBienCreado.plazasLibres("tenis");
            //Assert
            assertEquals(res, diferencia);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void ClubDeportivoMatricularEnVariosGrupos(){
        try {
            //Arrange
            String datos[] = {"4A", "padel", "15", "0", "30.0"};
            String datos2[] = {"4B", "padel", "20", "0", "30.0"};
            //Act
            clubBienCreado.anyadirActividad(datos);
            clubBienCreado.anyadirActividad(datos2);
            clubBienCreado.matricular("padel", 30);
            //Assert
            assertEquals(5, clubBienCreado.plazasLibres("padel"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void ClubDeportivoMatricular0Personas(){
        try {
            //Arrange
            int plazasIniciales = clubBienCreado.plazasLibres("tenis");
            //Act
            clubBienCreado.matricular("tenis", 0);
            //Assert
            assertEquals(plazasIniciales, clubBienCreado.plazasLibres("tenis"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void ClubDeportivoMatricularPersonasJustas(){
        try {
            //Arrange
            int plazasIniciales = clubBienCreado.plazasLibres("futbol");
            //Act
            clubBienCreado.matricular("futbol", plazasIniciales);
            //Assert
            assertEquals(0, clubBienCreado.plazasLibres("futbol"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

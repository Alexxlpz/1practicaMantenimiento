package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> clubBienCreado.anyadirActividad(datos));
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
        double cantidadNormal = 150 - 2; // hemos metido dos grupos, uno con 2 personas que pagan cada una 100 y otro grupo con 3 personas que pagan cada una 33
        //Assert
        assertEquals(res,cantidadNormal);
    }

}

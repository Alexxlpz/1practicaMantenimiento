package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAltoRendimientoTest {
    
    static ClubDeportivoAltoRendimiento clubBienCreado;

    @BeforeAll
    static public void clubAltoRendimientoBienCreado() throws ClubException{
        clubBienCreado = new ClubDeportivoAltoRendimiento("univesidad de malaga", 11, 100, 3);
        String[] datos = {"1", "tenis", "150", "2", "100"};
        String[] datos2 = {"2", "futbol", "100", "3", "33"};
        clubBienCreado.anyadirActividad(datos);
        clubBienCreado.anyadirActividad(datos2);
    }

    @Test 
    public void ClubAltoRendimientoConMaximoMenorOIgualA0(){
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("prueba1", -1, 33));
    }

    @Test 
    public void ClubAltoRendimientoConIncrementoMenorOIgualA0(){
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("prueba1", 33, -1));
    }
    
    @Test 
    public void ClubAltoRendimientoConTamConMaximoMenorOIgualA0(){
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("prueba2", 11, -1, 33));
    }

    @Test 
    public void ClubAltoRendimientoConTamConIncrementoMenorOIgualA0(){
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("prueba2", 11, 33, -1));
    }
    
    @Test 
    public void ClubAltoRendimientoAnyadirActividadDatosMenorQue5(){
        //Arrange
        String[] datos = {"1", "tenis", "150", "1"};
        //Act
        //Assert
        assertThrows(ClubException.class, () -> clubBienCreado.anyadirActividad(datos));
    }

    @Test 
    public void ClubAltoRendimientoAnyadirActividadDatosIncorrectos() throws ClubException{
        //Arrange
        String[] datos = {"1", "tenis", "99", "1", "a"};
        ClubDeportivoAltoRendimiento clubBienCreado2TamAuto = new ClubDeportivoAltoRendimiento("univesidad de malaga", 100, 3);
        //Act
        //Assert
        assertThrows(ClubException.class, () -> clubBienCreado2TamAuto.anyadirActividad(datos));
    }

    @Test 
    public void ClubAltoRendimientoIngresos(){

        //Arrange
        //Act
        double res = clubBienCreado.ingresos();
        double cantidadNormal = 100*2 + 3*33; // hemos metido dos grupos, uno con 2 personas que pagan cada una 100 y otro grupo con 3 personas que pagan cada una 33
        //Assert
        assertEquals(res,cantidadNormal+(cantidadNormal*(3.0/100)));

    }

}

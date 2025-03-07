package clubdeportivo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;

public class GrupoTest {
    
    static Grupo grupoBienCreado;

    @BeforeAll
    static public void initGrupoBienCreado() throws ClubException{
        grupoBienCreado = new Grupo("1","tenis", 50, 1, 100);
    }


    @Test
    public void GrupoConNplazasMenorQue0(){
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new Grupo("1","tenis", -1, 1, 1));      
    }

    @Test
    public void GrupoConMatriculadosMenorQue0(){
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new Grupo("1","tenis", 1, -1, 1));      
    }

    @Test
    public void GrupoConTarifaMenorQue0(){
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new Grupo("1","tenis", 1, 1, -1));      
    }

    @Test
    public void GrupoConMasMatriculadosQuePlazas(){
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new Grupo("1","tenis", 1, 3, 1));      
    }

    @Test
    public void GrupoActualizarPlazasConPlazasNegativas(){
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> grupoBienCreado.actualizarPlazas(-1));
    }

    @Test
    public void GrupoActualizarPlazasMasMatriculadosQuePlazas(){
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> grupoBienCreado.actualizarPlazas(1));
    }

    @Test
    public void GrupoActualizarPlazasBienHecho(){
        //Arrange
        try {
            //Act
			grupoBienCreado.actualizarPlazas(51);
		} catch (ClubException e) {
			e.printStackTrace();
		}finally{
            //Assert
            assertEquals(grupoBienCreado.getPlazas(), 51);
        }
    }

    @Test
    public void GrupoMatricularConNumeroMatriculadosNegativo(){
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> grupoBienCreado.matricular(-1));
    }

    @Test
    public void GrupoMatricularConMasMatriculadosQuePlazas(){
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> grupoBienCreado.matricular(51));
    }

    @Test
    public void GrupoMatricularBienHecho(){
        //Arrange
        int matriculadosAntes = grupoBienCreado.getMatriculados();
        try {
            //Act
			grupoBienCreado.matricular(33);
		} catch (ClubException e) {
			e.printStackTrace();
		}finally{
            //Assert
            assertEquals(grupoBienCreado.getMatriculados(), 33 + matriculadosAntes);
        }
    }
}
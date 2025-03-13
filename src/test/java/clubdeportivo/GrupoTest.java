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
    public void GrupoGetCodigoFunciona(){
        assertEquals("1", grupoBienCreado.getCodigo());
    }

    @Test
    public void GrupoHashChodeFunciona(){
        assertEquals("1".hashCode() + "TENIS".hashCode(), grupoBienCreado.hashCode());
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

    @Test
    public void GrupoEqualsConOtroTipo(){
        assertEquals(false, grupoBienCreado.equals("otra cosa"));
    }

    @Test
    public void GrupoEqualsBienHecho(){
        try{        
            //Arrange
            Grupo grupo2 = new Grupo("1", "tenis", 20, 15, 30.0);
            //Act
            //Assert
            assertEquals(true, grupoBienCreado.equals(grupo2));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void GrupoEqualsFallaCodigo(){
        try{        
            //Arrange
            Grupo grupo2 = new Grupo("2", "tenis", 20, 15, 30.0);
            //Act
            //Assert
            assertEquals(false, grupoBienCreado.equals(grupo2));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void GrupoEqualsFallaActividad(){
        try{        
            //Arrange
            Grupo grupo2 = new Grupo("1", "NoEsTenis", 20, 15, 30.0);
            //Act
            //Assert
            assertEquals(false, grupoBienCreado.equals(grupo2));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
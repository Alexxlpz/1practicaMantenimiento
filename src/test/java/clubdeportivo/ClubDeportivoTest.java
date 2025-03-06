package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    
    @Test
    public void ClubDeportivoConNGruposMenosQue1(){
        assertThrows(ClubException.class, () -> {
            new ClubDeportivo("Madrid", 0);
        });
    }

    @Test
    public void ClubDeportivoAnyadir(){
        //TODO
    }

}

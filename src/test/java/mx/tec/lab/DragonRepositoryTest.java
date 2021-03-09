package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.*;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mx.tec.lab.entity.Dragon;
import mx.tec.lab.repository.DragonRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DragonRepositoryTest {
	@Resource
	private DragonRepository dragonRepository;
	
	@Test
	public void givenDragon_whenSave_thenRetrieveSame() {
		Dragon dragon1 = new Dragon(1, "Meraxes");
		dragonRepository.save(dragon1);
		
		Dragon retrievedDragon = dragonRepository.findById(1L).orElse(null);
		assertEquals("Meraxes", retrievedDragon.getName());
	}
	
	//3
	@Test
	public void givenDragon_whenModify_thenRetrieveModification() {
		Dragon dragon2 = new Dragon(2,"Hocicorto");
		dragonRepository.save(dragon2);
		
		Dragon retrievedDragon = dragonRepository.findById(2L).orElse(null);
		
		retrievedDragon.setName("Colacuerno");
		dragonRepository.save(retrievedDragon);
		assertEquals("Colacuerno", retrievedDragon.getName());
	}
	
	@Test
	public void givenDragon_whenDeleted_thenRetrieveNull() {
		Dragon dragon3 = new Dragon(3,"Ridgeback");
		dragonRepository.save(dragon3);
		
		dragonRepository.delete(dragon3);
		
		Dragon retrievedDragon = dragonRepository.findById(3L).orElse(null);
		
		assertNull(retrievedDragon, "The Dragon is not deleted");
	}
	

}

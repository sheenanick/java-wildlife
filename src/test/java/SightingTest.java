import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class SightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void sighting_instantiates_true() {
    Sighting sighting = new Sighting(1, "Zone A", "Walker");
    assertTrue(sighting instanceof Sighting);
  }

  @Test
  public void sighting_getAnimalId_int() {
    Sighting sighting = new Sighting(1, "Zone A", "Walker");
    assertEquals(1, sighting.getAnimalId());
  }

  @Test
  public void sighting_getLocation_string() {
    Sighting sighting = new Sighting(1, "Zone A", "Walker");
    assertEquals("Zone A", sighting.getLocation());
  }

  @Test
  public void sighting_getRangerName_string() {
    Sighting sighting = new Sighting(1, "Zone A", "Walker");
    assertEquals("Walker", sighting.getRangerName());
  }

  @Test
  public void sighting_getTimestamp_true() {
    Sighting sighting = new Sighting(1, "Zone A", "Walker");
    assertTrue(sighting.getTimestamp() instanceof String);
  }

  @Test
  public void sighting_find() {
    Sighting sighting = new Sighting(1, "Zone A", "Walker");
    Sighting sightingTwo = new Sighting(2, "Zone B", "Jones");
    assertEquals(sightingTwo.getId(), Sighting.find(sightingTwo.getId()).getId());
  }

  @Test
  public void sighting_all() {
    Sighting sighting = new Sighting(1, "Zone A", "Walker");
    Sighting sightingTwo = new Sighting(2, "Zone B", "Jones");
    assertEquals(2, Sighting.all().size());
  }

  @Test
  public void sighting_allByAnimal() {
    Sighting sighting = new Sighting(1, "Zone A", "Walker");
    Sighting sightingTwo = new Sighting(2, "Zone B", "Jones");
    assertEquals(1, Sighting.allByAnimal(1).size());
  }

  @Test
  public void sighting_delete() {
    Sighting sighting = new Sighting(1, "Zone A", "Walker");
    sighting.delete();
    assertEquals(0, Sighting.all().size());
  }

  @Test
  public void sighting_update() {
    Sighting sighting = new Sighting(1, "Zone A", "Walker");
    sighting.save();
    sighting.setLocation("Zone B");
    sighting.update();
    assertEquals("Zone B", Sighting.find(sighting.getId()).getLocation());
  }

}

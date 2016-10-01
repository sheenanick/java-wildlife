import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class AnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void animal_instantiates_true() {
    Animal animal = new Animal("bear");
    assertTrue(animal instanceof Animal);
  }

  @Test
  public void animal_getName_string() {
    Animal animal = new Animal("bear");
    assertEquals("bear", animal.getName());
  }

  @Test
  public void animal_getType_string() {
    Animal animal = new Animal("bear");
    assertEquals("non-endangered", animal.getType());
  }

  @Test
  public void animal_find() {
    Animal animal = new Animal("bear");
    animal.save();
    Animal animalTwo = new Animal("coyote");
    animalTwo.save();
    assertEquals(animalTwo.getId(), Animal.find(animalTwo.getId()).getId());
  }

  @Test
  public void animal_all() {
    Animal animal = new Animal("bear");
    animal.save();
    Animal animalTwo = new Animal("coyote");
    animalTwo.save();
    assertEquals(2, Animal.all().size());
  }

  @Test
  public void animal_delete() {
    Animal animal = new Animal("bear");
    animal.save();
    animal.delete();
    assertEquals(0, Animal.all().size());
  }

  @Test
  public void animal_update() {
    Animal animal = new Animal("bear");
    animal.save();
    animal.setName("grizzly bear");
    animal.update();
    assertEquals("grizzly bear", Animal.find(animal.getId()).getName());
  }

}

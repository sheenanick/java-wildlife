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
  public void animal_delete() {
    Animal animal = new Animal("bear");
    animal.delete();
    assertEquals(0, Animal.all().size());
  }

}

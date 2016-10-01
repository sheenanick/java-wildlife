import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class EndangeredTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void endangered_instantiates_true() {
    Endangered endangered = new Endangered("bald eagle", "healthy", "newborn");
    endangered.save();
    assertTrue(endangered instanceof Endangered);
  }

  @Test
  public void endangered_saves_true() {
    Endangered endangered = new Endangered("bald eagle", "healthy", "newborn");
    endangered.save();
    assertTrue(Endangered.all().size() > 0);
  }

  @Test
  public void endangered_getType_string() {
    Endangered endangered = new Endangered("bald eagle", "healthy", "newborn");
    assertEquals("endangered", endangered.getType());
  }

  @Test
  public void endangered_getHealth_string() {
    Endangered endangered = new Endangered("bald eagle", "healthy", "newborn");
    assertEquals("healthy", endangered.getHealth());
  }
  @Test
  public void endangered_getAge_string() {
    Endangered endangered = new Endangered("bald eagle", "healthy", "newborn");
    assertEquals("newborn", endangered.getAge());
  }

  @Test
  public void endangered_setHealth_string() {
    Endangered endangered = new Endangered("bald eagle", "healthy", "newborn");
    endangered.setHealth("ill");
    assertEquals("ill", endangered.getHealth());
  }

  @Test
  public void endangered_setAge_string() {
    Endangered endangered = new Endangered("bald eagle", "healthy", "newborn");
    endangered.setAge("adult");
    assertEquals("adult", endangered.getAge());
  }

  @Test
  public void endangered_update() {
    Endangered endangered = new Endangered("bald eagle", "healthy", "newborn");
    endangered.save();
    endangered.setName("eagle");
    endangered.update();
    assertEquals("eagle", Endangered.find(endangered.getId()).getName());
  }

}

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

}

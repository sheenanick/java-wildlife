import org.sql2o.*;
import java.util.List;

public class Endangered extends Animal {
  public String health;
  public String age;
  public static final String DATABASE_TYPE = "endangered";

  public Endangered(String name, String health, String age) {
    super(name);
    this.health = health;
    this.age = age;
    type = DATABASE_TYPE;
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name, health, age) VALUES (:name, :health, :age)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("health", health)
        .addParameter("age", age)
        .executeUpdate()
        .getKey();
    }
  }

}

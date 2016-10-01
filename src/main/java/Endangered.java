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

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  public void setHealth(String health) {
    this.health = health;
  }

  public void setAge(String age) {
    this.age = age;
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name, health, age, type) VALUES (:name, :health, :age, :type)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("health", health)
        .addParameter("age", age)
        .addParameter("type", type)
        .executeUpdate()
        .getKey();
    }
  }

  @Override
  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET name = :name, health = :health, age = :age WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("health", health)
        .addParameter("age", age)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}

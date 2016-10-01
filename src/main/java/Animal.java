import org.sql2o.*;
import java.util.List;

public class Animal {
  public int id;
  public String name;
  public String type;
  public static final String DATABASE_TYPE = "non-endangered";

  public Animal(String name) {
    this.name = name;
    type = DATABASE_TYPE;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static Animal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id = :id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Animal.class);
    }
  }

  public static List<Animal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(Animal.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE from animals WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeUpdate();
    }
  }

}

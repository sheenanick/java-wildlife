import org.sql2o.*;
import java.util.List;

public class Sighting {
  private int id;
  private int animalId;
  private String location;
  private String rangerName;

  public Sighting(int animalId, String location, String rangerName) {
    this.animalId = animalId;
    this.location = location;
    this.rangerName = rangerName;
    this.save();
  }

  public int getId() {
    return id;
  }

  public int getAnimalId() {
    return animalId;
  }

  public String getLocation() {
    return location;
  }

  public String getRangerName() {
    return rangerName;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animalid, location, rangername) VALUES (:animalId, :location, :rangerName)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("animalId", this.animalId)
        .addParameter("location", this.location)
        .addParameter("rangerName", this.rangerName)
        .executeUpdate()
        .getKey();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE from sightings WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public static Sighting find(int thisid) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE id = :id";
      return con.createQuery(sql)
        .addParameter("id", thisid)
        .executeAndFetchFirst(Sighting.class);
    }
  }

  public static List<Sighting> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings";
      return con.createQuery(sql)
        .executeAndFetch(Sighting.class);
    }
  }

  public static List<Sighting> allByAnimal(int animalId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE animalid = :animalId";
      return con.createQuery(sql)
        .addParameter("animalId", animalId)
        .executeAndFetch(Sighting.class);
    }
  }

}

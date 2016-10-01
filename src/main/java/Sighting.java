import org.sql2o.*;
import java.util.List;
import java.sql.Timestamp;

public class Sighting {
  private int id;
  private int animalId;
  private String location;
  private String rangerName;
  private Timestamp timestamp;

  public Sighting(int animalId, String location, String rangerName) {
    if (rangerName.equals("")){
      throw new IllegalArgumentException("Please enter Ranger name.");
    }
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

  public String getTimestamp() {
    return String.format("%1$TD %1$TR", timestamp);
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setRangerName(String rangerName) {
    this.rangerName = rangerName;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animalid, location, rangername, timestamp) VALUES (:animalId, :location, :rangerName, now())";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("animalId", this.animalId)
        .addParameter("location", this.location)
        .addParameter("rangerName", this.rangerName)
        .executeUpdate()
        .getKey();
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
      String sql = "SELECT * FROM sightings ORDER BY timestamp DESC";
      return con.createQuery(sql)
        .executeAndFetch(Sighting.class);
    }
  }

  public static List<Sighting> allByAnimal(int animalId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE animalid = :animalId ORDER BY timestamp DESC";
      return con.createQuery(sql)
        .addParameter("animalId", animalId)
        .executeAndFetch(Sighting.class);
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

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE sightings SET location = :location, rangername = :rangername WHERE id = :id";
      con.createQuery(sql)
        .addParameter("location", location)
        .addParameter("rangername", rangerName)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeUpdate();
    }
  }

}

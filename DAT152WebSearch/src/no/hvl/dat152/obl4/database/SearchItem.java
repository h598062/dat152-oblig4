package no.hvl.dat152.obl4.database;

import java.sql.Timestamp;

public class SearchItem {

  private Timestamp datetime;
  private String username;
  private String searchkey;

  public SearchItem(Timestamp datetime, String username, String searchkey) {
    this.datetime = datetime;
    this.username = username;
    this.searchkey = searchkey;
  }

  public Timestamp getDatetime() {
    return datetime;
  }

  public String getUsername() {
    return username;
  }

  public String getSearchkey() {
    return searchkey;
  }

}

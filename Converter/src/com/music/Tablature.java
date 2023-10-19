package com.music;

import java.io.Serializable;
import java.util.List;

public class Tablature implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public String t0;
  
  public String tn;
  
  public String interval;
  
  public List<Note> notes;
  
  public String name;
  
  public String minKey;
  
  public String maxKey;
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getMinKey() {
    return this.minKey;
  }
  
  public void setMinKey(String minKey) {
    this.minKey = minKey;
  }
  
  public String getMaxKey() {
    return this.maxKey;
  }
  
  public void setMaxKey(String maxKey) {
    this.maxKey = maxKey;
  }
  
  public String getT0() {
    return this.t0;
  }
  
  public void setT0(String t0) {
    this.t0 = t0;
  }
  
  public String getTn() {
    return this.tn;
  }
  
  public void setTn(String tn) {
    this.tn = tn;
  }
  
  public String getInterval() {
    return this.interval;
  }
  
  public void setInterval(String interval) {
    this.interval = interval;
  }
  
  public List<Note> getNotes() {
    return this.notes;
  }
  
  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }
}

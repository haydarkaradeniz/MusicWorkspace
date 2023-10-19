package com.music;

import java.io.Serializable;

public class Note implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public String ID;
  
  public String key;
  
  public String t1;
  
  public String t2;
  
  public String hand;
  
  public String finger;
  
  public String desc;
  
  public Note() {}
  
  public Note(String ID, String key, String t1, String t2) {
    this.ID = ID;
    this.key = key;
    this.t1 = t1;
    this.t2 = t2;
  }
  
  public String getID() {
    return this.ID;
  }
  
  public void setID(String ID) {
    this.ID = ID;
  }
  
  public String getKey() {
    return this.key;
  }
  
  public void setKey(String key) {
    this.key = key;
  }
  
  public String getT1() {
    return this.t1;
  }
  
  public void setT1(String t1) {
    this.t1 = t1;
  }
  
  public String getT2() {
    return this.t2;
  }
  
  public void setT2(String t2) {
    this.t2 = t2;
  }
  
  public String getHand() {
    return this.hand;
  }
  
  public void setHand(String hand) {
    this.hand = hand;
  }
  
  public String getFinger() {
    return this.finger;
  }
  
  public void setFinger(String finger) {
    this.finger = finger;
  }
  
  public String getDesc() {
    return this.desc;
  }
  
  public void setDesc(String desc) {
    this.desc = desc;
  }
}

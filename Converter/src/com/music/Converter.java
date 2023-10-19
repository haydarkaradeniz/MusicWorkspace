package com.music;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class Converter {
  public static final int NOTE_ON = 144;
  
  public static final int NOTE_OFF = 128;
  
  public static final int ALL_NOTES_OFF = 88;
  
  public static void main(String[] args) {
    try {
      getMidiNoteMap(args[0]);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  private static void write(String fileName, String content) throws IOException {
    Path targetPath = Paths.get(fileName, new String[0]);
    byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
    Files.write(targetPath, bytes, new OpenOption[] { StandardOpenOption.CREATE });
  }
  
  private static String getLastMember(String str, String regex) {
    String[] temp = str.split(regex);
    return temp[temp.length - 1];
  }
  
  public static void getMidiNoteMap(String fileName) throws InvalidMidiDataException, IOException {
    File midiFile = new File(fileName);
    Sequence sequence = MidiSystem.getSequence(midiFile);
    int haydar = 0;
    byte b1;
    int i;
    Track[] arrayOfTrack1;
    for (i = (arrayOfTrack1 = sequence.getTracks()).length, b1 = 0; b1 < i; ) {
      Track track = arrayOfTrack1[b1];
      System.out.println("track" + ++haydar + " sice: " + track.size());
      b1++;
    } 
    Tablature tablature = new Tablature();
    List<Note> notes = new ArrayList<>();
    int interval = 9999;
    int t0 = 9999;
    int tn = 0;
    Map<Integer, MidiEvent> activeEvents = new HashMap<>();
    int ID = 0;
    int minKey = 9999;
    int maxKey = 0;
    byte b2;
    int j;
    Track[] arrayOfTrack2;
    for (j = (arrayOfTrack2 = sequence.getTracks()).length, b2 = 0; b2 < j; ) {
      Track track = arrayOfTrack2[b2];
      for (int k = 0; k < track.size(); k++) {
        MidiEvent event = track.get(k);
        MidiMessage message = event.getMessage();
        if (message instanceof ShortMessage) {
          ShortMessage sm = (ShortMessage)message;
          if (sm.getCommand() == 144 && sm.getData2() > 0) {
            activeEvents.put(Integer.valueOf(sm.getData1()), event);
          } else if ((sm.getCommand() == 144 && sm.getData2() == 0) || sm.getCommand() == 128) {
            MidiEvent tmp = activeEvents.get(Integer.valueOf(sm.getData1()));
            if (tmp == null) {
              System.err.println("yooooooo!");
            } else {
              notes.add(new Note(String.valueOf(ID++), String.valueOf(sm.getData1()), String.valueOf(tmp.getTick()), String.valueOf(event.getTick())));
              activeEvents.remove(Integer.valueOf(sm.getData1()));
            } 
          } 
        } 
      } 
      b2++;
    } 
    for (Note note : notes) {
      int tt1 = Integer.parseInt(note.getT1());
      int tt2 = Integer.parseInt(note.getT2());
      int key = Integer.parseInt(note.getKey());
      if (tt1 < t0)
        t0 = tt1; 
      if (tt2 - tt1 < interval)
        interval = tt2 - tt1; 
      if (tt2 > tn)
        tn = tt2; 
      if (key < minKey)
        minKey = key; 
      if (key > maxKey)
        maxKey = key; 
    } 
    tablature.setNotes(notes);
    tablature.setT0(String.valueOf(t0));
    tablature.setTn(String.valueOf(tn));
    tablature.setInterval(String.valueOf(interval));
    tablature.setName(getLastMember(fileName, "\\\\").split("\\.")[0]);
    tablature.setMinKey(String.valueOf(minKey));
    tablature.setMaxKey(String.valueOf(maxKey));
    System.out.println("remaining size: " + activeEvents.size());
    Gson gson = new Gson();
    String jsonInString = "var tab = " + gson.toJson(tablature);
    write(String.valueOf(fileName.replaceAll("\\.", "_")) + ".js", jsonInString);
  }
}

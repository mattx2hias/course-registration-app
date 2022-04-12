package dev.matthias.entities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Course {
    private String id;
    private String name;
    private String description;
    private long start;
    private long end;
    private int capacity;

    public Course() {}

    public Course(String id, String name, String description, LocalDate startDate, LocalDate endDate, int capacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        ZoneId zoneId = ZoneId.systemDefault();
        this.start = startDate.atStartOfDay(zoneId).toEpochSecond();
        this.end = endDate.atStartOfDay(zoneId).toEpochSecond();
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.toUpperCase(Locale.ROOT);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getStart() {
        return start;
    }

    public String getFormattedDate(long startOrEnd) {
        LocalDateTime ltd = Instant.ofEpochSecond(startOrEnd).atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");
        return ltd.format(dtf);
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        StringBuilder spacedString = new StringBuilder();
        spacedString.append(id);
        int length = id.length();
        while(length < 7) {
            spacedString.append(" ");
            length++;
        }
        spacedString.append(" | ").append(name);
        length = name.length();
        while(length < 30) {
            spacedString.append(" ");
            length++;
        }
        String formattedDate = this.getFormattedDate(this.start) + "-" + this.getFormattedDate(this.end);
        spacedString.append(" | ").append(formattedDate);
        length = formattedDate.length();
        while(length < 21) {
            spacedString.append(" ");
            length++;
        }
        return spacedString + " | " + this.capacity;
    }
}

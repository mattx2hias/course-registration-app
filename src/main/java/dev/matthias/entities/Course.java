package dev.matthias.entities;

import java.time.LocalDate;
import java.util.Locale;

public class Course {
    private String id;
    private String name;
    private String description;
    private long start;
    private long end;
    private int fId;
    private int capacity;

    public Course() {}

    public Course(String id, String name, String description, LocalDate startDate, LocalDate endDate, int fId, int capacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start = startDate.toEpochDay();
        this.end = endDate.toEpochDay();
        this.fId = fId;
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

    public int getfId() {
        return this.fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }
}

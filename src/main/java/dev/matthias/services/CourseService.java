package dev.matthias.services;

public interface CourseService {
    boolean atCapacity(String cId);

    boolean afterStartDate(String cId);
}

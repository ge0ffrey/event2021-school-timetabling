package org.acme.schooltimetabling.rest;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.acme.schooltimetabling.domain.Lesson;
import org.acme.schooltimetabling.domain.TimeTable;
import org.acme.schooltimetabling.persistence.LessonRepository;
import org.acme.schooltimetabling.persistence.RoomRepository;
import org.acme.schooltimetabling.persistence.TimeslotRepository;

import io.quarkus.panache.common.Sort;

@Path("timeTable")
public class TimeTableResource {

    @Inject
    TimeslotRepository timeslotRepository;
    @Inject
    RoomRepository roomRepository;
    @Inject
    LessonRepository lessonRepository;

    @GET
    public TimeTable getTimeTable() {
        return loadTimeTable();
    }

    @POST
    @Path("solve")
    public void solve() {
        throw new UnsupportedOperationException("TODO");
    }

    @POST
    @Path("stopSolving")
    public void stopSolving() {
        throw new UnsupportedOperationException("TODO");
    }

    @Transactional
    protected TimeTable loadTimeTable() {
        return new TimeTable(
                timeslotRepository.listAll(Sort.by("dayOfWeek").and("startTime").and("endTime").and("id")),
                roomRepository.listAll(Sort.by("name").and("id")),
                lessonRepository.listAll(Sort.by("subject").and("teacher").and("studentGroup").and("id")));
    }

    @Transactional
    protected void save(TimeTable timeTable) {
        for (Lesson lesson : timeTable.getLessonList()) {
            Lesson attachedLesson = lessonRepository.findById(lesson.getId());
            attachedLesson.setTimeslot(lesson.getTimeslot());
            attachedLesson.setRoom(lesson.getRoom());
        }
    }

}

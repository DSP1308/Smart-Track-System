package Backend_java;

import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.model.*;
import com.google.api.services.classroom.Classroom.Courses;
import com.google.api.services.classroom.ClassroomScopes;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ClassroomTasksFetcher {

    public static void fetchTasks() throws IOException {
        Classroom service = GoogleCalendarConnect.getClassroomService();

        // List first 5 courses
        ListCoursesResponse coursesResponse = service.courses().list()
                .setPageSize(5)
                .execute();
        List<Course> courses = coursesResponse.getCourses();

        if (courses == null || courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("Courses:");
        for (Course course : courses) {
            System.out.printf("%s (%s)\n", course.getName(), course.getId());
        }
    }
}

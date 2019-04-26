package Courses;

import Competitors.Team;
import Competitors.Competitor;

public class Course {
    private Obstacle[] course;

    public Course(Obstacle... course){
        this.course = course;
    }

    public void doIt(Team team){
        Competitor[] competitors = team.getCompetitors();
        for (Competitor c : competitors) {
            for (Obstacle o : course) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
    }
}

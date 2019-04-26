import Competitors.Cat;
import Competitors.Dog;
import Competitors.Human;
import Competitors.Team;
import Courses.Course;
import Courses.Cross;
import Courses.Wall;

public class Main {
    public static void main(String[] args) {
        Course c = new Course(new Cross(80), new Wall(2), new Wall(1), new Cross(120)); // Создаем полосу препятствий
        Team team = new Team("Winners", new Human("Боб"), new Cat("Барсик"), new Cat("Василий"), new Dog("Бобик")); // Создаем команду
        c.doIt(team); // Просим команду пройти полосу
        team.showResults(); // Показываем результаты



//        Competitor[] competitors = {new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")};
//        Obstacle[] course = {new Cross(80), new Wall(2), new Wall(1), new Cross(120)};
//        for (Competitor c : competitors) {
//            for (Obstacle o : course) {
//                o.doIt(c);
//                if (!c.isOnDistance()) break;
//            }
//        }
//        for (Competitor c : competitors) {
//            c.info();
//        }
    }
}
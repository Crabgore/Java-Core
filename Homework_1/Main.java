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
    }
}
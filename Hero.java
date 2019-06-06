package Game;

import java.util.Random;

class Team{
    String name;
    Hero[] heroes;

    public Team(String name, Hero...heroes) {
        this.name = name;
        this.heroes = heroes;
    }

    public String getName() {
        return name;
    }

    public Hero[] getHeroes() {
        return heroes;
    }
}

abstract class Hero {

    protected int health;
    protected String name;
    protected int damage;
    protected int addHeal;

    public Hero(int health, String name, int damage, int addHeal) {
        this.health = health;
        this.name = name;
        this.damage = damage;
        this.addHeal = addHeal;
    }

    // метод для удара
    abstract void hit(Hero hero);

    // метод для лечения
    abstract void healing(Hero hero);

    // метод для нанесения урона
    void causeDamage(int damage) {
        if(health < 0) {
            System.out.println("Герой уже мертвый!");
        } else {
            health -= damage;
        }

    }

    void addHealth(int health) {
        this.health += health;
    }

    void info() {

        System.out.println(name + " " + (health < 0 ? "Герой мертвый" : health) + " " + damage);
    }
}

class Warrior extends Hero {

    public Warrior(int health, String type, int damage, int addHeal) {
        super(health, type, damage, addHeal);
    }

    @Override
    void hit(Hero hero) {
        if (hero != this) {
            if(health < 0) {
                System.out.println("Герой погиб и бить не может!");
            } else {
                hero.causeDamage(damage);
            }
            System.out.println(this.name + " нанес урон " + hero.name);
        }
    }

    @Override
    void healing(Hero hero) {
        System.out.println("Войны не умеют лечить!");
    }
}

class Assassin extends Hero {
    // критический урон
    int criticalHit;
    Random random = new Random();

    public Assassin(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
        this.criticalHit = random.nextInt(20);
    }

    @Override
    void hit(Hero hero) {
        if (hero != this) {
            if(health < 0) {
                System.out.println("Герой погиб и бить не может!");
            } else {
                hero.causeDamage(damage + criticalHit);
            }
            System.out.println(this.name + " нанес урон " + hero.name);
        }
    }

    @Override
    void healing(Hero hero) {
        System.out.println("Убийцы не умеют лечить!");
    }
}

class Healer extends Hero {

    public Healer(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
    }

    @Override
    void hit(Hero hero) {
        System.out.println("Доктор не может бить!");
    }

    @Override
    void healing(Hero hero) {
        hero.addHealth(addHeal);
        System.out.println(this.name + " отхилил " + hero.name);
    }
}


class Game {
    public static void main(String[] args) {

        Random randomStep = new Random();

        Team team1 = new Team("Повелители арены", new Warrior(250, "Тигрил", 50, 0)
                , new Assassin(150, "Акали", 70, 0)
                , new Healer(120, "Жанна", 0, 60));

        Team team2 = new Team("Пустотные уничтожители", new Warrior(290, "Минотавр", 60, 0)
                , new Assassin(160, "Джинкс", 90, 0)
                , new Healer(110, "Зои", 0, 80));

        System.out.println(team1.getName()+":");
        for (Hero t1: team1.getHeroes()) {
            t1.info();
        }

        System.out.println(team2.getName()+":");
        for (Hero t2: team2.getHeroes()) {
            t2.info();
        }

        System.out.println("---------------");

        int count = 0;

        // 3 раунда
        while (true){
            boolean endGame = false;
            for (int i = 0; i < team1.getHeroes().length; i++) {
                if(randomStep.nextInt(2) == 0) {
                    // если доктор то только хилим
                    if(team1.getHeroes()[i] instanceof Healer) {
                        int healthAmount = 1000;
                        for (int j = 0; j < team1.getHeroes().length; j++) {
                            if (team1.getHeroes()[j].health<healthAmount){
                                healthAmount = team1.getHeroes()[j].health;
                            }
                        }
                        for (int k = 0; k < team1.getHeroes().length; k++) {
                            if (team1.getHeroes()[k].health==healthAmount){
                                team1.getHeroes()[i].healing(team1.getHeroes()[k]);
                            }
                        }
                    } else {
                        // удар по противнику
                        team1.getHeroes()[i].hit(team2.getHeroes()[i]);
                        if (team2.getHeroes()[i].health<=0) {
                            System.out.println(team2.getHeroes()[i].name+" мёртв!");
                            System.out.println("Команда " + team1.getName() + " побеждает!");
                            endGame = true;
                            break;
                        }
                    }
                } else {
                    if(team2.getHeroes()[i] instanceof Healer) {
                        int healthAmount = 1000;
                        for (int j = 0; j <team2.getHeroes().length ; j++) {
                            if (team2.getHeroes()[j].health<healthAmount){
                                healthAmount = team2.getHeroes()[j].health;
                            }
                        }
                        for (int k = 0; k < team2.getHeroes().length; k++) {
                            if (team2.getHeroes()[k].health==healthAmount){
                                team2.getHeroes()[i].healing(team2.getHeroes()[k]);
                            }
                        }
                    } else {
                        team2.getHeroes()[i].hit(team1.getHeroes()[i]);
                        if (team1.getHeroes()[i].health<=0) {
                            System.out.println(team1.getHeroes()[i].name+" мёртв!");
                            System.out.println("Команда " + team2.getName() + " побеждает!");
                            endGame = true;
                            break;
                        }
                    }
                }
                System.out.println(team1.getName()+":");
                for (Hero t1: team1.getHeroes()) {
                    t1.info();
                }

                System.out.println(team2.getName()+":");
                for (Hero t2: team2.getHeroes()) {
                    t2.info();
                }
                System.out.println("---------------");
                count++;
            }
            if (endGame) break;
        }
        System.out.println("Бой занял "+count+" ходов.");

        System.out.println("---------------");
        System.out.println("Итоги боя:");

        System.out.println(team1.getName()+":");
        for (Hero t1: team1.getHeroes()) {
            t1.info();
        }

        System.out.println(team2.getName()+":");
        for (Hero t2: team2.getHeroes()) {
            t2.info();
        }
    }
}

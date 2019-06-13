import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

class Team{
    String name;
    Hero[] heroes;

    public Team(String name, Hero...heroes) {
        this.name = name;
        this.heroes = heroes;
    }

    public Team() {
    }

    public String getName() {
        return name;
    }

    public Hero[] getHeroes() {
        return heroes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeroes(Hero...heroes) {
        this.heroes = heroes;
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

    String info() {
        String info = (name + " " + (health < 0 ? "Герой мертвый" : health) + " " + damage);
        return info;
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
            App.log.append(this.name + " нанес урон " + hero.name + "\n");
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
            App.log.append(this.name + " нанес урон " + hero.name + "\n");
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
        App.log.append(this.name + " отхилил " + hero.name + "\n");
    }
}

class GameProcess{
    public GameProcess(String hero1, String hero2, String hero3, String hero4, String hero5, String hero6){

        String[] names = {"Азир", "Акали", "Алистар", "Амуму", "Анивия", "Ари", "Атрокс", "Аурелион Сол", "Бард", "Блицкранк",
                "Браум", "Брэнд", "Вай", "Варвик", "Варус", "Вейгар", "Вейн", "Вел'Коз", "Виктор", "Владимир", "Волибир"};

        Random randomStep = new Random();

        Team team1 = new Team();
        Team team2 = new Team();

        team1.setHeroes(addHeroes(hero1, getName(names)), addHeroes(hero2, getName(names)), addHeroes(hero3, getName(names)));
        team2.setHeroes(addHeroes(hero4, getName(names)), addHeroes(hero5, getName(names)), addHeroes(hero6, getName(names)));

        App.log.append("Красная команда:" + "\n");
        for (Hero t1: team1.getHeroes()) {
            App.log.append(t1.info() + "\n");
        }

        App.log.append("Красная команда:" + "\n");
        for (Hero t2: team2.getHeroes()) {
            App.log.append(t2.info() + "\n");
        }

        App.log.append("---------------" + "\n");

        int count = 0;

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
                            App.log.append(team2.getHeroes()[i].name + " мёртв!" + "\n");
                            App.log.append("Красная команда побеждает!" + "\n");
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
                            App.log.append(team1.getHeroes()[i].name + " мёртв!" + "\n");
                            App.log.append("Синяя команда побеждает!" + "\n");
                            endGame = true;
                            break;
                        }
                    }
                }
                App.log.append("Красная команда:" + "\n");
                for (Hero t1: team1.getHeroes()) {
                    App.log.append(t1.info() + "\n");
                }

                App.log.append("Красная команда:" + "\n");
                for (Hero t2: team2.getHeroes()) {
                    App.log.append(t2.info() + "\n");
                }

                App.log.append("---------------" + "\n");
                count++;
            }
            if (endGame) break;
        }
        App.log.append("Бой занял "+count+" ходов." + "\n");
        App.log.append("---------------" + "\n");
        App.log.append("Итоги боя:" + "\n");

        App.log.append("Красная команда:" + "\n");
        for (Hero t1: team1.getHeroes()) {
            App.log.append(t1.info() + "\n");
        }

        App.log.append("Красная команда:" + "\n");
        for (Hero t2: team2.getHeroes()) {
            App.log.append(t2.info() + "\n");
        }

        App.log.append("\n");
    }

    public static Hero addHeroes(String prof, String name){
        Hero hero = null;
        int warriorHealth = (int)(Math.random()*(290-250))+250;
        int warriorDamage = (int)(Math.random()*(60-50))+50;
        int assassinHealth = (int)(Math.random()*(160-150))+150;
        int assassinDamage = (int)(Math.random()*(90-70))+70;
        int healerHealth = (int)(Math.random()*(120-110))+110;
        int healerHeal = (int)(Math.random()*(80-60))+60;
        if (prof.equals("воин") || prof.equals("Воин")) {
            hero = new Warrior(warriorHealth, name, warriorDamage, 0);
        } else if (prof.equals("убийца") || prof.equals("Убийца")) {
            hero = new Assassin(assassinHealth, name, assassinDamage, 0);
        } else if (prof.equals("лекарь") || prof.equals("Лекарь")) {
            hero = new Healer(healerHealth, name, 0, healerHeal);
        }
        return hero;
    }

    public static String getName(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}

class App extends JFrame {

    public static StringBuilder log = new StringBuilder();
    int counter = 1;

    public App() {
        super("Битвы героев");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(425, 805);

        String[] members = {"Воин", "Убийца", "Лекарь"};

        JPanel grid1 = new JPanel(new GridLayout(3, 2, 10, 10));
        JPanel grid2 = new JPanel(new GridLayout(1, 2, 10, 10));

        JLabel team1 = new JLabel("Красная команда");
        team1.setPreferredSize(new Dimension(195, 15));
        JLabel team2 = new JLabel("Синяя команда");
        team2.setPreferredSize(new Dimension(195, 15));
        JLabel t1 = new JLabel("Состав красной команды");
        t1.setPreferredSize(new Dimension(195, 15));
        JLabel t2 = new JLabel("Состав синей команды");
        t2.setPreferredSize(new Dimension(195, 15));
        JLabel logs = new JLabel("Ход игры");
        logs.setPreferredSize(new Dimension(400, 15));
        JComboBox t1Box = new JComboBox(members);
        JComboBox t2Box = new JComboBox(members);
        JTextArea t1Members = new JTextArea();
        t1Members.setPreferredSize(new Dimension(195, 50));
        JTextArea t2Members = new JTextArea();
        t1Members.setPreferredSize(new Dimension(195, 50));
        JTextArea logsArea = new JTextArea(String.valueOf(log));
        JScrollPane scrollPane = new JScrollPane(logsArea);
        scrollPane.setPreferredSize(new Dimension(400, 500));
        JButton start = new JButton("Начать бой!");
        start.setPreferredSize(new Dimension(400, 30));
        JButton fromTheBegining = new JButton("Начать заново");
        fromTheBegining.setPreferredSize(new Dimension(400, 30));

        grid1.add(team1);
        grid1.add(team2);
        grid1.add(t1Box);
        grid1.add(t2Box);
        grid1.add(t1);
        grid1.add(t2);
        grid2.add(t1Members);
        grid2.add(t2Members);

        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hero = t1Box.getSelectedItem() + "\n";
                t1Members.append(hero);
                String[] x = t1Members.getText().split("\n");
                if (x.length>=3) t1Box.removeAllItems();
            }
        };

        ActionListener listener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hero = t2Box.getSelectedItem() + "\n";
                t2Members.append(hero);
                String[] x = t2Members.getText().split("\n");
                if (x.length>=3) t2Box.removeAllItems();
            }
        };

        ActionListener startGame = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] members1 = t1Members.getText().split("\n");
                String[] members2 = t2Members.getText().split("\n");
                if (members1.length==3 && members2.length==3) {
                    logsArea.setText(null);
                    log.append("Бой №" + counter + "\n");
                    log.append("-----------------------------------------------------------------------------------------------" + "\n");
                    counter++;
                    new GameProcess(members1[0], members1[1], members1[2], members2[0], members2[1], members2[2]);
                }
                else logsArea.setText("В каждой команде должно быть 3 бойца!");
                logsArea.append(String.valueOf(log));
            }
        };

        ActionListener again = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t1Box.getItemCount()==0 && t2Box.getItemCount()==0) {
                    logsArea.setText(null);
                    t1Members.setText(null);
                    t2Members.setText(null);
                    t1Box.addItem("Воин");
                    t2Box.addItem("Воин");
                    t1Box.addItem("Убийца");
                    t2Box.addItem("Убийца");
                    t1Box.addItem("Лекарь");
                    t2Box.addItem("Лекарь");
                    t1Members.setText(null);
                    t2Members.setText(null);
                }
            }
        };

        t1Box.addActionListener(listener1);
        t2Box.addActionListener(listener2);
        start.addActionListener(startGame);
        fromTheBegining.addActionListener(again);

        JPanel grid = new JPanel(new FlowLayout(FlowLayout.LEFT));

        grid.add(grid1);
        grid.add(grid2);
        grid.add(start);
        grid.add(logs);
        grid.add(scrollPane);
        grid.add(fromTheBegining);

        super.add(grid);

        setVisible(true);
    }
}

class Game {
    public static void main(String[] args) {
        new App();
    }
}
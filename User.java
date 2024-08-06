public class User {
    private String name;
    private int age;
    private int weight;
    private int height;  

    public User(String name, int age, int weight, int height){
        this.name = name; 
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    //GETTERS
    public String get_name(){return name;}
    public int get_age(){return age;}
    public int get_weight(){return weight;}
    public int get_height(){return height;}

    //SETTERS
    public void set_name(String name){this.name = name;}
    public void set_age(int age){this.age = age;}
    public void set_weight(int weight){this.weight = weight;}
    public void set_height(int height){this.height = height;}

}
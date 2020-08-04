class Bird {

    public Bird(){
        sayName();
    }

    public void sayName(){
        System.out.println("Bird");
    }
}
class Pegas extends Bird {

    @Override
    public void sayName(){
        System.out.println("Pegas");
    }
}
public class Main {

    public static void main(String[] args) {
        Bird pegas = new Pegas();
    }
}
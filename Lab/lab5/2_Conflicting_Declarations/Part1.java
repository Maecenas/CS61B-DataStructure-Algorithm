/* Part1.java */

public class Part1 {
    public static void main(String[] args) {
        Animal cat1 = new Animal("kitty", 1);
        Dog dog1 = new Dog();
        System.out.println(cat1.getName());
        System.out.println(dog1.getName());

        Dog dog2;
        Animal ani3;
        dog2 = new Dog("five.lb", 5);
        ani3 = dog2;
        System.out.println(dog2.getName());
        System.out.println(ani3.getName());

        Animal ani4;
        Dog dog5;
        ani4 = new Animal("tiger", 10);
        // dog5 = (Dog) ani4;                      // requires a cast to compile.
                                              // and will cause a run-time error, 
                    // because ani4 references an Animal object that isn't a Dog.
        System.out.println(ani4.getName());
        // System.out.println(dog5.getName());


        Dog[] dog_a;
        Animal[] ani_a;
        dog_a = new Dog[3];
        dog_a[0] = new Dog();
        dog_a[1] = new Dog();
        dog_a[2] = new Dog();

        System.out.println(dog_a[0].getName());
        ani_a = dog_a;
        System.out.println(ani_a[0].getName());

        Animal[] ani_a2;
        ani_a2 = new Animal[3];
        ani_a2[0] = new Dog("pig", 3);
        ani_a2[1] = dog_a[1];
        ani_a2[2] = ani_a2[0];
        ani_a2[1] = ani_a2[2];
        System.out.println(ani_a2[0].getName());
        System.out.println(ani_a2[1].getName());
        System.out.println(ani_a2[2].getName());
        dog_a[1] = (Dog) ani_a2[2];
        System.out.println(dog_a[1].getName());
        dog_a[1].bark();


    }
}

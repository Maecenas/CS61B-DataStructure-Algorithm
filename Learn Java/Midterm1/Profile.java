/* Profile.java */

public abstract class Profile implements X {
  protected double weight;              // Maximize my privacy, please.

  public Profile(double weight) {
    this.weight = weight;
  }

  public abstract double date(double food);

  public int date(int food) {
    return (int) date((double) food);
  }

  public String introduce() {
    return "I'm neurotic and vindictive and I weigh " + weight;
  }

  public static void main(String[] args) {
    Profile p = new DatingProfile(300.0);
    ((DatingProfile) p).diet(p.weight, 5.0);
    System.out.println(p.introduce());
  }
}
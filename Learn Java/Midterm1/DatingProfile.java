/* DatingProfile.java */

public class DatingProfile extends Profile {
  public DatingProfile(double weight) {
    super(weight - 70.0);
  }

  public double date(double food) {
    weight = weight + (double) food;
    return weight;
  }

  public void diet(double weight, double loss) {
    weight = weight - loss;
  }

  public String introduce() {
    return "I'm feisty and spontaneous and I weigh " + (weight - 60);
  }

  public void whatever() { }
}
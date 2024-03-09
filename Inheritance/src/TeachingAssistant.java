public class TeachingAssistant extends Academician {
    private int doorNo;

    public TeachingAssistant(String name, String phone, String mail, String department, String title, int doorNo) {
        super(name, phone, mail, department, title);
        this.doorNo = doorNo;
    }

    @Override
    public void entryCourse(String hour) {
        System.out.println(this.getName() + " entered the lesson at " + hour);
    }
}

public class DataProcessing extends Officer {
    private String job;

    public DataProcessing(String name, String phone, String mail, String department, String overtime, String job) {
        super(name, phone, mail, department, overtime);
        this.job = job;
    }

    public void networkSetup() {
        System.out.println(this.getName() + " kurulumu yaptı");
    }

}

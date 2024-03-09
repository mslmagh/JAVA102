public class Officer extends Worker {
    private String department;
    private String overtime;

    public Officer(String name, String phone, String mail, String department, String overtime) {
        super(name, phone, mail);
        this.department = department;
        this.overtime = overtime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    public void work() {
        System.out.println(this.getName() + " officer is working !! ");
    }

    @Override
    public void entry() {
        System.out.println(this.getName() + " entered from the Gate C");
    }   
}

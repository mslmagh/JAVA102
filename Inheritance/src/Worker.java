public class Worker {
    private String name;
    private String phone;
    private String mail;
    public Worker(String name, String phone, String mail) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getMail() {
        return mail;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public void entry(){
        System.out.println(this.name + " entered the university !!");
    }
    public void out(){
        System.out.println(this.name + " outted the university !!");
    }
    public void cafeteria(){
        System.out.println(this.name + " entered the cafeteria !!");
    }
    public static void entriedPeople(Worker[] loginUsers){
        for (Worker worker : loginUsers) {
            worker.entry();
        }
    }
}

package il.ac.tau.cs.sw1.ex9.riddles.third;

public class B3 extends A3 {
    String message;
    public B3(String arg) {
        super(arg);
        this.message = arg;
    }

    @Override
    public void foo(String s) throws B3 {
        if (s.equals(message)) {
            throw new B3(s);
        }
    }

    @Override
    public String getMessage() {
        return message;
    }
}
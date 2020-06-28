package app;

public class IO <T, M> {
    private T input;
    private M output;

    public IO(T input, M output) {
        this.input = input;
        this.output = output;
    }

    public T getInput() {
        return this.input;
    }

    public void setInput(T input) {
        this.input = input;
    }

    public M getOutput() {
        return this.output;
    }

    public void setOutput(M output) {
        this.output = output;
    }

}
package MemoCompiler.AbstractSyntaxTrees;

public abstract class AST {

    private int line;

    public int getLine() {
        return line;
    }

    public AST(int line) {
        this.line = line;
    }

    public abstract Object Visit(Visitor visitor);

    @Override
    public String toString() {
        String[] names = this.getClass().getName().split("\\.");
        return names[names.length - 1];
    }
}

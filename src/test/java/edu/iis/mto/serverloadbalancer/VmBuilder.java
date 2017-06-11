package edu.iis.mto.serverloadbalancer;

/**
 * Created by Patryk on 2017-06-11.
 */
public class VmBuilder implements Builder<Vm> {
    private int size;

    public VmBuilder withSize(int size) {
        this.size = size;
        return this;
    }

    public Vm build() {
        return new Vm(size);
    }

    public static VmBuilder vm() {
        return new VmBuilder();
    }
}

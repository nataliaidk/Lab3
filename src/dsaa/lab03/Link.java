package dsaa.lab03;

public class Link{
    public String ref;
    // in the future there will be more fields
    public Link(String ref) {
        this.ref=ref;
    }
    @Override

    public String toString(){return ref;}
    public boolean equals(Object obj) {
        if (obj instanceof Link) {
            return ref.equals(((Link) obj).ref);
        }
        return false;
    }
    // in the future there will be more fields
}




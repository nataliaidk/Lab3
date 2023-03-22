package dsaa.lab03;


import java.util.Scanner;

public class Document {
    private final String nameofdoc;
    public TwoWayUnorderedListWithHeadAndTail<Link> links;

    public Document(String name, Scanner scan) {
        // TODO
        links = new TwoWayUnorderedListWithHeadAndTail<Link>();
        this.nameofdoc = name;
        load(scan);

    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    private static boolean correctLink(String link) {
        if (link == null || link.isEmpty()) {

            return false;
        }
        for (int i = 0; i < link.length(); i++) {
            char c = link.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {

                return false;
            }
        }
        return Character.isLetter(link.charAt(0));
    }

    public void load(Scanner scan) {
        String link;

        while (!(link = scan.nextLine()).equals("eod")) {


            String[] linki = link.split(" ");
            for (int x = 0; x < linki.length; x++) {
                if (linki[x].toLowerCase().startsWith("link=") && linki[x].length() >= 6) {
                    if (correctLink(linki[x].toLowerCase().substring(5))) {

                        Link newLink = new Link(linki[x].toLowerCase().substring(5));
                        links.add(newLink);

                    }
                }
            }


        }
    }

    public String toString() {
            String str = "Document: " + nameofdoc + "\n";
            for(var link : links) {
                str += link.ref + "\n";
            }
            str = str.strip();
            return str;
        }



    public String toStringReverse() {

     String str = "Document: " + nameofdoc + "\n";
     for (int i = links.size() - 1; i >= 0; i--)
     {
         str += links.get(i).ref + "\n";
     }
     str = str.strip();
     return str;
        }


}


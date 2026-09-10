package main.java.access_modifiers_encapsulation_object_modeling.assigment_problems;

public class MembershipFieldReachChecker {

    static class LibraryMember {
        private String membershipId;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        public LibraryMember(String membershipId, String branchCode,
                             double finesOwed, String displayName) {
            if (membershipId == null ||
                membershipId.trim().length() < 4)
                throw new IllegalArgumentException();

            this.membershipId = membershipId.trim();
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }
    }

    static String classifyAccess(String modifier, String context) {
        if (modifier.equals("public")) return "ALLOWED";

        if (modifier.equals("private"))
            return context.equals("SAME_CLASS") ?
                    "ALLOWED" : "DENIED";

        return context.equals("SAME_CLASS") ||
               context.equals("SAME_PACKAGE") ?
                    "ALLOWED" : "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {
        String[] mods = {
            "private","default","protected","public"
        };

        StringBuilder out = new StringBuilder();

        for (String m : mods) {
            int allowed = 0, denied = 0;

            for (String[] a : attempts) {
                if (!a[0].equals(m)) continue;

                if (classifyAccess(a[0],a[1]).equals("ALLOWED"))
                    allowed++;
                else
                    denied++;
            }

            if (out.length() > 0) out.append(" | ");

            out.append(m).append(": ")
               .append(allowed).append(" allowed / ")
               .append(denied).append(" denied");
        }

        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(
            classifyAccess("private","SAME_CLASS"));

        System.out.println(
            classifyAccess("protected","DIFFERENT_PACKAGE"));

        String[][] a = {
            {"private","SAME_CLASS"},
            {"private","SAME_PACKAGE"},
            {"default","SAME_PACKAGE"},
            {"default","DIFFERENT_PACKAGE"},
            {"protected","SAME_PACKAGE"},
            {"protected","SAME_CLASS"},
            {"public","DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(a));

        try {
            new LibraryMember("LB9","BR1",0,"Priya Nair");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }
}

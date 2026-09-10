package main.java.access_modifiers_encapsulation_object_modeling.assigment_problems;

public class LibraryMemberBean {

    static class LibraryMember {
        private String membershipId;
        private String name;
        private boolean premiumMember;
        private Integer securityAnswerHash;

        public LibraryMember() {
            this(null, null);
        }

        public LibraryMember(String name) {
            this(null, name);
        }

        public LibraryMember(String membershipId, String name) {
            this.name = name;
            setMembershipId(membershipId);
        }

        String getMembershipId() {
            return membershipId;
        }

        void setMembershipId(String id) {
            if (membershipId == null &&
                id != null && !id.trim().isEmpty())
                membershipId = id;
        }

        String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        boolean isPremiumMember() {
            return premiumMember;
        }

        void setPremiumMember(boolean premium) {
            premiumMember = premium;
        }

        void setSecurityAnswer(String answer) {
            if (answer != null)
                securityAnswerHash = answer.hashCode();
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new LibraryMember("Priya Nair").getMembershipId());

        System.out.println(
            new LibraryMember(
                "LIB-8841","Priya Nair").getMembershipId());

        LibraryMember m = new LibraryMember();

        m.setMembershipId("LIB-8841");
        m.setMembershipId("FAKE-0000");

        System.out.println(m.getMembershipId());
    }
}

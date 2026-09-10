package main.java.access_modifiers_encapsulation_object_modeling.class_problems;

public class CrossPackageInheritanceReach {

    static String classifyAccess(String modifier, String context) {
        if (modifier.equals("public"))
            return "ALLOWED";

        if (modifier.equals("private"))
            return context.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

        if (modifier.equals("default"))
            return context.equals("SAME_CLASS") ||
                   context.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";

        if (modifier.equals("protected"))
            return context.equals("SAME_CLASS") ||
                   context.equals("SAME_PACKAGE") ||
                   context.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")
                   ? "ALLOWED" : "DENIED";

        return "DENIED";
    }

    static String describeContext(String context) {
        String[] p = context.toLowerCase().split("_");
        StringBuilder s = new StringBuilder();

        for (String x : p) {
            if (s.length() > 0) s.append(" ");
            s.append(Character.toUpperCase(x.charAt(0)))
             .append(x.substring(1));
        }

        return s.toString();
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess(
            "protected","SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));

        System.out.println(classifyAccess(
            "protected","SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));

        System.out.println(describeContext(
            "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}
